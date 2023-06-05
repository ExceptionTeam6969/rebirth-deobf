//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.client.*;

@Mixin({ LayerArmorBase.class })
public class MixinLayerArmorBase
{
    @Shadow
    private void renderArmorLayer(final EntityLivingBase entityLivingBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final EntityEquipmentSlot entityEquipmentSlot) {
    }
    
    @Inject(method = { "doRenderLayer" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/LayerArmorBase;renderArmorLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFFLnet/minecraft/inventory/EntityEquipmentSlot;)V", shift = At.Shift.BEFORE) }, cancellable = true)
    public void doRenderLayerHook(final EntityLivingBase entityLivingBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final CallbackInfo callbackInfo) {
        final Chams instance = Chams.INSTANCE;
        if (instance.isOn() && entityLivingBase instanceof EntityPlayer) {
            callbackInfo.cancel();
            final float n8 = (instance.isOn() && (boolean)instance.noInterp.getValue()) ? 0.0f : n;
            final float n9 = (instance.isOn() && (boolean)instance.noInterp.getValue()) ? 0.0f : n2;
            if (!(boolean)instance.self.getValue() && entityLivingBase instanceof EntityPlayerSP) {
                this.renderLayers(entityLivingBase, n, n2, n3, n4, n5, n6, n7);
                return;
            }
            this.renderLayers(entityLivingBase, n8, n9, n3, n4, n5, n6, n7);
        }
        else {
            this.renderLayers(entityLivingBase, n, n2, n3, n4, n5, n6, n7);
        }
    }
    
    @Inject(method = { "doRenderLayer" }, at = { @At("HEAD") }, cancellable = true)
    public void doRenderLayer(final EntityLivingBase entityLivingBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final CallbackInfo callbackInfo) {
        if (Models.INSTANCE.isOn() && (boolean)Models.INSTANCE.onlySelf.getValue() && entityLivingBase == Minecraft.getMinecraft().player) {
            callbackInfo.cancel();
        }
        else if (Models.INSTANCE.isOn() && !(boolean)Models.INSTANCE.onlySelf.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    private void renderLayers(final EntityLivingBase entityLivingBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        this.renderArmorLayer(entityLivingBase, n, n2, n3, n4, n5, n6, n7, EntityEquipmentSlot.CHEST);
        this.renderArmorLayer(entityLivingBase, n, n2, n3, n4, n5, n6, n7, EntityEquipmentSlot.LEGS);
        this.renderArmorLayer(entityLivingBase, n, n2, n3, n4, n5, n6, n7, EntityEquipmentSlot.FEET);
        this.renderArmorLayer(entityLivingBase, n, n2, n3, n4, n5, n6, n7, EntityEquipmentSlot.HEAD);
    }
}
