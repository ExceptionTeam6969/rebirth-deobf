//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.item.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import me.rebirthclient.asm.accessors.*;
import me.rebirthclient.api.util.render.shaders.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;

public class ShaderChams extends Module
{
    private final Setting items;
    private final Setting crystals;
    private final Setting color;
    private final Setting shader;
    private final Setting fovOnly;
    public static ShaderChams INSTANCE;
    private final Setting radius;
    private final Setting maxSample;
    private final Setting divider;
    private final Setting xp;
    private final Setting self;
    private Boolean criticalSection;
    private final Setting animationSpeed;
    private final Setting animation;
    private final Setting players;
    private final Setting range;
    
    private void lambda$onRenderWorldLastEvent$1(final RenderWorldLastEvent renderWorldLastEvent, final Entity entity) {
        if (entity.getDistance((Entity)ShaderChams.mc.player) > (int)this.range.getValue() || ((boolean)this.fovOnly.getValue() && !Managers.ROTATIONS.isInFov(entity.getPosition()))) {
            return;
        }
        final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos(entity, renderWorldLastEvent.getPartialTicks());
        if (entity instanceof EntityPlayer) {
            ((EntityPlayer)entity).hurtTime = 0;
        }
        final Render getEntityRenderObject = ShaderChams.mc.getRenderManager().getEntityRenderObject(entity);
        if (getEntityRenderObject != null) {
            try {
                getEntityRenderObject.doRender(entity, interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z, entity.rotationYaw, renderWorldLastEvent.getPartialTicks());
            }
            catch (Exception ex) {}
        }
    }
    
    private boolean lambda$onRenderWorldLastEvent$0(final Entity entity) {
        return entity != null && (entity != ShaderChams.mc.player || entity != ShaderChams.mc.getRenderViewEntity()) && ShaderChams.mc.getRenderManager().getEntityRenderObject(entity) != null && ((entity instanceof EntityPlayer && (boolean)this.players.getValue() && !((EntityPlayer)entity).isSpectator()) || (entity instanceof EntityEnderCrystal && (boolean)this.crystals.getValue()) || (entity instanceof EntityExpBottle && (boolean)this.xp.getValue()) || (entity instanceof EntityItem && (boolean)this.items.getValue()));
    }
    
    public ShaderChams() {
        super("ShaderChams", "good render", Category.RENDER);
        this.shader = this.add(new Setting("Shader Mode", ShaderMode.Aqua));
        this.animation = this.add(new Setting("Animation", true));
        this.animationSpeed = this.add(new Setting("Animation Speed", 1, 1, 10));
        this.radius = this.add(new Setting("Glow Radius", 3.3f, 1.0f, 10.0f));
        this.divider = this.add(new Setting("Glow Divider", 158.6f, 1.0f, 1000.0f));
        this.maxSample = this.add(new Setting("Glow MaxSample", 10.0f, 1.0f, 20.0f));
        this.players = this.add(new Setting("Player", false));
        this.crystals = this.add(new Setting("Crystal", false));
        this.xp = this.add(new Setting("Exp", false));
        this.items = this.add(new Setting("DroppedItem", false));
        this.self = this.add(new Setting("ItemShaderChams", true));
        this.fovOnly = this.add(new Setting("FOVOnly", false));
        this.color = this.add(new Setting("Color", new Color(-8553003, true)));
        this.range = this.add(new Setting("Range", 50, 5, 250));
        this.criticalSection = false;
        ShaderChams.INSTANCE = this;
    }
    
    @Override
    public String getInfo() {
        return ((ShaderMode)this.shader.getValue()).getName();
    }
    
    @Override
    public void onLogin() {
        if (this.isOn()) {
            this.disable();
            this.enable();
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLastEvent(final RenderWorldLastEvent renderWorldLastEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (Display.isActive() || Display.isVisible()) {
            final FramebufferShader shader = ((ShaderMode)this.shader.getValue()).getShader();
            if (shader == null) {
                return;
            }
            GL11.glBlendFunc(770, 771);
            shader.setShaderParams((Boolean)this.animation.getValue(), (int)this.animationSpeed.getValue(), (Color)this.color.getValue(), (float)this.radius.getValue(), (float)this.divider.getValue(), (float)this.maxSample.getValue());
            this.criticalSection = true;
            shader.startDraw(ShaderChams.mc.getRenderPartialTicks());
            ShaderChams.mc.world.loadedEntityList.stream().filter(this::lambda$onRenderWorldLastEvent$0).forEach(this::lambda$onRenderWorldLastEvent$1);
            if ((boolean)this.self.getValue() && !BlockUtil.getBlock(EntityUtil.getPlayerPos().up()).equals(Blocks.WATER)) {
                ((IEntityRenderer)ShaderChams.mc.entityRenderer).invokeRenderHand(ShaderChams.mc.getRenderPartialTicks(), 2);
            }
            shader.stopDraw();
            this.criticalSection = false;
        }
    }
    
    @SubscribeEvent
    public void onRenderHand(final RenderHandEvent renderHandEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!this.criticalSection && (boolean)this.self.getValue()) {
            renderHandEvent.setCanceled(true);
        }
    }
}
