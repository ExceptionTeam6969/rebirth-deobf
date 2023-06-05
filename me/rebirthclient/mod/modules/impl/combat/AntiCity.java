//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.block.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;

public class AntiCity extends Module
{
    private final Setting multiPlace;
    private int progress;
    private final Setting rotate;
    private final Setting delay;
    private final Setting breakCrystal;
    private final Setting mode;
    private final Setting eatingPause;
    private final Setting packet;
    final Timer delayTimer;
    private final Setting onlySurround;
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.breakCrystal.isOpen();
    }
    
    @Override
    public void onTick() {
        if ((!Surround.INSTANCE.isOn() && (boolean)this.onlySurround.getValue()) || !this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        this.progress = 0;
        final BlockPos playerPos = EntityUtil.getPlayerPos();
        final EnumFacing[] VALUES = EnumFacing.VALUES;
        final int length = VALUES.length;
        int n = 0;
        if (n < length) {
            final EnumFacing enumFacing = VALUES[n];
            if (enumFacing != EnumFacing.DOWN) {
                if (enumFacing != EnumFacing.UP) {
                    if (this.getBlock(playerPos.offset(enumFacing)) == Blocks.OBSIDIAN && (BreakManager.isMine(playerPos.offset(enumFacing)) || BlockUtil.checkEntity(playerPos.offset(enumFacing)))) {
                        if (this.mode.getValue() == Mode.Single) {
                            this.placeBlock(playerPos.offset(enumFacing, 2));
                        }
                        else {
                            final EnumFacing[] VALUES2 = EnumFacing.VALUES;
                            final int length2 = VALUES2.length;
                            int n2 = 0;
                            if (n2 < length2) {
                                final EnumFacing enumFacing2 = VALUES2[n2];
                                if (enumFacing2 != EnumFacing.DOWN) {
                                    if (enumFacing2 != EnumFacing.UP) {
                                        this.placeBlock(playerPos.offset(enumFacing).offset(enumFacing2));
                                    }
                                }
                                ++n2;
                                return;
                            }
                            if (this.mode.getValue() == Mode.Full) {
                                this.placeBlock(playerPos.offset(enumFacing).up());
                            }
                        }
                    }
                }
            }
            ++n;
        }
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return AntiCity.mc.world.getBlockState(blockPos).getBlock();
    }
    
    public AntiCity() {
        super("AntiCity", "test", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.breakCrystal = this.add(new Setting("AttackCrystal", true).setParent());
        this.eatingPause = this.add(new Setting("EatingPause", true, this::lambda$new$0));
        this.onlySurround = this.add(new Setting("OnlySurround", true));
        this.delay = this.add(new Setting("Delay", 100, 0, 1000));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.mode = this.add(new Setting("Mode", Mode.Semi));
        this.delayTimer = new Timer();
        this.progress = 0;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (!BlockUtil.canPlace(blockPos)) {
            if (this.breakCrystal.getValue()) {
                CombatUtil.attackCrystal(blockPos, (boolean)this.rotate.getValue(), (boolean)this.eatingPause.getValue());
            }
            return;
        }
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        final int currentItem = AntiCity.mc.player.inventory.currentItem;
        if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) != -1) {
            InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
            this.delayTimer.reset();
            ++this.progress;
        }
    }
    
    public enum Mode
    {
        private static final Mode[] $VALUES;
        
        Full("Full", 2), 
        Single("Single", 0), 
        Semi("Semi", 1);
        
        static {
            $VALUES = new Mode[] { Mode.Single, Mode.Semi, Mode.Full };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
