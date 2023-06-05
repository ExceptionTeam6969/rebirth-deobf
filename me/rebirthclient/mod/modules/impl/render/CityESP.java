//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.block.state.*;

public class CityESP extends Module
{
    private final Setting onlyBurrow;
    private final Setting outline;
    private final Setting box;
    private final Setting boxAlpha;
    private final Setting canAttackColor;
    private final Setting burrowColor;
    private final List burrowPos;
    private final Setting range;
    private final Setting breakColor;
    private final Setting outlineAlpha;
    
    private void drawBurrow(final BlockPos blockPos) {
        if (this.burrowPos.contains(blockPos)) {
            return;
        }
        this.burrowPos.add(blockPos);
        if (this.getBlock(blockPos).getBlock() != Blocks.AIR && this.getBlock(blockPos).getBlock() != Blocks.BEDROCK) {
            final AxisAlignedBB getSelectedBoundingBox = CityESP.mc.world.getBlockState(new BlockPos((Vec3i)blockPos)).getSelectedBoundingBox((World)CityESP.mc.world, new BlockPos((Vec3i)blockPos));
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(getSelectedBoundingBox, (Color)this.burrowColor.getValue(), (int)this.boxAlpha.getValue());
            }
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(getSelectedBoundingBox, (Color)this.burrowColor.getValue(), (int)this.outlineAlpha.getValue());
            }
        }
    }
    
    private void doRender(final EntityPlayer entityPlayer) {
        final BlockPos entityPos = EntityUtil.getEntityPos((Entity)entityPlayer);
        this.drawBurrow(new BlockPos(entityPlayer.posX + 0.1, entityPlayer.posY + 0.5, entityPlayer.posZ + 0.1));
        this.drawBurrow(new BlockPos(entityPlayer.posX - 0.1, entityPlayer.posY + 0.5, entityPlayer.posZ + 0.1));
        this.drawBurrow(new BlockPos(entityPlayer.posX + 0.1, entityPlayer.posY + 0.5, entityPlayer.posZ - 0.1));
        this.drawBurrow(new BlockPos(entityPlayer.posX - 0.1, entityPlayer.posY + 0.5, entityPlayer.posZ - 0.1));
        if (this.onlyBurrow.getValue()) {
            return;
        }
        if (this.getBlock(entityPos.add(-1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
            if (this.getBlock(entityPos.add(-2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() == Blocks.AIR) {
                this.drawBlock(entityPos, -1.0, 0.0, 0.0, true);
            }
            else if (this.getBlock(entityPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.drawBlock(entityPos, -1.0, 0.0, 0.0, false);
            }
        }
        if (this.getBlock(entityPos.add(1, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
            if (this.getBlock(entityPos.add(2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(2, 1, 0)).getBlock() == Blocks.AIR) {
                this.drawBlock(entityPos, 1.0, 0.0, 0.0, true);
            }
            else if (this.getBlock(entityPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(2, 1, 0)).getBlock() != Blocks.BEDROCK) {
                this.drawBlock(entityPos, 1.0, 0.0, 0.0, false);
            }
        }
        if (this.getBlock(entityPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(-2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, -2.0, 0.0, 0.0, this.getBlock(entityPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(-2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(-2, 1, 0)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, -2.0, 1.0, 0.0, this.getBlock(entityPos.add(-1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(-2, 0, 0)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(2, 1, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(2, 0, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 2.0, 0.0, 0.0, this.getBlock(entityPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(2, 1, 0)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(2, 0, 0)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(2, 1, 0)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(2, 1, 0)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 2.0, 1.0, 0.0, this.getBlock(entityPos.add(1, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(2, 0, 0)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(0, 0, 1)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
            if (this.getBlock(entityPos.add(0, 0, 2)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 1, 2)).getBlock() == Blocks.AIR) {
                this.drawBlock(entityPos, 0.0, 0.0, 1.0, true);
            }
            else if (this.getBlock(entityPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, 2)).getBlock() != Blocks.BEDROCK) {
                this.drawBlock(entityPos, 0.0, 0.0, 1.0, false);
            }
        }
        if (this.getBlock(entityPos.add(0, 0, -1)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
            if (this.getBlock(entityPos.add(0, 0, -2)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 1, -2)).getBlock() == Blocks.AIR) {
                this.drawBlock(entityPos, 0.0, 0.0, -1.0, true);
            }
            else if (this.getBlock(entityPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, -2)).getBlock() != Blocks.BEDROCK) {
                this.drawBlock(entityPos, 0.0, 0.0, -1.0, false);
            }
        }
        if (this.getBlock(entityPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, 2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 0, 2)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 0.0, 0.0, 2.0, this.getBlock(entityPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 1, 2)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 0, 2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, 2)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 1, 2)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 0.0, 1.0, 2.0, this.getBlock(entityPos.add(0, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 0, 2)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, -2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 0, -2)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 0.0, 0.0, -2.0, this.getBlock(entityPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 1, -2)).getBlock() == Blocks.AIR);
        }
        if (this.getBlock(entityPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 0, -2)).getBlock() != Blocks.BEDROCK && this.getBlock(entityPos.add(0, 1, -2)).getBlock() != Blocks.AIR && this.getBlock(entityPos.add(0, 1, -2)).getBlock() != Blocks.BEDROCK) {
            this.drawBlock(entityPos, 0.0, 1.0, -2.0, this.getBlock(entityPos.add(0, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(entityPos.add(0, 0, -2)).getBlock() == Blocks.AIR);
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.burrowPos.clear();
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)CityESP.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (EntityUtil.invalid((Entity)entityPlayer, (double)(float)this.range.getValue())) {
            return;
        }
        this.doRender(entityPlayer);
    }
    
    private void drawBlock(BlockPos add, final double n, final double n2, final double n3, final boolean b) {
        add = add.add(n, n2, n3);
        if (CityESP.mc.world.getBlockState(add).getBlock() == Blocks.AIR) {
            return;
        }
        if (CityESP.mc.world.getBlockState(add).getBlock() == Blocks.FIRE) {
            return;
        }
        final AxisAlignedBB getSelectedBoundingBox = CityESP.mc.world.getBlockState(add).getSelectedBoundingBox((World)CityESP.mc.world, add);
        if (b) {
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(getSelectedBoundingBox, (Color)this.canAttackColor.getValue(), (int)this.outlineAlpha.getValue());
            }
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(getSelectedBoundingBox, (Color)this.canAttackColor.getValue(), (int)this.boxAlpha.getValue());
            }
            return;
        }
        if (this.outline.getValue()) {
            RenderUtil.drawBBBox(getSelectedBoundingBox, (Color)this.breakColor.getValue(), (int)this.outlineAlpha.getValue());
        }
        if (this.box.getValue()) {
            RenderUtil.drawBBFill(getSelectedBoundingBox, (Color)this.breakColor.getValue(), (int)this.boxAlpha.getValue());
        }
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.box.isOpen();
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.outline.isOpen();
    }
    
    public CityESP() {
        super("CityESP", "CityESP", Category.RENDER);
        this.onlyBurrow = this.add(new Setting("OnlyBurrow", false));
        this.outline = this.add(new Setting("Outline", true).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 150, 0, 255, this::lambda$new$0));
        this.box = this.add(new Setting("Box", true).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 70, 0, 255, this::lambda$new$1));
        this.range = this.add(new Setting("Range", 7.0f, 1.0f, 12.0f));
        this.canAttackColor = this.add(new Setting("AttackColor", new Color(255, 147, 147)).hideAlpha());
        this.breakColor = this.add(new Setting("Color", new Color(118, 118, 255)).hideAlpha());
        this.burrowColor = this.add(new Setting("BurrowColor", new Color(255, 255, 255)).hideAlpha());
        this.burrowPos = new ArrayList();
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return CityESP.mc.world.getBlockState(blockPos);
    }
}
