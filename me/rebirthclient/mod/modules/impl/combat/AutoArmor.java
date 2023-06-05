//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.gui.screen.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.inventory.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import me.rebirthclient.api.util.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.item.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.*;

public class AutoArmor extends Module
{
    private final Setting maxThreshold;
    private final Setting delay;
    private final Setting save;
    private final Setting elytraBind;
    private final Setting actions;
    private final Setting autoMend;
    private final List doneSlots;
    private final Setting saveThreshold;
    private final Timer timer;
    private final Setting bootsThreshold;
    private final Setting noHelmBind;
    private final Setting updateController;
    private final Setting tps;
    private final Setting shiftClick;
    private final Setting chestThreshold;
    private final Setting legThreshold;
    private boolean helmOff;
    private final Setting autoElytra;
    private final Setting closestEnemy;
    private final Queue queuedTaskList;
    private boolean elytraOn;
    private final Setting helmetThreshold;
    private final Timer elytraTimer;
    private final Setting curse;
    
    private boolean lambda$new$7(final Bind bind) {
        return !(boolean)this.autoElytra.getValue();
    }
    
    private boolean lambda$new$5(final Integer n) {
        return this.autoMend.isOpen();
    }
    
    @Override
    public void onLogout() {
        this.queuedTaskList.clear();
        this.doneSlots.clear();
    }
    
