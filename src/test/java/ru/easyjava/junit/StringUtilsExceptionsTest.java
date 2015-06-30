package ru.easyjava.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StringUtilsExceptionsTest {
    private final String testString  = "TEST";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test(expected = NumberFormatException.class)
    public void testToDoubleException() {
        StringUtils.toDouble(testString);
    }

    @Test
    public void testToDoubleExceptionDeepCheck() {
        exception.expect(NumberFormatException.class);
        exception.expectMessage(containsString(testString));

        StringUtils.toDouble(testString);
    }

    @Test
    public void testToDoubleExceptionManual() {
        try {
            StringUtils.toDouble(testString);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException ex) {
            assertThat(ex.getMessage(), containsString(testString));
        }
    }
}
