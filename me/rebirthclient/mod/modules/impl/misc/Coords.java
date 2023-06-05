//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import java.awt.*;
import java.awt.datatransfer.*;
import me.rebirthclient.mod.modules.*;

public class Coords extends Module
{
    @Override
    public void onEnable() {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("X: " + (int)Coords.mc.player.posX + " Y: " + (int)Coords.mc.player.posY + " Z: " + (int)Coords.mc.player.posZ), null);
        this.disable();
    }
    
    public Coords() {
        super("Coords", "copies your current position to the clipboard", Category.MISC);
    }
}
