package club.smarti.readable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import club.smarti.java.Classes;

/**
 * Inner parser. It converts different type of objects to human readable string
 * *
 * Code convention:
 * "–––"  string
 * [–––]  array, list, set
 * {–––}  map
 * <–––> nulls and other objects
 */
@SuppressWarnings({"WeakerAccess", "DuplicatedCode", "EnhancedSwitchMigration"})
final class ReadableBuilder {

    private final static char[] HEX = "0123456789ABCDEF".toCharArray();

    @Contract(pure = true)
    private ReadableBuilder() {
    }

    @Contract(pure = true, value = "null, _ -> fail")
    static void append(StringBuilder builder, Object data) {
        try {
            if (data != null) {
                Class<?> cls = data.getClass();

                if (cls == Class.class) {
                    appendClass((Class<?>) data, builder);
                }
                else if (Classes.isAssignable(Boolean.class, cls)) {
                    appendBoolean((Boolean) data, builder);
                }
                else if (Classes.isAssignable(Byte.class, cls)) {
                    appendByte((Byte) data, builder);
                }
                else if (Classes.isAssignable(Character.class, cls)) {
                    appendChar((Character) data, builder);
                }
                else if (Classes.isAssignable(Integer.class, cls)) {
                    appendInteger((Integer) data, builder);
                }
                else if (Classes.isAssignable(Long.class, cls)) {
                    appendLong((Long) data, builder);
                }
                else if (Classes.isAssignable(Float.class, cls)) {
                    appendFloat((Float) data, builder);
                }
                else if (Classes.isAssignable(Double.class, cls)) {
                    appendDouble((Double) data, builder);
                }
                else if (Classes.isAssignable(String.class, cls)) {
                    appendString((String) data, builder);
                }
                else if (Classes.isAssignable(boolean[].class, cls)) {
                    appendBooleanArray((boolean[]) data, builder);
                }
                else if (Classes.isAssignable(byte[].class, cls)) {
                    appendByteArray((byte[]) data, builder);
                }
                else if (Classes.isAssignable(char[].class, cls)) {
                    appendCharArray((char[]) data, builder);
                }
                else if (Classes.isAssignable(int[].class, cls)) {
                    appendIntegerArray((int[]) data, builder);
                }
                else if (Classes.isAssignable(long[].class, cls)) {
                    appendLongArray((long[]) data, builder);
                }
                else if (Classes.isAssignable(float[].class, cls)) {
                    appendFloatArray((float[]) data, builder);
                }
                else if (Classes.isAssignable(double[].class, cls)) {
                    appendDoubleArray((double[]) data, builder);
                }
                else if (Classes.isAssignable(Object[].class, cls)) {
                    appendObjectArray((Object[]) data, builder);
                }
                else if (Classes.isAssignable(Iterable.class, cls)) {
                    appendIterable((Iterable<?>) data, builder);
                }
                else if (Classes.isAssignable(Map.class, cls)) {
                    appendMap((Map<?, ?>) data, builder);
                }
                else if (Classes.isAssignable(File.class, cls)) {
                    appendFile((File) data, builder);
                }
                else if (Classes.isAssignable(Throwable.class, cls)) {
                    appendThrowable((Throwable) data, builder);
                }
                else if (Classes.isAssignable(StackTraceElement[].class, cls)) {
                    appendStackTrace((StackTraceElement[]) data, builder);
                }
                else if (Classes.isAssignable(Locale.class, cls)) {
                    appendLocale((Locale) data, builder);
                }
                else {
                    appendObject(data, builder);
                }
            }
            else {
                builder.append("<null>");
            }
        }
        catch (Throwable error) {
            builder.append("<error: ");
            builder.append(error.getMessage());
            builder.append(">");
        }
    }

