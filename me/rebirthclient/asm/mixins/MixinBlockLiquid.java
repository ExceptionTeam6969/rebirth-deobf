//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.block.state.*;
import net.minecraft.block.properties.*;
import me.rebirthclient.mod.modules.impl.exploit.*;

@Mixin({ BlockLiquid.class })
public class MixinBlockLiquid extends Block
{
    protected MixinBlockLiquid(final Material material) {
        super(material);
    }
    
    @Inject(method = { "modifyAcceleration" }, at = { @At("HEAD") }, cancellable = true)
    public void modifyAccelerationHook(final World world, final BlockPos blockPos, final Entity entity, final Vec3d returnValue, final CallbackInfoReturnable callbackInfoReturnable) {
        if (Velocity.INSTANCE.isOn()) {
            callbackInfoReturnable.setReturnValue((Object)returnValue);
        }
    }
    
    @Inject(method = { "canCollideCheck" }, at = { @At("HEAD") }, cancellable = true)
    public void canCollideCheckHook(final IBlockState blockState, final boolean b, final CallbackInfoReturnable callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue((Object)((b && (int)blockState.getValue((IProperty)BlockLiquid.LEVEL) == 0) || LiquidInteract.INSTANCE.isOn()));
    }
}
