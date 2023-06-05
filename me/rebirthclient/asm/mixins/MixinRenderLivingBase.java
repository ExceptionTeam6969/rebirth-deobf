//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRenderLivingBase extends Render
{
    private float prevRenderYawOffset;
    private float renderYawOffset;
    private float prevRotationYawHead;
    private float rotationYawHead;
    private float prevRotationPitch;
    private float rotationPitch;
    
    protected MixinRenderLivingBase(final RenderManager renderManager) {
        super(renderManager);
    }
    
    @Inject(method = { "doRender*" }, at = { @At("HEAD") })
    public void doRenderHookHead(final EntityLivingBase entityLivingBase, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        if (entityLivingBase instanceof EntityPlayerSP && Rotations.INSTANCE.check()) {
            this.prevRenderYawOffset = entityLivingBase.prevRenderYawOffset;
            this.renderYawOffset = entityLivingBase.renderYawOffset;
            this.prevRotationYawHead = entityLivingBase.prevRotationYawHead;
            this.rotationYawHead = entityLivingBase.rotationYawHead;
            this.prevRotationPitch = entityLivingBase.prevRotationPitch;
            this.rotationPitch = entityLivingBase.rotationPitch;
            entityLivingBase.prevRenderYawOffset = Rotations.getPrevRenderYawOffset();
            entityLivingBase.renderYawOffset = Rotations.getRenderYawOffset();
            entityLivingBase.prevRotationYawHead = Rotations.getPrevRotationYawHead();
            entityLivingBase.rotationYawHead = Rotations.getRotationYawHead();
            entityLivingBase.prevRotationPitch = Rotations.getPrevPitch();
            entityLivingBase.rotationPitch = Rotations.getRenderPitch();
        }
    }
    
    @Inject(method = { "doRender*" }, at = { @At("RETURN") })
    public void doRenderHookReturn(final EntityLivingBase entityLivingBase, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        if (entityLivingBase instanceof EntityPlayerSP && Rotations.INSTANCE.check()) {
            entityLivingBase.prevRenderYawOffset = this.prevRenderYawOffset;
            entityLivingBase.renderYawOffset = this.renderYawOffset;
            entityLivingBase.prevRotationYawHead = this.prevRotationYawHead;
            entityLivingBase.rotationYawHead = this.rotationYawHead;
            entityLivingBase.prevRotationPitch = this.prevRotationPitch;
            entityLivingBase.rotationPitch = this.rotationPitch;
        }
    }
    
    @Inject(method = { "renderModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = At.Shift.BEFORE) }, cancellable = true)
    private void renderModelHook(final EntityLivingBase entityLivingBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final CallbackInfo callbackInfo) {
        final Chams instance = Chams.INSTANCE;
        final ModelBase getMainModel = RenderLivingBase.class.cast(this).getMainModel();
        if (instance.isOn() && entityLivingBase instanceof EntityPlayer) {
            callbackInfo.cancel();
            final boolean friend = Managers.FRIENDS.isFriend(entityLivingBase.getName());
            final float n7 = instance.noInterp.getValue() ? 0.0f : n;
            final float n8 = instance.noInterp.getValue() ? 0.0f : n2;
            if (instance.sneak.getValue()) {
                entityLivingBase.setSneaking(true);
            }
            if (!(boolean)instance.self.getValue() && entityLivingBase instanceof EntityPlayerSP) {
                getMainModel.render((Entity)entityLivingBase, n, n2, n3, n4, n5, n6);
                return;
            }
            if (instance.model.getValue() == Chams.Model.VANILLA) {
                getMainModel.render((Entity)entityLivingBase, n7, n8, n3, n4, n5, n6);
            }
            else if (instance.model.getValue() == Chams.Model.XQZ) {
                GL11.glEnable(32823);
                GlStateManager.enablePolygonOffset();
                GL11.glPolygonOffset(1.0f, -1000000.0f);
                if (instance.modelColor.booleanValue) {
                    RenderUtil.glColor(friend ? Managers.COLORS.getFriendColor(((Color)instance.modelColor.getValue()).getAlpha()) : new Color(((Color)instance.modelColor.getValue()).getRed(), ((Color)instance.modelColor.getValue()).getGreen(), ((Color)instance.modelColor.getValue()).getBlue(), ((Color)instance.modelColor.getValue()).getAlpha()));
                }
                getMainModel.render((Entity)entityLivingBase, n7, n8, n3, n4, n5, n6);
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
                getMainModel.render((Entity)entityLivingBase, n7, n8, n3, n4, n5, n6);
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
                getMainModel.render((Entity)entityLivingBase, n7, n8, n3, n4, n5, n6);
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
                final float n9 = entityLivingBase.ticksExisted + Wrapper.mc.getRenderPartialTicks();
                Wrapper.mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                for (int i = 0; i < 2; ++i) {
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GL11.glScalef(1.0f, 1.0f, 1.0f);
                    GlStateManager.rotate(30.0f - i * 60.0f, 0.0f, 0.0f, 1.0f);
                    GlStateManager.translate(0.0f, n9 * (0.001f + i * 0.003f) * 20.0f, 0.0f);
                    GlStateManager.matrixMode(5888);
                    getMainModel.render((Entity)entityLivingBase, n7, n8, n3, n4, n5, n6);
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
            getMainModel.render((Entity)entityLivingBase, n, n2, n3, n4, n5, n6);
        }
    }
}
