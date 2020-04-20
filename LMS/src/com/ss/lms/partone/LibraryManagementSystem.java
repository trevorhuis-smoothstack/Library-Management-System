package com.ss.lms.partone;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ss.lms.partone.models.Author;
import com.ss.lms.partone.models.Book;
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
            }
        }
        
        return choice;
    }
    
    public String getStringInput() {
        String s = reader.nextLine();

        System.out.println("Input: " + s);
        System.out.println("\nIf this is correct enter 1, if not enter 2 to input again.");

        Integer choice = getIntInput();
        while (choice != 1 && choice !=2) {
            System.out.println("ERROR: Not a valid choice.");
            choice = getIntInput();
        }

        if(choice == 2) {
            System.out.println("Correction: ");
            return getStringInput();
        } else {
            return s;
        }
    }

    // Menu Functions
    protected void printCRUDMenu() {
        System.out.println("\nWelcome to The Huis LMS");
        System.out.println("=======================");
        System.out.println("Manage your books, authors, and publishers with the menu below\n");
        System.out.println("1) Enter new library data");
        System.out.println("2) Lookup library data");
        System.out.println("3) Update library data");
        System.out.println("4) Remove library data");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to quit):");
    }

    public Integer getCRUDOperationChoice() {
        printCRUDMenu();

        Integer choice = getIntInput();
        while (choice< 0 && choice> 4 && choice != -1) {
            System.out.println("ERROR: Not a valid menu choice.");
            choice = getIntInput();
        }
        
        return choice;
    }

    private void printABPMenu(String operationType) {
        System.out.println("\nWhat library data would you like to " + operationType + "?");
        System.out.println("1) Author");
        System.out.println("2) Book");
        System.out.println("3) Publisher");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to go back):");
    }

    public Integer getABPDataTypeChoice(String operationType) {
        printABPMenu(operationType);

        Integer choice = getIntInput();
        while (choice< 0 && choice> 4 && choice != -1) {
            System.out.println("ERROR: Not a valid library data choice.");
            choice = getIntInput();
        }

        return choice;
    }

    // Create Functions
    public String createAuthorData(LinkedList<Author> authors) {
        Author author;
        String authorName;
        Integer authorID;

        System.out.println("Enter the new author's name:");
        authorName = getStringInput();

        List<Author> authorOutput = authors.stream()
            .filter(e -> e.getAuthorName() == authorName)
            .collect(Collectors.toList());

        if(authorOutput.size()>0){
            System.out.println("That author already exists");
            return "Not valid.";
        }

        if (authors.size() > 0) {
            authorID = authors.getLast().getAuthorID() + 1;
        } else {
            authorID = 1;
        }

        author = new Author(authorID, authorName);

        System.out.println("Author Created!\n");

        return author.toString();
    }

    public String createBookData(LinkedList<Book> books, LinkedList<Author> authors, LinkedList<Publisher> publishers) {
        Book book;
        Author author;
        Publisher publisher;
        String bookName;
        Integer bookID;
        
        System.out.println("Enter the new books's title:");
        bookName = getStringInput();

        List<Book> bookOutput = books.stream()
            .filter(e -> e.getBookName() == bookName)
            .collect(Collectors.toList());

        if(bookOutput.size()>0){
            System.out.println("That book title already exists");
            return "Not valid.";
        }

        System.out.println("A new book can only be created if the Author and Publisher are already in our system.");
        author = createBookDataHelperAuthor(authors);
        if(author==null){
            return "Not valid.";
        } 

        publisher = createBookDataHelperPublisher(publishers);
        if(publisher==null) {
            return "Not valid.";
        }

        if (books.size() > 0) {
            bookID = books.getLast().getBookID() + 1;
        } else {
            bookID = 1;
        }

        book = new Book(bookID, bookName, author.getAuthorID(), publisher.getPublisherID());

        System.out.println("Book Created!\n");

        return book.toString();
    }

    private Author createBookDataHelperAuthor(LinkedList<Author> authors) {
        System.out.println("Who is the author of the book?");
        String authorToFind = getStringInput();
        
        List<Author> listOutput = authors.stream()
            .filter(e -> e.getAuthorName().equals(authorToFind))
            .collect(Collectors.toList());

        if(listOutput.size()==0) {
            System.out.println("The author is not in our system. Please add this author to our system to enter this new book.");
            return null;
        } else {
            return listOutput.get(0);
        }
    }

    private Publisher createBookDataHelperPublisher(LinkedList<Publisher> publishers) {
        System.out.println("Who is the publisher of the book?");
        String publisherToFind = getStringInput();
        
        List<Publisher> listOutput = publishers.stream()
            .filter(e -> e.getPublisherName().equals(publisherToFind))
            .collect(Collectors.toList());

        if(listOutput.size()==0) {
            System.out.println("The publisher is not in our system. Please add this author to our system to enter this new book.");
            return null;
        } else {
            return listOutput.get(0);
        }
    }

    public String createPublisherData(LinkedList<Publisher> publishers) {
        Publisher publisher;
        String publisherName;
        String publisherAddress;
        Integer publisherID;

        System.out.println("Enter the new publisher's name:");
        publisherName = getStringInput();

        List<Publisher> publisherOutput = publishers.stream()
            .filter(e -> e.getPublisherName() == publisherName)
            .collect(Collectors.toList());

        if(publisherOutput.size()>0){
            System.out.println("That publisher already exists");
            return "Not valid.";
        }

        System.out.println("Enter the new publisher's address:");
        publisherAddress = getStringInput();

        if (publishers.size() > 0) {
            publisherID = publishers.getLast().getPublisherID() + 1;
        } else {
            publisherID = 1;
        }

        publisher = new Publisher(publisherID, publisherName, publisherAddress);

        System.out.println("Publisher Created!\n");
        return publisher.toString();
    }


    // Read Functions
    public void readAuthorData(LinkedList<Author> authors, LinkedList<Book> books) {
        System.out.println("Would you like to lookup a single Author or list all of them?");
        System.out.println("1) Single Author");
        System.out.println("2) List all authors");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to go back):");

        Integer choice = getIntInput();
        while (choice< 0 && choice> 2 && choice != -1) {
            System.out.println("ERROR: Not a valid choice.");
            choice = getIntInput();
        }

        switch (choice) {
            case -1:
                return;
            case 1:
                lookupSingleAuthor(authors, books);
                break;
            case 2:
                authors.stream().forEach(e-> 
                    System.out.println("Author: " + e.getAuthorName())
                );
        }
    }

    public Author lookupSingleAuthor(LinkedList<Author> authors, LinkedList<Book> books) {
        System.out.println("Who is the author you are looking for?");
        String authorToFind = getStringInput();
        
        List<Author> listOutput = authors.stream()
            .filter(e -> e.getAuthorName().equals(authorToFind))
            .collect(Collectors.toList());
          
        if(listOutput.size()==0) {
            System.out.println("The author is not in our system.");
            return null;
        } 

        Author author = listOutput.get(0);

        List<Book> bookOutput = books.stream()
            .filter(e -> e.getAuthorID() == author.getAuthorID())
            .collect(Collectors.toList());

        System.out.println("Books by: " + author.getAuthorName());
        bookOutput.stream().forEach(e ->
            System.out.println("Title: " + e.getBookName())
        );
        return author;  
        
    }

    public void readBookData(LinkedList<Book> books, LinkedList<Author> authors, LinkedList<Publisher> publishers) {
        System.out.println("Would you like to lookup a single book or list all of them?");
        System.out.println("1) Single book");
        System.out.println("2) List all books");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to go back):");

        Integer choice = getIntInput();
        while (choice< 0 && choice> 2 && choice == -1) {
            System.out.println("ERROR: Not a valid choice.");
            choice = getIntInput();
        }

        switch (choice) {
            case -1:
                return;
            case 1:
                lookupSingleBook(books, authors, publishers);
                break;
            case 2:
                books.stream().forEach(e -> 
                    System.out.println("Title: " + e.getBookName())
                );
        }
    }

    public Book lookupSingleBook(LinkedList<Book> books, LinkedList<Author> authors, LinkedList<Publisher> publishers) {
        System.out.println("What is the book you are looking for?");
        String bookToFind = getStringInput();
        
        List<Book> listOutput = books.stream()
            .filter(e -> e.getBookName().equals(bookToFind))
            .collect(Collectors.toList());

        if(listOutput.size() == 0) {
            System.out.println("No book of that title was found.");
            return null;
        }
        
        Book book = listOutput.get(0);
        
        int authorID = book.getAuthorID();
        int publisherID = book.getPublisherID();

        String authorOutput = authors.stream()
            .filter(e -> e.getAuthorID() == authorID)
            .map(e -> e.getAuthorName())
            .collect(Collectors.joining());

        String publisherOutput = publishers.stream()
            .filter(e -> e.getPublisherID() == publisherID)
            .map(e -> e.getPublisherName())
            .collect(Collectors.joining());

        System.out.println("Author: " + authorOutput);
        System.out.println("Publisher: " + publisherOutput);
        
        return book;
        
    }

    public void readPublisherData(LinkedList<Publisher> publishers, LinkedList<Book> books) {
        System.out.println("Would you like to lookup a single Author or list all of them?");
        System.out.println("1) Single publisher");
        System.out.println("2) List all publishers");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to go back):");

        Integer choice = getIntInput();
        while (choice< 0 && choice> 2 && choice != -1) {
            System.out.println("ERROR: Not a valid choice.");
            choice = getIntInput();
        }

        switch (choice) {
            case -1:
                return;
            case 1:
                lookupSinglePublisher(publishers, books);
                break;
            case 2:
                publishers.stream().forEach(e-> 
                    System.out.println("Publisher: " + e.getPublisherName())
                );
        }
    }

    public Publisher lookupSinglePublisher(LinkedList<Publisher> publishers, LinkedList<Book> books) {
        System.out.println("Who is the author you are looking for?");
        String publisherToFind = getStringInput();
        
        List<Publisher> listOutput = publishers.stream()
            .filter(e -> e.getPublisherName().equals(publisherToFind))
            .collect(Collectors.toList());
            
        if(listOutput.size()==0) {
            System.out.println("The publisher is not in our system.");
            return null;
        }

        Publisher publisher = listOutput.get(0);

        int publisherID = publisher.getPublisherID();

        List<Book> bookOutput = books.stream()
            .filter(e -> e.getPublisherID() == publisherID)
            .collect(Collectors.toList());

        System.out.println("Books by: " + publisher.getPublisherName());
        bookOutput.stream().forEach(e ->
            System.out.println("Title: " + e.getBookName())
        );

        return publisher;
        
    }

    // Update Functions
    public LinkedList<Author> updateAuthorData(LinkedList<Author> authors) {
        System.out.println("Who is the author you are updating?");
        String authorToFind = getStringInput();

        OptionalInt indexOpt = IntStream.range(0, authors.size())
            .filter(i -> authorToFind.equals(authors.get(i).getAuthorName()))
            .findFirst();

        if(indexOpt.isEmpty()) {
            System.out.println("The author is not in our system.");
            return null;
        } else {
            System.out.println("What is the new name of the author?");
            String newAuthorName = getStringInput();
            authors.get(indexOpt.getAsInt()).setAuthorName(newAuthorName);
            System.out.println("Author updated.");
        }

        return authors;
    }

    public LinkedList<Book> updateBookData(LinkedList<Book> books, LinkedList<Author> authors, LinkedList<Publisher> publishers) {
        System.out.println("What is the book you are updating?");
        String bookToFind = getStringInput();

        OptionalInt indexOpt = IntStream.range(0, books.size())
            .filter(i -> bookToFind.equals(books.get(i).getBookName()))
            .findFirst();


        if(indexOpt.isEmpty()) {
            System.out.println("The book is not in our system.");
        } else {
            System.out.println("What part of the book data would you like to update?");
            System.out.println("2) Update the book author");
            System.out.println("2) Update the book publisher");
            System.out.println("3) Update both");
            System.out.println();
            System.out.println("Please select an option (or Enter -1 to go back):");

            Integer choice = getIntInput();
            while (choice< 0 && choice> 3 && choice != -1) {
                System.out.println("ERROR: Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case -1:
                    return null;
                case 1:
                    updateBookDataHelperAuthor(books, indexOpt, authors);
                    return books;
                case 2:
                    updateBookDataHelperPublisher(books, indexOpt, publishers);
                    return books;
                case 3:
                    updateBookDataHelperAuthor(books, indexOpt, authors);
                    updateBookDataHelperPublisher(books, indexOpt, publishers);
                    return books;
            }
        }

        return null;
    }

    private void updateBookDataHelperAuthor(LinkedList<Book> books, OptionalInt index, LinkedList<Author> authors) {
        System.out.println("We can only change the author to another author in our system.");
        System.out.println("If we are unable to find the author, then you must create a new author and update the book information again.");

        Author author = lookupSingleAuthor(authors, books);

        if(author == null){
            return;
        }else {
            books.get(index.getAsInt()).setAuthorID(author.getAuthorID());
        }

        System.out.println("Publisher updated.");
    }

    private void updateBookDataHelperPublisher(LinkedList<Book> books, OptionalInt index, LinkedList<Publisher> publishers) {
        System.out.println("We can only change the author to another author in our system.");
        System.out.println("If we are unable to find the author, then you must create a new author and update the book information again.");

        Publisher publisher = lookupSinglePublisher(publishers, books);

        if(publisher == null){
            return;
        }else {
            books.get(index.getAsInt()).setAuthorID(publisher.getPublisherID());
        }

        System.out.println("Publisher updated.");
    }

    public LinkedList<Publisher> updatePublisherData(LinkedList<Publisher> publishers) {
        System.out.println("What is the publisher you are updating?");
        String publisherToFind = getStringInput();

        OptionalInt indexOpt = IntStream.range(0, publishers.size())
            .filter(i -> publisherToFind.equals(publishers.get(i).getPublisherName()))
            .findFirst();

        if(indexOpt.isEmpty()) {
            System.out.println("The publisher is not in our system.");
        } else {
            System.out.println("What part of the publisher data would you like to update?");
            System.out.println("1) Update the publisher name");
            System.out.println("2) Update the publisher address");
            System.out.println("3) Update both");
            System.out.println();
            System.out.println("Please select an option (or Enter -1 to go back):");

            Integer choice = getIntInput();
            while (choice< 0 && choice> 3 && choice != -1) {
                System.out.println("ERROR: Not a valid choice.");
                choice = getIntInput();
            }

            switch (choice) {
                case -1:
                    return null;
                case 1:
                    updatePublisherDataHelperName(publishers, indexOpt);
                    return publishers;
                case 2:
                    updatePublisherDataHelperAddress(publishers, indexOpt);
                    return publishers;
                case 3:
                    updatePublisherDataHelperName(publishers, indexOpt);
                    updatePublisherDataHelperAddress(publishers, indexOpt);
                    return publishers;
            }

        }
        return null;
    }

    private void updatePublisherDataHelperName(LinkedList<Publisher> publishers, OptionalInt index) {
        System.out.println("What is the new name of the publisher?");
        String newPublisherName = getStringInput();
        publishers.get(index.getAsInt()).setPublisherName(newPublisherName);
        System.out.println("Publisher updated.");
    }

    private void updatePublisherDataHelperAddress(LinkedList<Publisher> publishers, OptionalInt index) {
        System.out.println("What is the new address of the publisher?");
        String newPublisherAddress = getStringInput();
        publishers.get(index.getAsInt()).setAddress(newPublisherAddress);
        System.out.println("Publisher updated.");
    }

    // Delete Functions
    public LinkedList<Publisher> deletePublisherData(LinkedList<Publisher> publishers, LinkedList<Book> books) {
        System.out.println("What is the publisher you are deleting?");
        String publisherToFind = getStringInput();
        
        OptionalInt indexOpt = IntStream.range(0, publishers.size())
            .filter(i -> publisherToFind.equals(publishers.get(i).getPublisherName()))
            .findFirst();
            
        if(indexOpt.isEmpty()) {
            System.out.println("The publisher is not in our system.");
            return null;
        }

        Publisher publisher = publishers.get(indexOpt.getAsInt());

        List<Book> booksByPublisher = books.stream()
            .filter(e -> e.getPublisherID() == publisher.getPublisherID())
            .collect(Collectors.toList());

        booksByPublisher.forEach(book -> {
            books.remove(book);
        });

        publishers.remove(indexOpt.getAsInt());

        return publishers;
    }

    public LinkedList<Author> deleteAuthorData(LinkedList<Author> authors, LinkedList<Book> books) {
        System.out.println("What is the author you are deleting?");
        String authorToFind = getStringInput();
        
        OptionalInt indexOpt = IntStream.range(0, authors.size())
            .filter(i -> authorToFind.equals(authors.get(i).getAuthorName()))
            .findFirst();
            
        if(indexOpt.isEmpty()) {
            System.out.println("The author is not in our system.");
            return null;
        }

        Author author = authors.get(indexOpt.getAsInt());

        List<Book> booksByAuthor = books.stream()
            .filter(e -> e.getAuthorID() == author.getAuthorID())
            .collect(Collectors.toList());

            booksByAuthor.forEach(book -> {
            books.remove(book);
        });

        authors.remove(indexOpt.getAsInt());

        return authors;
    }

    public LinkedList<Book> deleteBookData(LinkedList<Book> books) {
        System.out.println("What is the book you are deleting?");
        String bookToFind = getStringInput();
        
        OptionalInt indexOpt = IntStream.range(0, books.size())
            .filter(i -> bookToFind.equals(books.get(i).getBookName()))
            .findFirst();
            
        if(indexOpt.isEmpty()) {
            System.out.println("The book is not in our system.");
            return null;
        }

        books.remove(indexOpt.getAsInt());

        return books;
    }

}
