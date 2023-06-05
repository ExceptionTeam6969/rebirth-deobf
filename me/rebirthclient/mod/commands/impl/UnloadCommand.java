//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.*;

public class UnloadCommand extends Command
{
    public UnloadCommand() {
        super("unload", new String[0]);
    }
    
    public void execute(final String[] array) {
        Rebirth.unload(true);
    }
}
