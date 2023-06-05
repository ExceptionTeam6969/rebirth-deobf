//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.requests;

import org.junit.runner.*;
import org.junit.internal.runners.*;
import org.junit.runner.manipulation.*;

public final class FilterRequest extends Request
{
    private final Request fRequest;
    private final Filter fFilter;
    
    public FilterRequest(final Request fRequest, final Filter fFilter) {
        this.fRequest = fRequest;
        this.fFilter = fFilter;
    }
    
    @Override
    public Runner getRunner() {
        try {
            final Runner runner = this.fRequest.getRunner();
            this.fFilter.apply(runner);
            return runner;
        }
        catch (NoTestsRemainException ex) {
            return new ErrorReportingRunner(Filter.class, new Exception(String.format("No tests found matching %s from %s", this.fFilter.describe(), this.fRequest.toString())));
        }
    }
}
