//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.client.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.scoreboard.*;
import me.rebirthclient.mod.modules.impl.render.*;

@Mixin({ GuiIngame.class })
public abstract class MixinGuiInGame extends Gui
{
    @Inject(method = { "renderPotionEffects" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPotionEffectsHook(final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        final HUD instance = HUD.INSTANCE;
        if ((boolean)instance.potionIcons.getValue() && instance.isOn()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderScoreboard" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderScoreboardHook(final ScoreObjective scoreObjective, final ScaledResolution scaledResolution, final CallbackInfo callbackInfo) {
        final NoLag instance = NoLag.INSTANCE;
        if ((boolean)instance.scoreBoards.getValue() && instance.isOn()) {
            callbackInfo.cancel();
        }
    }
}
