package com.ss.lms.partone;

public class FileHandler {
    String hello = "Hello, World!";
    String filePath = null;

    private void writeObject() {
        // One method to write and be called by the other writing object. Should I make this a parent class
    }

    public boolean writeAuthor(Author author) {
        System.out.println(hello);
        return true;
    }

    public boolean writeBook(Book book) {
        System.out.println(hello);
        return true;
    }

    public boolean writePublisher(Publisher publisher) {
        System.out.println(hello);
        return true;
    }

    public boolean updateAuthor(Author author) {
        System.out.println(hello);
        return true;
    }

    public boolean updateBook(Book book) {
        System.out.println(hello);
        return true;
    }

    public boolean updatePublisher(Publisher publisher) {
        System.out.println(hello);
        return true;
    }


}