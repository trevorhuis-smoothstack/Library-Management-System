package com.ss.lms.partone;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ss.lms.partone.models.Author;
import com.ss.lms.partone.models.Book;
import com.ss.lms.partone.models.LibraryData;
import com.ss.lms.partone.models.Publisher;

public class LibraryManagementSystem {
    
    private final  Scanner reader = new Scanner(System.in);

    private Integer getIntInput() {
        Integer choice = 0;

        while (choice == 0) {
            try {
                System.out.println("Please enter an integer: ");
                choice = reader.nextInt();

                if (choice == 0) {
                    throw new InputMismatchException();
                }

                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again:");
                choice = reader.nextInt();
            }
        }
        
        return choice;
    }

    public void removeData(HashMap<Integer, LibraryData> map) {
        System.out.println("Wait");
    }

    

    public Integer getCRUDOperationChoice() {
        //Integer crudChoice, objectChoice;

        readCRUDMenu();

        return getIntInput();
    }

    public Integer getABPDataTypeChoice() {
        return 1;
    }

    public void readSecondaryMenu(LibraryData obj) {

        System.out.println("Wait");
    }
    
    private void readCRUDMenu() {
        System.out.println("Welcome to The Huis LMS");
        System.out.println("=======================");
        System.out.println("Manage your books, authors, and publishers with the menu below\n");
        System.out.println("1) Enter new library data");
        System.out.println("2) Lookup library data");
        System.out.println("3) Update library data");
        System.out.println("4) Remove library data");
    }

    public void readData(HashMap<Integer, LibraryData> map) {
        System.out.println("MAP!");
    }

    public void createData(HashMap<Integer, LibraryData> map) {
        System.out.println("MAP!");
    }


}