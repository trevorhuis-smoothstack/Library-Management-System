package com.ss.lms.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.models.Book;
import com.ss.lms.models.BookCopies;
import com.ss.lms.models.BookLoan;
import com.ss.lms.models.Borrower;
import com.ss.lms.models.LibraryBranch;

public class TerminalService {
    private final Scanner reader = new Scanner(System.in);

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

    public Integer openingMenu() {
        System.out.println("Welcome to the Huis Library Management System. Which category of a user are you");

        System.out.println("1) Librarian");
        System.out.println("2) Administrator");
        System.out.println("3) Borrower");
        System.out.println("4) Quit system");

        Integer choice = getIntInput();

        while (choice < 1 || choice > 4) {
            System.out.println("Not a valid choice.");
            choice = getIntInput();
        }

        return choice;
    }

    public Integer LibrarianMenuIntro() {
        System.out.println("\n Librarian Menu:");
        System.out.println("1) Enter Branch you manage");
        System.out.println("2) Quit to previous");

        int choice = getIntInput();

        while (choice != 1 && choice != 2) {
            System.err.println("Not a valid choice.");
            choice = getIntInput();
        }

        return choice;
    }

    public void librarianFunctionality() throws SQLException {
        Integer libMenuOne = LibrarianMenuIntro();

        while (libMenuOne != 2) {
            switch (libMenuOne) {
                case 1:
                    libraryMenuTwo();
                case 2:
                    break;
            }
            libMenuOne = LibrarianMenuIntro();
        }
    }

    public LibraryBranch selectABranchMenu(List<LibraryBranch> branches) {
        int length = branches.size();
        int quit = length + 1;

        System.out.println("\nBranches:");
        for (int i = 0; i < length; i++) {
            System.out.println(i + 1 + ") " + branches.get(i).getBranchName());
        }
        System.out.println(quit + ") Quit to previous");
        int choice = getIntInput();

        while (choice < 0 && choice > quit) {
            System.out.println("Not a valid library branch");
            choice = getIntInput();
        }

        if (choice == quit) {
            return null;
        }
        return branches.get(choice - 1);
    }

    private void libraryMenuTwo() throws SQLException {
        LibrarianService libService = new LibrarianService();
        List<LibraryBranch> branches = libService.getBranches();
        LibraryBranch branch = selectABranchMenu(branches);

        while (branch != null) {
            librarianMenuThree(branch);
            branch = selectABranchMenu(branches);

        }
        return;
    }

    public void librarianMenuThree(LibraryBranch branch) throws SQLException {
        LibrarianService libService = new LibrarianService();
        Integer choice = -1;

        while (choice != 3) {
            System.out.println("\n" + branch.getBranchName() + " Library Menu: ");
            System.out.println("1) Update the details of the " + branch.getBranchName() + " Library");
            System.out.println("2) Update copies of a book to the " + branch.getBranchName() + " Library");
            System.out.println("3) Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 3) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    LibraryBranch updatedBranch = updateBranchInformation(branch);
                    if (branch != null) {
                        libService.updateBranch(branch);
                        branch = updatedBranch;
                    }
                    break;
                case 2:
                    Book book = selectABookMenu();
                    if (book != null) {
                        updateLibraryCopiesData(branch.getBranchId(), book.getBookId());
                    }
                    break;
            }
        }
        return;
    }

    public Book selectABookMenu() throws SQLException {
        LibrarianService libService = new LibrarianService();
        List<Book> books;
        System.out.println("Please search for the book you would like to update");
        String search = getStringInput();
        books = libService.getBooksWithSearch(search);

        while (books.size() == 0) {
            System.out.println("No book with that title was found.");
            System.out.println("You can either search again or return to the previous menu.");
            System.out.println("1) Search again.");
            System.out.println("2) Return to the previous menu.");

            int choice = getIntInput();

            while (choice != 1 && choice != 2) {
                System.err.println("Not a valid choice.");
                choice = getIntInput();
            }

            if (choice == 2) {
                return null;
            }

            System.out.println("Please search for the book you would like to update");
            search = getStringInput();

            books = libService.getBooksWithSearch(search);
        }

        int length = books.size();
        int quit = length + 1;

        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.println(i + 1 + ") " + books.get(i).getTitle());
        }
        System.out.println(quit + ") Quit to previous");
        int choice = getIntInput();

        while (choice < 0 && choice > quit) {
            System.out.println("Not a valid book.");
            choice = getIntInput();
        }

