package com.ss.lms.models;

import java.io.Serializable;

public class Book implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = -5777962224597444798L;
    private int bookId;
    private String title;
    private int authorId;
    private int publisherId;

    @Override
    public String toString() {
        return bookId + ", " + title + ", " + authorId + ", " + publisherId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Book(int bookId, String title, int authorId, int publisherId) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    public Book() {
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + authorId;
        result = prime * result + bookId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + publisherId;
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
        if (authorId != other.authorId)
            return false;
        if (bookId != other.bookId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (publisherId != other.publisherId)
            return false;
        return true;
    }
    
}