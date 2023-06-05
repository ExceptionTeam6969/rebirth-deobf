//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;

public class AntiWeak extends Module
{
    private final Setting swapMode;
    private final Setting onlyCrystal;
    private int lastSlot;
    private final Setting testSync;
    private final Timer delayTimer;
    private final Setting always;
    private final Setting delay;
    private CPacketUseEntity packet;
    private final Setting sync;
    
    private boolean lambda$new$0(final Boolean b) {
        return this.swapMode.getValue() == SwapMode.Bypass;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.isCanceled()) {
            return;
        }
        if (!AntiWeak.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            return;
        }
        if (AntiWeak.mc.player.getHeldItemMainhand().item instanceof ItemSword) {
            return;
        }
        if (!this.delayTimer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        if (send.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
            final Entity getEntityFromWorld = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)AntiWeak.mc.world);
            if (getEntityFromWorld == null || (!(getEntityFromWorld instanceof EntityEnderCrystal) && (boolean)this.onlyCrystal.getValue())) {
                return;
            }
            this.packet = (CPacketUseEntity)send.getPacket();
            this.doAnti();
            this.delayTimer.reset();
            send.setCanceled(true);
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        this.update();
    }
    
    @Override
    public void onUpdate() {
        this.update();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.swapMode.getValue() == SwapMode.Bypass;
    }
    
    @Override
    public String getInfo() {
        return ((SwapMode)this.swapMode.getValue()).name();
    }
    
    @Override
    public void onTick() {
        this.update();
    }
    
    public AntiWeak() {
        super("AntiWeak", "anti weak", Category.COMBAT);
        this.delay = this.add(new Setting("Delay", 100, 0, 500));
        this.swapMode = this.add(new Setting("SwapMode", SwapMode.Bypass));
        this.onlyCrystal = this.add(new Setting("OnlyCrystal", true));
        this.testSync = this.add(new Setting("TestSync", true, this::lambda$new$0));
        this.sync = this.add(new Setting("Sync", false, this::lambda$new$1).setParent());
        this.always = this.add(new Setting("Always", false, this::lambda$new$2));
        this.lastSlot = -1;
        this.delayTimer = new Timer();
        this.packet = null;
    }
    
    private void doAnti() {
        if (this.packet == null) {
            return;
        }
        int lastSlot;
        if (this.swapMode.getValue() != SwapMode.Bypass) {
            lastSlot = InventoryUtil.findHotbarBlock((Class)ItemSword.class);
        }
        else {
            lastSlot = InventoryUtil.findClassInventorySlot((Class)ItemSword.class, true);
        }
        if (lastSlot == -1) {
            return;
        }
        final int currentItem = AntiWeak.mc.player.inventory.currentItem;
        if (this.swapMode.getValue() != SwapMode.Bypass) {
            InventoryUtil.doSwap(lastSlot);
        }
        else {
            AntiWeak.mc.playerController.windowClick(0, lastSlot, currentItem, ClickType.SWAP, (EntityPlayer)AntiWeak.mc.player);
        }
        AntiWeak.mc.player.connection.sendPacket((Packet)this.packet);
        if (this.swapMode.getValue() != SwapMode.Bypass) {
            if (this.swapMode.getValue() != SwapMode.Normal) {
                InventoryUtil.doSwap(currentItem);
            }
        }
        else {
            AntiWeak.mc.playerController.windowClick(0, lastSlot, currentItem, ClickType.SWAP, (EntityPlayer)AntiWeak.mc.player);
            if ((boolean)this.sync.getValue() && (boolean)this.always.getValue()) {
                PacketExp.INSTANCE.throwExp();
            }
            this.lastSlot = lastSlot;
            if (this.testSync.getValue()) {
                AntiWeak.mc.player.connection.sendPacket((Packet)new CPacketConfirmTransaction(AntiWeak.mc.player.inventoryContainer.windowId, AntiWeak.mc.player.openContainer.getNextTransactionID(AntiWeak.mc.player.inventory), true));
            }
        }
    }
    
    private void update() {
        if (this.lastSlot == -1 || (boolean)this.always.getValue() || !(boolean)this.sync.getValue()) {
            return;
        }
        if (!(((ItemStack)AntiWeak.mc.player.inventoryContainer.getInventory().get(this.lastSlot)).getItem() instanceof ItemSword)) {
            PacketExp.INSTANCE.throwExp();
            this.lastSlot = -1;
        }
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.sync.isOpen() && this.swapMode.getValue() == SwapMode.Bypass;
    }
    
    public enum SwapMode
    {
        Normal("Normal", 0), 
        Bypass("Bypass", 2);
        
        private static final SwapMode[] $VALUES;
        
        Silent("Silent", 1);
        
        private SwapMode(final String s, final int n) {
        }
        
        static {
            $VALUES = new SwapMode[] { SwapMode.Normal, SwapMode.Silent, SwapMode.Bypass };
        }
    }
}
