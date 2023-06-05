//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class InventoryUtil implements Wrapper
{
    public static void doSwap(final int currentItem) {
        InventoryUtil.mc.player.inventory.currentItem = currentItem;
        InventoryUtil.mc.playerController.updateController();
    }
    
    public static int getItemDurability(final ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        }
        return itemStack.getMaxDamage() - itemStack.itemDamage;
    }
    
    public static int findHotbarBlock(final Class clazz) {
        int n = 0;
        if (n < 9) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(n);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (clazz.isInstance(getStackInSlot.getItem())) {
                    return n;
                }
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (clazz.isInstance(((ItemBlock)getStackInSlot.getItem()).getBlock())) {
                        return n;
                    }
                }
            }
            ++n;
            return 0;
        }
        return -1;
    }
    
    public static Map getInventoryAndHotbarSlots() {
        final HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();
        int n = 9;
        if (n <= 44) {
            hashMap.put(n, InventoryUtil.mc.player.inventoryContainer.getInventory().get(n));
            ++n;
            return null;
        }
        return hashMap;
    }
    
    public static int findHotbarClass(final Class clazz) {
        int n = 0;
        if (n < 9) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(n);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (clazz.isInstance(getStackInSlot.getItem())) {
                    return n;
                }
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (clazz.isInstance(((ItemBlock)getStackInSlot.getItem()).getBlock())) {
                        return n;
                    }
                }
            }
            ++n;
            return 0;
        }
        return -1;
    }
    
    public static boolean isNull(final ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }
    
    public static boolean isInstanceOf(final ItemStack itemStack, final Class clazz) {
        if (itemStack == null) {
            return false;
        }
        final Item getItem = itemStack.getItem();
        return clazz.isInstance(getItem) || (getItem instanceof ItemBlock && clazz.isInstance(Block.getBlockFromItem(getItem)));
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b, final boolean b2) {
        final int armorSlot = findArmorSlot(entityEquipmentSlot, b);
        if (armorSlot == -1 && b2) {
            final float n = 0.0f;
            int n2 = 1;
            if (n2 < 5) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n2).getStack();
                if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                    final ItemArmor itemArmor;
                    if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                        final float n3 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                        final boolean b3 = b && EnchantmentHelper.hasBindingCurse(getStack);
                        if (n3 > n) {
                            if (b3) {}
                        }
                    }
                }
                ++n2;
                return 0;
            }
        }
        return armorSlot;
    }
    
    public static int findItemInventorySlot(final Class clazz, final boolean b, final boolean b2) {
        final int classInventorySlot = findClassInventorySlot(clazz, b);
        if (classInventorySlot == -1 && b2) {
            int n = 1;
            if (n < 5) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack();
                if (getStack.getItem() != Items.AIR) {
                    if (!clazz.isInstance(getStack.getItem())) {}
                }
                ++n;
                return 0;
            }
        }
        return classInventorySlot;
    }
    
    public static int findItemInHotbar(final Item item) {
        int n = -1;
        int n2 = 0;
        if (n2 < 9) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(n2);
            if (getStackInSlot != ItemStack.EMPTY) {
                getStackInSlot.getItem();
                if (getStackInSlot.getItem().equals(item)) {
                    n = n2;
                    return n;
                }
            }
            ++n2;
            return 0;
        }
        return n;
    }
    
    public static boolean holdingItem(final Class clazz) {
        final ItemStack getHeldItemMainhand = InventoryUtil.mc.player.getHeldItemMainhand();
        boolean b = isInstanceOf(getHeldItemMainhand, clazz);
        if (!b) {
            InventoryUtil.mc.player.getHeldItemOffhand();
            b = isInstanceOf(getHeldItemMainhand, clazz);
        }
        return b;
    }
    
    public static int findHotbarBlock(final Block block) {
        int n = 0;
        if (n >= 9) {
            return -1;
        }
        final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(n);
        if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() == block) {
            return n;
        }
        ++n;
        return 0;
    }
    
    public static int findClassInventorySlot(final Class clazz, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<K, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (clazz.isInstance(entry.getValue().getItem())) {
                if ((int)entry.getKey() == 45 && !b) {
                    return 0;
                }
                atomicInteger.set((int)entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static int getItemCount(final Item item) {
        int n = 0;
        if (InventoryUtil.mc.player.getHeldItemOffhand().getItem() == item) {
            n += InventoryUtil.mc.player.getHeldItemOffhand().getCount();
        }
        int n2 = 1;
        if (n2 < 5) {
            final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n2).getStack();
            if (getStack.getItem() == item) {
                final int n3 = n + getStack.getCount();
            }
            ++n2;
            return 0;
        }
        for (final Map.Entry<K, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == item) {
                if ((int)entry.getKey() == 45) {
                    return 0;
                }
                final int n4 = n + entry.getValue().getCount();
                return 0;
            }
        }
        return n;
    }
    
    public static int findItemInventorySlot(final Item item, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<K, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == item) {
                if ((int)entry.getKey() == 45 && !b) {
                    return 0;
                }
                atomicInteger.set((int)entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static int getItemHotbar(final Item item) {
        int n = 0;
        if (n >= 9) {
            return -1;
        }
        if (Item.getIdFromItem(InventoryUtil.mc.player.inventory.getStackInSlot(n).getItem()) != Item.getIdFromItem(item)) {
            ++n;
            return 0;
        }
        return n;
    }
    
    public static void switchToHotbarSlot(final Class clazz, final boolean b) {
        final int hotbarClass = findHotbarClass(clazz);
        if (hotbarClass > -1) {
            switchToHotbarSlot(hotbarClass, b);
        }
    }
    
    public static boolean isSlotEmpty(final int n) {
        return InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack().isEmpty();
    }
    
    public static int findItemInventorySlot(final Item item, final boolean b, final boolean b2) {
        final int itemInventorySlot = findItemInventorySlot(item, b);
        if (itemInventorySlot == -1 && b2) {
            int n = 1;
            if (n < 5) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack();
                if (getStack.getItem() != Items.AIR) {
                    if (getStack.getItem() != item) {}
                }
                ++n;
                return 0;
            }
        }
        return itemInventorySlot;
    }
    
    public static List findEmptySlots(final boolean b) {
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterator<Map.Entry<K, ItemStack>> iterator = getInventoryAndHotbarSlots().entrySet().iterator();
        if (!iterator.hasNext()) {
            if (b) {
                int n = 1;
                if (n < 5) {
                    final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack();
                    if (getStack.isEmpty() || getStack.getItem() == Items.AIR) {
                        list.add(n);
                    }
                    ++n;
                    return null;
                }
            }
            return list;
        }
        final Map.Entry<K, ItemStack> entry = iterator.next();
        if (!entry.getValue().isEmpty && entry.getValue().getItem() != Items.AIR) {
            return null;
        }
        list.add(entry.getKey());
        return null;
    }
    
    public static void switchToHotbarSlot(final int currentItem, final boolean b) {
        if (InventoryUtil.mc.player.inventory.currentItem == currentItem || currentItem < 0) {
            return;
        }
        if (b) {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil.mc.playerController.updateController();
        }
        else {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil.mc.player.inventory.currentItem = currentItem;
            InventoryUtil.mc.playerController.updateController();
        }
    }
    
    public static int getEmptyXCarry() {
        int n = 1;
        if (n >= 5) {
            return -1;
        }
        final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack();
        if (!getStack.isEmpty() && getStack.getItem() != Items.AIR) {
            ++n;
            return 0;
        }
        return n;
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b) {
        final int n = -1;
        final float n2 = 0.0f;
        int n3 = 9;
        if (n3 < 45) {
            final ItemStack getStack = Minecraft.getMinecraft().player.inventoryContainer.getSlot(n3).getStack();
            if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                final ItemArmor itemArmor;
                if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                    final float n4 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                    final boolean b2 = b && EnchantmentHelper.hasBindingCurse(getStack);
                    if (n4 > n2) {
                        if (b2) {}
                    }
                }
            }
            ++n3;
            return 0;
        }
        return n;
    }
    
    public static class QueuedTask
    {
        private final int slot;
        private final boolean quickClick;
        private final boolean update;
        
        public QueuedTask(final int slot, final boolean quickClick) {
            this.slot = slot;
            this.quickClick = quickClick;
            this.update = false;
        }
        
        public void run() {
            if (this.update) {
                Wrapper.mc.playerController.updateController();
            }
            if (this.slot != -1) {
                Wrapper.mc.playerController.windowClick(0, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Wrapper.mc.player);
            }
        }
        
        public QueuedTask(final int slot) {
            this.slot = slot;
            this.quickClick = false;
            this.update = false;
        }
        
        public QueuedTask() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }
    }
}
