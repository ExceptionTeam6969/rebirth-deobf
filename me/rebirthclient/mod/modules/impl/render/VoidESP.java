//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;

public class VoidESP extends Module
{
    private final Setting rangeY;
    private final Setting line;
    private final Setting color;
    private final Setting rangeX;
    private final Setting mode;
    private final Setting height;
    private final Setting fill;
    static final boolean $assertionsDisabled;
    private final Setting wireframe;
    
    static {
        $assertionsDisabled = !VoidESP.class.desiredAssertionStatus();
    }
    
    public VoidESP() {
        super("VoidESP", "Highlights void blocks", Category.RENDER);
        this.rangeX = this.add(new Setting("RangeX", 10, 0, 25));
        this.rangeY = this.add(new Setting("RangeY", 5, 0, 25));
        this.mode = this.add(new Setting("Mode", Mode.FULL));
        this.height = this.add(new Setting("Height", 1, 0, 4, this::lambda$new$0));
        this.fill = this.add(new Setting("Fill", true));
        this.line = this.add(new Setting("Outline", true));
        this.wireframe = this.add(new Setting("Wireframe", true));
        this.color = this.add(new Setting("Color", new Color(1692929536, true)));
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!fullNullCheck()) {
            assert VoidESP.mc.renderViewEntity != null;
            final Vec3i vec3i = new Vec3i(VoidESP.mc.renderViewEntity.posX, VoidESP.mc.renderViewEntity.posY, VoidESP.mc.renderViewEntity.posZ);
            int n = vec3i.getX() - (int)this.rangeX.getValue();
            if (n < vec3i.getX() + (int)this.rangeX.getValue()) {
                int n2 = vec3i.getZ() - (int)this.rangeX.getValue();
                if (n2 >= vec3i.getZ() + (int)this.rangeX.getValue()) {
                    ++n;
                    return;
                }
                int n3 = vec3i.getY() + (int)this.rangeY.getValue();
                if (n3 > vec3i.getY() - (int)this.rangeY.getValue()) {
                    final BlockPos blockPos = new BlockPos(n, n3, n2);
                    double n4 = 0.0;
                    if (this.mode.getValue() == Mode.FLAT) {
                        n4 = -1.0;
                    }
                    else if (this.mode.getValue() == Mode.SLAB) {
                        n4 = -0.8;
                    }
                    if (blockPos != 0) {
                        if (this.mode.getValue() == Mode.FULL) {
                            if ((int)this.height.getValue() == 1 || blockPos.up() != 0) {
                                this.drawVoidESP(blockPos, (Color)this.color.getValue(), new Color(-1), (boolean)this.line.getValue(), (boolean)this.fill.getValue(), ((Color)this.color.getValue()).getAlpha(), 0.0, (boolean)this.wireframe.getValue());
                            }
                            else if ((int)this.height.getValue() == 2 && blockPos.up() != 0) {
                                this.drawVoidESP(blockPos, (Color)this.color.getValue(), new Color(-1), (boolean)this.line.getValue(), (boolean)this.fill.getValue(), ((Color)this.color.getValue()).getAlpha(), 1.0, (boolean)this.wireframe.getValue());
                            }
                            else if ((int)this.height.getValue() == 3 && blockPos.up() != 0 && blockPos.up().up() != 0) {
                                this.drawVoidESP(blockPos, (Color)this.color.getValue(), new Color(-1), (boolean)this.line.getValue(), (boolean)this.fill.getValue(), ((Color)this.color.getValue()).getAlpha(), 2.0, (boolean)this.wireframe.getValue());
                            }
                            else if ((int)this.height.getValue() == 4 && blockPos.up() != 0 && blockPos.up().up() != 0 && blockPos.up().up().up() != 0) {
                                this.drawVoidESP(blockPos, (Color)this.color.getValue(), new Color(-1), (boolean)this.line.getValue(), (boolean)this.fill.getValue(), ((Color)this.color.getValue()).getAlpha(), 3.0, (boolean)this.wireframe.getValue());
                            }
                        }
                        else {
                            this.drawVoidESP(blockPos, (Color)this.color.getValue(), new Color(-1), (boolean)this.line.getValue(), (boolean)this.fill.getValue(), ((Color)this.color.getValue()).getAlpha(), n4, (boolean)this.wireframe.getValue());
                        }
                    }
                    --n3;
                    return;
                }
                ++n2;
            }
        }
    }
    
    @Override
    public String getInfo() {
        return Managers.TEXT.normalizeCases(this.mode.getValue());
    }
    
    private void drawVoidESP(final BlockPos blockPos, final Color color, final Color color2, final boolean b, final boolean b2, final int n, final double n2, final boolean b3) {
        if (b2) {
            RenderUtil.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n), n2, false, false, 0);
        }
        if (b) {
            RenderUtil.drawBlockOutline(blockPos, color, 0.8f, true, n2, false, false, 0, false);
        }
        if (b3) {
            RenderUtil.drawBlockWireframe(blockPos, color, 0.8f, n2, true);
        }
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.mode.getValue() == Mode.FULL;
    }
    
    private enum Mode
    {
        private static final Mode[] $VALUES;
        
        FLAT("FLAT", 0), 
        SLAB("SLAB", 1), 
        FULL("FULL", 2);
        
        static {
            $VALUES = new Mode[] { Mode.FLAT, Mode.SLAB, Mode.FULL };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
