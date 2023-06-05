//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.chunk.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.mod.modules.*;

public class FakeFPS extends Module
{
    int lastFps;
    public final Setting times;
    public static FakeFPS INSTANCE;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        Minecraft.getMinecraft().debug = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", Minecraft.getDebugFPS(), RenderChunk.renderChunksUpdated, (RenderChunk.renderChunksUpdated == 1) ? "" : "s", (Minecraft.getMinecraft().gameSettings.limitFramerate == GameSettings.Options.FRAMERATE_LIMIT.getValueMax()) ? "inf" : Integer.valueOf(Minecraft.getMinecraft().gameSettings.limitFramerate), Minecraft.getMinecraft().gameSettings.enableVsync ? " vsync" : "", Minecraft.getMinecraft().gameSettings.fancyGraphics ? "" : " fast", (Minecraft.getMinecraft().gameSettings.clouds == 0) ? "" : ((Minecraft.getMinecraft().gameSettings.clouds == 1) ? " fast-clouds" : " fancy-clouds"), OpenGlHelper.useVbo() ? " vbo" : "");
        if (Minecraft.getDebugFPS() == this.lastFps) {
            return;
        }
        Minecraft.debugFPS *= (int)this.times.getValue();
        this.lastFps = Minecraft.getDebugFPS();
    }
    
    public FakeFPS() {
        super("FakeFPS", "FakeFPS", Category.CLIENT);
        this.times = this.add(new Setting("times", 5, 1, 100));
        this.lastFps = 0;
        FakeFPS.INSTANCE = this;
    }
}
