//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class AntiWeb extends Module
{
    public final Setting antiModeSetting;
    public static AntiWeb INSTANCE;
    
    @Override
    public void onUpdate() {
        if (this.antiModeSetting.getValue() == AntiMode.Ignore && AntiWeb.mc.player.isInWeb) {
            AntiWeb.mc.player.isInWeb = false;
        }
    }
    
    public AntiWeb() {
        super("AntiWeb", "Solid web", Category.MOVEMENT);
        this.antiModeSetting = this.add(new Setting("AntiMode", AntiMode.Block));
        AntiWeb.INSTANCE = this;
    }
    
    public enum AntiMode
    {
        Block("Block", 0);
        
        private static final AntiMode[] $VALUES;
        
        Ignore("Ignore", 1);
        
        static {
            $VALUES = new AntiMode[] { AntiMode.Block, AntiMode.Ignore };
        }
        
        private AntiMode(final String s, final int n) {
        }
    }
}
