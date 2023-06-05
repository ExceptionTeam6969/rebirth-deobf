//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraft.stats.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.*;

@Mixin(value = { EntityPlayerSP.class }, priority = 9998)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer
{
    @Shadow
    @Final
    public NetHandlerPlayClient connection;
    @Shadow
    protected Minecraft mc;
    @Shadow
    private boolean serverSprintState;
    @Shadow
    private boolean serverSneakState;
    @Shadow
    private double lastReportedPosX;
    @Shadow
    private double lastReportedPosY;
    @Shadow
    private double lastReportedPosZ;
    @Shadow
    private float lastReportedYaw;
    @Shadow
    private float lastReportedPitch;
    @Shadow
    private int positionUpdateTicks;
    @Shadow
    private boolean autoJumpEnabled;
    @Shadow
    private boolean prevOnGround;
    
    public MixinEntityPlayerSP(final Minecraft minecraft, final World world, final NetHandlerPlayClient netHandlerPlayClient, final StatisticsManager statisticsManager, final RecipeBook recipeBook) {
        super(world, netHandlerPlayClient.getGameProfile());
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreenHook(final EntityPlayerSP entityPlayerSP) {
        if (!BetterPortal.INSTANCE.isOn() || !(boolean)BetterPortal.INSTANCE.portalChat.getValue()) {
            entityPlayerSP.closeScreen();
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void displayGuiScreenHook(final Minecraft minecraft, final GuiScreen guiScreen) {
        if (!BetterPortal.INSTANCE.isOn() || !(boolean)BetterPortal.INSTANCE.portalChat.getValue()) {
            minecraft.displayGuiScreen(guiScreen);
        }
    }
    
    @Inject(method = { "pushOutOfBlocks" }, at = { @At("HEAD") }, cancellable = true)
    private void pushOutOfBlocksHook(final double n, final double n2, final double n3, final CallbackInfoReturnable callbackInfoReturnable) {
        final PushEvent pushEvent = new PushEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (!pushEvent.isCanceled()) {
            return;
        }
        callbackInfoReturnable.setReturnValue((Object)false);
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(final AbstractClientPlayer abstractClientPlayer, final MoverType moverType, final double n, final double n2, final double n3) {
        final MoveEvent moveEvent = new MoveEvent(0, moverType, n, n2, n3);
        MinecraftForge.EVENT_BUS.post((Event)moveEvent);
        if (!moveEvent.isCanceled()) {
            super.move(moveEvent.getType(), moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    private void preMotion(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent(0));
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    private void postMotion(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent(1));
    }
    
    @Redirect(method = { "onUpdateWalkingPlayer" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isCurrentViewEntity()Z"))
    private boolean redirectIsCurrentViewEntity(final EntityPlayerSP entityPlayerSP) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        if (freecamEvent.isCanceled()) {
            return entityPlayerSP == getMinecraft.player;
        }
        return getMinecraft.getRenderViewEntity() == entityPlayerSP;
    }
    
    @Redirect(method = { "updateEntityActionState" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isCurrentViewEntity()Z"))
    private boolean redirectIsCurrentViewEntity2(final EntityPlayerSP entityPlayerSP) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        if (freecamEvent.isCanceled()) {
            return entityPlayerSP == getMinecraft.player;
        }
        return getMinecraft.getRenderViewEntity() == entityPlayerSP;
    }
    
    @Shadow
    protected boolean isCurrentViewEntity() {
        return false;
    }
    
    @Overwrite
    private void onUpdateWalkingPlayer() {
        final boolean isSprinting = this.isSprinting();
        final MotionEvent motionEvent = new MotionEvent(0, this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround);
        MinecraftForge.EVENT_BUS.post((Event)motionEvent);
        if (motionEvent.isCanceled()) {
            MinecraftForge.EVENT_BUS.post((Event)new MotionEvent(1, motionEvent.getX(), motionEvent.getY(), motionEvent.getZ(), motionEvent.getYaw(), motionEvent.getPitch(), motionEvent.isOnGround()));
            return;
        }
        if (isSprinting != this.serverSprintState) {
            if (isSprinting) {
                this.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.START_SPRINTING));
            }
            else {
                this.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            this.serverSprintState = isSprinting;
        }
        final boolean isSneaking = this.isSneaking();
        if (isSneaking != this.serverSneakState) {
            if (isSneaking) {
                this.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.START_SNEAKING));
            }
            else {
                this.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.getMinecraft().player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            this.serverSneakState = isSneaking;
        }
        if (this.isCurrentViewEntity()) {
            final double n = this.posX - this.lastReportedPosX;
            final double n2 = this.getEntityBoundingBox().minY - this.lastReportedPosY;
            final double n3 = this.posZ - this.lastReportedPosZ;
            final double n4 = motionEvent.getYaw() - this.lastReportedYaw;
            final double n5 = motionEvent.getPitch() - this.lastReportedPitch;
            int n6 = (n * n + n2 * n2 + n3 * n3 > 9.0E-4 || this.positionUpdateTicks >= 20) ? 1 : 0;
            final boolean b = n4 != 0.0 || n5 != 0.0;
            if (this.ridingEntity == null) {
                if (n6 != 0 && b) {
                    this.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(motionEvent.getX(), motionEvent.getY(), motionEvent.getZ(), motionEvent.getYaw(), motionEvent.getPitch(), motionEvent.isOnGround()));
                }
                else if (n6 != 0) {
                    this.connection.sendPacket((Packet)new CPacketPlayer.Position(motionEvent.getX(), motionEvent.getY(), motionEvent.getZ(), motionEvent.isOnGround()));
                }
                else if (b) {
                    this.connection.sendPacket((Packet)new CPacketPlayer.Rotation(motionEvent.getYaw(), motionEvent.getPitch(), motionEvent.isOnGround()));
                }
                else {
                    this.connection.sendPacket((Packet)new CPacketPlayer(motionEvent.isOnGround()));
                }
            }
            else {
                this.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(this.motionX, -999.0, this.motionZ, motionEvent.getYaw(), motionEvent.getPitch(), motionEvent.isOnGround()));
                n6 = 0;
            }
            ++this.positionUpdateTicks;
            if (n6 != 0) {
                this.lastReportedPosX = motionEvent.getX();
                this.lastReportedPosY = motionEvent.getY();
                this.lastReportedPosZ = motionEvent.getZ();
                this.positionUpdateTicks = 0;
            }
            if (b) {
                this.lastReportedYaw = motionEvent.getYaw();
                this.lastReportedPitch = motionEvent.getPitch();
            }
            this.prevOnGround = this.onGround;
            this.autoJumpEnabled = this.mc.gameSettings.autoJump;
            MinecraftForge.EVENT_BUS.post((Event)new MotionEvent(1, motionEvent.getX(), motionEvent.getY(), motionEvent.getZ(), motionEvent.getYaw(), motionEvent.getPitch(), motionEvent.isOnGround()));
        }
    }
}
