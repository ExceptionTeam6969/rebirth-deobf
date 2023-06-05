//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.play.server.*;

public class Rotations extends Module
{
    private int ticksExisted;
    private static float prevRenderYawOffset;
    private static float prevPitch;
    private static float renderPitch;
    private static float renderYawOffset;
    private final Setting onlyThird;
    private static float prevRotationYawHead;
    public static Rotations INSTANCE;
    private static float rotationYawHead;
    
    public static float getPrevPitch() {
        return Rotations.prevPitch;
    }
    
    private float getRenderYawOffset(final float n, final float n2) {
        float n3 = n2;
        final double n4 = Rotations.mc.player.posX - Rotations.mc.player.prevPosX;
        final double n5 = Rotations.mc.player.posZ - Rotations.mc.player.prevPosZ;
        if (n4 * n4 + n5 * n5 > 0.002500000176951289) {
            final float n6 = (float)MathHelper.atan2(n5, n4) * 57.295776f - 90.0f;
            final float abs = MathHelper.abs(MathHelper.wrapDegrees(n) - n6);
            if (95.0f < abs && abs < 265.0f) {
                n3 = n6 - 180.0f;
            }
            else {
                n3 = n6;
            }
        }
        if (Rotations.mc.player.swingProgress > 0.0f) {
            n3 = n;
        }
        float wrapDegrees = MathHelper.wrapDegrees(n - (n2 + MathHelper.wrapDegrees(n3 - n2) * 0.3f));
        if (wrapDegrees < -75.0f) {
            wrapDegrees = -75.0f;
        }
        else if (wrapDegrees >= 75.0f) {
            wrapDegrees = 75.0f;
        }
        float n7 = n - wrapDegrees;
        if (wrapDegrees * wrapDegrees > 2500.0f) {
            n7 += wrapDegrees * 0.2f;
        }
        return n7;
    }
    
    public boolean check() {
        return this.isOn() && (Rotations.mc.gameSettings.thirdPersonView != 0 || !(boolean)this.onlyThird.getValue());
    }
    
    public Rotations() {
        super("Rotations", "show rotation", Category.RENDER);
        this.onlyThird = this.add(new Setting("OnlyThird", true));
        Rotations.INSTANCE = this;
    }
    
    public static float getRenderYawOffset() {
        return Rotations.renderYawOffset;
    }
    
    private void set(final float rotationYawHead, final float renderPitch) {
        if (Rotations.mc.player.ticksExisted == this.ticksExisted) {
            return;
        }
        this.ticksExisted = Rotations.mc.player.ticksExisted;
        Rotations.prevPitch = Rotations.renderPitch;
        Rotations.prevRenderYawOffset = Rotations.renderYawOffset;
        Rotations.renderYawOffset = this.getRenderYawOffset(rotationYawHead, Rotations.prevRenderYawOffset);
        Rotations.prevRotationYawHead = Rotations.rotationYawHead;
        Rotations.rotationYawHead = rotationYawHead;
        Rotations.renderPitch = renderPitch;
    }
    
    public static float getRotationYawHead() {
        return Rotations.rotationYawHead;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void invoke(final MotionEvent motionEvent) {
        this.set(motionEvent.getYaw(), motionEvent.getPitch());
    }
    
    public static float getRenderPitch() {
        return Rotations.renderPitch;
    }
    
    public static float getPrevRenderYawOffset() {
        return Rotations.prevRenderYawOffset;
    }
    
    public static float getPrevRotationYawHead() {
        return Rotations.prevRotationYawHead;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && ((CPacketPlayer)send.getPacket()).rotating) {
            this.set(((CPacketPlayer)send.getPacket()).yaw, ((CPacketPlayer)send.getPacket()).pitch);
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled() || fullNullCheck()) {
            return;
        }
        if (receive.getStage() == 0 && receive.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            this.set(sPacketPlayerPosLook.yaw, sPacketPlayerPosLook.pitch);
        }
    }
}
