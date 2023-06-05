//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class InventoryMove extends Module
{
    public static InventoryMove INSTANCE;
    public final Setting sneak;
    
    static {
        InventoryMove.INSTANCE = new InventoryMove();
    }
    
    public InventoryMove() {
        super("InvMove", "Allow walking on the interface", Category.MOVEMENT);
        this.sneak = this.add(new Setting("Sneak", false));
        InventoryMove.INSTANCE = this;
    }
}
