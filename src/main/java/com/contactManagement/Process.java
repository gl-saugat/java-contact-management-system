package com.contactManagement;

import java.util.*;

public class Process {


    public HashMap<String, Contact> contacts;

    public Process(){
        contacts = new HashMap<>();
    }

    public void addContact(String id, String name, String phone, String mail){
        Contact contact = new Contact(id, name, phone, mail);
        contacts.put(id, contact);
    }

    public void deleteContact(String id) throws TaskNotFoundException{
        if(getTaskById(id).isPresent()){
            contacts.remove(id);
        }else{
            throw new TaskNotFoundException("Task Not Found.");
        }

    }

    public void updateContacts(String id, int option, String change ) throws TaskNotFoundException{
        Contact contact = getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found!"));
        switch (option){
            case 1: contact.changeName(change);
            case 2: contact.changePhone(change);
            case 3: contact.changeMail(change);
        }
    }

    public Optional<Contact> getTaskById(String id){
        return contacts.entrySet().stream()
                .filter(e -> e.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public List<Contact> getAllContacts(){
        return contacts.values().stream().toList();
    }


}
