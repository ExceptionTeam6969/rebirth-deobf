//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import java.util.function.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.item.*;

public class DamageUtil implements Wrapper
{
    private static RayTraceResult rayTraceBlocks(final World world, Vec3d vec3d, final Vec3d vec3d2, final BlockPos.MutableBlockPos mutableBlockPos, final BiPredicate biPredicate) {
        if (!Double.isNaN(vec3d.x) && !Double.isNaN(vec3d.y) && !Double.isNaN(vec3d.z) && !Double.isNaN(vec3d2.x) && !Double.isNaN(vec3d2.y) && !Double.isNaN(vec3d2.z)) {
            int floor = MathHelper.floor(vec3d.x);
            int floor2 = MathHelper.floor(vec3d.y);
            int floor3 = MathHelper.floor(vec3d.z);
            final int floor4 = MathHelper.floor(vec3d2.x);
            final int floor5 = MathHelper.floor(vec3d2.y);
            final int floor6 = MathHelper.floor(vec3d2.z);
            final IBlockState getBlockState = world.getBlockState((BlockPos)mutableBlockPos.setPos(floor, floor2, floor3));
            if (getBlockState.getBlock().canCollideCheck(getBlockState, false) && biPredicate.test(mutableBlockPos, getBlockState)) {
                return getBlockState.collisionRayTrace(world, (BlockPos)mutableBlockPos, vec3d, vec3d2);
            }
            int n = 20;
            while (n-- >= 0) {
                if (Double.isNaN(vec3d.x) || Double.isNaN(vec3d.y) || Double.isNaN(vec3d.z)) {
                    return null;
                }
                if (floor == floor4 && floor2 == floor5 && floor3 == floor6) {
                    return null;
                }
                final double n2 = vec3d2.x - vec3d.x;
                final double n3 = vec3d2.y - vec3d.y;
                final double n4 = vec3d2.z - vec3d.z;
                double n5 = 999.0;
                double n6 = 999.0;
                double n7 = 999.0;
                double n8 = 999.0;
                double n9 = 999.0;
                double n10 = 999.0;
                if (floor4 > floor) {
                    n5 = floor + 1.0;
                    n8 = (n5 - vec3d.x) / n2;
                }
                else if (floor4 < floor) {
                    n5 = floor;
                    n8 = (n5 - vec3d.x) / n2;
                }
                if (floor5 > floor2) {
                    n6 = floor2 + 1.0;
                    n9 = (n6 - vec3d.y) / n3;
                }
                else if (floor5 < floor2) {
                    n6 = floor2;
                    n9 = (n6 - vec3d.y) / n3;
                }
                if (floor6 > floor3) {
                    n7 = floor3 + 1.0;
                    n10 = (n7 - vec3d.z) / n4;
                }
                else if (floor6 < floor3) {
                    n7 = floor3;
                    n10 = (n7 - vec3d.z) / n4;
                }
                if (n8 == -0.0) {
                    n8 = -1.0E-4;
                }
                if (n9 == -0.0) {
                    n9 = -1.0E-4;
                }
                if (n10 == -0.0) {
                    n10 = -1.0E-4;
                }
                EnumFacing enumFacing;
                if (n8 < n9 && n8 < n10) {
                    enumFacing = ((floor4 > floor) ? EnumFacing.WEST : EnumFacing.EAST);
                    vec3d = new Vec3d(n5, vec3d.y + n3 * n8, vec3d.z + n4 * n8);
                }
                else if (n9 < n10) {
                    enumFacing = ((floor5 > floor2) ? EnumFacing.DOWN : EnumFacing.UP);
                    vec3d = new Vec3d(vec3d.x + n2 * n9, n6, vec3d.z + n4 * n9);
                }
                else {
                    enumFacing = ((floor6 > floor3) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                    vec3d = new Vec3d(vec3d.x + n2 * n10, vec3d.y + n3 * n10, n7);
                }
                floor = MathHelper.floor(vec3d.x) - ((enumFacing == EnumFacing.EAST) ? 1 : 0);
                floor2 = MathHelper.floor(vec3d.y) - ((enumFacing == EnumFacing.UP) ? 1 : 0);
                floor3 = MathHelper.floor(vec3d.z) - ((enumFacing == EnumFacing.SOUTH) ? 1 : 0);
                mutableBlockPos.setPos(floor, floor2, floor3);
                final IBlockState getBlockState2 = world.getBlockState((BlockPos)mutableBlockPos);
                if (!getBlockState2.getBlock().canCollideCheck(getBlockState2, false)) {
                    continue;
                }
                if (!biPredicate.test(mutableBlockPos, getBlockState2)) {
                    return null;
                }
                return getBlockState2.collisionRayTrace(world, (BlockPos)mutableBlockPos, vec3d, vec3d2);
            }
        }
        return null;
    }
    
    public static float calculateDamage(final BlockPos blockPos, final Entity entity) {
        return calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity);
    }
    
