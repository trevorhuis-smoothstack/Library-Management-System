package com.ss.lms.partone.filehandler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.LinkedList;

import com.ss.lms.partone.models.Author;

public class AuthorFileHandler {
 
    public void appendFile(String author) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/authors.csv", true))){
            writer.write(author + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<Author> readFile() {
        LinkedList<Author> authors = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Author author;

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/authors.csv"))) {
            lineRead = reader.readLine();

            while (lineRead != null) {
                splitLine = lineRead.split(", ");

                author = new Author(Integer.parseInt(splitLine[0]), splitLine[1]);

                authors.add(author);
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return authors;
    }

    public void overwriteFile(LinkedList<Author> authors) {
        String authorString;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/authors.temp", false))) {

            for(int i=0; i< authors.size(); i++) {
                authorString = authors.get(i).toString();
                writer.write(authorString + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("resources/authors.csv");
            File tempFile = new File("resources/authors.temp");

            file.delete();
            tempFile.renameTo(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}