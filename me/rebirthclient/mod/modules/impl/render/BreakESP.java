//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.util.*;
import me.rebirthclient.mod.modules.impl.misc.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import java.awt.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.render.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;

public class BreakESP extends Module
{
    private final Setting doubleRender;
    private final Setting outline;
    private final Setting outlineAlpha;
    private final Setting boxAlpha;
    private final Setting renderProgress;
    private final Setting animationMode;
    static final HashMap MineMap;
    private final Setting color;
    private final Setting range;
    public static BreakESP INSTANCE;
    private final Setting box;
    private final Setting animationTime;
    private final Setting renderName;
    private final Setting nameColor;
    private final Setting renderAir;
    private final Setting renderUnknown;
    private final Setting renderSelf;
    
    public String getColor() {
        switch ((TabFriends.FriendColor)this.nameColor.getValue()) {
            case White: {
                return "¡ìf";
            }
            case DarkRed: {
                return "¡ì4";
            }
            case Red: {
                return "¡ìc";
            }
            case Gold: {
                return "¡ì6";
            }
            case Yellow: {
                return "¡ìe";
            }
            case DarkGreen: {
                return "¡ì2";
            }
            case Green: {
                return "¡ìa";
            }
            case Aqua: {
                return "¡ìb";
            }
            case DarkAqua: {
                return "¡ì3";
            }
            case DarkBlue: {
                return "¡ì1";
            }
            case Blue: {
                return "¡ì9";
            }
            case LightPurple: {
                return "¡ìd";
            }
            case DarkPurple: {
                return "¡ì5";
            }
            case Gray: {
                return "¡ì7";
            }
            case DarkGray: {
                return "¡ì8";
            }
            case Black: {
                return "¡ì0";
            }
            default: {
                return "";
            }
        }
    }
    
    static Setting access$000(final BreakESP breakESP) {
        return breakESP.animationTime;
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.box.isOpen();
    }
    
    @SubscribeEvent
    public void BlockBreak(final DamageBlockEvent damageBlockEvent) {
        if (damageBlockEvent.getPosition().getY() == -1) {
            return;
        }
        final EntityPlayer entityPlayer = (EntityPlayer)BreakESP.mc.world.getEntityByID(damageBlockEvent.getBreakerId());
        if (entityPlayer == null || entityPlayer.getDistance(damageBlockEvent.getPosition().getX() + 0.5, (double)damageBlockEvent.getPosition().getY(), damageBlockEvent.getPosition().getZ() + 0.5) > 7.0) {
            return;
        }
        if (!BreakESP.MineMap.containsKey(entityPlayer)) {
            BreakESP.MineMap.put(entityPlayer, new MinePosition(entityPlayer));
        }
        ((MinePosition)BreakESP.MineMap.get(entityPlayer)).update(damageBlockEvent.getPosition());
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final EntityPlayer[] array = (EntityPlayer[])BreakESP.MineMap.keySet().toArray(new EntityPlayer[0]);
        final int length = array.length;
        int n = 0;
        if (n < length) {
            final EntityPlayer entityPlayer = array[n];
            if (entityPlayer != null && !entityPlayer.isEntityAlive()) {
                BreakESP.MineMap.remove(entityPlayer);
            }
            ++n;
            return;
        }
        BreakESP.MineMap.values().forEach(this::lambda$onRender3D$4);
    }
    
    static Setting access$100(final BreakESP breakESP) {
        return breakESP.renderAir;
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.outline.isOpen();
    }
    
    static {
        MineMap = new HashMap();
        BreakESP.INSTANCE = new BreakESP();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.renderName.isOpen();
    }
    
