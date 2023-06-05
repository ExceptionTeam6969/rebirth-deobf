//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.world.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Ambience extends Module
{
    public final Setting lightMap;
    public static Ambience INSTANCE;
    public final Setting fogNether;
    public final Setting sky;
    public final Setting skyNether;
    public final Setting fog;
    private final Setting time;
    public final Setting noFog;
    public final Setting customTime;
    
    @SubscribeEvent
    public void setFogColor(final RenderFogColorEvent renderFogColorEvent) {
        if (this.fog.booleanValue && Ambience.mc.player.dimension == 0) {
            renderFogColorEvent.setColor((Color)this.fog.getValue());
            renderFogColorEvent.setCanceled(true);
        }
        else if (this.fogNether.booleanValue && Ambience.mc.player.dimension == -1) {
            renderFogColorEvent.setColor((Color)this.fogNether.getValue());
            renderFogColorEvent.setCanceled(true);
        }
    }
    
    public Ambience() {
        super("Ambience", "Custom ambience", Category.RENDER);
        this.customTime = this.add(new Setting("CustomTime", false).setParent());
        this.time = this.add(new Setting("Time", 0, 0, 24000, this::lambda$new$0));
        this.noFog = this.add(new Setting("NoFog", false));
        this.lightMap = this.add(new Setting("LightMap", new Color(-557395713, true)).injectBoolean(false).hideAlpha());
        this.sky = this.add(new Setting("OverWorldSky", new Color(8224213)).injectBoolean(true).hideAlpha());
        this.skyNether = this.add(new Setting("NetherSky", new Color(8224213)).injectBoolean(true).hideAlpha());
        this.fog = this.add(new Setting("OverWorldFog", new Color(13401557)).injectBoolean(false).hideAlpha());
        this.fogNether = this.add(new Setting("NetherFog", new Color(13401557)).injectBoolean(false).hideAlpha());
        Ambience.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void setFogDensity(final EntityViewRenderEvent.FogDensity fogDensity) {
        if (this.noFog.getValue()) {
            fogDensity.setDensity(0.0f);
            fogDensity.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void init(final WorldEvent worldEvent) {
        if (this.customTime.getValue()) {
            worldEvent.getWorld().setWorldTime((long)(int)this.time.getValue());
        }
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.customTime.isOpen();
    }
    
    @SubscribeEvent
    public void setSkyColor(final RenderSkyEvent renderSkyEvent) {
        if (this.sky.booleanValue && Ambience.mc.player.dimension == 0) {
            renderSkyEvent.setColor((Color)this.sky.getValue());
            renderSkyEvent.setCanceled(true);
        }
        else if (this.skyNether.booleanValue && Ambience.mc.player.dimension == -1) {
            renderSkyEvent.setColor((Color)this.skyNether.getValue());
            renderSkyEvent.setCanceled(true);
        }
    }
    
    public Color getColor() {
        return new Color(((Color)this.lightMap.getValue()).getRed(), ((Color)this.lightMap.getValue()).getGreen(), ((Color)this.lightMap.getValue()).getBlue(), ((Color)this.lightMap.getValue()).getAlpha());
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketTimeUpdate && (boolean)this.customTime.getValue()) {
            receive.setCanceled(true);
        }
    }
}
