//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner.manipulation;

import org.junit.runner.*;

public abstract class Filter
{
    public static Filter ALL;
    
    public static Filter matchMethodDescription(final Description description) {
        return new Filter(description) {
            final Description val$desiredDescription;
            
            @Override
            public String describe() {
                return String.format("Method %s", this.val$desiredDescription.getDisplayName());
            }
        };
    }
    
    public abstract boolean shouldRun(final Description p0);
    
    public abstract String describe();
    
    public void apply(final Object o) throws NoTestsRemainException {
        if (!(o instanceof Filterable)) {
            return;
        }
        ((Filterable)o).filter(this);
    }
    
    public Filter intersect(final Filter filter) {
        if (filter == this || filter == Filter.ALL) {
            return this;
        }
        return new Filter(this, filter) {
            final Filter val$first;
            final Filter val$second;
            final Filter this$0;
            
            @Override
            public boolean shouldRun(final Description description) {
                return this.val$first.shouldRun(description) && this.val$second.shouldRun(description);
            }
            
            @Override
            public String describe() {
                return this.val$first.describe() + " and " + this.val$second.describe();
            }
        };
    }
    
    static {
        Filter.ALL = new Filter() {
            @Override
            public boolean shouldRun(final Description description) {
                return true;
            }
            
            @Override
            public String describe() {
                return "all tests";
            }
            
            @Override
            public void apply(final Object o) throws NoTestsRemainException {
            }
            
            @Override
            public Filter intersect(final Filter filter) {
                return filter;
            }
        };
    }
}
