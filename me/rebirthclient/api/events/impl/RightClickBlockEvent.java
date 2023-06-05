//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

@Cancelable
public class RightClickBlockEvent extends Event
{
    public final BlockPos pos;
    public final ItemStack stack;
    public final EnumHand hand;
    
    public RightClickBlockEvent(final BlockPos pos, final EnumHand hand, final ItemStack stack) {
        this.pos = pos;
        this.hand = hand;
        this.stack = stack;
    }
}
