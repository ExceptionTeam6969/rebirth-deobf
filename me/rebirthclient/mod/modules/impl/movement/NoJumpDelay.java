//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.*;

public final class NoJumpDelay extends Module
{
    public static NoJumpDelay INSTANCE;
    
    static {
        NoJumpDelay.INSTANCE = new NoJumpDelay();
    }
    
    public NoJumpDelay() {
        super("NoJumpDelay", "No jump delay", Category.MOVEMENT);
        NoJumpDelay.INSTANCE = this;
    }
}
