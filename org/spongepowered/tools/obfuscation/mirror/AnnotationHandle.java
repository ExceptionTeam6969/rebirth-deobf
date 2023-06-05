//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.collect.*;
import java.util.*;
import javax.lang.model.element.*;

public final class AnnotationHandle
{
    public static final AnnotationHandle MISSING;
    private final AnnotationMirror annotation;
    
    private AnnotationHandle(final AnnotationMirror annotation) {
        this.annotation = annotation;
    }
    
    public AnnotationMirror asMirror() {
        return this.annotation;
    }
    
    public boolean exists() {
        return this.annotation != null;
    }
    
    @Override
    public String toString() {
        if (this.annotation == null) {
            return "@{UnknownAnnotation}";
        }
        return "@" + (Object)this.annotation.getAnnotationType().asElement().getSimpleName();
    }
    
    public Object getValue(final String s, final Object o) {
        if (this.annotation == null) {
            return o;
        }
        final AnnotationValue annotationValue = this.getAnnotationValue(s);
        if (!(o instanceof Enum) || annotationValue == null) {
            return (annotationValue != null) ? annotationValue.getValue() : o;
        }
        final VariableElement variableElement = (VariableElement)annotationValue.getValue();
        if (variableElement == null) {
            return o;
        }
        return Enum.valueOf(o.getClass(), variableElement.getSimpleName().toString());
    }
    
    public Object getValue() {
        return this.getValue("value", null);
    }
    
    public Object getValue(final String s) {
        return this.getValue(s, null);
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        return (boolean)this.getValue(s, b);
    }
    
    public AnnotationHandle getAnnotation(final String s) {
        final Object value = this.getValue(s);
        if (value instanceof AnnotationMirror) {
            return of((AnnotationMirror)value);
        }
        if (value instanceof AnnotationValue) {
            final Object value2 = ((AnnotationValue)value).getValue();
            if (value2 instanceof AnnotationMirror) {
                return of((AnnotationMirror)value2);
            }
        }
        return null;
    }
    
    public List getList() {
        return this.getList("value");
    }
    
    public List getList(final String s) {
        return unwrapAnnotationValueList((List)this.getValue(s, Collections.emptyList()));
    }
    
    public List getAnnotationList(final String s) {
        final Object value = this.getValue(s, null);
        if (value == null) {
            return Collections.emptyList();
        }
        if (value instanceof AnnotationMirror) {
            return (List)ImmutableList.of((Object)of((AnnotationMirror)value));
        }
        final List<AnnotationValue> list = (List<AnnotationValue>)value;
        final ArrayList list2 = new ArrayList<AnnotationHandle>(list.size());
        final Iterator<AnnotationValue> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(new AnnotationHandle((AnnotationMirror)iterator.next().getValue()));
        }
        return Collections.unmodifiableList((List<?>)list2);
    }
    
    protected AnnotationValue getAnnotationValue(final String s) {
        for (final ExecutableElement executableElement : this.annotation.getElementValues().keySet()) {
            if (executableElement.getSimpleName().contentEquals(s)) {
                return (AnnotationValue)this.annotation.getElementValues().get(executableElement);
            }
        }
        return null;
    }
    
    protected static List unwrapAnnotationValueList(final List list) {
        if (list == null) {
            return Collections.emptyList();
        }
        final ArrayList<Object> list2 = new ArrayList<Object>(list.size());
        final Iterator<AnnotationValue> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getValue());
        }
        return list2;
    }
    
    protected static AnnotationMirror getAnnotation(final Element element, final Class clazz) {
        if (element == null) {
            return null;
        }
        final List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
        if (annotationMirrors == null) {
            return null;
        }
        for (final AnnotationMirror annotationMirror : annotationMirrors) {
            final Element element2 = annotationMirror.getAnnotationType().asElement();
            if (!(element2 instanceof TypeElement)) {
                continue;
            }
            if (((TypeElement)element2).getQualifiedName().contentEquals(clazz.getName())) {
                return annotationMirror;
            }
        }
        return null;
    }
    
    public static AnnotationHandle of(final AnnotationMirror annotationMirror) {
        return new AnnotationHandle(annotationMirror);
    }
    
    public static AnnotationHandle of(final Element element, final Class clazz) {
        return new AnnotationHandle(getAnnotation(element, clazz));
    }
    
    static {
        MISSING = new AnnotationHandle(null);
    }
}
