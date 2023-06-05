//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.network.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.render.*;
import com.mojang.realmsclient.gui.*;
import java.text.*;
import net.minecraft.enchantment.*;
import java.util.concurrent.atomic.*;
import net.minecraft.item.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;

public class PacketMine extends Module
{
    private final Timer delayTimer;
    public final Setting godCancel;
    public static final List godBlocks;
    public static PacketMine INSTANCE;
    private final Setting debug;
    private final Setting instant;
    private final Setting box;
    public static BlockPos breakPos;
    private final Setting restart;
    private final Setting outline;
    private int breakNumber;
    private final Setting swing;
    private final Setting allowWeb;
    private final Setting outlineAlpha;
    private final Setting time;
    private final Setting doubleBreak;
    private final Timer mineTimer;
    private final Setting fillStart;
    private final Timer firstTimer;
    private final Setting switchReset;
    private final Setting animationMode;
    private final Setting render;
    private final Setting text;
    private final Setting enderChest;
    int lastSlot;
    private final Setting textColorMode;
    private boolean startMine;
    private FadeUtils animationTime;
    private final Setting range;
    private final Setting boxExtend;
    private final Setting onlyGround;
    private final Setting wait;
    private final Setting maxBreak;
    private boolean first;
    private final Setting textColor;
    private final Setting color;
    private final Setting rotate;
    private final Setting boxStart;
    private final Setting colorMode;
    public final Setting hotBar;
    private final Setting damage;
    private final Setting mineAir;
    private final Setting boxAlpha;
    private final Setting delay;
    
