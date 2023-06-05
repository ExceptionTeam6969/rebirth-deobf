//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.settings;

import org.lwjgl.input.*;
import com.google.common.base.*;
import com.google.gson.*;

public class Bind
{
    private int key;
    
    @Override
    public String toString() {
        return (this != 0) ? "None" : ((this.key < 0) ? "None" : this.capitalise(Keyboard.getKeyName(this.key)));
    }
    
    public static Bind none() {
        return new Bind(-1);
    }
    
    public Bind(final int key) {
        this.key = key;
    }
    
    public boolean isDown() {
        return this != 0 && Keyboard.isKeyDown(this.getKey());
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    private String capitalise(final String s) {
        if (s.isEmpty()) {
            return "";
        }
        return Character.toUpperCase(s.charAt(0)) + ((s.length() != 1) ? s.substring(1).toLowerCase() : "");
    }
    
    public int getKey() {
        return this.key;
    }
    
    public static class BindConverter extends Converter
    {
        public Object doForward(final Object o) {
            return this.doForward((Bind)o);
        }
        
        public JsonElement doForward(final Bind bind) {
            return (JsonElement)new JsonPrimitive(bind.toString());
        }
        
        public Bind doBackward(final JsonElement jsonElement) {
            final String asString = jsonElement.getAsString();
            if (Integer.valueOf("None".toUpperCase().hashCode()).equals(asString.toUpperCase().hashCode())) {
                return Bind.none();
            }
            int keyIndex = -1;
            try {
                keyIndex = Keyboard.getKeyIndex(asString.toUpperCase());
            }
            catch (Exception ex) {}
            if (keyIndex == 0) {
                return Bind.none();
            }
            return new Bind(keyIndex);
        }
        
        public Object doBackward(final Object o) {
            return this.doBackward((JsonElement)o);
        }
    }
}
