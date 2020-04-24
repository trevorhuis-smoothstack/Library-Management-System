package com.ss.lms.models;

import java.io.Serializable;

public class Book implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -5777962224597444798L;
    private int bookID;
    private String bookName;
    private int authorID;
    private int publisherID;

    @Override
    public String toString() {
        return bookID + ", " + bookName + ", " + authorID + ", " + publisherID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public Book(int bookID, String bookName, int authorID, int publisherID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorID = authorID;
        this.publisherID = publisherID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + authorID;
        result = prime * result + bookID;
        result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
        result = prime * result + publisherID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (authorID != other.authorID)
            return false;
        if (bookID != other.bookID)
            return false;
        if (bookName == null) {
            if (other.bookName != null)
                return false;
        } else if (!bookName.equals(other.bookName))
            return false;
        if (publisherID != other.publisherID)
            return false;
        return true;
    }
    
}