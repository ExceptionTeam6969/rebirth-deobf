//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class AntiNullPointer extends Module
{
    public final Setting debug;
    public static AntiNullPointer INSTANCE;
    
    public AntiNullPointer() {
        super("AntiNull", "anti null pointer kick", Category.MISC);
        this.debug = this.add(new Setting("Debug", true));
        AntiNullPointer.INSTANCE = this;
    }
    
    static {
        AntiNullPointer.INSTANCE = new AntiNullPointer();
    }
    
    public void sendWarning(final Throwable t) {
        if (this.debug.getValue()) {
            this.sendMessage("Patch null point kick!");
        }
        t.printStackTrace();
    }
}
