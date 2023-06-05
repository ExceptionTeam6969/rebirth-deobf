//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;

public class RuleChain implements TestRule
{
    private static final RuleChain EMPTY_CHAIN;
    private List rulesStartingWithInnerMost;
    
    public static RuleChain emptyRuleChain() {
        return RuleChain.EMPTY_CHAIN;
    }
    
    public static RuleChain outerRule(final TestRule testRule) {
        return emptyRuleChain().around(testRule);
    }
    
    private RuleChain(final List rulesStartingWithInnerMost) {
        this.rulesStartingWithInnerMost = rulesStartingWithInnerMost;
    }
    
    public RuleChain around(final TestRule testRule) {
        final ArrayList<Object> list = new ArrayList<Object>();
        list.add(testRule);
        list.addAll(this.rulesStartingWithInnerMost);
        return new RuleChain(list);
    }
    
    public Statement apply(Statement apply, final Description description) {
        final Iterator<TestRule> iterator = this.rulesStartingWithInnerMost.iterator();
        while (iterator.hasNext()) {
            apply = iterator.next().apply(apply, description);
        }
        return apply;
    }
    
    static {
        EMPTY_CHAIN = new RuleChain(Collections.emptyList());
    }
}
