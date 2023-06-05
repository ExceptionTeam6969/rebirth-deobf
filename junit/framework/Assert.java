//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

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
            throw new AssertionFailedError();
        }
        throw new AssertionFailedError(s);
    }
    
    public static void fail() {
        fail(null);
    }
    
    public static void assertEquals(final String s, final Object o, final Object o2) {
        if (o == null && o2 == null) {
            return;
        }
        if (o != null && o.equals(o2)) {
            return;
        }
        failNotEquals(s, o, o2);
    }
    
    public static void assertEquals(final Object o, final Object o2) {
        assertEquals(null, o, o2);
    }
    
    public static void assertEquals(final String s, final String s2, final String s3) {
        if (s2 == null && s3 == null) {
            return;
        }
        if (s2 != null && s2.equals(s3)) {
            return;
        }
        throw new ComparisonFailure((s == null) ? "" : s, s2, s3);
    }
    
    public static void assertEquals(final String s, final String s2) {
        assertEquals(null, s, s2);
    }
    
    public static void assertEquals(final String s, final double n, final double n2, final double n3) {
        if (Double.compare(n, n2) == 0) {
            return;
        }
        if (Math.abs(n - n2) > n3) {
            failNotEquals(s, new Double(n), new Double(n2));
        }
    }
    
    public static void assertEquals(final double n, final double n2, final double n3) {
        assertEquals(null, n, n2, n3);
    }
    
    public static void assertEquals(final String s, final float n, final float n2, final float n3) {
        if (Float.compare(n, n2) == 0) {
            return;
        }
        if (Math.abs(n - n2) > n3) {
            failNotEquals(s, new Float(n), new Float(n2));
        }
    }
    
    public static void assertEquals(final float n, final float n2, final float n3) {
        assertEquals(null, n, n2, n3);
    }
    
    public static void assertEquals(final String s, final long n, final long n2) {
        assertEquals(s, new Long(n), new Long(n2));
    }
    
    public static void assertEquals(final long n, final long n2) {
        assertEquals(null, n, n2);
    }
    
    public static void assertEquals(final String s, final boolean b, final boolean b2) {
        assertEquals(s, b, (Object)b2);
    }
    
    public static void assertEquals(final boolean b, final boolean b2) {
        assertEquals(null, b, b2);
    }
    
    public static void assertEquals(final String s, final byte b, final byte b2) {
        assertEquals(s, new Byte(b), new Byte(b2));
    }
    
    public static void assertEquals(final byte b, final byte b2) {
        assertEquals(null, b, b2);
    }
    
    public static void assertEquals(final String s, final char c, final char c2) {
        assertEquals(s, new Character(c), new Character(c2));
    }
    
    public static void assertEquals(final char c, final char c2) {
        assertEquals(null, c, c2);
    }
    
    public static void assertEquals(final String s, final short n, final short n2) {
        assertEquals(s, new Short(n), new Short(n2));
    }
    
    public static void assertEquals(final short n, final short n2) {
        assertEquals(null, n, n2);
    }
    
    public static void assertEquals(final String s, final int n, final int n2) {
        assertEquals(s, new Integer(n), new Integer(n2));
    }
    
    public static void assertEquals(final int n, final int n2) {
        assertEquals(null, n, n2);
    }
    
    public static void assertNotNull(final Object o) {
        assertNotNull(null, o);
    }
    
    public static void assertNotNull(final String s, final Object o) {
        assertTrue(s, o != null);
    }
    
    public static void assertNull(final Object o) {
        assertNull("Expected: <null> but was: " + String.valueOf(o), o);
    }
    
    public static void assertNull(final String s, final Object o) {
        assertTrue(s, o == null);
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
    
    public static void failSame(final String s) {
        String string = "";
        if (s != null) {
            string = s + " ";
        }
        fail(string + "expected not same");
    }
    
    public static void failNotSame(final String s, final Object o, final Object o2) {
        String string = "";
        if (s != null) {
            string = s + " ";
        }
        fail(string + "expected same:<" + o + "> was not:<" + o2 + ">");
    }
    
    public static void failNotEquals(final String s, final Object o, final Object o2) {
        fail(format(s, o, o2));
    }
    
    public static String format(final String s, final Object o, final Object o2) {
        String string = "";
        if (s != null && s.length() > 0) {
            string = s + " ";
        }
        return string + "expected:<" + o + "> but was:<" + o2 + ">";
    }
}
