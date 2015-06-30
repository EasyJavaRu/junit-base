package ru.easyjava.junit;

/**
 * Collection of common String functions.
 */
public final class StringUtils {

    /**
     * Do not construct me.
     */
    private StringUtils() { }

    /**
     * Combines array of string to the single string,
     * inserting delimiters between array entries.
     *
     * @param source Array of string to join.
     * @param del Delimiter for array entries.
     * @return null if array is null or joined array entries.
     */
    public static String joinArray(final String[] source, final char del) {
        if (source == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < source.length - 1; i++) {
            result.append(source[i]);
            result.append(del);
        }

        result.append(source[source.length - 1]);

        return result.toString();
    }

    /**
     * Splits the supplied strings to array of
     * strings.
     * @param source String to split.
     * @param delimiter Character to use as token boundary.
     * @return empty array if source is null or array of substrings, split
     * on delimiter character.
     */
    public static String[] toArray(final String source, final char delimiter) {
        if (source == null) {
            return new String[]{};
        }
        return source.split(Character.toString(delimiter));
    }

    /**
     * Checkes whether string contains any usable
     * content (any non-empty characters).
     * @param subject String to test.
     * @return true if string doesn't have any contents or
     * have only white space characters in it, false otherwise.
     */
    public static boolean isEmpty(final String subject) {
        return subject == null || subject.replaceAll("\\s", "").isEmpty();
    }

    /**
     * Tries to extract double value from String.
     * @param source String to process.
     * @return extracted double value or NaN if
     * source is null.
     */
    public static double toDouble(final String source) {
        if (source == null) {
            return Double.NaN;
        }
        return Double.valueOf(source);
    }

    /**
     * Converts double to string.
     * @param source value to convert.
     * @return Textual representation of double.
     */
    public static String fromDouble(final double source) {
        return String.valueOf(source);
    }
}
