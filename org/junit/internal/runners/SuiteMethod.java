//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import junit.framework.*;
import java.lang.reflect.*;

public class SuiteMethod extends JUnit38ClassRunner
{
    public SuiteMethod(final Class clazz) throws Throwable {
        super(testFromSuiteMethod(clazz));
    }
    
    public static Test testFromSuiteMethod(final Class clazz) throws Throwable {
        Test test;
        try {
            final Method method = clazz.getMethod("suite", (Class[])new Class[0]);
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new Exception(clazz.getName() + ".suite() must be static");
            }
            test = (Test)method.invoke(null, new Object[0]);
        }
        catch (InvocationTargetException ex) {
            throw ex.getCause();
        }
        return test;
    }
}
