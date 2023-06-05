//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Criticals extends Module
{
    private final Timer delayTimer;
    private final Setting onlySword;
    private final Setting webs;
    private final Setting onlyAura;
    private final Setting delay;
    private final Setting mode;
    public static Criticals INSTANCE;
    
    private boolean lambda$new$0(final Boolean b) {
        return this.mode.getValue() == Mode.NCP;
    }
    
    @Override
    public String getInfo() {
        return (this.mode.getValue() == Mode.NCP) ? String.valueOf(this.mode.getValue()) : Managers.TEXT.normalizeCases(this.mode.getValue());
    }
    
    public Criticals() {
        super("Criticals", "Always do as much damage as you can!", Category.COMBAT);
        this.mode = this.add(new Setting("Mode", Mode.PACKET));
        this.webs = this.add(new Setting("Webs", false, this::lambda$new$0));
        this.onlyAura = this.add(new Setting("OnlyAura", false));
        this.onlySword = this.add(new Setting("OnlySword", true));
        this.delay = this.add(new Setting("Delay", 100, 0, 1000));
        this.delayTimer = new Timer();
        Criticals.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.isCanceled()) {
            return;
        }
        if (Aura.target == null && (boolean)this.onlyAura.getValue()) {
            return;
        }
        if ((boolean)this.onlySword.getValue() && !(Criticals.mc.player.getHeldItemMainhand().item instanceof ItemSword)) {
            return;
        }
        if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        if (send.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && Criticals.mc.player.onGround && Criticals.mc.player.collidedVertically && !Criticals.mc.player.isInLava() && !Criticals.mc.player.isInWater()) {
            final Entity getEntityFromWorld = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)Criticals.mc.world);
            if (getEntityFromWorld instanceof EntityEnderCrystal || getEntityFromWorld == null) {
                return;
            }
            this.delayTimer.reset();
            switch ((Mode)this.mode.getValue()) {
                case PACKET: {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0125, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                    break;
                }
                case NCP: {
                    if ((boolean)this.webs.getValue() && Criticals.mc.world.getBlockState(new BlockPos((Entity)Criticals.mc.player)).getBlock() instanceof BlockWeb) {
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0125, Criticals.mc.player.posZ, false));
                        Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                        break;
                    }
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.11, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1100013579, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.3579E-6, Criticals.mc.player.posZ, false));
                    break;
                }
            }
            Criticals.mc.player.onCriticalHit(getEntityFromWorld);
        }
    }
    
    private enum Mode
    {
        PACKET("PACKET", 0), 
        NCP("NCP", 1);
        
        private static final Mode[] $VALUES;
        
        static {
            $VALUES = new Mode[] { Mode.PACKET, Mode.NCP };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
