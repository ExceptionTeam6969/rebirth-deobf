//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.world.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;

public class BlockTweaks extends Module
{
    public Setting autoTool;
    private int lastHotbarSlot;
    public Setting noFriendAttack;
    public Setting noGhost;
    private int currentTargetSlot;
    public static BlockTweaks INSTANCE;
    private boolean switched;
    
    private void removeGlitchBlocks(final BlockPos blockPos) {
        int n = -4;
        if (n > 4) {
            return;
        }
        int n2 = -4;
        if (n2 > 4) {
            ++n;
            return;
        }
        int n3 = -4;
        if (n3 <= 4) {
            final BlockPos blockPos2 = new BlockPos(blockPos.getX() + n, blockPos.getY() + n2, blockPos.getZ() + n3);
            if (BlockTweaks.mc.world.getBlockState(blockPos2).getBlock().equals(Blocks.AIR)) {
                BlockTweaks.mc.playerController.processRightClickBlock(BlockTweaks.mc.player, BlockTweaks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
            }
            ++n3;
            return;
        }
        ++n2;
    }
    
    public BlockTweaks() {
        super("BlockTweaks", "Some tweaks for blocks", Category.PLAYER);
        this.noFriendAttack = this.add(new Setting("NoFriendAttack", false));
        this.autoTool = this.add(new Setting("AutoTool", false));
        this.noGhost = this.add(new Setting("NoGlitchBlocks", false));
        this.lastHotbarSlot = -1;
        this.switched = false;
        this.currentTargetSlot = -1;
        BlockTweaks.INSTANCE = this;
    }
    
    @Override
    public void onUpdate() {
        if (BlockTweaks.mc.player.inventory.currentItem != this.lastHotbarSlot && BlockTweaks.mc.player.inventory.currentItem != this.currentTargetSlot) {
            this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
        }
        if (!BlockTweaks.mc.gameSettings.keyBindAttack.isKeyDown() && this.switched) {
            this.equip(this.lastHotbarSlot, false);
        }
    }
    
    private void equip(final int n, final boolean switched) {
        if (n != -1) {
            if (n != BlockTweaks.mc.player.inventory.currentItem) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            this.currentTargetSlot = n;
            BlockTweaks.mc.player.inventory.currentItem = n;
            this.switched = switched;
        }
    }
    
    @SubscribeEvent
    public void onBreak(final BlockEvent.BreakEvent breakEvent) {
        if (fullNullCheck() || !(boolean)this.noGhost.getValue()) {
            return;
        }
        if (!(BlockTweaks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            this.removeGlitchBlocks(BlockTweaks.mc.player.getPosition());
        }
    }
    
    @Override
    public void onDisable() {
        if (this.switched) {
            this.equip(this.lastHotbarSlot, false);
        }
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        final Entity getEntityFromWorld;
        if ((boolean)this.noFriendAttack.getValue() && send.getPacket() instanceof CPacketUseEntity && (getEntityFromWorld = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)BlockTweaks.mc.world)) != null && Managers.FRIENDS.isFriend(getEntityFromWorld.getName())) {
            send.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onBlockInteract(final PlayerInteractEvent.LeftClickBlock leftClickBlock) {
        if ((boolean)this.autoTool.getValue() && !fullNullCheck()) {
            this.equipBestTool(BlockTweaks.mc.world.getBlockState(leftClickBlock.getPos()));
        }
    }
    
    private void equipBestTool(final IBlockState blockState) {
        final int n = -1;
        final double n2 = 0.0;
        int n3 = 0;
        if (n3 < 9) {
            final ItemStack getStackInSlot = BlockTweaks.mc.player.inventory.getStackInSlot(n3);
            final float getDestroySpeed;
            final int getEnchantmentLevel;
            final float n4;
            if (!getStackInSlot.isEmpty && (getDestroySpeed = getStackInSlot.getDestroySpeed(blockState)) > 1.0f && (n4 = (float)(getDestroySpeed + (((getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot)) > 0) ? (Math.pow(getEnchantmentLevel, 2.0) + 1.0) : 0.0))) > n2) {
                final double n5 = n4;
            }
            ++n3;
            return;
        }
        this.equip(n, true);
    }
}
