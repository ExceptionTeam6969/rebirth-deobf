//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.block.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class AutoWeb extends Module
{
    private final Setting noInWeb;
    private final Setting onlyGround;
    private final Setting onlyAir;
    private final Timer timer;
    private final Setting down;
    private final Setting delay;
    private final Setting multiPlace;
    private final Setting range;
    private final Setting feet;
    private final Setting air;
    private final Setting rotate;
    private final Setting maxSelfSpeed;
    private EntityPlayer target;
    private final Setting raytrace;
    private final Setting minTargetSpeed;
    private int progress;
    private final Setting packet;
    private final Setting checkSelf;
    private final Setting face;
    private final Setting surroundCheck;
    
    @Override
    public void onTick() {
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            return;
        }
        if ((boolean)this.onlyGround.getValue() && !AutoWeb.mc.player.onGround) {
            this.target = null;
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)AutoWeb.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            this.target = null;
            return;
        }
        if (PullCrystal.INSTANCE.isOn() && (boolean)PullCrystal.INSTANCE.pauseWeb.getValue()) {
            this.target = null;
            return;
        }
        this.progress = 0;
        boolean b = false;
        for (final EntityPlayer target : AutoWeb.mc.world.playerEntities) {
            if (EntityUtil.invalid((Entity)target, (double)(float)this.range.getValue())) {
                return;
            }
            if (target != 0 && (boolean)this.noInWeb.getValue()) {
                return;
            }
            b = true;
            this.target = target;
            if ((boolean)this.onlyAir.getValue() && target.onGround) {
                return;
            }
            if (this.down.getValue()) {
                this.placeWeb(new BlockPos(this.target.posX, this.target.posY - 0.3, this.target.posZ));
                this.placeWeb(new BlockPos(this.target.posX + 0.1, this.target.posY - 0.3, this.target.posZ + 0.1));
                this.placeWeb(new BlockPos(this.target.posX - 0.1, this.target.posY - 0.3, this.target.posZ + 0.1));
                this.placeWeb(new BlockPos(this.target.posX - 0.1, this.target.posY - 0.3, this.target.posZ - 0.1));
                this.placeWeb(new BlockPos(this.target.posX + 0.1, this.target.posY - 0.3, this.target.posZ - 0.1));
            }
            if (this.face.getValue()) {
                this.placeWeb(new BlockPos(this.target.posX + 0.2, this.target.posY + 1.5, this.target.posZ + 0.2));
                this.placeWeb(new BlockPos(this.target.posX - 0.2, this.target.posY + 1.5, this.target.posZ + 0.2));
                this.placeWeb(new BlockPos(this.target.posX - 0.2, this.target.posY + 1.5, this.target.posZ - 0.2));
                this.placeWeb(new BlockPos(this.target.posX + 0.2, this.target.posY + 1.5, this.target.posZ - 0.2));
            }
            if (Managers.SPEED.getPlayerSpeed(target) < (double)this.minTargetSpeed.getValue()) {
                if (!(boolean)this.air.getValue()) {
                    continue;
                }
                if (target.onGround) {
                    return;
                }
            }
            if ((boolean)this.feet.getValue() && !CombatUtil.isHole(EntityUtil.getEntityPos((Entity)this.target), true, (int)this.surroundCheck.getValue(), false)) {
                this.placeWeb(new BlockPos(this.target.posX + 0.2, this.target.posY + 0.5, this.target.posZ + 0.2));
                this.placeWeb(new BlockPos(this.target.posX - 0.2, this.target.posY + 0.5, this.target.posZ + 0.2));
                this.placeWeb(new BlockPos(this.target.posX - 0.2, this.target.posY + 0.5, this.target.posZ - 0.2));
                this.placeWeb(new BlockPos(this.target.posX + 0.2, this.target.posY + 0.5, this.target.posZ - 0.2));
            }
            return;
        }
        if (!b) {
            this.target = null;
        }
    }
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.feet.isOpen();
    }
    
    private boolean strictPlaceCheck(final BlockPos blockPos) {
        if (!(boolean)CombatSetting.INSTANCE.strictPlace.getValue() && (boolean)this.raytrace.getValue()) {
            return true;
        }
        final Iterator<EnumFacing> iterator = (Iterator<EnumFacing>)BlockUtil.getPlacableFacings(blockPos, true, (boolean)CombatSetting.INSTANCE.checkRaytrace.getValue() || !(boolean)this.raytrace.getValue()).iterator();
        return iterator.hasNext() && BlockUtil.canClick(blockPos.offset((EnumFacing)iterator.next()));
    }
    
    private void placeWeb(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (!AutoWeb.mc.world.isAirBlock(blockPos.up())) {
            return;
        }
        if (blockPos == 0) {
            return;
        }
        if (blockPos == 0) {
            return;
        }
        if (InventoryUtil.findHotbarClass((Class)BlockWeb.class) == -1) {
            return;
        }
        final int currentItem = AutoWeb.mc.player.inventory.currentItem;
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockWeb.class));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        ++this.progress;
        InventoryUtil.doSwap(currentItem);
        this.timer.reset();
    }
    
    public AutoWeb() {
        super("AutoWeb", "", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.delay = this.add(new Setting("Delay", 50, 0, 2000));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.raytrace = this.add(new Setting("Raytrace", false));
        this.noInWeb = this.add(new Setting("NoInWeb", true));
        this.checkSelf = this.add(new Setting("CheckSelf", true));
        this.onlyGround = this.add(new Setting("SelfGround", true));
        this.down = this.add(new Setting("Down", false));
        this.face = this.add(new Setting("Face", false));
        this.feet = this.add(new Setting("Feet", true).setParent());
        this.onlyAir = this.add(new Setting("OnlyAir", true));
        this.surroundCheck = this.add(new Setting("SurroundCheck", 3, 1, 5, this::lambda$new$0));
        this.air = this.add(new Setting("Air", true));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 6.0, 1.0, 30.0));
        this.minTargetSpeed = this.add(new Setting("MinTargetSpeed", 0.0, 0.0, 20.0));
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 6.0f));
        this.timer = new Timer();
        this.progress = 0;
    }
}
