//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner.notification;

import org.junit.runner.*;
import java.io.*;

public class Failure implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final Description fDescription;
    private final Throwable fThrownException;
    
    public Failure(final Description fDescription, final Throwable fThrownException) {
        this.fThrownException = fThrownException;
        this.fDescription = fDescription;
    }
    
    public String getTestHeader() {
        return this.fDescription.getDisplayName();
    }
    
    public Description getDescription() {
        return this.fDescription;
    }
    
    public Throwable getException() {
        return this.fThrownException;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getTestHeader() + ": " + this.fThrownException.getMessage());
        return sb.toString();
    }
    
    public String getTrace() {
        final StringWriter stringWriter = new StringWriter();
        this.getException().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }
    
    public String getMessage() {
        return this.getException().getMessage();
    }
}
