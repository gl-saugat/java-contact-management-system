package com.contactManagement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Process {


    public HashMap<Integer, Contacts> contacts;

    ObjectMapper mapper = new ObjectMapper();

    private File file;


    public Process(){

        contacts = new HashMap<>();

        Path filePath = Paths.get("contacts.json");
        createNewFile(filePath);
        this.file = filePath.toFile();

        loadContacts();
    }

    private void createNewFile(Path filePath){
        try{
            if(Files.notExists(filePath)){
                Files.createFile(filePath);
                System.out.println("New File Created!");
            }else {
                System.out.println("File found.");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



    public void addContact(int id, String name, String phone, String mail){
        Contacts contact = new Contacts("#"+id, name, phone, mail);
        contacts.put(id, contact);
    }

    public void deleteContact(int id) throws TaskNotFoundException{
        if(getTaskById(id).isPresent()){
            contacts.remove(id);
        }else{
            throw new TaskNotFoundException("Task Not Found.");
        }

    }

    public void updateContacts(int id, int option, String change ) throws TaskNotFoundException{
        Contacts contact = getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));
        switch (option){
            case 1: contact.changeName(change);
            case 2: contact.changePhone(change);
            case 3: contact.changeMail(change);
        }
    }

    public Optional<Contacts> getTaskById(int id){
        return contacts.entrySet().stream()
                .filter(e -> e.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public List<Contacts> getAllContacts(){
        return contacts.values().stream().toList();
    }

    public int getNextNumber(){
        return contacts.isEmpty() ? 1 :
                contacts.keySet().stream()
                .max(Integer::compareTo)
                .get() +1;
    }

    public List<Contacts> searchContact(String keyword){
        return contacts.values().stream().filter(c -> c.compareKeyword(keyword)).toList();
    }

    public void saveContacts(){
        try{
            List<Contacts> toLoadContacts= getAllContacts();

            if(toLoadContacts.isEmpty()){
                return;
            }

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file,toLoadContacts);

        }catch (Exception e){
            System.out.println("Writing Error is: " + e.getMessage());
        }
    }

    private void loadContacts(){
        try{
            if(!file.exists() || file.length() ==0){
                return;
            }

            List<Contacts> loadedContacts = mapper.readValue(file, new TypeReference<List<Contacts>>() {
            });

            for(Contacts contact : loadedContacts){
                contacts.put(getNextNumber(), contact);
            }

        }catch(Exception e){
            System.out.println("Writing error is: " + e.getMessage());
        }
    }


}
