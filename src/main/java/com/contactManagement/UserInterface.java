package com.contactManagement;

import java.util.List;
import java.util.Scanner;



public class UserInterface {
    public Scanner scanner;
    Process process = new Process();

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }


    public void start(){
        while(true){
            printMenu();
            int menuInput = getMainMenuOption();

            switch (menuInput){
                case 1:
                    contactAddMenu();

                    break;

                case 2:
                    if(!(process.getAllContacts().isEmpty())){
                        contactDeleteMenu();
                    }
                    System.out.println("No tasks yet!");
                    break;

                case 3:
                    if(!(process.getAllContacts().isEmpty())){
                        updateContactMenu();
                    }
                    System.out.println("No tasks yet!");
                    break;

                case 4:
                    printTasks();
                    break;

                case 5:
                    searchContacts();
                    break;

                case 6:
                    System.out.println("Thanks for trying the app.");
                    process.saveContacts();
                    return;
            }
        }


    }

    public void printMenu(){
        System.out.println("""
                Welcome to your Contacts App. Please press the adjacent number of the task you'd like to perform:
                1. Add Contact
                2. Delete Contact
                3. Update Contact
                4. Show all Contacts
                5. Search Contact
                6. Exit
                """);
    }

    public void contactAddMenu(){
        System.out.println("Enter the contact's name: ");
        String name = getStringInput();
        System.out.println("Enter their phone number: ");
        String phone = getStringInput();
        System.out.println("Enter their mail address: ");
        String mail = getEmailInput();
        int id = process.getNextNumber();
        try{
            process.addContact(id ,name, phone, mail);
            System.out.println("Contact added!");
        }catch(Exception e){
            System.out.println(e.getMessage() + " Contact Not Added.");
        }
    }

    public void contactDeleteMenu(){
        System.out.println("Enter the ID of the task you want to delete. (NO need for '#')");
        printTasks();
        int id = getIDOptions();
        try{
            process.deleteContact(id);
            System.out.println("Task deleted.");
        }catch (ContactNotFoundException e){
            System.out.println("Please enter the valid ID.");
        }
    }

    public void printUpdateOptionsMenu(){
        System.out.println("""
                1. Name
                2. Phone
                3. Mail
                """);
    }

    private void updateContactMenu(){
        int updateID;
        while(true){
            System.out.println("Enter the ID of the contact you'd want to update: (NO need for '#')");
            printTasks();
            updateID = getIDOptions();
            if(process.getTaskById(updateID).isPresent()){
                break;
            }
            System.out.println("Enter a valid ID!");

        }
        printUpdateOptionsMenu();
        int updateOption = getUpdateMenuOption();
        System.out.println("Enter the updated credentials:");
        String updatedInfo = getStringInput();

        try{
            process.updateContacts(updateID, updateOption,updatedInfo);
        }catch (ContactNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void printTasks(){
        process.getAllContacts().forEach(System.out::println);
    }

    public int getUpdateMenuOption(){
        return getOptions(3);
    }

    public int getMainMenuOption(){
        return getOptions(6);
    }

    public int getIDOptions(){
        return getOptions(process.getAllContacts().size());
    }

    public void searchContacts(){
        System.out.println("Enter keyword of the contact you want to search for: ");
        String keyword = getStringInput();
        List<Contacts> tmpList = process.searchContact(keyword);
        if(tmpList.isEmpty()){
            System.out.println("No contacts found!");
            return;
        }
        tmpList.forEach(System.out::println);
    }

    public int getOptions(int limit){
        int input;
        while(true){
            try{
                input = Integer.parseInt(scanner.nextLine());

                if(input >0 && input <=limit){
                    return input;
                }
                System.out.println("Enter a valid number.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String getStringInput(){
        String input = "";
        while(true){
            try{
                input = scanner.nextLine();
                if(!(input.isEmpty())){
                    return input;
                }
                System.out.println("Can't leave the field empty.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String getEmailInput(){
        while(true){
            String input = getStringInput();
            if(input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                return input;
            }
            System.out.println("Invalid format. Should be as 'example@mail.com'");

        }
    }

}
