//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.impl.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import me.rebirthclient.mod.modules.impl.player.*;
import net.minecraft.entity.passive.*;

public class NewStep extends Module
{
    public Setting useTimer;
    public Setting entityStep;
    public Setting stepDelay;
    private final Timer stepTimer;
    private final Setting onlyMoving;
    private final Setting pauseSneak;
    private final Setting pauseBurrow;
    public static boolean timer;
    private final Setting mode;
    private Entity entityRiding;
    public Setting height;
    private final Setting pauseWeb;
    public Setting strict;
    
    @SubscribeEvent
    public void onStep(final StepEvent stepEvent) {
        if (((Mode)this.mode.getValue()).equals(Mode.NORMAL)) {
            final double n = stepEvent.getAxisAlignedBB().minY - NewStep.mc.player.posY;
            if (n <= 0.0 || n > (float)this.height.getValue()) {
                return;
            }
            final double[] offset = this.getOffset(n);
            if (offset != null && offset.length > 1) {
                if (this.useTimer.getValue()) {
                    Managers.TIMER.timer = 1.0f / offset.length;
                    NewStep.timer = true;
                }
                final double[] array = offset;
                final int length = array.length;
                int n2 = 0;
                if (n2 < length) {
                    NewStep.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(NewStep.mc.player.posX, NewStep.mc.player.posY + array[n2], NewStep.mc.player.posZ, false));
                    ++n2;
                    return;
                }
            }
            this.stepTimer.reset();
        }
    }
    
    public double[] getOffset(final double n) {
        if (n == 0.75) {
            if (this.strict.getValue()) {
                return new double[] { 0.42, 0.753, 0.75 };
            }
            return new double[] { 0.42, 0.753 };
        }
        else if (n == 0.8125) {
            if (this.strict.getValue()) {
                return new double[] { 0.39, 0.7, 0.8125 };
            }
            return new double[] { 0.39, 0.7 };
        }
        else if (n == 0.875) {
            if (this.strict.getValue()) {
                return new double[] { 0.39, 0.7, 0.875 };
            }
            return new double[] { 0.39, 0.7 };
        }
        else if (n == 1.0) {
            if (this.strict.getValue()) {
                return new double[] { 0.42, 0.753, 1.0 };
            }
            return new double[] { 0.42, 0.753 };
        }
        else {
            if (n == 1.5) {
                return new double[] { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
            }
            if (n == 2.0) {
                return new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
            }
            if (n == 2.5) {
                return new double[] { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
            }
            return null;
        }
    }
    
    public NewStep() {
        super("NewStep", "\u890f\u82af\u5199\u61c8\u890c\u891c \u950c\u82af \u659c\u8c22\u82af\u6cfb\u90aa\u5c51 1 \u61c8\u8c22\u61c8 2 \u659c\u8c22\u82af\u6cfb\u90aa", Category.MOVEMENT);
        this.stepTimer = new Timer();
        this.height = this.register(new Setting("Height", 2.0f, 1.0f, 2.5f));
        this.entityStep = this.add(new Setting("EntityStep", false));
        this.useTimer = this.add(new Setting("Timer", true));
        this.strict = this.add(new Setting("Strict", false));
        this.pauseBurrow = this.add(new Setting("PauseBurrow", true));
        this.pauseSneak = this.add(new Setting("PauseSneak", true));
        this.pauseWeb = this.add(new Setting("PauseWeb", true));
        this.onlyMoving = this.add(new Setting("OnlyMoving", true));
        this.stepDelay = this.register(new Setting("StepDelay", 200, 0, 1000));
        this.mode = this.add(new Setting("Mode", Mode.NORMAL));
    }
    
    @Override
    public void onUpdate() {
        if ((NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.PISTON_HEAD || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.OBSIDIAN || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.ENDER_CHEST || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.BEDROCK) && (boolean)this.pauseBurrow.getValue()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if ((NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.PISTON_HEAD || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.OBSIDIAN || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.ENDER_CHEST || NewStep.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.BEDROCK) && (boolean)this.pauseBurrow.getValue()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if ((boolean)this.pauseWeb.getValue() && NewStep.mc.player.isInWeb) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if (SneakManager.isSneaking && (boolean)this.pauseSneak.getValue()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if ((boolean)this.onlyMoving.getValue() && !MovementUtil.isMoving() && HoleSnap.INSTANCE.isOff()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if (Clip.INSTANCE.isOn()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if (NewStep.mc.player.capabilities.isFlying || Freecam.INSTANCE.isOn()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if (EntityUtil.isInLiquid()) {
            NewStep.mc.player.stepHeight = 0.6f;
            return;
        }
        if (NewStep.timer && NewStep.mc.player.onGround) {
            Managers.TIMER.timer = 1.0f;
            NewStep.timer = false;
        }
        if (NewStep.mc.player.onGround && this.stepTimer.passedMs((long)(int)this.stepDelay.getValue())) {
            if (NewStep.mc.player.isRiding() && NewStep.mc.player.getRidingEntity() != null) {
                this.entityRiding = NewStep.mc.player.getRidingEntity();
                if (this.entityStep.getValue()) {
                    NewStep.mc.player.getRidingEntity().stepHeight = (float)this.height.getValue();
                }
            }
            else {
                NewStep.mc.player.stepHeight = (float)this.height.getValue();
            }
        }
        else if (NewStep.mc.player.isRiding() && NewStep.mc.player.getRidingEntity() != null) {
            this.entityRiding = NewStep.mc.player.getRidingEntity();
            if (this.entityRiding != null) {
                if (this.entityRiding instanceof EntityHorse || this.entityRiding instanceof EntityLlama || this.entityRiding instanceof EntityMule || (this.entityRiding instanceof EntityPig && this.entityRiding.isBeingRidden() && ((EntityPig)this.entityRiding).canBeSteered())) {
                    this.entityRiding.stepHeight = 1.0f;
                }
                else {
                    this.entityRiding.stepHeight = 0.5f;
                }
            }
        }
        else {
            NewStep.mc.player.stepHeight = 0.6f;
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        NewStep.mc.player.stepHeight = 0.6f;
        if (this.entityRiding != null) {
            if (this.entityRiding instanceof EntityHorse || this.entityRiding instanceof EntityLlama || this.entityRiding instanceof EntityMule || (this.entityRiding instanceof EntityPig && this.entityRiding.isBeingRidden() && ((EntityPig)this.entityRiding).canBeSteered())) {
                this.entityRiding.stepHeight = 1.0f;
            }
            else {
                this.entityRiding.stepHeight = 0.5f;
            }
        }
    }
    
    public enum Mode
    {
        VANILLA("VANILLA", 1);
        
        private static final Mode[] $VALUES;
        
        NORMAL("NORMAL", 0);
        
        static {
            $VALUES = new Mode[] { Mode.NORMAL, Mode.VANILLA };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
