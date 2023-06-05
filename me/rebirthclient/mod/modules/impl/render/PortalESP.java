//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import java.util.*;
import me.rebirthclient.api.util.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;

public class PortalESP extends Module
{
    private final Setting distance;
    private final Setting boxAlpha;
    private final Setting outline;
    private final Setting outlineAlpha;
    private final Setting updateDelay;
    private final Setting color;
    private final Setting box;
    private final ArrayList blockPosArrayList;
    private final Timer timer;
    
    public PortalESP() {
        super("PortalESP", "Draws portals", Category.RENDER);
        this.blockPosArrayList = new ArrayList();
        this.distance = this.add(new Setting("Distance", 60, 10, 100));
        this.updateDelay = this.add(new Setting("UpdateDelay", 2000, 500, 5000));
        this.box = this.add(new Setting("Box", false).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 125, 0, 255, this::lambda$new$0));
        this.outline = this.add(new Setting("Outline", true).setParent());
        this.outlineAlpha = this.add(new Setting("outlineAlpha", 150, 0, 255, this::lambda$new$1));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255, 100)).hideAlpha());
        this.timer = new Timer();
    }
    
    private boolean lambda$new$1(final Object o) {
        return this.outline.isOpen();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final Iterator<BlockPos> iterator = this.blockPosArrayList.iterator();
        if (iterator.hasNext()) {
            this.drawBlock(iterator.next());
        }
    }
    
    private boolean lambda$new$0(final Object o) {
        return this.box.isOpen();
    }
    
    private void updateBlocks() {
        final Iterator iterator = BlockUtil.getBox((float)(int)this.distance.getValue()).iterator();
        if (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            if (PortalESP.mc.world.getBlockState(blockPos).getBlock() instanceof BlockPortal || PortalESP.mc.world.getBlockState(blockPos).getBlock() instanceof BlockEndPortalFrame) {
                this.blockPosArrayList.add(blockPos);
            }
        }
    }
    
    @Override
    public void onTick() {
        if (this.timer.passedMs((long)(int)this.updateDelay.getValue())) {
            this.blockPosArrayList.clear();
            this.updateBlocks();
            this.timer.reset();
        }
    }
    
    private void drawBlock(final BlockPos blockPos) {
        final AxisAlignedBB getSelectedBoundingBox = PortalESP.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PortalESP.mc.world, blockPos);
        if (this.outline.getValue()) {
            RenderUtil.drawBBBox(getSelectedBoundingBox, (Color)this.color.getValue(), (int)this.outlineAlpha.getValue());
        }
        if (this.box.getValue()) {
            RenderUtil.drawBoxESP(blockPos, (Color)this.color.getValue(), (int)this.boxAlpha.getValue());
        }
    }
}
