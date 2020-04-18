package com.ss.lms.partone.tests.author;

import static org.junit.Assert.assertEquals;

import com.ss.lms.partone.models.Author;

import org.junit.Test;

public class AddAuthorTest {

    @Test 
    public void authorNameEmptyOrNull() {
        Author author = new Author();
        assertEquals(author.showMeSomething(), "Something");
    }
}