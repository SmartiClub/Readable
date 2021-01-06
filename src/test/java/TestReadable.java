import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import club.smarti.readable.Readable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ConstantConditions")
public class TestReadable {

    @SuppressWarnings("RedundantCast")
    @Test
    public void testToString_Null() {
        Object obj = null;
        assertEquals("<null>", Readable.toString(obj));

        String str = null;
        assertEquals("<null>", Readable.toString(str));

        int[] intArray = null;
        assertEquals("<null>", Readable.toString((Object) intArray));

        boolean[] boolArray = null;
        assertEquals("<null>", Readable.toString((Object) boolArray));

        byte[] byteArray = null;
        assertEquals("<null>", Readable.toString((Object) byteArray));

        char[] charArray = null;
        assertEquals("<null>", Readable.toString((Object) charArray));

        Object[] objArray = null;
        assertEquals("<null>", Readable.toString((Object) objArray));

        List<Integer> objList = null;
        assertEquals("<null>", Readable.toString(objList));

        List<String> strList = null;
        assertEquals("<null>", Readable.toString(strList));

        ArrayList<?> genArrayList = null;
        assertEquals("<null>", Readable.toString(genArrayList));

        ArrayList<String> strArrayList = null;
        assertEquals("<null>", Readable.toString(strArrayList));

        Map<String, String> strMap = null;
        assertEquals("<null>", Readable.toString(strMap));

        HashMap<Integer, Object> objHashMap = null;
        assertEquals("<null>", Readable.toString(objHashMap));

        Exception error = null;
        assertEquals("<null>", Readable.toString(error));

        Locale locale = null;
        assertEquals("<null>", Readable.toString(locale));
    }

    @Test
    public void testToString_Primitives() {
        assertEquals("false", Readable.toString(false));
        assertEquals("true", Readable.toString(true));

        assertEquals("0x00", Readable.toString((byte) 0));
        assertEquals("0x01", Readable.toString((byte) 1));
        assertEquals("0x12", Readable.toString((byte) 18));
        assertEquals("0xFF", Readable.toString((byte) -1));

        assertEquals("'\\u0020'-' '", Readable.toString(' '));
        assertEquals("'\\u0009'-TAB", Readable.toString('\t'));
        assertEquals("'\\u0031'-'1'", Readable.toString('1'));
        assertEquals("'\\u007A'-'z'", Readable.toString('z'));
        assertEquals("'\\u044F'-'я'", Readable.toString('я'));
        assertEquals("'\\u3041'-'ぁ'", Readable.toString('ぁ'));
        assertEquals("'\\u005C'-'\\'", Readable.toString('\\'));
        assertEquals("'\\u000A'-LF", Readable.toString('\n'));
        assertEquals("'\\u000D'-CR", Readable.toString('\r'));

        assertEquals("0", Readable.toString(0));
        assertEquals("100", Readable.toString(100));
        assertEquals("-200", Readable.toString(-200));
        assertEquals("2147483647", Readable.toString(Integer.MAX_VALUE));

        assertEquals("0", Readable.toString(0L));
        assertEquals("100", Readable.toString(100L));
        assertEquals("-200", Readable.toString(-200L));
        assertEquals("9000000000000000000", Readable.toString(9000000000000000000L));

        assertEquals("0.0", Readable.toString(0f));
        assertEquals("0.5", Readable.toString(0.5f));
        assertEquals("100.0", Readable.toString(100f));
        assertEquals("-1.2", Readable.toString(-1.2f));
        assertEquals("-200.0", Readable.toString(-200f));
        assertEquals("0.3333333", Readable.toString(0.3333333f));

        assertEquals("0.0", Readable.toString(0.0));
        assertEquals("0.5", Readable.toString(0.5));
        assertEquals("100.0", Readable.toString(100.0));
        assertEquals("-1.2", Readable.toString(-1.2));
        assertEquals("-200.0", Readable.toString(-200.0));
        assertEquals("0.3333333333333333", Readable.toString(0.3333333333333333));
    }

