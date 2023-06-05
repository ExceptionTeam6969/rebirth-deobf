//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.item.*;
import me.rebirthclient.api.util.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketExp extends Module
{
    public final Setting throwBind;
    public final Setting allowGui;
    public final Setting down;
    private final Setting delay;
    private final Timer delayTimer;
    private final Setting mode;
    public static PacketExp INSTANCE;
    public final Setting checkDura;
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    private boolean lambda$new$0(final Bind bind) {
        return this.mode.getValue() == Mode.Key;
    }
    
    public PacketExp() {
        super("PacketExp", "Robot module", Category.COMBAT);
        this.delay = this.add(new Setting("Delay", 1, 0, 5));
        this.down = this.add(new Setting("Down", true));
        this.allowGui = this.add(new Setting("allowGui", false));
        this.checkDura = this.add(new Setting("CheckDura", true));
        this.mode = this.add(new Setting("Mode", Mode.Key));
        this.throwBind = this.add(new Setting("ThrowBind", new Bind(-1), this::lambda$new$0));
        this.delayTimer = new Timer();
        PacketExp.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        if (this != 0 && this.delayTimer.passedMs((long)((int)this.delay.getValue() * 20))) {
            this.throwExp();
        }
    }
    
    public void throwExp() {
        final int currentItem = PacketExp.mc.player.inventory.currentItem;
        final int hotbarClass = InventoryUtil.findHotbarClass((Class)ItemExpBottle.class);
        if (hotbarClass != -1) {
            PacketExp.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(hotbarClass));
            PacketExp.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            PacketExp.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            this.delayTimer.reset();
        }
    }
    
    @SubscribeEvent
    public void RotateEvent(final MotionEvent motionEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!(boolean)this.down.getValue()) {
            return;
        }
        if (this != 0) {
            motionEvent.setPitch(90.0f);
        }
    }
    
    protected enum Mode
    {
        private static final Mode[] $VALUES;
        
        Key("Key", 0), 
        Middle("Middle", 1);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.Key, Mode.Middle };
        }
    }
}
