//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.item.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import java.util.*;

public class ElytraFly extends Module
{
    public Setting maxSpeed;
    private final Setting timeout;
    private static boolean hasElytra;
    public Setting downFactor;
    public Setting upFactor;
    private final Timer instantFlyTimer;
    public final Setting boostTimer;
    private final Setting instantFly;
    private boolean hasTouchedGround;
    public static ElytraFly INSTANCE;
    public Setting speed;
    private final Timer strictTimer;
    public Setting noDrag;
    private final Setting sneakDownSpeed;
    public Setting speedLimit;
    
    private boolean lambda$new$0(final Float n) {
        return (boolean)this.speedLimit.getValue();
    }
    
    @SubscribeEvent
    public void onElytra(final ElytraEvent elytraEvent) {
        if (fullNullCheck() || !ElytraFly.hasElytra || !ElytraFly.mc.player.isElytraFlying()) {
            return;
        }
        if ((elytraEvent.getEntity() == ElytraFly.mc.player && ElytraFly.mc.player.isServerWorld()) || (ElytraFly.mc.player.canPassengerSteer() && !ElytraFly.mc.player.isInWater()) || (ElytraFly.mc.player.capabilities.isFlying && !ElytraFly.mc.player.isInLava()) || (ElytraFly.mc.player.capabilities.isFlying && ElytraFly.mc.player.isElytraFlying())) {
            elytraEvent.setCanceled(true);
            final Vec3d getLookVec = ElytraFly.mc.player.getLookVec();
            final double sqrt = Math.sqrt(getLookVec.x * getLookVec.x + getLookVec.z * getLookVec.z);
            final double sqrt2 = Math.sqrt(ElytraFly.mc.player.motionX * ElytraFly.mc.player.motionX + ElytraFly.mc.player.motionZ * ElytraFly.mc.player.motionZ);
            if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                ElytraFly.mc.player.motionY = -(float)this.sneakDownSpeed.getValue();
            }
            else if (!MovementUtil.isJumping()) {
                ElytraFly.mc.player.motionY = -3.0E-14 * (float)this.downFactor.getValue();
            }
            if (MovementUtil.isJumping()) {
                if (sqrt2 > (float)this.upFactor.getValue() / (float)this.upFactor.getMaxValue()) {
                    final double n = sqrt2 * 0.01325;
                    final EntityPlayerSP player = ElytraFly.mc.player;
                    player.motionY += n * 3.2;
                    final EntityPlayerSP player2 = ElytraFly.mc.player;
                    player2.motionX -= getLookVec.x * n / sqrt;
                    final EntityPlayerSP player3 = ElytraFly.mc.player;
                    player3.motionZ -= getLookVec.z * n / sqrt;
                }
                else {
                    final double[] directionSpeed = directionSpeed((float)this.speed.getValue());
                    ElytraFly.mc.player.motionX = directionSpeed[0];
                    ElytraFly.mc.player.motionZ = directionSpeed[1];
                }
            }
            if (sqrt > 0.0) {
                final EntityPlayerSP player4 = ElytraFly.mc.player;
                player4.motionX += (getLookVec.x / sqrt * sqrt2 - ElytraFly.mc.player.motionX) * 0.1;
                final EntityPlayerSP player5 = ElytraFly.mc.player;
                player5.motionZ += (getLookVec.z / sqrt * sqrt2 - ElytraFly.mc.player.motionZ) * 0.1;
            }
            if (!MovementUtil.isJumping()) {
                final double[] directionSpeed2 = directionSpeed((float)this.speed.getValue());
                ElytraFly.mc.player.motionX = directionSpeed2[0];
                ElytraFly.mc.player.motionZ = directionSpeed2[1];
            }
            if (!(boolean)this.noDrag.getValue()) {
                final EntityPlayerSP player6 = ElytraFly.mc.player;
                player6.motionX *= 0.9900000095367432;
                final EntityPlayerSP player7 = ElytraFly.mc.player;
                player7.motionY *= 0.9800000190734863;
                final EntityPlayerSP player8 = ElytraFly.mc.player;
                player8.motionZ *= 0.9900000095367432;
            }
            final double sqrt3 = Math.sqrt(ElytraFly.mc.player.motionX * ElytraFly.mc.player.motionX + ElytraFly.mc.player.motionZ * ElytraFly.mc.player.motionZ);
            if ((boolean)this.speedLimit.getValue() && sqrt3 > (float)this.maxSpeed.getValue()) {
                final EntityPlayerSP player9 = ElytraFly.mc.player;
                player9.motionX *= (float)this.maxSpeed.getValue() / sqrt3;
                final EntityPlayerSP player10 = ElytraFly.mc.player;
                player10.motionZ *= (float)this.maxSpeed.getValue() / sqrt3;
            }
            ElytraFly.mc.player.move(MoverType.SELF, ElytraFly.mc.player.motionX, ElytraFly.mc.player.motionY, ElytraFly.mc.player.motionZ);
        }
    }
    
    static {
        ElytraFly.INSTANCE = new ElytraFly();
        ElytraFly.hasElytra = false;
    }
    
    @Override
    public void onEnable() {
        if (ElytraFly.mc.player != null) {
            if (!ElytraFly.mc.player.isCreative()) {
                ElytraFly.mc.player.capabilities.allowFlying = false;
            }
            ElytraFly.mc.player.capabilities.isFlying = false;
        }
        ElytraFly.hasElytra = false;
    }
    
    public ElytraFly() {
        super("ElytraFly", "\u659c\u890d\u890b\u890c\u8918 \u5199\u8c22\u891f 2\u659c", Category.MOVEMENT);
        this.instantFly = this.register(new Setting("InstantFly", true));
        this.timeout = this.register(new Setting("Timeout", 0.5f, 0.1f, 1.0f));
        this.upFactor = this.register(new Setting("UpFactor", 1.0f, 0.0f, 10.0f));
        this.downFactor = this.register(new Setting("DownFactor", 1.0f, 0.0f, 10.0f));
        this.speed = this.register(new Setting("Speed", 1.0f, 0.1f, 10.0f));
        this.sneakDownSpeed = this.register(new Setting("DownSpeed", 1.0f, 0.1f, 10.0f));
        this.boostTimer = this.register(new Setting("Timer", true));
        this.speedLimit = this.register(new Setting("SpeedLimit", true));
        this.maxSpeed = this.register(new Setting("MaxSpeed", 2.5f, 0.1f, 10.0f, this::lambda$new$0));
        this.noDrag = new Setting("NoDrag", false);
        this.instantFlyTimer = new Timer();
        this.strictTimer = new Timer();
        this.hasTouchedGround = false;
        ElytraFly.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (ElytraFly.mc.player.onGround) {
            this.hasTouchedGround = true;
        }
        final Iterator<ItemStack> iterator = ElytraFly.mc.player.getArmorInventoryList().iterator();
        if (iterator.hasNext()) {
            if (!(iterator.next().getItem() instanceof ItemElytra)) {
                ElytraFly.hasElytra = false;
                return;
            }
            ElytraFly.hasElytra = true;
        }
        if ((this.strictTimer.passedMs(1500L) && !this.strictTimer.passedMs(2000L)) || (ElytraFly.mc.player.isElytraFlying() && Managers.TIMER.get() == 0.3f)) {
            Managers.TIMER.reset();
        }
        if (!ElytraFly.mc.player.isElytraFlying()) {
            if (this.hasTouchedGround && (boolean)this.boostTimer.getValue() && !ElytraFly.mc.player.onGround) {
                Managers.TIMER.set(0.3f);
            }
            if (!ElytraFly.mc.player.onGround && (boolean)this.instantFly.getValue() && ElytraFly.mc.player.motionY < 0.0) {
                if (!this.instantFlyTimer.passedMs((long)(1000.0f * (float)this.timeout.getValue()))) {
                    return;
                }
                this.instantFlyTimer.reset();
                ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                this.hasTouchedGround = false;
                this.strictTimer.reset();
            }
            return;
        }
        ElytraFly.mc.player.isElytraFlying();
    }
    
    @Override
    public void onDisable() {
        if (ElytraFly.mc.player != null) {
            if (!ElytraFly.mc.player.isCreative()) {
                ElytraFly.mc.player.capabilities.allowFlying = false;
            }
            ElytraFly.mc.player.capabilities.isFlying = false;
        }
        Managers.TIMER.reset();
        ElytraFly.hasElytra = false;
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = ElytraFly.mc.player.movementInput.moveForward;
        float moveStrafe = ElytraFly.mc.player.movementInput.moveStrafe;
        float n2 = ElytraFly.mc.player.prevRotationYaw + (ElytraFly.mc.player.rotationYaw - ElytraFly.mc.player.prevRotationYaw) * ElytraFly.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
}
