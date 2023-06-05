//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.resources.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ Locale.class })
public class MixinLocale
{
    @Inject(method = { "checkUnicode" }, at = { @At("HEAD") }, cancellable = true)
    public void checkUnicode(final CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }
}
