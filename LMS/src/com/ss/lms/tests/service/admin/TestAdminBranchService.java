package com.ss.lms.tests.service.admin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.models.LibraryBranch;
import com.ss.lms.service.admin.AdminBranchService;

import org.junit.Test;

public class TestAdminBranchService {
    @Test
    public void addABranchTest() throws SQLException {
        AdminBranchService adminLibService = new AdminBranchService();
        LibraryBranch branch = new LibraryBranch();
        branch.setBranchName("Test Library Branch");
        branch.setBranchAddress("789 Test St.");
        Integer primaryKey = adminLibService.addABranch(branch);
        System.out.println(primaryKey);

        assertEquals(adminLibService.readABranch(primaryKey).getBranchName().equals("Test Library Branch"), true);
    }

    @Test
    public void updateABranchTest() throws SQLException {
        AdminBranchService adminLibService = new AdminBranchService();
        LibraryBranch branch = new LibraryBranch();
        branch.setBranchName("Test Library Branch");
        branch.setBranchAddress("789 Test St.");
        Integer primaryKey = adminLibService.addABranch(branch);
        System.out.println(primaryKey);

        LibraryBranch updateBor = new LibraryBranch(primaryKey, "Updated Test Library Branch", "789 Test St.");
        adminLibService.updateABranch(updateBor);

        assertEquals(adminLibService.readABranch(primaryKey).getBranchName().equals("Updated Test Library Branch"), true);
    }

    @Test
    public void deleteABranchTest() throws SQLException {
        AdminBranchService adminLibService = new AdminBranchService();
        LibraryBranch branch = new LibraryBranch();
        branch.setBranchName("Test Library Branch");
        branch.setBranchAddress("789 Test St.");
        Integer primaryKey = adminLibService.addABranch(branch);
        branch.setBranchId(primaryKey);
        System.out.println(primaryKey);
        assertEquals(adminLibService.readABranch(primaryKey).getBranchName().equals("Test Library Branch"), true);

        adminLibService.deleteABranch(branch);

        LibraryBranch deletedBranch = adminLibService.readABranch(primaryKey);

        assertEquals(deletedBranch ==null, true);
    }

    @Test
    public void readABranchTest() throws SQLException {
        AdminBranchService adminLibService = new AdminBranchService();
        LibraryBranch branch = adminLibService.readABranch(1);
        System.out.println(branch.getBranchName());

        assertEquals(branch.getBranchName().equals("Hyde Park"), true);
    }

    @Test
    public void readAllLibraryBranchesTest() throws SQLException {
        AdminBranchService adminLibService = new AdminBranchService();
        List<LibraryBranch> branches = adminLibService.readAllBranches();

        assertEquals(branches.get(0) instanceof LibraryBranch, true);
    }
}