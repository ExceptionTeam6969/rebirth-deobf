//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.model.*;
import net.minecraft.inventory.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ LayerBipedArmor.class })
public class MixinLayerBipedArmor
{
    @Inject(method = { "setModelSlotVisible*" }, at = { @At("HEAD") }, cancellable = true)
    protected void setModelSlotVisible(final ModelBiped modelBiped, final EntityEquipmentSlot entityEquipmentSlot, final CallbackInfo callbackInfo) {
        if (NoRender.INSTANCE.isOff() || !(boolean)NoRender.INSTANCE.armor.getValue()) {
            return;
        }
        callbackInfo.cancel();
        modelBiped.bipedHead.showModel = false;
        modelBiped.bipedHeadwear.showModel = false;
        modelBiped.bipedRightArm.showModel = false;
        modelBiped.bipedLeftArm.showModel = false;
        modelBiped.bipedBody.showModel = false;
        modelBiped.bipedRightLeg.showModel = false;
        modelBiped.bipedLeftLeg.showModel = false;
    }
}
