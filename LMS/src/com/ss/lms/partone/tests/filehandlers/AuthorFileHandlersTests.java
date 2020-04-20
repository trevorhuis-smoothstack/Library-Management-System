package com.ss.lms.partone.tests.filehandlers;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import com.ss.lms.partone.filehandler.AuthorFileHandler;
import com.ss.lms.partone.models.Author;

import org.junit.Test;

public class AuthorFileHandlersTests {
    @Test
    public void writeNewDataTest() {
        LinkedList<Author> aList = new LinkedList<>();
        AuthorFileHandler afh = new AuthorFileHandler();

        //CLEAR DATA
        afh.overwriteFile(aList);


        Author author = new Author(1, "Trevor");
        aList.add(author);
        afh.appendFile(author.toString());

        assertEquals(aList.equals(afh.readFile()), true);
    }

    @Test 
    public void overwriteDataTest() {
        AuthorFileHandler afh = new AuthorFileHandler();

        LinkedList<Author> aList = new LinkedList<>();
        Author author = new Author(1, "Trevor");
        Author author2 = new Author(22, "David");
        Author author3 = new Author(3, "John");
        aList.add(author);
        aList.add(author2);
        aList.add(author3);

        LinkedList<Author> aList2 = new LinkedList<>();
        Author author4 = new Author(1, "Nick");
        Author author5 = new Author(2, "Bob");
        aList2.add(author4);
        aList2.add(author5);


        afh.overwriteFile(aList);
        LinkedList<Author> readList = afh.readFile();
        // Test the first list
        assertEquals(readList.get(0).getAuthorName().equals("Trevor"), true);
        assertEquals(readList.get(1).getAuthorID() == 22, true);

        afh.overwriteFile(aList2);
        readList = afh.readFile();
        //Test the new list
        assertEquals(readList.get(0).getAuthorName().equals("Trevor"), false);
        assertEquals(readList.get(1).getAuthorID() == 22, false);

        assertEquals(readList.get(0).getAuthorName().equals("Nick"), true);
        assertEquals(readList.get(1).getAuthorID() == 2, true);

    }
}