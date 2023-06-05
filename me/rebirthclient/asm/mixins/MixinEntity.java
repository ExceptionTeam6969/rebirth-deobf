//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.util.math.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;

@Mixin(value = { Entity.class }, priority = Integer.MAX_VALUE)
public abstract class MixinEntity
{
    @Shadow
    public float stepHeight;
    
    @Shadow
    @Override
    public abstract boolean equals(final Object p0);
    
    @Shadow
    public abstract AxisAlignedBB getEntityBoundingBox();
    
    @Shadow
    public abstract boolean isSneaking();
    
    @Inject(method = { "turn" }, at = { @At("HEAD") }, cancellable = true)
    public void onTurnHook(final float n, final float n2, final CallbackInfo callbackInfo) {
        final TurnEvent turnEvent = new TurnEvent(n, n2);
        MinecraftForge.EVENT_BUS.post((Event)turnEvent);
        if (turnEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "rayTrace" }, at = { @At("HEAD") }, cancellable = true)
    public void rayTrace$Inject$INVOKE$rayTraceBlocks(final double n, final float n2, final CallbackInfoReturnable callbackInfoReturnable) {
        if (this == Minecraft.getMinecraft().player) {
            GhostHand.handleRayTrace(n, n2, callbackInfoReturnable);
        }
    }
    
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocityHook(final Entity entity, final double n, final double n2, final double n3) {
        final PushEvent pushEvent = new PushEvent(entity, n, n2, n3, true);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (pushEvent.isCanceled()) {
            return;
        }
        entity.motionX += pushEvent.x;
        entity.motionY += pushEvent.y;
        entity.motionZ += pushEvent.z;
        entity.isAirBorne = pushEvent.airbone;
    }
    
    @Inject(method = { "move" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.BEFORE, ordinal = 0) })
    public void onMove(final MoverType moverType, final double n, final double n2, final double n3, final CallbackInfo callbackInfo) {
        if (((Entity)this).equals((Object)Minecraft.getMinecraft().player)) {
            final StepEvent stepEvent = new StepEvent(this.getEntityBoundingBox(), this.stepHeight);
            MinecraftForge.EVENT_BUS.post((Event)stepEvent);
            if (stepEvent.isCanceled()) {
                this.stepHeight = stepEvent.getHeight();
            }
        }
    }
}
