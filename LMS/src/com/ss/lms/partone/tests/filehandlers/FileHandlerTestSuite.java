package com.ss.lms.partone.tests.filehandlers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
 
@RunWith(Suite.class)
@SuiteClasses({PublisherFileHandlerTest.class, AuthorFileHandlersTests.class, BookFileHandlerTest.class}) //adding them here
public class FileHandlerTestSuite {

}