    public void draw(final BlockPos blockPos, final double n, final Color color) {
        if (this.animationMode.getValue() != Mode.Both) {
            AxisAlignedBB axisAlignedBB;
            if (this.animationMode.getValue() == Mode.InToOut) {
                axisAlignedBB = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos).grow(n / 2.0 - 0.5);
            }
            else if (this.animationMode.getValue() == Mode.Up) {
                final AxisAlignedBB getSelectedBoundingBox = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox.minX, getSelectedBoundingBox.minY, getSelectedBoundingBox.minZ, getSelectedBoundingBox.maxX, getSelectedBoundingBox.minY + (getSelectedBoundingBox.maxY - getSelectedBoundingBox.minY) * n, getSelectedBoundingBox.maxZ);
            }
            else if (this.animationMode.getValue() == Mode.Down) {
                final AxisAlignedBB getSelectedBoundingBox2 = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox2.minX, getSelectedBoundingBox2.maxY - (getSelectedBoundingBox2.maxY - getSelectedBoundingBox2.minY) * n, getSelectedBoundingBox2.minZ, getSelectedBoundingBox2.maxX, getSelectedBoundingBox2.maxY, getSelectedBoundingBox2.maxZ);
            }
            else if (this.animationMode.getValue() == Mode.None) {
                axisAlignedBB = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos);
            }
            else {
                final AxisAlignedBB grow = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos).grow(n / 2.0 - 0.5);
                final AxisAlignedBB getSelectedBoundingBox3 = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos);
                if (this.animationMode.getValue() == Mode.Horizontal) {
                    axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox3.minX, grow.minY, getSelectedBoundingBox3.minZ, getSelectedBoundingBox3.maxX, grow.maxY, getSelectedBoundingBox3.maxZ);
                }
                else {
                    axisAlignedBB = new AxisAlignedBB(grow.minX, getSelectedBoundingBox3.minY, grow.minZ, grow.maxX, getSelectedBoundingBox3.maxY, grow.maxZ);
                }
            }
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(axisAlignedBB, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(axisAlignedBB, color, (int)this.boxAlpha.getValue());
            }
        }
        else {
            final AxisAlignedBB grow2 = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos).grow(n / 2.0 - 0.5);
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow2, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow2, color, (int)this.boxAlpha.getValue());
            }
            final AxisAlignedBB grow3 = BreakESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)BreakESP.mc.world, blockPos).grow(-Math.abs(n / 2.0 - 1.0));
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow3, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow3, color, (int)this.boxAlpha.getValue());
            }
        }
    }
    
    private boolean lambda$new$0(final TabFriends.FriendColor friendColor) {
        return this.renderName.isOpen();
    }
    
    private void lambda$onRender3D$4(final MinePosition minePosition) {
        if (!(boolean)this.renderAir.getValue() && BreakESP.mc.world.isAirBlock(minePosition.first)) {
            minePosition.finishFirst();
        }
        if ((!minePosition.firstFinished || (boolean)this.renderAir.getValue()) && (minePosition.miner != BreakESP.mc.player || (boolean)this.renderSelf.getValue()) && minePosition.first.getDistance((int)BreakESP.mc.player.posX, (int)BreakESP.mc.player.posY, (int)BreakESP.mc.player.posZ) < (double)this.range.getValue() && (minePosition.miner != null || (boolean)this.renderUnknown.getValue())) {
            this.draw(minePosition.first, minePosition.firstFade.easeOutQuad(), (Color)this.color.getValue());
            if (this.renderName.getValue()) {
                final double n = minePosition.first.getX() - BreakESP.mc.getRenderManager().renderPosX + 0.5;
                final double n2 = minePosition.first.getY() - BreakESP.mc.getRenderManager().renderPosY - 1.0;
                final double n3 = minePosition.first.getZ() - BreakESP.mc.getRenderManager().renderPosZ + 0.5;
                RenderUtil.drawText(ChatFormatting.GRAY + ((minePosition.miner == null) ? (this.getColor() + "Unknown") : (this.getColor() + minePosition.miner.getName())), n, ((boolean)this.renderProgress.getValue()) ? (n2 + 0.15) : n2, n3, new Color(255, 255, 255, 255));
                if (this.renderProgress.getValue()) {
                    if (BreakESP.mc.world.isAirBlock(minePosition.first)) {
                        RenderUtil.drawText(ChatFormatting.GREEN + "Broke", n, n2 - 0.15, n3, new Color(255, 255, 255, 255));
                    }
                    else {
                        RenderUtil.drawText(ChatFormatting.GREEN + "Breaking", n, n2 - 0.15, n3, new Color(255, 255, 255, 255));
                    }
                }
            }
        }
        if ((minePosition.miner != BreakESP.mc.player || (boolean)this.renderSelf.getValue()) && !minePosition.secondFinished && minePosition.second != null) {
            if (BreakESP.mc.world.isAirBlock(minePosition.second)) {
                minePosition.finishSecond();
            }
            else if (!minePosition.second.equals((Object)minePosition.first) && minePosition.second.getDistance((int)BreakESP.mc.player.posX, (int)BreakESP.mc.player.posY, (int)BreakESP.mc.player.posZ) < (double)this.range.getValue() && (minePosition.miner != null || (boolean)this.renderUnknown.getValue()) && this.doubleRender.booleanValue) {
                this.draw(minePosition.second, minePosition.secondFade.easeOutQuad(), (Color)this.doubleRender.getValue());
                if (this.renderName.getValue()) {
                    final double n4 = minePosition.second.getX() - BreakESP.mc.getRenderManager().renderPosX + 0.5;
                    final double n5 = minePosition.second.getY() - BreakESP.mc.getRenderManager().renderPosY - 1.0;
                    final double n6 = minePosition.second.getZ() - BreakESP.mc.getRenderManager().renderPosZ + 0.5;
                    RenderUtil.drawText(ChatFormatting.GRAY + ((minePosition.miner == null) ? (this.getColor() + "Unknown") : (this.getColor() + minePosition.miner.getName())), n4, n5 + 0.15, n6, new Color(255, 255, 255, 255));
                    RenderUtil.drawText(ChatFormatting.GOLD + "Double", n4, n5 - 0.15, n6, new Color(255, 255, 255, 255));
                }
            }
        }
    }
    
    @Override
    public void onDisable() {
        BreakESP.MineMap.clear();
    }
    
    public BreakESP() {
        super("BreakESP", "Show mine postion", Category.RENDER);
        this.renderAir = this.add(new Setting("RenderAir", true));
        this.renderSelf = this.add(new Setting("OneSelf", true));
        this.renderUnknown = this.add(new Setting("RenderUnknown", true));
        this.range = this.add(new Setting("Range", 15.0, 0.0, 50.0));
        this.renderName = this.add(new Setting("RenderName", true).setParent());
        this.nameColor = this.add(new Setting("Color", TabFriends.FriendColor.Gray, this::lambda$new$0));
        this.renderProgress = this.add(new Setting("Progress", true, this::lambda$new$1).setParent());
        this.animationMode = this.add(new Setting("AnimationMode", Mode.Up));
        this.animationTime = this.add(new Setting("AnimationTime", 1000, 0, 5000));
        this.box = this.add(new Setting("Box", true).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 30, 0, 255, this::lambda$new$2));
        this.outline = this.add(new Setting("Outline", true).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 100, 0, 255, this::lambda$new$3));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255, 100)).hideAlpha());
        this.doubleRender = this.add(new Setting("Double", new Color(255, 255, 255, 100)).injectBoolean(true).hideAlpha());
        BreakESP.INSTANCE = this;
    }
    
    private static class MinePosition
    {
        public FadeUtils secondFade;
        public boolean firstFinished;
        public boolean secondFinished;
        public BlockPos second;
        public FadeUtils firstFade;
        public final EntityPlayer miner;
        public BlockPos first;
        
        public void finishFirst() {
            this.firstFinished = true;
        }
        
        public void update(final BlockPos blockPos) {
            if (this.first != null && this.first.equals((Object)blockPos) && (boolean)BreakESP.access$100(BreakESP.INSTANCE).getValue()) {
                return;
            }
            if (this.secondFinished || this.second == null) {
                this.second = blockPos;
                this.secondFinished = false;
                this.secondFade = new FadeUtils((long)(int)BreakESP.access$000(BreakESP.INSTANCE).getValue());
            }
            if (this.first == null || !this.first.equals((Object)blockPos) || this.firstFinished) {
                this.firstFade = new FadeUtils((long)(int)BreakESP.access$000(BreakESP.INSTANCE).getValue());
            }
            this.firstFinished = false;
            this.first = blockPos;
        }
        
        public void finishSecond() {
            this.secondFinished = true;
        }
        
        public MinePosition(final EntityPlayer miner) {
            this.firstFade = new FadeUtils((long)(int)BreakESP.access$000(BreakESP.INSTANCE).getValue());
            this.secondFade = new FadeUtils((long)(int)BreakESP.access$000(BreakESP.INSTANCE).getValue());
            this.miner = miner;
            this.secondFinished = true;
        }
    }
    
    public enum Mode
    {
        Vertical("Vertical", 4), 
        Horizontal("Horizontal", 5), 
        InToOut("InToOut", 2), 
        None("None", 6);
        
        private static final Mode[] $VALUES;
        
        Down("Down", 0), 
        Both("Both", 3), 
        Up("Up", 1);
        
        static {
            $VALUES = new Mode[] { Mode.Down, Mode.Up, Mode.InToOut, Mode.Both, Mode.Vertical, Mode.Horizontal, Mode.None };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
