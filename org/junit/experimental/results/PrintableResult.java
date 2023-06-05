//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.results;

import org.junit.runner.*;
import java.util.*;
import java.io.*;
import org.junit.internal.*;

public class PrintableResult
{
    private Result result;
    
    public static PrintableResult testResult(final Class clazz) {
        return testResult(Request.aClass(clazz));
    }
    
    public static PrintableResult testResult(final Request request) {
        return new PrintableResult(new JUnitCore().run(request));
    }
    
    public PrintableResult(final List list) {
        this(new FailureList(list).result());
    }
    
    private PrintableResult(final Result result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new TextListener(new PrintStream(byteArrayOutputStream)).testRunFinished(this.result);
        return byteArrayOutputStream.toString();
    }
    
    public int failureCount() {
        return this.result.getFailures().size();
    }
}
