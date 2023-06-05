//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.tileentity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ TileEntityShulkerBox.class })
public abstract class MixinTileEntityShulkerBox
{
    @Inject(method = { "moveCollidedEntities" }, at = { @At("HEAD") }, cancellable = true)
    public void moveCollidedEntitiesHook(final CallbackInfo callbackInfo) {
        if (Velocity.INSTANCE.isOn()) {
            callbackInfo.cancel();
        }
    }
}
