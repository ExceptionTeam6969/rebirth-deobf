//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.misc.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiPlayerTabOverlay.class })
public class MixinGuiPlayerTabOverlay extends Gui
{
    @Redirect(method = { "renderPlayerlist" }, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;"))
    public List subListHook(final List list, final int n, final int n2) {
        return list.subList(n, ExtraTab.INSTANCE.isOn() ? Math.min((int)ExtraTab.INSTANCE.size.getValue(), list.size()) : n2);
    }
    
    @Inject(method = { "getPlayerName" }, at = { @At("HEAD") }, cancellable = true)
    public void getPlayerNameHook(final NetworkPlayerInfo networkPlayerInfo, final CallbackInfoReturnable callbackInfoReturnable) {
        if (TabFriends.INSTANCE.isOn()) {
            callbackInfoReturnable.setReturnValue((Object)TabFriends.getPlayerName(networkPlayerInfo));
        }
    }
}
