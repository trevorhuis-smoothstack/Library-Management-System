package com.ss.lms.partone;

public class Book {
    private int book_id;
    private String book_name;
    private int author_id;
    private int publisher_id;

    @Override
    public String toString() {
        return book_id + ", " + book_name + ", " + author_id + ", " + publisher_id + "\n";
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    
}