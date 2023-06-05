//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.max;

import java.io.*;
import org.junit.internal.requests.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.model.*;
import java.util.*;
import org.junit.internal.runners.*;
import junit.framework.*;
import java.lang.annotation.*;

public class MaxCore
{
    private static final String MALFORMED_JUNIT_3_TEST_CLASS_PREFIX = "malformed JUnit 3 test class: ";
    private final MaxHistory fHistory;
    
    @Deprecated
    public static MaxCore forFolder(final String s) {
        return storedLocally(new File(s));
    }
    
    public static MaxCore storedLocally(final File file) {
        return new MaxCore(file);
    }
    
    private MaxCore(final File file) {
        this.fHistory = MaxHistory.forFolder(file);
    }
    
    public Result run(final Class clazz) {
        return this.run(Request.aClass(clazz));
    }
    
    public Result run(final Request request) {
        return this.run(request, new JUnitCore());
    }
    
    public Result run(final Request request, final JUnitCore unitCore) {
        unitCore.addListener(this.fHistory.listener());
        return unitCore.run(this.sortRequest(request).getRunner());
    }
    
    public Request sortRequest(final Request request) {
        if (request instanceof SortingRequest) {
            return request;
        }
        final List leaves = this.findLeaves(request);
        Collections.sort((List<Object>)leaves, this.fHistory.testComparator());
        return this.constructLeafRequest(leaves);
    }
    
    private Request constructLeafRequest(final List list) {
        final ArrayList<Runner> list2 = new ArrayList<Runner>();
        final Iterator<Description> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(this.buildRunner(iterator.next()));
        }
        return new Request((List)list2) {
            final List val$runners;
            final MaxCore this$0;
            
            @Override
            public Runner getRunner() {
                try {
                    return new Suite((Class)null, this.val$runners) {
                        final MaxCore$1 this$1;
                    };
                }
                catch (InitializationError initializationError) {
                    return new ErrorReportingRunner(null, initializationError);
                }
            }
        };
    }
    
    private Runner buildRunner(final Description description) {
        if (description.toString().equals("TestSuite with 0 tests")) {
            return Suite.emptySuite();
        }
        if (description.toString().startsWith("malformed JUnit 3 test class: ")) {
            return new JUnit38ClassRunner((Test)new TestSuite(this.getMalformedTestClass(description)));
        }
        final Class testClass = description.getTestClass();
        if (testClass == null) {
            throw new RuntimeException("Can't build a runner from description [" + description + "]");
        }
        final String methodName = description.getMethodName();
        if (methodName == null) {
            return Request.aClass(testClass).getRunner();
        }
        return Request.method(testClass, methodName).getRunner();
    }
    
    private Class getMalformedTestClass(final Description description) {
        try {
            return Class.forName(description.toString().replace("malformed JUnit 3 test class: ", ""));
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public List sortedLeavesForTest(final Request request) {
        return this.findLeaves(this.sortRequest(request));
    }
    
    private List findLeaves(final Request request) {
        final ArrayList list = new ArrayList();
        this.findLeaves(null, request.getRunner().getDescription(), list);
        return list;
    }
    
    private void findLeaves(final Description description, final Description description2, final List list) {
        if (description2.getChildren().isEmpty()) {
            if (description2.toString().equals("warning(junit.framework.TestSuite$1)")) {
                list.add(Description.createSuiteDescription("malformed JUnit 3 test class: " + description, new Annotation[0]));
            }
            else {
                list.add(description2);
            }
        }
        else {
            final Iterator<Description> iterator = (Iterator<Description>)description2.getChildren().iterator();
            while (iterator.hasNext()) {
                this.findLeaves(description2, iterator.next(), list);
            }
        }
    }
}
