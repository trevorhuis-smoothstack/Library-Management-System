package com.ss.lms.service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.models.Author;
import com.ss.lms.models.Book;
import com.ss.lms.models.BookCopies;
import com.ss.lms.models.BookLoan;
import com.ss.lms.models.Borrower;
import com.ss.lms.models.Genre;
import com.ss.lms.models.LibraryBranch;
import com.ss.lms.models.Publisher;
import com.ss.lms.service.admin.AdminAuthorService;
import com.ss.lms.service.admin.AdminBookService;
import com.ss.lms.service.admin.AdminBorrowerService;
import com.ss.lms.service.admin.AdminBranchService;
import com.ss.lms.service.admin.AdminGenreService;
import com.ss.lms.service.admin.AdminOverrideLoanService;
import com.ss.lms.service.admin.AdminPublisherService;

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

    public void adminFunctionality() throws SQLException {
        String password = "";
        while (!password.equals("smoothstack")) {
            System.out.println("Enter the admin password or enter 'quit':");
            password = getStringInput();
            if (password.equals("quit")) {
                return;
            }
        }

        System.out.println("Hello Trevor!");
        adminMenuOne();
    }
    
    public void adminMenuOne() throws SQLException {
        Integer choice = -1;

        while (choice != 7) {
            System.out.println("1) Add/Update/Delete/Read Book and Author");
            System.out.println("2) Add/Update/Delete/Read Genres");
            System.out.println("3) Add/Update/Delete/Read Publishers");
            System.out.println("4) Add/Update/Delete/Read Library Branches");
            System.out.println("5) Add/Update/Delete/Read Borrowers");
            System.out.println("6) Over-ride Due Date for a Book Loan");
            System.out.println("7) Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 7) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    adminBookAndAuthorMenu();
                    break;
                case 2:
                    adminGenreMenu();
                    break;
                case 3:
                    adminPublisherMenu();
                    break;
                case 4:
                    adminLibraryBranchMenu();
                    break;
                case 5:
                    adminBorrowerMenu();
                    break;
                case 6:
                    adminOverRideLoanMenu();
                    break;
                case 7:
                    break;
            }
        }
        return;
    }

    private void adminOverRideLoanMenu() throws SQLException {
        AdminOverrideLoanService aLoanService = new AdminOverrideLoanService();
        BorrowerService borService = new BorrowerService();

        System.out.println("Enter borrower ID you updating:");
        Integer idToUpdate = getIntInput();
        
        List<BookLoan> checkedOutLoans = borService.getLoansFromABorrower(idToUpdate);
        if(checkedOutLoans == null){
            return;
        }
        List<Book> checkedOutBooks = borService.getBookNamesFromLoans(checkedOutLoans);

        System.out.println("Which book are you overriding?");
        for(int i = 0; i < checkedOutBooks.size();i++){
            System.out.println((i + 1) + ") " + checkedOutBooks.get(i).getTitle());
        }
        System.out.println((checkedOutBooks.size() + 1) + ") Cancel overrride. Back to previous menu.");
        Integer choice = getIntInput();
        if(choice == checkedOutBooks.size() + 1) {
            return;
        }
        aLoanService.addAWeekToALoan(checkedOutLoans.get(choice - 1));
    }

    private void adminBorrowerMenu() throws SQLException {
        AdminBorrowerService aService = new AdminBorrowerService();

        Integer choice = -1;
        while (choice != 5) {
            System.out.println("1)	Read all Borrowers");
            System.out.println("2)  Add a Borrower");
            System.out.println("3)  Delete a Borrower");
            System.out.println("4)  Update a Borrower");
            System.out.println("5)  Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 5) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    List<Borrower> borrowers = aService.readAllBorrowers();
                    for(Borrower bor: borrowers) {
                        System.out.println(bor.toString());
                        
                    }
                    break;
                case 2:
                    System.out.println("Enter the borrowers Name:");
                    String name = getStringInput();
                    System.out.println("Enter the borrowers Address:");
                    String address = getStringInput();
                    System.out.println("Enter the borrowers Phone Number:");
                    String phone = getStringInput();
                    Borrower newBor = new Borrower(name, address, phone);
                    aService.addABorrower(newBor);
                    break;
                case 3:
                    System.out.println("Enter the Borrowers Card Number:");
                    Integer deleteId = getIntInput();
                    Borrower borToDelete = aService.readABorrower(deleteId);
                    aService.deleteABorrower(borToDelete);
                    break;
                case 4:
                    System.out.println("Enter the Borrowers Card Number:");
                    Integer updateId = getIntInput();
                    Borrower borToUpdate = aService.readABorrower(updateId);
                    System.out.println("Enter the borrowers new name or 'N/A' for no change:");
                    String newName = getStringInput();
                    if ("n/a".equalsIgnoreCase(newName)) {
                        newName = borToUpdate.getName();
                    }
                    System.out.println("Enter the borrowers new address or 'N/A' for no change:");
                    String newAddress = getStringInput();
                    if ("n/a".equalsIgnoreCase(newAddress)) {
                        newAddress = borToUpdate.getAddress();
                    }
                    System.out.println("Enter the borrowers new phone number or 'N/A' for no change:");
                    String newNumber = getStringInput();
                    if ("n/a".equalsIgnoreCase(newNumber)) {
                        newNumber = borToUpdate.getPhone();
                    }
                    borToUpdate.setAddress(newAddress);
                    borToUpdate.setName(newName);
                    borToUpdate.setPhone(newNumber);
                    aService.updateABorrower(borToUpdate);
                    break;
                case 5:
                    break;
            }
        }
        return;

    }

    private void adminLibraryBranchMenu() throws SQLException {
        AdminBranchService aBranchService = new AdminBranchService();
        LibrarianService libService = new LibrarianService();

        Integer choice = -1;
        while (choice != 5) {
            System.out.println("1)	Read all Branch");
            System.out.println("2)  Add a Branch");
            System.out.println("3)  Delete a Branch");
            System.out.println("4)  Update a Branch");
            System.out.println("5)  Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 5) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    List<LibraryBranch> branches = aBranchService.readAllBranches();
                    for(LibraryBranch branch: branches) {
                        System.out.println(branch.toString());
                        
                    }
                    break;
                case 2:
                    System.out.println("Enter the branches Name:");
                    String branchName = getStringInput();
                    System.out.println("Enter the branches Address:");
                    String address = getStringInput();
                    LibraryBranch branch = new LibraryBranch(branchName, address);
                    aBranchService.addABranch(branch);
                    break;
                case 3:
                    LibraryBranch deleteBranch = selectABranchMenu(libService.getBranches());
                    aBranchService.deleteABranch(deleteBranch);
                    break;
                case 4:
                    LibraryBranch updateBranch = selectABranchMenu(libService.getBranches());
                    updateBranchInformation(updateBranch);
                    break;
                case 5:
                    break;
            }
        }
        return;
    }

    private void adminPublisherMenu() throws SQLException {
        AdminPublisherService aPublisherService = new AdminPublisherService();

        Integer choice = -1;
        while (choice != 5) {
            System.out.println("1)	Read all Publisher");
            System.out.println("2)  Add a Publisher");
            System.out.println("3)  Delete a Publisher");
            System.out.println("4)  Update a Publisher");
            System.out.println("5)  Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 5) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    List<Publisher> publishers = aPublisherService.readAllPublishers();
                    for(Publisher pub: publishers) {
                        System.out.println(pub.toString());
                        
                    }
                    break;
                case 2:
                    System.out.println("Enter the publishers Name:");
                    String name = getStringInput();
                    System.out.println("Enter the publishers Address:");
                    String address = getStringInput();
                    System.out.println("Enter the publishers Phone Number:");
                    String phone = getStringInput();
                    Publisher newPub = new Publisher(name, address, phone);
                    aPublisherService.addAPublisher(newPub);
                    break;
                case 3:
                    System.out.println("Enter the Publishers ID:");
                    Integer deleteId = getIntInput();
                    Publisher pubToDelete = aPublisherService.readAPublisher(deleteId);
                    aPublisherService.deleteAPublisher(pubToDelete);
                    break;
                case 4:
                    System.out.println("Enter the Publishers ID:");
                    Integer updateId = getIntInput();
                    Publisher pubToUpdate = aPublisherService.readAPublisher(updateId);
                    System.out.println("Enter the publishers new name or 'N/A' for no change:");
                    String newName = getStringInput();
                    if ("n/a".equalsIgnoreCase(newName)) {
                        newName = pubToUpdate.getPublisherName();
                    }
                    System.out.println("Enter the publishers new address or 'N/A' for no change:");
                    String newAddress = getStringInput();
                    if ("n/a".equalsIgnoreCase(newAddress)) {
                        newAddress = pubToUpdate.getAddress();
                    }
                    System.out.println("Enter the publishers new phone number or 'N/A' for no change:");
                    String newNumber = getStringInput();
                    if ("n/a".equalsIgnoreCase(newNumber)) {
                        newNumber = pubToUpdate.getPhone();
                    }
                    pubToUpdate.setAddress(newAddress);
                    pubToUpdate.setPublisherName(newName);
                    pubToUpdate.setPhone(newNumber);
                    aPublisherService.updateAPublisher(pubToUpdate);
                    break;
                case 5:
                    break;
            }
        }
        return;

    }

    private void adminGenreMenu() throws SQLException {
        AdminGenreService aGenreService = new AdminGenreService();

        Integer choice = -1;
        while (choice != 5) {
            System.out.println("1)	Read all Publisher");
            System.out.println("2)  Add a Publisher");
            System.out.println("3)  Delete a Publisher");
            System.out.println("4)  Update a Publisher");
            System.out.println("5)  Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 5) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    List<Genre> genres = aGenreService.readAllGenres();
                    for(Genre genre: genres) {
                        System.out.println(genre.toString());
                        
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the new Genre:");
                    String name = getStringInput();
                    Genre newGenre = new Genre(name);
                    aGenreService.addAGenre(newGenre);
                    break;
                case 3:
                    System.out.println("Enter the Genre ID:");
                    Integer deleteId = getIntInput();
                    Genre genreToDelete = aGenreService.readAGenre(deleteId);
                    aGenreService.deleteAGenre(genreToDelete);
                    break;
                case 4:
                    System.out.println("Enter the Genres ID:");
                    Integer updateId = getIntInput();
                    Genre genreToUpdate = aGenreService.readAGenre(updateId);
                    System.out.println("Enter the publishers new name or 'N/A' for no change:");
                    String newName = getStringInput();
                    if ("n/a".equalsIgnoreCase(newName)) {
                        newName = genreToUpdate.getGenreName();
                    }
                    genreToUpdate.setGenreName(newName);
                    aGenreService.updateAGenre(genreToUpdate);
                    break;
                case 5:
                    break;
            }
        }
        return;

    }

    private void adminBookAndAuthorMenu() throws SQLException {
        AdminBookService aBookService = new AdminBookService();
        AdminAuthorService aAuthorService = new AdminAuthorService();
        AdminPublisherService aPublisherService = new AdminPublisherService();
        AdminGenreService aGenreService = new AdminGenreService();
        AdminBorrowerService aBorrowerService = new AdminBorrowerService();

        Integer choice = -1;
        while (choice != 5) {
            System.out.println("1)	Read the Books in LMS");
            System.out.println("2)  Add a Book");
            System.out.println("3)  Delete a Book");
            System.out.println("4)  Update a Book");
            System.out.println("5)  Quit to previous menu");

            choice = getIntInput();

            while (choice < 1 || choice > 5) {
                System.out.println("Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case 1:
                    List<Book> books = aBookService.readAllBooks();
                    for(Book book: books) {
                        System.out.println(book.toString());  
                    }
                    break;
                case 2:
                    // Title
                    System.out.println("Enter the books name:");
                    String title = getStringInput();

                    // Publisher
                    List<Publisher> pubs = aPublisherService.readAllPublishers();
                    for(Publisher pub: pubs) {
                        System.out.println(pub.toString());
                    }
                    System.out.println("Enter the id of the publisher:");
                    Integer pubChoice = getIntInput();
                    while (choice < 1 || choice > pubs.size()) {
                        System.out.println("Not a valid choice.");
                        pubChoice = getIntInput();
                    }
                    Publisher pub = pubs.get(pubChoice);

                    // Author
                    System.out.println("Enter the author of the book");
                    String author = getStringInput();
                    Author newAuthor = new Author(author);
                    Integer authorPK = aAuthorService.addAuthor(newAuthor);
                    newAuthor.setAuthorId(authorPK);

                    // Genre
                    List<Genre> genres = aGenreService.readAllGenres();
                    for(Genre genre: genres) {
                        System.out.println(genre.toString());
                    }
                    System.out.println("Enter the id of the genre:");
                    Integer genreChoice = getIntInput();
                    while (choice < 1 || choice > genres.size()) {
                        System.out.println("Not a valid choice.");
                        genreChoice = getIntInput();
                    }
                    Genre genre = genres.get(genreChoice);

                    Book newBook = new Book(title, pub.getPublisherID());

                    aBookService.addBook(newBook);
                    //aBookService.addBookReferences(newBook, newAuthor, pub, genre);

                    System.out.println("Book added");
                    
                    break;
                case 3:
                    System.out.println("Enter the Borrowers Card Number:");
                    Integer deleteId = getIntInput();
                    Borrower borToDelete = aBorrowerService.readABorrower(deleteId);
                    aBorrowerService.deleteABorrower(borToDelete);
                    break;
                case 4:
                    System.out.println("Enter the Borrowers Card Number:");
                    Integer updateId = getIntInput();
                    Borrower borToUpdate = aBorrowerService.readABorrower(updateId);
                    System.out.println("Enter the borrowers new name or 'N/A' for no change:");
                    String newName = getStringInput();
                    if ("n/a".equalsIgnoreCase(newName)) {
                        newName = borToUpdate.getName();
                    }
                    System.out.println("Enter the borrowers new address or 'N/A' for no change:");
                    String newAddress = getStringInput();
                    if ("n/a".equalsIgnoreCase(newAddress)) {
                        newAddress = borToUpdate.getAddress();
                    }
                    System.out.println("Enter the borrowers new phone number or 'N/A' for no change:");
                    String newNumber = getStringInput();
                    if ("n/a".equalsIgnoreCase(newNumber)) {
                        newNumber = borToUpdate.getAddress();
                    }
                    borToUpdate.setAddress(newAddress);
                    borToUpdate.setName(newName);
                    borToUpdate.setPhone(newNumber);
                    aBorrowerService.updateABorrower(borToUpdate);
                    break;
                case 5:
                    break;
            }
        }
        return;

    }

}