//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import me.rebirthclient.api.util.*;

public class AntiBurrow extends Module
{
    public final Setting packet;
    private final Setting blockSetting;
    private final Setting onlyGround;
    public EntityPlayer target;
    private final Timer timer;
    private final Setting multiPlace;
    private final Setting range;
    public final Setting delay;
    public final Setting rotate;
    private int progress;
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    public AntiBurrow() {
        super("AntiBurrow", "put something under foot", Category.COMBAT);
        this.blockSetting = this.add(new Setting("Block", Block.RedStone));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.delay = this.add(new Setting("Delay", 50, 0, 500));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 4));
        this.timer = new Timer();
        this.onlyGround = this.add(new Setting("SelfGround", true));
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 6.0f));
        this.progress = 0;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (blockPos != 0) {
            return;
        }
        if (blockPos != 0) {
            return;
        }
        final int currentItem = AntiBurrow.mc.player.inventory.currentItem;
        if (this.blockSetting.getValue() == Block.Button && (InventoryUtil.findHotbarBlock(Blocks.STONE_BUTTON) != -1 || InventoryUtil.findHotbarBlock(Blocks.WOODEN_BUTTON) != -1)) {
            if (InventoryUtil.findHotbarBlock(Blocks.STONE_BUTTON) != -1) {
                InventoryUtil.doSwap(InventoryUtil.findHotbarBlock(Blocks.STONE_BUTTON));
            }
            else {
                InventoryUtil.doSwap(InventoryUtil.findHotbarBlock(Blocks.WOODEN_BUTTON));
            }
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        }
        else {
            if (this.blockSetting.getValue() != Block.RedStone || InventoryUtil.findItemInHotbar(Items.REDSTONE) == -1) {
                return;
            }
            InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.REDSTONE));
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        }
        ++this.progress;
        InventoryUtil.doSwap(currentItem);
        this.timer.reset();
    }
    
    @Override
    public void onTick() {
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        this.target = CombatUtil.getTarget((double)(float)this.range.getValue());
        if (this.target == null) {
            return;
        }
        if ((boolean)this.onlyGround.getValue() && !AntiBurrow.mc.player.onGround) {
            return;
        }
        this.progress = 0;
        this.placeBlock(new BlockPos(this.target.posX + 0.2, this.target.posY + 0.5, this.target.posZ + 0.2));
        this.placeBlock(new BlockPos(this.target.posX - 0.2, this.target.posY + 0.5, this.target.posZ + 0.2));
        this.placeBlock(new BlockPos(this.target.posX - 0.2, this.target.posY + 0.5, this.target.posZ - 0.2));
        this.placeBlock(new BlockPos(this.target.posX + 0.2, this.target.posY + 0.5, this.target.posZ - 0.2));
    }
    
    private enum Block
    {
        RedStone("RedStone", 1), 
        Button("Button", 0);
        
        private static final Block[] $VALUES;
        
        private Block(final String s, final int n) {
        }
        
        static {
            $VALUES = new Block[] { Block.Button, Block.RedStone };
        }
    }
}
