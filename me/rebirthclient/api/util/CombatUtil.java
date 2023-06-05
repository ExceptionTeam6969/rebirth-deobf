//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;

public class CombatUtil implements Wrapper
{
    public static final Timer breakTimer;
    
    public static Block getBlock(final BlockPos blockPos) {
        return CombatUtil.mc.world.getBlockState(blockPos).getBlock();
    }
    
    static {
        breakTimer = new Timer();
    }
    
    public static void attackCrystal(final Entity entity, final boolean b, final boolean b2) {
        if (!CombatUtil.breakTimer.passedMs((int)CombatSetting.INSTANCE.attackDelay.getValue())) {
            return;
        }
        if (b2 && EntityUtil.isEating()) {
            return;
        }
        if (entity != null) {
            CombatUtil.breakTimer.reset();
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
            CombatUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            if (b) {
                EntityUtil.faceXYZ(entity.posX, entity.posY + 0.25, entity.posZ);
            }
        }
    }
    
    public static void attackCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        if (!CombatUtil.breakTimer.passedMs((int)CombatSetting.INSTANCE.attackDelay.getValue())) {
            return;
        }
        if (b2 && EntityUtil.isEating()) {
            return;
        }
        final Iterator<Entity> iterator = CombatUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos)).iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if (!(entity instanceof EntityEnderCrystal)) {
                return;
            }
            CombatUtil.breakTimer.reset();
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
            CombatUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            if (b) {
                EntityUtil.faceXYZ(entity.posX, entity.posY + 0.25, entity.posZ);
            }
        }
    }
    
    public static boolean isHole(final BlockPos blockPos, final boolean b, final int n, final boolean b2) {
        int n2 = 0;
        if (b) {
            if (blockPos.add(0, 0, 1) == 0 || (blockPos.add(0, 0, 2) != 0 && blockPos.add(0, 1, 1) != 0 && blockPos.add(1, 0, 1) != 0 && blockPos.add(-1, 0, 1) != 0)) {
                ++n2;
            }
            if (blockPos.add(0, 0, -1) == 0 || (blockPos.add(0, 0, -2) != 0 && blockPos.add(0, 1, -1) != 0 && blockPos.add(1, 0, -1) != 0 && blockPos.add(-1, 0, -1) != 0)) {
                ++n2;
            }
            if (blockPos.add(1, 0, 0) == 0 || (blockPos.add(2, 0, 0) != 0 && blockPos.add(1, 1, 0) != 0 && blockPos.add(1, 0, 1) != 0 && blockPos.add(1, 0, -1) != 0)) {
                ++n2;
            }
            if (blockPos.add(-1, 0, 0) == 0 || (blockPos.add(-2, 0, 0) != 0 && blockPos.add(-1, 1, 0) != 0 && blockPos.add(-1, 0, 1) != 0 && blockPos.add(-1, 0, -1) != 0)) {
                ++n2;
            }
        }
        else {
            if (getBlock(blockPos.add(0, 0, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 0, 1)) == Blocks.BEDROCK || ((getBlock(blockPos.add(0, 0, 2)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 0, 2)) == Blocks.BEDROCK) && (getBlock(blockPos.add(0, 1, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 1, 1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(1, 0, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 0, 1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(-1, 0, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 0, 1)) == Blocks.BEDROCK))) {
                ++n2;
            }
            if (getBlock(blockPos.add(0, 0, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 0, -1)) == Blocks.BEDROCK || ((getBlock(blockPos.add(0, 0, -2)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 0, -2)) == Blocks.BEDROCK) && (getBlock(blockPos.add(0, 1, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(0, 1, -1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(1, 0, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 0, -1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(-1, 0, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 0, -1)) == Blocks.BEDROCK))) {
                ++n2;
            }
            if (getBlock(blockPos.add(1, 0, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 0, 0)) == Blocks.BEDROCK || ((getBlock(blockPos.add(2, 0, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(2, 0, 0)) == Blocks.BEDROCK) && (getBlock(blockPos.add(1, 1, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 1, 0)) == Blocks.BEDROCK) && (getBlock(blockPos.add(1, 0, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 0, 1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(1, 0, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(1, 0, -1)) == Blocks.BEDROCK))) {
                ++n2;
            }
            if (getBlock(blockPos.add(-1, 0, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 0, 0)) == Blocks.BEDROCK || ((getBlock(blockPos.add(-2, 0, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-2, 0, 0)) == Blocks.BEDROCK) && (getBlock(blockPos.add(-1, 1, 0)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 1, 0)) == Blocks.BEDROCK) && (getBlock(blockPos.add(-1, 0, 1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 0, 1)) == Blocks.BEDROCK) && (getBlock(blockPos.add(-1, 0, -1)) == Blocks.OBSIDIAN || getBlock(blockPos.add(-1, 0, -1)) == Blocks.BEDROCK))) {
                ++n2;
            }
        }
        return getBlock(blockPos) == Blocks.AIR && getBlock(blockPos.add(0, 1, 0)) == Blocks.AIR && (getBlock(blockPos.add(0, -1, 0)) != Blocks.AIR || !b2) && getBlock(blockPos.add(0, 2, 0)) == Blocks.AIR && n2 > n - 1;
    }
    
    public static void mineBlock(final BlockPos blockPos) {
        if (PacketMine.godBlocks.contains(getBlock(blockPos)) && (boolean)PacketMine.INSTANCE.godCancel.getValue()) {
            return;
        }
        if (blockPos.equals((Object)PacketMine.breakPos)) {
            return;
        }
        CombatUtil.mc.playerController.onPlayerDamageBlock(blockPos, BlockUtil.getRayTraceFacing(blockPos));
    }
    
    public static EntityPlayer getTarget(final double n) {
        final EntityPlayer entityPlayer = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)CombatUtil.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return entityPlayer;
        }
        final EntityPlayer entityPlayer2 = iterator.next();
        if (EntityUtil.invalid((Entity)entityPlayer2, n)) {
            return null;
        }
        if (entityPlayer == null) {
            CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
            return null;
        }
        if (CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
            return null;
        }
        CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
        return null;
    }
    
    public static EntityPlayer getTarget(final double n, final double n2) {
        final EntityPlayer entityPlayer = null;
        for (final EntityPlayer entityPlayer2 : CombatUtil.mc.world.playerEntities) {
            if (Managers.SPEED.getPlayerSpeed(entityPlayer2) <= n2) {
                if (EntityUtil.invalid((Entity)entityPlayer2, n)) {
                    return null;
                }
                if (entityPlayer == null) {
                    CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
                    return null;
                }
                if (CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                    return null;
                }
                CombatUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
                return null;
            }
        }
        return entityPlayer;
    }
}
