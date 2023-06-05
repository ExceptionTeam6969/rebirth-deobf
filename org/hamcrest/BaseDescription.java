//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest;

import java.util.*;
import org.hamcrest.internal.*;

public abstract class BaseDescription implements Description
{
    public Description appendText(final String s) {
        this.append(s);
        return this;
    }
    
    public Description appendDescriptionOf(final SelfDescribing selfDescribing) {
        selfDescribing.describeTo(this);
        return this;
    }
    
    public Description appendValue(final Object o) {
        if (o == null) {
            this.append("null");
        }
        else if (o instanceof String) {
            this.toJavaSyntax((String)o);
        }
        else if (o instanceof Character) {
            this.append('\"');
            this.toJavaSyntax((char)o);
            this.append('\"');
        }
        else if (o instanceof Short) {
            this.append('<');
            this.append(String.valueOf(o));
            this.append("s>");
        }
        else if (o instanceof Long) {
            this.append('<');
            this.append(String.valueOf(o));
            this.append("L>");
        }
        else if (o instanceof Float) {
            this.append('<');
            this.append(String.valueOf(o));
            this.append("F>");
        }
        else if (o.getClass().isArray()) {
            this.appendValueList("[", ", ", "]", new ArrayIterator(o));
        }
        else {
            this.append('<');
            this.append(String.valueOf(o));
            this.append('>');
        }
        return this;
    }
    
    public Description appendValueList(final String s, final String s2, final String s3, final Object... array) {
        return this.appendValueList(s, s2, s3, Arrays.asList(array));
    }
    
    public Description appendValueList(final String s, final String s2, final String s3, final Iterable iterable) {
        return this.appendValueList(s, s2, s3, iterable.iterator());
    }
    
    private Description appendValueList(final String s, final String s2, final String s3, final Iterator iterator) {
        return this.appendList(s, s2, s3, new SelfDescribingValueIterator(iterator));
    }
    
    public Description appendList(final String s, final String s2, final String s3, final Iterable iterable) {
        return this.appendList(s, s2, s3, iterable.iterator());
    }
    
    private Description appendList(final String s, final String s2, final String s3, final Iterator iterator) {
        int n = 0;
        this.append(s);
        while (iterator.hasNext()) {
            if (n != 0) {
                this.append(s2);
            }
            this.appendDescriptionOf(iterator.next());
            n = 1;
        }
        this.append(s3);
        return this;
    }
    
    protected void append(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.append(s.charAt(i));
        }
    }
    
    protected abstract void append(final char p0);
    
    private void toJavaSyntax(final String s) {
        this.append('\"');
        for (int i = 0; i < s.length(); ++i) {
            this.toJavaSyntax(s.charAt(i));
        }
        this.append('\"');
    }
    
    private void toJavaSyntax(final char c) {
        switch (c) {
            case '\"': {
                this.append("\\\"");
                break;
            }
            case '\n': {
                this.append("\\n");
                break;
            }
            case '\r': {
                this.append("\\r");
                break;
            }
            case '\t': {
                this.append("\\t");
                break;
            }
            default: {
                this.append(c);
                break;
            }
        }
    }
}
