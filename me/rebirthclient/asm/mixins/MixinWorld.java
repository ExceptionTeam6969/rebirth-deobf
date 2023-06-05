//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.client.*;
import net.minecraft.world.chunk.*;
import java.util.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;

@Mixin({ World.class })
public class MixinWorld
{
    @Inject(method = { "checkLightFor" }, at = { @At("HEAD") }, cancellable = true)
    private void updateLightmapHook(final EnumSkyBlock enumSkyBlock, final BlockPos blockPos, final CallbackInfoReturnable callbackInfoReturnable) {
        final NoRender instance = NoRender.INSTANCE;
        if (enumSkyBlock == EnumSkyBlock.SKY && instance.isOn() && (boolean)instance.skyLight.getValue() && !Minecraft.getMinecraft().isSingleplayer()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
    
    @Redirect(method = { "getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;getEntitiesOfTypeWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lcom/google/common/base/Predicate;)V"))
    public void getEntitiesOfTypeWithinAABBHook(final Chunk chunk, final Class clazz, final AxisAlignedBB axisAlignedBB, final List list, final Predicate predicate) {
        try {
            chunk.getEntitiesOfTypeWithinAABB(clazz, axisAlignedBB, list, predicate);
        }
        catch (Exception ex) {}
    }
    
    @Redirect(method = { "handleMaterialAcceleration" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isPushedByWater()Z"))
    public boolean isPushedbyWaterHook(final Entity entity) {
        final PushEvent pushEvent = new PushEvent(2, entity);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        return entity.isPushedByWater() && !pushEvent.isCanceled();
    }
    
    @Inject(method = { "onEntityAdded" }, at = { @At("HEAD") })
    private void onEntityAdded(final Entity entity, final CallbackInfo callbackInfo) {
    }
    
    @Inject(method = { "getSkyColor" }, at = { @At("HEAD") }, cancellable = true)
    public void getSkyColorHook(final Entity entity, final float n, final CallbackInfoReturnable callbackInfoReturnable) {
        final RenderSkyEvent renderSkyEvent = new RenderSkyEvent();
        MinecraftForge.EVENT_BUS.post((Event)renderSkyEvent);
        if (renderSkyEvent.isCanceled()) {
            callbackInfoReturnable.cancel();
            callbackInfoReturnable.setReturnValue((Object)new Vec3d(renderSkyEvent.getColor().getRed() / 255.0, renderSkyEvent.getColor().getGreen() / 255.0, renderSkyEvent.getColor().getBlue() / 255.0));
        }
    }
}
