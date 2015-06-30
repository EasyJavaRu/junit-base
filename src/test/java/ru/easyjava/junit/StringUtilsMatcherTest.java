package ru.easyjava.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StringUtilsMatcherTest {

    private String testString;
    private String[] testArray;

    private static String veryLargeString;

    @Before
    public void setUp() {
        testString =  "T:E:S:T";
        testArray = new String[] {"T", "E", "S", "T"};
    }

    @After
    public void tearDown() {
        testString=""; //Not very useful, but good enough for the example.
    }

    @BeforeClass
    public static void setUpClass() {
        veryLargeString = new BigInteger(16384, new Random()).toString();
    }

    @AfterClass
    public static void tearDownClass() {
        veryLargeString = "";
    }

    @Test
    public void testToArray() {
        //assertArrayEquals("Wrong array", testArray, StringUtils.toArray(testString, ':'));
        //Assert.assertEquals(0, StringUtils.toArray(null, ':').length);
        assertThat(StringUtils.toArray(testString, ':'), is(testArray));
        assertThat(StringUtils.toArray(null, ':').length, is(0));

        //Power of matchers
        assertThat(
                Arrays.asList(StringUtils.toArray(testString, ':')),
                containsInAnyOrder("T","T", "S","E"));

    }

    @Test
    public void testToArrayLong() {
        assertEquals("Should not be splitted", veryLargeString, StringUtils.toArray(veryLargeString, ':')[0]);
    }

    @Test
    public void testJoinArray() {
        //assertEquals(testString, StringUtils.joinArray(testArray, ':'));
        assertThat(StringUtils.joinArray(testArray, ':'), is(testString));
        assertNull(StringUtils.joinArray(null, ':'));
    }

    @Test
    public void testIsEmpty() {
        assertFalse("Non empty string claimed to be empty", StringUtils.isEmpty("TEST"));
        assertTrue("Empty string not recognized", StringUtils.isEmpty(""));
        assertTrue("Whitespaces not recognized", StringUtils.isEmpty(" "));
    }

    @Test
    public void testToDouble() {
        //assertEquals(3.1415, StringUtils.toDouble("3.1415"), 0.0001);
        assertThat(
                StringUtils.toDouble("3.1415"),
                is(closeTo(3.1415, 0.0001)));

        //assertEquals("Not NaN for null", Double.NaN, StringUtils.toDouble(null));
        assertThat(
                StringUtils.toDouble(null),
                is(Double.NaN));
    }

    @Test
    public void testFromDouble() {
        assertThat(
                StringUtils.fromDouble(3.1415),
                is("3.1415"));
    }
}
