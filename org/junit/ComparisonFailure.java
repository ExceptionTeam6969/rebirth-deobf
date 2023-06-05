//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit;

public class ComparisonFailure extends AssertionError
{
    private static final int MAX_CONTEXT_LENGTH = 20;
    private static final long serialVersionUID = 1L;
    private String fExpected;
    private String fActual;
    
    public ComparisonFailure(final String s, final String fExpected, final String fActual) {
        super((Object)s);
        this.fExpected = fExpected;
        this.fActual = fActual;
    }
    
    @Override
    public String getMessage() {
        return ComparisonCompactor.access$000(new ComparisonCompactor(20, this.fExpected, this.fActual), super.getMessage());
    }
    
    public String getActual() {
        return this.fActual;
    }
    
    public String getExpected() {
        return this.fExpected;
    }
    
    private static class ComparisonCompactor
    {
        private static final String ELLIPSIS = "...";
        private static final String DELTA_END = "]";
        private static final String DELTA_START = "[";
        private int fContextLength;
        private String fExpected;
        private String fActual;
        private int fPrefix;
        private int fSuffix;
        
        public ComparisonCompactor(final int fContextLength, final String fExpected, final String fActual) {
            this.fContextLength = fContextLength;
            this.fExpected = fExpected;
            this.fActual = fActual;
        }
        
        private String compact(final String s) {
            if (this.fExpected == null || this.fActual == null || this.areStringsEqual()) {
                return Assert.format(s, (Object)this.fExpected, (Object)this.fActual);
            }
            this.findCommonPrefix();
            this.findCommonSuffix();
            return Assert.format(s, (Object)this.compactString(this.fExpected), (Object)this.compactString(this.fActual));
        }
        
        private String compactString(final String s) {
            String s2 = "[" + s.substring(this.fPrefix, s.length() - this.fSuffix + 1) + "]";
            if (this.fPrefix > 0) {
                s2 = this.computeCommonPrefix() + s2;
            }
            if (this.fSuffix > 0) {
                s2 += this.computeCommonSuffix();
            }
            return s2;
        }
        
        private void findCommonPrefix() {
            this.fPrefix = 0;
            while (this.fPrefix < Math.min(this.fExpected.length(), this.fActual.length()) && this.fExpected.charAt(this.fPrefix) == this.fActual.charAt(this.fPrefix)) {
                ++this.fPrefix;
            }
        }
        
        private void findCommonSuffix() {
            int n = this.fExpected.length() - 1;
            for (int n2 = this.fActual.length() - 1; n2 >= this.fPrefix && n >= this.fPrefix && this.fExpected.charAt(n) == this.fActual.charAt(n2); --n2, --n) {}
            this.fSuffix = this.fExpected.length() - n;
        }
        
        private String computeCommonPrefix() {
            return ((this.fPrefix > this.fContextLength) ? "..." : "") + this.fExpected.substring(Math.max(0, this.fPrefix - this.fContextLength), this.fPrefix);
        }
        
        private String computeCommonSuffix() {
            return this.fExpected.substring(this.fExpected.length() - this.fSuffix + 1, Math.min(this.fExpected.length() - this.fSuffix + 1 + this.fContextLength, this.fExpected.length())) + ((this.fExpected.length() - this.fSuffix + 1 < this.fExpected.length() - this.fContextLength) ? "..." : "");
        }
        
        private boolean areStringsEqual() {
            return this.fExpected.equals(this.fActual);
        }
        
        static String access$000(final ComparisonCompactor comparisonCompactor, final String s) {
            return comparisonCompactor.compact(s);
        }
    }
}
