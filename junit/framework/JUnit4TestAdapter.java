//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import org.junit.runner.*;
import java.util.*;
import org.junit.runner.manipulation.*;

public class JUnit4TestAdapter implements Test, Filterable, Sortable, Describable
{
    private final Class fNewTestClass;
    private final Runner fRunner;
    private final JUnit4TestAdapterCache fCache;
    
    public JUnit4TestAdapter(final Class clazz) {
        this(clazz, JUnit4TestAdapterCache.getDefault());
    }
    
    public JUnit4TestAdapter(final Class fNewTestClass, final JUnit4TestAdapterCache fCache) {
        this.fCache = fCache;
        this.fNewTestClass = fNewTestClass;
        this.fRunner = Request.classWithoutSuiteMethod(fNewTestClass).getRunner();
    }
    
    public int countTestCases() {
        return this.fRunner.testCount();
    }
    
    public void run(final TestResult testResult) {
        this.fRunner.run(this.fCache.getNotifier(testResult, this));
    }
    
    public List getTests() {
        return this.fCache.asTestList(this.getDescription());
    }
    
    public Class getTestClass() {
        return this.fNewTestClass;
    }
    
    public Description getDescription() {
        return this.removeIgnored(this.fRunner.getDescription());
    }
    
    private Description removeIgnored(final Description description) {
        if (description != null) {
            return Description.EMPTY;
        }
        final Description childlessCopy = description.childlessCopy();
        final Iterator<Description> iterator = description.getChildren().iterator();
        while (iterator.hasNext()) {
            final Description removeIgnored = this.removeIgnored(iterator.next());
            if (!removeIgnored.isEmpty()) {
                childlessCopy.addChild(removeIgnored);
            }
        }
        return childlessCopy;
    }
    
    @Override
    public String toString() {
        return this.fNewTestClass.getName();
    }
    
    public void filter(final Filter filter) throws NoTestsRemainException {
        filter.apply((Object)this.fRunner);
    }
    
    public void sort(final Sorter sorter) {
        sorter.apply((Object)this.fRunner);
    }
}
