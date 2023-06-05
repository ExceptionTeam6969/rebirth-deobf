//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import java.util.*;

public final class Annotations
{
    private Annotations() {
    }
    
    public static void setVisible(final FieldNode fieldNode, final Class clazz, final Object... array) {
        fieldNode.visibleAnnotations = add(fieldNode.visibleAnnotations, createNode(Type.getDescriptor(clazz), array));
    }
    
    public static void setInvisible(final FieldNode fieldNode, final Class clazz, final Object... array) {
        fieldNode.invisibleAnnotations = add(fieldNode.invisibleAnnotations, createNode(Type.getDescriptor(clazz), array));
    }
    
    public static void setVisible(final MethodNode methodNode, final Class clazz, final Object... array) {
        methodNode.visibleAnnotations = add(methodNode.visibleAnnotations, createNode(Type.getDescriptor(clazz), array));
    }
    
    public static void setInvisible(final MethodNode methodNode, final Class clazz, final Object... array) {
        methodNode.invisibleAnnotations = add(methodNode.invisibleAnnotations, createNode(Type.getDescriptor(clazz), array));
    }
    
    private static AnnotationNode createNode(final String s, final Object... array) {
        final AnnotationNode annotationNode = new AnnotationNode(s);
        for (int i = 0; i < array.length - 1; i += 2) {
            if (!(array[i] instanceof String)) {
                throw new IllegalArgumentException("Annotation keys must be strings, found " + array[i].getClass().getSimpleName() + " with " + array[i].toString() + " at index " + i + " creating " + s);
            }
            annotationNode.visit((String)array[i], array[i + 1]);
        }
        return annotationNode;
    }
    
    private static List add(List list, final AnnotationNode annotationNode) {
        if (list == null) {
            list = new ArrayList<AnnotationNode>(1);
        }
        else {
            list.remove(get(list, annotationNode.desc));
        }
        list.add(annotationNode);
        return list;
    }
    
    public static AnnotationNode getVisible(final FieldNode fieldNode, final Class clazz) {
        return get(fieldNode.visibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getInvisible(final FieldNode fieldNode, final Class clazz) {
        return get(fieldNode.invisibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getVisible(final MethodNode methodNode, final Class clazz) {
        return get(methodNode.visibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getInvisible(final MethodNode methodNode, final Class clazz) {
        return get(methodNode.invisibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getSingleVisible(final MethodNode methodNode, final Class... array) {
        return getSingle(methodNode.visibleAnnotations, array);
    }
    
    public static AnnotationNode getSingleInvisible(final MethodNode methodNode, final Class... array) {
        return getSingle(methodNode.invisibleAnnotations, array);
    }
    
    public static AnnotationNode getVisible(final ClassNode classNode, final Class clazz) {
        return get(classNode.visibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getInvisible(final ClassNode classNode, final Class clazz) {
        return get(classNode.invisibleAnnotations, Type.getDescriptor(clazz));
    }
    
    public static AnnotationNode getVisibleParameter(final MethodNode methodNode, final Class clazz, final int n) {
        return getParameter(methodNode.visibleParameterAnnotations, Type.getDescriptor(clazz), n);
    }
    
    public static AnnotationNode getInvisibleParameter(final MethodNode methodNode, final Class clazz, final int n) {
        return getParameter(methodNode.invisibleParameterAnnotations, Type.getDescriptor(clazz), n);
    }
    
    public static AnnotationNode getParameter(final List[] array, final String s, final int n) {
        if (array == null || n < 0 || n >= array.length) {
            return null;
        }
        return get(array[n], s);
    }
    
    public static AnnotationNode get(final List list, final String s) {
        if (list == null) {
            return null;
        }
        for (final AnnotationNode annotationNode : list) {
            if (s.equals(annotationNode.desc)) {
                return annotationNode;
            }
        }
        return null;
    }
    
    private static AnnotationNode getSingle(final List list, final Class[] array) {
        final ArrayList<AnnotationNode> list2 = new ArrayList<AnnotationNode>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final AnnotationNode value = get(list, Type.getDescriptor(array[i]));
            if (value != null) {
                list2.add(value);
            }
        }
        final int size = list2.size();
        if (size > 1) {
            throw new IllegalArgumentException("Conflicting annotations found: " + Lists.transform((List)list2, (Function)new Function() {
                public String apply(final AnnotationNode annotationNode) {
                    return annotationNode.desc;
                }
                
                public Object apply(final Object o) {
                    return this.apply((AnnotationNode)o);
                }
            }));
        }
        return (size == 0) ? null : list2.get(0);
    }
    
    public static Object getValue(final AnnotationNode annotationNode) {
        return getValue(annotationNode, "value");
    }
    
    public static Object getValue(final AnnotationNode annotationNode, final String s, final Object o) {
        final Object value = getValue(annotationNode, s);
        return (value != null) ? value : o;
    }
    
    public static Object getValue(final AnnotationNode annotationNode, final String s, final Class clazz) {
        Preconditions.checkNotNull((Object)clazz, (Object)"annotationClass cannot be null");
        Object o = getValue(annotationNode, s);
        if (o == null) {
            try {
                o = clazz.getDeclaredMethod(s, (Class[])new Class[0]).getDefaultValue();
            }
            catch (NoSuchMethodException ex) {}
        }
        return o;
    }
    
    public static Object getValue(final AnnotationNode annotationNode, final String s) {
        int n = 0;
        if (annotationNode == null || annotationNode.values == null) {
            return null;
        }
        for (final Object next : annotationNode.values) {
            if (n != 0) {
                return next;
            }
            if (!next.equals(s)) {
                continue;
            }
            n = 1;
        }
        return null;
    }
    
    public static Enum getValue(final AnnotationNode annotationNode, final String s, final Class clazz, final Enum enum1) {
        final String[] array = (String[])getValue(annotationNode, s);
        if (array == null) {
            return enum1;
        }
        return toEnumValue(clazz, array);
    }
    
    public static List getValue(final AnnotationNode annotationNode, final String s, final boolean b) {
        final Object value = getValue(annotationNode, s);
        if (value instanceof List) {
            return (List)value;
        }
        if (value != null) {
            final ArrayList<List> list = new ArrayList<List>();
            list.add((List)value);
            return list;
        }
        return Collections.emptyList();
    }
    
    public static List getValue(final AnnotationNode annotationNode, final String s, final boolean b, final Class clazz) {
        final Object value = getValue(annotationNode, s);
        if (value instanceof List) {
            final ListIterator<String[]> listIterator = ((List<String[]>)value).listIterator();
            while (listIterator.hasNext()) {
                listIterator.set((String[])(Object)toEnumValue(clazz, listIterator.next()));
            }
            return (List<String[]>)value;
        }
        if (value instanceof String[]) {
            final ArrayList<Enum> list = new ArrayList<Enum>();
            list.add(toEnumValue(clazz, (String[])value));
            return list;
        }
        return Collections.emptyList();
    }
    
    private static Enum toEnumValue(final Class clazz, final String[] array) {
        if (!clazz.getName().equals(Type.getType(array[0]).getClassName())) {
            throw new IllegalArgumentException("The supplied enum class does not match the stored enum value");
        }
        return Enum.valueOf((Class<Enum>)clazz, array[1]);
    }
}
