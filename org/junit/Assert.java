//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit;

import org.junit.internal.*;
import org.hamcrest.*;

public class Assert
{
    protected Assert() {
    }
    
    public static void assertTrue(final String s, final boolean b) {
        if (!b) {
            fail(s);
        }
    }
    
    public static void assertTrue(final boolean b) {
        assertTrue(null, b);
    }
    
    public static void assertFalse(final String s, final boolean b) {
        assertTrue(s, !b);
    }
    
    public static void assertFalse(final boolean b) {
        assertFalse(null, b);
    }
    
    public static void fail(final String s) {
        if (s == null) {
            throw new AssertionError();
        }
        throw new AssertionError((Object)s);
    }
    
    public static void fail() {
        fail(null);
    }
    
    public static void assertEquals(final String s, final Object o, final Object o2) {
        if (o == null && o2 == null) {
            return;
        }
        if (o != null && isEquals(o, o2)) {
            return;
        }
        if (o instanceof String && o2 instanceof String) {
            throw new ComparisonFailure((s == null) ? "" : s, (String)o, (String)o2);
        }
        failNotEquals(s, o, o2);
    }
    
    private static boolean isEquals(final Object o, final Object o2) {
        return o.equals(o2);
    }
    
    public static void assertEquals(final Object o, final Object o2) {
        assertEquals(null, o, o2);
    }
    
    public static void assertArrayEquals(final String s, final Object[] array, final Object[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final Object[] array, final Object[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final byte[] array, final byte[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final byte[] array, final byte[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final char[] array, final char[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final char[] array, final char[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final short[] array, final short[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final short[] array, final short[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final int[] array, final int[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final int[] array, final int[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final long[] array, final long[] array2) throws ArrayComparisonFailure {
        internalArrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final long[] array, final long[] array2) {
        assertArrayEquals(null, array, array2);
    }
    
    public static void assertArrayEquals(final String s, final double[] array, final double[] array2, final double n) throws ArrayComparisonFailure {
        new InexactComparisonCriteria(n).arrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final double[] array, final double[] array2, final double n) {
        assertArrayEquals(null, array, array2, n);
    }
    
    public static void assertArrayEquals(final String s, final float[] array, final float[] array2, final float n) throws ArrayComparisonFailure {
        new InexactComparisonCriteria(n).arrayEquals(s, array, array2);
    }
    
    public static void assertArrayEquals(final float[] array, final float[] array2, final float n) {
        assertArrayEquals(null, array, array2, n);
    }
    
    private static void internalArrayEquals(final String s, final Object o, final Object o2) throws ArrayComparisonFailure {
        new ExactComparisonCriteria().arrayEquals(s, o, o2);
    }
    
    public static void assertEquals(final String s, final double n, final double n2, final double n3) {
        if (Double.compare(n, n2) == 0) {
            return;
        }
        if (Math.abs(n - n2) > n3) {
            failNotEquals(s, new Double(n), new Double(n2));
        }
    }
    
    public static void assertEquals(final long n, final long n2) {
        assertEquals(null, n, n2);
    }
    
    public static void assertEquals(final String s, final long n, final long n2) {
        assertEquals(s, n, (Object)n2);
    }
    
    @Deprecated
    public static void assertEquals(final double n, final double n2) {
        assertEquals(null, n, n2);
    }
    
    @Deprecated
    public static void assertEquals(final String s, final double n, final double n2) {
        fail("Use assertEquals(expected, actual, delta) to compare floating-point numbers");
    }
    
    public static void assertEquals(final double n, final double n2, final double n3) {
        assertEquals(null, n, n2, n3);
    }
    
    public static void assertNotNull(final String s, final Object o) {
        assertTrue(s, o != null);
    }
    
    public static void assertNotNull(final Object o) {
        assertNotNull(null, o);
    }
    
    public static void assertNull(final String s, final Object o) {
        assertTrue(s, o == null);
    }
    
    public static void assertNull(final Object o) {
        assertNull(null, o);
    }
    
    public static void assertSame(final String s, final Object o, final Object o2) {
        if (o == o2) {
            return;
        }
        failNotSame(s, o, o2);
    }
    
    public static void assertSame(final Object o, final Object o2) {
        assertSame(null, o, o2);
    }
    
    public static void assertNotSame(final String s, final Object o, final Object o2) {
        if (o == o2) {
            failSame(s);
        }
    }
    
    public static void assertNotSame(final Object o, final Object o2) {
        assertNotSame(null, o, o2);
    }
    
    private static void failSame(final String s) {
        String string = "";
        if (s != null) {
            string = s + " ";
        }
        fail(string + "expected not same");
    }
    
    private static void failNotSame(final String s, final Object o, final Object o2) {
        String string = "";
        if (s != null) {
            string = s + " ";
        }
        fail(string + "expected same:<" + o + "> was not:<" + o2 + ">");
    }
    
    private static void failNotEquals(final String s, final Object o, final Object o2) {
        fail(format(s, o, o2));
    }
    
    static String format(final String s, final Object o, final Object o2) {
        String string = "";
        if (s != null && !s.equals("")) {
            string = s + " ";
        }
        final String value = String.valueOf(o);
        final String value2 = String.valueOf(o2);
        if (value.equals(value2)) {
            return string + "expected: " + formatClassAndValue(o, value) + " but was: " + formatClassAndValue(o2, value2);
        }
        return string + "expected:<" + value + "> but was:<" + value2 + ">";
    }
    
    private static String formatClassAndValue(final Object o, final String s) {
        return ((o == null) ? "null" : o.getClass().getName()) + "<" + s + ">";
    }
    
    @Deprecated
    public static void assertEquals(final String s, final Object[] array, final Object[] array2) {
        assertArrayEquals(s, array, array2);
    }
    
    @Deprecated
    public static void assertEquals(final Object[] array, final Object[] array2) {
        assertArrayEquals(array, array2);
    }
    
    public static void assertThat(final Object o, final Matcher matcher) {
        assertThat("", o, matcher);
    }
    
    public static void assertThat(final String s, final Object o, final Matcher matcher) {
        if (!matcher.matches(o)) {
            final StringDescription stringDescription = new StringDescription();
            stringDescription.appendText(s);
            stringDescription.appendText("\nExpected: ");
            stringDescription.appendDescriptionOf(matcher);
            stringDescription.appendText("\n     got: ");
            stringDescription.appendValue(o);
            stringDescription.appendText("\n");
            throw new AssertionError((Object)stringDescription.toString());
        }
    }
}
