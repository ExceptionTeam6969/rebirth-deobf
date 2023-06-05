//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import me.rebirthclient.mod.modules.impl.combat.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;

public class CrystalUtil implements Wrapper
{
    private static final List valid;
    
    public static boolean rayTracePlace(final BlockPos blockPos) {
        if (CrystalBot.getInstance().directionMode.getValue() != CrystalBot.DirectionMode.VANILLA) {
            final double n = 0.05;
            final double n2 = 0.95;
            final Vec3d vec3d = new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.getEntityBoundingBox().minY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ);
            final double n3 = n;
            if (n3 > n2) {
                return false;
            }
            final double n4 = n;
            if (n4 > n2) {
                return false;
            }
            final double n5 = n;
            if (n5 <= n2) {
                final Vec3d add = new Vec3d((Vec3i)blockPos).add(n3, n4, n5);
                final double distanceTo = vec3d.distanceTo(add);
                if (!(boolean)CrystalBot.getInstance().strictDirection.getValue() || distanceTo <= (float)CrystalBot.getInstance().placeRange.getValue()) {
                    final double n6 = add.x - vec3d.x;
                    final double n7 = add.y - vec3d.y;
                    final double n8 = add.z - vec3d.z;
                    final double[] array = { MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(n8, n6)) - 90.0f), MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(n7, MathHelper.sqrt(n6 * n6 + n8 * n8))))) };
                    final float cos = MathHelper.cos((float)(-array[0] * 0.01745329238474369 - 3.1415927410125732));
                    final float sin = MathHelper.sin((float)(-array[0] * 0.01745329238474369 - 3.1415927410125732));
                    final float n9 = -MathHelper.cos((float)(-array[1] * 0.01745329238474369));
                    final Vec3d vec3d2 = new Vec3d((double)(sin * n9), (double)MathHelper.sin((float)(-array[1] * 0.01745329238474369)), (double)(cos * n9));
                    final RayTraceResult rayTraceBlocks = CrystalUtil.mc.world.rayTraceBlocks(vec3d, vec3d.add(vec3d2.x * distanceTo, vec3d2.y * distanceTo, vec3d2.z * distanceTo), false, false, false);
                    if (rayTraceBlocks != null && rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK) {
                        if (rayTraceBlocks.getBlockPos().equals((Object)blockPos)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }
        else {
            final EnumFacing[] values = EnumFacing.values();
            final int length = values.length;
            int n10 = 0;
            if (n10 >= length) {
                return false;
            }
            final EnumFacing enumFacing = values[n10];
            final Vec3d vec3d3 = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getDirectionVec().getX() * 0.5, blockPos.getY() + 0.5 + enumFacing.getDirectionVec().getY() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getDirectionVec().getZ() * 0.5);
            final RayTraceResult rayTraceBlocks2;
            if ((!(boolean)CrystalBot.getInstance().strictDirection.getValue() || CrystalUtil.mc.player.getPositionVector().add(0.0, (double)CrystalUtil.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d3) <= (float)CrystalBot.getInstance().placeRange.getValue()) && (rayTraceBlocks2 = CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), vec3d3, false, true, false)) != null && rayTraceBlocks2.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK) && rayTraceBlocks2.getBlockPos().equals((Object)blockPos)) {
                return true;
            }
            ++n10;
            return false;
        }
    }
    
    static {
        valid = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL);
    }
    
    public static int ping() {
        if (CrystalUtil.mc.getConnection() == null) {
            return 50;
        }
        if (CrystalUtil.mc.player == null) {
            return 50;
        }
        try {
            return CrystalUtil.mc.getConnection().getPlayerInfo(CrystalUtil.mc.player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException ex) {
            return 50;
        }
    }
    
    public static Vec3d getEntityPosVec(final Entity entity, final int n, final boolean b) {
        return entity.getPositionVector().add(getMotionVec(entity, n, b));
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final float n4 = 12.0f;
        final double n5 = getEntityPosVec(entity, ((int)CrystalBot.predictTicks.getValue() > 0) ? ((int)CrystalBot.predictTicks.getValue()) : 0).distanceTo(new Vec3d(n, n2, n3)) / n4;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n6 = 0.0;
        try {
            if (CrystalBot.terrainIgnore.getValue()) {
                n6 = getBlockDensity(vec3d, ((int)CrystalBot.predictTicks.getValue() > 0) ? entity.getEntityBoundingBox().offset(getMotionVec(entity, (int)CrystalBot.predictTicks.getValue())) : entity.getEntityBoundingBox());
            }
            else {
                n6 = entity.world.getBlockDensity(vec3d, ((int)CrystalBot.predictTicks.getValue() > 0) ? entity.getEntityBoundingBox().offset(getMotionVec(entity, (int)CrystalBot.predictTicks.getValue())) : entity.getEntityBoundingBox());
            }
        }
        catch (Exception ex) {}
        final double n7 = (1.0 - n5) * n6;
        final float n8 = (float)(int)((n7 * n7 + n7) / 2.0 * 7.0 * n4 + 1.0);
        double n9 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n9 = DamageUtil.getBlastReduction((EntityLivingBase)entity, DamageUtil.getDamageMultiplied(n8), new Explosion((World)CrystalUtil.mc.world, (Entity)CrystalUtil.mc.player, n, n2, n3, 6.0f, false, true));
        }
        return (float)n9;
    }
    
    public static float calculateDamage(final Vec3d vec3d, final Entity entity) {
        return calculateDamage(vec3d.x, vec3d.y, vec3d.z, entity);
    }
    
    public static float getBlockDensity(final Vec3d vec3d, final AxisAlignedBB axisAlignedBB) {
        final double n = 1.0 / ((axisAlignedBB.maxX - axisAlignedBB.minX) * 2.0 + 1.0);
        final double n2 = 1.0 / ((axisAlignedBB.maxY - axisAlignedBB.minY) * 2.0 + 1.0);
        final double n3 = 1.0 / ((axisAlignedBB.maxZ - axisAlignedBB.minZ) * 2.0 + 1.0);
        final double n4 = (1.0 - Math.floor(1.0 / n) * n) / 2.0;
        final double n5 = (1.0 - Math.floor(1.0 / n3) * n3) / 2.0;
        if (n < 0.0 || n2 < 0.0 || n3 < 0.0) {
            return 0.0f;
        }
        int n6 = 0;
        int n7 = 0;
        final float n8 = 0.0f;
        if (n8 > 0.5f) {
            return n6 / (float)n7;
        }
        final float n9 = 0.0f;
        if (n9 > 0.5f) {
            final float n10 = (float)(n8 + n);
            return 0.0f;
        }
        final float n11 = 0.0f;
        if (n11 <= 0.5f) {
            if (rayTraceBlocks(new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * n8 + n4, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * n9, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * n11 + n5), vec3d) == null) {
                ++n6;
            }
            ++n7;
            final float n12 = (float)(n11 + n3);
            return 0.0f;
        }
        final float n13 = (float)(n9 + n2);
        return 0.0f;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity, final int n4, final boolean b, final boolean b2) {
        final float n5 = 12.0f;
        final double n6 = getEntityPosVec(entity, Math.max(n4, 0), b).distanceTo(new Vec3d(n, n2, n3)) / n5;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n7 = 0.0;
        try {
            if (b2) {
                n7 = getBlockDensity(vec3d, (n4 > 0) ? entity.getEntityBoundingBox().offset(getMotionVec(entity, n4, b)) : entity.getEntityBoundingBox());
            }
            else {
                n7 = entity.world.getBlockDensity(vec3d, (n4 > 0) ? entity.getEntityBoundingBox().offset(getMotionVec(entity, n4, b)) : entity.getEntityBoundingBox());
            }
        }
        catch (Exception ex) {}
        final double n8 = (1.0 - n6) * n7;
        final float n9 = (float)(int)((n8 * n8 + n8) / 2.0 * 7.0 * n5 + 1.0);
        double n10 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n10 = DamageUtil.getBlastReduction((EntityLivingBase)entity, DamageUtil.getDamageMultiplied(n9), new Explosion((World)CrystalUtil.mc.world, (Entity)CrystalUtil.mc.player, n, n2, n3, 6.0f, false, true));
        }
        return (float)n10;
    }
    
    public static RayTraceResult rayTraceBlocks(final Vec3d vec3d, final Vec3d vec3d2) {
        return rayTraceBlocks(vec3d, vec3d2, false, false, false);
    }
    
    public static int getCrystalSlot() {
        int currentItem = -1;
        if (Wrapper.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            currentItem = Wrapper.mc.player.inventory.currentItem;
        }
        if (currentItem == -1) {
            int n = 0;
            if (n < 9) {
                if (Wrapper.mc.player.inventory.getStackInSlot(n).getItem() != Items.END_CRYSTAL) {
                    ++n;
                    return 0;
                }
                currentItem = n;
            }
        }
        return currentItem;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity, final int n, final boolean b, final boolean b2) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity, n, b, b2);
    }
    
    public static boolean isVisible(final Vec3d vec3d) {
        return CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.getEntityBoundingBox().minY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), vec3d) == null;
    }
    
    public static float calculateDamage(final EntityEnderCrystal entityEnderCrystal, final Entity entity) {
        return calculateDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, entity);
    }
    
    public static boolean rayTraceBreak(final double n, final double n2, final double n3) {
        return CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d(n, n2 + 1.8, n3), false, true, false) == null || CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d(n, n2 + 1.5, n3), false, true, false) == null || CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d(n, n2, n3), false, true, false) == null;
    }
    
    public static int getSwordSlot() {
        int currentItem = -1;
        if (Wrapper.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
            currentItem = Wrapper.mc.player.inventory.currentItem;
        }
        if (currentItem == -1) {
            int n = 0;
            if (n < 9) {
                if (Wrapper.mc.player.inventory.getStackInSlot(n).getItem() != Items.DIAMOND_SWORD) {
                    ++n;
                    return 0;
                }
                currentItem = n;
            }
        }
        return currentItem;
    }
    
    public static Vec3d getMotionVec(final Entity entity, final int n) {
        final double n2 = entity.posX - entity.prevPosX;
        final double n3 = entity.posZ - entity.prevPosZ;
        double n4 = 0.0;
        double n5 = 0.0;
        if (CrystalBot.collision.getValue()) {
            int n6 = 1;
            if (n6 <= n && CrystalUtil.mc.world.getBlockState(new BlockPos(entity.posX + n2 * n6, entity.posY, entity.posZ + n3 * n6)).getBlock() instanceof BlockAir) {
                final double n7 = n2 * n6;
                final double n8 = n3 * n6;
                ++n6;
                return null;
            }
        }
        else {
            n4 = n2 * n;
            n5 = n3 * n;
        }
        return new Vec3d(n4, 0.0, n5);
    }
    
    public static Vec3d getMotionVec(final Entity entity, final int n, final boolean b) {
        final double n2 = entity.posX - entity.prevPosX;
        final double n3 = entity.posZ - entity.prevPosZ;
        double n4 = 0.0;
        double n5 = 0.0;
        if (b) {
            int n6 = 1;
            if (n6 <= n && CrystalUtil.mc.world.getBlockState(new BlockPos(entity.posX + n2 * n6, entity.posY, entity.posZ + n3 * n6)).getBlock() instanceof BlockAir) {
                final double n7 = n2 * n6;
                final double n8 = n3 * n6;
                ++n6;
                return null;
            }
        }
        else {
            n4 = n2 * n;
            n5 = n3 * n;
        }
        return new Vec3d(n4, 0.0, n5);
    }
    
    public static RayTraceResult rayTraceBlocks(final Vec3d vec3d, final Vec3d vec3d2, final boolean b, final boolean b2, final boolean b3) {
        if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
            return null;
        }
        if (Double.isNaN(vec3d2.x) || Double.isNaN(vec3d2.y) || Double.isNaN(vec3d2.z)) {
            return null;
        }
        final int floor = MathHelper.floor(vec3d2.x);
        final int floor2 = MathHelper.floor(vec3d2.y);
        final int floor3 = MathHelper.floor(vec3d2.z);
        final int floor4 = MathHelper.floor(vec3d.x);
        final int floor5 = MathHelper.floor(vec3d.y);
        final int floor6 = MathHelper.floor(vec3d.z);
        final BlockPos blockPos = new BlockPos(floor4, floor5, floor6);
        IBlockState blockState = CrystalUtil.mc.world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (!CrystalUtil.valid.contains(block)) {
            block = Blocks.AIR;
            blockState = Blocks.AIR.getBlockState().getBaseState();
        }
        if ((!b2 || blockState.getCollisionBoundingBox((IBlockAccess)CrystalUtil.mc.world, blockPos) != Block.NULL_AABB) && block.canCollideCheck(blockState, b)) {
            return blockState.collisionRayTrace((World)CrystalUtil.mc.world, blockPos, vec3d, vec3d2);
        }
        final RayTraceResult rayTraceResult = null;
        int n = 200;
        if (n-- < 0) {
            return b3 ? rayTraceResult : null;
        }
        if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
            return null;
        }
        if (floor4 == floor && floor5 == floor2 && floor6 == floor3) {
            return b3 ? rayTraceResult : null;
        }
        boolean b4 = true;
        boolean b5 = true;
        boolean b6 = true;
        double n2 = 999.0;
        double n3 = 999.0;
        double n4 = 999.0;
        if (floor > floor4) {
            n2 = floor4 + 1.0;
        }
        else if (floor < floor4) {
            n2 = floor4 + 0.0;
        }
        else {
            b4 = false;
        }
        if (floor2 > floor5) {
            n3 = floor5 + 1.0;
        }
        else if (floor2 < floor5) {
            n3 = floor5 + 0.0;
        }
        else {
            b5 = false;
        }
        if (floor3 > floor6) {
            n4 = floor6 + 1.0;
        }
        else if (floor3 < floor6) {
            n4 = floor6 + 0.0;
        }
        else {
            b6 = false;
        }
        double n5 = 999.0;
        double n6 = 999.0;
        double n7 = 999.0;
        final double n8 = vec3d2.x - vec3d.x;
        final double n9 = vec3d2.y - vec3d.y;
        final double n10 = vec3d2.z - vec3d.z;
        if (b4) {
            n5 = (n2 - vec3d.x) / n8;
        }
        if (b5) {
            n6 = (n3 - vec3d.y) / n9;
        }
        if (b6) {
            n7 = (n4 - vec3d.z) / n10;
        }
        if (n5 == -0.0) {
            n5 = -1.0E-4;
        }
        if (n6 == -0.0) {
            n6 = -1.0E-4;
        }
        if (n7 == -0.0) {
            n7 = -1.0E-4;
        }
        EnumFacing enumFacing;
        Vec3d vec3d3;
        if (n5 < n6 && n5 < n7) {
            enumFacing = ((floor > floor4) ? EnumFacing.WEST : EnumFacing.EAST);
            vec3d3 = new Vec3d(n2, vec3d.y + n9 * n5, vec3d.z + n10 * n5);
        }
        else if (n6 < n7) {
            enumFacing = ((floor2 > floor5) ? EnumFacing.DOWN : EnumFacing.UP);
            vec3d3 = new Vec3d(vec3d.x + n8 * n6, n3, vec3d.z + n10 * n6);
        }
        else {
            enumFacing = ((floor3 > floor6) ? EnumFacing.NORTH : EnumFacing.SOUTH);
            vec3d3 = new Vec3d(vec3d.x + n8 * n7, vec3d.y + n9 * n7, n4);
        }
        final BlockPos blockPos2 = new BlockPos(MathHelper.floor(vec3d3.x) - ((enumFacing == EnumFacing.EAST) ? 1 : 0), MathHelper.floor(vec3d3.y) - ((enumFacing == EnumFacing.UP) ? 1 : 0), MathHelper.floor(vec3d3.z) - ((enumFacing == EnumFacing.SOUTH) ? 1 : 0));
        IBlockState blockState2 = CrystalUtil.mc.world.getBlockState(blockPos2);
        Block block2 = blockState2.getBlock();
        if (!CrystalUtil.valid.contains(block2)) {
            block2 = Blocks.AIR;
            blockState2 = Blocks.AIR.getBlockState().getBaseState();
        }
        if (!b2 || blockState2.getMaterial() == Material.PORTAL || blockState2.getCollisionBoundingBox((IBlockAccess)CrystalUtil.mc.world, blockPos2) != Block.NULL_AABB) {
            if (block2.canCollideCheck(blockState2, b)) {
                return blockState2.collisionRayTrace((World)CrystalUtil.mc.world, blockPos2, vec3d3, vec3d2);
            }
            final RayTraceResult rayTraceResult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, enumFacing, blockPos2);
        }
        return null;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity);
    }
    
    public static Vec3d getEntityPosVec(final Entity entity, final int n) {
        return entity.getPositionVector().add(getMotionVec(entity, n));
    }
    
    public static float calculateDamage(final EntityEnderCrystal entityEnderCrystal, final Entity entity, final int n, final boolean b, final boolean b2) {
        return calculateDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, entity, n, b, b2);
    }
}
