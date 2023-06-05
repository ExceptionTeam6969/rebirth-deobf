//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;

public class NoFall extends Module
{
    private final Setting distance;
    public static NoFall INSTANCE;
    
    @Override
    public String getInfo() {
        return "Packet";
    }
    
    static {
        NoFall.INSTANCE = new NoFall();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayer) {
            final Iterator<ItemStack> iterator = NoFall.mc.player.getArmorInventoryList().iterator();
            if (iterator.hasNext()) {
                if (iterator.next().getItem() instanceof ItemElytra) {
                    return;
                }
            }
            else {
                if (NoFall.mc.player.isElytraFlying()) {
                    return;
                }
                if (NoFall.mc.player.fallDistance >= (int)this.distance.getValue()) {
                    ((CPacketPlayer)send.getPacket()).onGround = true;
                }
            }
        }
    }
    
    public NoFall() {
        super("NoFall", "Prevents fall damage", Category.PLAYER);
        this.distance = this.add(new Setting("Distance", 3, 0, 50));
        NoFall.INSTANCE = this;
    }
}
