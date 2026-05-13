package com.contactManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Process {


    public HashMap<Integer, Contact> contacts;

    ObjectMapper mapper = new ObjectMapper();

    private File file;


    public Process(){

        contacts = new HashMap<>();

        Path filePath = Paths.get("contacts.json");
        createNewFile(filePath);
        file = filePath.toFile();


    }

    private void createNewFile(Path filePath){
        try{
            if(Files.notExists(filePath)){
                Files.createFile(filePath);
                System.out.println("New File Created!");
            }
            System.out.println("File found.");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



    public void addContact(int id, String name, String phone, String mail){
        Contact contact = new Contact("#"+id, name, phone, mail);
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
        Contact contact = getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));
        switch (option){
            case 1: contact.changeName(change);
            case 2: contact.changePhone(change);
            case 3: contact.changeMail(change);
        }
    }

    public Optional<Contact> getTaskById(int id){
        return contacts.entrySet().stream()
                .filter(e -> e.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public List<Contact> getAllContacts(){
        return contacts.values().stream().toList();
    }

    public int getNextNumber(){
        return contacts.isEmpty() ? 1 :
                contacts.keySet().stream()
                .max(Integer::compareTo)
                .get() +1;
    }

    public List<Contact> searchContact(String keyword){
        return contacts.values().stream().filter(c -> c.compareKeyword(keyword)).toList();
    }

    private void saveToJson(){

    }


}
