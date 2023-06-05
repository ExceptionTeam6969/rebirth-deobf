//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.item.*;

public class SelfWeb extends Module
{
    private final Setting enemyRange;
    public final List blackList;
    private boolean sneak;
    private int newSlot;
    private final Setting rotate;
    private final Setting smart;
    
    @Override
    public void onDisable() {
        if (SelfWeb.mc.player != null && this.sneak) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneak = false;
        }
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.smart.isOpen();
    }
    
    private boolean canBeClicked(final BlockPos blockPos) {
        return BlockUtil.getBlock(blockPos).canCollideCheck(BlockUtil.getState(blockPos), false);
    }
    
    private EntityPlayer getClosestTarget() {
        if (SelfWeb.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        final Entity entity = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)SelfWeb.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return (EntityPlayer)entity;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (entityPlayer == SelfWeb.mc.player) {
            return null;
        }
        if (!EntityUtil.isLiving((Entity)entityPlayer)) {
            return null;
        }
        if (entityPlayer.getHealth() <= 0.0f) {
            return null;
        }
        if (entity != null && SelfWeb.mc.player.getDistance((Entity)entityPlayer) > SelfWeb.mc.player.getDistance(entity)) {
            return null;
        }
        return null;
    }
    
    @Override
    public void onEnable() {
        if (SelfWeb.mc.player != null) {
            this.newSlot = this.getHotbarItem();
            if (this.newSlot == -1) {
                this.sendMessage("[" + this.getName() + "] " + ChatFormatting.RED + "No Webs in hotbar. disabling...");
                this.disable();
            }
        }
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (!SelfWeb.mc.world.getBlockState(blockPos).getMaterial().isReplaceable()) {
            return;
        }
        if (blockPos != 0) {
            return;
        }
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        int n = 0;
        if (n >= length) {
            return;
        }
        final EnumFacing enumFacing = values[n];
        final BlockPos offset = blockPos.offset(enumFacing);
        final EnumFacing getOpposite = enumFacing.getOpposite();
        if (!this.canBeClicked(offset)) {
            ++n;
            return;
        }
        if (this.blackList.contains(SelfWeb.mc.world.getBlockState(offset).getBlock())) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneak = true;
        }
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (this.rotate.getValue()) {
            Managers.ROTATIONS.lookAtVec3dPacket(add);
        }
        SelfWeb.mc.playerController.processRightClickBlock(SelfWeb.mc.player, SelfWeb.mc.world, offset, getOpposite, add, EnumHand.MAIN_HAND);
        SelfWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    @Override
    public void onUpdate() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifeq            7
        //     6: return         
        //     7: aload_0        
        //     8: getfield        me/rebirthclient/mod/modules/impl/combat/SelfWeb.smart:Lme/rebirthclient/mod/modules/settings/Setting;
        //    11: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    14: checkcast       Ljava/lang/Boolean;
        //    17: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    20: ifeq            114
        //    23: aload_0        
        //    24: invokespecial   me/rebirthclient/mod/modules/impl/combat/SelfWeb.getClosestTarget:()Lnet/minecraft/entity/player/EntityPlayer;
        //    27: astore_1       
        //    28: aload_1        
        //    29: ifnonnull       33
        //    32: return         
        //    33: getstatic       me/rebirthclient/api/managers/Managers.FRIENDS:Lme/rebirthclient/api/managers/impl/FriendManager;
        //    36: aload_1        
        //    37: invokevirtual   net/minecraft/entity/player/EntityPlayer.getName:()Ljava/lang/String;
        //    40: invokevirtual   me/rebirthclient/api/managers/impl/FriendManager.isFriend:(Ljava/lang/String;)Z
        //    43: ifeq            47
        //    46: return         
        //    47: getstatic       me/rebirthclient/mod/modules/impl/combat/SelfWeb.mc:Lnet/minecraft/client/Minecraft;
        //    50: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    53: aload_1        
        //    54: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //    57: aload_0        
        //    58: getfield        me/rebirthclient/mod/modules/impl/combat/SelfWeb.enemyRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //    61: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/Integer;
        //    67: invokevirtual   java/lang/Integer.intValue:()I
        //    70: i2f            
        //    71: fcmpg          
        //    72: ifge            111
        //    75: aload_0        
        //    76: if_acmpeq       111
        //    79: getstatic       me/rebirthclient/mod/modules/impl/combat/SelfWeb.mc:Lnet/minecraft/client/Minecraft;
        //    82: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    85: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //    88: getfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //    91: istore_2       
        //    92: aload_0        
        //    93: getfield        me/rebirthclient/mod/modules/impl/combat/SelfWeb.newSlot:I
        //    96: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //    99: aload_0        
        //   100: aload_0        
        //   101: invokespecial   me/rebirthclient/mod/modules/impl/combat/SelfWeb.getFloorPos:()Lnet/minecraft/util/math/BlockPos;
        //   104: invokespecial   me/rebirthclient/mod/modules/impl/combat/SelfWeb.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   107: iload_2        
        //   108: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //   111: goto            150
        //   114: getstatic       me/rebirthclient/mod/modules/impl/combat/SelfWeb.mc:Lnet/minecraft/client/Minecraft;
        //   117: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   120: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //   123: getfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //   126: istore_1       
        //   127: aload_0        
        //   128: getfield        me/rebirthclient/mod/modules/impl/combat/SelfWeb.newSlot:I
        //   131: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //   134: aload_0        
        //   135: aload_0        
        //   136: invokespecial   me/rebirthclient/mod/modules/impl/combat/SelfWeb.getFloorPos:()Lnet/minecraft/util/math/BlockPos;
        //   139: invokespecial   me/rebirthclient/mod/modules/impl/combat/SelfWeb.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   142: iload_1        
        //   143: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //   146: aload_0        
        //   147: invokevirtual   me/rebirthclient/mod/modules/impl/combat/SelfWeb.disable:()V
        //   150: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public SelfWeb() {
        super("SelfWeb", "Places webs at your feet", Category.COMBAT);
        this.blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER);
        this.rotate = this.add(new Setting("Rotate", true));
        this.smart = this.add(new Setting("Smart", false).setParent());
        this.enemyRange = this.add(new Setting("EnemyRange", 4, 0, 8, this::lambda$new$0));
        this.newSlot = -1;
    }
    
    private int getHotbarItem() {
        int n = 0;
        if (n >= 9) {
            return -1;
        }
        if (SelfWeb.mc.player.inventory.getStackInSlot(n).getItem() == Item.getItemById(30)) {
            return n;
        }
        ++n;
        return 0;
    }
    
    private BlockPos getFloorPos() {
        return new BlockPos(Math.floor(SelfWeb.mc.player.posX), Math.floor(SelfWeb.mc.player.posY), Math.floor(SelfWeb.mc.player.posZ));
    }
}
