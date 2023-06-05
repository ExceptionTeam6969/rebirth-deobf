//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.*;
import net.minecraft.client.gui.*;

public class AutoRespawn extends Module
{
    public AutoRespawn() {
        super("AutoRespawn", "Auto Respawn when dead", Category.PLAYER);
    }
    
    @Override
    public void onUpdate() {
        if (AutoRespawn.mc.currentScreen instanceof GuiGameOver) {
            AutoRespawn.mc.player.respawnPlayer();
            AutoRespawn.mc.displayGuiScreen((GuiScreen)null);
        }
    }
}
