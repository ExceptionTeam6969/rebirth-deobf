//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import java.util.*;

public class AutoWire extends Module
{
    private final Setting face;
    private final Setting checkDamage;
    boolean active;
    private final Setting packet;
    private final Setting maxSelfSpeed;
    private final Setting crystalRange;
    private final Setting rotate;
    private final Setting minDamage;
    private final Setting onlyGround;
    
    private boolean lambda$new$1(final Double n) {
        return this.checkDamage.isOpen();
    }
    
    @Override
    public String getInfo() {
        if (this.active) {
            return "Active";
        }
        return null;
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.checkDamage.isOpen();
    }
    
    public AutoWire() {
        super("AutoWire", "", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.onlyGround = this.add(new Setting("OnlyGround", true));
        this.face = this.add(new Setting("Face", true));
        this.checkDamage = this.add(new Setting("CheckDamage", true).setParent());
        this.crystalRange = this.add(new Setting("CrystalRange", 6.0f, 0.0f, 16.0f, this::lambda$new$0));
        this.minDamage = this.add(new Setting("MinDamage", 5.0, 0.0, 20.0, this::lambda$new$1));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 12.0, 1.0, 30.0));
        this.active = false;
    }
    
    @Override
    public void onTick() {
        if (InventoryUtil.findItemInHotbar(Items.STRING) == -1) {
            this.active = false;
            return;
        }
        if ((boolean)this.onlyGround.getValue() && (!AutoWire.mc.player.onGround || MovementUtil.isJumping())) {
            this.active = false;
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)AutoWire.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            this.active = false;
            return;
        }
        if (this.checkDamage.getValue()) {
            boolean b = true;
            final Iterator<Entity> iterator = (Iterator<Entity>)AutoWire.mc.world.loadedEntityList.iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (!(entity instanceof EntityEnderCrystal)) {
                    return;
                }
                if (AutoWire.mc.player.getDistance(entity) > (float)this.crystalRange.getValue()) {
                    return;
                }
                if (DamageUtil.calculateDamage(entity, (Entity)AutoWire.mc.player) <= (double)this.minDamage.getValue()) {
                    return;
                }
                b = false;
            }
            if (b) {
                this.active = false;
                return;
            }
        }
        this.active = true;
        if (BlockUtil.canBlockFacing(EntityUtil.getPlayerPos()) && AutoWire.mc.world.isAirBlock(EntityUtil.getPlayerPos())) {
            final int currentItem = AutoWire.mc.player.inventory.currentItem;
            if (InventoryUtil.findItemInHotbar(Items.STRING) == -1) {
                return;
            }
            InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.STRING));
            BlockUtil.placeBlock(EntityUtil.getPlayerPos(), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
        }
        if ((boolean)this.face.getValue() && BlockUtil.canBlockFacing(EntityUtil.getPlayerPos().up()) && AutoWire.mc.world.isAirBlock(EntityUtil.getPlayerPos().up())) {
            final int currentItem2 = AutoWire.mc.player.inventory.currentItem;
            if (InventoryUtil.findItemInHotbar(Items.STRING) == -1) {
                return;
            }
            InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.STRING));
            BlockUtil.placeBlock(EntityUtil.getPlayerPos().up(), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem2);
        }
    }
}
