package com.contactManagement;

import java.util.HashMap;
import java.util.Scanner;

public class Process {


    public HashMap<String, Contact> contacts;

    public Process(){
        contacts = new HashMap<>();
    }

    public void addContact(String id, String name, String phone, String mail){
        Contact contact = new Contact(id, name, phone, mail);
        contacts.put(id, contact);
    }

    public void deleteContact(String id){
        contacts.remove(id);
    }

    public void updateContacts(int option, String change, Contact contact){
        switch (option){
            case 1: contact.changeName(change);
            case 2: contact.changePhone(change);
            case 3: contact.changeMail(change);
        }
    }


}
