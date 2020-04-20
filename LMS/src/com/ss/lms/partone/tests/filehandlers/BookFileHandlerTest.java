package com.ss.lms.partone.tests.filehandlers;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import com.ss.lms.partone.filehandler.BookFileHandler;
import com.ss.lms.partone.models.Book;

import org.junit.Test;

public class BookFileHandlerTest {
    @Test
    public void writeNewDataTest() {
        BookFileHandler bfh = new BookFileHandler();
        LinkedList<Book> aList = new LinkedList<>();

        //Clear data
        bfh.overwriteFile(aList);

        Book book = new Book(1, "Lord of The Rings", 1, 3);
        aList.add(book);

        bfh.appendFile(book.toString());
        LinkedList<Book> empty = new LinkedList<>();
        assertEquals(aList.equals(bfh.readFile()), true);
        assertEquals(empty.equals(bfh.readFile()), false);
    }

    @Test 
    public void overwriteDataTest() {
        BookFileHandler bfh = new BookFileHandler();

        LinkedList<Book> aList = new LinkedList<>();
        Book book = new Book(1, "The Bible", 1, 3);
        Book book2 = new Book(22, "Harry Potter", 4, 1);
        Book book3 = new Book(3, "Design Patterns", 2, 1);
        aList.add(book);
        aList.add(book2);
        aList.add(book3);

        LinkedList<Book> aList2 = new LinkedList<>();
        Book book4 = new Book(1, "Clean Code", 1, 5);
        Book book5 = new Book(2, "Eloquent Javascript", 2, 4);
        aList2.add(book4);
        aList2.add(book5);


        bfh.overwriteFile(aList);
        LinkedList<Book> readList = bfh.readFile();
        // Test the first list
        assertEquals(readList.get(0).getBookName().equals("The Bible"), true);
        assertEquals(readList.get(1).getBookID() == 22, true);
        assertEquals(readList.get(1).getAuthorID() == 4, true);
        assertEquals(readList.get(2).getPublisherID() == 1, true);

        bfh.overwriteFile(aList2);
        readList = bfh.readFile();
        //Test the new list
        assertEquals(readList.get(0).getBookName().equals("The Bible"), false);
        assertEquals(readList.get(1).getBookID() == 22, false);
        assertEquals(readList.get(1).getAuthorID() == 4, false);
        assertEquals(readList.get(0).getPublisherID() == 1, false);

        assertEquals(readList.get(0).getBookName().equals("Clean Code"), true);
        assertEquals(readList.get(1).getBookID() == 2, true);
        assertEquals(readList.get(1).getAuthorID() == 2, true);
        assertEquals(readList.get(0).getPublisherID() == 5, true);

    }
}