//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner;

import java.io.*;
import java.lang.annotation.*;
import java.util.*;
import java.util.regex.*;

public class Description implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final Description EMPTY;
    public static final Description TEST_MECHANISM;
    private final ArrayList fChildren;
    private final String fDisplayName;
    private final Annotation[] fAnnotations;
    
    public static Description createSuiteDescription(final String s, final Annotation... array) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("name must have non-zero length");
        }
        return new Description(s, array);
    }
    
    public static Description createTestDescription(final Class clazz, final String s, final Annotation... array) {
        return new Description(String.format("%s(%s)", s, clazz.getName()), array);
    }
    
    public static Description createTestDescription(final Class clazz, final String s) {
        return createTestDescription(clazz, s, new Annotation[0]);
    }
    
    public static Description createSuiteDescription(final Class clazz) {
        return new Description(clazz.getName(), clazz.getAnnotations());
    }
    
    private Description(final String fDisplayName, final Annotation... fAnnotations) {
        this.fChildren = new ArrayList();
        this.fDisplayName = fDisplayName;
        this.fAnnotations = fAnnotations;
    }
    
    public String getDisplayName() {
        return this.fDisplayName;
    }
    
    public void addChild(final Description description) {
        this.getChildren().add(description);
    }
    
    public ArrayList getChildren() {
        return this.fChildren;
    }
    
    public boolean isSuite() {
        return !this.isTest();
    }
    
    public boolean isTest() {
        return this.getChildren().isEmpty();
    }
    
    public int testCount() {
        if (this.isTest()) {
            return 1;
        }
        int n = 0;
        final Iterator<Description> iterator = this.getChildren().iterator();
        while (iterator.hasNext()) {
            n += iterator.next().testCount();
        }
        return n;
    }
    
    @Override
    public int hashCode() {
        return this.getDisplayName().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Description && this.getDisplayName().equals(((Description)o).getDisplayName());
    }
    
    @Override
    public String toString() {
        return this.getDisplayName();
    }
    
    public boolean isEmpty() {
        return this.equals(Description.EMPTY);
    }
    
    public Description childlessCopy() {
        return new Description(this.fDisplayName, this.fAnnotations);
    }
    
    public Annotation getAnnotation(final Class clazz) {
        for (final Annotation annotation : this.fAnnotations) {
            if (annotation.annotationType().equals(clazz)) {
                return clazz.cast(annotation);
            }
        }
        return null;
    }
    
    public Collection getAnnotations() {
        return Arrays.asList(this.fAnnotations);
    }
    
    public Class getTestClass() {
        final String className = this.getClassName();
        if (className == null) {
            return null;
        }
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public String getClassName() {
        final Matcher methodStringMatcher = this.methodStringMatcher();
        return methodStringMatcher.matches() ? methodStringMatcher.group(2) : this.toString();
    }
    
    public String getMethodName() {
        return this.parseMethod();
    }
    
    private String parseMethod() {
        final Matcher methodStringMatcher = this.methodStringMatcher();
        if (methodStringMatcher.matches()) {
            return methodStringMatcher.group(1);
        }
        return null;
    }
    
    private Matcher methodStringMatcher() {
        return Pattern.compile("(.*)\\((.*)\\)").matcher(this.toString());
    }
    
    static {
        EMPTY = new Description("No Tests", new Annotation[0]);
        TEST_MECHANISM = new Description("Test mechanism", new Annotation[0]);
    }
}
