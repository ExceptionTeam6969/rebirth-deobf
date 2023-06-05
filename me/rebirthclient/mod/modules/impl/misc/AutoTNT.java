//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.*;

public class AutoTNT extends Module
{
    private final Setting range;
    private final Setting packet;
    private EntityPlayer target;
    private final Setting rotate;
    
    private EntityPlayer getTarget(final double n) {
        final EntityPlayer entityPlayer = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)AutoTNT.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return entityPlayer;
        }
        final EntityPlayer entityPlayer2 = iterator.next();
        if (EntityUtil.invalid((Entity)entityPlayer2, n)) {
            return null;
        }
        if (!BlockUtil.canPlace(EntityUtil.getEntityPos((Entity)entityPlayer2).up(2)) && AutoTNT.mc.world.getBlockState(EntityUtil.getEntityPos((Entity)entityPlayer2).up(2)).getBlock() != Blocks.TNT) {
            return null;
        }
        if (entityPlayer == null) {
            AutoTNT.mc.player.getDistanceSq((Entity)entityPlayer2);
            return null;
        }
        if (AutoTNT.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
            return null;
        }
        AutoTNT.mc.player.getDistanceSq((Entity)entityPlayer2);
        return null;
    }
    
    @Override
    public void onUpdate() {
        if (InventoryUtil.findHotbarBlock(Blocks.TNT) == -1) {
            return;
        }
        if (InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL) == -1 && InventoryUtil.findHotbarBlock(Blocks.REDSTONE_BLOCK) == -1) {
            return;
        }
        this.target = this.getTarget((float)this.range.getValue());
        if (this.target == null) {
            return;
        }
        final int currentItem = AutoTNT.mc.player.inventory.currentItem;
        final BlockPos up = EntityUtil.getEntityPos((Entity)this.target).up(2);
        if (BlockUtil.canPlace(up)) {
            InventoryUtil.doSwap(InventoryUtil.findHotbarBlock(Blocks.TNT));
            BlockUtil.placeBlock(up, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
            InventoryUtil.doSwap(currentItem);
        }
        if (AutoTNT.mc.world.getBlockState(up).getBlock() == Blocks.TNT) {
            if (InventoryUtil.findHotbarBlock(Blocks.REDSTONE_BLOCK) != -1) {
                final EnumFacing[] VALUES = EnumFacing.VALUES;
                final int length = VALUES.length;
                int n = 0;
                if (n < length) {
                    if (AutoTNT.mc.world.getBlockState(up.offset(VALUES[n])).getBlock() == Blocks.REDSTONE_BLOCK) {
                        return;
                    }
                    ++n;
                }
                else {
                    final EnumFacing[] VALUES2 = EnumFacing.VALUES;
                    final int length2 = VALUES2.length;
                    int n2 = 0;
                    if (n2 < length2) {
                        final EnumFacing enumFacing = VALUES2[n2];
                        if (enumFacing != EnumFacing.DOWN) {
                            if (BlockUtil.canPlace(up.offset(enumFacing))) {
                                InventoryUtil.doSwap(InventoryUtil.findHotbarBlock(Blocks.REDSTONE_BLOCK));
                                BlockUtil.placeBlock(up.offset(enumFacing), EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
                                InventoryUtil.doSwap(currentItem);
                                return;
                            }
                        }
                        ++n2;
                    }
                }
            }
            else if (InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL) != -1) {
                InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.FLINT_AND_STEEL));
                final Vec3d add = new Vec3d((Vec3i)up).add(0.5, 0.5, 0.5).add(new Vec3d(BlockUtil.getRayTraceFacing(up).getDirectionVec()).scale(0.5));
                if (this.rotate.getValue()) {
                    EntityUtil.faceVector(add);
                }
                AutoTNT.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(up, BlockUtil.getRayTraceFacing(up), EnumHand.MAIN_HAND, (float)(add.x - up.getX()), (float)(add.y - up.getY()), (float)(add.z - up.getZ())));
                InventoryUtil.doSwap(currentItem);
            }
        }
    }
    
    public AutoTNT() {
        super("AutoTNT", "IQless", Category.MISC);
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.range = this.add(new Setting("Range", 4.0f, 0.0f, 6.0f));
    }
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
}
