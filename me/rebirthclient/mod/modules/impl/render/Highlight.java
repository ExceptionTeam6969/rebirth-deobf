//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.render.*;

public class Highlight extends Module
{
    private final Setting color;
    AxisAlignedBB renderBB;
    private final Setting box;
    private final Setting depth;
    BlockPos lastPos;
    FadeUtils fade;
    private final Setting line;
    private final Setting lineWidth;
    private final Setting animationTime;
    
    public Highlight() {
        super("Highlight", "Highlights the block u look at", Category.RENDER);
        this.animationTime = this.add(new Setting("AnimationTime", 1000, 0, 6000));
        this.line = this.add(new Setting("Line", true));
        this.box = this.add(new Setting("Box", false));
        this.depth = this.add(new Setting("Depth", true));
        this.lineWidth = this.add(new Setting("LineWidth", 3.0f, 0.1f, 3.0f));
        this.color = this.add(new Setting("Color", new Color(125, 125, 213, 150)));
        this.fade = new FadeUtils((long)(int)this.animationTime.getValue());
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final RayTraceResult objectMouseOver = Highlight.mc.objectMouseOver;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos getBlockPos = objectMouseOver.getBlockPos();
            if (this.lastPos == null) {
                this.lastPos = getBlockPos;
                this.renderBB = Highlight.mc.world.getBlockState(getBlockPos).getSelectedBoundingBox((World)Highlight.mc.world, getBlockPos).grow(0.002);
            }
            if (!this.lastPos.equals((Object)objectMouseOver.getBlockPos())) {
                this.lastPos = objectMouseOver.getBlockPos();
                this.fade = new FadeUtils((long)(int)this.animationTime.getValue());
            }
            final AxisAlignedBB grow = Highlight.mc.world.getBlockState(getBlockPos).getSelectedBoundingBox((World)Highlight.mc.world, getBlockPos).grow(0.002);
            RenderUtil.testESP(this.renderBB = new AxisAlignedBB(this.renderBB.minX - (this.renderBB.minX - grow.minX) * this.fade.easeOutQuad(), this.renderBB.minY - (this.renderBB.minY - grow.minY) * this.fade.easeOutQuad(), this.renderBB.minZ - (this.renderBB.minZ - grow.minZ) * this.fade.easeOutQuad(), this.renderBB.maxX - (this.renderBB.maxX - grow.maxX) * this.fade.easeOutQuad(), this.renderBB.maxY - (this.renderBB.maxY - grow.maxY) * this.fade.easeOutQuad(), this.renderBB.maxZ - (this.renderBB.maxZ - grow.maxZ) * this.fade.easeOutQuad()), (Color)this.color.getValue(), false, new Color(-1), (float)this.lineWidth.getValue(), (boolean)this.line.getValue(), (boolean)this.box.getValue(), ((Color)this.color.getValue()).getAlpha(), (boolean)this.depth.getValue());
        }
    }
}
