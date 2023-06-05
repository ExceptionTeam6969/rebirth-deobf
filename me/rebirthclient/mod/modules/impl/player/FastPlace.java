//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.block.*;
import me.rebirthclient.api.util.*;
import net.minecraft.item.*;

public class FastPlace extends Module
{
    public final Setting enderChest;
    public final Setting exp;
    private final Setting delay;
    private final Timer delayTimer;
    
    public FastPlace() {
        super("FastPlace", "Fast projectile", Category.PLAYER);
        this.enderChest = this.add(new Setting("OnlyEnderChest", true));
        this.exp = this.add(new Setting("OnlyEXP", true));
        this.delay = this.add(new Setting("Delay", 20, 0, 100));
        this.delayTimer = new Timer();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if ((boolean)this.enderChest.getValue() && !InventoryUtil.holdingItem((Class)BlockEnderChest.class) && !(boolean)this.exp.getValue()) {
            return;
        }
        if ((boolean)this.exp.getValue() && !InventoryUtil.holdingItem((Class)ItemExpBottle.class)) {
            return;
        }
        if (this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
            FastPlace.mc.rightClickDelayTimer = 1;
            this.delayTimer.reset();
        }
    }
}
