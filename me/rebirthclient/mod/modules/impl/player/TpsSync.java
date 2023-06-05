//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class TpsSync extends Module
{
    public final Setting attack;
    public static TpsSync INSTANCE;
    public final Setting mining;
    
    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS", Category.PLAYER);
        this.attack = this.add(new Setting("Attack", false));
        this.mining = this.add(new Setting("Mine", true));
        TpsSync.INSTANCE = this;
    }
}
