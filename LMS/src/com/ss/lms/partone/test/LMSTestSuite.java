package com.ss.lms.partone.test;

import com.ss.lms.partone.tests.author.AuthorTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AuthorTestSuite.class})
public class LMSTestSuite {

}