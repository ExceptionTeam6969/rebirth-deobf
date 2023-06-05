//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;

public class AntiPiston extends Module
{
    public final Setting packet;
    private final Setting onlyBurrow;
    public final Setting helper;
    public final Setting trap;
    private final Setting whenDouble;
    private final Setting maxSelfSpeed;
    public final Setting rotate;
    public static AntiPiston INSTANCE;
    
    private Block getBlock(final BlockPos blockPos) {
        return AntiPiston.mc.world.getBlockState(blockPos).getBlock();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck() || !AntiPiston.mc.player.onGround || Managers.SPEED.getPlayerSpeed((EntityPlayer)AntiPiston.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            return;
        }
        this.block();
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (blockPos != 0) {
            return;
        }
        final int currentItem = AntiPiston.mc.player.inventory.currentItem;
        if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1) {
            return;
        }
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        InventoryUtil.doSwap(currentItem);
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.onlyBurrow.isOpen();
    }
    
    public AntiPiston() {
        super("AntiPiston", "Trap self when piston kick", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 6.0, 1.0, 30.0));
        this.helper = this.add(new Setting("Helper", true));
        this.trap = this.add(new Setting("Trap", true).setParent());
        this.onlyBurrow = this.add(new Setting("OnlyBurrow", true, this::lambda$new$0).setParent());
        this.whenDouble = this.add(new Setting("WhenDouble", true, this::lambda$new$1));
        AntiPiston.INSTANCE = this;
    }
    
    private void block() {
        final BlockPos playerPos = EntityUtil.getPlayerPos();
        if (this.getBlock(playerPos.up(2)) == Blocks.OBSIDIAN || this.getBlock(playerPos.up(2)) == Blocks.BEDROCK) {
            return;
        }
        int n = 0;
        if (this.whenDouble.getValue()) {
            final EnumFacing[] VALUES = EnumFacing.VALUES;
            final int length = VALUES.length;
            int n2 = 0;
            if (n2 < length) {
                final EnumFacing enumFacing = VALUES[n2];
                if (enumFacing != EnumFacing.DOWN) {
                    if (enumFacing != EnumFacing.UP) {
                        if (this.getBlock(playerPos.offset(enumFacing).up()) instanceof BlockPistonBase) {
                            if (((EnumFacing)AntiPiston.mc.world.getBlockState(playerPos.offset(enumFacing).up()).getValue((IProperty)BlockDirectional.FACING)).getOpposite() == enumFacing) {
                                ++n;
                            }
                        }
                    }
                }
                ++n2;
                return;
            }
        }
        final EnumFacing[] VALUES2 = EnumFacing.VALUES;
        final int length2 = VALUES2.length;
        int n3 = 0;
        if (n3 < length2) {
            final EnumFacing enumFacing2 = VALUES2[n3];
            if (enumFacing2 != EnumFacing.DOWN) {
                if (enumFacing2 != EnumFacing.UP) {
                    if (this.getBlock(playerPos.offset(enumFacing2).up()) instanceof BlockPistonBase) {
                        if (((EnumFacing)AntiPiston.mc.world.getBlockState(playerPos.offset(enumFacing2).up()).getValue((IProperty)BlockDirectional.FACING)).getOpposite() == enumFacing2) {
                            this.placeBlock(playerPos.up().offset(enumFacing2, -1));
                            if ((boolean)this.trap.getValue() && (this.getBlock(playerPos) != Blocks.AIR || !(boolean)this.onlyBurrow.getValue() || n >= 2)) {
                                this.placeBlock(playerPos.up(2));
                                if (!BlockUtil.canPlaceEnum(playerPos.up(2))) {
                                    final EnumFacing[] VALUES3 = EnumFacing.VALUES;
                                    final int length3 = VALUES3.length;
                                    int n4 = 0;
                                    if (n4 < length3) {
                                        final EnumFacing enumFacing3 = VALUES3[n4];
                                        if (playerPos.offset(enumFacing3).up(2) != 0) {
                                            ++n4;
                                            return;
                                        }
                                        this.placeBlock(playerPos.offset(enumFacing3).up(2));
                                    }
                                }
                            }
                            if (!BlockUtil.canPlaceEnum(playerPos.up().offset(enumFacing2, -1)) && (boolean)this.helper.getValue()) {
                                if (BlockUtil.canPlaceEnum(playerPos.offset(enumFacing2, -1))) {
                                    this.placeBlock(playerPos.offset(enumFacing2, -1));
                                }
                                else {
                                    this.placeBlock(playerPos.offset(enumFacing2, -1).down());
                                }
                            }
                        }
                    }
                }
            }
            ++n3;
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.trap.isOpen();
    }
}
