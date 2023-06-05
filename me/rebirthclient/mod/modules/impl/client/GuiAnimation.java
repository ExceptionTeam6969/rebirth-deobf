//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;

public class GuiAnimation extends Module
{
    private final Setting inventoryTime;
    public static FadeUtils inventoryFade;
    public static GuiAnimation INSTANCE;
    
    static {
        GuiAnimation.inventoryFade = new FadeUtils(500L);
    }
    
    public GuiAnimation() {
        super("GuiAnimation", "", Category.CLIENT);
        this.inventoryTime = this.add(new Setting("InventoryTime", 500, 0, 2000));
        GuiAnimation.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        GuiAnimation.inventoryFade.setLength((long)(int)this.inventoryTime.getValue());
        if (GuiAnimation.mc.currentScreen == null) {
            GuiAnimation.inventoryFade.reset();
        }
    }
}