        if (choice == quit) {
            return null;
        }
        return books.get(choice - 1);
    }

    public void updateLibraryCopiesData(Integer branchId, Integer bookId) throws SQLException {
        LibrarianService libService = new LibrarianService();
        BookCopies entry = libService.getAnEntryOfBookCopies(branchId, bookId);

        if (entry == null) {
            entry = new BookCopies(branchId, bookId, 0);
        }

        System.out.println("Existing number of copies: " + entry.getNoOfCopies());
        System.out.println("Enter new number of copies: ");

        int choice = getIntInput();
        entry.setNoOfCopies(choice);

        boolean didUpdate = libService.updateCopies(entry);

        if (didUpdate == true) {
            System.out.println("Number Of Copies Updated");
        } else {
            System.out.println("We were unable to update the Number of copies");
        }
    }

    public LibraryBranch updateBranchInformation(LibraryBranch branch) {
        System.out.println("You have chosen to update the Branch with Branch Id: " + branch.getBranchId()
                + " and Branch Name: " + branch.getBranchName() + ". Enter ‘quit’ at any prompt to cancel operation.");
        System.out.println("Please enter new branch name or enter N/A for no change:");
        String newBranchName = getStringInput();
        if ("n/a".equalsIgnoreCase(newBranchName)) {
            newBranchName = branch.getBranchName();
        } else if ("quit".equalsIgnoreCase(newBranchName)) {
            return null;
        }

        System.out.println("Please enter new branch address or enter N/A for no change:");
        String newBranchAddress = getStringInput();
        if ("n/a".equalsIgnoreCase(newBranchAddress)) {
            newBranchAddress = branch.getBranchAddress();
        } else if ("quit".equalsIgnoreCase(newBranchAddress)) {
            return null;
        }

        branch.setBranchAddress(newBranchAddress);
        branch.setBranchName(newBranchName);

        return branch;
    }

    public void borrowerFunctionality() throws SQLException {
        Borrower borrower = null;
        BorrowerService borServ = new BorrowerService();
        Integer choice;
        while (borrower == null) {
            System.out.println("Enter your Card Number or '0' to return to previous menu:");
            choice = getIntInput();
            if (choice == 0) {
                return;
            }
            borrower = borServ.getBorrower(choice);
        }

        System.out.println("Hello " + borrower.getName() + "!");
        borrowerMenuOne(borrower);

    }

    public void borrowerMenuOne(Borrower borrower) throws SQLException {
        Integer choice = -1;

        while (choice != 3) {
            System.out.println("1) Check out a book");
            System.out.println("2) Return a book");
            System.out.println("3) Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 3) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    checkOutABook(borrower);
                    break;
                case 2:
                    returnABook(borrower);
                    break;
            }
        }
        return;
    }

    private void returnABook(Borrower borrower) throws SQLException {
        BorrowerService borService = new BorrowerService();
        List<BookLoan> checkedOutLoans = borService.getLoansFromABorrower(borrower.getCardNo());
        if(checkedOutLoans == null){
            return;
        }
        List<Book> checkedOutBooks = borService.getBookNamesFromLoans(checkedOutLoans);

        System.out.println("Which book are you returning?");
        for(int i = 0; i < checkedOutBooks.size();i++){
            System.out.println((i + 1) + ") " + checkedOutBooks.get(i).getTitle());
        }
        System.out.println((checkedOutBooks.size() + 1) + ") Cancel return. Back to previous menu.");
        Integer choice = getIntInput();
        if(choice == checkedOutBooks.size() + 1) {
            return;
        }
        borService.returnABook(checkedOutLoans.get(choice - 1));
        System.out.println("Book returned!");
    }

    private void checkOutABook(Borrower borrower) throws SQLException {
        LibrarianService libService = new LibrarianService();
        BorrowerService borService = new BorrowerService();
        LibraryBranch branch = selectABranchMenu(libService.getBranches());
        libService.getBooksAtABranch(branch);
        List<Book> books = libService.getBooksAtABranch(branch);

        int length = books.size();
        int quit = length + 1;

        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.println(i + 1 + ") " + books.get(i).getTitle());
        }
        System.out.println(quit + ") Quit to previous");
        int choice = getIntInput();

        while (choice < 0 && choice > quit) {
            System.out.println("Not a valid book.");
            choice = getIntInput();
        }

        if (choice == quit) {
            return;
        }

        Book book = books.get(choice - 1);

        boolean didCheckOut = borService.checkOutABook(book.getBookId(), branch.getBranchId(), borrower.getCardNo());
        if(didCheckOut == true) {
            System.out.println("Book checked out!");
        } else {
            return;
        }
    }

    public void adminFunctionality() {
	}

}