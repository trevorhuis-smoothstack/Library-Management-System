package com.ss.lms.partone.models;

import java.io.Serializable;

public class Author implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 6507072100577910119L;
    private int authorID;
    private String authorName;

    public Author(int authorID, String authorName) {
        this.authorID = authorID;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return authorID + ", " + authorName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + authorID;
        result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
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
        Author other = (Author) obj;
        if (authorID != other.authorID)
            return false;
        if (authorName == null) {
            if (other.authorName != null)
                return false;
        } else if (!authorName.equals(other.authorName))
            return false;
        return true;
    }

    

    

}