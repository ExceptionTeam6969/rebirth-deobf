//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.inventory.*;
import me.rebirthclient.mod.modules.impl.combat.*;

public class KeyPearl extends Module
{
    private final Setting noPlayerTrace;
    private boolean clicked;
    private final Setting sync;
    private final Setting testSync;
    private final Setting mode;
    private final Setting inventory;
    
    private boolean lambda$new$1(final Boolean b) {
        return this.inventory.isOpen();
    }
    
    @Override
    public void onEnable() {
        if (!fullNullCheck() && this.mode.getValue() == Mode.Key) {
            this.throwPearl();
            this.disable();
        }
    }
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    public KeyPearl() {
        super("KeyPearl", "Throws a pearl", Category.PLAYER);
        this.mode = this.add(new Setting("Mode", Mode.Middle));
        this.noPlayerTrace = this.add(new Setting("NoPlayerTrace", true));
        this.inventory = this.add(new Setting("Inventory", true).setParent());
        this.sync = this.add(new Setting("Sync", true, this::lambda$new$0));
        this.testSync = this.add(new Setting("TestSync", true, this::lambda$new$1));
    }
    
    @Override
    public void onTick() {
        if (this.mode.getValue() == Mode.Middle) {
            if (Mouse.isButtonDown(2) && KeyPearl.mc.currentScreen == null) {
                this.clicked = true;
            }
            else if (this.clicked) {
                this.throwPearl();
                this.clicked = false;
            }
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.inventory.isOpen();
    }
    
    private void throwPearl() {
        final RayTraceResult objectMouseOver;
        if ((boolean)this.noPlayerTrace.getValue() && (objectMouseOver = KeyPearl.mc.objectMouseOver) != null && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && objectMouseOver.entityHit instanceof EntityPlayer) {
            return;
        }
        final int hotbarClass = InventoryUtil.findHotbarClass((Class)ItemEnderPearl.class);
        final boolean b = KeyPearl.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        final boolean b2 = KeyPearl.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        if (hotbarClass != -1 || b || b2) {
            final int currentItem = KeyPearl.mc.player.inventory.currentItem;
            if (!b && !b2) {
                InventoryUtil.switchToHotbarSlot(hotbarClass, false);
            }
            KeyPearl.mc.playerController.processRightClick((EntityPlayer)KeyPearl.mc.player, (World)KeyPearl.mc.world, b ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!b && !b2) {
                InventoryUtil.switchToHotbarSlot(currentItem, false);
            }
            return;
        }
        if (this.inventory.getValue()) {
            final int classInventorySlot = InventoryUtil.findClassInventorySlot((Class)ItemEnderPearl.class, false);
            if (classInventorySlot != -1) {
                KeyPearl.mc.playerController.windowClick(0, classInventorySlot, KeyPearl.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)KeyPearl.mc.player);
                KeyPearl.mc.playerController.processRightClick((EntityPlayer)KeyPearl.mc.player, (World)KeyPearl.mc.world, EnumHand.MAIN_HAND);
                KeyPearl.mc.playerController.windowClick(0, classInventorySlot, KeyPearl.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)KeyPearl.mc.player);
                if (this.sync.getValue()) {
                    PacketExp.INSTANCE.throwExp();
                }
                if (this.testSync.getValue()) {
                    KeyPearl.mc.playerController.windowClick(0, classInventorySlot, 0, ClickType.PICKUP, (EntityPlayer)KeyPearl.mc.player);
                    KeyPearl.mc.playerController.windowClick(0, classInventorySlot, 0, ClickType.PICKUP, (EntityPlayer)KeyPearl.mc.player);
                    KeyPearl.mc.playerController.windowClick(0, 36 + KeyPearl.mc.player.inventory.currentItem, 0, ClickType.PICKUP, (EntityPlayer)KeyPearl.mc.player);
                    KeyPearl.mc.playerController.windowClick(0, 36 + KeyPearl.mc.player.inventory.currentItem, 0, ClickType.PICKUP, (EntityPlayer)KeyPearl.mc.player);
                }
            }
        }
    }
    
    private enum Mode
    {
        Middle("Middle", 1);
        
        private static final Mode[] $VALUES;
        
        Key("Key", 0);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.Key, Mode.Middle };
        }
    }
}
