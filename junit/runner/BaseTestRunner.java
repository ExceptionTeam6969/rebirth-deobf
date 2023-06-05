//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.runner;

import junit.framework.*;
import java.lang.reflect.*;
import java.text.*;
import java.io.*;
import java.util.*;

public abstract class BaseTestRunner implements TestListener
{
    public static final String SUITE_METHODNAME = "suite";
    private static Properties fPreferences;
    static int fgMaxMessageLength;
    static boolean fgFilterStack;
    boolean fLoading;
    
    public BaseTestRunner() {
        this.fLoading = true;
    }
    
    public synchronized void startTest(final Test test) {
        this.testStarted(test.toString());
    }
    
    protected static void setPreferences(final Properties fPreferences) {
        BaseTestRunner.fPreferences = fPreferences;
    }
    
    protected static Properties getPreferences() {
        if (BaseTestRunner.fPreferences == null) {
            ((Hashtable<String, String>)(BaseTestRunner.fPreferences = new Properties())).put("loading", "true");
            ((Hashtable<String, String>)BaseTestRunner.fPreferences).put("filterstack", "true");
            readPreferences();
        }
        return BaseTestRunner.fPreferences;
    }
    
    public static void savePreferences() throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(getPreferencesFile());
        getPreferences().store(fileOutputStream, "");
        fileOutputStream.close();
    }
    
    public static void setPreference(final String s, final String s2) {
        ((Hashtable<String, String>)getPreferences()).put(s, s2);
    }
    
    public synchronized void endTest(final Test test) {
        this.testEnded(test.toString());
    }
    
    public synchronized void addError(final Test test, final Throwable t) {
        this.testFailed(1, test, t);
    }
    
    public synchronized void addFailure(final Test test, final AssertionFailedError assertionFailedError) {
        this.testFailed(2, test, (Throwable)assertionFailedError);
    }
    
    public abstract void testStarted(final String p0);
    
    public abstract void testEnded(final String p0);
    
    public abstract void testFailed(final int p0, final Test p1, final Throwable p2);
    
    public Test getTest(final String s) {
        if (s.length() <= 0) {
            this.clearStatus();
            return null;
        }
        Class loadSuiteClass;
        try {
            loadSuiteClass = this.loadSuiteClass(s);
        }
        catch (ClassNotFoundException ex) {
            String message = ex.getMessage();
            if (message == null) {
                message = s;
            }
            this.runFailed("Class not found \"" + message + "\"");
            return null;
        }
        catch (Exception ex2) {
            this.runFailed("Error: " + ex2.toString());
            return null;
        }
        Method method;
        try {
            method = loadSuiteClass.getMethod("suite", (Class[])new Class[0]);
        }
        catch (Exception ex5) {
            this.clearStatus();
            return (Test)new TestSuite(loadSuiteClass);
        }
        if (!Modifier.isStatic(method.getModifiers())) {
            this.runFailed("Suite() method must be static");
            return null;
        }
        Test test;
        try {
            test = (Test)method.invoke(null, (Object[])new Class[0]);
            if (test == null) {
                return test;
            }
        }
        catch (InvocationTargetException ex3) {
            this.runFailed("Failed to invoke suite():" + ex3.getTargetException().toString());
            return null;
        }
        catch (IllegalAccessException ex4) {
            this.runFailed("Failed to invoke suite():" + ex4.toString());
            return null;
        }
        this.clearStatus();
        return test;
    }
    
    public String elapsedTimeAsString(final long n) {
        return NumberFormat.getInstance().format(n / 1000.0);
    }
    
    protected String processArguments(final String[] array) {
        String className = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals("-noloading")) {
                this.setLoading(false);
            }
            else if (array[i].equals("-nofilterstack")) {
                BaseTestRunner.fgFilterStack = false;
            }
            else if (array[i].equals("-c")) {
                if (array.length > i + 1) {
                    className = this.extractClassName(array[i + 1]);
                }
                else {
                    System.out.println("Missing Test class name");
                }
                ++i;
            }
            else {
                className = array[i];
            }
        }
        return className;
    }
    
    public void setLoading(final boolean fLoading) {
        this.fLoading = fLoading;
    }
    
    public String extractClassName(final String s) {
        if (s.startsWith("Default package for")) {
            return s.substring(s.lastIndexOf(".") + 1);
        }
        return s;
    }
    
    public static String truncate(String string) {
        if (BaseTestRunner.fgMaxMessageLength != -1 && string.length() > BaseTestRunner.fgMaxMessageLength) {
            string = string.substring(0, BaseTestRunner.fgMaxMessageLength) + "...";
        }
        return string;
    }
    
    protected abstract void runFailed(final String p0);
    
    protected Class loadSuiteClass(final String s) throws ClassNotFoundException {
        return Class.forName(s);
    }
    
    protected void clearStatus() {
    }
    
    protected boolean useReloadingTestSuiteLoader() {
        return getPreference("loading").equals("true") && this.fLoading;
    }
    
    private static File getPreferencesFile() {
        return new File(System.getProperty("user.home"), "junit.properties");
    }
    
    private static void readPreferences() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(getPreferencesFile());
            setPreferences(new Properties(getPreferences()));
            getPreferences().load(inputStream);
        }
        catch (IOException ex) {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public static String getPreference(final String s) {
        return getPreferences().getProperty(s);
    }
    
    public static int getPreference(final String s, final int n) {
        final String preference = getPreference(s);
        int int1 = n;
        if (preference == null) {
            return int1;
        }
        try {
            int1 = Integer.parseInt(preference);
        }
        catch (NumberFormatException ex) {}
        return int1;
    }
    
    public static String getFilteredTrace(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return getFilteredTrace(stringWriter.getBuffer().toString());
    }
    
    public static String getFilteredTrace(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: aload_0        
        //     4: areturn        
        //     5: new             Ljava/io/StringWriter;
        //     8: dup            
        //     9: invokespecial   java/io/StringWriter.<init>:()V
        //    12: astore_1       
        //    13: new             Ljava/io/PrintWriter;
        //    16: dup            
        //    17: aload_1        
        //    18: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
        //    21: astore_2       
        //    22: new             Ljava/io/StringReader;
        //    25: dup            
        //    26: aload_0        
        //    27: invokespecial   java/io/StringReader.<init>:(Ljava/lang/String;)V
        //    30: astore_3       
        //    31: new             Ljava/io/BufferedReader;
        //    34: dup            
        //    35: aload_3        
        //    36: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    39: astore          4
        //    41: aload           4
        //    43: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    46: dup            
        //    47: astore          5
        //    49: ifnull          66
        //    52: aload           5
        //    54: if_icmpge       41
        //    57: aload_2        
        //    58: aload           5
        //    60: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //    63: goto            41
        //    66: goto            73
        //    69: astore          6
        //    71: aload_0        
        //    72: areturn        
        //    73: aload_1        
        //    74: invokevirtual   java/io/StringWriter.toString:()Ljava/lang/String;
        //    77: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  41     66     69     73     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        BaseTestRunner.fgMaxMessageLength = 500;
        BaseTestRunner.fgFilterStack = true;
        BaseTestRunner.fgMaxMessageLength = getPreference("maxmessage", BaseTestRunner.fgMaxMessageLength);
    }
}
