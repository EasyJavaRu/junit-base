package ru.easyjava.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StringUtilsSetupTest {

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
    }

    @Test
    public void testToDouble() {
        assertEquals(3.1415, StringUtils.toDouble("3.1415"), 0.0001);
        assertEquals("Not NaN for null", Double.NaN, StringUtils.toDouble(null), 0.0001);
    }

    @Test
    public void testFromDouble() {
        assertEquals("3.1415", StringUtils.fromDouble(3.1415));
    }
}
