package com.ss.lms.partone.models;

public class Publisher extends LibraryData{
    private int publisher_id;
    private String publisher_name;
    private String address;

    @Override
    public String toString() {
        return publisher_id + ", " + publisher_name + ", " + address + "\n"; 
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}