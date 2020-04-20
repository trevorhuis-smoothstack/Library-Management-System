package com.ss.lms.partone.tests.models;

import static org.junit.Assert.assertEquals;

import com.ss.lms.partone.models.Publisher;

import org.junit.Test;

public class PublisherTest {
    @Test
    public void testSerializationTrue() {
        Publisher publisher = new Publisher(1, "Trevor Books", "123 Drive");
        Publisher publisher2 = new Publisher(1, "Trevor Books", "123 Drive");

        assertEquals(publisher.equals(publisher2), true);

    }

    @Test
    public void testSerializationFalse() {
        Publisher publisher =  new Publisher(1, "Trevor Books", "123 Drive");
        Publisher publisher2 = new Publisher(2, "Trevor Books", "1234 Drive");

        assertEquals(publisher.equals(publisher2), false);

    }

    @Test 
    public void toStringTest() {
        Publisher publisher = new Publisher(1, "Trevor Books", "123 Drive");
        assertEquals(publisher.toString(), "1, Trevor Books, 123 Drive");
    }

    @Test
    public void gettersAndSettersTest() {
        Publisher publisher = new Publisher(1, "Trevor Books", "123 Drive");
        assertEquals(publisher.getPublisherID(), 1);
        assertEquals(publisher.getPublisherName(), "Trevor Books");
        assertEquals(publisher.getAddress(), "123 Drive");
    }
}