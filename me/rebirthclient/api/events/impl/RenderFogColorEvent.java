//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

@Cancelable
public class RenderFogColorEvent extends Event
{
    private Color color;
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
}
