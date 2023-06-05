//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderManager.class })
public class MixinRenderManager
{
    @Inject(method = { "renderEntity" }, at = { @At("HEAD") }, cancellable = true)
    public void renderEntityHook(final Entity entity, final double n, final double n2, final double n3, final float n4, final float n5, final boolean b, final CallbackInfo callbackInfo) {
        final RenderEntityEvent renderEntityEvent = new RenderEntityEvent(0, entity, n, n2, n3, n4, n5);
        MinecraftForge.EVENT_BUS.post((Event)renderEntityEvent);
        if (renderEntityEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}
