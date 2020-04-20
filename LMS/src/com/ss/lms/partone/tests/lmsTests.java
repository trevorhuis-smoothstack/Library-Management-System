package com.ss.lms.partone.tests;

import static org.junit.Assert.assertEquals;

import com.ss.lms.partone.LibraryManagementSystem;

import org.junit.Test;

public class lmsTests {
    @Test
    public void getIntInputTest() {
        LibraryManagementSystem lms = new LibraryManagementSystem();
        Integer testInt = lms.getIntInput();

        assertEquals(testInt == 10, true);
        
    }
}