//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import me.rebirthclient.api.util.*;
import org.spongepowered.asm.mixin.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mixin({ EntityLivingBase.class })
public class MixinEntityLivingBase implements Wrapper
{
    @Shadow
    private int jumpTicks;
    
    @Inject(method = { "onLivingUpdate" }, at = { @At("HEAD") })
    private void headLiving(final CallbackInfo callbackInfo) {
        if (NoJumpDelay.INSTANCE.isOn()) {
            this.jumpTicks = 0;
        }
    }
    
    @Inject(method = { "getArmSwingAnimationEnd" }, at = { @At("HEAD") }, cancellable = true)
    private void getArmSwingAnimationEnd(final CallbackInfoReturnable callbackInfoReturnable) {
        final ItemModel instance = ItemModel.INSTANCE;
        if (instance.isOn() && (boolean)instance.customSwing.getValue() && instance.swing.getValue() == ItemModel.Swing.SERVER) {
            callbackInfoReturnable.setReturnValue((Object)(-1));
        }
        else if (instance.isOn() && (boolean)instance.slowSwing.getValue()) {
            callbackInfoReturnable.setReturnValue(instance.swingSpeed.getValue());
        }
    }
    
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    public void onTravelPre(final float n, final float n2, final float n3, final CallbackInfo callbackInfo) {
        final ElytraEvent elytraEvent = new ElytraEvent((Entity)this);
        MinecraftForge.EVENT_BUS.post((Event)elytraEvent);
        if (elytraEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}
