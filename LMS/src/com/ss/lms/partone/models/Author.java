package com.ss.lms.partone.models;

public class Author extends LibraryData {
    private int author_id;
    private String author_name;

    @Override
    public String toString() {
        return author_id + ", " + author_name + "\n";
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String showMeSomething() {
        return "Something";
    }
}