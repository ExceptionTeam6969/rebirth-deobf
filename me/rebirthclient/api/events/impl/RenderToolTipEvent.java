//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;

@Cancelable
public class RenderToolTipEvent extends Event
{
    private final int y;
    private final int x;
    private final ItemStack stack;
    
    public int getY() {
        return this.y;
    }
    
    public ItemStack getItemStack() {
        return this.stack;
    }
    
    public RenderToolTipEvent(final ItemStack stack, final int x, final int y) {
        this.stack = stack;
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
}
