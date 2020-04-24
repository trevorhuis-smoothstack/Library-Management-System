package com.ss.lms.tests.models;

import static org.junit.Assert.assertEquals;

import com.ss.lms.models.Book;

import org.junit.Test;

public class BookTest {
    @Test
    public void testSerializationTrue() {
        Book book = new Book(1, "Fake Title", 1, 1);
        Book book2 = new Book(1, "Fake Title", 1, 1);

        assertEquals(book.equals(book2), true);
    }

    @Test
    public void testSerializationFalse() {
        Book book = new Book(1, "Fake Title", 1, 1);
        Book book2 = new Book(1, "Fake Title", 1, 2);

        assertEquals(book.equals(book2), false);
    }

    @Test 
    public void toStringTest() {
        Book book = new Book(1, "Fake Title", 1, 1);
        assertEquals(book.toString(), "1, Fake Title, 1, 1");
    }

    @Test
    public void gettersAndSettersTest() {
        Book book = new Book(1, "Fake Title", 1, 1);
        assertEquals(book.getBookID(), 1);
        assertEquals(book.getBookName(), "Fake Title");
        assertEquals(book.getAuthorID(), 1);
        assertEquals(book.getPublisherID(), 1);

    }
}