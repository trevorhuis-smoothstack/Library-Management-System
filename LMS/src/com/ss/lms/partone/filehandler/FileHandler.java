package com.ss.lms.partone.filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import com.ss.lms.partone.models.LibraryData;

public class FileHandler {
    String filePath = null;

    public void addObjectToFile(HashMap<Integer, LibraryData> map) {
        return;
    }

    public void updateObjectOnFile(HashMap<Integer, LibraryData> map) {
        return;
    }

    public void deleteObjectFromFile(HashMap<Integer, LibraryData> map) {
        return;
    }

    public HashMap<Integer, LibraryData> readFile() {
        HashMap<Integer, LibraryData> fileContents = new HashMap<>();

        try (BufferedReader bufStream = new BufferedReader(new FileReader(this.filePath))) {
            String line = bufStream.readLine();

            

            while (line != null) {
                System.out.println(line);
                line = bufStream.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return fileContents;

    }
}