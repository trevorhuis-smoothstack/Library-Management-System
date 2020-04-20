package com.ss.lms.partone.tests.filehandlers;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import com.ss.lms.partone.filehandler.PublisherFileHandler;
import com.ss.lms.partone.models.Publisher;

import org.junit.Test;

public class PublisherFileHandlerTest {
    @Test
    public void writeNewDataTest() {
        LinkedList<Publisher> aList = new LinkedList<>();
        PublisherFileHandler pfh = new PublisherFileHandler();

        // Clear data
        pfh.overwriteFile(aList);

        Publisher pub = new Publisher(1, "Trevor Books", "123 Court");
        aList.add(pub);

        pfh.appendFile(pub.toString());

        assertEquals(aList.equals(pfh.readFile()), true);
    }

    @Test 
    public void overwriteDataTest() {
        PublisherFileHandler pfh = new PublisherFileHandler();

        LinkedList<Publisher> aList = new LinkedList<>();
        Publisher pub = new Publisher(1, "Trevor Books", "456 Drive");
        Publisher pub2 = new Publisher(22, "David Books", "1234 Lane");
        Publisher pub3 = new Publisher(3, "John Books", "1 Blvd.");
        aList.add(pub);
        aList.add(pub2);
        aList.add(pub3);

        LinkedList<Publisher> aList2 = new LinkedList<>();
        Publisher pub4 = new Publisher(1, "Nick Books", "789 St.");
        Publisher pub5 = new Publisher(2, "Bob Books", "100 Road");
        aList2.add(pub4);
        aList2.add(pub5);


        pfh.overwriteFile(aList);
        LinkedList<Publisher> readList = pfh.readFile();
        // Test the first list
        assertEquals(readList.get(0).getPublisherName().equals("Trevor Books"), true);
        assertEquals(readList.get(1).getPublisherID() == 22, true);
        assertEquals(readList.get(1).getAddress().equals("1234 Lane"), true);

        pfh.overwriteFile(aList2);
        readList = pfh.readFile();
        //Test the new list
        assertEquals(readList.get(0).getPublisherName().equals("Trevor Books"), false);
        assertEquals(readList.get(1).getPublisherID() == 22, false);
        assertEquals(readList.get(1).getAddress().equals("1234 Lane"), false);

        assertEquals(readList.get(0).getPublisherName().equals("Nick Books"), true);
        assertEquals(readList.get(1).getPublisherID() == 2, true);
        assertEquals(readList.get(1).getAddress().equals("100 Road"), true);

    }
}