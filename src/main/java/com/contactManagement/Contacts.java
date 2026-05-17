package com.contactManagement;

import java.util.Objects;

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
        return(getName().contains(keyword.toLowerCase()) || getPhone().contains(keyword.toLowerCase())|| getEmail().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Contacts tmpContact = (Contacts) obj;

        return  name.equals(tmpContact.getName()) &&
                phone.equals(tmpContact.getPhone())&&
                email.equals(tmpContact.getEmail());


    }

    @Override
    public int hashCode(){
        return Objects.hash(name,phone, email);
    }

    @Override
    public String toString(){
        return this.id + " Name: " +this.name+ " Phone: " + this.phone + " E-mail: " + this.email;
    }
}
