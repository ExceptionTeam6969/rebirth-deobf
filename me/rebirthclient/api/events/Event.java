//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events;

import me.rebirthclient.*;

public class Event extends net.minecraftforge.fml.common.eventhandler.Event
{
    private int stage;
    
    public void cancel() {
        try {
            this.setCanceled(true);
        }
        catch (Exception ex) {
            Rebirth.LOGGER.info(this.getClass().toString() + " Isn't cancellable!");
        }
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public Event(final int stage) {
        this.stage = stage;
    }
    
    public void setStage(final int stage) {
        this.stage = stage;
    }
    
    public Event() {
    }
}
