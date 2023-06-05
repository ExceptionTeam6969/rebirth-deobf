//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.item.*;
import me.rebirthclient.api.util.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.modules.*;
import java.util.*;
import net.minecraft.init.*;

public class ArmorWarner extends Module
{
    private final Setting notifySelf;
    private final Setting notification;
    private final Setting armorThreshold;
    private final Map entityArmorArraylist;
    
    @Override
    public void onUpdate() {
        for (final EntityPlayer entityPlayer : ArmorWarner.mc.world.playerEntities) {
            if (!entityPlayer.isDead) {
                if (!Managers.FRIENDS.isFriend(entityPlayer.getName())) {
                    return;
                }
                for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
                    if (itemStack == ItemStack.EMPTY) {
                        return;
                    }
                    final int damagePercent = EntityUtil.getDamagePercent(itemStack);
                    if (damagePercent <= (int)this.armorThreshold.getValue() && !this.entityArmorArraylist.containsKey(entityPlayer)) {
                        if (entityPlayer == ArmorWarner.mc.player && (boolean)this.notifySelf.getValue()) {
                            this.sendMessage(ChatFormatting.RED + "Your " + this.getArmorPieceName(itemStack) + " low dura!");
                        }
                        if (Managers.FRIENDS.isFriend(entityPlayer.getName()) && (boolean)this.notification.getValue() && entityPlayer != ArmorWarner.mc.player) {
                            ArmorWarner.mc.player.sendChatMessage("/msg " + entityPlayer.getName() + " Yo, " + entityPlayer.getName() + ", ur " + this.getArmorPieceName(itemStack) + " low dura!");
                        }
                        this.entityArmorArraylist.put(entityPlayer, entityPlayer.inventory.armorInventory.indexOf((Object)itemStack));
                    }
                    if (!this.entityArmorArraylist.containsKey(entityPlayer) || this.entityArmorArraylist.get(entityPlayer) != entityPlayer.inventory.armorInventory.indexOf((Object)itemStack)) {
                        continue;
                    }
                    if (damagePercent <= (int)this.armorThreshold.getValue()) {
                        return;
                    }
                    this.entityArmorArraylist.remove(entityPlayer);
                    return;
                }
                if (!this.entityArmorArraylist.containsKey(entityPlayer)) {
                    continue;
                }
                if (entityPlayer.inventory.armorInventory.get((int)this.entityArmorArraylist.get(entityPlayer)) != ItemStack.EMPTY) {
                    return;
                }
                this.entityArmorArraylist.remove(entityPlayer);
            }
        }
    }
    
    public ArmorWarner() {
        super("ArmorWarner", "Notifies when your armor is low durability", Category.PLAYER);
        this.armorThreshold = this.add(new Setting("Armor%", 20, 1, 100));
        this.notifySelf = this.add(new Setting("Self", true));
        this.notification = this.add(new Setting("Friends", true));
        this.entityArmorArraylist = new HashMap();
    }
    
    private String getArmorPieceName(final ItemStack itemStack) {
        if (itemStack.getItem() == Items.DIAMOND_HELMET || itemStack.getItem() == Items.GOLDEN_HELMET || itemStack.getItem() == Items.IRON_HELMET || itemStack.getItem() == Items.CHAINMAIL_HELMET || itemStack.getItem() == Items.LEATHER_HELMET) {
            return "helmet is";
        }
        if (itemStack.getItem() == Items.DIAMOND_CHESTPLATE || itemStack.getItem() == Items.GOLDEN_CHESTPLATE || itemStack.getItem() == Items.IRON_CHESTPLATE || itemStack.getItem() == Items.CHAINMAIL_CHESTPLATE || itemStack.getItem() == Items.LEATHER_CHESTPLATE) {
            return "chest is";
        }
        if (itemStack.getItem() == Items.DIAMOND_LEGGINGS || itemStack.getItem() == Items.GOLDEN_LEGGINGS || itemStack.getItem() == Items.IRON_LEGGINGS || itemStack.getItem() == Items.CHAINMAIL_LEGGINGS || itemStack.getItem() == Items.LEATHER_LEGGINGS) {
            return "leggings are";
        }
        return "boots are";
    }
}
