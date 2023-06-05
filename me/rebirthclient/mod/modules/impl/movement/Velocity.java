//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.network.play.server.*;
import me.rebirthclient.asm.accessors.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;

public class Velocity extends Module
{
    private final Setting vertical;
    private final Setting entityPush;
    private final Setting blockPush;
    private final Setting noWaterPush;
    public static Velocity INSTANCE;
    private final Setting horizontal;
    
    @Override
    public String getInfo() {
        return MathUtil.round((float)this.horizontal.getValue(), 1) + "%," + MathUtil.round((float)this.vertical.getValue(), 1) + "%";
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.isCanceled()) {
            return;
        }
        final float n = (float)this.horizontal.getValue() / 100.0f;
        final float n2 = (float)this.vertical.getValue() / 100.0f;
        if (receive.getPacket() instanceof EntityFishHook) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            final ISPacketExplosion isPacketExplosion = (ISPacketExplosion)receive.getPacket();
            isPacketExplosion.setX(isPacketExplosion.getX() * n);
            isPacketExplosion.setY(isPacketExplosion.getY() * n2);
            isPacketExplosion.setZ(isPacketExplosion.getZ() * n);
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity) {
            final ISPacketEntityVelocity isPacketEntityVelocity = (ISPacketEntityVelocity)receive.getPacket();
            if (isPacketEntityVelocity.getEntityID() == Velocity.mc.player.getEntityId()) {
                if ((float)this.horizontal.getValue() == 0.0f && (float)this.vertical.getValue() == 0.0f) {
                    receive.setCanceled(true);
                }
                else {
                    isPacketEntityVelocity.setX((int)(isPacketEntityVelocity.getX() * n));
                    isPacketEntityVelocity.setY((int)(isPacketEntityVelocity.getY() * n2));
                    isPacketEntityVelocity.setZ((int)(isPacketEntityVelocity.getZ() * n));
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (pushEvent.getStage() == 0 && (boolean)this.entityPush.getValue() && pushEvent.entity.equals((Object)Velocity.mc.player)) {
            pushEvent.x = -pushEvent.x * 0.0;
            pushEvent.y = -pushEvent.y * 0.0;
            pushEvent.z = -pushEvent.z * 0.0;
        }
        else if (pushEvent.getStage() == 1 && (boolean)this.blockPush.getValue()) {
            pushEvent.setCanceled(true);
        }
        else if (pushEvent.getStage() == 2 && (boolean)this.noWaterPush.getValue() && Velocity.mc.player != null && Velocity.mc.player.equals((Object)pushEvent.entity)) {
            pushEvent.setCanceled(true);
        }
    }
    
    public Velocity() {
        super("Velocity", "Cancels all the pushing your player receives", Category.MOVEMENT);
        this.horizontal = this.add(new Setting("Horizontal", 0.0f, 0.0f, 100.0f));
        this.vertical = this.add(new Setting("Vertical", 0.0f, 0.0f, 100.0f));
        this.noWaterPush = this.add(new Setting("LiquidPush", true));
        this.blockPush = this.add(new Setting("BlockPush", true));
        this.entityPush = this.add(new Setting("EntityPush", true));
        Velocity.INSTANCE = this;
    }
}
