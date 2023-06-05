//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.*;

public class AutoLogin extends Module
{
    final Timer timer;
    private final Setting password;
    boolean needLogin;
    
    @Override
    public void onLogin() {
        this.needLogin = true;
        this.timer.reset();
    }
    
    @Override
    public void onTick() {
        if (this.needLogin && this.timer.passedMs(1000L)) {
            this.needLogin = false;
            AutoLogin.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/login " + (String)this.password.getValue()));
        }
    }
    
    public AutoLogin() {
        super("AutoLogin", "Auto login", Category.MISC);
        this.timer = new Timer();
        this.password = this.add(new Setting("password", "password"));
        this.needLogin = false;
    }
}
