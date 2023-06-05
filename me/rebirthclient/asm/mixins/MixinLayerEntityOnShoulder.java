//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ LayerEntityOnShoulder.class })
public class MixinLayerEntityOnShoulder
{
    @Inject(method = { "doRenderLayer*" }, at = { @At("HEAD") }, cancellable = true)
    public void doRenderLayerHook(final EntityPlayer entityPlayer, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final CallbackInfo callbackInfo) {
        final NoLag instance = NoLag.INSTANCE;
        if (instance.isOn() && (boolean)instance.parrots.getValue()) {
            callbackInfo.cancel();
        }
    }
}
