//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.*;

public class SilentDisconnect extends Module
{
    public static SilentDisconnect INSTANCE;
    
    public SilentDisconnect() {
        super("SilentDisconnect", "Silent disconnect", Category.MISC);
        SilentDisconnect.INSTANCE = this;
    }
    
    static {
        SilentDisconnect.INSTANCE = new SilentDisconnect();
    }
}
