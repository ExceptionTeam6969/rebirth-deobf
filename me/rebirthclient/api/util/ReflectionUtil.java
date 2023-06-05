//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import java.util.*;
import java.lang.reflect.*;

public class ReflectionUtil
{
    public static void copyOf(final Object o, final Object o2, final boolean b) throws IllegalAccessException, NoSuchFieldException {
        Objects.requireNonNull(o);
        Objects.requireNonNull(o2);
        final Field[] declaredFields = o.getClass().getDeclaredFields();
        final int length = declaredFields.length;
        int n = 0;
        if (n < length) {
            final Field field = declaredFields[n];
            makePublic(field);
            if (field != 0) {
                if (!b || field == 0) {
                    makeMutable(field);
                    field.set(o2, field.get(o));
                }
            }
            ++n;
        }
    }
    
    public static void makeAccessible(final AccessibleObject accessibleObject, final boolean accessible) {
        Objects.requireNonNull(accessibleObject);
        accessibleObject.setAccessible(accessible);
    }
    
    public static void makeMutable(final Member member) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(member);
        final Field declaredField = Field.class.getDeclaredField("modifiers");
        makePublic(declaredField);
        declaredField.setInt(member, member.getModifiers() & 0xFFFFFFEF);
    }
    
    public static void copyOf(final Object o, final Object o2) throws NoSuchFieldException, IllegalAccessException {
        copyOf(o, o2, false);
    }
    
    public static void makePublic(final AccessibleObject accessibleObject) {
        makeAccessible(accessibleObject, true);
    }
}
