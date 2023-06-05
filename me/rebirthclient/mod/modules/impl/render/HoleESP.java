//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.*;

public class HoleESP extends Module
{
    private final Setting range;
    private final Setting fov;
    private final Setting box;
    private final Setting customOutline;
    private final Setting renderOwn;
    private final Setting lineWidth;
    static final boolean $assertionsDisabled;
    private final Setting brockLineColor;
    private final Setting page;
    private final Setting invertGradientBox;
    private final Setting obbyColor;
    private final Setting lineHeight;
    private final Setting outline;
    private final Setting brockColor;
    private final Setting wireframeMode;
    private final Setting boxAlpha;
    private final Setting obbyLineColor;
    private final Setting separateHeight;
    private final Setting gradientBox;
    private final Setting invertGradientOutline;
    private final Setting wireframe;
    private final Setting height;
    private final Setting gradientOutline;
    
    private boolean lambda$new$14(final Float n) {
        return ((boolean)this.outline.getValue() || (boolean)this.wireframe.getValue()) && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$18(final Boolean b) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$5(final Boolean b) {
        return this.box.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$6(final Integer n) {
        return (boolean)this.box.getValue() && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$19(final Color color) {
        return this.customOutline.isOpen() && this.page.getValue() == Page.COLORS;
    }
    
    public void drawHoleESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4, final double n3, final double n4, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final int n5, final boolean b9, final boolean b10) {
        if (b3) {
            RenderUtil.drawBox(blockPos, ColorUtil.injectAlpha(color, n2), n3, b5, b7, n5);
        }
        if (b2) {
            RenderUtil.drawBlockOutline(blockPos, b ? color2 : color, n, b4, n4, b6, b8, n5, false);
        }
        if (b9) {
            RenderUtil.drawBlockWireframe(blockPos, b ? color2 : color, n, n3, b10);
        }
    }
    
    private boolean lambda$new$7(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$20(final Color color) {
        return this.customOutline.isOpen() && this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    public void drawDoubles(final boolean b, final BlockPos blockPos, final Color color, final boolean b2, final Color color2, final float n, final boolean b3, final boolean b4, final int n2, final boolean b5, final double n3, final double n4, final boolean b6, final boolean b7, final boolean b8, final boolean b9, final int n5, final boolean b10, final boolean b11) {
        this.drawHoleESP(blockPos, color, b2, color2, n, b3, b4, n2, b5, n3, n4, b6, b7, b8, b9, n5, b10, b11);
        this.drawHoleESP(b ? blockPos.north() : blockPos.east(), color, b2, color2, n, b3, b4, n2, b5, n3, n4, b6, b7, b8, b9, n5, b10, b11);
    }
    
    public HoleESP() {
        super("HoleESP", "Shows safe spots near you", Category.RENDER);
        this.page = this.add(new Setting("Settings", Page.GLOBAL));
        this.renderOwn = this.add(new Setting("RenderOwn", true, this::lambda$new$0));
        this.fov = this.add(new Setting("FovOnly", true, this::lambda$new$1));
        this.range = this.add(new Setting("Range", 5, 0, 25, this::lambda$new$2));
        this.box = this.add(new Setting("Box", true, this::lambda$new$3).setParent());
        this.gradientBox = this.add(new Setting("FadeBox", false, this::lambda$new$4));
        this.invertGradientBox = this.add(new Setting("InvertBoxFade", false, this::lambda$new$5));
        this.boxAlpha = this.add(new Setting("BoxAlpha", 80, 0, 255, this::lambda$new$6));
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$7).setParent());
        this.gradientOutline = this.add(new Setting("FadeLine", false, this::lambda$new$8));
        this.invertGradientOutline = this.add(new Setting("InvertLineFade", false, this::lambda$new$9));
        this.separateHeight = this.add(new Setting("SeparateHeight", false, this::lambda$new$10));
        this.lineHeight = this.add(new Setting("LineHeight", -1.1, -2.0, 2.0, this::lambda$new$11));
        this.wireframe = this.add(new Setting("Wireframe", true, this::lambda$new$12).setParent());
        this.wireframeMode = this.add(new Setting("Mode", WireframeMode.FULL, this::lambda$new$13));
        this.lineWidth = this.add(new Setting("LineWidth", 0.5f, 0.1f, 5.0f, this::lambda$new$14));
        this.height = this.add(new Setting("Height", -1.1, -2.0, 2.0, this::lambda$new$15));
        this.obbyColor = this.add(new Setting("Obby", new Color(12721437), this::lambda$new$16));
        this.brockColor = this.add(new Setting("Bedrock", new Color(2595403), this::lambda$new$17));
        this.customOutline = this.add(new Setting("LineColor", false, this::lambda$new$18).setParent());
        this.obbyLineColor = this.add(new Setting("ObbyLine", new Color(-1), this::lambda$new$19));
        this.brockLineColor = this.add(new Setting("BedrockLine", new Color(-1), this::lambda$new$20));
    }
    
    private boolean lambda$new$15(final Double n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return this.outline.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        assert HoleESP.mc.renderViewEntity != null;
        final Vec3i vec3i = new Vec3i(HoleESP.mc.renderViewEntity.posX, HoleESP.mc.renderViewEntity.posY, HoleESP.mc.renderViewEntity.posZ);
        int n = vec3i.getX() - (int)this.range.getValue();
        if (n >= vec3i.getX() + (int)this.range.getValue()) {
            return;
        }
        int n2 = vec3i.getZ() - (int)this.range.getValue();
        if (n2 >= vec3i.getZ() + (int)this.range.getValue()) {
            ++n;
            return;
        }
        final int n3 = 5;
        int n4 = vec3i.getY() + n3;
        if (n4 > vec3i.getY() - n3) {
            final BlockPos blockPos = new BlockPos(n, n4, n2);
            final Color color = (Color)this.brockColor.getValue();
            final Color color2 = (Color)this.obbyColor.getValue();
            final Color color3 = (Color)this.brockLineColor.getValue();
            final Color color4 = (Color)this.obbyLineColor.getValue();
            if (HoleESP.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) && (!blockPos.equals((Object)EntityUtil.getPlayerPos()) || (boolean)this.renderOwn.getValue())) {
                if (Managers.ROTATIONS.isInFov(blockPos) || !(boolean)this.fov.getValue()) {
                    if (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.north().up()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.north().down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.north(2)).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.north().east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.north().west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
                        this.drawDoubles(true, blockPos, color, (boolean)this.customOutline.getValue(), color3, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                    }
                    else if (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.north().up()).getBlock() == Blocks.AIR && (HoleESP.mc.world.getBlockState(blockPos.north().down()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.north().down()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.north(2)).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.north(2)).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.north().east()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.north().east()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.north().west()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.north().west()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK) && (HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.OBSIDIAN || HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK)) {
                        this.drawDoubles(true, blockPos, color2, (boolean)this.customOutline.getValue(), color4, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                    }
                    if (HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.east().up()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.east().down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east(2)).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east(2).down()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east().north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east().south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
                        this.drawDoubles(false, blockPos, color, (boolean)this.customOutline.getValue(), color3, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                    }
                    else if (HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.AIR && HoleESP.mc.world.getBlockState(blockPos.east().up()).getBlock() == Blocks.AIR && (HoleESP.mc.world.getBlockState(blockPos.east().down()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.east().down()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.east(2)).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.east(2)).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.east().north()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.east().north()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.east().south()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.east().south()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.OBSIDIAN) && (HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK || HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.OBSIDIAN)) {
                        this.drawDoubles(false, blockPos, color2, (boolean)this.customOutline.getValue(), color4, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                    }
                    if (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
                        this.drawHoleESP(blockPos, color, (boolean)this.customOutline.getValue(), color3, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                    }
                    else if (!BlockUtil.isSafe(HoleESP.mc.world.getBlockState(blockPos.down()).getBlock()) && !BlockUtil.isSafe(HoleESP.mc.world.getBlockState(blockPos.east()).getBlock()) && !BlockUtil.isSafe(HoleESP.mc.world.getBlockState(blockPos.west()).getBlock()) && !BlockUtil.isSafe(HoleESP.mc.world.getBlockState(blockPos.south()).getBlock())) {
                        if (!BlockUtil.isSafe(HoleESP.mc.world.getBlockState(blockPos.north()).getBlock())) {
                            this.drawHoleESP(blockPos, color2, (boolean)this.customOutline.getValue(), color4, (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), true, (double)this.height.getValue(), ((boolean)this.separateHeight.getValue()) ? ((double)this.lineHeight.getValue()) : ((double)this.height.getValue()), (boolean)this.gradientBox.getValue(), (boolean)this.gradientOutline.getValue(), (boolean)this.invertGradientBox.getValue(), (boolean)this.invertGradientOutline.getValue(), 0, (boolean)this.wireframe.getValue(), this.wireframeMode.getValue() == WireframeMode.FLAT);
                        }
                    }
                }
            }
            --n4;
            return;
        }
        ++n2;
    }
    
    private boolean lambda$new$12(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$17(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$16(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$8(final Boolean b) {
        return this.outline.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    static {
        $assertionsDisabled = !HoleESP.class.desiredAssertionStatus();
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$10(final Boolean b) {
        return this.outline.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$13(final WireframeMode wireframeMode) {
        return this.wireframe.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$11(final Double n) {
        return this.outline.isOpen() && this.page.getValue() == Page.GLOBAL && (boolean)this.separateHeight.getValue();
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.box.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    private enum WireframeMode
    {
        private static final WireframeMode[] $VALUES;
        
        FULL("FULL", 1), 
        FLAT("FLAT", 0);
        
        static {
            $VALUES = new WireframeMode[] { WireframeMode.FLAT, WireframeMode.FULL };
        }
        
        private WireframeMode(final String s, final int n) {
        }
    }
    
    private enum Page
    {
        COLORS("COLORS", 0), 
        GLOBAL("GLOBAL", 1);
        
        private static final Page[] $VALUES;
        
        private Page(final String s, final int n) {
        }
        
        static {
            $VALUES = new Page[] { Page.COLORS, Page.GLOBAL };
        }
    }
}
