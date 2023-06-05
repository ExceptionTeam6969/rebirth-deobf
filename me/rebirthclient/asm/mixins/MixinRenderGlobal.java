//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import me.rebirthclient.asm.accessors.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderGlobal.class })
public abstract class MixinRenderGlobal implements IRenderGlobal
{
    @Nonnull
    @Accessor("damagedBlocks")
    public abstract Map getDamagedBlocks();
    
    @Inject(method = { "sendBlockBreakProgress" }, at = { @At("HEAD") })
    public void onSendingBlockBreakProgressPre(final int n, final BlockPos blockPos, final int n2, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new DamageBlockEvent(blockPos, n2, n));
    }
}
