//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.*;

public class Scaffold extends Module
{
    private final Setting airCheck;
    private float lastYaw;
    private final Setting down;
    private final Setting placeDelay;
    private final Setting yCheck;
    private final Timer rotateTimer;
    private final Timer placeTimer;
    private final Setting allowUp;
    private final Setting tower;
    private final Setting search;
    private final Setting rotateTimerSetting;
    private final Setting range;
    private float lastPitch;
    private final Setting rotate;
    private final Timer timer;
    private final Setting packet;
    private final Setting sameY;
    private final Setting autoSwap;
    private final Setting safeWalk;
    private BlockPos PlacePos;
    
    @SubscribeEvent
    public final void onMotion(final MotionEvent motionEvent) {
        if (!this.rotateTimer.passedMs((long)(int)this.rotateTimerSetting.getValue()) && (boolean)this.rotate.getValue()) {
            motionEvent.setYaw(this.lastYaw);
            motionEvent.setPitch(this.lastPitch);
        }
    }
    
    @Override
    public void onTick() {
        if (!this.placeTimer.passedMs((long)(int)this.placeDelay.getValue())) {
            return;
        }
        if (this.PlacePos == null) {
            this.PlacePos = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 1.0, Scaffold.mc.player.posZ);
        }
        this.doScaffold();
    }
    
    private boolean lambda$new$3(final Float n) {
        return this.sameY.isOpen();
    }
    
    public Scaffold() {
        super("Scaffold", "Places Blocks underneath you", Category.MOVEMENT);
        this.safeWalk = this.add(new Setting("SafeWalk", false));
        this.rotate = this.add(new Setting("Rotate", true).setParent());
        this.rotateTimerSetting = this.add(new Setting("RotateTimer", 800, 0, 1000, this::lambda$new$0));
        this.packet = this.add(new Setting("Packet", false));
        this.placeDelay = this.add(new Setting("PlaceDelay", 100, 0, 500));
        this.autoSwap = this.add(new Setting("AutoSwap", SwapMode.SILENT));
        this.search = this.add(new Setting("Search", true).setParent());
        this.allowUp = this.add(new Setting("AllowUp", false, this::lambda$new$1));
        this.range = this.add(new Setting("Range", 3.5f, 2.5f, 5.0f, this::lambda$new$2));
        this.timer = new Timer();
        this.placeTimer = new Timer();
        this.tower = this.add(new Setting("Tower", true));
        this.down = this.add(new Setting("Down", true));
        this.sameY = this.add(new Setting("SameY", false).setParent());
        this.yCheck = this.add(new Setting("YCheck", 2.5f, 2.5f, 12.0f, this::lambda$new$3));
        this.airCheck = this.add(new Setting("AirCheck", true, this::lambda$new$4));
        this.rotateTimer = new Timer();
        this.lastYaw = 0.0f;
        this.lastPitch = 0.0f;
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (!(boolean)this.safeWalk.getValue()) {
            return;
        }
        if ((boolean)this.down.getValue() && Scaffold.mc.gameSettings.keyBindSprint.isKeyDown()) {
            return;
        }
        final double x = moveEvent.getX();
        final double y = moveEvent.getY();
        final double z = moveEvent.getZ();
        if (Scaffold.mc.player.onGround) {
            final double n = 0.05;
            if (x != 0.0 && this.isOffsetBBEmpty(x, -1.0, 0.0)) {
                if (x < n && x >= -n) {
                    return;
                }
                if (x > 0.0) {
                    return;
                }
                return;
            }
            else if (z != 0.0 && this.isOffsetBBEmpty(0.0, -1.0, z)) {
                if (z < n && z >= -n) {
                    return;
                }
                if (z > 0.0) {
                    return;
                }
                return;
            }
            else if (x != 0.0 && z != 0.0 && this.isOffsetBBEmpty(x, -1.0, z)) {
                final double n2 = (x < n && x >= -n) ? 0.0 : ((x > 0.0) ? (x - n) : (x + n));
                if (z < n && z >= -n) {
                    return;
                }
                if (z > 0.0) {
                    return;
                }
                return;
            }
        }
        moveEvent.setX(x);
        moveEvent.setY(y);
        moveEvent.setZ(z);
    }
    
    public void doScaffold() {
        if (!MovementUtil.isJumping()) {
            this.timer.reset();
        }
        if (!(boolean)this.sameY.getValue() || Scaffold.mc.player.posY - this.PlacePos.getY() > (float)this.yCheck.getValue() || ((boolean)this.airCheck.getValue() && !BlockUtil.canReplace(new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 1.0, Scaffold.mc.player.posZ))) || (MovementUtil.isJumping() && !MovementUtil.isMoving()) || new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 1.0, Scaffold.mc.player.posZ).getY() < this.PlacePos.getY()) {
            this.PlacePos = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 1.0, Scaffold.mc.player.posZ);
        }
        else {
            this.PlacePos = new BlockPos(Scaffold.mc.player.posX, (double)this.PlacePos.getY(), Scaffold.mc.player.posZ);
        }
        if ((boolean)this.down.getValue() && Scaffold.mc.gameSettings.keyBindSprint.isKeyDown()) {
            this.PlacePos = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 2.0, Scaffold.mc.player.posZ);
        }
        this.place(this.PlacePos);
    }
    
    public boolean isOffsetBBEmpty(final double n, final double n2, final double n3) {
        final EntityPlayerSP player = Scaffold.mc.player;
        return Scaffold.mc.world.getCollisionBoxes((Entity)player, player.getEntityBoundingBox().offset(n, n2, n3)).isEmpty();
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.rotate.isOpen();
    }
    
    public void place(BlockPos blockPos) {
        if (blockPos.getDistance((int)Scaffold.mc.player.posX, (int)Scaffold.mc.player.posY, (int)Scaffold.mc.player.posZ) > 6.0) {
            return;
        }
        if (!BlockUtil.canReplace(blockPos)) {
            return;
        }
        if ((boolean)this.search.getValue() && !BlockUtil.canBlockFacing(blockPos)) {
            BlockPos blockPos2 = null;
            double getDistance = 1000.0;
            final boolean b = !(boolean)this.allowUp.getValue();
            final Iterator iterator = BlockUtil.getBox((float)this.range.getValue(), blockPos).iterator();
            if (iterator.hasNext()) {
                final BlockPos blockPos3 = iterator.next();
                if (!BlockUtil.canPlace(blockPos3)) {
                    return;
                }
                if ((blockPos2 == null || blockPos.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()) < getDistance) && !b) {
                    blockPos2 = blockPos3;
                    getDistance = blockPos.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ());
                }
                if ((blockPos2 == null || blockPos.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ()) < getDistance) && blockPos.getY() >= blockPos3.getY()) {
                    blockPos.getDistance(blockPos3.getX(), blockPos3.getY(), blockPos3.getZ());
                }
                return;
            }
            else {
                if (blockPos2 == null) {
                    return;
                }
                blockPos = blockPos2;
            }
        }
        if (!BlockUtil.canPlace(blockPos)) {
            return;
        }
        final int currentItem = Scaffold.mc.player.inventory.currentItem;
        int n = -1;
        int n2 = 0;
        if (n2 < 9) {
            final ItemStack getStackInSlot = Scaffold.mc.player.inventory.getStackInSlot(n2);
            if (InventoryUtil.isNull(getStackInSlot) || !(getStackInSlot.getItem() instanceof ItemBlock) || !Block.getBlockFromItem(getStackInSlot.getItem()).getDefaultState().isFullBlock()) {
                ++n2;
                return;
            }
            n = n2;
        }
        if (n == -1) {
            return;
        }
        final EnumFacing firstFacing = BlockUtil.getFirstFacing(blockPos);
        if (firstFacing == null) {
            return;
        }
        boolean b2 = false;
        if (!(Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            if (this.autoSwap.getValue() == SwapMode.OFF) {
                return;
            }
            if (this.autoSwap.getValue() == SwapMode.SILENT) {
                b2 = true;
            }
            InventoryUtil.doSwap(n);
        }
        if (MovementUtil.isJumping() && !MovementUtil.isMoving() && (boolean)this.tower.getValue()) {
            Scaffold.mc.player.motionX = 0.0;
            Scaffold.mc.player.motionZ = 0.0;
            Scaffold.mc.player.jump();
            if (this.timer.passedMs(1500L)) {
                Scaffold.mc.player.motionY = -0.28;
                this.timer.reset();
            }
        }
        this.rotateTimer.reset();
        this.faceVector(new Vec3d((Vec3i)blockPos.offset(firstFacing)).add(0.5, 0.5, 0.5).add(new Vec3d(firstFacing.getOpposite().getDirectionVec()).scale(0.5)));
        this.placeTimer.reset();
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        if (b2) {
            InventoryUtil.doSwap(currentItem);
        }
    }
    
    private void faceVector(final Vec3d vec3d) {
        final float[] legitRotations = EntityUtil.getLegitRotations(vec3d);
        this.lastYaw = legitRotations[0];
        this.lastPitch = legitRotations[1];
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.search.isOpen();
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.search.isOpen();
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.sameY.isOpen();
    }
    
    @Override
    public void onEnable() {
        this.PlacePos = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - 1.0, Scaffold.mc.player.posZ);
        this.timer.reset();
    }
    
    public enum SwapMode
    {
        OFF("OFF", 0);
        
        private static final SwapMode[] $VALUES;
        
        NORMAL("NORMAL", 1), 
        SILENT("SILENT", 2);
        
        static {
            $VALUES = new SwapMode[] { SwapMode.OFF, SwapMode.NORMAL, SwapMode.SILENT };
        }
        
        private SwapMode(final String s, final int n) {
        }
    }
}
