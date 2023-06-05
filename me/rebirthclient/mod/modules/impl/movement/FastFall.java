//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.client.entity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class FastFall extends Module
{
    private final Setting noLag;
    private final Setting mode;
    private boolean useTimer;
    private final Setting height;
    private final Timer lagTimer;
    
    private int traceDown() {
        int n = 0;
        int n2 = (int)Math.round(FastFall.mc.player.posY) - 1;
        if (n2 < 0) {
            return n;
        }
        final RayTraceResult rayTraceBlocks = FastFall.mc.world.rayTraceBlocks(FastFall.mc.player.getPositionVector(), new Vec3d(FastFall.mc.player.posX, (double)n2, FastFall.mc.player.posZ), false);
        if (rayTraceBlocks != null && rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK) {
            return n;
        }
        ++n;
        --n2;
        return 0;
    }
    
    @Override
    public String getInfo() {
        return Managers.TEXT.normalizeCases(this.mode.getValue());
    }
    
    @Override
    public void onTick() {
        if (((int)this.height.getValue() > 0 && this.traceDown() > (int)this.height.getValue()) || FastFall.mc.player.isEntityInsideOpaqueBlock() || FastFall.mc.player.isInWater() || FastFall.mc.player.isInLava() || FastFall.mc.player.isOnLadder() || !this.lagTimer.passedMs(1000L) || fullNullCheck()) {
            if ((ElytraFly.INSTANCE.isOff() || !(boolean)ElytraFly.INSTANCE.boostTimer.getValue()) && !NewStep.timer) {
                Managers.TIMER.reset();
            }
            return;
        }
        if (FastFall.mc.player.isInWeb) {
            return;
        }
        if (FastFall.mc.player.onGround && this.mode.getValue() == Mode.FAST) {
            final EntityPlayerSP player = FastFall.mc.player;
            player.motionY -= (this.noLag.getValue() ? 0.62f : 1.0f);
        }
        if (this.traceDown() != 0 && this.traceDown() <= (int)this.height.getValue() && this != 0 && FastFall.mc.player.onGround) {
            final EntityPlayerSP player2 = FastFall.mc.player;
            player2.motionX *= 0.05000000074505806;
            final EntityPlayerSP player3 = FastFall.mc.player;
            player3.motionZ *= 0.05000000074505806;
        }
        if (this.mode.getValue() == Mode.STRICT) {
            if (!FastFall.mc.player.onGround) {
                if (FastFall.mc.player.motionY < 0.0 && this.useTimer) {
                    Managers.TIMER.set(2.5f);
                    return;
                }
                this.useTimer = false;
            }
            else {
                FastFall.mc.player.motionY = -0.08;
                this.useTimer = true;
            }
        }
        Managers.TIMER.reset();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        if (!fullNullCheck() && packetEvent.getPacket() instanceof SPacketPlayerPosLook) {
            this.lagTimer.reset();
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.mode.getValue() == Mode.FAST;
    }
    
    @Override
    public void onDisable() {
        Managers.TIMER.reset();
        this.useTimer = false;
    }
    
    public FastFall() {
        super("FastFall", "Miyagi son simulator", Category.MOVEMENT);
        this.mode = this.add(new Setting("Mode", Mode.FAST));
        this.noLag = this.add(new Setting("NoLag", true, this::lambda$new$0));
        this.height = this.add(new Setting("Height", 10, 1, 20));
        this.lagTimer = new Timer();
    }
    
    private enum Mode
    {
        private static final Mode[] $VALUES;
        
        FAST("FAST", 0), 
        STRICT("STRICT", 1);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.FAST, Mode.STRICT };
        }
    }
}
