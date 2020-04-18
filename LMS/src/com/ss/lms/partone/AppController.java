package com.ss.lms.partone;

import java.util.HashMap;

import com.ss.lms.partone.filehandler.AuthorFileHandler;
import com.ss.lms.partone.filehandler.BookFileHandler;
import com.ss.lms.partone.filehandler.PublisherFileHandler;

import com.ss.lms.partone.models.Author;
import com.ss.lms.partone.models.Book;
import com.ss.lms.partone.models.LibraryData;
import com.ss.lms.partone.models.Publisher;

public class AppController {
    Integer dataTypeChoice;
    StringBuffer strBuf = new StringBuffer();

    String errorMessage = "\n ERROR: Not a valid choice.";

    private LibraryManagementSystem lms = new LibraryManagementSystem();
    private AuthorFileHandler afh = new AuthorFileHandler();
    private BookFileHandler bfh = new BookFileHandler();
    private PublisherFileHandler pfh = new PublisherFileHandler();

    private HashMap<Integer, LibraryData> authorMap = afh.readFile();
    private HashMap<Integer, LibraryData> bookMap = bfh.readFile();
    private HashMap<Integer, LibraryData> publisherMap = pfh.readFile();

    public static void main(String[] args) {
        AppController app = new AppController();
        app.startApp();
    }

    public void startApp() {
        Integer operationChoice = lms.getCRUDOperationChoice();

        while (operationChoice != -1) {
            
            switch (operationChoice) {
                case 1:
                    runMenuOne();                    
                    break;
                case 2:
                    runMenuTwo();
                    break;
                case 3:
                    runMenuThree();
                    break;
                case 4:
                   runMenuFour();
                   break;
                default:
                    System.out.println(errorMessage);
            }
            operationChoice = lms.getCRUDOperationChoice();
        }
    }

    public void runMenuOne() {
        Integer dataTypeChoice = lms.getABPDataTypeChoice();
        switch (dataTypeChoice) {
            case 1:
                lms.createData(authorMap);
                afh.addObjectToFile(authorMap);
                break;
            case 2:
                lms.createData(bookMap);
                bfh.addObjectToFile(bookMap);
                break;
            case 3:
                lms.createData(publisherMap);
                pfh.addObjectToFile(publisherMap);
                break;
            default:
                System.out.println(errorMessage);
        }
    }

    public void runMenuTwo() {
        dataTypeChoice = lms.getABPDataTypeChoice();
        switch (dataTypeChoice) {
            case 1:
                lms.readData(authorMap);
                break;
            case 2:
                lms.readData(bookMap);
                break;
            case 3:
                lms.readData(publisherMap);
                break;
            default:
                System.out.println(errorMessage);
        }
    }

    public void runMenuThree() {
        dataTypeChoice = lms.getABPDataTypeChoice();
        switch (dataTypeChoice) {
            case 1:
                lms.removeData(authorMap);
                afh.updateObjectOnFile(authorMap);
                break;
            case 2:
                lms.removeData(bookMap);
                afh.updateObjectOnFile(bookMap);
                break;
            case 3:
                lms.removeData(publisherMap);
                afh.updateObjectOnFile(publisherMap);
                break;
            default:
                System.out.println(errorMessage);
        }
    }

    public void runMenuFour() {
        dataTypeChoice = lms.getABPDataTypeChoice();
        switch (dataTypeChoice) {
            case 1:
                lms.removeData(authorMap);
                afh.deleteObjectFromFile(authorMap);
                break;
            case 2:
                lms.removeData(bookMap);
                bfh.deleteObjectFromFile(bookMap);
                break;
            case 3:
                lms.removeData(publisherMap);
                pfh.deleteObjectFromFile(publisherMap);
                break;
            default:
                System.out.println(errorMessage);
        }
    }
}