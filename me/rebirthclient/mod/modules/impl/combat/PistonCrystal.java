//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.init.*;
import me.rebirthclient.api.util.*;

public class PistonCrystal extends Module
{
    private final Setting fire;
    private final Setting placeRange;
    private final Setting onlyGround;
    private final Setting updateDelay;
    private final Timer timer;
    private final Setting packet;
    private EntityPlayer target;
    private final Setting onlyStatic;
    private final Setting autoDisable;
    private final Setting range;
    private final Setting noEating;
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return PistonCrystal.mc.world.getBlockState(blockPos).getBlock();
    }
    
    public PistonCrystal() {
        super("PistonCrystal", "in strict", Category.COMBAT);
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.noEating = this.add(new Setting("NoEating", true));
        this.placeRange = this.add(new Setting("PlaceRange", 5.0f, 1.0f, 8.0f));
        this.range = this.add(new Setting("Range", 4.0f, 1.0f, 8.0f));
        this.packet = this.add(new Setting("Packet", true));
        this.fire = this.add(new Setting("Fire", true));
        this.onlyGround = this.add(new Setting("pauseAir", true));
        this.onlyStatic = this.add(new Setting("pauseMoving", true));
        this.updateDelay = this.add(new Setting("UpdateDelay", 100, 0, 500));
        this.target = null;
        this.timer = new Timer();
    }
    
    @Override
    public void onTick() {
        if ((boolean)this.autoDisable.getValue() && AutoTrap.INSTANCE.isOff()) {
            this.disable();
            return;
        }
        if ((boolean)this.noEating.getValue() && EntityUtil.isEating()) {
            return;
        }
        if (!this.timer.passedMs((long)(int)this.updateDelay.getValue())) {
            return;
        }
        if (PullCrystal.check((boolean)this.onlyStatic.getValue(), !PistonCrystal.mc.player.onGround, (boolean)this.onlyGround.getValue())) {
            return;
        }
        this.target = this.getTarget((float)this.range.getValue());
        if (this.target == null) {
            return;
        }
        this.timer.reset();
        final BlockPos entityPos = EntityUtil.getEntityPos((Entity)this.target);
        if (entityPos.up(0) != 0) {
            CombatUtil.attackCrystal(entityPos.up(0), true, true);
        }
        if (entityPos.up(1) != 0) {
            CombatUtil.attackCrystal(entityPos.up(1), true, true);
        }
        if (entityPos.up(2) != 0) {
            CombatUtil.attackCrystal(entityPos.up(2), true, true);
        }
        if (entityPos.up(2) != 0) {
            return;
        }
        if (entityPos.up() != 0) {
            return;
        }
        if (entityPos.up(2) != 0) {
            return;
        }
        if (entityPos.up() != 0) {
            return;
        }
        if (this < entityPos.up(2)) {
            return;
        }
        this.doPlacePiston(entityPos.up());
    }
    
    private EntityPlayer getTarget(final double n) {
        final EntityPlayer entityPlayer = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)PistonCrystal.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return entityPlayer;
        }
        final EntityPlayer entityPlayer2 = iterator.next();
        if (EntityUtil.invalid((Entity)entityPlayer2, n)) {
            return null;
        }
        if (this.getBlock(entityPlayer2.getPosition()) == Blocks.OBSIDIAN) {
            return null;
        }
        if (entityPlayer == null) {
            PistonCrystal.mc.player.getDistanceSq((Entity)entityPlayer2);
            return null;
        }
        if (PistonCrystal.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
            return null;
        }
        PistonCrystal.mc.player.getDistanceSq((Entity)entityPlayer2);
        return null;
    }
    
    public static void placeFire(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2) {
        final EnumFacing down = EnumFacing.DOWN;
        final BlockPos offset = blockPos.offset(down);
        final EnumFacing getOpposite = down.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            EntityUtil.faceVector(add);
        }
        PlaceRender.PlaceMap.put(blockPos, new PlaceRender.placePosition(blockPos));
        BlockUtil.rightClickBlock(offset, add, enumHand, getOpposite, b2);
    }
    
    private void doFire(final BlockPos blockPos, final EnumFacing enumFacing) {
        if (!(boolean)this.fire.getValue()) {
            return;
        }
        if (InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL) == -1) {
            return;
        }
        final int currentItem = PistonCrystal.mc.player.inventory.currentItem;
        final EnumFacing[] VALUES = EnumFacing.VALUES;
        final int length = VALUES.length;
        int n = 0;
        if (n < length) {
            final EnumFacing enumFacing2 = VALUES[n];
            if (enumFacing2 != EnumFacing.DOWN) {
                if (enumFacing2 != EnumFacing.UP) {
                    if (!blockPos.offset(enumFacing2).equals((Object)blockPos.offset(enumFacing))) {
                        if (PistonCrystal.mc.world.getBlockState(blockPos.offset(enumFacing2)).getBlock() == Blocks.FIRE) {
                            return;
                        }
                    }
                }
            }
            ++n;
            return;
        }
        final EnumFacing[] VALUES2 = EnumFacing.VALUES;
        final int length2 = VALUES2.length;
        int n2 = 0;
        if (n2 < length2) {
            final EnumFacing enumFacing3 = VALUES2[n2];
            if (enumFacing3 != EnumFacing.DOWN) {
                if (enumFacing3 != EnumFacing.UP) {
                    if (!blockPos.offset(enumFacing3).equals((Object)blockPos.offset(enumFacing))) {
                        if (!blockPos.offset(enumFacing3).equals((Object)blockPos.offset(enumFacing, -1))) {
                            if (blockPos.offset(enumFacing3) != 0) {
                                InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL));
                                placeFire(blockPos.offset(enumFacing3), EnumHand.MAIN_HAND, true, (boolean)this.packet.getValue());
                                InventoryUtil.doSwap(currentItem);
                                return;
                            }
                        }
                    }
                }
            }
            ++n2;
            return;
        }
        if (blockPos.offset(enumFacing, -1) != 0) {
            InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL));
            placeFire(blockPos.offset(enumFacing, -1), EnumHand.MAIN_HAND, true, (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
            return;
        }
        if (blockPos.offset(enumFacing, 1) != 0) {
            InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL));
            placeFire(blockPos.offset(enumFacing, 1), EnumHand.MAIN_HAND, true, (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
        }
    }
}
