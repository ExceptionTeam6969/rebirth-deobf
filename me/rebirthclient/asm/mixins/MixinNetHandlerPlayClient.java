//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.network.*;
import net.minecraft.util.text.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.impl.misc.*;
import me.rebirthclient.mod.commands.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Inject(method = { "onDisconnect" }, at = { @At("HEAD") }, cancellable = true)
    private void onDisconnect(final ITextComponent textComponent, final CallbackInfo callbackInfo) {
        if (Wrapper.mc.player != null && Wrapper.mc.world != null && SilentDisconnect.INSTANCE.isOn()) {
            Command.sendMessage(textComponent.getFormattedText());
            callbackInfo.cancel();
        }
    }
}
