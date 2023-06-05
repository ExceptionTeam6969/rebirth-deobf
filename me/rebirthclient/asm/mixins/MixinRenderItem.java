//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.util.*;

@Mixin({ RenderItem.class })
public class MixinRenderItem
{
    final Minecraft mc;
    
    public MixinRenderItem() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @ModifyArg(method = { "renderEffect" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
    private int renderEffect(final int n) {
        return GlintModify.INSTANCE.isOn() ? GlintModify.getColor().getRGB() : n;
    }
    
    @Inject(method = { "renderItemModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE) })
    private void renderCustom(final ItemStack itemStack, final IBakedModel bakedModel, final ItemCameraTransforms.TransformType transformType, final boolean b, final CallbackInfo callbackInfo) {
        final ItemModel instance = ItemModel.INSTANCE;
        final float n = 1.0f;
        final float n2 = 0.0f;
        final float n3 = 0.0f;
        if (instance.isOn()) {
            GlStateManager.scale(n, n, n);
            if (this.mc.player.getActiveItemStack() != itemStack) {
                GlStateManager.translate(n2, n3, 0.0f);
            }
        }
    }
    
    @Inject(method = { "renderItemModel" }, at = { @At("HEAD") })
    public void renderItem(final ItemStack itemStack, final IBakedModel bakedModel, final ItemCameraTransforms.TransformType transformType, final boolean b, final CallbackInfo callbackInfo) {
        final ItemModel instance = ItemModel.INSTANCE;
        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND && this.mc.player.isHandActive() && this.mc.player.getActiveHand() == EnumHand.OFF_HAND) {
                return;
            }
            if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND && this.mc.player.isHandActive() && this.mc.player.getActiveHand() == EnumHand.MAIN_HAND) {
                return;
            }
        }
        if (instance.isOn() && ((boolean)instance.spinX.getValue() || (boolean)instance.spinY.getValue())) {
            GlStateManager.rotate(instance.angle, ((boolean)instance.spinX.getValue()) ? instance.angle : 0.0f, ((boolean)instance.spinY.getValue()) ? instance.angle : 0.0f, 0.0f);
        }
    }
}