    @Test
    public void testToString_Classes() {
        assertEquals("boolean", Readable.toString(boolean.class));
        assertEquals("byte", Readable.toString(byte.class));
        assertEquals("char", Readable.toString(char.class));
        assertEquals("short", Readable.toString(short.class));
        assertEquals("int", Readable.toString(int.class));
        assertEquals("long", Readable.toString(long.class));
        assertEquals("float", Readable.toString(float.class));
        assertEquals("double", Readable.toString(double.class));

        assertEquals("Boolean", Readable.toString(Boolean.class));
        assertEquals("Byte", Readable.toString(Byte.class));
        assertEquals("Character", Readable.toString(Character.class));
        assertEquals("Short", Readable.toString(Short.class));
        assertEquals("Integer", Readable.toString(Integer.class));
        assertEquals("Long", Readable.toString(Long.class));
        assertEquals("Float", Readable.toString(Float.class));
        assertEquals("Double", Readable.toString(Double.class));

        assertEquals("Object", Readable.toString(Object.class));
        assertEquals("String", Readable.toString(String.class));

        assertEquals("boolean[]", Readable.toString(boolean[].class));
        assertEquals("byte[]", Readable.toString(byte[].class));
        assertEquals("char[]", Readable.toString(char[].class));
        assertEquals("short[]", Readable.toString(short[].class));
        assertEquals("int[]", Readable.toString(int[].class));
        assertEquals("long[]", Readable.toString(long[].class));
        assertEquals("float[]", Readable.toString(float[].class));
        assertEquals("double[]", Readable.toString(double[].class));

        assertEquals("int[][]", Readable.toString(int[][].class));
        assertEquals("double[][]", Readable.toString(double[][].class));

        assertEquals("Boolean[]", Readable.toString(Boolean[].class));
        assertEquals("Byte[]", Readable.toString(Byte[].class));
        assertEquals("Character[]", Readable.toString(Character[].class));
        assertEquals("Short[]", Readable.toString(Short[].class));
        assertEquals("Integer[]", Readable.toString(Integer[].class));
        assertEquals("Long[]", Readable.toString(Long[].class));
        assertEquals("Float[]", Readable.toString(Float[].class));
        assertEquals("Double[]", Readable.toString(Double[].class));

        assertEquals("Object[]", Readable.toString(Object[].class));
        assertEquals("String[]", Readable.toString(String[].class));

        assertEquals("List", Readable.toString(List.class));
        assertEquals("ArrayList", Readable.toString(ArrayList.class));
        assertEquals("Map", Readable.toString(Map.class));
        assertEquals("HashMap", Readable.toString(HashMap.class));

        assertEquals("TestReadable$1", Readable.toString(new Cloneable() {
        }.getClass()));
        assertEquals("TestReadable$2", Readable.toString(new Cloneable() {
        }.getClass()));
    }

