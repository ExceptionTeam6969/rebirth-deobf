//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.managers.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Timer.class })
public class MixinTimer
{
    @Shadow
    public float elapsedPartialTicks;
    
    @Inject(method = { "updateTimer" }, at = { @At(value = "FIELD", target = "net/minecraft/util/Timer.elapsedPartialTicks:F", ordinal = 1) })
    public void updateTimer(final CallbackInfo callbackInfo) {
        this.elapsedPartialTicks *= Managers.TIMER.get();
    }
}
