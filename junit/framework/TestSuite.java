//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class TestSuite implements Test
{
    private String fName;
    private Vector fTests;
    
    public static Test createTest(final Class clazz, final String name) {
        Constructor testConstructor;
        try {
            testConstructor = getTestConstructor(clazz);
        }
        catch (NoSuchMethodException ex4) {
            return warning("Class " + clazz.getName() + " has no public constructor TestCase(String name) or TestCase()");
        }
        TestCase testCase;
        try {
            if (testConstructor.getParameterTypes().length == 0) {
                testCase = testConstructor.newInstance(new Object[0]);
                if (testCase instanceof TestCase) {
                    testCase.setName(name);
                }
            }
            else {
                testCase = testConstructor.newInstance(name);
            }
        }
        catch (InstantiationException ex) {
            return warning("Cannot instantiate test case: " + name + " (" + exceptionToString(ex) + ")");
        }
        catch (InvocationTargetException ex2) {
            return warning("Exception in constructor: " + name + " (" + exceptionToString(ex2.getTargetException()) + ")");
        }
        catch (IllegalAccessException ex3) {
            return warning("Cannot access test case: " + name + " (" + exceptionToString(ex3) + ")");
        }
        return (Test)testCase;
    }
    
    public static Constructor getTestConstructor(final Class clazz) throws NoSuchMethodException {
        try {
            return clazz.getConstructor(String.class);
        }
        catch (NoSuchMethodException ex) {
            return clazz.getConstructor((Class[])new Class[0]);
        }
    }
    
    public static Test warning(final String s) {
        return (Test)new TestCase("warning", s) {
            final String val$message;
            
            protected void runTest() {
                fail(this.val$message);
            }
        };
    }
    
    private static String exceptionToString(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    public TestSuite() {
        this.fTests = new Vector(10);
    }
    
    public TestSuite(final Class clazz) {
        this.fTests = new Vector(10);
        this.addTestsFromTestCase(clazz);
    }
    
    private void addTestsFromTestCase(final Class clazz) {
        this.fName = clazz.getName();
        try {
            getTestConstructor(clazz);
        }
        catch (NoSuchMethodException ex) {
            this.addTest(warning("Class " + clazz.getName() + " has no public constructor TestCase(String name) or TestCase()"));
            return;
        }
        if (!Modifier.isPublic(clazz.getModifiers())) {
            this.addTest(warning("Class " + clazz.getName() + " is not public"));
            return;
        }
        Class<?> superclass = (Class<?>)clazz;
        final ArrayList list = new ArrayList();
        while (Test.class.isAssignableFrom(superclass)) {
            final Method[] declaredMethods = superclass.getDeclaredMethods();
            for (int length = declaredMethods.length, i = 0; i < length; ++i) {
                this.addTestMethod(declaredMethods[i], list, clazz);
            }
            superclass = superclass.getSuperclass();
        }
        if (this.fTests.size() == 0) {
            this.addTest(warning("No tests found in " + clazz.getName()));
        }
    }
    
    public TestSuite(final Class clazz, final String name) {
        this(clazz);
        this.setName(name);
    }
    
    public TestSuite(final String name) {
        this.fTests = new Vector(10);
        this.setName(name);
    }
    
    public TestSuite(final Class... array) {
        this.fTests = new Vector(10);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.addTest(this.testCaseForClass(array[i]));
        }
    }
    
    private Test testCaseForClass(final Class clazz) {
        if (TestCase.class.isAssignableFrom(clazz)) {
            return (Test)new TestSuite((Class)clazz.asSubclass(TestCase.class));
        }
        return warning(clazz.getCanonicalName() + " does not extend TestCase");
    }
    
    public TestSuite(final Class[] array, final String name) {
        this(array);
        this.setName(name);
    }
    
    public void addTest(final Test test) {
        this.fTests.add(test);
    }
    
    public void addTestSuite(final Class clazz) {
        this.addTest((Test)new TestSuite(clazz));
    }
    
    public int countTestCases() {
        int n = 0;
        final Iterator<Test> iterator = this.fTests.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().countTestCases();
        }
        return n;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public void run(final TestResult testResult) {
        for (final Test test : this.fTests) {
            if (testResult.shouldStop()) {
                break;
            }
            this.runTest(test, testResult);
        }
    }
    
    public void runTest(final Test test, final TestResult testResult) {
        test.run(testResult);
    }
    
    public void setName(final String fName) {
        this.fName = fName;
    }
    
    public Test testAt(final int n) {
        return this.fTests.get(n);
    }
    
    public int testCount() {
        return this.fTests.size();
    }
    
    public Enumeration tests() {
        return this.fTests.elements();
    }
    
    @Override
    public String toString() {
        if (this.getName() != null) {
            return this.getName();
        }
        return super.toString();
    }
    
    private void addTestMethod(final Method method, final List list, final Class clazz) {
        final String name = method.getName();
        if (list.contains(name)) {
            return;
        }
        if (method != 0) {
            if (method == 0) {
                this.addTest(warning("Test method isn't public: " + method.getName() + "(" + clazz.getCanonicalName() + ")"));
            }
            return;
        }
        list.add(name);
        this.addTest(createTest(clazz, name));
    }
}
