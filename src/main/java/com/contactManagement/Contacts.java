package com.contactManagement;

public class Contacts {

    private String id;
    private String name;
    private String phone;
    private String email;

    public Contacts() {
    }

    public Contacts(String id, String name, String phone, String email){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void changePhone(String newPhone){
        this.phone = newPhone;
    }

    public void changeMail(String newMail){
        this.email = newMail;
    }

    public boolean compareKeyword(String keyword){
        return(getName().contains(keyword) || getPhone().contains(keyword)|| getEmail().contains(keyword));
    }

    @Override
    public String toString(){
        return this.id + " Name: " +this.name+ " Phone: " + this.phone + " E-mail: " + this.email;
    }
}
