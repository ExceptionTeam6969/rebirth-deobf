//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.settings;

import me.rebirthclient.mod.*;
import java.util.function.*;
import java.awt.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Setting
{
    public boolean open;
    private Mod mod;
    private boolean restriction;
    public boolean hasBoolean;
    private Object value;
    public boolean noRainbow;
    public boolean parent;
    public boolean isRainbow;
    private final String name;
    private Object plannedValue;
    private Predicate visibility;
    public boolean hideAlpha;
    public boolean booleanValue;
    private Object maxValue;
    private Object minValue;
    private final Object defaultValue;
    
    public Setting(final String name, final Object plannedValue, final Object minValue, final Object maxValue) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.plannedValue = plannedValue;
        this.restriction = true;
    }
    
    public Object getPlannedValue() {
        return this.plannedValue;
    }
    
    public String getCurrentEnumName() {
        return EnumConverter.getProperName((Enum)this.value);
    }
    
    public Setting setParent() {
        this.parent = true;
        return this;
    }
    
    public void setMod(final Mod mod) {
        this.mod = mod;
    }
    
    public String getType() {
        if (this != 0) {
            return "Enum";
        }
        return this.getClassName(this.defaultValue);
    }
    
    public void setEnumValue(final String s) {
        final Enum[] array = (Enum[])((Enum)this.value).getClass().getEnumConstants();
        final int length = array.length;
        int n = 0;
        if (n < length) {
            final Enum value = array[n];
            if (Integer.valueOf(s.toUpperCase().hashCode()).equals(value.name().toUpperCase().hashCode())) {
                this.value = value;
            }
            ++n;
        }
    }
    
    public Object getValue() {
        if (this.value instanceof Color && this.isRainbow && !this.noRainbow) {
            return ColorUtil.injectAlpha(Managers.COLORS.getRainbow(), ((Color)this.value).getAlpha());
        }
        return this.value;
    }
    
    public boolean isStringSetting() {
        return this.value instanceof String;
    }
    
    public void setValue(final Object plannedValue) {
        this.setPlannedValue(plannedValue);
        if (this.restriction) {
            if (((Number)this.minValue).floatValue() > ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.minValue);
            }
            if (((Number)this.maxValue).floatValue() < ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.maxValue);
            }
        }
        final ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public void increaseEnum() {
        this.plannedValue = EnumConverter.increaseEnum((Enum)this.value);
        final ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public boolean isVisible() {
        return this.visibility == null || this.visibility.test(this.getValue());
    }
    
    public boolean isOpen() {
        return this.open && this.parent;
    }
    
    public void setPlannedValue(final Object plannedValue) {
        this.plannedValue = plannedValue;
    }
    
    public Setting(final String name, final Object plannedValue) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.plannedValue = plannedValue;
    }
    
    public Setting(final String name, final Object plannedValue, final Object minValue, final Object maxValue, final Predicate visibility) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.plannedValue = plannedValue;
        this.visibility = visibility;
        this.restriction = true;
    }
    
    public Mod getMod() {
        return this.mod;
    }
    
    public Setting hideAlpha() {
        this.hideAlpha = true;
        return this;
    }
    
    public Setting noRainbow() {
        this.noRainbow = true;
        return this;
    }
    
    public Object getMinValue() {
        return this.minValue;
    }
    
    public Setting(final String name, final Object plannedValue, final Predicate visibility) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.plannedValue = plannedValue;
        this.visibility = visibility;
    }
    
    public Object getMaxValue() {
        return this.maxValue;
    }
    
    public boolean hasRestriction() {
        return this.restriction;
    }
    
    public String getClassName(final Object o) {
        return o.getClass().getSimpleName();
    }
    
    public Object getDefaultValue() {
        return this.defaultValue;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Setting injectBoolean(final boolean booleanValue) {
        if (this.value instanceof Color) {
            this.hasBoolean = true;
            this.booleanValue = booleanValue;
        }
        return this;
    }
}
