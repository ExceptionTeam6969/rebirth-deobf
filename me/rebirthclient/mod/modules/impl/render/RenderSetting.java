//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class RenderSetting extends Module
{
    public final Setting outlineWidth;
    public static RenderSetting INSTANCE;
    
    public RenderSetting() {
        super("RenderSetting", "idk", Category.RENDER);
        this.outlineWidth = this.add(new Setting("OutlineWidth", 1.0f, 0.1f, 4.0f));
        RenderSetting.INSTANCE = this;
    }
}