    /**
     * Base converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendObject(@NotNull Object data, StringBuilder builder) {
        builder.append("<");
        builder.append(data);
        builder.append(">");
    }

    /**
     * Class name converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendClass(@NotNull Class<?> data, StringBuilder builder) {
        builder.append(Classes.getSimpleName(data));
    }

    /**
     * Boolean converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendBoolean(@NotNull Boolean data, StringBuilder builder) {
        builder.append(data.toString());
    }

    /**
     * Byte converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendByte(@NotNull Byte data, StringBuilder builder) {
        int num = data & 0xFF;
        builder.append("0x");
        builder.append(HEX[num >>> 4]);
        builder.append(HEX[num & 0x0F]);
    }

    /**
     * Char converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendChar(@NotNull Character data, StringBuilder builder) {
        int num = data;
        switch (data) {
            case '\b':
                builder.append("'\\u0008'-BS");
                break;
            case '\t':
                builder.append("'\\u0009'-TAB");
                break;
            case '\n':
                builder.append("'\\u000A'-LF");
                break;
            case '\r':
                builder.append("'\\u000D'-CR");
                break;
            case '\f':
                builder.append("'\\u000C'-FF");
                break;
            case '\u00A0':
                builder.append("'\\u00A0'-NBSP");
                break;
            default:
                builder.append("'\\u");
                builder.append(HEX[num >> 12]);
                builder.append(HEX[(num & 0x0F00) >> 8]);
                builder.append(HEX[(num & 0x00F0) >> 4]);
                builder.append(HEX[num & 0x000F]);
                builder.append("'-'").append(data).append("'");
        }
    }

    /**
     * Integer converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendInteger(@NotNull Integer data, StringBuilder builder) {
        builder.append(data.toString());
    }

    /**
     * Long converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendLong(@NotNull Long data, StringBuilder builder) {
        builder.append(data.toString());
    }

    /**
     * Float converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendFloat(@NotNull Float data, StringBuilder builder) {
        builder.append(data.toString());
    }

    /**
     * Double converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendDouble(@NotNull Double data, StringBuilder builder) {
        builder.append(data.toString());
    }

    /**
     * String converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendString(@NotNull String str, StringBuilder builder) {
        builder.append("\"");
        builder.append(str);
        builder.append("\"");
    }

    /**
     * Primitive boolean array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendBooleanArray(boolean[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            builder.append(array[n]);
        }
        builder.append("]");
    }

    /**
     * Primitive byte array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendByteArray(byte[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            appendByte(array[n], builder);
        }
        builder.append("]");
    }

    /**
     * Primitive char array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendCharArray(char[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            appendChar(array[n], builder);
        }
        builder.append("]");
    }

    /**
     * Primitive int array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendIntegerArray(int[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            builder.append(array[n]);
        }
        builder.append("]");
    }

    /**
     * Primitive long array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendLongArray(long[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            builder.append(array[n]);
        }
        builder.append("]");
    }

    /**
     * Primitive float array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendFloatArray(float[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            builder.append(array[n]);
        }
        builder.append("]");
    }

    /**
     * Primitive double array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendDoubleArray(double[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            builder.append(array[n]);
        }
        builder.append("]");
    }

    /**
     * Objects array converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendObjectArray(@NotNull Object[] array, StringBuilder builder) {
        builder.append("[");

        for (int n = 0; n < array.length; n++) {
            if (n > 0) {
                builder.append(", ");
            }
            append(builder, array[n]);
        }
        builder.append("]");
    }

    /**
     * Iterable type converter (List, Set, ...)
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static <T> void appendIterable(@NotNull final Iterable<T> iterable, StringBuilder builder) {
        builder.append("[");

        boolean empty = true;
        for (T item : iterable) {
            if (!empty) {
                builder.append(", ");
            }
            append(builder, item);
            empty = false;
        }
        builder.append("]");
    }

    /**
     * Map converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static <K, V> void appendMap(@NotNull Map<K, V> map, StringBuilder builder) {
        builder.append("{");

        boolean empty = true;
        Set<K> keys = map.keySet();
        for (K key : keys) {
            if (!empty) {
                builder.append(", ");
            }
            append(builder, key);
            builder.append("–");

            V value = map.get(key);
            append(builder, value);
            empty = false;
        }
        builder.append("}");
    }

    /**
     * File/directory description
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendFile(@NotNull File file, StringBuilder builder) {
        boolean dir = file.isDirectory();

        builder.append('<').append(Classes.getSignature(file));
        builder.append(": name=").append(file.getName());
        builder.append(", type=").append(dir ? "dir" : "file");
        builder.append(", ").append(file.canRead() ? "r" : "-").append(file.canWrite() ? "w" : "-").append(file.canExecute() ? "x" : "-");
        if (!dir) {
            builder.append(", len=").append(file.length());
        }
        builder.append(", path=\"").append(file.getAbsolutePath()).append("\">");
    }

    /**
     * Exception converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendThrowable(@NotNull Throwable error, StringBuilder builder) {
        builder.append(Classes.getSimpleName(error));
        builder.append(": ");
        builder.append(error.getMessage());

        StackTraceElement[] stack = error.getStackTrace();
        appendStackTrace(stack, builder);
    }

    /**
     * Exception stack trace converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendStackTrace(StackTraceElement[] stack, StringBuilder builder) {
        for (StackTraceElement foo : stack) {
            if (foo != null) {
                String className = foo.getClassName();
                String classNameSimple = className.substring(className.lastIndexOf(".") + 1);

                builder.append("\r\n").append(className);
                builder.append('.').append(foo.getMethodName());
                builder.append(" (").append(classNameSimple);
                builder.append(':').append(foo.getLineNumber()).append(')');
            }
        }
    }

    /**
     * Locale converter
     */
    @Contract(pure = true, value = "_, null -> fail")
    private static void appendLocale(@NotNull Locale locale, StringBuilder builder) {
        builder.append(locale.getLanguage());
        builder.append("-");
        builder.append(locale.getCountry());
    }
}