    @Test
    public void testToString_Strings() {
        assertEquals("\"\"", Readable.toString(""));
        assertEquals("\"English\"", Readable.toString("English"));
        assertEquals("\"中國\"", Readable.toString("中國"));
        assertEquals("\"Русский\"", Readable.toString("Русский"));
        assertEquals("\"हिन्दी\"", Readable.toString("हिन्दी"));
        assertEquals("\"العربية\"", Readable.toString("العربية"));

        assertEquals("\"$€£¥₽\"", Readable.toString("$€£¥₽"));
        assertEquals("\"*&\\^`'\"@#(-+)=~%_[.,:;]{?}</>!\"", Readable.toString("*&\\^`'\"@#(-+)=~%_[.,:;]{?}</>!"));

        assertEquals(
                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"",
                Readable.toString(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testToString_Arrays() {
        boolean[] boolArray = new boolean[]{};
        assertEquals("[]", Readable.toString((Object) boolArray));
        boolArray = new boolean[]{true, false};
        assertEquals("[true, false]", Readable.toString((Object) boolArray));

        byte[] byteArray = new byte[]{};
        assertEquals("[]", Readable.toString((Object) byteArray));
        byteArray = new byte[]{0, 1, -1};
        assertEquals("[0x00, 0x01, 0xFF]", Readable.toString((Object) byteArray));

        char[] charArray = new char[]{};
        assertEquals("[]", Readable.toString((Object) charArray));
        charArray = new char[]{' ', 'a', 'A'};
        assertEquals("['\\u0020'-' ', '\\u0061'-'a', '\\u0041'-'A']", Readable.toString((Object) charArray));

        int[] intArray = new int[]{};
        assertEquals("[]", Readable.toString((Object) intArray));
        intArray = new int[]{0};
        assertEquals("[0]", Readable.toString((Object) intArray));
        intArray = new int[]{0, 1, -2, 3, -4};
        assertEquals("[0, 1, -2, 3, -4]", Readable.toString((Object) intArray));

        long[] longArray = new long[]{};
        assertEquals("[]", Readable.toString((Object) longArray));
        longArray = new long[]{0};
        assertEquals("[0]", Readable.toString((Object) longArray));
        longArray = new long[]{0, 1L, -2L, 3L, -4L};
        assertEquals("[0, 1, -2, 3, -4]", Readable.toString((Object) longArray));

        float[] floatArray = new float[]{};
        assertEquals("[]", Readable.toString((Object) floatArray));
        floatArray = new float[]{0, 1f, -2f, 3f, -4f};
        assertEquals("[0.0, 1.0, -2.0, 3.0, -4.0]", Readable.toString((Object) floatArray));

        double[] doubleArray = new double[]{};
        assertEquals("[]", Readable.toString((Object) doubleArray));
        doubleArray = new double[]{0.0, 1.0, -2.0};
        assertEquals("[0.0, 1.0, -2.0]", Readable.toString((Object) doubleArray));
        doubleArray = new double[]{9.999999999999};
        assertEquals("[9.999999999999]", Readable.toString((Object) doubleArray));

        Object[] objArray = new String[]{};
        assertEquals("[]", Readable.toString((Object) objArray));
        Object[] objArray2 = new Object[]{new Object(), new Object()};
        assertDoesNotThrow(() -> Readable.toString((Object) objArray2));

        String[] strArray = new String[]{};
        assertEquals("[]", Readable.toString((Object) strArray));
        strArray = new String[]{"A1", "B2", "C3"};
        assertEquals("[\"A1\", \"B2\", \"C3\"]", Readable.toString((Object) strArray));
    }

    @Test
    public void testToString_Lists() {
        List<Integer> list = new ArrayList<>();
        assertEquals("[]", Readable.toString(list));
        list.add(0);
        assertEquals("[0]", Readable.toString(list));
        list.add(10);
        assertEquals("[0, 10]", Readable.toString(list));

        ArrayList<String> arrayList = new ArrayList<>();
        assertEquals("[]", Readable.toString(arrayList));
        arrayList.add("");
        assertEquals("[\"\"]", Readable.toString(arrayList));
        arrayList.add("AAA");
        assertEquals("[\"\", \"AAA\"]", Readable.toString(arrayList));

        ArrayList<List<?>> listList = new ArrayList<>();
        listList.add(Arrays.asList(0, 1, 2));
        listList.add(Arrays.asList(0f, 10f, 20f));
        assertEquals("[[0, 1, 2], [0.0, 10.0, 20.0]]", Readable.toString(listList));
    }

    @Test
    public void testToString_Maps() {
        // Integer key:
        Map<Integer, String> mapIntStr = new HashMap<>();
        assertEquals("{}", Readable.toString(mapIntStr));

        mapIntStr.put(0, "A");
        assertEquals("{0–\"A\"}", Readable.toString(mapIntStr));

        mapIntStr.put(1, "B");
        assertEquals("{0–\"A\", 1–\"B\"}", Readable.toString(mapIntStr));

        // String key:
        HashMap<String, String> hashmapStrStr = new HashMap<>();
        assertEquals("{}", Readable.toString(hashmapStrStr));

        hashmapStrStr.put(null, null);
        assertEquals("{<null>–<null>}", Readable.toString(hashmapStrStr));

        hashmapStrStr.clear();
        hashmapStrStr.put("", "");
        assertEquals("{\"\"–\"\"}", Readable.toString(hashmapStrStr));

        hashmapStrStr.clear();
        hashmapStrStr.put("first", "S1");
        hashmapStrStr.put("second", "S2");
        assertEquals("{\"first\"–\"S1\", \"second\"–\"S2\"}", Readable.toString(hashmapStrStr));
    }

    @Test
    public void testToString_Files() {
        File file = new File("src/test/resources/EmptyFile.txt");
        assertTrue(Readable.toString(file).contains("name=EmptyFile.txt, type=file, rwx, len=0, path="));
        file = new File("src/test/resources/TextFile.txt");
        assertTrue(Readable.toString(file).contains("name=TextFile.txt, type=file, rwx, len=10, path="));

        File dir = new File("src/test/resources/ContentDir");
        assertTrue(Readable.toString(dir).contains("name=ContentDir, type=dir, rwx"));
    }

    @Test
    public void testToString_Throwable() {
        assertDoesNotThrow(() -> Readable.toString(new Exception("Error message")));
    }

    @Test
    public void testToString_StackTrace() {
        NullPointerException exception = new NullPointerException("Error message");
        StackTraceElement[] trace = exception.getStackTrace();
        for (StackTraceElement item : trace) {
            assertDoesNotThrow(() -> Readable.toString(item));
        }
    }

    @Test
    public void testToString_Locales() {
        Locale locale = Locale.US;
        assertEquals("en-US", Readable.toString(locale));
        locale = new Locale("ru", "RU");
        assertEquals("ru-RU", Readable.toString(locale));
    }
}