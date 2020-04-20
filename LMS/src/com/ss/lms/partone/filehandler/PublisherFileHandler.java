package com.ss.lms.partone.filehandler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.LinkedList;

import com.ss.lms.partone.models.Publisher;

public class PublisherFileHandler {
 
    public void appendFile(String publisher) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/publishers.csv", true))){
            writer.write(publisher + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<Publisher> readFile() {
        LinkedList<Publisher> publishers = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Publisher publisher;

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/publishers.csv"))) {
            lineRead = reader.readLine();

            while (lineRead != null) {
                splitLine = lineRead.split(", ");

                publisher = new Publisher(Integer.parseInt(splitLine[0]), splitLine[1], splitLine[2]);

                publishers.add(publisher);
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return publishers;
    }

    public void overwriteFile(LinkedList<Publisher> publishers) {
        String publisherString;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/publishers.temp", false))) {

            for(int i=0; i< publishers.size(); i++) {
                publisherString = publishers.get(i).toString();
                writer.write(publisherString + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("resources/publishers.csv");
            File tempFile = new File("resources/publishers.temp");

            file.delete();
            tempFile.renameTo(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}