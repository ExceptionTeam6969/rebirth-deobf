//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.client.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AutoKit extends Module
{
    public final Setting Name;
    boolean needKit;
    
    public AutoKit() {
        super("AutoKit", "Auto select kit", Category.MISC);
        this.Name = this.add(new Setting("KitName", "1"));
        this.needKit = false;
    }
    
    @Override
    public void onUpdate() {
        if (AutoKit.mc.currentScreen instanceof GuiGameOver) {
            this.needKit = true;
        }
        else if (this.needKit) {
            AutoKit.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/kit " + (String)this.Name.getValue()));
            this.needKit = false;
        }
    }
}
