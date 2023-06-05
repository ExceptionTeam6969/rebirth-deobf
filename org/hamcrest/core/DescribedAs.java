//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import java.util.regex.*;
import org.hamcrest.*;

public class DescribedAs extends BaseMatcher
{
    private final String descriptionTemplate;
    private final Matcher matcher;
    private final Object[] values;
    private static final Pattern ARG_PATTERN;
    
    public DescribedAs(final String descriptionTemplate, final Matcher matcher, final Object[] array) {
        this.descriptionTemplate = descriptionTemplate;
        this.matcher = matcher;
        this.values = array.clone();
    }
    
    public boolean matches(final Object o) {
        return this.matcher.matches(o);
    }
    
    public void describeTo(final Description description) {
        final java.util.regex.Matcher matcher = DescribedAs.ARG_PATTERN.matcher(this.descriptionTemplate);
        int end = 0;
        while (matcher.find()) {
            description.appendText(this.descriptionTemplate.substring(end, matcher.start()));
            description.appendValue(this.values[Integer.parseInt(matcher.group(1))]);
            end = matcher.end();
        }
        if (end < this.descriptionTemplate.length()) {
            description.appendText(this.descriptionTemplate.substring(end));
        }
    }
    
    @Factory
    public static Matcher describedAs(final String s, final Matcher matcher, final Object... array) {
        return new DescribedAs(s, matcher, array);
    }
    
    static {
        ARG_PATTERN = Pattern.compile("%([0-9]+)");
    }
}
