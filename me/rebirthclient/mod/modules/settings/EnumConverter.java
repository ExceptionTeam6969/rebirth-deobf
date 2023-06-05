//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.settings;

import com.google.common.base.*;
import com.google.gson.*;

public class EnumConverter extends Converter
{
    private final Class clazz;
    
    public JsonElement doForward(final Enum enum1) {
        return (JsonElement)new JsonPrimitive(enum1.toString());
    }
    
    public Enum doBackward(final JsonElement jsonElement) {
        try {
            return Enum.valueOf((Class<Enum>)this.clazz, jsonElement.getAsString());
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public Object doForward(final Object o) {
        return this.doForward((Enum)o);
    }
    
    public static String getProperName(final Enum enum1) {
        return Character.toUpperCase(enum1.name().charAt(0)) + enum1.name().toLowerCase().substring(1);
    }
    
    public EnumConverter(final Class clazz) {
        this.clazz = clazz;
    }
    
    public static Enum increaseEnum(final Enum enum1) {
        final int currentEnum = currentEnum(enum1);
        int n = 0;
        if (n >= ((Enum[])enum1.getClass().getEnumConstants()).length) {
            return ((Enum[])enum1.getClass().getEnumConstants())[0];
        }
        final Enum enum2 = ((Enum[])enum1.getClass().getEnumConstants())[n];
        if (n != currentEnum + 1) {
            ++n;
            return null;
        }
        return enum2;
    }
    
    public static int currentEnum(final Enum enum1) {
        int n = 0;
        if (n >= ((Enum[])enum1.getClass().getEnumConstants()).length) {
            return -1;
        }
        if (!Integer.valueOf(enum1.name().toUpperCase().hashCode()).equals(((Enum[])enum1.getClass().getEnumConstants())[n].name().toUpperCase().hashCode())) {
            ++n;
            return 0;
        }
        return n;
    }
    
    public Object doBackward(final Object o) {
        return this.doBackward((JsonElement)o);
    }
}
