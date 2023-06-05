//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class CameraClip extends Module
{
    public static CameraClip INSTANCE;
    public final Setting distance;
    
    static {
        CameraClip.INSTANCE = new CameraClip();
    }
    
    public CameraClip() {
        super("CameraClip", "CameraClip", Category.RENDER);
        this.distance = this.add(new Setting("Distance", 4.0, -0.5, 15.0));
        CameraClip.INSTANCE = this;
    }
}
