//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.*;
import java.util.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.entity.player.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.*;

public class InteractionManager extends Mod
{
    private final Timer attackTimer;
    
    public Optional getClickLocation(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        final Block getBlock = InteractionManager.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return Optional.empty();
        }
        if (!b) {
            final Iterator<Entity> iterator = (Iterator<Entity>)InteractionManager.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos)).iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (b3 && entity instanceof EntityEnderCrystal) {
                    return null;
                }
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityArrow)) {
                    return Optional.empty();
                }
                return null;
            }
        }
        EnumFacing enumFacing = null;
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        int n = 0;
        Label_0275: {
            if (n < length) {
                final EnumFacing enumFacing2 = values[n];
                final BlockPos offset = blockPos.offset(enumFacing2);
                if (!b2 || InteractionManager.mc.world.getBlockState(offset).getBlock() != Blocks.PISTON) {
                    if (InteractionManager.mc.world.getBlockState(offset).getBlock().canCollideCheck(InteractionManager.mc.world.getBlockState(offset), false)) {
                        if (!InteractionManager.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                            enumFacing = enumFacing2;
                            break Label_0275;
                        }
                    }
                }
                ++n;
                return null;
            }
        }
        if (enumFacing == null) {
            return Optional.empty();
        }
        final BlockPos offset2 = blockPos.offset(enumFacing);
        final EnumFacing getOpposite = enumFacing.getOpposite();
        if (!InteractionManager.mc.world.getBlockState(offset2).getBlock().canCollideCheck(InteractionManager.mc.world.getBlockState(offset2), false)) {
            return Optional.empty();
        }
        return Optional.of(new ClickLocation(offset2, getOpposite));
    }
    
    public InteractionManager() {
        this.attackTimer = new Timer();
    }
    
    public void attackCrystals(final BlockPos blockPos, final boolean b) {
        final boolean isSprinting = InteractionManager.mc.player.isSprinting();
        final int ping = Managers.SERVER.getPing();
        final Iterator<EntityEnderCrystal> iterator = InteractionManager.mc.world.getEntitiesWithinAABB((Class)EntityEnderCrystal.class, new AxisAlignedBB(blockPos)).iterator();
        if (iterator.hasNext()) {
            final EntityEnderCrystal entityEnderCrystal = iterator.next();
            if (!this.attackTimer.passedMs((ping <= 50) ? 75L : 100L)) {
                return;
            }
            if (b) {
                Managers.ROTATIONS.lookAtVec3dPacket(entityEnderCrystal.getPositionVector(), true);
            }
            if (isSprinting) {
                InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionManager.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)entityEnderCrystal));
            InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            if (isSprinting) {
                InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionManager.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            this.attackTimer.reset();
        }
    }
    
    public void placeBlock(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (fullNullCheck()) {
            return;
        }
        if (BlockUtil.canReplace(blockPos)) {
            if (b3) {
                this.attackCrystals(blockPos, b);
            }
            final Optional clickLocation = this.getClickLocation(blockPos, b4, false, b3);
            if (clickLocation.isPresent()) {
                final BlockPos neighbour = clickLocation.get().neighbour;
                final EnumFacing opposite = clickLocation.get().opposite;
                final boolean shouldShiftClick = this.shouldShiftClick(neighbour);
                if (shouldShiftClick) {
                    InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionManager.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                final Vec3d add = new Vec3d((Vec3i)neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
                if (b) {
                    Managers.ROTATIONS.lookAtVec3dPacket(add, true);
                }
                if (b2) {
                    final Vec3d add = new Vec3d((Vec3i)neighbour).add(0.5, 0.5, 0.5);
                    InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(neighbour, opposite, EnumHand.MAIN_HAND, (float)(add.x - neighbour.getX()), (float)(add.y - neighbour.getY()), (float)(add.z - neighbour.getZ())));
                }
                else {
                    InteractionManager.mc.playerController.processRightClickBlock(InteractionManager.mc.player, InteractionManager.mc.world, neighbour, opposite, add, EnumHand.MAIN_HAND);
                }
                PlaceRender.PlaceMap.put(blockPos, new PlaceRender.placePosition(blockPos));
                InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                if (shouldShiftClick) {
                    InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionManager.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
            }
        }
    }
    
    public void placeBlock(final BlockPos blockPos, final boolean b, final boolean b2, final boolean b3) {
        this.placeBlock(blockPos, b, b2, b3, false);
    }
    
    public void attackEntity(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            InteractionManager.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            InteractionManager.mc.playerController.attackEntity((EntityPlayer)InteractionManager.mc.player, entity);
        }
        if (b2) {
            InteractionManager.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public boolean shouldShiftClick(final BlockPos blockPos) {
        final Block getBlock = InteractionManager.mc.world.getBlockState(blockPos).getBlock();
        TileEntity tileEntity = null;
        final Iterator<TileEntity> iterator = InteractionManager.mc.world.loadedTileEntityList.iterator();
        if (iterator.hasNext()) {
            final TileEntity tileEntity2 = iterator.next();
            if (!tileEntity2.getPos().equals((Object)blockPos)) {
                return false;
            }
            tileEntity = tileEntity2;
        }
        return tileEntity != null || getBlock instanceof BlockBed || getBlock instanceof BlockContainer || getBlock instanceof BlockDoor || getBlock instanceof BlockTrapDoor || getBlock instanceof BlockFenceGate || getBlock instanceof BlockButton || getBlock instanceof BlockAnvil || getBlock instanceof BlockWorkbench || getBlock instanceof BlockCake || getBlock instanceof BlockRedstoneDiode;
    }
    
    public static class ClickLocation
    {
        public final BlockPos neighbour;
        public final EnumFacing opposite;
        
        public ClickLocation(final BlockPos neighbour, final EnumFacing opposite) {
            this.neighbour = neighbour;
            this.opposite = opposite;
        }
    }
}
