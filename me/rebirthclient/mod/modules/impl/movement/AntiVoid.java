//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;

public class AntiVoid extends Module
{
    private final Setting height;
    
    public AntiVoid() {
        super("AntiVoid", "Allows you to fly over void blocks", Category.MOVEMENT);
        this.height = this.add(new Setting("Height", 100, 0, 256));
    }
    
    @Override
    public void onTick() {
        boolean b = true;
        int n = (int)AntiVoid.mc.player.posY;
        if (n > -1) {
            if (BlockUtil.getBlock(new BlockPos(AntiVoid.mc.player.posX, (double)n, AntiVoid.mc.player.posZ)) == Blocks.AIR) {
                --n;
                return;
            }
            b = false;
        }
        if (AntiVoid.mc.player.posY < (int)this.height.getValue() && b) {
            AntiVoid.mc.player.motionY = 0.0;
        }
    }
}
