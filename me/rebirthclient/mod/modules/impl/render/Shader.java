//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.render.shader.framebuffer.impl.*;
import java.awt.*;
import me.rebirthclient.asm.accessors.*;
import net.minecraft.entity.item.*;
import java.util.function.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.events.impl.*;

public class Shader extends Module
{
    private boolean forceRender;
    private final Setting model;
    private final Setting crystals;
    private final Setting range;
    private final Setting fovOnly;
    private final Setting smoothness;
    private final Setting items;
    private final Setting alpha;
    private final Setting color;
    private final Setting players;
    public static Shader INSTANCE;
    private final Setting self;
    private final Setting radius;
    private final Setting glow;
    private final Setting xp;
    public static boolean crystalRender;
    
    private void lambda$onRenderWorldLastEvent$4(final RenderWorldLastEvent renderWorldLastEvent, final Entity entity) {
        if (entity.getDistance((Entity)Shader.mc.player) > (int)this.range.getValue() || ((boolean)this.fovOnly.getValue() && !Managers.ROTATIONS.isInFov(entity.getPosition()))) {
            return;
        }
        if (Integer.valueOf(Shader.mc.player.getName().hashCode()).equals(entity.getName().hashCode()) && Shader.mc.gameSettings.thirdPersonView == 0) {
            return;
        }
        final Vec3d interpolatedRenderPos = InterpolationUtil.getInterpolatedRenderPos(entity, renderWorldLastEvent.getPartialTicks());
        if (entity instanceof EntityPlayer) {
            ((EntityPlayer)entity).hurtTime = 0;
        }
        final Render getEntityRenderObject = Shader.mc.getRenderManager().getEntityRenderObject(entity);
        if (getEntityRenderObject != null) {
            try {
                getEntityRenderObject.doRender(entity, interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z, entity.rotationYaw, renderWorldLastEvent.getPartialTicks());
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if ((Display.isActive() || Display.isVisible()) && !(Shader.mc.currentScreen instanceof GuiDownloadTerrain)) {
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            GlStateManager.enableAlpha();
            final ItemShader instance = ItemShader.INSTANCE;
            instance.mix = ((Color)this.color.getValue()).getAlpha() / 255.0f;
            instance.alpha = (205 + (int)this.alpha.getValue()) / 255.0f;
            instance.model = (boolean)this.model.getValue();
            instance.startDraw(Shader.mc.getRenderPartialTicks());
            this.forceRender = true;
            if (this.self.getValue()) {
                ((IEntityRenderer)Shader.mc.entityRenderer).invokeRenderHand(Shader.mc.getRenderPartialTicks(), 2);
            }
            this.forceRender = false;
            instance.stopDraw((Color)this.color.getValue(), ((boolean)this.glow.getValue()) ? ((float)this.radius.getValue()) : 0.0f, (float)this.smoothness.getValue());
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.disableDepth();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
    
    private boolean lambda$new$1(final Float n) {
        return this.glow.isOpen();
    }
    
    private boolean lambda$onRenderWorldLastEvent$3(final Entity entity) {
        return entity != null && (entity != Shader.mc.player || entity != Shader.mc.getRenderViewEntity()) && Shader.mc.getRenderManager().getEntityRenderObject(entity) != null && ((entity instanceof EntityPlayer && (boolean)this.players.getValue() && !((EntityPlayer)entity).isSpectator()) || (entity instanceof EntityEnderCrystal && (boolean)this.crystals.getValue()) || (entity instanceof EntityExpBottle && (boolean)this.xp.getValue()) || (entity instanceof EntityItem && (boolean)this.items.getValue()));
    }
    
    @SubscribeEvent
    public void onRenderWorldLastEvent(final RenderWorldLastEvent renderWorldLastEvent) {
        if (fullNullCheck() || !this.isOn()) {
            return;
        }
        if ((Display.isActive() || Display.isVisible()) && !(Shader.mc.currentScreen instanceof GuiDownloadTerrain)) {
            GlStateManager.pushMatrix();
            GlStateManager.pushAttrib();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            GlStateManager.enableAlpha();
            final ItemShader instance = ItemShader.INSTANCE;
            instance.mix = ((Color)this.color.getValue()).getAlpha() / 255.0f;
            instance.alpha = (205 + (int)this.alpha.getValue()) / 255.0f;
            instance.model = false;
            instance.startDraw(Shader.mc.getRenderPartialTicks());
            Shader.crystalRender = true;
            Shader.mc.world.loadedEntityList.stream().filter(this::lambda$onRenderWorldLastEvent$3).forEach(this::lambda$onRenderWorldLastEvent$4);
            Shader.crystalRender = false;
            instance.stopDraw((Color)this.color.getValue(), ((boolean)this.glow.getValue()) ? ((float)this.radius.getValue()) : 0.0f, (float)this.smoothness.getValue());
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.disableDepth();
            GlStateManager.popAttrib();
            GlStateManager.popMatrix();
        }
    }
    
    @Override
    public void onLogin() {
        if (this.isOn()) {
            this.disable();
            this.enable();
        }
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.glow.isOpen();
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.glow.isOpen();
    }
    
    public Shader() {
        super("Shader", "Is in beta test stage", Category.RENDER);
        this.players = this.add(new Setting("Players", false));
        this.crystals = this.add(new Setting("Crystals", false));
        this.xp = this.add(new Setting("Exp", false));
        this.items = this.add(new Setting("Items", false));
        this.self = this.add(new Setting("Self", true));
        this.color = this.add(new Setting("Color", new Color(-8553003, true)));
        this.glow = this.add(new Setting("Glow", true).setParent());
        this.radius = this.add(new Setting("Radius", 4.0f, 0.1f, 6.0f, this::lambda$new$0));
        this.smoothness = this.add(new Setting("Smoothness", 1.0f, 0.1f, 1.0f, this::lambda$new$1));
        this.alpha = this.add(new Setting("Alpha", 50, 1, 50, this::lambda$new$2));
        this.model = this.add(new Setting("Model", true));
        this.range = this.add(new Setting("Range", 75, 5, 250));
        this.fovOnly = this.add(new Setting("FOVOnly", false));
        Shader.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void renderItemInFirstPerson(final RenderItemInFirstPersonEvent renderItemInFirstPersonEvent) {
        if (fullNullCheck() || !this.isOn() || renderItemInFirstPersonEvent.getStage() != 0 || this.forceRender || !(boolean)this.self.getValue()) {
            return;
        }
        renderItemInFirstPersonEvent.setCanceled(true);
    }
}
