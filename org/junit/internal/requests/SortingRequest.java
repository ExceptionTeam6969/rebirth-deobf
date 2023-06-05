//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.requests;

import java.util.*;
import org.junit.runner.*;
import org.junit.runner.manipulation.*;

public class SortingRequest extends Request
{
    private final Request fRequest;
    private final Comparator fComparator;
    
    public SortingRequest(final Request fRequest, final Comparator fComparator) {
        this.fRequest = fRequest;
        this.fComparator = fComparator;
    }
    
    @Override
    public Runner getRunner() {
        final Runner runner = this.fRequest.getRunner();
        new Sorter(this.fComparator).apply(runner);
        return runner;
    }
}
