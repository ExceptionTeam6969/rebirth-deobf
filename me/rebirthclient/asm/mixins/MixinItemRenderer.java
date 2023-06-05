//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.api.managers.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void renderItemInFirstPerson(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Redirect(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;renderItemSide(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemCameraTransforms$TransformType;Z)V"))
    public void renderItemInFirstPerson(final ItemRenderer itemRenderer, final EntityLivingBase entityLivingBase, final ItemStack itemStack, final ItemCameraTransforms.TransformType transformType, final boolean b) {
        final RenderItemInFirstPersonEvent renderItemInFirstPersonEvent = new RenderItemInFirstPersonEvent(entityLivingBase, itemStack, transformType, b, 0);
        MinecraftForge.EVENT_BUS.post((Event)renderItemInFirstPersonEvent);
        if (!renderItemInFirstPersonEvent.isCanceled()) {
            itemRenderer.renderItemSide(entityLivingBase, renderItemInFirstPersonEvent.getStack(), renderItemInFirstPersonEvent.getTransformType(), b);
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderItemInFirstPersonEvent(entityLivingBase, itemStack, transformType, b, 1));
    }
    
    @Inject(method = { "renderFireInFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void renderFireInFirstPersonHook(final CallbackInfo callbackInfo) {
        if (Shader.INSTANCE.isOn()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemInFirstPersonHook(final AbstractClientPlayer abstractClientPlayer, final float n, final float n2, final EnumHand enumHand, final float n3, final ItemStack itemStack, final float n4, final CallbackInfo callbackInfo) {
        final Chams instance = Chams.INSTANCE;
        if (this.injection) {
            callbackInfo.cancel();
            final boolean friend = Managers.FRIENDS.isFriend(abstractClientPlayer.getName());
            this.injection = false;
            if (instance.isOn() && (boolean)instance.self.getValue() && enumHand == EnumHand.MAIN_HAND && itemStack.isEmpty()) {
                if (instance.model.getValue() == Chams.Model.VANILLA) {
                    this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
                }
                else if (instance.model.getValue() == Chams.Model.XQZ) {
                    GL11.glEnable(32823);
                    GlStateManager.enablePolygonOffset();
                    GL11.glPolygonOffset(1.0f, -1000000.0f);
                    if (instance.modelColor.booleanValue) {
                        RenderUtil.glColor(friend ? Managers.COLORS.getFriendColor(((Color)instance.modelColor.getValue()).getAlpha()) : new Color(((Color)instance.modelColor.getValue()).getRed(), ((Color)instance.modelColor.getValue()).getGreen(), ((Color)instance.modelColor.getValue()).getBlue(), ((Color)instance.modelColor.getValue()).getAlpha()));
                    }
                    this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
                    GL11.glDisable(32823);
                    GlStateManager.disablePolygonOffset();
                    GL11.glPolygonOffset(1.0f, 1000000.0f);
                }
                if (instance.wireframe.getValue()) {
                    final Color color = friend ? Managers.COLORS.getFriendColor(instance.lineColor.booleanValue ? ((Color)instance.lineColor.getValue()).getAlpha() : ((Color)instance.color.getValue()).getAlpha()) : (instance.lineColor.booleanValue ? new Color(((Color)instance.lineColor.getValue()).getRed(), ((Color)instance.lineColor.getValue()).getGreen(), ((Color)instance.lineColor.getValue()).getBlue(), ((Color)instance.lineColor.getValue()).getAlpha()) : new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha()));
                    GL11.glPushMatrix();
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1032, 6913);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                    GlStateManager.blendFunc(770, 771);
                    RenderUtil.glColor(color);
                    GlStateManager.glLineWidth((float)instance.lineWidth.getValue());
                    this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
                    GL11.glPopAttrib();
                    GL11.glPopMatrix();
                }
                if (instance.fill.getValue()) {
                    final Color color2 = friend ? Managers.COLORS.getFriendColor(((Color)instance.color.getValue()).getAlpha()) : new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha());
                    GL11.glPushAttrib(1048575);
                    GL11.glDisable(3008);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glLineWidth(1.5f);
                    GL11.glEnable(2960);
                    if (instance.xqz.getValue()) {
                        GL11.glDisable(2929);
                        GL11.glDepthMask(false);
                    }
                    GL11.glEnable(10754);
                    RenderUtil.glColor(color2);
                    this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
                    if (instance.xqz.getValue()) {
                        GL11.glEnable(2929);
                        GL11.glDepthMask(true);
                    }
                    GL11.glEnable(3042);
                    GL11.glEnable(2896);
                    GL11.glEnable(3553);
                    GL11.glEnable(3008);
                    GL11.glPopAttrib();
                }
                if (instance.glint.getValue()) {
                    final Color color3 = friend ? Managers.COLORS.getFriendColor(((Color)instance.color.getValue()).getAlpha()) : new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha());
                    GL11.glPushMatrix();
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1032, 6914);
                    GL11.glDisable(2896);
                    GL11.glDepthRange(0.0, 0.1);
                    GL11.glEnable(3042);
                    RenderUtil.glColor(color3);
                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
                    final float n5 = abstractClientPlayer.ticksExisted + this.mc.getRenderPartialTicks();
                    this.mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                    for (int i = 0; i < 2; ++i) {
                        GlStateManager.matrixMode(5890);
                        GlStateManager.loadIdentity();
                        GL11.glScalef(1.0f, 1.0f, 1.0f);
                        GlStateManager.rotate(30.0f - i * 60.0f, 0.0f, 0.0f, 1.0f);
                        GlStateManager.translate(0.0f, n5 * (0.001f + i * 0.003f) * 20.0f, 0.0f);
                        GlStateManager.matrixMode(5888);
                        this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
                    }
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GlStateManager.matrixMode(5888);
                    GL11.glDisable(3042);
                    GL11.glDepthRange(0.0, 1.0);
                    GL11.glEnable(2896);
                    GL11.glPopAttrib();
                    GL11.glPopMatrix();
                }
            }
            else {
                this.renderItemInFirstPerson(abstractClientPlayer, n, n2, enumHand, n3, itemStack, n4);
            }
            this.injection = true;
        }
    }
    
    @Inject(method = { "rotateArm" }, at = { @At("HEAD") }, cancellable = true)
    public void rotateArmHook(final float n, final CallbackInfo callbackInfo) {
        final ItemModel instance = ItemModel.INSTANCE;
        if (instance.isOn() && (boolean)instance.noSway.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "transformSideFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void transformSideFirstPerson(final EnumHandSide enumHandSide, final float n, final CallbackInfo callbackInfo) {
        if (ItemModel.INSTANCE.isOn()) {
            GlStateManager.translate(((enumHandSide == EnumHandSide.RIGHT) ? 1 : -1) * 0.56f, -0.52f + (ItemModel.INSTANCE.doBob.getValue() ? n : 0.0f) * -0.6f, -0.72f);
            if (enumHandSide == EnumHandSide.RIGHT) {
                GlStateManager.translate((double)ItemModel.INSTANCE.mainX.getValue(), (double)ItemModel.INSTANCE.mainY.getValue(), (double)ItemModel.INSTANCE.mainZ.getValue());
            }
            else {
                GlStateManager.translate((double)ItemModel.INSTANCE.offX.getValue(), (double)ItemModel.INSTANCE.offY.getValue(), (double)ItemModel.INSTANCE.offZ.getValue());
            }
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "transformEatFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    private void transformEatFirstPerson(final float n, final EnumHandSide enumHandSide, final ItemStack itemStack, final CallbackInfo callbackInfo) {
        if (ItemModel.INSTANCE.isOn()) {
            if (!(boolean)ItemModel.INSTANCE.noEatAnimation.getValue()) {
                final float n2 = Minecraft.getMinecraft().player.getItemInUseCount() - n + 1.0f;
                final float n3 = n2 / itemStack.getMaxItemUseDuration();
                if (n3 < 0.8f) {
                    GlStateManager.translate(0.0f, MathHelper.abs(MathHelper.cos(n2 / 4.0f * 3.1415927f) * 0.1f), 0.0f);
                }
                final float n4 = 1.0f - (float)Math.pow(n3, 27.0);
                final int n5 = (enumHandSide == EnumHandSide.RIGHT) ? 1 : -1;
                GlStateManager.translate(n4 * 0.6f * n5 * (double)ItemModel.INSTANCE.eatX.getValue(), n4 * 0.5f * -(double)ItemModel.INSTANCE.eatY.getValue(), 0.0);
                GlStateManager.rotate(n5 * n4 * 90.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(n4 * 10.0f, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(n5 * n4 * 30.0f, 0.0f, 0.0f, 1.0f);
            }
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderOverlays" }, at = { @At("HEAD") }, cancellable = true)
    private void renderOverlaysInject(final float n, final CallbackInfo callbackInfo) {
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        if (freecamEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Redirect(method = { "setLightmap" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/client/entity/EntityPlayerSP;"))
    private EntityPlayerSP redirectLightmapPlayer(final Minecraft minecraft) {
        final FreecamEntityEvent freecamEntityEvent = new FreecamEntityEvent((Entity)minecraft.player);
        MinecraftForge.EVENT_BUS.post((Event)freecamEntityEvent);
        return (EntityPlayerSP)freecamEntityEvent.getEntity();
    }
    
    @Redirect(method = { "rotateArm" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/client/entity/EntityPlayerSP;"))
    private EntityPlayerSP rotateArmPlayer(final Minecraft minecraft) {
        final FreecamEntityEvent freecamEntityEvent = new FreecamEntityEvent((Entity)minecraft.player);
        MinecraftForge.EVENT_BUS.post((Event)freecamEntityEvent);
        return (EntityPlayerSP)freecamEntityEvent.getEntity();
    }
    
    @Redirect(method = { "renderItemInFirstPerson(F)V" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/client/entity/EntityPlayerSP;"))
    private EntityPlayerSP redirectPlayer(final Minecraft minecraft) {
        final FreecamEntityEvent freecamEntityEvent = new FreecamEntityEvent((Entity)minecraft.player);
        MinecraftForge.EVENT_BUS.post((Event)freecamEntityEvent);
        return (EntityPlayerSP)freecamEntityEvent.getEntity();
    }
    
    @Redirect(method = { "renderOverlays" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/client/entity/EntityPlayerSP;"))
    private EntityPlayerSP renderOverlaysPlayer(final Minecraft minecraft) {
        final FreecamEntityEvent freecamEntityEvent = new FreecamEntityEvent((Entity)minecraft.player);
        MinecraftForge.EVENT_BUS.post((Event)freecamEntityEvent);
        return (EntityPlayerSP)freecamEntityEvent.getEntity();
    }
}
