//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import java.text.*;
import me.rebirthclient.mod.modules.impl.client.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.rebirthclient.api.util.*;

public class ServerManager extends Mod
{
    private String serverBrand;
    private final Timer timer;
    private final float[] tpsCounts;
    private long lastUpdate;
    private float TPS;
    private final DecimalFormat format;
    
    public boolean isServerNotResponding() {
        return this.timer.passedMs((int)HUD.INSTANCE.lagTime.getValue());
    }
    
    public void setServerBrand(final String serverBrand) {
        this.serverBrand = serverBrand;
    }
    
    public void reset() {
        Arrays.fill(this.tpsCounts, 20.0f);
        this.TPS = 20.0f;
    }
    
    public long serverRespondingTime() {
        return this.timer.getPassedTimeMs();
    }
    
    public ServerManager() {
        this.tpsCounts = new float[10];
        this.format = new DecimalFormat("##.00#");
        this.timer = new Timer();
        this.TPS = 20.0f;
        this.lastUpdate = -1L;
        this.serverBrand = "";
    }
    
    public int getPing() {
        if (fullNullCheck()) {
            return 0;
        }
        try {
            return Objects.requireNonNull(ServerManager.mc.getConnection()).getPlayerInfo(Wrapper.mc.getConnection().getGameProfile().getId()).getResponseTime();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public void onPacketReceived() {
        this.timer.reset();
    }
    
    public float getTpsFactor() {
        return 20.0f / this.TPS;
    }
    
    public String getServerBrand() {
        return this.serverBrand;
    }
    
    public void update() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.lastUpdate == -1L) {
            this.lastUpdate = currentTimeMillis;
            return;
        }
        float n = (currentTimeMillis - this.lastUpdate) / 20.0f;
        if (n == 0.0f) {
            n = 50.0f;
        }
        float n2;
        if ((n2 = 1000.0f / n) > 20.0f) {
            n2 = 20.0f;
        }
        System.arraycopy(this.tpsCounts, 0, this.tpsCounts, 1, this.tpsCounts.length - 1);
        this.tpsCounts[0] = n2;
        final double n3 = 0.0;
        final float[] tpsCounts = this.tpsCounts;
        final int length = tpsCounts.length;
        int n4 = 0;
        if (n4 < length) {
            final double n5 = n3 + tpsCounts[n4];
            ++n4;
            return;
        }
        double n6;
        if ((n6 = n3 / this.tpsCounts.length) > 20.0) {
            n6 = 20.0;
        }
        this.TPS = Float.parseFloat(this.format.format(n6));
        this.lastUpdate = currentTimeMillis;
    }
    
    public float getTPS() {
        return this.TPS;
    }
}