    public static int getItemDamage(final ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }
    
    public static float getBlastReduction(final EntityLivingBase entityLivingBase, final float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
            final float getDamageAfterAbsorb = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            int getEnchantmentModifierDamage = 0;
            try {
                getEnchantmentModifierDamage = EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage);
            }
            catch (Exception ex) {}
            float n2 = getDamageAfterAbsorb * (1.0f - MathHelper.clamp((float)getEnchantmentModifierDamage, 0.0f, 20.0f) / 25.0f);
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                n2 -= n2 / 4.0f;
            }
            return Math.max(n2, 0.0f);
        }
        return CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    }
    
    private static float getBlockDensity(final Vec3d vec3d, final AxisAlignedBB axisAlignedBB, final BlockPos.MutableBlockPos mutableBlockPos) {
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
        if (n8 > 1.0f) {
            return n6 / (float)n7;
        }
        final float n9 = 0.0f;
        if (n9 > 1.0f) {
            final float n10 = n8 + (float)n;
            return 0.0f;
        }
        final float n11 = 0.0f;
        if (n11 <= 1.0f) {
            if (rayTraceBlocks((World)DamageUtil.mc.world, new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * n8 + n4, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * n9, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * n11 + n5), vec3d, mutableBlockPos, DamageUtil::isResistant) == null) {
                ++n6;
            }
            ++n7;
            final float n12 = n11 + (float)n3;
            return 0.0f;
        }
        final float n13 = n9 + (float)n2;
        return 0.0f;
    }
    
    public static float getDifficultyMultiplier(final float n) {
        switch (DamageUtil.mc.world.getDifficulty()) {
            case PEACEFUL: {
                return 0.0f;
            }
            case EASY: {
                return Math.min(n / 2.0f + 1.0f, n);
            }
            case HARD: {
                return n * 1.5f;
            }
            default: {
                return n;
            }
        }
    }
    
    public static float getDamageMultiplied(final float n) {
        final int getId = DamageUtil.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    public static boolean canBreakWeakness() {
        int getAmplifier = 0;
        final PotionEffect getActivePotionEffect = DamageUtil.mc.player.getActivePotionEffect(MobEffects.STRENGTH);
        if (getActivePotionEffect != null) {
            getAmplifier = getActivePotionEffect.getAmplifier();
        }
        return !DamageUtil.mc.player.isPotionActive(MobEffects.WEAKNESS) || getAmplifier >= 1 || DamageUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || DamageUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe || DamageUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe || DamageUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemSpade;
    }
    
    public static boolean isNaked(final EntityPlayer entityPlayer) {
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack != null) {
                return itemStack.isEmpty() && false;
            }
        }
        return true;
    }
    
    public static float calculateDamage(final Entity entity, final Entity entity2) {
        return calculateDamage(entity.posX, entity.posY, entity.posZ, entity2);
    }
    
    public static float getDamageInPercent(final ItemStack itemStack) {
        return getItemDamage(itemStack) / (float)itemStack.getMaxDamage() * 100.0f;
    }
    
    public static boolean canTakeDamage(final boolean b) {
        return !DamageUtil.mc.player.capabilities.isCreativeMode && !b;
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final float n4 = 12.0f;
        final double n5 = entity.getDistance(n, n2, n3) / n4;
        final Vec3d vec3d = new Vec3d(n, n2, n3);
        double n6 = 0.0;
        try {
            n6 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception ex) {}
        final double n7 = (1.0 - n5) * n6;
        final float n8 = (float)(int)((n7 * n7 + n7) / 2.0 * 7.0 * n4 + 1.0);
        double n9 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n9 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n8), new Explosion((World)DamageUtil.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n9;
    }
    
    private static boolean isResistant(final BlockPos blockPos, final IBlockState blockState) {
        return !blockState.getMaterial().isLiquid() && blockState.getBlock().getExplosionResistance((World)DamageUtil.mc.world, blockPos, (Entity)null, (Explosion)null) >= 19.7;
    }
    
    public static int getRoundedDamage(final ItemStack itemStack) {
        return (int)getDamageInPercent(itemStack);
    }
    
    public static boolean hasDurability(final ItemStack itemStack) {
        final Item getItem = itemStack.getItem();
        return getItem instanceof ItemArmor || getItem instanceof ItemSword || getItem instanceof ItemTool || getItem instanceof ItemShield;
    }
    
    public static boolean isArmorLow(final EntityPlayer entityPlayer, final int n) {
        final Iterator iterator = entityPlayer.inventory.armorInventory.iterator();
        if (iterator.hasNext()) {
            final ItemStack itemStack = iterator.next();
            return itemStack == null || getItemDamage(itemStack) < n;
        }
        return false;
    }
}
