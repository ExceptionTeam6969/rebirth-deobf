//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.textui;

import java.io.*;
import java.util.*;
import junit.runner.*;
import java.text.*;
import junit.framework.*;

public class ResultPrinter implements TestListener
{
    PrintStream fWriter;
    int fColumn;
    
    public ResultPrinter(final PrintStream fWriter) {
        this.fColumn = 0;
        this.fWriter = fWriter;
    }
    
    synchronized void print(final TestResult testResult, final long n) {
        this.printHeader(n);
        this.printErrors(testResult);
        this.printFailures(testResult);
        this.printFooter(testResult);
    }
    
    void printWaitPrompt() {
        this.getWriter().println();
        this.getWriter().println("<RETURN> to continue");
    }
    
    protected void printHeader(final long n) {
        this.getWriter().println();
        this.getWriter().println("Time: " + this.elapsedTimeAsString(n));
    }
    
    protected void printErrors(final TestResult testResult) {
        this.printDefects(testResult.errors(), testResult.errorCount(), "error");
    }
    
    protected void printFailures(final TestResult testResult) {
        this.printDefects(testResult.failures(), testResult.failureCount(), "failure");
    }
    
    protected void printDefects(final Enumeration enumeration, final int n, final String s) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            this.getWriter().println("There was " + n + " " + s + ":");
        }
        else {
            this.getWriter().println("There were " + n + " " + s + "s:");
        }
        int n2 = 1;
        while (enumeration.hasMoreElements()) {
            this.printDefect(enumeration.nextElement(), n2);
            ++n2;
        }
    }
    
    public void printDefect(final TestFailure testFailure, final int n) {
        this.printDefectHeader(testFailure, n);
        this.printDefectTrace(testFailure);
    }
    
    protected void printDefectHeader(final TestFailure testFailure, final int n) {
        this.getWriter().print(n + ") " + testFailure.failedTest());
    }
    
    protected void printDefectTrace(final TestFailure testFailure) {
        this.getWriter().print(BaseTestRunner.getFilteredTrace(testFailure.trace()));
    }
    
    protected void printFooter(final TestResult testResult) {
        if (testResult.wasSuccessful()) {
            this.getWriter().println();
            this.getWriter().print("OK");
            this.getWriter().println(" (" + testResult.runCount() + " test" + ((testResult.runCount() == 1) ? "" : "s") + ")");
        }
        else {
            this.getWriter().println();
            this.getWriter().println("FAILURES!!!");
            this.getWriter().println("Tests run: " + testResult.runCount() + ",  Failures: " + testResult.failureCount() + ",  Errors: " + testResult.errorCount());
        }
        this.getWriter().println();
    }
    
    protected String elapsedTimeAsString(final long n) {
        return NumberFormat.getInstance().format(n / 1000.0);
    }
    
    public PrintStream getWriter() {
        return this.fWriter;
    }
    
    public void addError(final Test test, final Throwable t) {
        this.getWriter().print("E");
    }
    
    public void addFailure(final Test test, final AssertionFailedError assertionFailedError) {
        this.getWriter().print("F");
    }
    
    public void endTest(final Test test) {
    }
    
    public void startTest(final Test test) {
        this.getWriter().print(".");
        if (this.fColumn++ >= 40) {
            this.getWriter().println();
            this.fColumn = 0;
        }
    }
}
