package com.contactManagement;

public class ContactAlreadyExists extends Exception{
    public ContactAlreadyExists(String message){
        super(message);
    }
}
