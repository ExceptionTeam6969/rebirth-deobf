//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import me.rebirthclient.mod.modules.impl.client.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.crash.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.client.entity.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.client.multiplayer.*;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Inject(method = { "getLimitFramerate" }, at = { @At("HEAD") }, cancellable = true)
    public void getLimitFramerateHook(final CallbackInfoReturnable callbackInfoReturnable) {
        final UnfocusedCPU instance = UnfocusedCPU.INSTANCE;
        try {
            if (instance.isOn() && !Display.isActive()) {
                callbackInfoReturnable.setReturnValue(instance.unfocusedFps.getValue());
            }
        }
        catch (Exception ex) {}
    }
    
    @Inject(method = { "shutdownMinecraftApplet" }, at = { @At("HEAD") })
    private void stopClient(final CallbackInfo callbackInfo) {
        this.unload();
    }
    
    @Redirect(method = { "run" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
    public void displayCrashReport(final Minecraft minecraft, final CrashReport crashReport) {
        this.unload();
    }
    
    private void unload() {
        Rebirth.LOGGER.info("Initiated client shutdown.");
        Managers.onUnload();
        Rebirth.LOGGER.info("Finished client shutdown.");
    }
    
    @Redirect(method = { "sendClickBlockToController" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
    private boolean isHandActiveWrapper(final EntityPlayerSP entityPlayerSP) {
        return !MultiTask.INSTANCE.isOn() && entityPlayerSP.isHandActive();
    }
    
    @Redirect(method = { "rightClickMouse" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z", ordinal = 0))
    private boolean isHittingBlockHook(final PlayerControllerMP playerControllerMP) {
        return !MultiTask.INSTANCE.isOn() && playerControllerMP.getIsHittingBlock();
    }
}
