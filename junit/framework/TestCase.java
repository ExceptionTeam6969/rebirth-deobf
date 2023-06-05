//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import java.lang.reflect.*;

public abstract class TestCase extends Assert implements Test
{
    private String fName;
    
    public TestCase() {
        this.fName = null;
    }
    
    public TestCase(final String fName) {
        this.fName = fName;
    }
    
    public int countTestCases() {
        return 1;
    }
    
    protected TestResult createResult() {
        return new TestResult();
    }
    
    public TestResult run() {
        final TestResult result = this.createResult();
        this.run(result);
        return result;
    }
    
    public void run(final TestResult testResult) {
        testResult.run(this);
    }
    
    public void runBare() throws Throwable {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: aload_0        
        //     3: invokevirtual   junit/framework/TestCase.setUp:()V
        //     6: aload_0        
        //     7: invokevirtual   junit/framework/TestCase.runTest:()V
        //    10: aload_0        
        //    11: invokevirtual   junit/framework/TestCase.tearDown:()V
        //    14: goto            66
        //    17: astore_2       
        //    18: aload_1        
        //    19: ifnonnull       24
        //    22: aload_2        
        //    23: astore_1       
        //    24: goto            66
        //    27: astore_2       
        //    28: aload_2        
        //    29: astore_1       
        //    30: aload_0        
        //    31: invokevirtual   junit/framework/TestCase.tearDown:()V
        //    34: goto            66
        //    37: astore_2       
        //    38: aload_1        
        //    39: ifnonnull       44
        //    42: aload_2        
        //    43: astore_1       
        //    44: goto            66
        //    47: astore_3       
        //    48: aload_0        
        //    49: invokevirtual   junit/framework/TestCase.tearDown:()V
        //    52: goto            64
        //    55: astore          4
        //    57: aload_1        
        //    58: ifnonnull       64
        //    61: aload           4
        //    63: astore_1       
        //    64: aload_3        
        //    65: athrow         
        //    66: aload_1        
        //    67: ifnull          72
        //    70: aload_1        
        //    71: athrow         
        //    72: return         
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     14     17     27     Ljava/lang/Throwable;
        //  6      10     27     47     Ljava/lang/Throwable;
        //  30     34     37     47     Ljava/lang/Throwable;
        //  48     52     55     61     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException
        //     at java.util.Collections$1.remove(Collections.java:4686)
        //     at java.util.AbstractCollection.removeAll(AbstractCollection.java:376)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2968)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void runTest() throws Throwable {
        assertNotNull("TestCase.fName cannot be null", (Object)this.fName);
        Method method = null;
        try {
            method = this.getClass().getMethod(this.fName, (Class<?>[])null);
        }
        catch (NoSuchMethodException ex3) {
            fail("Method \"" + this.fName + "\" not found");
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            fail("Method \"" + this.fName + "\" should be public");
        }
        try {
            method.invoke(this, new Object[0]);
        }
        catch (InvocationTargetException ex) {
            ex.fillInStackTrace();
            throw ex.getTargetException();
        }
        catch (IllegalAccessException ex2) {
            ex2.fillInStackTrace();
            throw ex2;
        }
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
    
    public String toString() {
        return this.getName() + "(" + this.getClass().getName() + ")";
    }
    
    public String getName() {
        return this.fName;
    }
    
    public void setName(final String fName) {
        this.fName = fName;
    }
}
