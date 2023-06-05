//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.util.*;

public class GhastNotifier extends Module
{
    private final Setting sound;
    private final Setting censorCoords;
    private final Set ghasts;
    private final Setting chat;
    
    @Override
    public void onUpdate() {
        for (final Entity entity : GhastNotifier.mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityGhast) {
                if (this.ghasts.contains(entity)) {
                    return;
                }
                if (this.chat.getValue()) {
                    if (this.censorCoords.getValue()) {
                        this.sendMessage("There is a ghast!");
                    }
                    else {
                        this.sendMessage("There is a ghast at: " + entity.getPosition().getX() + "X, " + entity.getPosition().getY() + "Y, " + entity.getPosition().getZ() + "Z.");
                    }
                }
                this.ghasts.add(entity);
                if (!(boolean)this.sound.getValue()) {
                    return;
                }
                GhastNotifier.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
    }
    
    public GhastNotifier() {
        super("GhastNotify", "Helps you find ghasts", Category.MISC);
        this.chat = this.add(new Setting("Chat", true).setParent());
        this.censorCoords = this.add(new Setting("CensorCoords", false, this::lambda$new$0));
        this.sound = this.add(new Setting("Sound", true));
        this.ghasts = new HashSet();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.chat.isOpen();
    }
    
    @Override
    public void onEnable() {
        this.ghasts.clear();
    }
}
