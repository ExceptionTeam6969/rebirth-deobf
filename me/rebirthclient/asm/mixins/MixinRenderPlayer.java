//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import me.rebirthclient.api.managers.*;
import org.lwjgl.opengl.*;

@Mixin({ RenderPlayer.class })
public class MixinRenderPlayer
{
    private final ResourceLocation amongUs;
    private final ResourceLocation rabbit;
    private final ResourceLocation freddy;
    
    public MixinRenderPlayer() {
        this.amongUs = new ResourceLocation("textures/rebirth/models/amongus.png");
        this.rabbit = new ResourceLocation("textures/rebirth/models/rabbit.png");
        this.freddy = new ResourceLocation("textures/rebirth/models/freddy.png");
    }
    
    @Inject(method = { "renderEntityName*" }, at = { @At("HEAD") }, cancellable = true)
    public void renderEntityNameHook(final AbstractClientPlayer abstractClientPlayer, final double n, final double n2, final double n3, final String s, final double n4, final CallbackInfo callbackInfo) {
        if (NameTags.INSTANCE.isOn() || ESP2D.INSTANCE.isOn()) {
            callbackInfo.cancel();
        }
    }
    
    @Redirect(method = { "doRender" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;isUser()Z"))
    private boolean isUserRedirect(final AbstractClientPlayer abstractClientPlayer) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        if (freecamEvent.isCanceled()) {
            return abstractClientPlayer.isUser() && abstractClientPlayer == getMinecraft.getRenderViewEntity();
        }
        return abstractClientPlayer.isUser();
    }
    
    @Inject(method = { "getEntityTexture" }, at = { @At("HEAD") }, cancellable = true)
    public void getEntityTexture(final AbstractClientPlayer abstractClientPlayer, final CallbackInfoReturnable callbackInfoReturnable) {
        if (Models.INSTANCE.isOn() && (!(boolean)Models.INSTANCE.onlySelf.getValue() || abstractClientPlayer == Minecraft.getMinecraft().player || (Managers.FRIENDS.isFriend(abstractClientPlayer.getName()) && (boolean)Models.INSTANCE.friends.getValue()))) {
            if (Models.INSTANCE.Mode.getValue() == Models.mode.AmongUs) {
                callbackInfoReturnable.setReturnValue((Object)this.amongUs);
            }
            if (Models.INSTANCE.Mode.getValue() == Models.mode.Rabbit) {
                callbackInfoReturnable.setReturnValue((Object)this.rabbit);
            }
            if (Models.INSTANCE.Mode.getValue() == Models.mode.Freddy) {
                callbackInfoReturnable.setReturnValue((Object)this.freddy);
            }
        }
        else {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            callbackInfoReturnable.setReturnValue((Object)abstractClientPlayer.getLocationSkin());
        }
    }
}
