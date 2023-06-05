//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.*;
import net.minecraft.client.gui.*;

public class Appearance extends Module
{
    public Appearance() {
        super("HUDEditor", "Drag HUD elements all over your screen", Category.CLIENT);
    }
    
    @Override
    public void onEnable() {
        Appearance.mc.displayGuiScreen((GuiScreen)me.rebirthclient.mod.gui.screen.Appearance.getClickGui());
    }
    
    @Override
    public void onTick() {
        if (!(Appearance.mc.currentScreen instanceof me.rebirthclient.mod.gui.screen.Appearance)) {
            this.disable();
        }
    }
}
