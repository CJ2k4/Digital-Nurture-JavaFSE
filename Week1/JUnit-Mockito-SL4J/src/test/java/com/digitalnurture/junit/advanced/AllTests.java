package com.digitalnurture.junit.advanced;

import com.digitalnurture.junit.basic.AssertionsTest;
import com.digitalnurture.junit.basic.CalculatorTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CalculatorTest.class, AssertionsTest.class, EvenCheckerTest.class})
class AllTests {
}
