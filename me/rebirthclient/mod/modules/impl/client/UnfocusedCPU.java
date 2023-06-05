//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class UnfocusedCPU extends Module
{
    public static UnfocusedCPU INSTANCE;
    public final Setting unfocusedFps;
    
    public UnfocusedCPU() {
        super("UnfocusedCPU", "Decreases your framerate when minecraft is unfocused", Category.CLIENT);
        this.unfocusedFps = this.add(new Setting("UnfocusedFPS", 5, 1, 30));
        UnfocusedCPU.INSTANCE = this;
    }
}
