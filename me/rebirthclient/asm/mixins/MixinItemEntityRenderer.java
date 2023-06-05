//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import java.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.item.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderEntityItem.class })
public abstract class MixinItemEntityRenderer extends Render
{
    private static final float RAD_TO_DEG = 57.29578f;
    @Shadow
    @Final
    private Random random;
    @Shadow
    private RenderItem itemRenderer;
    
    protected MixinItemEntityRenderer(final RenderManager renderManager) {
        super(renderManager);
    }
    
    @Shadow
    public abstract int getModelCount(final ItemStack p0);
    
    @Inject(at = { @At("HEAD") }, method = { "doRender" }, cancellable = true)
    public void render(final EntityItem entityItem, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        final ItemStack getItem = entityItem.getItem();
        if (!ItemPhysics.INSTANCE.isOn() || getItem.getItem() == null) {
            return;
        }
        final int getModelCount = this.getModelCount(getItem);
        this.random.setSeed(Item.getIdFromItem(getItem.getItem()) + getItem.getItemDamage());
        final float n6 = ((entityItem.getAge() + n5) / 20.0f + entityItem.height) / 20.0f * (float)ItemPhysics.INSTANCE.rotateSpeed.getValue();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)n, (float)n2, (float)n3);
        if (entityItem.getItem().item instanceof ItemShulkerBox) {
            GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f + (float)ItemPhysics.INSTANCE.shulkerBox.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f + (float)ItemPhysics.INSTANCE.shulkerBox.getValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f + (float)ItemPhysics.INSTANCE.shulkerBox.getValue());
        }
        else if (entityItem.getItem().item instanceof ItemBlock) {
            GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f, (float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f, (float)ItemPhysics.INSTANCE.Scaling.getValue() + 1.1f);
        }
        else {
            GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue() + 0.3f, (float)ItemPhysics.INSTANCE.Scaling.getValue() + 0.3f, (float)ItemPhysics.INSTANCE.Scaling.getValue() + 0.3f);
        }
        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(entityItem.rotationYaw * 57.29578f, 0.0f, 0.0f, 1.0f);
        final IBakedModel getItemModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(getItem);
        this.rotateX(entityItem, n6);
        if (getItemModel.isGui3d()) {
            GlStateManager.translate(0.0f, -0.2f, -0.08f);
        }
        else if (entityItem.getEntityWorld().getBlockState(entityItem.getPosition()).getBlock() == Blocks.SNOW || entityItem.getEntityWorld().getBlockState(entityItem.getPosition().down()).getBlock() == Blocks.SOUL_SAND) {
            GlStateManager.translate(0.0f, 0.0f, -0.14f);
        }
        else {
            GlStateManager.translate(0.0f, 0.0f, -0.04f);
        }
        final float n7 = 0.2f;
        if (getItemModel.isGui3d()) {
            GlStateManager.translate(0.0f, n7, 0.0f);
        }
        GlStateManager.rotate(entityItem.rotationPitch * 57.29578f, 0.0f, 1.0f, 0.0f);
        if (getItemModel.isGui3d()) {
            GlStateManager.translate(0.0f, -n7, 0.0f);
        }
        if (!getItemModel.isGui3d()) {
            GlStateManager.translate(-0.0f * (getModelCount - 1) * 0.5f, -0.0f * (getModelCount - 1) * 0.5f, -0.09375f * (getModelCount - 1) * 0.5f);
        }
        for (int i = 0; i < getModelCount; ++i) {
            GlStateManager.pushMatrix();
            if (i > 0 && getItemModel.isGui3d()) {
                GlStateManager.translate((this.random.nextFloat() * 2.0f - 1.0f) * 0.15f, (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f, (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f);
            }
            getItemModel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
            this.itemRenderer.renderItem(getItem, getItemModel);
            GlStateManager.popMatrix();
            if (!getItemModel.isGui3d()) {
                GlStateManager.translate(0.0f, 0.0f, 0.09375f);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        this.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
        callbackInfo.cancel();
    }
    
    private void rotateX(final EntityItem entityItem, final float n) {
        if (entityItem.onGround) {
            return;
        }
        entityItem.rotationPitch += n * 2.0f;
    }
}
