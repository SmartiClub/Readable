package club.smarti.readable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Object to String translator (for development and debug purpose)
 * *
 * Advantages:
 * – avoid client code duplication
 * – the key part of Smarti Logger
 */
@SuppressWarnings("WeakerAccess")
public class Readable {

    @Contract(pure = true)
    private Readable() {
    }

    /**
     * Convert the list of objects to human readable string
     *
     * @param data - list of objects (can be empty)
     * @return the result string
     */
    @NotNull
    @Contract(pure = true, value = "null -> fail")
    public static String toString(Object... data) {
        StringBuilder builder = new StringBuilder();
        append(builder, data);
        return builder.toString();
    }

    /**
     * Convert the list of objects to human readable string and append to existing {@link StringBuilder}
     *
     * @param builder - the string builder to append
     * @param data - list of objects (can be empty)
     */
    @Contract(pure = true, value = "null, _ -> fail; _, null -> fail")
    public static void append(StringBuilder builder, Object... data) {
        if (data == null) {
            throw new NullPointerException("Null args list");
        }
        for (Object arg : data) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            ReadableBuilder.append(builder, arg);
        }
    }
}