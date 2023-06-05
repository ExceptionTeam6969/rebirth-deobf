//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.*;

public class ExplosionSpawn extends Module
{
    public final Setting color;
    private final Setting time;
    private final Setting animationTime;
    public static final ArrayList spawnList;
    public final Setting minSize;
    public final Setting up;
    private final Setting extra;
    public final Setting size;
    public final Setting height;
    
    static {
        spawnList = new ArrayList();
    }
    
    public ExplosionSpawn() {
        super("ExplosionSpawn", "Draws a circle when a crystal spawn", Category.RENDER);
        this.color = this.add(new Setting("Color", new Color(-557395713, true)));
        this.size = this.add(new Setting("Max Size", 0.5f, 0.1f, 5.0f));
        this.minSize = this.add(new Setting("Min Size", 0.1, 0.0, 1.0));
        this.up = this.add(new Setting("Up", 0.1, 0.0, 1.0));
        this.height = this.add(new Setting("Height", 0.5, -1.0, 1.0));
        this.extra = this.add(new Setting("Extra", true));
        this.time = this.add(new Setting("Time", 500, 0, 5000));
        this.animationTime = this.add(new Setting("animationTime", 500, 0, 5000));
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (ExplosionSpawn.spawnList.size() != 0) {
            final Color color = (Color)this.color.getValue();
            final boolean b = true;
            final Iterator<Pos> iterator = ExplosionSpawn.spawnList.iterator();
            if (iterator.hasNext()) {
                final Pos pos = iterator.next();
                if (pos.time.easeOutQuad() >= 1.0) {
                    return;
                }
                RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())), color);
                if (this.extra.getValue()) {
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.01f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.015f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.02f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.025f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.03f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.035f, color);
                    RenderUtil.drawCircle((float)pos.pos.getX(), (float)(pos.pos.getY() + 1 + 1.0 * pos.firstFade.easeOutQuad() * (double)this.up.getValue() + (double)this.height.getValue()), (float)pos.pos.getZ(), (float)((pos.firstFade.easeOutQuad() > (double)this.minSize.getValue()) ? ((float)this.size.getValue() * pos.firstFade.easeOutQuad()) : ((float)this.size.getValue() * (double)this.minSize.getValue())) - 0.005f, color);
                }
            }
            else if (b) {
                ExplosionSpawn.spawnList.clear();
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            ExplosionSpawn.spawnList.add(new Pos(((CPacketPlayerTryUseItemOnBlock)send.getPacket()).getPos(), (int)this.animationTime.getValue(), (int)this.time.getValue()));
        }
    }
    
    @Override
    public void onEnable() {
        ExplosionSpawn.spawnList.clear();
    }
    
    private static class Pos
    {
        public final BlockPos pos;
        public final FadeUtils firstFade;
        public final FadeUtils time;
        
        public Pos(final BlockPos pos, final int n, final int n2) {
            this.firstFade = new FadeUtils((long)n);
            this.time = new FadeUtils((long)n2);
            this.pos = pos;
        }
    }
}
