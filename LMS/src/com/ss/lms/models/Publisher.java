package com.ss.lms.models;

import java.io.Serializable;
import java.util.List;

public class Publisher implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -4973659968249782246L;
    private int publisherID;
    private String publisherName;
    private String address;
    private List<Book> books;

    public Publisher(int publisherID, String publisherName, String address, List<Book> books) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.address = address;
        this.books = books;
    }

    @Override
    public String toString() {
        return publisherID + ", " + publisherName + ", " + address;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((books == null) ? 0 : books.hashCode());
        result = prime * result + publisherID;
        result = prime * result + ((publisherName == null) ? 0 : publisherName.hashCode());
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
        Publisher other = (Publisher) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        if (publisherID != other.publisherID)
            return false;
        if (publisherName == null) {
            if (other.publisherName != null)
                return false;
        } else if (!publisherName.equals(other.publisherName))
            return false;
        return true;
    }
}