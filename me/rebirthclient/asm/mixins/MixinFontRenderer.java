//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ FontRenderer.class })
public abstract class MixinFontRenderer
{
    @Shadow
    protected abstract void renderStringAtPos(final String p0, final boolean p1);
    
    @Inject(method = { "drawString(Ljava/lang/String;FFIZ)I" }, at = { @At("HEAD") }, cancellable = true)
    public void renderStringHook(final String s, final float n, final float n2, final int n3, final boolean b, final CallbackInfoReturnable callbackInfoReturnable) {
        if (FontMod.INSTANCE == null) {
            FontMod.INSTANCE = new FontMod();
        }
        final FontMod instance = FontMod.INSTANCE;
        if (instance.isOn() && (boolean)instance.global.getValue() && Managers.TEXT != null) {
            callbackInfoReturnable.setReturnValue((Object)(int)Managers.TEXT.drawString(s, n, n2, n3, b));
        }
    }
    
    @Redirect(method = { "renderString(Ljava/lang/String;FFIZ)I" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
    public void renderStringAtPosHook(final FontRenderer fontRenderer, String s, final boolean b) {
        if (NameProtect.INSTANCE == null) {
            NameProtect.INSTANCE = new NameProtect();
        }
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(Wrapper.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        this.renderStringAtPos(s, b);
    }
}
