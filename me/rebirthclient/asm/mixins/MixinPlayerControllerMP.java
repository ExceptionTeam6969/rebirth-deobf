//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import me.rebirthclient.mod.modules.impl.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.api.events.impl.*;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    public float getPlayerRelativeBlockHardnessHook(final IBlockState blockState, final EntityPlayer entityPlayer, final World world, final BlockPos blockPos) {
        return blockState.getPlayerRelativeBlockHardness(entityPlayer, world, blockPos) * ((TpsSync.INSTANCE.isOn() && (boolean)TpsSync.INSTANCE.mining.getValue()) ? (1.0f / Managers.SERVER.getTpsFactor()) : 1.0f);
    }
    
    @Inject(method = { "processRightClickBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void processRightClickBlock(final EntityPlayerSP entityPlayerSP, final WorldClient worldClient, final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final EnumHand enumHand, final CallbackInfoReturnable callbackInfoReturnable) {
        final RightClickBlockEvent rightClickBlockEvent = new RightClickBlockEvent(blockPos, enumHand, Minecraft.instance.player.getHeldItem(enumHand));
        MinecraftForge.EVENT_BUS.post((Event)rightClickBlockEvent);
        if (rightClickBlockEvent.isCanceled()) {
            callbackInfoReturnable.cancel();
        }
    }
    
    @Inject(method = { "onPlayerDestroyBlock" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playEvent(ILnet/minecraft/util/math/BlockPos;I)V") }, cancellable = true)
    private void onPlayerDestroyBlock(final BlockPos blockPos, final CallbackInfoReturnable callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new BreakBlockEvent(blockPos));
    }
    
    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void clickBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable callbackInfoReturnable) {
        final BlockEvent blockEvent = new BlockEvent(blockPos, enumFacing);
        MinecraftForge.EVENT_BUS.post((Event)blockEvent);
        if (blockEvent.isCanceled()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlayerDamageBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable callbackInfoReturnable) {
        final BlockEvent blockEvent = new BlockEvent(blockPos, enumFacing);
        MinecraftForge.EVENT_BUS.post((Event)blockEvent);
        if (blockEvent.isCanceled()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
}
