//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import me.rebirthclient.api.util.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class AutoTotem extends Module
{
    private final Setting crystal;
    private final Setting health;
    private final Setting mainHand;
    
    private boolean lambda$new$0(final Boolean b) {
        return !(boolean)this.mainHand.getValue();
    }
    
    @Override
    public void onTick() {
        if (AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount() > (float)this.health.getValue()) {
            if (!AutoTotem.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) && (boolean)this.crystal.getValue() && !(boolean)this.mainHand.getValue()) {
                final int itemInventorySlot = InventoryUtil.findItemInventorySlot(Items.END_CRYSTAL, true, true);
                if (itemInventorySlot != -1) {
                    final boolean equals = AutoTotem.mc.player.getHeldItemOffhand().getItem().equals(Items.AIR);
                    AutoTotem.mc.playerController.windowClick(0, itemInventorySlot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                    AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                    if (!equals) {
                        AutoTotem.mc.playerController.windowClick(0, itemInventorySlot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                    }
                }
            }
            return;
        }
        if (AutoTotem.mc.player.getHeldItemOffhand().getItem().equals(Items.TOTEM_OF_UNDYING) || AutoTotem.mc.player.getHeldItemMainhand().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            return;
        }
        final int itemInventorySlot2 = InventoryUtil.findItemInventorySlot(Items.TOTEM_OF_UNDYING, true, true);
        if (itemInventorySlot2 != -1) {
            if (!(boolean)this.mainHand.getValue()) {
                final boolean equals2 = AutoTotem.mc.player.getHeldItemOffhand().getItem().equals(Items.AIR);
                AutoTotem.mc.playerController.windowClick(0, itemInventorySlot2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                if (!equals2) {
                    AutoTotem.mc.playerController.windowClick(0, itemInventorySlot2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                }
            }
            else {
                InventoryUtil.switchToHotbarSlot(0, false);
                if (AutoTotem.mc.player.inventory.getStackInSlot(0).getItem().equals(Items.TOTEM_OF_UNDYING)) {
                    return;
                }
                final boolean equals3 = AutoTotem.mc.player.getHeldItemMainhand().getItem().equals(Items.AIR);
                AutoTotem.mc.playerController.windowClick(0, itemInventorySlot2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                AutoTotem.mc.playerController.windowClick(0, 36, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                if (!equals3) {
                    AutoTotem.mc.playerController.windowClick(0, itemInventorySlot2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
                }
            }
            AutoTotem.mc.player.connection.sendPacket((Packet)new CPacketConfirmTransaction(AutoTotem.mc.player.inventoryContainer.windowId, AutoTotem.mc.player.openContainer.getNextTransactionID(AutoTotem.mc.player.inventory), true));
        }
    }
    
    public AutoTotem() {
        super("AutoTotem", "AutoTotem", Category.COMBAT);
        this.health = this.add(new Setting("Health", 16.0f, 0.0f, 36.0f));
        this.mainHand = this.add(new Setting("MainHand", false));
        this.crystal = this.add(new Setting("Crystal", true, this::lambda$new$0));
    }
}
