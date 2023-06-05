//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import org.lwjgl.input.*;
import net.minecraft.entity.monster.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public class EntityUtil implements Wrapper
{
    public static int getDamagePercent(final ItemStack itemStack) {
        return (int)((itemStack.getMaxDamage() - itemStack.getItemDamage()) / Math.max(0.1, itemStack.getMaxDamage()) * 100.0);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
    
    public static void facePlacePos(final BlockPos blockPos) {
        final EnumFacing firstFacing = BlockUtil.getFirstFacing(blockPos);
        if (firstFacing == null) {
            return;
        }
        faceVector(new Vec3d((Vec3i)blockPos.offset(firstFacing)).add(0.5, 0.5, 0.5).add(new Vec3d(firstFacing.getOpposite().getDirectionVec()).scale(0.5)));
    }
    
    public static boolean isArmorLow(final EntityPlayer entityPlayer, final int n) {
        final Iterator iterator = entityPlayer.inventory.armorInventory.iterator();
        if (iterator.hasNext()) {
            final ItemStack itemStack = iterator.next();
            return itemStack == null || getDamagePercent(itemStack) < n;
        }
        return false;
    }
    
    public static BlockPos getRoundedPos(final Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }
    
    public static void facePosFacing(final BlockPos blockPos, final EnumFacing enumFacing) {
        faceVector(new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5)));
    }
    
    public static List getVarOffsetList(final int n, final int n2, final int n3) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d((double)n, (double)n2, (double)n3));
        return list;
    }
    
    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static boolean isSafe(final Entity entity) {
        return isSafe(entity, 0, false);
    }
    
    public static float getHealth(final Entity entity) {
        if (isLiving(entity)) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static boolean isVehicle(final Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }
    
    public static List getOffsetList(final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        if (b) {
            list.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return list;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY + EntityUtil.mc.player.getEyeHeight(), EntityUtil.mc.player.posZ);
    }
    
    public static Vec3d[] getVarOffsets(final int n, final int n2, final int n3) {
        final List varOffsetList = getVarOffsetList(n, n2, n3);
        return varOffsetList.toArray(new Vec3d[varOffsetList.size()]);
    }
    
    public static boolean isFeetVisible(final Entity entity) {
        return EntityUtil.mc.world.rayTraceBlocks(new Vec3d(EntityUtil.mc.player.posX, EntityUtil.mc.player.posX + EntityUtil.mc.player.getEyeHeight(), EntityUtil.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }
    
    public static boolean isProjectile(final Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }
    
    public static boolean isEating() {
        return (EntityUtil.mc.player.isHandActive() && EntityUtil.mc.player.getActiveItemStack().getItem() instanceof ItemFood) || (EntityUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood && Mouse.isButtonDown(1));
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksList(entity, n, b).size() == 0;
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return isHostileMob(entity);
    }
    
    public static List getTrapOffsetList(final Vec3d vec3d, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        final ArrayList<Object> list = new ArrayList<Object>();
        if (!b2) {
            final List unsafeBlocksList = getUnsafeBlocksList(vec3d, 2, false);
            if (unsafeBlocksList.size() == 4) {
                final Iterator<Vec3d> iterator = unsafeBlocksList.iterator();
                if (iterator.hasNext()) {
                    final Vec3d vec3d2 = iterator.next();
                    switch (BlockUtil.getPlaceAbility(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z), b6)) {
                        case -1:
                        case 1:
                        case 2: {
                            return null;
                        }
                        case 3: {
                            list.add(vec3d.add(vec3d2));
                            break;
                        }
                    }
                    Collections.addAll(list, MathUtil.convertVectors(vec3d, getTrapOffsets(b, false, b3, b4, b5)));
                    return list;
                }
            }
        }
        Collections.addAll(list, MathUtil.convertVectors(vec3d, getTrapOffsets(b, b2, b3, b4, b5)));
        return list;
    }
    
    public static void faceYawAndPitch(final float n, final float n2) {
        sendPlayerRot(n, n2, EntityUtil.mc.player.onGround);
    }
    
    public static EntityPlayer getClosestEnemy(final double n) {
        final Entity entity = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)EntityUtil.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return (EntityPlayer)entity;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (n != 0) {
            return null;
        }
        if (entity == null) {
            return null;
        }
        if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer) >= EntityUtil.mc.player.getDistanceSq(entity)) {
            return null;
        }
        return null;
    }
    
    public static float getXYZYaw(final double n, final double n2, final double n3) {
        return MathUtil.calcAngle(EntityUtil.mc.player.getPositionEyes(EntityUtil.mc.getRenderPartialTicks()), new Vec3d(n, n2, n3))[0];
    }
    
    public static float getXYZPitch(final double n, final double n2, final double n3) {
        return MathUtil.calcAngle(EntityUtil.mc.player.getPositionEyes(EntityUtil.mc.getRenderPartialTicks()), new Vec3d(n, n2, n3))[1];
    }
    
    public static EntityPlayer getCopiedPlayer(final EntityPlayer entityPlayer) {
        final EntityPlayer entityPlayer2 = new EntityPlayer((World)EntityUtil.mc.world, new GameProfile(UUID.randomUUID(), entityPlayer.getName()), entityPlayer.getItemInUseCount()) {
            final int val$count;
            
            public boolean isSpectator() {
                return false;
            }
            
            public int getItemInUseCount() {
                return this.val$count;
            }
            
            public boolean isCreative() {
                return false;
            }
        };
        entityPlayer2.setSneaking(entityPlayer.isSneaking());
        entityPlayer2.swingProgress = entityPlayer.swingProgress;
        entityPlayer2.limbSwing = entityPlayer.limbSwing;
        entityPlayer2.limbSwingAmount = entityPlayer.prevLimbSwingAmount;
        entityPlayer2.inventory.copyInventory(entityPlayer.inventory);
        entityPlayer2.setPrimaryHand(entityPlayer.getPrimaryHand());
        entityPlayer2.ticksExisted = entityPlayer.ticksExisted;
        entityPlayer2.setEntityId(entityPlayer.getEntityId());
        entityPlayer2.copyLocationAndAnglesFrom((Entity)entityPlayer);
        return entityPlayer2;
    }
    
    public static boolean isInHole(final Entity entity) {
        return BlockUtil.isHole(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static void faceVector(final Vec3d vec) {
        final float[] legitRotations = getLegitRotations(vec);
        CombatSetting.vec = vec;
        CombatSetting.timer.reset();
        sendPlayerRot(legitRotations[0], legitRotations[1], EntityUtil.mc.player.onGround);
    }
    
    public static boolean stopSneaking(final boolean b) {
        if (b && EntityUtil.mc.player != null) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static List getUnsafeBlocksList(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksList(entity.getPositionVector(), n, b);
    }
    
    public static Vec3d[] getTrapOffsets(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>(getOffsetList(1, false));
        list.add(new Vec3d(0.0, 2.0, 0.0));
        if (b) {
            list.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (b2) {
            list.addAll(getOffsetList(2, false));
        }
        if (b3) {
            list.addAll(getOffsetList(0, false));
        }
        if (b4) {
            list.addAll(getOffsetList(-1, false));
            list.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (b5) {
            list.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return list.toArray(new Vec3d[list.size()]);
    }
    
    public static void sendPlayerRot(final float n, final float n2, final boolean b) {
        EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(n, n2, b));
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double n = vec3d.x - eyesPos.x;
        final double n2 = vec3d.y - eyesPos.y;
        final double n3 = vec3d.z - eyesPos.z;
        return new float[] { EntityUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(n3, n)) - 90.0f - EntityUtil.mc.player.rotationYaw), EntityUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(n2, Math.sqrt(n * n + n3 * n3)))) - EntityUtil.mc.player.rotationPitch) };
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static boolean isHoldingWeapon(final EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && entity == 0;
    }
    
    public static void faceXYZ(final double n, final double n2, final double n3) {
        faceYawAndPitch(getXYZYaw(n, n2, n3), getXYZPitch(n, n2, n3));
    }
    
    public static BlockPos getPlayerPos() {
        return getEntityPos((Entity)EntityUtil.mc.player);
    }
    
    public static Vec3d getEyePosition(final Entity entity) {
        return new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
    }
    
    public static BlockPos getEntityPos(final Entity entity) {
        return new BlockPos(entity.posX, entity.posY + 0.5, entity.posZ);
    }
    
    public static boolean isTrapped(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        return getUntrappedBlocks(entityPlayer, b, b2, b3, b4, b5).size() == 0;
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static List getUnsafeBlocksList(final Vec3d vec3d, final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final Vec3d[] offsets = getOffsets(n, b);
        final int length = offsets.length;
        int n2 = 0;
        if (n2 < length) {
            final Vec3d vec3d2 = offsets[n2];
            final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(vec3d2);
            }
            ++n2;
            return null;
        }
        return list;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List offsetList = getOffsetList(n, b);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec3d) {
        return getInterpolatedAmount(entity, vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static boolean isHolding32k(final EntityPlayer entityPlayer) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, entityPlayer.getHeldItemMainhand()) >= 1000;
    }
    
    public static boolean invalid(final Entity entity, final double n) {
        return entity == null || entity == 0 || entity.equals((Object)EntityUtil.mc.player) || (entity instanceof EntityPlayer && Managers.FRIENDS.isFriend(entity.getName())) || EntityUtil.mc.player.getDistanceSq(entity) > MathUtil.square(n);
    }
    
    public static int getHitCoolDown(final EntityPlayer entityPlayer) {
        final Item getItem = entityPlayer.getHeldItemMainhand().getItem();
        if (getItem instanceof ItemSword) {
            return 600;
        }
        if (getItem instanceof ItemPickaxe) {
            return 850;
        }
        if (getItem == Items.IRON_AXE) {
            return 1100;
        }
        if (getItem == Items.STONE_HOE) {
            return 500;
        }
        if (getItem == Items.IRON_HOE) {
            return 350;
        }
        if (getItem == Items.WOODEN_AXE || getItem == Items.STONE_AXE) {
            return 1250;
        }
        if (getItem instanceof ItemSpade || getItem == Items.GOLDEN_AXE || getItem == Items.DIAMOND_AXE || getItem == Items.WOODEN_HOE || getItem == Items.GOLDEN_HOE) {
            return 1000;
        }
        return 250;
    }
    
    public static boolean isInLiquid() {
        if (EntityUtil.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        final boolean b = false;
        final AxisAlignedBB axisAlignedBB = (EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().getEntityBoundingBox() : EntityUtil.mc.player.getEntityBoundingBox();
        final int n = (int)axisAlignedBB.minY;
        int floor = MathHelper.floor(axisAlignedBB.minX);
        if (floor >= MathHelper.floor(axisAlignedBB.maxX) + 1) {
            return b;
        }
        int floor2 = MathHelper.floor(axisAlignedBB.minZ);
        if (floor2 >= MathHelper.floor(axisAlignedBB.maxZ) + 1) {
            ++floor;
            return false;
        }
        final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(floor, n, floor2)).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid)) {
            return false;
        }
        ++floor2;
        return false;
    }
    
    public static List getUntrappedBlocks(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (!b2 && getUnsafeBlocksList((Entity)entityPlayer, 2, false).size() == 4) {
            list.addAll(getUnsafeBlocksList((Entity)entityPlayer, 2, false));
        }
        int n = 0;
        if (n < getTrapOffsets(b, b2, b3, b4, b5).length) {
            final Vec3d vec3d = getTrapOffsets(b, b2, b3, b4, b5)[n];
            final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(entityPlayer.getPositionVector()).add(vec3d.x, vec3d.y, vec3d.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(vec3d);
            }
            ++n;
            return null;
        }
        return list;
    }
}
