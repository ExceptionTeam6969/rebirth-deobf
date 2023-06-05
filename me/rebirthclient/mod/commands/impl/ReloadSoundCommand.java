//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.common.*;
import com.mojang.realmsclient.gui.*;

public class ReloadSoundCommand extends Command
{
    public ReloadSoundCommand() {
        super("sound", new String[0]);
    }
    
    public void execute(final String[] array) {
        try {
            ((SoundManager)ObfuscationReflectionHelper.getPrivateValue((Class)SoundHandler.class, (Object)ReloadSoundCommand.mc.getSoundHandler(), new String[] { "sndManager", "sndManager" })).reloadSoundSystem();
            Command.sendMessage(ChatFormatting.GREEN + "Reloaded Sound System.");
        }
        catch (Exception ex) {
            System.out.println(ChatFormatting.RED + "Could not restart sound manager: " + ex);
            ex.printStackTrace();
            Command.sendMessage(ChatFormatting.RED + "Couldnt Reload Sound System!");
        }
    }
}
