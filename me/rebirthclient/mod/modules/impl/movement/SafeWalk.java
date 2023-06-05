//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SafeWalk extends Module
{
    public SafeWalk() {
        super("SafeWalk", "stop at the edge", Category.MOVEMENT);
    }
    
    public boolean isOffsetBBEmpty(final double n, final double n2, final double n3) {
        final EntityPlayerSP player = SafeWalk.mc.player;
        return SafeWalk.mc.world.getCollisionBoxes((Entity)player, player.getEntityBoundingBox().offset(n, n2, n3)).isEmpty();
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        final double x = moveEvent.getX();
        final double y = moveEvent.getY();
        final double z = moveEvent.getZ();
        if (SafeWalk.mc.player.onGround) {
            final double n = 0.05;
            if (x != 0.0 && this.isOffsetBBEmpty(x, -1.0, 0.0)) {
                if (x < n && x >= -n) {
                    return;
                }
                if (x > 0.0) {
                    return;
                }
                return;
            }
            else if (z != 0.0 && this.isOffsetBBEmpty(0.0, -1.0, z)) {
                if (z < n && z >= -n) {
                    return;
                }
                if (z > 0.0) {
                    return;
                }
                return;
            }
            else if (x != 0.0 && z != 0.0 && this.isOffsetBBEmpty(x, -1.0, z)) {
                final double n2 = (x < n && x >= -n) ? 0.0 : ((x > 0.0) ? (x - n) : (x + n));
                if (z < n && z >= -n) {
                    return;
                }
                if (z > 0.0) {
                    return;
                }
                return;
            }
        }
        moveEvent.setX(x);
        moveEvent.setY(y);
        moveEvent.setZ(z);
    }
}
