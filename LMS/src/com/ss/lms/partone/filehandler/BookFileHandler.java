package com.ss.lms.partone.filehandler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.LinkedList;

import com.ss.lms.partone.models.Book;

public class BookFileHandler {
 
    public void appendFile(String book) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/books.csv", true))){
            writer.write(book + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<Book> readFile() {
        LinkedList<Book> books = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Book book;

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/books.csv"))) {
            lineRead = reader.readLine();

            while (lineRead != null) {
                splitLine = lineRead.split(", ");

                book = new Book(Integer.parseInt(splitLine[0]), splitLine[1], Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));

                books.add(book);
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    public void overwriteFile(LinkedList<Book> books) {
        String bookString;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/books.temp", false))) {

            for(int i=0; i< books.size(); i++) {
                bookString = books.get(i).toString();
                writer.write(bookString + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("resources/books.csv");
            File tempFile = new File("resources/books.temp");

            file.delete();
            tempFile.renameTo(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}