//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod;

import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.settings.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.mod.gui.screen.*;

public class Mod implements Wrapper
{
    public List settings;
    private String name;
    
    public List getSettings() {
        return this.settings;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static boolean spawnCheck() {
        return Mod.mc.player.ticksExisted > 15;
    }
    
    public void resetSettings() {
        this.settings = new ArrayList();
    }
    
    public static boolean nullCheck() {
        return Mod.mc.player == null;
    }
    
    public Mod(final String name) {
        this.settings = new ArrayList();
        this.name = name;
    }
    
    public Setting getSettingByName(final String s) {
        final Iterator<Setting> iterator = (Iterator<Setting>)this.settings.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        final Setting setting = iterator.next();
        if (!Integer.valueOf(s.toUpperCase().hashCode()).equals(setting.getName().toUpperCase().hashCode())) {
            return null;
        }
        return setting;
    }
    
    public Setting register(final Setting setting) {
        setting.setMod(this);
        this.settings.add(setting);
        if (this instanceof Module && Mod.mc.currentScreen instanceof Gui) {
            Gui.INSTANCE.updateModule((Module)this);
        }
        return setting;
    }
    
    public Mod() {
        this.settings = new ArrayList();
    }
    
    public static boolean fullNullCheck() {
        return Mod.mc.player == null || Mod.mc.world == null;
    }
    
    public Setting add(final Setting setting) {
        setting.setMod(this);
        this.settings.add(setting);
        if (this instanceof Module && Mod.mc.currentScreen instanceof Gui) {
            Gui.INSTANCE.updateModule((Module)this);
        }
        return setting;
    }
}
