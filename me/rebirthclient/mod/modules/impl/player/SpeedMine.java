//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.*;
import java.awt.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;

public class SpeedMine extends Module
{
    private final Setting startDamage;
    private final Setting colorMode;
    private final Setting outline;
    private static float mineDamage;
    private final Setting animationMode;
    private final Setting outlineAlpha;
    private final Setting boxExtend;
    private final Setting boxAlpha;
    private final Setting boxStart;
    private final Setting strictReMine;
    private BlockPos minePosition;
    private final Setting mode;
    private final Setting rotate;
    private final Setting color;
    private EnumFacing mineFacing;
    private final Setting box;
    private final Setting fillStart;
    private final Setting strict;
    private final Setting endDamage;
    private int mineBreaks;
    private final Setting render;
    private final Setting range;
    
    public void draw(final BlockPos blockPos, double n, final Color color) {
        if (n > 1.0) {
            n = 1.0;
        }
        if (this.animationMode.getValue() != PacketMine.Mode.Both && this.animationMode.getValue() != PacketMine.Mode.Custom) {
            AxisAlignedBB axisAlignedBB;
            if (this.animationMode.getValue() == PacketMine.Mode.InToOut) {
                axisAlignedBB = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
            }
            else if (this.animationMode.getValue() == PacketMine.Mode.Up) {
                final AxisAlignedBB getSelectedBoundingBox = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox.minX, getSelectedBoundingBox.minY, getSelectedBoundingBox.minZ, getSelectedBoundingBox.maxX, getSelectedBoundingBox.minY + (getSelectedBoundingBox.maxY - getSelectedBoundingBox.minY) * n, getSelectedBoundingBox.maxZ);
            }
            else if (this.animationMode.getValue() == PacketMine.Mode.Down) {
                final AxisAlignedBB getSelectedBoundingBox2 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos);
                axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox2.minX, getSelectedBoundingBox2.maxY - (getSelectedBoundingBox2.maxY - getSelectedBoundingBox2.minY) * n, getSelectedBoundingBox2.minZ, getSelectedBoundingBox2.maxX, getSelectedBoundingBox2.maxY, getSelectedBoundingBox2.maxZ);
            }
            else if (this.animationMode.getValue() == PacketMine.Mode.OutToIn) {
                axisAlignedBB = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(-Math.abs(n / 2.0 - 1.0));
            }
            else if (this.animationMode.getValue() == PacketMine.Mode.None) {
                axisAlignedBB = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos);
            }
            else {
                final AxisAlignedBB grow = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
                final AxisAlignedBB getSelectedBoundingBox3 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos);
                if (this.animationMode.getValue() == PacketMine.Mode.Horizontal) {
                    axisAlignedBB = new AxisAlignedBB(getSelectedBoundingBox3.minX, grow.minY, getSelectedBoundingBox3.minZ, getSelectedBoundingBox3.maxX, grow.maxY, getSelectedBoundingBox3.maxZ);
                }
                else {
                    axisAlignedBB = new AxisAlignedBB(grow.minX, getSelectedBoundingBox3.minY, grow.minZ, grow.maxX, getSelectedBoundingBox3.maxY, grow.maxZ);
                }
            }
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(axisAlignedBB, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(axisAlignedBB, color, (int)this.boxAlpha.getValue());
            }
        }
        else if (this.animationMode.getValue() == PacketMine.Mode.Custom) {
            final AxisAlignedBB grow2 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(-(float)this.fillStart.getValue() - n * (1.0f - (float)this.fillStart.getValue()));
            double n2 = n + (float)this.boxExtend.getValue();
            if (n2 > 1.0) {
                n2 = 1.0;
            }
            final AxisAlignedBB grow3 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(-(float)this.boxStart.getValue() - n2 * (1.0f - (float)this.boxStart.getValue()));
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow3, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow2, color, (int)this.boxAlpha.getValue());
            }
        }
        else {
            final AxisAlignedBB grow4 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(n / 2.0 - 0.5);
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow4, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow4, color, (int)this.boxAlpha.getValue());
            }
            final AxisAlignedBB grow5 = SpeedMine.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)SpeedMine.mc.world, blockPos).grow(-Math.abs(n / 2.0 - 1.0));
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(grow5, color, (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(grow5, color, (int)this.boxAlpha.getValue());
            }
        }
    }
    
    @SubscribeEvent
    public void onEntitySync(final MotionEvent motionEvent) {
        if ((boolean)this.rotate.getValue() && SpeedMine.mineDamage > 0.95 && this.minePosition != null) {
            final float[] calcAngle = MathUtil.calcAngle(SpeedMine.mc.player.getPositionEyes(SpeedMine.mc.getRenderPartialTicks()), new Vec3d((Vec3i)this.minePosition.add(0.5, 0.5, 0.5)));
            motionEvent.setYaw(calcAngle[0]);
            motionEvent.setPitch(calcAngle[1]);
        }
    }
    
    private boolean lambda$new$5(final Boolean b) {
        return this.mode.getValue() == Mode.Packet;
    }
    
    public float getBlockStrength(final IBlockState blockState, final BlockPos blockPos) {
        final float getBlockHardness = blockState.getBlockHardness((World)SpeedMine.mc.world, blockPos);
        if (getBlockHardness < 0.0f) {
            return 0.0f;
        }
        if (blockPos != 0) {
            return this.getDigSpeed(blockState) / getBlockHardness / 100.0f;
        }
        return this.getDigSpeed(blockState) / getBlockHardness / 30.0f;
    }
    
    private boolean lambda$new$15(final PacketMine.ColorMode colorMode) {
        return this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange && (boolean)this.strict.getValue()) {
            SpeedMine.mineDamage = 0.0f;
        }
    }
    
    private boolean lambda$new$1(final Float n) {
        return this.mode.getValue() == Mode.Damage;
    }
    
    private boolean lambda$new$16(final Color color) {
        return this.render.isOpen() && this.mode.getValue() == Mode.Packet && this.colorMode.getValue() == PacketMine.ColorMode.Custom;
    }
    
    private boolean lambda$new$11(final Boolean b) {
        return this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    private int getTool(final BlockPos blockPos) {
        final int n = -1;
        final float n2 = 1.0f;
        int n3 = 0;
        if (n3 < 9) {
            final ItemStack getStackInSlot = SpeedMine.mc.player.inventory.getStackInSlot(n3);
            if (getStackInSlot != ItemStack.EMPTY && EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot) + getStackInSlot.getDestroySpeed(SpeedMine.mc.world.getBlockState(blockPos)) > n2) {}
            ++n3;
            return 0;
        }
        return n;
    }
    
    private ItemStack getTool2(final IBlockState blockState) {
        final ItemStack itemStack = null;
        final float n = 1.0f;
        int n2 = 0;
        if (n2 < 9) {
            final ItemStack getStackInSlot = SpeedMine.mc.player.inventory.getStackInSlot(n2);
            if (getStackInSlot != ItemStack.EMPTY && EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot) + getStackInSlot.getDestroySpeed(blockState) > n) {}
            ++n2;
            return null;
        }
        return itemStack;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.mode.getValue() == Mode.Packet;
    }
    
    private boolean lambda$new$9(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == PacketMine.Mode.Custom && this.mode.getValue() == Mode.Packet;
    }
    
    public SpeedMine() {
        super("SpeedMine", "Allows you to dig-quickly", Category.PLAYER);
        this.mode = this.register(new Setting("Mode", Mode.Packet));
        this.startDamage = this.register(new Setting("StartDamage", 0.1f, 0.0f, 1.0f, this::lambda$new$0));
        this.endDamage = this.register(new Setting("EndDamage", 0.9f, 0.0f, 1.0f, this::lambda$new$1));
        this.range = this.register(new Setting("Range", 6.2f, 3.0f, 10.0f, this::lambda$new$2));
        this.rotate = this.register(new Setting("Rotate", false, this::lambda$new$3));
        this.strict = this.register(new Setting("Strict", false, this::lambda$new$4));
        this.strictReMine = this.register(new Setting("StrictBreak", false, this::lambda$new$5));
        this.render = this.add(new Setting("Render", true, this::lambda$new$6).setParent());
        this.animationMode = this.add(new Setting("AnimationMode", PacketMine.Mode.Up, this::lambda$new$7));
        this.fillStart = this.add(new Setting("FillStart", 0.2f, 0.0f, 1.0f, this::lambda$new$8));
        this.boxStart = this.add(new Setting("BoxStart", 0.4f, 0.0f, 1.0f, this::lambda$new$9));
        this.boxExtend = this.add(new Setting("BoxExtend", 0.2f, 0.0f, 1.0f, this::lambda$new$10));
        this.box = this.add(new Setting("Box", true, this::lambda$new$11).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 100, 0, 255, this::lambda$new$12));
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$13).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 100, 0, 255, this::lambda$new$14));
        this.colorMode = this.add(new Setting("ColorMode", PacketMine.ColorMode.Progress, this::lambda$new$15));
        this.color = this.add(new Setting("Color", new Color(189, 212, 255), this::lambda$new$16).hideAlpha());
    }
    
    private boolean lambda$new$12(final Integer n) {
        return this.box.isOpen() && this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    private boolean lambda$new$8(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == PacketMine.Mode.Custom && this.mode.getValue() == Mode.Packet;
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.mode.getValue() == Mode.Damage;
    }
    
    @Override
    public void onUpdate() {
        if (!SpeedMine.mc.player.capabilities.isCreativeMode) {
            if (this.minePosition != null) {
                final double getDistance = SpeedMine.mc.player.getDistance(this.minePosition.getX() + 0.5, this.minePosition.getY() + 0.5, this.minePosition.getZ() + 0.5);
                if ((this.mineBreaks >= 2 && (boolean)this.strictReMine.getValue()) || getDistance > (float)this.range.getValue() || SpeedMine.mc.world.isAirBlock(this.minePosition)) {
                    this.minePosition = null;
                    this.mineFacing = null;
                    SpeedMine.mineDamage = 0.0f;
                    this.mineBreaks = 0;
                }
            }
            if (this.mode.getValue() == Mode.Damage) {
                if (SpeedMine.mc.playerController.curBlockDamageMP < (float)this.startDamage.getValue()) {
                    SpeedMine.mc.playerController.curBlockDamageMP = (float)this.startDamage.getValue();
                }
                if (SpeedMine.mc.playerController.curBlockDamageMP >= (float)this.endDamage.getValue()) {
                    SpeedMine.mc.playerController.curBlockDamageMP = 1.0f;
                }
            }
            else if (this.mode.getValue() == Mode.Packet) {
                if (this.minePosition != null) {
                    if (SpeedMine.mineDamage >= 1.0f) {
                        final int currentItem = SpeedMine.mc.player.inventory.currentItem;
                        final int tool = this.getTool(this.minePosition);
                        if (tool == -1) {
                            return;
                        }
                        if (this.strict.getValue()) {
                            SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(SpeedMine.mc.player.inventoryContainer.windowId, tool, SpeedMine.mc.player.inventory.currentItem, ClickType.SWAP, SpeedMine.mc.player.openContainer.slotClick(tool, SpeedMine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)SpeedMine.mc.player), SpeedMine.mc.player.openContainer.getNextTransactionID(SpeedMine.mc.player.inventory)));
                        }
                        else {
                            SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(tool));
                        }
                        SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                        SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.minePosition, EnumFacing.UP));
                        if (this.strict.getValue()) {
                            SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                        }
                        SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                        if (currentItem != -1) {
                            if (this.strict.getValue()) {
                                final short getNextTransactionID = SpeedMine.mc.player.openContainer.getNextTransactionID(SpeedMine.mc.player.inventory);
                                SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(SpeedMine.mc.player.inventoryContainer.windowId, tool, SpeedMine.mc.player.inventory.currentItem, ClickType.SWAP, SpeedMine.mc.player.openContainer.slotClick(tool, SpeedMine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)SpeedMine.mc.player), getNextTransactionID));
                                SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketConfirmTransaction(SpeedMine.mc.player.inventoryContainer.windowId, getNextTransactionID, true));
                            }
                            else {
                                SpeedMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
                            }
                        }
                        SpeedMine.mineDamage = 0.0f;
                        ++this.mineBreaks;
                    }
                    SpeedMine.mineDamage += this.getBlockStrength(SpeedMine.mc.world.getBlockState(this.minePosition), this.minePosition);
                }
                else {
                    SpeedMine.mineDamage = 0.0f;
                }
            }
        }
    }
    
    private boolean lambda$new$14(final Integer n) {
        return this.outline.isOpen() && this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    private boolean lambda$new$7(final PacketMine.Mode mode) {
        return this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    @Override
    public void onDisable() {
        this.minePosition = null;
        this.mineFacing = null;
        SpeedMine.mineDamage = 0.0f;
        this.mineBreaks = 0;
    }
    
    public float getDestroySpeed(final IBlockState blockState) {
        float n = 1.0f;
        if (this.getTool2(blockState) != null && !this.getTool2(blockState).isEmpty()) {
            n *= this.getTool2(blockState).getDestroySpeed(blockState);
        }
        return n;
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.mode.getValue() == Mode.Packet;
    }
    
    @SubscribeEvent
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.mode.getValue() == Mode.Packet && this.minePosition != null && !SpeedMine.mc.world.isAirBlock(this.minePosition)) {
            double n = SpeedMine.mineDamage;
            if (n <= 0.0) {
                n = 0.0;
            }
            else if (n >= 1.0) {
                n = 1.0;
            }
            this.draw(this.minePosition, SpeedMine.mineDamage, (this.colorMode.getValue() == PacketMine.ColorMode.Custom) ? ((Color)this.color.getValue()) : new Color((int)(255.0 * Math.abs(n - 1.0)), (int)(255.0 * n), 0));
        }
    }
    
    private boolean lambda$new$10(final Float n) {
        return this.render.isOpen() && this.animationMode.getValue() == PacketMine.Mode.Custom && this.mode.getValue() == Mode.Packet;
    }
    
    @SubscribeEvent
    public void onLeftClickBlock(final BlockEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //     6: getfield        net/minecraft/client/entity/EntityPlayerSP.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //     9: getfield        net/minecraft/entity/player/PlayerCapabilities.isCreativeMode:Z
        //    12: ifne            153
        //    15: aload_0        
        //    16: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.mode:Lme/rebirthclient/mod/modules/settings/Setting;
        //    19: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    22: getstatic       me/rebirthclient/mod/modules/impl/player/SpeedMine$Mode.Packet:Lme/rebirthclient/mod/modules/impl/player/SpeedMine$Mode;
        //    25: if_acmpne       153
        //    28: aload_1        
        //    29: iconst_1       
        //    30: invokevirtual   me/rebirthclient/api/events/impl/BlockEvent.setCanceled:(Z)V
        //    33: aload_0        
        //    34: aload_1        
        //    35: invokevirtual   me/rebirthclient/api/events/impl/BlockEvent.getBlockPos:()Lnet/minecraft/util/math/BlockPos;
        //    38: ifeq            153
        //    41: aload_1        
        //    42: invokevirtual   me/rebirthclient/api/events/impl/BlockEvent.getBlockPos:()Lnet/minecraft/util/math/BlockPos;
        //    45: aload_0        
        //    46: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.minePosition:Lnet/minecraft/util/math/BlockPos;
        //    49: invokevirtual   net/minecraft/util/math/BlockPos.equals:(Ljava/lang/Object;)Z
        //    52: ifne            153
        //    55: aload_0        
        //    56: aload_1        
        //    57: invokevirtual   me/rebirthclient/api/events/impl/BlockEvent.getBlockPos:()Lnet/minecraft/util/math/BlockPos;
        //    60: putfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.minePosition:Lnet/minecraft/util/math/BlockPos;
        //    63: aload_0        
        //    64: aload_1        
        //    65: invokevirtual   me/rebirthclient/api/events/impl/BlockEvent.getEnumFacing:()Lnet/minecraft/util/EnumFacing;
        //    68: putfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.mineFacing:Lnet/minecraft/util/EnumFacing;
        //    71: fconst_0       
        //    72: putstatic       me/rebirthclient/mod/modules/impl/player/SpeedMine.mineDamage:F
        //    75: aload_0        
        //    76: iconst_0       
        //    77: putfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.mineBreaks:I
        //    80: aload_0        
        //    81: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.minePosition:Lnet/minecraft/util/math/BlockPos;
        //    84: ifnull          153
        //    87: aload_0        
        //    88: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.mineFacing:Lnet/minecraft/util/EnumFacing;
        //    91: ifnull          153
        //    94: getstatic       me/rebirthclient/mod/modules/impl/player/SpeedMine.mc:Lnet/minecraft/client/Minecraft;
        //    97: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   100: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   103: new             Lnet/minecraft/network/play/client/CPacketPlayerDigging;
        //   106: dup            
        //   107: getstatic       net/minecraft/network/play/client/CPacketPlayerDigging$Action.START_DESTROY_BLOCK:Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;
        //   110: aload_0        
        //   111: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.minePosition:Lnet/minecraft/util/math/BlockPos;
        //   114: aload_0        
        //   115: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.mineFacing:Lnet/minecraft/util/EnumFacing;
        //   118: invokespecial   net/minecraft/network/play/client/CPacketPlayerDigging.<init>:(Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V
        //   121: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   124: getstatic       me/rebirthclient/mod/modules/impl/player/SpeedMine.mc:Lnet/minecraft/client/Minecraft;
        //   127: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   130: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   133: new             Lnet/minecraft/network/play/client/CPacketPlayerDigging;
        //   136: dup            
        //   137: getstatic       net/minecraft/network/play/client/CPacketPlayerDigging$Action.ABORT_DESTROY_BLOCK:Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;
        //   140: aload_0        
        //   141: getfield        me/rebirthclient/mod/modules/impl/player/SpeedMine.minePosition:Lnet/minecraft/util/math/BlockPos;
        //   144: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   147: invokespecial   net/minecraft/network/play/client/CPacketPlayerDigging.<init>:(Lnet/minecraft/network/play/client/CPacketPlayerDigging$Action;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V
        //   150: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   153: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0153 (coming from #0038).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.mode.getValue() == Mode.Packet;
    }
    
    public float getDigSpeed(final IBlockState blockState) {
        float destroySpeed = this.getDestroySpeed(blockState);
        if (destroySpeed > 1.0f) {
            final ItemStack tool2 = this.getTool2(blockState);
            final int getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, tool2);
            if (getEnchantmentLevel > 0 && !tool2.isEmpty()) {
                destroySpeed += (float)(StrictMath.pow(getEnchantmentLevel, 2.0) + 1.0);
            }
        }
        if (SpeedMine.mc.player.isPotionActive(MobEffects.HASTE)) {
            destroySpeed *= 1.0f + (SpeedMine.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (SpeedMine.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float n = 0.0f;
            switch (SpeedMine.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
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
        if (SpeedMine.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)SpeedMine.mc.player)) {
            destroySpeed /= 5.0f;
        }
        if (!SpeedMine.mc.player.onGround) {
            destroySpeed /= 5.0f;
        }
        return (destroySpeed < 0.0f) ? 0.0f : destroySpeed;
    }
    
    private boolean lambda$new$13(final Boolean b) {
        return this.render.isOpen() && this.mode.getValue() == Mode.Packet;
    }
    
    private boolean lambda$new$6(final Boolean b) {
        return this.mode.getValue() == Mode.Packet;
    }
    
    public enum Mode
    {
        private static final Mode[] $VALUES;
        
        Damage("Damage", 1), 
        Packet("Packet", 0);
        
        static {
            $VALUES = new Mode[] { Mode.Packet, Mode.Damage };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
