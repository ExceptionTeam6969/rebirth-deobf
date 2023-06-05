//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import org.apache.commons.lang3.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.*;

public class Message extends Module
{
    private final Setting random;
    private final Setting delay;
    private final Timer timer;
    private final Setting custom;
    
    @Override
    public void onTick() {
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        Message.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)this.custom.getValue() + " " + RandomStringUtils.randomAlphanumeric((int)this.random.getValue())));
        this.timer.reset();
    }
    
    public Message() {
        super("Spammer", "Message", Category.MISC);
        this.timer = new Timer();
        this.custom = this.add(new Setting("Custom", "Rebirth very good"));
        this.random = this.add(new Setting("Random", 0, 0, 20));
        this.delay = this.add(new Setting("Delay", 100, 0, 10000));
    }
}
