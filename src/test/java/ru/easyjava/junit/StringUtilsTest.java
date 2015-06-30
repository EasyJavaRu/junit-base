package ru.easyjava.junit;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void testJoinArray() {
        String[] source = {"T", "E", "S", "T"};
        String expected="T:E:S:T";
        assertEquals(expected,StringUtils.joinArray(source, ':'));
        assertNull(StringUtils.joinArray(null, ':'));
    }

    @Test
    public void testToArray() {
        String[] expected = {"T", "E", "S", "T"};
        String source="T:E:S:T";
        assertArrayEquals("Wrong array", expected, StringUtils.toArray(source, ':'));
        assertEquals(0,StringUtils.toArray(null, ':').length);
    }

    @Test
    public void testIsEmpty() {
        assertFalse("Non empty string claimed to be empty", StringUtils.isEmpty("TEST"));
        assertTrue("Empty string not recognized", StringUtils.isEmpty(""));
        assertTrue("Whitespaces not recognized",StringUtils.isEmpty(" "));
    }

    @Test
    public void testToDouble() {
        assertEquals(3.1415, StringUtils.toDouble("3.1415"), 0.0001);
        assertEquals("Not NaN for null", Double.NaN, StringUtils.toDouble(null), 0.00001);
    }

    @Test
    public void testFromDouble() {
        double source = 3.1415;
        String expected="3.1415";

        String actual = StringUtils.fromDouble(source);
        assertEquals("Unexpected string value", expected, actual);
    }
}
