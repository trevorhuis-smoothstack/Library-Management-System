package com.ss.lms.partone.models;

import java.io.Serializable;

public class Publisher implements Serializable{
    
    
    /**
     *
     */
    private static final long serialVersionUID = 1815290587924468727L;
    private int publisherID;
    private String publisherName;
    private String address;

    public Publisher(int publisherID, String publisherName, String address) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.address = address;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
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