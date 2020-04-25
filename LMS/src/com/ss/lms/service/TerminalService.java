package com.ss.lms.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.models.BookCopies;
import com.ss.lms.models.LibraryBranch;

public class TerminalService {
    private final  Scanner reader = new Scanner(System.in);

    private Integer getIntInput() {
        Integer choice = -1;

        while (choice == -1) {
            try {
                System.out.println("Please enter an integer: ");
                choice = reader.nextInt();

                if (choice == -1) {
                    throw new InputMismatchException();
                }

                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again:");
            }
        }
        
        return choice;
    }
            
    public String getStringInput() {
        String s = reader.nextLine();

        return s;
    }

    public BookCopies getLibraryCopiesData(BookCopies entry) {
        System.out.println("Existing number of copies: " + entry.getNoOfCopies());
        System.out.println("Enter new number of copies: ");

        int choice = getIntInput();
        entry.setNoOfCopies(choice);

        return entry;
    }

    public Integer LibrarianMenuIntro() {
        System.out.println("1) Enter Branch you manage");
        System.out.println("2) Quit to previous");

        int choice = getIntInput();

        while(choice != 1 || choice != 2) {
            System.err.println("Not a valid choice.");
            choice = getIntInput();
        }

        return choice;
    }

    public LibraryBranch LibrarianBranchMenu(List<LibraryBranch> branches){
        int length = branches.size();

        for(int i=1; i>=length; i++) {
            System.out.println(i + ") " + branches.get(i-1).getBranchName());
        }

        int choice = getIntInput();

        while(choice < 0 && choice > length) {
            System.out.println("Not a valid library branch");
            choice = getIntInput();
        }

        return branches.get(choice + 1);
    }
}