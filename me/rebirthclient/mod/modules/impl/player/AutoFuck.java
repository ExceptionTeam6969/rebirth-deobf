//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.*;
import net.minecraft.client.settings.*;

public class AutoFuck extends Module
{
    public AutoFuck() {
        super("AutoFuck", "auto fuck!!", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        AutoFuck.mc.gameSettings.keyBindSneak.pressed = (!AutoFuck.mc.player.isSneaking() || GameSettings.isKeyDown(AutoFuck.mc.gameSettings.keyBindSneak));
    }
    
    @Override
    public void onDisable() {
        AutoFuck.mc.gameSettings.keyBindSneak.pressed = GameSettings.isKeyDown(AutoFuck.mc.gameSettings.keyBindSneak);
    }
}
