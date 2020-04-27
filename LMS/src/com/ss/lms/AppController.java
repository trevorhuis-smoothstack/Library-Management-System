package com.ss.lms;

import java.sql.SQLException;

import com.ss.lms.service.TerminalService;

public class AppController {

    // public static void main(String[] args) throws SQLException {
    //     LibrarianService libServ = new LibrarianService();
    //     BookCopies entry = new BookCopies(1, 1, 4);
    //     libServ.updateCopies(entry);
    //     // Changed an existing entry
    //     BookCopies entryReturn = libServ.getAnEntryOfBookCopies(1, 1);
    //     System.out.println(entryReturn);
    // }

    public static void main(String[] args) throws SQLException{

        TerminalService term = new TerminalService();

        Integer userType = term.openingMenu();

        while(userType != 4) {
            switch (userType) {
                case 1:
                    term.librarianFunctionality();
                    break;
                case 2:
                    term.adminFunctionality();
                    break;
                case 3:
                    term.borrowerFunctionality();
                    break;
                case 4:
                    break;
            }
            userType = term.openingMenu();
        }
    }
//     Integer dataTypeChoice;
//     StringBuffer strBuf = new StringBuffer();

//     String errorMessage = "\n ERROR: Not a valid choice.";

//     private LibraryManagementSystem lms = new LibraryManagementSystem();
//     private AuthorFileHandler afh = new AuthorFileHandler();
//     private BookFileHandler bfh = new BookFileHandler();
//     private PublisherFileHandler pfh = new PublisherFileHandler();

//     private LinkedList<Author> authors = afh.readFile();
//     private LinkedList<Book> books = bfh.readFile();
//     private LinkedList<Publisher> publishers = pfh.readFile();
//     // public static void main(String[] args) {
//     //     AppController app = new AppController();
//     //     app.startApp();
//     // }

//     public void startApp() {
//         Integer operationChoice = lms.getCRUDOperationChoice();

//         while (operationChoice != -1) {
//             switch (operationChoice) {
//                 case 1:
//                     runMenuOne();                    
//                     break;
//                 case 2:
//                     runMenuTwo();
//                     break;
//                 case 3:
//                     runMenuThree();
//                     break;
//                 case 4:
//                    runMenuFour();
//                    break;
//                 default:
//                     System.out.println(errorMessage);
//             }
//             operationChoice = lms.getCRUDOperationChoice();
//         }
//     }

//     public void runMenuOne() {
//         Integer dataTypeChoice = lms.getABPDataTypeChoice("create");
//         switch (dataTypeChoice) {
//             case 1:
//                 strBuf.replace(0, strBuf.length(), lms.createAuthorData(authors));
//                 if(!strBuf.toString().equals("Not valid.")) {
//                     afh.appendFile(strBuf.toString());
//                 }
//                 authors = afh.readFile();
//                 break;
//             case 2:
//                 strBuf.replace(0, strBuf.length(), lms.createBookData(books, authors, publishers));
//                 if(!strBuf.toString().equals("Not valid.")) {
//                     bfh.appendFile(strBuf.toString());
//                 }    
//                 books = bfh.readFile();
//                 break;
//             case 3:
//                 strBuf.replace(0, strBuf.length(), lms.createPublisherData(publishers));
//                 if(!strBuf.toString().equals("Not valid.")) {
//                     pfh.appendFile(strBuf.toString());
//                 }
//                 publishers = pfh.readFile();
//                 break;
//             default:
//                 System.out.println(errorMessage);
//         }
//     }

//     public void runMenuTwo() {
//         dataTypeChoice = lms.getABPDataTypeChoice("lookup");

//         switch (dataTypeChoice) {
//             case 1:
//                 lms.readAuthorData(authors, books);
//                 break;
//             case 2:
//                 lms.readBookData(books, authors, publishers);
//                 break;
//             case 3:
//                 lms.readPublisherData(publishers, books);
//                 break;
//             default:
//                 System.out.println(errorMessage);
//         }
//     }

//     public void runMenuThree() {
//         dataTypeChoice = lms.getABPDataTypeChoice("update");
//         switch (dataTypeChoice) {
//             case 1:
//                 if(lms.updateAuthorData(authors) != null){
//                     afh.overwriteFile(authors);
//                 }
//                 authors = afh.readFile();
//                 break;
//             case 2:
//                 if(lms.updateBookData(books, authors, publishers) != null){
//                     bfh.overwriteFile(books);
//                 }
//                 books = bfh.readFile();
//                 break;
//             case 3:
//                 if(lms.updatePublisherData(publishers) != null){
//                     pfh.overwriteFile(publishers);
//                 }
//                 publishers = pfh.readFile();
//                 break;
//             default:
//                 System.out.println(errorMessage);
//         }
//     }

//     public void runMenuFour() {
//         dataTypeChoice = lms.getABPDataTypeChoice("delete");
//         switch (dataTypeChoice) {
//             case 1:
//                 if(lms.deleteAuthorData(authors, books) != null){
//                     afh.overwriteFile(authors);
//                     bfh.overwriteFile(books);
//                 }
//                 authors = afh.readFile();
//                 books = bfh.readFile();
//                 break;
//             case 2:
//                 if(lms.deleteBookData(books) != null) {
//                     bfh.overwriteFile(books);
//                 }
//                 books = bfh.readFile();
//                 break;
//             case 3:
//                 if(lms.deletePublisherData(publishers, books) != null){
//                     pfh.overwriteFile(publishers);
//                     bfh.overwriteFile(books);
//                 }
//                 books = bfh.readFile();
//                 publishers = pfh.readFile();
//                 break;
//             default:
//                 System.out.println(errorMessage);
//         }
//     }
}