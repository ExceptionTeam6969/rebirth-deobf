//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import java.util.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import me.rebirthclient.mod.modules.*;

public class Replenish extends Module
{
    private final ArrayList Hotbar;
    private final Timer timer;
    private final Setting delay;
    private final Setting stack;
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        this.Hotbar.clear();
        int n = 0;
        if (n < 9) {
            final ItemStack getStackInSlot = Replenish.mc.player.inventory.getStackInSlot(n);
            if (!getStackInSlot.isEmpty() && !this.Hotbar.contains(getStackInSlot.getItem())) {
                this.Hotbar.add(getStackInSlot.getItem());
            }
            else {
                this.Hotbar.add(Items.AIR);
            }
            ++n;
        }
    }
    
    @Override
    public void onUpdate() {
        if (Replenish.mc.currentScreen != null) {
            return;
        }
        if (!this.timer.passedMs((long)((int)this.delay.getValue() * 1000))) {
            return;
        }
        int n = 0;
        if (n >= 9) {
            return;
        }
        if (n == 0) {
            ++n;
            return;
        }
        this.timer.reset();
    }
    
    public Replenish() {
        super("Replenish", "Replenishes your hotbar", Category.PLAYER);
        this.delay = this.add(new Setting("Delay", 2, 0, 10));
        this.stack = this.add(new Setting("Stack", 50, 8, 64));
        this.timer = new Timer();
        this.Hotbar = new ArrayList();
    }
}