    @Override
    public void onTick() {
        if (PacketMine.breakPos == null) {
            this.breakNumber = 0;
            this.startMine = false;
            return;
        }
        if (PacketMine.mc.player.isCreative() || PacketMine.mc.player.getDistance(PacketMine.breakPos.getX() + 0.5, PacketMine.breakPos.getY() + 0.5, PacketMine.breakPos.getZ() + 0.5) > (float)this.range.getValue() || this.breakNumber > (int)this.maxBreak.getValue() - 1 || (!(boolean)this.wait.getValue() && PacketMine.mc.world.isAirBlock(PacketMine.breakPos) && !(boolean)this.instant.getValue())) {
            this.startMine = false;
            this.breakNumber = 0;
            PacketMine.breakPos = null;
            return;
        }
        if (PacketMine.godBlocks.contains(PacketMine.mc.world.getBlockState(PacketMine.breakPos).getBlock())) {
            if (this.godCancel.getValue()) {
                PacketMine.breakPos = null;
                this.startMine = false;
            }
            return;
        }
        if (PacketMine.mc.world.isAirBlock(PacketMine.breakPos)) {
            if (((Bind)this.enderChest.getValue()).isDown() && BlockUtil.canPlace(PacketMine.breakPos)) {
                final int hotbarBlock = InventoryUtil.findHotbarBlock(Blocks.ENDER_CHEST);
                if (hotbarBlock != -1) {
                    final int currentItem = PacketMine.mc.player.inventory.currentItem;
                    InventoryUtil.doSwap(hotbarBlock);
                    BlockUtil.placeBlock(PacketMine.breakPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), true);
                    InventoryUtil.doSwap(currentItem);
                }
            }
            this.breakNumber = 0;
        }
        if (this.first) {
            if (!this.firstTimer.passedMs(300L)) {
                return;
            }
            PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
            this.first = false;
        }
        else {
            if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
                return;
            }
            if (this.startMine) {
                if (PacketMine.mc.world.isAirBlock(PacketMine.breakPos)) {
                    return;
                }
                if ((boolean)this.onlyGround.getValue() && !PacketMine.mc.player.onGround && (!(boolean)this.allowWeb.getValue() || !PacketMine.mc.player.isInWeb)) {
                    return;
                }
                if (PullCrystal.INSTANCE.isOn() && PacketMine.breakPos.equals((Object)PullCrystal.powerPos) && PullCrystal.crystalPos != null && !BlockUtil.posHasCrystal(PullCrystal.crystalPos)) {
                    return;
                }
                int tool = this.getTool(PacketMine.breakPos);
                if (tool == -1) {
                    tool = PacketMine.mc.player.inventory.currentItem + 36;
                }
                if (this.mineTimer.passedMs((long)(1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool)) / 20.0f * 1000.0f * (float)this.damage.getValue()))) {
                    final int currentItem2 = PacketMine.mc.player.inventory.currentItem;
                    final boolean b = currentItem2 + 36 != tool;
                    if (b) {
                        if (this.hotBar.getValue()) {
                            InventoryUtil.doSwap(tool - 36);
                        }
                        else {
                            PacketMine.mc.playerController.windowClick(0, tool, currentItem2, ClickType.SWAP, (EntityPlayer)PacketMine.mc.player);
                        }
                    }
                    if (this.rotate.getValue()) {
                        EntityUtil.facePosFacing(PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos));
                    }
                    if (this.swing.getValue()) {
                        PacketMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                    PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
                    if (b) {
                        if (this.hotBar.getValue()) {
                            InventoryUtil.doSwap(currentItem2);
                        }
                        else {
                            PacketMine.mc.playerController.windowClick(0, tool, currentItem2, ClickType.SWAP, (EntityPlayer)PacketMine.mc.player);
                        }
                    }
                    ++this.breakNumber;
                    this.delayTimer.reset();
                }
            }
            else {
                if (!(boolean)this.mineAir.getValue() && PacketMine.mc.world.isAirBlock(PacketMine.breakPos)) {
                    return;
                }
                int tool2 = this.getTool(PacketMine.breakPos);
                if (tool2 == -1) {
                    tool2 = PacketMine.mc.player.inventory.currentItem + 36;
                }
                this.animationTime = new FadeUtils((long)(1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool2)) / 20.0f * 1000.0f * (float)this.damage.getValue()));
                this.mineTimer.reset();
                if (this.swing.getValue()) {
                    PacketMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
                this.delayTimer.reset();
            }
        }
    }
    
    private boolean lambda$new$5(final Mode mode) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$14(final Boolean b) {
        return this.render.isOpen();
    }
    
    @Override
    public void onDisable() {
        this.startMine = false;
        PacketMine.breakPos = null;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return !(boolean)this.instant.getValue();
    }
    
    private boolean lambda$new$7(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == Mode.Custom;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onSend(final PacketEvent.Send send) {
        if (fullNullCheck() || PacketMine.mc.player.isCreative() || !(boolean)this.debug.getValue() || !(send.getPacket() instanceof CPacketPlayerDigging)) {
            return;
        }
        this.sendMessage(((CPacketPlayerDigging)send.getPacket()).getAction().name());
    }
    
    @SubscribeEvent
    public void onMotion(final MotionEvent motionEvent) {
        if (fullNullCheck()) {
            return;
        }
        if ((boolean)this.onlyGround.getValue() && !PacketMine.mc.player.onGround && (!(boolean)this.allowWeb.getValue() || !PacketMine.mc.player.isInWeb)) {
            return;
        }
        if ((boolean)this.rotate.getValue() && PacketMine.breakPos != null && !PacketMine.mc.world.isAirBlock(PacketMine.breakPos) && (int)this.time.getValue() > 0) {
            int tool = this.getTool(PacketMine.breakPos);
            if (tool == -1) {
                tool = PacketMine.mc.player.inventory.currentItem + 36;
            }
            final float n = 1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool)) / 20.0f * 1000.0f * (float)this.damage.getValue() - (int)this.time.getValue();
            if (n <= 0.0f || this.mineTimer.passedMs((long)n)) {
                facePosFacing(PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos), motionEvent);
            }
        }
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return (boolean)this.onlyGround.getValue();
    }
    
    static {
        godBlocks = Arrays.asList(Blocks.COMMAND_BLOCK, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.BEDROCK, Blocks.BARRIER);
    }
    
    private boolean lambda$new$10(final ColorMode colorMode) {
        return this.render.isOpen() && this.text.isOpen();
    }
    
    public static float getBlockStrength(final BlockPos blockPos, final ItemStack itemStack) {
        final IBlockState getBlockState = PacketMine.mc.world.getBlockState(blockPos);
        final float getBlockHardness = getBlockState.getBlockHardness((World)PacketMine.mc.world, blockPos);
        if (getBlockHardness < 0.0f) {
            return 0.0f;
        }
        if (blockPos != 0) {
            return getDigSpeed(getBlockState, itemStack) / getBlockHardness / 100.0f;
        }
        return getDigSpeed(getBlockState, itemStack) / getBlockHardness / 30.0f;
    }
    
    private boolean lambda$new$15(final Integer n) {
        return this.outline.isOpen() && this.render.isOpen();
    }
    
    public static void facePosFacing(final BlockPos blockPos, final EnumFacing enumFacing, final MotionEvent motionEvent) {
        faceVector(new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5)), motionEvent);
    }
    
    private boolean lambda$new$11(final Color color) {
        return this.render.isOpen() && this.text.isOpen();
    }
    
    public static float getDestroySpeed(final IBlockState blockState, final ItemStack itemStack) {
        float n = 1.0f;
        if (itemStack != null && !itemStack.isEmpty()) {
            n *= itemStack.getDestroySpeed(blockState);
        }
        return n;
    }
    
    private boolean lambda$new$6(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == Mode.Custom;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck() || PacketMine.mc.player.isCreative()) {
            return;
        }
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            if (((CPacketHeldItemChange)send.getPacket()).getSlotId() != this.lastSlot) {
                this.lastSlot = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
                if (this.switchReset.getValue()) {
                    this.startMine = false;
                    this.mineTimer.reset();
                    this.animationTime.reset();
                }
            }
            return;
        }
        if (!(send.getPacket() instanceof CPacketPlayerDigging)) {
            return;
        }
        if (((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            if (PacketMine.breakPos == null || !((CPacketPlayerDigging)send.getPacket()).getPosition().equals((Object)PacketMine.breakPos)) {
                send.setCanceled(true);
                return;
            }
            this.startMine = true;
        }
        else if (((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
            if (PacketMine.breakPos == null || !((CPacketPlayerDigging)send.getPacket()).getPosition().equals((Object)PacketMine.breakPos)) {
                send.setCanceled(true);
                return;
            }
            if (!(boolean)this.instant.getValue()) {
                this.startMine = false;
            }
        }
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$16(final ColorMode colorMode) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$4(final Integer n) {
        return this.rotate.isOpen();
    }
    
    private boolean lambda$new$12(final Boolean b) {
        return this.render.isOpen();
    }
    
    private static void faceVector(final Vec3d vec3d, final MotionEvent motionEvent) {
        final float[] legitRotations = EntityUtil.getLegitRotations(vec3d);
        motionEvent.setRotation(legitRotations[0], legitRotations[1]);
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!PacketMine.mc.player.isCreative() && PacketMine.breakPos != null) {
            if (this.debug.getValue()) {
                RenderUtil.drawBBFill(new AxisAlignedBB(PacketMine.breakPos.offset(BlockUtil.getRayTraceFacing(PacketMine.breakPos))), new Color(255, 255, 255), 70);
            }
            if (this.render.getValue()) {
                if (PacketMine.mc.world.isAirBlock(PacketMine.breakPos) && !(boolean)this.wait.getValue() && !(boolean)this.instant.getValue()) {
                    return;
                }
                if (PacketMine.godBlocks.contains(PacketMine.mc.world.getBlockState(PacketMine.breakPos).getBlock())) {
                    this.draw(PacketMine.breakPos, 1.0, (this.colorMode.getValue() == ColorMode.Custom) ? ((Color)this.color.getValue()) : new Color(255, 0, 0, 255), true);
                    if (this.text.getValue()) {
                        RenderUtil.drawText(PacketMine.mc.world.getBlockState(PacketMine.breakPos).getSelectedBoundingBox((World)PacketMine.mc.world, PacketMine.breakPos), ChatFormatting.RED + "GodBlock");
                    }
                }
                else {
                    int tool = this.getTool(PacketMine.breakPos);
                    if (tool == -1) {
                        tool = PacketMine.mc.player.inventory.currentItem + 36;
                    }
                    this.animationTime.setLength((long)(1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool)) / 20.0f * 1000.0f * (float)this.damage.getValue()));
                    this.draw(PacketMine.breakPos, PacketMine.mc.world.isAirBlock(PacketMine.breakPos) ? 1.0 : this.animationTime.easeOutQuad(), (this.colorMode.getValue() == ColorMode.Custom) ? ((Color)this.color.getValue()) : new Color((int)(255.0 * Math.abs(this.animationTime.easeOutQuad() - 1.0)), (int)(255.0 * this.animationTime.easeOutQuad()), 0), PacketMine.mc.world.isAirBlock(PacketMine.breakPos));
                    if (this.text.getValue()) {
                        final AxisAlignedBB getSelectedBoundingBox = PacketMine.mc.world.getBlockState(PacketMine.breakPos).getSelectedBoundingBox((World)PacketMine.mc.world, PacketMine.breakPos);
                        if (!PacketMine.mc.world.isAirBlock(PacketMine.breakPos)) {
                            if ((int)this.mineTimer.getPassedTimeMs() < 1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool)) / 20.0f * 1000.0f * (float)this.damage.getValue()) {
                                RenderUtil.drawText(getSelectedBoundingBox, new DecimalFormat("0.0").format(this.mineTimer.getPassedTimeMs() / (double)(1.0f / getBlockStrength(PacketMine.breakPos, (ItemStack)PacketMine.mc.player.inventoryContainer.getInventory().get(tool)) / 20.0f * 1000.0f * (float)this.damage.getValue() / 100.0f)) + "%", (this.textColorMode.getValue() == ColorMode.Progress) ? new Color((int)(255.0 * Math.abs(this.animationTime.easeOutQuad() - 1.0)), (int)(255.0 * this.animationTime.easeOutQuad()), 0, 255) : ((Color)this.textColor.getValue()));
                            }
                            else {
                                RenderUtil.drawText(getSelectedBoundingBox, "100.0%", (this.textColorMode.getValue() == ColorMode.Progress) ? new Color(0, 255, 0, 255) : ((Color)this.textColor.getValue()));
                            }
                        }
                        else {
                            RenderUtil.drawText(getSelectedBoundingBox, "Waiting", (this.textColorMode.getValue() == ColorMode.Progress) ? new Color(0, 255, 0, 255) : ((Color)this.textColor.getValue()));
                        }
                    }
                }
            }
        }
    }
    
    private boolean lambda$new$8(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == Mode.Custom;
    }
    
    private int getTool(final BlockPos blockPos) {
        if (this.hotBar.getValue()) {
            final int n = -1;
            final float n2 = 1.0f;
            int n3 = 0;
            if (n3 < 9) {
                final ItemStack getStackInSlot = PacketMine.mc.player.inventory.getStackInSlot(n3);
                if (getStackInSlot != ItemStack.EMPTY && EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot) + getStackInSlot.getDestroySpeed(PacketMine.mc.world.getBlockState(blockPos)) > n2) {}
                ++n3;
                return 0;
            }
            return n;
        }
        else {
            final AtomicInteger atomicInteger = new AtomicInteger();
            atomicInteger.set(-1);
            final float n4 = 1.0f;
            final Iterator<Map.Entry<K, ItemStack>> iterator = InventoryUtil.getInventoryAndHotbarSlots().entrySet().iterator();
            if (iterator.hasNext()) {
                final Map.Entry<K, ItemStack> entry = iterator.next();
                if (!(entry.getValue().getItem() instanceof ItemAir) && EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, (ItemStack)entry.getValue()) + entry.getValue().getDestroySpeed(PacketMine.mc.world.getBlockState(blockPos)) > n4) {
                    atomicInteger.set((int)entry.getKey());
                }
                return 0;
            }
            return atomicInteger.get();
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return !(boolean)this.instant.getValue();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.wait.isOpen();
    }
    
    @SubscribeEvent
    public void onClickBlock(final BlockEvent blockEvent) {
        if (fullNullCheck() || PacketMine.mc.player.isCreative()) {
            return;
        }
        blockEvent.setCanceled(true);
        if (PacketMine.godBlocks.contains(PacketMine.mc.world.getBlockState(blockEvent.getBlockPos()).getBlock()) && (boolean)this.godCancel.getValue()) {
            return;
        }
        if (blockEvent.getBlockPos().equals((Object)PacketMine.breakPos)) {
            return;
        }
        PacketMine.breakPos = blockEvent.getBlockPos();
        this.mineTimer.reset();
        this.animationTime.reset();
        if (PacketMine.godBlocks.contains(PacketMine.mc.world.getBlockState(blockEvent.getBlockPos()).getBlock())) {
            return;
        }
        if ((boolean)this.restart.getValue() && !(boolean)this.instant.getValue()) {
            this.first = true;
        }
        this.firstTimer.reset();
        PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
        if (this.doubleBreak.getValue()) {
            PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
            PacketMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, PacketMine.breakPos, BlockUtil.getRayTraceFacing(PacketMine.breakPos)));
        }
        if (this.swing.getValue()) {
            PacketMine.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        this.breakNumber = 0;
    }
    
    public void draw(final BlockPos blockPos, final double n, final Color color, final boolean b) {
        if (this.animationMode.getValue() != Mode.Both && this.animationMode.getValue() != Mode.Custom) {
            AxisAlignedBB axisAlignedBB;
            if (this.animationMode.getValue() == Mode.InToOut) {
                axisAlignedBB = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
            }
            else if (this.animationMode.getValue() == Mode.Up) {
                final AxisAlignedBB getSelectedBoundingBox = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox.minX, getSelectedBoundingBox.minY, getSelectedBoundingBox.minZ, getSelectedBoundingBox.maxX, getSelectedBoundingBox.minY + (getSelectedBoundingBox.maxY - getSelectedBoundingBox.minY) * n, getSelectedBoundingBox.maxZ);
            }
            else if (this.animationMode.getValue() == Mode.Down) {
                final AxisAlignedBB getSelectedBoundingBox2 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox2.minX, getSelectedBoundingBox2.maxY - (getSelectedBoundingBox2.maxY - getSelectedBoundingBox2.minY) * n, getSelectedBoundingBox2.minZ, getSelectedBoundingBox2.maxX, getSelectedBoundingBox2.maxY, getSelectedBoundingBox2.maxZ);
            }
            else if (this.animationMode.getValue() == Mode.OutToIn) {
                axisAlignedBB = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(-Math.abs(n / 2.0 - 1.0));
            }
            else if (this.animationMode.getValue() == Mode.None) {
                axisAlignedBB = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
            }
            else {
                final AxisAlignedBB grow = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
                final AxisAlignedBB getSelectedBoundingBox3 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
                if (this.animationMode.getValue() == Mode.Horizontal) {
                    axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox3.minX, grow.minY, getSelectedBoundingBox3.minZ, getSelectedBoundingBox3.maxX, grow.maxY, getSelectedBoundingBox3.maxZ);
                }
                else {
                    axisAlignedBB = new AxisAlignedBB(grow.minX, getSelectedBoundingBox3.minY, grow.minZ, grow.maxX, getSelectedBoundingBox3.maxY, grow.maxZ);
                }
            }
            if (b) {
                axisAlignedBB = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
            }
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(axisAlignedBB, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(axisAlignedBB, color, (int)this.boxAlpha.getValue());
            }
        }
        else if (this.animationMode.getValue() == Mode.Custom) {
            if (b) {
                final AxisAlignedBB getSelectedBoundingBox4 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
                if (this.outline.getValue()) {
                    RenderUtil.drawBBBox(getSelectedBoundingBox4, color, (int)this.outlineAlpha.getValue());
                }
                if (this.box.getValue()) {
                    RenderUtil.drawBBFill(getSelectedBoundingBox4, color, (int)this.boxAlpha.getValue());
                }
            }
            else {
                final AxisAlignedBB grow2 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(-(float)this.fillStart.getValue() - n * (1.0f - (float)this.fillStart.getValue()));
                double n2 = n + (float)this.boxExtend.getValue();
                if (n2 > 1.0) {
                    n2 = 1.0;
                }
                final AxisAlignedBB grow3 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(-(float)this.boxStart.getValue() - n2 * (1.0f - (float)this.boxStart.getValue()));
                if (this.outline.getValue()) {
                    RenderUtil.drawBBBox(grow3, color, (int)this.outlineAlpha.getValue());
                }
                if (this.box.getValue()) {
                    RenderUtil.drawBBFill(grow2, color, (int)this.boxAlpha.getValue());
                }
            }
        }
        else if (b) {
            final AxisAlignedBB getSelectedBoundingBox5 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos);
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(getSelectedBoundingBox5, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(getSelectedBoundingBox5, color, (int)this.boxAlpha.getValue());
            }
        }
        else {
            final AxisAlignedBB grow4 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow4, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow4, color, (int)this.boxAlpha.getValue());
            }
            final AxisAlignedBB grow5 = PacketMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PacketMine.mc.world, blockPos).grow(-Math.abs(n / 2.0 - 1.0));
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow5, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow5, color, (int)this.boxAlpha.getValue());
            }
        }
    }
    
    public PacketMine() {
        super("PacketMine", "1", Category.COMBAT);
        this.delay = this.add(new Setting("Delay", 100, 0, 1000));
        this.damage = this.add(new Setting("Damage", 0.7f, 0.0f, 2.0f));
        this.range = this.add(new Setting("Range", 7.0f, 3.0f, 10.0f));
        this.maxBreak = this.add(new Setting("MaxBreak", 2, 1, 20));
        this.instant = this.add(new Setting("Instant", false));
        this.restart = this.add(new Setting("ReStart", true, this::lambda$new$0));
        this.wait = this.add(new Setting("Wait", true, this::lambda$new$1).setParent());
        this.mineAir = this.add(new Setting("MineAir", true, this::lambda$new$2));
        this.godCancel = this.add(new Setting("GodCancel", true));
        this.hotBar = this.add(new Setting("HotBar", false));
        this.onlyGround = this.add(new Setting("OnlyGround", true).setParent());
        this.allowWeb = this.add(new Setting("AllowWeb", true, this::lambda$new$3));
        this.doubleBreak = this.add(new Setting("DoubleBreak", true));
        this.swing = this.add(new Setting("Swing", true));
        this.rotate = this.add(new Setting("Rotate", true).setParent());
        this.time = this.add(new Setting("Time", 100, 0, 2000, this::lambda$new$4));
        this.switchReset = this.add(new Setting("SwitchReset", false));
        this.render = this.add(new Setting("Render", true).setParent());
        this.animationMode = this.add(new Setting("AnimationMode", Mode.Up, this::lambda$new$5));
        this.fillStart = this.add(new Setting("FillStart", 0.2f, 0.0f, 1.0f, this::lambda$new$6));
        this.boxStart = this.add(new Setting("BoxStart", 0.4f, 0.0f, 1.0f, this::lambda$new$7));
        this.boxExtend = this.add(new Setting("BoxExtend", 0.2f, 0.0f, 1.0f, this::lambda$new$8));
        this.text = this.add(new Setting("Text", true, this::lambda$new$9).setParent());
        this.textColorMode = this.add(new Setting("TextMode", ColorMode.Progress, this::lambda$new$10));
        this.textColor = this.add(new Setting("TextColor", new Color(255, 255, 255, 255), this::lambda$new$11).hideAlpha());
        this.box = this.add(new Setting("Box", true, this::lambda$new$12).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 100, 0, 255, this::lambda$new$13));
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$14).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 100, 0, 255, this::lambda$new$15));
        this.colorMode = this.add(new Setting("ColorMode", ColorMode.Progress, this::lambda$new$16));
        this.color = this.add(new Setting("Color", new Color(189, 212, 255), this::lambda$new$17).hideAlpha());
        this.enderChest = this.add(new Setting("EnderChest", new Bind(-1)));
        this.debug = this.add(new Setting("Debug", false));
        this.mineTimer = new Timer();
        this.animationTime = new FadeUtils(1000L);
        this.startMine = false;
        this.breakNumber = 0;
        this.delayTimer = new Timer();
        this.first = false;
        this.firstTimer = new Timer();
        this.lastSlot = -1;
        PacketMine.INSTANCE = this;
    }
    
    private boolean lambda$new$13(final Integer n) {
        return this.box.isOpen() && this.render.isOpen();
    }
    
    public static float getDigSpeed(final IBlockState blockState, final ItemStack itemStack) {
        float destroySpeed = getDestroySpeed(blockState, itemStack);
        if (destroySpeed > 1.0f) {
            final int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, itemStack);
            if (getEnchantmentLevel > 0 && !itemStack.isEmpty()) {
                destroySpeed += (float)(StrictMath.pow(getEnchantmentLevel, 2.0) + 1.0);
            }
        }
        if (PacketMine.mc.player.isPotionActive(MobEffects.HASTE)) {
            destroySpeed *= 1.0f + (PacketMine.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (PacketMine.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float n = 0.0f;
            switch (PacketMine.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    n = 0.3f;
                    break;
                }
                case 1: {
                    n = 0.09f;
                    break;
                }
                case 2: {
                    n = 0.0027f;
                    break;
                }
                default: {
                    n = 8.1E-4f;
                    break;
                }
            }
            destroySpeed *= n;
        }
        if (PacketMine.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)PacketMine.mc.player)) {
            destroySpeed /= 5.0f;
        }
        return (destroySpeed < 0.0f) ? 0.0f : destroySpeed;
    }
    
    private boolean lambda$new$17(final Color color) {
        return this.render.isOpen() && this.colorMode.getValue() == ColorMode.Custom;
    }
    
    public enum Mode
    {
        Both("Both", 4), 
        InToOut("InToOut", 2), 
        None("None", 8), 
        Down("Down", 0);
        
        private static final Mode[] $VALUES;
        
        Custom("Custom", 7), 
        OutToIn("OutToIn", 3), 
        Up("Up", 1), 
        Horizontal("Horizontal", 6), 
        Vertical("Vertical", 5);
        
        static {
            $VALUES = new Mode[] { Mode.Down, Mode.Up, Mode.InToOut, Mode.OutToIn, Mode.Both, Mode.Vertical, Mode.Horizontal, Mode.Custom, Mode.None };
        }
        
        private Mode(final String s, final int n) {
        }
    }
    
    public enum ColorMode
    {
        private static final ColorMode[] $VALUES;
        
        Progress("Progress", 1), 
        Custom("Custom", 0);
        
        static {
            $VALUES = new ColorMode[] { ColorMode.Custom, ColorMode.Progress };
        }
        
        private ColorMode(final String s, final int n) {
        }
    }
}
