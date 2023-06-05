//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.api.util.shaders.impl.fill.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.shaders.impl.outline.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;

public class Shaders extends Module
{
    public Setting WaveLenghtFIll;
    public Setting PI;
    public Setting alphaFill;
    public Setting colorESP;
    public Setting saturationOutline;
    public Setting stepSizeFill;
    public Setting volumStepsFill;
    public Setting maxEntities;
    public Setting MaxIterFill;
    public Setting radOutline;
    public Setting GSTARTOutline;
    public Setting NUM_OCTAVESFill;
    private final Setting fillShader;
    private final Setting player;
    private final Setting fadeOutline;
    public final Setting crystal;
    public Setting greenFill;
    private final Setting xpOrb;
    public Setting stepSizeOutline;
    private final Setting glowESP;
    public Setting moreGradientFill;
    private final Setting default1;
    private final Setting fadeFill;
    public Setting duplicateFill;
    public Setting formuparam2Outline;
    public Setting zoomOutline;
    public Setting tauOutline;
    public Setting WaveLenghtOutline;
    public Setting colorImgFill;
    public Setting RSTARTOutline;
    public Setting NUM_OCTAVESOutline;
    public Setting MaxIterOutline;
    public Setting titleOutline;
    private final Setting enderPearl;
    public static Shaders INSTANCE;
    public Setting zoomFill;
    public Setting blueFill;
    public Setting PIOutline;
    public Setting duplicateOutline;
    public Setting speedFill;
    public Setting thirdColorImgFIll;
    private final Setting xpBottle;
    public Setting greenOutline;
    public Setting GSTARTFill;
    public Setting BSTARTOutline;
    private final Setting items;
    public Setting secondColorImgFill;
    private final Setting mob;
    public Setting creepyFill;
    public Setting RSTARTFill;
    public Setting thirdColorImgOutline;
    public Setting tauFill;
    public Setting redFill;
    public Setting speedOutline;
    public Setting volumStepsOutline;
    public Setting BSTARTFIll;
    public Setting iterationsFill;
    public Setting blueOutline;
    public Setting redOutline;
    public boolean notShader;
    public Setting moreGradientOutline;
    public Setting distfadingFill;
    public Setting iterationsOutline;
    public Setting titleFill;
    public Setting colorImgOutline;
    public Setting minRange;
    public Setting alphaValue;
    public Setting saturationFill;
    public Setting formuparam2Fill;
    public Setting quality;
    public Setting radius;
    public Setting alphaOutline;
    public Setting distfadingOutline;
    public Setting maxRange;
    private final Setting Fpreset;
    public Setting rad;
    private final Setting rangeCheck;
    public Setting creepyOutline;
    
