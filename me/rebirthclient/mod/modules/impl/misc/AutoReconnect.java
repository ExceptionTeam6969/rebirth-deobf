//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.world.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.util.*;
import net.minecraft.client.multiplayer.*;
import me.rebirthclient.api.util.math.*;

public class AutoReconnect extends Module
{
    private final Setting delay;
    private static ServerData serverData;
    public static AutoReconnect INSTANCE;
    
    public void updateLastConnectedServer() {
        final ServerData getCurrentServerData = AutoReconnect.mc.getCurrentServerData();
        if (getCurrentServerData != null) {
            AutoReconnect.serverData = getCurrentServerData;
        }
    }
    
    static ServerData access$100() {
        return AutoReconnect.serverData;
    }
    
    @SubscribeEvent
    public void sendPacket(final GuiOpenEvent guiOpenEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (guiOpenEvent.getGui() instanceof GuiDisconnected) {
            this.updateLastConnectedServer();
            guiOpenEvent.setGui((GuiScreen)new GuiDisconnectedHook((GuiDisconnected)guiOpenEvent.getGui()));
        }
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload unload) {
        if (fullNullCheck()) {
            return;
        }
        this.updateLastConnectedServer();
    }
    
    static Setting access$000(final AutoReconnect autoReconnect) {
        return autoReconnect.delay;
    }
    
    public AutoReconnect() {
        super("AutoReconnect", "Reconnects you if you disconnect", Category.MISC);
        this.delay = this.add(new Setting("Delay", 5));
        AutoReconnect.INSTANCE = this;
    }
    
    private class GuiDisconnectedHook extends GuiDisconnected
    {
        final AutoReconnect this$0;
        private final Timer timer;
        
        public GuiDisconnectedHook(final AutoReconnect this$0, final GuiDisconnected guiDisconnected) {
            this.this$0 = this$0;
            super(guiDisconnected.parentScreen, guiDisconnected.reason, guiDisconnected.message);
            (this.timer = new Timer()).reset();
        }
        
        public void updateScreen() {
            if (this.timer.passedS((double)(int)AutoReconnect.access$000(this.this$0).getValue())) {
                this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this.parentScreen, this.mc, (AutoReconnect.access$100() == null) ? this.mc.currentServerData : AutoReconnect.access$100()));
            }
        }
        
        public void drawScreen(final int n, final int n2, final float n3) {
            super.drawScreen(n, n2, n3);
            final String string = "Reconnecting in " + MathUtil.round(((int)AutoReconnect.access$000(this.this$0).getValue() * 1000 - this.timer.getPassedTimeMs()) / 1000.0, 1);
            this.mc.fontRenderer.drawString(string, this.width / 2.0f - this.mc.fontRenderer.getStringWidth(string) / 2.0f, (float)(this.height - 16), 16777215, true);
        }
    }
}
