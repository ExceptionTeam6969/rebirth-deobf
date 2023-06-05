//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.util.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class TrapSelf extends Module
{
    private final Setting trapHead;
    public static TrapSelf INSTANCE;
    int progress;
    private BlockPos startPos;
    public final Setting safeHealth;
    private final Setting headButton;
    private final Setting packet;
    private final Setting delay;
    private final Setting trapBody;
    private final Setting breakCrystal;
    private final Setting multiPlace;
    private final Setting rotate;
    final Timer timer;
    private final Setting eatingPause;
    private final Setting center;
    
    @Override
    public void onEnable() {
        this.startPos = EntityUtil.getPlayerPos();
        if (this.center.getValue()) {
            AutoCenter.INSTANCE.enable();
        }
    }
    
    @Override
    public void onTick() {
        if (!this.startPos.equals((Object)EntityUtil.getPlayerPos())) {
            this.disable();
            return;
        }
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        this.progress = 0;
        if (InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN) == -1) {
            this.disable();
            return;
        }
        final BlockPos playerPos = EntityUtil.getPlayerPos();
        if (this.trapBody.getValue()) {
            this.placeBlock(playerPos.add(1, 0, 0));
            this.placeBlock(playerPos.add(1, 1, 0));
            this.placeBlock(playerPos.add(-1, 0, 0));
            this.placeBlock(playerPos.add(-1, 1, 0));
            this.placeBlock(playerPos.add(0, 0, 1));
            this.placeBlock(playerPos.add(0, 1, 1));
            this.placeBlock(playerPos.add(0, 0, -1));
            this.placeBlock(playerPos.add(0, 1, -1));
            if (!BlockUtil.canBlockFacing(playerPos.add(0, 0, -1))) {
                this.placeBlock(playerPos.add(0, -1, -1));
            }
            if (!BlockUtil.canBlockFacing(playerPos.add(0, 0, 1))) {
                this.placeBlock(playerPos.add(0, -1, 1));
            }
            if (!BlockUtil.canBlockFacing(playerPos.add(1, 0, 0))) {
                this.placeBlock(playerPos.add(1, -1, 0));
            }
            if (!BlockUtil.canBlockFacing(playerPos.add(-1, 0, 0))) {
                this.placeBlock(playerPos.add(-1, -1, 0));
            }
        }
        if (this.trapHead.getValue()) {
            if (!BlockUtil.canPlace4(playerPos.add(0, 2, 0))) {
                this.placeBlock(playerPos.add(0, 2, -1));
                this.placeBlock(playerPos.add(0, 2, 1));
                this.placeBlock(playerPos.add(-1, 2, 0));
                this.placeBlock(playerPos.add(1, 2, 0));
            }
            this.placeBlock(playerPos.add(0, 2, 0));
        }
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.breakCrystal.isOpen();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.breakCrystal.isOpen();
    }
    
    public TrapSelf() {
        super("TrapSelf", "One Self Trap", Category.COMBAT);
        this.timer = new Timer();
        this.delay = this.add(new Setting("Delay", 50, 0, 500));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.breakCrystal = this.add(new Setting("BreakCrystal", true).setParent());
        this.safeHealth = this.add(new Setting("SafeHealth", 16.0f, 0.0f, 36.0f, this::lambda$new$0));
        this.eatingPause = this.add(new Setting("eatingPause", true, this::lambda$new$1));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.center = this.add(new Setting("Center", true));
        this.trapBody = this.add(new Setting("TrapBody", true));
        this.trapHead = this.add(new Setting("TrapHead", false).setParent());
        this.headButton = this.add(new Setting("useButton", false, this::lambda$new$2));
        this.progress = 0;
        TrapSelf.INSTANCE = this;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (!BlockUtil.canPlace3(blockPos)) {
            return;
        }
        if ((!(boolean)this.breakCrystal.getValue() || EntityUtil.getHealth((Entity)TrapSelf.mc.player) < (float)this.safeHealth.getValue()) && !BlockUtil.canPlace(blockPos)) {
            return;
        }
        if ((boolean)this.breakCrystal.getValue() && EntityUtil.getHealth((Entity)TrapSelf.mc.player) >= (float)this.safeHealth.getValue()) {
            CombatUtil.attackCrystal(blockPos, (boolean)this.rotate.getValue(), false);
        }
        final int currentItem = TrapSelf.mc.player.inventory.currentItem;
        int n;
        if (blockPos.equals((Object)EntityUtil.getPlayerPos().up(2)) && (boolean)this.headButton.getValue()) {
            if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1 && InventoryUtil.findHotbarClass((Class)BlockButton.class) == -1) {
                return;
            }
            if (InventoryUtil.findHotbarClass((Class)BlockButton.class) != -1) {
                n = InventoryUtil.findHotbarClass((Class)BlockButton.class);
            }
            else {
                n = InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN);
            }
        }
        else {
            if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1) {
                return;
            }
            n = InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN);
        }
        InventoryUtil.doSwap(n);
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), (boolean)this.breakCrystal.getValue(), (boolean)this.eatingPause.getValue());
        InventoryUtil.doSwap(currentItem);
        ++this.progress;
        this.timer.reset();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.trapHead.isOpen();
    }
}