    private void takeOffSlot(final int n) {
        if (this.queuedTaskList.isEmpty()) {
            final int n2 = -1;
            final Iterator<Integer> iterator = InventoryUtil.findEmptySlots(XCarry.INSTANCE.isOn()).iterator();
            if (iterator.hasNext()) {
                final int intValue = iterator.next();
                if (this.doneSlots.contains(n2)) {
                    return;
                }
                this.doneSlots.add(intValue);
            }
            else if (n2 != -1) {
                if ((n2 < 5 && n2 > 0) || !(boolean)this.shiftClick.getValue()) {
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(n));
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(n2));
                }
                else {
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(n, true));
                }
                if (this.updateController.getValue()) {
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask());
                }
            }
        }
    }
    
    private boolean lambda$new$6(final Integer n) {
        return this.save.isOpen();
    }
    
    private void getSlotOn(final int n, final int n2) {
        if (this.queuedTaskList.isEmpty()) {
            this.doneSlots.remove((Object)n2);
            if ((n2 < 5 && n2 > 0) || !(boolean)this.shiftClick.getValue()) {
                this.queuedTaskList.add(new InventoryUtil.QueuedTask(n2));
                this.queuedTaskList.add(new InventoryUtil.QueuedTask(n));
            }
            else {
                this.queuedTaskList.add(new InventoryUtil.QueuedTask(n2, true));
            }
            if (this.updateController.getValue()) {
                this.queuedTaskList.add(new InventoryUtil.QueuedTask());
            }
        }
    }
    
    private boolean lambda$new$4(final Integer n) {
        return this.autoMend.isOpen();
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (Keyboard.getEventKeyState() && !(AutoArmor.mc.currentScreen instanceof Gui) && ((Bind)this.elytraBind.getValue()).getKey() == Keyboard.getEventKey() && !(boolean)this.autoElytra.getValue()) {
            this.elytraOn = !this.elytraOn;
        }
        if (Keyboard.getEventKeyState() && !(AutoArmor.mc.currentScreen instanceof Gui) && ((Bind)this.noHelmBind.getValue()).getKey() == Keyboard.getEventKey()) {
            this.helmOff = !this.helmOff;
        }
    }
    
    @Override
    public void onTick() {
        if (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (this.autoElytra.getValue()) {
            this.elytraOn = (!AutoArmor.mc.player.onGround && ElytraFly.INSTANCE.isOn());
        }
        else if (((Bind)this.elytraBind.getValue()).getKey() == -1) {
            this.elytraOn = false;
        }
        if (this.queuedTaskList.isEmpty()) {
            final boolean b = (InventoryUtil.holdingItem((Class)ItemExpBottle.class) && AutoArmor.mc.gameSettings.keyBindUseItem.isKeyDown()) || PacketExp.INSTANCE.isThrow();
            if ((boolean)this.autoMend.getValue() && b && (this != null || EntityUtil.isSafe((Entity)AutoArmor.mc.player, 1, false))) {
                final ItemStack getStack = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                final ItemStack getStack2 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                final ItemStack getStack3 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                final ItemStack getStack4 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                if ((!getStack.isEmpty && EntityUtil.getDamagePercent(getStack) < (int)this.maxThreshold.getValue()) || (!getStack2.isEmpty && EntityUtil.getDamagePercent(getStack2) < (int)this.maxThreshold.getValue()) || (!getStack3.isEmpty && EntityUtil.getDamagePercent(getStack3) < (int)this.maxThreshold.getValue()) || (!getStack4.isEmpty && EntityUtil.getDamagePercent(getStack4) < (int)this.maxThreshold.getValue())) {
                    if (!getStack.isEmpty && EntityUtil.getDamagePercent(getStack) >= (int)this.helmetThreshold.getValue()) {
                        this.takeOffSlot(5);
                    }
                    if (!getStack2.isEmpty && EntityUtil.getDamagePercent(getStack2) >= (int)this.chestThreshold.getValue()) {
                        this.takeOffSlot(6);
                    }
                    if (!getStack3.isEmpty && EntityUtil.getDamagePercent(getStack3) >= (int)this.legThreshold.getValue()) {
                        this.takeOffSlot(7);
                    }
                    if (!getStack4.isEmpty && EntityUtil.getDamagePercent(getStack4) >= (int)this.bootsThreshold.getValue()) {
                        this.takeOffSlot(8);
                    }
                    return;
                }
            }
            if (this.save.getValue()) {
                final ItemStack getStack5 = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                if ((boolean)this.save.getValue() && !getStack5.isEmpty && EntityUtil.getDamagePercent(getStack5) <= (int)this.saveThreshold.getValue()) {
                    this.takeOffSlot(5);
                }
                final ItemStack getStack6 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                if ((boolean)this.save.getValue() && !getStack6.isEmpty && EntityUtil.getDamagePercent(getStack6) <= (int)this.saveThreshold.getValue()) {
                    this.takeOffSlot(6);
                }
                final ItemStack getStack7 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                if ((boolean)this.save.getValue() && !getStack7.isEmpty && EntityUtil.getDamagePercent(getStack7) <= (int)this.saveThreshold.getValue()) {
                    this.takeOffSlot(7);
                }
                final ItemStack getStack8 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                if ((boolean)this.save.getValue() && !getStack8.isEmpty && EntityUtil.getDamagePercent(getStack8) <= (int)this.saveThreshold.getValue()) {
                    this.takeOffSlot(8);
                }
            }
            final ItemStack getStack9 = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
            final int armorSlot;
            if (!this.helmOff && getStack9.getItem() == Items.AIR && (armorSlot = InventoryUtil.findArmorSlot(EntityEquipmentSlot.HEAD, (boolean)this.curse.getValue(), XCarry.INSTANCE.isOn())) != -1) {
                this.getSlotOn(5, armorSlot);
            }
            else if (this.helmOff && getStack9.getItem() != Items.AIR) {
                this.takeOffSlot(5);
            }
            final ItemStack getStack10;
            if ((getStack10 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack()).getItem() == Items.AIR) {
                if (this.queuedTaskList.isEmpty()) {
                    if (this.elytraOn && this.elytraTimer.passedMs(500L)) {
                        final int itemInventorySlot = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.INSTANCE.isOn());
                        if (itemInventorySlot != -1) {
                            if ((itemInventorySlot < 5 && itemInventorySlot > 1) || !(boolean)this.shiftClick.getValue()) {
                                this.queuedTaskList.add(new InventoryUtil.QueuedTask(itemInventorySlot));
                                this.queuedTaskList.add(new InventoryUtil.QueuedTask(6));
                            }
                            else {
                                this.queuedTaskList.add(new InventoryUtil.QueuedTask(itemInventorySlot, true));
                            }
                            if (this.updateController.getValue()) {
                                this.queuedTaskList.add(new InventoryUtil.QueuedTask());
                            }
                            this.elytraTimer.reset();
                        }
                    }
                    else {
                        final int armorSlot2;
                        if (!this.elytraOn && (armorSlot2 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.CHEST, (boolean)this.curse.getValue(), XCarry.INSTANCE.isOn())) != -1) {
                            this.getSlotOn(6, armorSlot2);
                        }
                    }
                }
            }
            else if (this.elytraOn && getStack10.getItem() != Items.ELYTRA && this.elytraTimer.passedMs(500L)) {
                if (this.queuedTaskList.isEmpty()) {
                    final int itemInventorySlot2 = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.INSTANCE.isOn());
                    if (itemInventorySlot2 != -1) {
                        this.queuedTaskList.add(new InventoryUtil.QueuedTask(itemInventorySlot2));
                        this.queuedTaskList.add(new InventoryUtil.QueuedTask(6));
                        this.queuedTaskList.add(new InventoryUtil.QueuedTask(itemInventorySlot2));
                        if (this.updateController.getValue()) {
                            this.queuedTaskList.add(new InventoryUtil.QueuedTask());
                        }
                    }
                    this.elytraTimer.reset();
                }
            }
            else if (!this.elytraOn && getStack10.getItem() == Items.ELYTRA && this.elytraTimer.passedMs(500L) && this.queuedTaskList.isEmpty()) {
                int n = InventoryUtil.findItemInventorySlot((Item)Items.DIAMOND_CHESTPLATE, false, XCarry.INSTANCE.isOn());
                if (n == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.IRON_CHESTPLATE, false, XCarry.INSTANCE.isOn())) == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.GOLDEN_CHESTPLATE, false, XCarry.INSTANCE.isOn())) == -1 && (n = InventoryUtil.findItemInventorySlot((Item)Items.CHAINMAIL_CHESTPLATE, false, XCarry.INSTANCE.isOn())) == -1) {
                    n = InventoryUtil.findItemInventorySlot((Item)Items.LEATHER_CHESTPLATE, false, XCarry.INSTANCE.isOn());
                }
                if (n != -1) {
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(n));
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(6));
                    this.queuedTaskList.add(new InventoryUtil.QueuedTask(n));
                    if (this.updateController.getValue()) {
                        this.queuedTaskList.add(new InventoryUtil.QueuedTask());
                    }
                }
                this.elytraTimer.reset();
            }
            AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
            final int armorSlot3;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack().getItem() == Items.AIR && (armorSlot3 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.LEGS, (boolean)this.curse.getValue(), XCarry.INSTANCE.isOn())) != -1) {
                this.getSlotOn(7, armorSlot3);
            }
            AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
            final int armorSlot4;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack().getItem() == Items.AIR && (armorSlot4 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.FEET, (boolean)this.curse.getValue(), XCarry.INSTANCE.isOn())) != -1) {
                this.getSlotOn(8, armorSlot4);
            }
        }
        if (this.timer.passedMs((long)(int)((int)this.delay.getValue() * (this.tps.getValue() ? Managers.SERVER.getTpsFactor() : 1.0f)))) {
            if (!this.queuedTaskList.isEmpty()) {
                int n2 = 0;
                if (n2 < (int)this.actions.getValue()) {
                    final InventoryUtil.QueuedTask queuedTask = this.queuedTaskList.poll();
                    if (queuedTask != null) {
                        queuedTask.run();
                    }
                    ++n2;
                    return;
                }
            }
            this.timer.reset();
        }
    }
    
    @Override
    public String getInfo() {
        if (this.elytraOn) {
            return "Elytra";
        }
        return null;
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.autoMend.isOpen();
    }
    
    public AutoArmor() {
        super("AutoArmor", "Puts Armor on for you", Category.COMBAT);
        this.autoMend = this.add(new Setting("AutoMend", false).setParent());
        this.closestEnemy = this.add(new Setting("EnemyRange", 8, 1, 20, this::lambda$new$0));
        this.helmetThreshold = this.add(new Setting("Helmet%", 80, 1, 100, this::lambda$new$1));
        this.chestThreshold = this.add(new Setting("Chest%", 80, 1, 100, this::lambda$new$2));
        this.legThreshold = this.add(new Setting("Legs%", 80, 1, 100, this::lambda$new$3));
        this.bootsThreshold = this.add(new Setting("Boots%", 80, 1, 100, this::lambda$new$4));
        this.maxThreshold = this.add(new Setting("Max%", 90, 1, 100, this::lambda$new$5));
        this.save = this.add(new Setting("Save", false).setParent());
        this.saveThreshold = this.add(new Setting("Save%", 5, 1, 10, this::lambda$new$6));
        this.delay = this.add(new Setting("Delay", 50, 0, 500));
        this.actions = this.add(new Setting("Actions", 3, 1, 12));
        this.curse = this.add(new Setting("CurseOfBinding", false));
        this.tps = this.add(new Setting("TpsSync", true));
        this.updateController = this.add(new Setting("Update", true));
        this.shiftClick = this.add(new Setting("ShiftClick", false));
        this.autoElytra = this.add(new Setting("AutoElytra", true));
        this.elytraBind = this.add(new Setting("Elytra", new Bind(-1), this::lambda$new$7));
        this.noHelmBind = this.add(new Setting("NoHelmet", new Bind(-1)));
        this.timer = new Timer();
        this.elytraTimer = new Timer();
        this.queuedTaskList = new ConcurrentLinkedQueue();
        this.doneSlots = new ArrayList();
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.autoMend.isOpen();
    }
    
    @Override
    public void onDisable() {
        this.queuedTaskList.clear();
        this.doneSlots.clear();
        this.elytraOn = false;
        this.helmOff = false;
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.autoMend.isOpen();
    }
    
    @Override
    public void onLogin() {
        this.timer.reset();
        this.elytraTimer.reset();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.autoMend.isOpen();
    }
}
