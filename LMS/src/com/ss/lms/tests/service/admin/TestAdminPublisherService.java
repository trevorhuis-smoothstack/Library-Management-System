package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.Publisher;
import com.ss.lms.service.admin.AdminPublisherService;

import org.junit.Test;

public class TestAdminPublisherService {
    @Test
    public void addAPublisherTest() throws SQLException {
        AdminPublisherService aPubServ = new AdminPublisherService();
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Test Publisher");
        publisher.setAddress("789 Test St.");
        publisher.setPhone("235-423-1268");
        Integer primaryKey = aPubServ.addAPublisher(publisher);
        System.out.println(primaryKey);

        assertEquals(aPubServ.readAPublisher(primaryKey).getPublisherName().equals("Test Publisher"), true);
    }

    @Test
    public void updateAPublisherTest() throws SQLException {
        AdminPublisherService aPubServ = new AdminPublisherService();
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Test Publisher");
        publisher.setAddress("789 Test St.");
        publisher.setPhone("235-423-1268");
        Integer primaryKey = aPubServ.addAPublisher(publisher);

        Publisher updateBor = new Publisher(primaryKey, "Updated Test Publisher", "789 Test St.", "235-423-1268");
        aPubServ.updateAPublisher(updateBor);

        assertEquals(aPubServ.readAPublisher(primaryKey).getPublisherName().equals("Updated Test Publisher"), true);
    }

    @Test
    public void deleteAPublisherTest() throws SQLException {
        AdminPublisherService aPubServ = new AdminPublisherService();
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Test Publisher");
        publisher.setAddress("789 Test St.");
        Integer primaryKey = aPubServ.addAPublisher(publisher);
        publisher.setPublisherID(primaryKey);
        assertEquals(aPubServ.readAPublisher(primaryKey).getPublisherName().equals("Test Publisher"), true);

        aPubServ.deleteAPublisher(publisher);
        Publisher deletedPublisher = aPubServ.readAPublisher(primaryKey);

        assertEquals(deletedPublisher == null, true);
    }

    @Test
    public void readAPublisherTest() throws SQLException {
        AdminPublisherService aPubServ = new AdminPublisherService();
        Publisher publisher = aPubServ.readAPublisher(1);

        assertEquals(publisher.getPublisherName().equals("Penguin Books"), true);
    }

    @Test
    public void readAllPublishersTest() throws SQLException {
        AdminPublisherService aPubServ = new AdminPublisherService();
        List<Publisher> publishers = aPubServ.readAllPublishers();

        assertEquals(publishers.get(0) instanceof Publisher, true);
    }
}