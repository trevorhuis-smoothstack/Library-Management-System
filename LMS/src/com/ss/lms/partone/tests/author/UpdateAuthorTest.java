package com.ss.lms.partone.tests.author;

import static org.junit.Assert.assertEquals;

import com.ss.lms.partone.Author;

import org.junit.Test;

public class UpdateAuthorTest {

    @Test 
    public void authorNameEmptyOrNull() {
        Author author = new Author();
        assertEquals(author.showMeSomething(), "Something");
    }
}