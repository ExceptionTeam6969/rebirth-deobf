//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.client.event.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlowDown extends Module
{
    private final Setting sneak;
    private final Setting mode;
    
    public NoSlowDown() {
        super("NoSlowDown", "No item use slow down", Category.MOVEMENT);
        this.mode = this.add(new Setting("Mode", Mode.Vanilla));
        this.sneak = this.add(new Setting("Sneak", false));
    }
    
    @SubscribeEvent
    public void Slow(final InputUpdateEvent inputUpdateEvent) {
        if (!SneakManager.isSneaking && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            if (this.mode.getValue() == Mode.Strict) {
                NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(NoSlowDown.mc.player.inventory.currentItem));
            }
            final MovementInput movementInput = NoSlowDown.mc.player.movementInput;
            movementInput.moveForward /= (float)0.2;
            final MovementInput movementInput2 = NoSlowDown.mc.player.movementInput;
            movementInput2.moveStrafe /= (float)0.2;
        }
        else if (SneakManager.isSneaking && (boolean)this.sneak.getValue() && !NoSlowDown.mc.player.isRiding()) {
            final MovementInput movementInput = inputUpdateEvent.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = inputUpdateEvent.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    public enum Mode
    {
        Strict("Strict", 1);
        
        private static final Mode[] $VALUES;
        
        Vanilla("Vanilla", 0);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.Vanilla, Mode.Strict };
        }
    }
}
