//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.entity.item.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderEnderCrystal.class })
public class MixinRenderEnderCrystal
{
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void renderModelBaseHook(final ModelBase modelBase, final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        final CrystalChams instance = CrystalChams.INSTANCE;
        final float n7 = instance.changeSpeed.getValue() ? (n2 * (float)instance.spinSpeed.getValue()) : n2;
        final float n8 = instance.changeSpeed.getValue() ? (((float)instance.floatFactor.getValue() == 0.0f) ? 0.15f : (n3 * (float)instance.floatFactor.getValue())) : n3;
        if (instance.isOn()) {
            GlStateManager.scale((float)instance.scale.getValue(), (float)instance.scale.getValue(), (float)instance.scale.getValue());
            if (instance.model.getValue() == CrystalChams.Model.VANILLA) {
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
            }
            else if (instance.model.getValue() == CrystalChams.Model.XQZ) {
                GL11.glEnable(32823);
                GlStateManager.enablePolygonOffset();
                GL11.glPolygonOffset(1.0f, -1000000.0f);
                if (instance.modelColor.booleanValue) {
                    RenderUtil.glColor(new Color(((Color)instance.modelColor.getValue()).getRed(), ((Color)instance.modelColor.getValue()).getGreen(), ((Color)instance.modelColor.getValue()).getBlue(), ((Color)instance.modelColor.getValue()).getAlpha()));
                }
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
                GL11.glDisable(32823);
                GlStateManager.disablePolygonOffset();
                GL11.glPolygonOffset(1.0f, 1000000.0f);
            }
            else if (Shader.INSTANCE.isOn() && Shader.crystalRender) {
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
            }
            else if (Shaders.INSTANCE.isOn() && Shaders.INSTANCE.crystal.getValue() != Shaders.Crystal1.None && !Shaders.INSTANCE.notShader) {
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
            }
            if (instance.wireframe.getValue()) {
                final Color color = instance.lineColor.booleanValue ? new Color(((Color)instance.lineColor.getValue()).getRed(), ((Color)instance.lineColor.getValue()).getGreen(), ((Color)instance.lineColor.getValue()).getBlue(), ((Color)instance.lineColor.getValue()).getAlpha()) : new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha());
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
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
            if (instance.fill.getValue()) {
                final Color color2 = new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha());
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
                modelBase.render(entity, n, n7, n8, n4, n5, n6);
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
            if ((boolean)instance.glint.getValue() && entity instanceof EntityEnderCrystal) {
                final Color color3 = new Color(((Color)instance.color.getValue()).getRed(), ((Color)instance.color.getValue()).getGreen(), ((Color)instance.color.getValue()).getBlue(), ((Color)instance.color.getValue()).getAlpha());
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glPolygonMode(1032, 6914);
                GL11.glDisable(2896);
                GL11.glDepthRange(0.0, 0.1);
                GL11.glEnable(3042);
                RenderUtil.glColor(color3);
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
                final float n9 = entity.ticksExisted + Wrapper.mc.getRenderPartialTicks();
                Wrapper.mc.getRenderManager().renderEngine.bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                for (int i = 0; i < 2; ++i) {
                    GlStateManager.matrixMode(5890);
                    GlStateManager.loadIdentity();
                    GL11.glScalef(1.0f, 1.0f, 1.0f);
                    GlStateManager.rotate(30.0f - i * 60.0f, 0.0f, 0.0f, 1.0f);
                    GlStateManager.translate(0.0f, n9 * (0.001f + i * 0.003f) * 20.0f, 0.0f);
                    GlStateManager.matrixMode(5888);
                    modelBase.render(entity, n, n7, n8, n4, n5, n6);
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
            GlStateManager.scale(1.0f / (float)instance.scale.getValue(), 1.0f / (float)instance.scale.getValue(), 1.0f / (float)instance.scale.getValue());
        }
        else {
            modelBase.render(entity, n, n2, n3, n4, n5, n6);
        }
    }
}
