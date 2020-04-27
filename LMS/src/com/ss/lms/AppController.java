package com.ss.lms;

import java.sql.SQLException;

import com.ss.lms.service.TerminalService;

public class AppController {
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

}