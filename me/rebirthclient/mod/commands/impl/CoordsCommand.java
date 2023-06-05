//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import java.awt.*;
import java.awt.datatransfer.*;
import com.mojang.realmsclient.gui.*;

public class CoordsCommand extends Command
{
    String coords;
    
    public void execute(final String[] array) {
        this.coords = "X: " + (int)CoordsCommand.mc.player.posX + " Y: " + (int)CoordsCommand.mc.player.posY + " Z: " + (int)CoordsCommand.mc.player.posZ;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(this.coords), null);
        Command.sendMessage(ChatFormatting.GRAY + "Coords copied.");
    }
    
    public CoordsCommand() {
        super("coords");
    }
}
