package ru.easyjava.junit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;

import java.math.BigInteger;
import java.util.Random;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringUtilsRuleTest {

    private String testString;
    private String[] testArray;

    private static String veryLargeString;

    @Rule
    public ExternalResource fixtures = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            testString =  "T:E:S:T";
            testArray = new String[] {"T", "E", "S", "T"};
        }

        @Override
        protected void after() {
            testString=""; //Not very useful, but good enough for the example.
        }
    };

    @ClassRule
    public static ExternalResource classFixtures = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            veryLargeString = new BigInteger(16384, new Random()).toString();
        }

        @Override
        protected void after() {
            veryLargeString = "";
        }
    };

    @Rule
    public TestName nameRule = new TestName();

    @Rule
    public ErrorCollector collectorRule = new ErrorCollector();

    @Test
    public void testToArray() {
        assertArrayEquals("Wrong array", testArray, StringUtils.toArray(testString, ':'));
        assertEquals(0, StringUtils.toArray(null, ':').length);
    }

    @Test
    public void testToArrayLong() {
        assertEquals("Should not be splitted", veryLargeString, StringUtils.toArray(veryLargeString, ':')[0]);
    }

    @Test
    public void testJoinArray() {
        assertEquals(testString, StringUtils.joinArray(testArray, ':'));
        assertNull(StringUtils.joinArray(null, ':'));
    }

    @Test
    public void testIsEmpty() {
        assertFalse("Non empty string claimed to be empty", StringUtils.isEmpty("TEST"));
        assertTrue("Empty string not recognized", StringUtils.isEmpty(""));
        assertTrue("Whitespaces not recognized", StringUtils.isEmpty(" "));

        assertFalse("Test name could not be empty", StringUtils.isEmpty(nameRule.getMethodName()));
    }

    @Test
    public void testToDouble() {
        collectorRule.checkThat(
                StringUtils.toDouble("3.1415"),
                is(closeTo(3.1415, 0.0001)));

        collectorRule.checkThat(
                StringUtils.toDouble(null),
                is(Double.NaN));
    }

    @Test
    public void testFromDouble() {
        assertEquals("3.1415", StringUtils.fromDouble(3.1415));
    }
}
