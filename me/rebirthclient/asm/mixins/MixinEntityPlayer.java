//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import me.rebirthclient.mod.modules.impl.player.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.managers.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mixin({ EntityPlayer.class })
public abstract class MixinEntityPlayer extends EntityLivingBase
{
    public MixinEntityPlayer(final World world, final GameProfile gameProfile) {
        super(world);
    }
    
    @Inject(method = { "getCooldownPeriod" }, at = { @At("HEAD") }, cancellable = true)
    private void getCooldownPeriodHook(final CallbackInfoReturnable callbackInfoReturnable) {
        final TpsSync instance = TpsSync.INSTANCE;
        if (instance.isOn() && (boolean)instance.attack.getValue()) {
            callbackInfoReturnable.setReturnValue((Object)(float)(1.0 / EntityPlayer.class.cast(this).getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue() * 20.0 * Managers.SERVER.getTpsFactor()));
        }
    }
    
    @Inject(method = { "jump" }, at = { @At("HEAD") })
    public void onJump(final CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().player.getName().equals(this.getName())) {
            MinecraftForge.EVENT_BUS.post((Event)new JumpEvent(this.motionX, this.motionY));
        }
    }
}
