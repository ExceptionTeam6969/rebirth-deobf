//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.events.impl.*;
import java.util.*;
import me.rebirthclient.api.util.*;

public class PlaceRender extends Module
{
    public final Setting color;
    private final Setting animationTime;
    public static PlaceRender INSTANCE;
    public static HashMap PlaceMap;
    private final Setting sync;
    private final Setting outline;
    private final Setting box;
    
    public PlaceRender() {
        super("PlaceRender", "test", Category.RENDER);
        this.box = this.add(new Setting("Box", true));
        this.outline = this.add(new Setting("Outline", false));
        this.animationTime = this.add(new Setting("animationTime", 1000, 0, 5000));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255, 100)));
        this.sync = this.add(new Setting("Sync", true));
        PlaceRender.INSTANCE = this;
    }
    
    static Setting access$000(final PlaceRender placeRender) {
        return placeRender.animationTime;
    }
    
    static {
        PlaceRender.PlaceMap = new HashMap();
    }
    
    private void drawBlock(final BlockPos blockPos, final double n, Color color) {
        if (this.sync.getValue()) {
            color = (Color)this.color.getValue();
        }
        final AxisAlignedBB getSelectedBoundingBox = PlaceRender.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)PlaceRender.mc.world, blockPos);
        if (this.outline.getValue()) {
            RenderUtil.drawBBBox(getSelectedBoundingBox, color, (int)(color.getAlpha() * -n));
        }
        if (this.box.getValue()) {
            RenderUtil.drawBoxESP(blockPos, color, (int)(color.getAlpha() * -n));
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final boolean b = true;
        final Iterator<placePosition> iterator = PlaceRender.PlaceMap.values().iterator();
        if (!iterator.hasNext()) {
            if (b) {
                PlaceRender.PlaceMap.clear();
            }
            return;
        }
        final placePosition placePosition = iterator.next();
        if (placePosition.firstFade.easeOutQuad() == 1.0) {
            return;
        }
        this.drawBlock(placePosition.pos, placePosition.firstFade.easeOutQuad() - 1.0, placePosition.posColor);
    }
    
    public static class placePosition
    {
        public Color posColor;
        public BlockPos pos;
        public final FadeUtils firstFade;
        
        public placePosition(final BlockPos pos) {
            this.firstFade = new FadeUtils((long)(int)PlaceRender.access$000(PlaceRender.INSTANCE).getValue());
            this.pos = pos;
            this.posColor = (Color)PlaceRender.INSTANCE.color.getValue();
        }
    }
}
