//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import java.util.*;

public class PositionUtil implements Wrapper
{
    private static boolean inLiquid(final int n) {
        return findState(BlockLiquid.class, n) != null;
    }
    
    public static BlockPos fromBB(final AxisAlignedBB axisAlignedBB) {
        return new BlockPos((axisAlignedBB.minX + axisAlignedBB.maxX) / 2.0, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0, (axisAlignedBB.minZ + axisAlignedBB.maxZ) / 2.0);
    }
    
    public static boolean intersects(final AxisAlignedBB axisAlignedBB, final BlockPos blockPos) {
        return axisAlignedBB.intersects((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1));
    }
    
    public static Entity getPositionEntity() {
        final EntityPlayerSP player = PositionUtil.mc.player;
        Entity getRidingEntity;
        return (Entity)((player == null) ? null : (((getRidingEntity = player.getRidingEntity()) != null && !(getRidingEntity instanceof EntityBoat)) ? getRidingEntity : player));
    }
    
    public static BlockPos getPosition(final Entity entity) {
        return getPosition(entity, 0.0);
    }
    
    private static IBlockState findState(final Class clazz, final int n) {
        final Entity requirePositionEntity = requirePositionEntity();
        final int floor = MathHelper.floor(requirePositionEntity.getEntityBoundingBox().minX);
        final int floor2 = MathHelper.floor(requirePositionEntity.getEntityBoundingBox().minZ);
        final int ceil = MathHelper.ceil(requirePositionEntity.getEntityBoundingBox().maxX);
        final int ceil2 = MathHelper.ceil(requirePositionEntity.getEntityBoundingBox().maxZ);
        int n2 = floor;
        if (n2 >= ceil) {
            return null;
        }
        int n3 = floor2;
        if (n3 >= ceil2) {
            ++n2;
            return null;
        }
        final IBlockState getBlockState = PositionUtil.mc.world.getBlockState(new BlockPos(n2, n, n3));
        if (clazz.isInstance(getBlockState.getBlock())) {
            return getBlockState;
        }
        ++n3;
        return null;
    }
    
    public static boolean isMovementBlocked() {
        final IBlockState state = findState(Block.class, MathHelper.floor(PositionUtil.mc.player.getEntityBoundingBox().minY - 0.01));
        return state != null && state.getMaterial().blocksMovement();
    }
    
    public static Vec3d getEyePos(final Entity entity) {
        return new Vec3d(entity.posX, getEyeHeight(entity), entity.posZ);
    }
    
    public static Vec3d getEyePos() {
        return getEyePos((Entity)PositionUtil.mc.player);
    }
    
    public static boolean isAbove(final BlockPos blockPos) {
        return PositionUtil.mc.player.getEntityBoundingBox().minY >= blockPos.getY();
    }
    
    public static BlockPos getPosition(final Entity entity, final double n) {
        double ceil = entity.posY + n;
        if (entity.posY - Math.floor(entity.posY) > 0.5) {
            ceil = Math.ceil(entity.posY);
        }
        return new BlockPos(entity.posX, ceil, entity.posZ);
    }
    
    public static boolean inLiquid(final boolean b) {
        return inLiquid(MathHelper.floor(requirePositionEntity().getEntityBoundingBox().minY - (b ? 0.03 : 0.2)));
    }
    
    public static boolean inLiquid() {
        return inLiquid(MathHelper.floor(requirePositionEntity().getEntityBoundingBox().minY + 0.01));
    }
    
    public static boolean isBoxColliding() {
        return PositionUtil.mc.world.getCollisionBoxes((Entity)PositionUtil.mc.player, PositionUtil.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0;
    }
    
    public static Set getBlockedPositions(final Entity entity) {
        return getBlockedPositions(entity.getEntityBoundingBox());
    }
    
    public static Set getBlockedPositions(final AxisAlignedBB axisAlignedBB) {
        return getBlockedPositions(axisAlignedBB, 0.5);
    }
    
    public static double getEyeHeight() {
        return getEyeHeight((Entity)PositionUtil.mc.player);
    }
    
    public static Entity requirePositionEntity() {
        return Objects.requireNonNull(getPositionEntity());
    }
    
    public static double getEyeHeight(final Entity entity) {
        return entity.posY + entity.getEyeHeight();
    }
    
    public static Set getBlockedPositions(final AxisAlignedBB axisAlignedBB, final double n) {
        final HashSet<BlockPos> set = new HashSet<BlockPos>();
        double n2 = axisAlignedBB.minY;
        if (axisAlignedBB.minY - Math.floor(axisAlignedBB.minY) > n) {
            n2 = Math.ceil(axisAlignedBB.minY);
        }
        set.add(new BlockPos(axisAlignedBB.maxX, n2, axisAlignedBB.maxZ));
        set.add(new BlockPos(axisAlignedBB.minX, n2, axisAlignedBB.minZ));
        set.add(new BlockPos(axisAlignedBB.maxX, n2, axisAlignedBB.minZ));
        set.add(new BlockPos(axisAlignedBB.minX, n2, axisAlignedBB.maxZ));
        return set;
    }
}
