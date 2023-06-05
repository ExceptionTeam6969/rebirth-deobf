//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.lwjgl.input.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class Speed extends Module
{
    private final Setting pauseTime;
    private final Setting multiplier;
    private final Setting cap;
    private double speed;
    private final Setting velocity;
    private final Setting inWater;
    private final Setting scaleCap;
    private double distance;
    private double lastExp;
    private final Setting coolDown;
    private boolean stop;
    private boolean boost;
    private final Setting jump;
    private final Setting yFactor;
    private final Setting directional;
    private final Setting explosions;
    private final Setting xzFactor;
    private final Setting debug;
    private final Setting vertical;
    private int stage;
    private final Timer expTimer;
    public static Speed INSTANCE;
    private final Setting strafeSpeed;
    private final Setting modify;
    private final Setting slow;
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity) {
            final SPacketEntityVelocity sPacketEntityVelocity = (SPacketEntityVelocity)receive.getPacket();
            if (sPacketEntityVelocity.getEntityID() == Speed.mc.player.getEntityId() && !(boolean)this.directional.getValue() && (boolean)this.velocity.getValue()) {
                final double n = Math.sqrt(sPacketEntityVelocity.getMotionX() * sPacketEntityVelocity.getMotionX() + sPacketEntityVelocity.getMotionZ() * sPacketEntityVelocity.getMotionZ()) / 8000.0;
                this.lastExp = (this.expTimer.passedMs((long)(int)this.coolDown.getValue()) ? n : (n - this.lastExp));
                if (this.lastExp > 0.0) {
                    if (this.debug.getValue()) {
                        this.sendMessage("boost");
                    }
                    this.expTimer.reset();
                    this.speed += this.lastExp * (float)this.multiplier.getValue();
                    this.distance += this.lastExp * (float)this.multiplier.getValue();
                    if (Speed.mc.player.motionY > 0.0 && (float)this.vertical.getValue() != 0.0f) {
                        final EntityPlayerSP player = Speed.mc.player;
                        player.motionY *= (float)this.vertical.getValue();
                    }
                }
            }
        }
        else if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.distance = 0.0;
            this.speed = 0.0;
            this.stage = 4;
        }
        else if (receive.getPacket() instanceof SPacketExplosion && (boolean)this.explosions.getValue() && MovementUtil.isMoving()) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)receive.getPacket();
            if (Speed.mc.player.getDistanceSq(new BlockPos(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ())) < 100.0 && (!(boolean)this.directional.getValue() || !MovementUtil.isInMovementDirection(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ()))) {
                final double sqrt = Math.sqrt(sPacketExplosion.getMotionX() * sPacketExplosion.getMotionX() + sPacketExplosion.getMotionZ() * sPacketExplosion.getMotionZ());
                this.lastExp = (this.expTimer.passedMs((long)(int)this.coolDown.getValue()) ? sqrt : (sqrt - this.lastExp));
                if (this.lastExp > 0.0) {
                    if (this.debug.getValue()) {
                        this.sendMessage("boost");
                    }
                    this.expTimer.reset();
                    this.speed += this.lastExp * (float)this.multiplier.getValue();
                    this.distance += this.lastExp * (float)this.multiplier.getValue();
                    if (Speed.mc.player.motionY > 0.0) {
                        final EntityPlayerSP player2 = Speed.mc.player;
                        player2.motionY *= (float)this.vertical.getValue();
                    }
                }
            }
        }
    }
    
    @Override
    public String getInfo() {
        return "3arthh4ck";
    }
    
    @Override
    public void onEnable() {
        this.speed = MovementUtil.getSpeed();
        this.distance = MovementUtil.getDistance2D();
        this.stage = 4;
    }
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (pushEvent.getStage() == 0 && pushEvent.entity.equals((Object)Speed.mc.player)) {
            pushEvent.x = -pushEvent.x * 0.0;
            pushEvent.y = -pushEvent.y * 0.0;
            pushEvent.z = -pushEvent.z * 0.0;
        }
        else if (pushEvent.getStage() == 1) {
            pushEvent.setCanceled(true);
        }
        else if (pushEvent.getStage() == 2 && Speed.mc.player != null && Speed.mc.player.equals((Object)pushEvent.entity)) {
            pushEvent.setCanceled(true);
        }
    }
    
    private boolean lambda$new$1(final Double n) {
        return (boolean)this.modify.getValue();
    }
    
    public void playerMove(final MoveEvent moveEvent) {
        if (!MovementUtil.isMoving()) {
            return;
        }
        if (LongJump.INSTANCE.isOn()) {
            return;
        }
        if (Flight.INSTANCE.isOn()) {
            return;
        }
        if (this.stage == 1) {
            this.speed = 1.35 * MovementUtil.getSpeed((boolean)this.slow.getValue(), (double)this.strafeSpeed.getValue() / 1000.0) - 0.01;
        }
        else if (this.stage == 2) {
            if ((boolean)this.jump.getValue() || Speed.mc.gameSettings.keyBindJump.isKeyDown() || (InventoryMove.INSTANCE.isOn() && Keyboard.isKeyDown(Speed.mc.gameSettings.keyBindJump.getKeyCode()))) {
                moveEvent.setY(Speed.mc.player.motionY = 0.3999 + MovementUtil.getJumpSpeed());
                this.speed *= (this.boost ? 1.6835 : 1.395);
            }
        }
        else if (this.stage == 3) {
            this.speed = this.distance - 0.66 * (this.distance - MovementUtil.getSpeed((boolean)this.slow.getValue(), (double)this.strafeSpeed.getValue() / 1000.0));
            this.boost = !this.boost;
        }
        else {
            if ((Speed.mc.world.getCollisionBoxes((Entity)null, Speed.mc.player.getEntityBoundingBox().offset(0.0, Speed.mc.player.motionY, 0.0)).size() > 0 || Speed.mc.player.collidedVertically) && this.stage > 0) {
                this.stage = (MovementUtil.isMoving() ? 1 : 0);
            }
            this.speed = this.distance - this.distance / 159.0;
        }
        this.speed = Math.min(this.speed, this.getCap());
        MovementUtil.strafe(moveEvent, this.speed = Math.max(this.speed, MovementUtil.getSpeed((boolean)this.slow.getValue(), (double)this.strafeSpeed.getValue() / 1000.0)));
        ++this.stage;
    }
    
    @SubscribeEvent
    public void Update(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.expTimer.passedMs((long)(int)this.pauseTime.getValue())) {
            this.distance = MovementUtil.getDistance2D();
        }
    }
    
    public double getCap() {
        double doubleValue = (double)this.cap.getValue();
        if (!(boolean)this.scaleCap.getValue()) {
            return doubleValue;
        }
        if (Speed.mc.player.isPotionActive(MobEffects.SPEED)) {
            doubleValue *= 1.0 + 0.2 * (Objects.requireNonNull(Speed.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        if ((boolean)this.slow.getValue() && Speed.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
            doubleValue /= 1.0 + 0.2 * (Objects.requireNonNull(Speed.mc.player.getActivePotionEffect(MobEffects.SLOWNESS)).getAmplifier() + 1);
        }
        return doubleValue;
    }
    
    private boolean lambda$new$0(final Double n) {
        return (boolean)this.modify.getValue();
    }
    
    @SubscribeEvent
    public void Move(final MoveEvent moveEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (Speed.mc.player == 0) {
            return;
        }
        if ((!(boolean)this.inWater.getValue() && (PositionUtil.inLiquid() || PositionUtil.inLiquid(true))) || Speed.mc.player.isOnLadder() || Speed.mc.player.isEntityInsideOpaqueBlock()) {
            this.stop = true;
            return;
        }
        if (this.stop) {
            this.stop = false;
            return;
        }
        if (!MovementUtil.isMoving()) {
            Speed.mc.player.motionX = 0.0;
            Speed.mc.player.motionZ = 0.0;
        }
        this.playerMove(moveEvent);
        if (this.modify.getValue()) {
            moveEvent.setX(moveEvent.getX() * (double)this.xzFactor.getValue());
            moveEvent.setY(moveEvent.getY() * (double)this.yFactor.getValue());
            moveEvent.setZ(moveEvent.getZ() * (double)this.xzFactor.getValue());
        }
    }
    
    public Speed() {
        super("Speed", "3ar", Category.MOVEMENT);
        this.jump = this.add(new Setting("Jump", false));
        this.inWater = this.add(new Setting("InWater", false));
        this.strafeSpeed = this.add(new Setting("StrafeSpeed", 278.5, 100.0, 1000.0));
        this.explosions = this.add(new Setting("Explosions", true));
        this.velocity = this.add(new Setting("Velocity", true));
        this.multiplier = this.add(new Setting("H-Factor", 1.0f, 0.0f, 5.0f));
        this.vertical = this.add(new Setting("V-Factor", 1.0f, 0.0f, 5.0f));
        this.coolDown = this.add(new Setting("CoolDown", 400, 0, 5000));
        this.pauseTime = this.add(new Setting("PauseTime", 400, 0, 1000));
        this.directional = this.add(new Setting("Directional", false));
        this.cap = this.add(new Setting("Cap", 10.0, 0.0, 10.0));
        this.scaleCap = this.add(new Setting("ScaleCap", false));
        this.slow = this.add(new Setting("Slowness", true));
        this.modify = this.add(new Setting("Modify", false));
        this.xzFactor = this.add(new Setting("XZ-Factor", 1.0, 0.0, 5.0, this::lambda$new$0));
        this.yFactor = this.add(new Setting("Y-Factor", 1.0, 0.0, 5.0, this::lambda$new$1));
        this.debug = this.add(new Setting("Debug", false));
        this.expTimer = new Timer();
        Speed.INSTANCE = this;
    }
    
    static {
        Speed.INSTANCE = new Speed();
    }
}
