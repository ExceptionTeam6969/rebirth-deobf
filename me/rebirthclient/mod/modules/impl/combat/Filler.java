//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import java.util.*;

public class Filler extends Module
{
    private final Setting noInWeb;
    private final Setting allowUp;
    private final Setting holeRange;
    private final Setting onlyCanStand;
    private final Setting placeRange;
    private final Setting delay;
    private final Setting rotate;
    private final Setting packet;
    private final Setting holeCheck;
    private final Setting air;
    int progress;
    private final Setting maxSelfSpeed;
    private final Setting anyBlock;
    private final Setting multiPlace;
    private final Setting range;
    private final Timer timer;
    private final Setting check;
    public EntityPlayer target;
    private final Setting raytrace;
    private final Setting minTargetSpeed;
    private final Setting minSelfRange;
    private final Setting web;
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (Filler.mc.player.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5) > (float)this.placeRange.getValue()) {
            return;
        }
        if (Filler.mc.player.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5) <= (float)this.minSelfRange.getValue()) {
            return;
        }
        if ((boolean)this.holeCheck.getValue() && !CombatUtil.isHole(blockPos, (boolean)this.anyBlock.getValue(), (int)this.check.getValue(), (boolean)this.onlyCanStand.getValue())) {
            return;
        }
        if (blockPos == 0) {
            return;
        }
        final int currentItem = Filler.mc.player.inventory.currentItem;
        if ((boolean)this.web.getValue() && InventoryUtil.findHotbarClass((Class)BlockWeb.class) != -1) {
            InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockWeb.class));
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
        }
        else if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) != -1) {
            InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
        }
        this.timer.reset();
        ++this.progress;
    }
    
    public Filler() {
        super("Filler", "Automatically pave the road for the enemy", Category.COMBAT);
        this.timer = new Timer();
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 0, 8));
        this.delay = this.add(new Setting("Delay", 50, 0, 500));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.holeCheck = this.add(new Setting("HoleCheck", true).setParent());
        this.holeRange = this.add(new Setting("HoleRange", 2.0f, 0.5f, 3.0f, this::lambda$new$0));
        this.check = this.add(new Setting("Check", 3, 2, 4, this::lambda$new$1));
        this.anyBlock = this.add(new Setting("anyBlock", true, this::lambda$new$2));
        this.onlyCanStand = this.add(new Setting("OnlyCanStand", false, this::lambda$new$3));
        this.allowUp = this.add(new Setting("AllowUp", false, this::lambda$new$4));
        this.minSelfRange = this.add(new Setting("MinSelfRange", 2.0f, 1.0f, 4.0f, this::lambda$new$5));
        this.raytrace = this.add(new Setting("Raytrace", false));
        this.web = this.add(new Setting("Web", true));
        this.noInWeb = this.add(new Setting("NoInWeb", false));
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 6.0f));
        this.placeRange = this.add(new Setting("PlaceRange", 4.0f, 1.0f, 6.0f));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 20.0, 1.0, 30.0));
        this.minTargetSpeed = this.add(new Setting("MinTargetSpeed", 6.0, 0.0, 20.0));
        this.air = this.add(new Setting("SelfGround", false));
        this.progress = 0;
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.holeCheck.isOpen();
    }
    
    @Override
    public void onUpdate() {
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        this.progress = 0;
        if ((boolean)this.air.getValue() && !Filler.mc.player.onGround) {
            this.target = null;
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)Filler.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            this.target = null;
            return;
        }
        final boolean b = false;
        final Iterator iterator = Filler.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            if (!b) {
                this.target = null;
            }
            return;
        }
        final EntityPlayer target = iterator.next();
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (EntityUtil.invalid((Entity)target, (double)(float)this.range.getValue())) {
            return;
        }
        if (AutoWeb.isInWeb(target) && (boolean)this.noInWeb.getValue()) {
            return;
        }
        if (Managers.SPEED.getPlayerSpeed(target) < (double)this.minTargetSpeed.getValue()) {
            return;
        }
        this.target = target;
        if (this.holeCheck.getValue()) {
            final Iterator iterator2 = BlockUtil.getBox((float)this.holeRange.getValue() + 2.0f, EntityUtil.getEntityPos((Entity)target).down()).iterator();
            if (iterator2.hasNext()) {
                final BlockPos blockPos = iterator2.next();
                if (blockPos.getY() >= EntityUtil.getEntityPos((Entity)target).getY() && !(boolean)this.allowUp.getValue()) {
                    return;
                }
                if (blockPos.equals((Object)EntityUtil.getEntityPos((Entity)target))) {
                    return;
                }
                if (this.allowUp.getValue()) {
                    boolean b2 = false;
                    final EnumFacing[] values = EnumFacing.values();
                    final int length = values.length;
                    int n = 0;
                    Label_0439: {
                        if (n < length) {
                            final EnumFacing enumFacing = values[n];
                            if (enumFacing != EnumFacing.UP) {
                                if (enumFacing != EnumFacing.DOWN) {
                                    if (blockPos.equals((Object)EntityUtil.getEntityPos((Entity)target).offset(enumFacing))) {
                                        b2 = true;
                                        break Label_0439;
                                    }
                                }
                            }
                            ++n;
                            return;
                        }
                    }
                    if (b2) {
                        return;
                    }
                }
                if (target.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5) > (float)this.holeRange.getValue() && target.getDistance(blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), blockPos.getZ() + 0.5) > (float)this.holeRange.getValue()) {
                    return;
                }
                if (Filler.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) > (float)this.placeRange.getValue()) {
                    return;
                }
                this.placeBlock(blockPos);
            }
        }
        else {
            final BlockPos blockPos2 = new BlockPos(this.target.posX, this.target.posY + 0.5, this.target.posZ);
            this.placeBlock(blockPos2.down());
            final EnumFacing[] values2 = EnumFacing.values();
            final int length2 = values2.length;
            int n2 = 0;
            if (n2 < length2) {
                final EnumFacing enumFacing2 = values2[n2];
                if (enumFacing2 != EnumFacing.UP) {
                    if (enumFacing2 != EnumFacing.DOWN) {
                        this.placeBlock(blockPos2.offset(enumFacing2).down());
                    }
                }
                ++n2;
            }
        }
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.holeCheck.isOpen();
    }
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.holeCheck.isOpen();
    }
    
    private boolean lambda$new$5(final Float n) {
        return this.holeCheck.isOpen();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.holeCheck.isOpen();
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.holeCheck.isOpen();
    }
}