    private boolean lambda$new$44(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$5(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Circle;
    }
    
    private boolean lambda$new$25(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.Smoke;
    }
    
    private boolean lambda$new$31(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral || this.fillShader.getValue() == fillShadermode.Smoke;
    }
    
    private boolean lambda$new$38(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$42(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral || this.glowESP.getValue() == glowESPmode.Gradient;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.fillShader.getValue() == fillShadermode.Astral || this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$26(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.RainbowCube;
    }
    
    private boolean lambda$new$30(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$29(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.RainbowCube;
    }
    
    private boolean lambda$new$22(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$15(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$10(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private static void lambda$renderPlayersFill$56(final float n, final Entity entity) {
        Shaders.mc.getRenderManager().renderEntityStatic(entity, n, true);
    }
    
    private boolean lambda$new$32(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$34(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Aqua;
    }
    
    private boolean lambda$new$43(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$53(final Color color) {
        return this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke;
    }
    
    private boolean lambda$new$36(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Smoke;
    }
    
    void getFill() {
        switch ((fillShadermode)this.fillShader.getValue()) {
            case Astral: {
                FlowShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Aqua: {
                AquaShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Smoke: {
                SmokeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case RainbowCube: {
                RainbowCubeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Gradient: {
                GradientShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Fill: {
                FillShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Circle: {
                CircleShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
            case Phobos: {
                PhobosShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                break;
            }
        }
    }
    
    @Override
    public void onTick() {
        if (this.Fpreset.getValue()) {
            this.fillShader.setValue(fillShadermode.None);
            this.glowESP.setValue(glowESPmode.Gradient);
            this.player.setValue(Player1.Outline);
            this.crystal.setValue(Crystal1.Outline);
            this.duplicateOutline.setValue(2.0f);
            this.speedOutline.setValue(30.0f);
            this.quality.setValue(0.6f);
            this.radius.setValue(1.7f);
            this.creepyOutline.setValue(1.0f);
            this.moreGradientOutline.setValue(1.0f);
            this.Fpreset.setValue(false);
        }
        if (this.default1.getValue()) {
            this.fillShader.setValue(fillShadermode.None);
            this.glowESP.setValue(glowESPmode.None);
            this.rangeCheck.setValue(true);
            this.maxRange.setValue(35.0f);
            this.minRange.setValue(0.0f);
            this.crystal.setValue(Crystal1.None);
            this.player.setValue(Player1.None);
            this.mob.setValue(Mob1.None);
            this.items.setValue(Itemsl.None);
            this.fadeFill.setValue(false);
            this.fadeOutline.setValue(false);
            this.duplicateOutline.setValue(1.0f);
            this.duplicateFill.setValue(1.0f);
            this.speedOutline.setValue(10.0f);
            this.speedFill.setValue(10.0f);
            this.quality.setValue(1.0f);
            this.radius.setValue(1.0f);
            this.rad.setValue(0.75f);
            this.PI.setValue(3.1415927f);
            this.saturationFill.setValue(0.4f);
            this.distfadingFill.setValue(0.56f);
            this.titleFill.setValue(0.45f);
            this.stepSizeFill.setValue(0.2f);
            this.volumStepsFill.setValue(10.0f);
            this.zoomFill.setValue(3.9f);
            this.formuparam2Fill.setValue(0.89f);
            this.saturationOutline.setValue(0.4f);
            this.maxEntities.setValue(100);
            this.iterationsFill.setValue(4);
            this.redFill.setValue(0);
            this.MaxIterFill.setValue(5);
            this.NUM_OCTAVESFill.setValue(5);
            this.BSTARTFIll.setValue(0);
            this.GSTARTFill.setValue(0);
            this.RSTARTFill.setValue(0);
            this.WaveLenghtFIll.setValue(555);
            this.volumStepsOutline.setValue(10);
            this.iterationsOutline.setValue(4);
            this.MaxIterOutline.setValue(5);
            this.NUM_OCTAVESOutline.setValue(5);
            this.BSTARTOutline.setValue(0);
            this.GSTARTOutline.setValue(0);
            this.RSTARTOutline.setValue(0);
            this.alphaValue.setValue(255);
            this.WaveLenghtOutline.setValue(555);
            this.redOutline.setValue(0);
            this.alphaFill.setValue(1.0f);
            this.blueFill.setValue(0.0f);
            this.greenFill.setValue(0.0f);
            this.tauFill.setValue(6.2831855f);
            this.creepyFill.setValue(1.0f);
            this.moreGradientFill.setValue(1.0f);
            this.distfadingOutline.setValue(0.56f);
            this.titleOutline.setValue(0.45f);
            this.stepSizeOutline.setValue(0.19f);
            this.zoomOutline.setValue(3.9f);
            this.formuparam2Outline.setValue(0.89f);
            this.alphaOutline.setValue(1.0f);
            this.blueOutline.setValue(0.0f);
            this.greenOutline.setValue(0.0f);
            this.tauOutline.setValue(0.0f);
            this.creepyOutline.setValue(1.0f);
            this.moreGradientOutline.setValue(1.0f);
            this.radOutline.setValue(0.75f);
            this.PIOutline.setValue(3.1415927f);
            this.default1.setValue(false);
        }
    }
    
    private boolean lambda$new$12(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private static boolean lambda$renderPlayersFill$55(final boolean b, final double n, final double n2, final Entity entity) {
        if (!b) {
            return true;
        }
        final double getDistanceSq = Shaders.mc.player.getDistanceSq(entity);
        return getDistanceSq > n && getDistanceSq < n2;
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            if (Shaders.mc.world == null || Shaders.mc.player == null) {
                return;
            }
            GlStateManager.pushMatrix();
            this.notShader = false;
            final Color color = new Color(((Color)this.colorImgFill.getValue()).getRed(), ((Color)this.colorImgFill.getValue()).getGreen(), ((Color)this.colorImgFill.getValue()).getBlue(), ((Color)this.colorImgFill.getValue()).getAlpha());
            final Color color2 = new Color(((Color)this.colorESP.getValue()).getRed(), ((Color)this.colorESP.getValue()).getGreen(), ((Color)this.colorESP.getValue()).getBlue(), ((Color)this.colorESP.getValue()).getAlpha());
            final Color color3 = new Color(((Color)this.secondColorImgFill.getValue()).getRed(), ((Color)this.secondColorImgFill.getValue()).getGreen(), ((Color)this.secondColorImgFill.getValue()).getBlue(), ((Color)this.secondColorImgFill.getValue()).getAlpha());
            final Color color4 = new Color(((Color)this.thirdColorImgOutline.getValue()).getRed(), ((Color)this.thirdColorImgOutline.getValue()).getGreen(), ((Color)this.thirdColorImgOutline.getValue()).getBlue(), ((Color)this.thirdColorImgOutline.getValue()).getAlpha());
            final Color color5 = new Color(((Color)this.thirdColorImgFIll.getValue()).getRed(), ((Color)this.thirdColorImgFIll.getValue()).getGreen(), ((Color)this.thirdColorImgFIll.getValue()).getBlue(), ((Color)this.thirdColorImgFIll.getValue()).getAlpha());
            final Color color6 = new Color(((Color)this.colorImgOutline.getValue()).getRed(), ((Color)this.colorImgOutline.getValue()).getGreen(), ((Color)this.colorImgOutline.getValue()).getBlue(), ((Color)this.colorImgOutline.getValue()).getAlpha());
            if (this.glowESP.getValue() != glowESPmode.None && this.fillShader.getValue() != fillShadermode.None) {
                this.getFill();
                switch ((fillShadermode)this.fillShader.getValue()) {
                    case Astral: {
                        FlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FlowShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.redFill.getValue(), (float)this.greenFill.getValue(), (float)this.blueFill.getValue(), (float)this.alphaFill.getValue(), (int)this.iterationsFill.getValue(), (float)this.formuparam2Fill.getValue(), (float)this.zoomFill.getValue(), (float)this.volumStepsFill.getValue(), (float)this.stepSizeFill.getValue(), (float)this.titleFill.getValue(), (float)this.distfadingFill.getValue(), (float)this.saturationFill.getValue(), 0.0f, (int)(((boolean)this.fadeFill.getValue()) ? 1 : 0));
                        FlowShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        AquaShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        AquaShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        SmokeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, color3, color5, (int)this.NUM_OCTAVESFill.getValue());
                        SmokeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, (int)this.WaveLenghtFIll.getValue(), (int)this.RSTARTFill.getValue(), (int)this.GSTARTFill.getValue(), (int)this.BSTARTFIll.getValue());
                        RainbowCubeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        GradientShader.INSTANCE.stopDraw(color2, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.moreGradientFill.getValue(), (float)this.creepyFill.getValue(), (float)this.alphaFill.getValue(), (int)this.NUM_OCTAVESFill.getValue());
                        GradientShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Fill: {
                        FillShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FillShader.INSTANCE.stopDraw(color);
                        FillShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        CircleShader.INSTANCE.stopDraw((float)this.duplicateFill.getValue(), color, (Float)this.PI.getValue(), (Float)this.rad.getValue());
                        CircleShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Phobos: {
                        PhobosShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        PhobosShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        PhobosShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                }
                switch ((glowESPmode)this.glowESP.getValue()) {
                    case Color: {
                        GlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GlowShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue());
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color6, (int)this.WaveLenghtOutline.getValue(), (int)this.RSTARTOutline.getValue(), (int)this.GSTARTOutline.getValue(), (int)this.BSTARTOutline.getValue());
                        RainbowCubeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GradientOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.moreGradientOutline.getValue(), (float)this.creepyOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.NUM_OCTAVESOutline.getValue());
                        GradientOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Astral: {
                        AstralOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AstralOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.redOutline.getValue(), (float)this.greenOutline.getValue(), (float)this.blueOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.iterationsOutline.getValue(), (float)this.formuparam2Outline.getValue(), (float)this.zoomOutline.getValue(), (float)(int)this.volumStepsOutline.getValue(), (float)this.stepSizeOutline.getValue(), (float)this.titleOutline.getValue(), (float)this.distfadingOutline.getValue(), (float)this.saturationOutline.getValue(), 0.0f, (int)(((boolean)this.fadeOutline.getValue()) ? 1 : 0));
                        AstralOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AquaOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (int)this.MaxIterOutline.getValue(), (double)(float)this.tauOutline.getValue());
                        AquaOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        CircleOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.PIOutline.getValue(), (float)this.radOutline.getValue());
                        CircleOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        SmokeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color3, color4, (int)this.NUM_OCTAVESOutline.getValue());
                        SmokeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                }
            }
            else {
                switch ((glowESPmode)this.glowESP.getValue()) {
                    case Color: {
                        GlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GlowShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue());
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color6, (int)this.WaveLenghtOutline.getValue(), (int)this.RSTARTOutline.getValue(), (int)this.GSTARTOutline.getValue(), (int)this.BSTARTOutline.getValue());
                        RainbowCubeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        GradientOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.moreGradientOutline.getValue(), (float)this.creepyOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.NUM_OCTAVESOutline.getValue());
                        GradientOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Astral: {
                        AstralOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AstralOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.redOutline.getValue(), (float)this.greenOutline.getValue(), (float)this.blueOutline.getValue(), (float)this.alphaOutline.getValue(), (int)this.iterationsOutline.getValue(), (float)this.formuparam2Outline.getValue(), (float)this.zoomOutline.getValue(), (float)(int)this.volumStepsOutline.getValue(), (float)this.stepSizeOutline.getValue(), (float)this.titleOutline.getValue(), (float)this.distfadingOutline.getValue(), (float)this.saturationOutline.getValue(), 0.0f, (int)(((boolean)this.fadeOutline.getValue()) ? 1 : 0));
                        AstralOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        AquaOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (int)this.MaxIterOutline.getValue(), (double)(float)this.tauOutline.getValue());
                        AquaOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        CircleOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), (float)this.PIOutline.getValue(), (float)this.radOutline.getValue());
                        CircleOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeOutlineShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersOutline(renderGameOverlayEvent.getPartialTicks());
                        SmokeOutlineShader.INSTANCE.stopDraw(color2, (float)this.radius.getValue(), (float)this.quality.getValue(), false, (int)this.alphaValue.getValue(), (float)this.duplicateOutline.getValue(), color3, color4, (int)this.NUM_OCTAVESOutline.getValue());
                        SmokeOutlineShader.INSTANCE.update((double)((float)this.speedOutline.getValue() / 1000.0f));
                        break;
                    }
                }
                switch ((fillShadermode)this.fillShader.getValue()) {
                    case Astral: {
                        FlowShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FlowShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.redFill.getValue(), (float)this.greenFill.getValue(), (float)this.blueFill.getValue(), (float)this.alphaFill.getValue(), (int)this.iterationsFill.getValue(), (float)this.formuparam2Fill.getValue(), (float)this.zoomFill.getValue(), (float)this.volumStepsFill.getValue(), (float)this.stepSizeFill.getValue(), (float)this.titleFill.getValue(), (float)this.distfadingFill.getValue(), (float)this.saturationFill.getValue(), 0.0f, (int)(((boolean)this.fadeFill.getValue()) ? 1 : 0));
                        FlowShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Aqua: {
                        AquaShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        AquaShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        AquaShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Smoke: {
                        SmokeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        SmokeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, color3, color5, (int)this.NUM_OCTAVESFill.getValue());
                        SmokeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case RainbowCube: {
                        RainbowCubeShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        RainbowCubeShader.INSTANCE.stopDraw(Color.WHITE, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), color, (int)this.WaveLenghtFIll.getValue(), (int)this.RSTARTFill.getValue(), (int)this.GSTARTFill.getValue(), (int)this.BSTARTFIll.getValue());
                        RainbowCubeShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Gradient: {
                        GradientShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        GradientShader.INSTANCE.stopDraw(color2, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (float)this.moreGradientFill.getValue(), (float)this.creepyFill.getValue(), (float)this.alphaFill.getValue(), (int)this.NUM_OCTAVESFill.getValue());
                        GradientShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Fill: {
                        FillShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        FillShader.INSTANCE.stopDraw(color);
                        FillShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Circle: {
                        CircleShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        CircleShader.INSTANCE.stopDraw((float)this.duplicateFill.getValue(), color, (Float)this.PI.getValue(), (Float)this.rad.getValue());
                        CircleShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                    case Phobos: {
                        PhobosShader.INSTANCE.startDraw(renderGameOverlayEvent.getPartialTicks());
                        this.renderPlayersFill(renderGameOverlayEvent.getPartialTicks());
                        PhobosShader.INSTANCE.stopDraw(color, 1.0f, 1.0f, (float)this.duplicateFill.getValue(), (int)this.MaxIterFill.getValue(), (double)(float)this.tauFill.getValue());
                        PhobosShader.INSTANCE.update((double)((float)this.speedFill.getValue() / 1000.0f));
                        break;
                    }
                }
            }
            this.notShader = true;
            GlStateManager.popMatrix();
        }
    }
    
    private boolean lambda$new$52(final Color color) {
        return this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke;
    }
    
    private boolean lambda$new$47(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Gradient;
    }
    
    private boolean lambda$new$37(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$16(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.Aqua;
    }
    
    private boolean lambda$new$18(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.RainbowCube;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.fillShader.getValue() == fillShadermode.Astral || this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    public Shaders() {
        super("Shaders", "test", Category.RENDER);
        this.fillShader = this.add(new Setting("Fill Shader", fillShadermode.None));
        this.glowESP = this.add(new Setting("Glow ESP", glowESPmode.None));
        this.crystal = this.add(new Setting("Crystals", Crystal1.None));
        this.player = this.add(new Setting("Players", Player1.None));
        this.mob = this.add(new Setting("Mobs", Mob1.None));
        this.items = this.add(new Setting("Items", Itemsl.None));
        this.xpOrb = this.add(new Setting("XP", XPl.None));
        this.xpBottle = this.add(new Setting("XPBottle", XPBl.None));
        this.enderPearl = this.add(new Setting("EnderPearl", EPl.None));
        this.rangeCheck = this.add(new Setting("Range Check", true));
        this.maxRange = this.add(new Setting("Max Range", 35.0f, 10.0f, 100.0f, this::lambda$new$0));
        this.minRange = this.add(new Setting("Min range", 0.0f, 0.0f, 5.0f, this::lambda$new$1));
        this.default1 = this.add(new Setting("Reset Setting", false));
        this.Fpreset = this.add(new Setting("FutureRainbow Preset", false));
        this.fadeFill = this.add(new Setting("Fade Fill", Boolean.FALSE, this::lambda$new$2));
        this.fadeOutline = this.add(new Setting("FadeOL Fill", Boolean.FALSE, this::lambda$new$3));
        this.duplicateOutline = this.add(new Setting("duplicateOutline", 1.0f, 0.0f, 20.0f));
        this.duplicateFill = this.add(new Setting("Duplicate Fill", 1.0f, 0.0f, 5.0f));
        this.speedOutline = this.add(new Setting("Speed Outline", 10.0f, 1.0f, 100.0f));
        this.speedFill = this.add(new Setting("Speed Fill", 10.0f, 1.0f, 100.0f));
        this.quality = this.add(new Setting("Shader Quality", 1.0f, 0.0f, 20.0f));
        this.radius = this.add(new Setting("Shader Radius", 1.0f, 0.0f, 5.0f));
        this.rad = this.add(new Setting("RAD Fill", 0.75f, 0.0f, 5.0f, this::lambda$new$4));
        this.PI = this.add(new Setting("PI Fill", 3.1415927f, 0.0f, 10.0f, this::lambda$new$5));
        this.saturationFill = this.add(new Setting("saturation", 0.4f, 0.0f, 3.0f, this::lambda$new$6));
        this.distfadingFill = this.add(new Setting("distfading", 0.56f, 0.0f, 1.0f, this::lambda$new$7));
        this.titleFill = this.add(new Setting("Tile", 0.45f, 0.0f, 1.3f, this::lambda$new$8));
        this.stepSizeFill = this.add(new Setting("Step Size", 0.2f, 0.0f, 0.7f, this::lambda$new$9));
        this.volumStepsFill = this.add(new Setting("Volum Steps", 10.0f, 0.0f, 10.0f, this::lambda$new$10));
        this.zoomFill = this.add(new Setting("Zoom", 3.9f, 0.0f, 20.0f, this::lambda$new$11));
        this.formuparam2Fill = this.add(new Setting("formuparam2", 0.89f, 0.0f, 1.5f, this::lambda$new$12));
        this.saturationOutline = this.add(new Setting("saturation", 0.4f, 0.0f, 3.0f, this::lambda$new$13));
        this.maxEntities = this.add(new Setting("Max Entities", 100, 10, 500));
        this.iterationsFill = this.add(new Setting("Iteration", 4, 3, 20, this::lambda$new$14));
        this.redFill = this.add(new Setting("Tick Regen", 0, 0, 100, this::lambda$new$15));
        this.MaxIterFill = this.add(new Setting("Max Iter", 5, 0, 30, this::lambda$new$16));
        this.NUM_OCTAVESFill = this.add(new Setting("NUM_OCTAVES", 5, 1, 30, this::lambda$new$17));
        this.BSTARTFIll = this.add(new Setting("BSTART", 0, 0, 1000, this::lambda$new$18));
        this.GSTARTFill = this.add(new Setting("GSTART", 0, 0, 1000, this::lambda$new$19));
        this.RSTARTFill = this.add(new Setting("RSTART", 0, 0, 1000, this::lambda$new$20));
        this.WaveLenghtFIll = this.add(new Setting("Wave Lenght", 555, 0, 2000, this::lambda$new$21));
        this.volumStepsOutline = this.add(new Setting("Volum Steps", 10, 0, 10, this::lambda$new$22));
        this.iterationsOutline = this.add(new Setting("Iteration", 4, 3, 20, this::lambda$new$23));
        this.MaxIterOutline = this.add(new Setting("Max Iter", 5, 0, 30, this::lambda$new$24));
        this.NUM_OCTAVESOutline = this.add(new Setting("NUM_OCTAVES", 5, 1, 30, this::lambda$new$25));
        this.BSTARTOutline = this.add(new Setting("BSTART", 0, 0, 1000, this::lambda$new$26));
        this.GSTARTOutline = this.add(new Setting("GSTART", 0, 0, 1000, this::lambda$new$27));
        this.RSTARTOutline = this.add(new Setting("RSTART", 0, 0, 1000, this::lambda$new$28));
        this.alphaValue = this.add(new Setting("Alpha Outline", 255, 0, 255));
        this.WaveLenghtOutline = this.add(new Setting("Wave Lenght", 555, 0, 2000, this::lambda$new$29));
        this.redOutline = this.add(new Setting("Red", 0, 0, 100, this::lambda$new$30));
        this.alphaFill = this.add(new Setting("AlphaF", 1.0f, 0.0f, 1.0f, this::lambda$new$31));
        this.blueFill = this.add(new Setting("BlueF", 0.0f, 0.0f, 5.0f, this::lambda$new$32));
        this.greenFill = this.add(new Setting("GreenF", 0.0f, 0.0f, 5.0f, this::lambda$new$33));
        this.tauFill = this.add(new Setting("TAU", 6.2831855f, 0.0f, 20.0f, this::lambda$new$34));
        this.creepyFill = this.add(new Setting("Creepy", 1.0f, 0.0f, 20.0f, this::lambda$new$35));
        this.moreGradientFill = this.add(new Setting("More Gradient", 1.0f, 0.0f, 10.0, this::lambda$new$36));
        this.distfadingOutline = this.add(new Setting("distfading", 0.56f, 0.0f, 1.0f, this::lambda$new$37));
        this.titleOutline = this.add(new Setting("Tile", 0.45f, 0.0f, 1.3f, this::lambda$new$38));
        this.stepSizeOutline = this.add(new Setting("Step Size", 0.19f, 0.0f, 0.7f, this::lambda$new$39));
        this.zoomOutline = this.add(new Setting("Zoom", 3.9f, 0.0f, 20.0f, this::lambda$new$40));
        this.formuparam2Outline = this.add(new Setting("formuparam2", 0.89f, 0.0f, 1.5f, this::lambda$new$41));
        this.alphaOutline = this.add(new Setting("Alpha", 1.0f, 0.0f, 1.0f, this::lambda$new$42));
        this.blueOutline = this.add(new Setting("Blue", 0.0f, 0.0f, 5.0f, this::lambda$new$43));
        this.greenOutline = this.add(new Setting("Green", 0.0f, 0.0f, 5.0f, this::lambda$new$44));
        this.tauOutline = this.add(new Setting("TAU", 6.2831855f, 0.0f, 20.0f, this::lambda$new$45));
        this.creepyOutline = this.add(new Setting("Gradient Creepy", 1.0f, 0.0f, 20.0f, this::lambda$new$46));
        this.moreGradientOutline = this.add(new Setting("More Gradient", 1.0f, 0.0f, 10.0f, this::lambda$new$47));
        this.radOutline = this.add(new Setting("RAD Outline", 0.75f, 0.0f, 5.0f, this::lambda$new$48));
        this.PIOutline = this.add(new Setting("PI Outline", 3.1415927f, 0.0f, 10.0f, this::lambda$new$49));
        this.colorImgOutline = this.add(new Setting("ColorImgOutline", new Color(0, 0, 0, 255), this::lambda$new$50));
        this.thirdColorImgOutline = this.add(new Setting("ThirdColorImg", new Color(0, 0, 0, 255), this::lambda$new$51));
        this.colorESP = this.add(new Setting("ColorESP", new Color(0, 0, 0, 255)));
        this.colorImgFill = this.add(new Setting("ColorImgFill", new Color(0, 0, 0, 255)));
        this.thirdColorImgFIll = this.add(new Setting("SmokeImgFill", new Color(0, 0, 0, 255), this::lambda$new$52));
        this.secondColorImgFill = this.add(new Setting("SmokeFill", new Color(0, 0, 0, 255), this::lambda$new$53));
        this.notShader = true;
        Shaders.INSTANCE = this;
    }
    
    private static void lambda$renderPlayersOutline$59(final float n, final Entity entity) {
        Shaders.mc.getRenderManager().renderEntityStatic(entity, n, true);
    }
    
    private boolean lambda$new$6(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$28(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.RainbowCube;
    }
    
    private boolean lambda$new$17(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.Smoke;
    }
    
    private boolean lambda$new$1(final Object o) {
        return (boolean)this.rangeCheck.getValue();
    }
    
    private static boolean lambda$renderPlayersOutline$58(final boolean b, final double n, final double n2, final Entity entity) {
        if (!b) {
            return true;
        }
        final double getDistanceSq = Shaders.mc.player.getDistanceSq(entity);
        return (getDistanceSq > n && getDistanceSq < n2) || entity.getEntityId() == -1000;
    }
    
    void renderPlayersFill(final float n) {
        final boolean booleanValue = (boolean)this.rangeCheck.getValue();
        final double n2 = (float)this.minRange.getValue() * (float)this.minRange.getValue();
        final double n3 = (float)this.maxRange.getValue() * (float)this.maxRange.getValue();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final int intValue = (int)this.maxEntities.getValue();
        try {
            Shaders.mc.world.loadedEntityList.stream().filter(this::lambda$renderPlayersFill$54).filter(Shaders::lambda$renderPlayersFill$55).forEach(Shaders::lambda$renderPlayersFill$56);
        }
        catch (Exception ex) {}
    }
    
    private boolean lambda$new$13(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$46(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Gradient;
    }
    
    private boolean lambda$new$39(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$49(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Circle;
    }
    
    private boolean lambda$new$33(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$11(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$45(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Aqua;
    }
    
    private boolean lambda$renderPlayersFill$54(final AtomicInteger atomicInteger, final int n, final Entity entity) {
        return atomicInteger.getAndIncrement() <= n && ((entity instanceof EntityPlayer) ? ((this.player.getValue() == Player1.Fill || this.player.getValue() == Player1.Both) && (entity != Shaders.mc.player || Shaders.mc.gameSettings.thirdPersonView != 0)) : ((entity instanceof EntityEnderPearl) ? (this.enderPearl.getValue() == EPl.Fill || this.enderPearl.getValue() == EPl.Both) : ((entity instanceof EntityExpBottle) ? (this.xpBottle.getValue() == XPBl.Fill || this.xpBottle.getValue() == XPBl.Both) : ((entity instanceof EntityXPOrb) ? (this.xpOrb.getValue() == XPl.Fill || this.xpOrb.getValue() == XPl.Both) : ((entity instanceof EntityItem) ? (this.items.getValue() == Itemsl.Fill || this.items.getValue() == Itemsl.Both) : ((entity instanceof EntityCreature) ? (this.mob.getValue() == Mob1.Fill || this.mob.getValue() == Mob1.Both) : (entity instanceof EntityEnderCrystal && (this.crystal.getValue() == Crystal1.Fill || this.crystal.getValue() == Crystal1.Both))))))));
    }
    
    private boolean lambda$new$4(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Circle;
    }
    
    private boolean lambda$new$14(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$9(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$19(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.RainbowCube;
    }
    
    private boolean lambda$new$21(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.RainbowCube;
    }
    
    private boolean lambda$new$0(final Object o) {
        return (boolean)this.rangeCheck.getValue();
    }
    
    private boolean lambda$new$51(final Color color) {
        return this.fillShader.getValue() == fillShadermode.Smoke || this.glowESP.getValue() == glowESPmode.Smoke;
    }
    
    void renderPlayersOutline(final float n) {
        final boolean booleanValue = (boolean)this.rangeCheck.getValue();
        final double n2 = (float)this.minRange.getValue() * (float)this.minRange.getValue();
        final double n3 = (float)this.maxRange.getValue() * (float)this.maxRange.getValue();
        final AtomicInteger atomicInteger = new AtomicInteger();
        final int intValue = (int)this.maxEntities.getValue();
        Shaders.mc.world.addEntityToWorld(-1000, (Entity)new EntityXPOrb((World)Shaders.mc.world, Shaders.mc.player.posX, Shaders.mc.player.posY + 1000000.0, Shaders.mc.player.posZ, 1));
        Shaders.mc.world.loadedEntityList.stream().filter(this::lambda$renderPlayersOutline$57).filter(Shaders::lambda$renderPlayersOutline$58).forEach(Shaders::lambda$renderPlayersOutline$59);
        Shaders.mc.world.removeEntityFromWorld(-1000);
    }
    
    private boolean lambda$new$41(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$8(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    private boolean lambda$new$27(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.RainbowCube;
    }
    
    private boolean lambda$new$35(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Smoke;
    }
    
    private boolean lambda$new$48(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Circle;
    }
    
    private boolean lambda$new$23(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$new$24(final Integer n) {
        return this.glowESP.getValue() == glowESPmode.Aqua;
    }
    
    private boolean lambda$new$20(final Integer n) {
        return this.fillShader.getValue() == fillShadermode.RainbowCube;
    }
    
    private boolean lambda$new$50(final Color color) {
        return this.fillShader.getValue() == fillShadermode.RainbowCube || this.glowESP.getValue() == glowESPmode.RainbowCube;
    }
    
    private boolean lambda$new$40(final Object o) {
        return this.glowESP.getValue() == glowESPmode.Astral;
    }
    
    private boolean lambda$renderPlayersOutline$57(final AtomicInteger atomicInteger, final int n, final Entity entity) {
        return atomicInteger.getAndIncrement() <= n && ((entity instanceof EntityPlayer) ? ((this.player.getValue() == Player1.Outline || this.player.getValue() == Player1.Both) && (entity != Shaders.mc.player || Shaders.mc.gameSettings.thirdPersonView != 0)) : ((entity instanceof EntityEnderPearl) ? (this.enderPearl.getValue() == EPl.Outline || this.enderPearl.getValue() == EPl.Both) : ((entity instanceof EntityExpBottle) ? (this.xpBottle.getValue() == XPBl.Outline || this.xpBottle.getValue() == XPBl.Both) : ((entity instanceof EntityXPOrb) ? (this.xpOrb.getValue() == XPl.Outline || this.xpOrb.getValue() == XPl.Both) : ((entity instanceof EntityItem) ? (this.items.getValue() == Itemsl.Outline || this.items.getValue() == Itemsl.Both) : ((entity instanceof EntityCreature) ? (this.mob.getValue() == Mob1.Outline || this.mob.getValue() == Mob1.Both) : (entity instanceof EntityEnderCrystal && (this.crystal.getValue() == Crystal1.Outline || this.crystal.getValue() == Crystal1.Both))))))));
    }
    
    private boolean lambda$new$7(final Object o) {
        return this.fillShader.getValue() == fillShadermode.Astral;
    }
    
    public enum EPl
    {
        Outline("Outline", 2);
        
        private static final EPl[] $VALUES;
        
        None("None", 0), 
        Fill("Fill", 1), 
        Both("Both", 3);
        
        static {
            $VALUES = new EPl[] { EPl.None, EPl.Fill, EPl.Outline, EPl.Both };
        }
        
        private EPl(final String s, final int n) {
        }
    }
    
    public enum Mob1
    {
        Outline("Outline", 2), 
        Both("Both", 3), 
        Fill("Fill", 1);
        
        private static final Mob1[] $VALUES;
        
        None("None", 0);
        
        private Mob1(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mob1[] { Mob1.None, Mob1.Fill, Mob1.Outline, Mob1.Both };
        }
    }
    
    public enum XPl
    {
        Fill("Fill", 1);
        
        private static final XPl[] $VALUES;
        
        Outline("Outline", 2), 
        Both("Both", 3), 
        None("None", 0);
        
        private XPl(final String s, final int n) {
        }
        
        static {
            $VALUES = new XPl[] { XPl.None, XPl.Fill, XPl.Outline, XPl.Both };
        }
    }
    
    public enum Crystal1
    {
        None("None", 0);
        
        private static final Crystal1[] $VALUES;
        
        Outline("Outline", 2), 
        Both("Both", 3), 
        Fill("Fill", 1);
        
        private Crystal1(final String s, final int n) {
        }
        
        static {
            $VALUES = new Crystal1[] { Crystal1.None, Crystal1.Fill, Crystal1.Outline, Crystal1.Both };
        }
    }
    
    public enum fillShadermode
    {
        Fill("Fill", 5);
        
        private static final fillShadermode[] $VALUES;
        
        Gradient("Gradient", 4), 
        Aqua("Aqua", 1), 
        None("None", 8), 
        RainbowCube("RainbowCube", 3), 
        Smoke("Smoke", 2), 
        Circle("Circle", 6), 
        Phobos("Phobos", 7), 
        Astral("Astral", 0);
        
        static {
            $VALUES = new fillShadermode[] { fillShadermode.Astral, fillShadermode.Aqua, fillShadermode.Smoke, fillShadermode.RainbowCube, fillShadermode.Gradient, fillShadermode.Fill, fillShadermode.Circle, fillShadermode.Phobos, fillShadermode.None };
        }
        
        private fillShadermode(final String s, final int n) {
        }
    }
    
    public enum Itemsl
    {
        None("None", 0);
        
        private static final Itemsl[] $VALUES;
        
        Fill("Fill", 1), 
        Outline("Outline", 2), 
        Both("Both", 3);
        
        private Itemsl(final String s, final int n) {
        }
        
        static {
            $VALUES = new Itemsl[] { Itemsl.None, Itemsl.Fill, Itemsl.Outline, Itemsl.Both };
        }
    }
    
    public enum XPBl
    {
        private static final XPBl[] $VALUES;
        
        Outline("Outline", 2), 
        Both("Both", 3), 
        None("None", 0), 
        Fill("Fill", 1);
        
        static {
            $VALUES = new XPBl[] { XPBl.None, XPBl.Fill, XPBl.Outline, XPBl.Both };
        }
        
        private XPBl(final String s, final int n) {
        }
    }
    
    public enum glowESPmode
    {
        private static final glowESPmode[] $VALUES;
        
        Color("Color", 1), 
        Astral("Astral", 2), 
        Gradient("Gradient", 4), 
        None("None", 0), 
        Smoke("Smoke", 6), 
        RainbowCube("RainbowCube", 3), 
        Aqua("Aqua", 7), 
        Circle("Circle", 5);
        
        static {
            $VALUES = new glowESPmode[] { glowESPmode.None, glowESPmode.Color, glowESPmode.Astral, glowESPmode.RainbowCube, glowESPmode.Gradient, glowESPmode.Circle, glowESPmode.Smoke, glowESPmode.Aqua };
        }
        
        private glowESPmode(final String s, final int n) {
        }
    }
    
    public enum Player1
    {
        Outline("Outline", 2), 
        Both("Both", 3), 
        None("None", 0);
        
        private static final Player1[] $VALUES;
        
        Fill("Fill", 1);
        
        static {
            $VALUES = new Player1[] { Player1.None, Player1.Fill, Player1.Outline, Player1.Both };
        }
        
        private Player1(final String s, final int n) {
        }
    }
}
