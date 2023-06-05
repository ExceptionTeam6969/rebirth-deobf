//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.util.math.*;
import java.math.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.render.*;

public class CatCrystal extends Module
{
    private final Setting onlyFood;
    private final Setting slowFace;
    private final Setting Break;
    private final Setting boxAlpha;
    private final Timer faceTimer;
    private final Setting color;
    private final Setting rotate;
    private final Setting outline;
    private final Setting terrainIgnore;
    private final Setting extraPlaceDamage;
    private final Setting slowDelay;
    private final Setting collision;
    private final Setting text;
    private final Timer noPosTimer;
    private EntityPlayer displayTarget;
    private final Setting breakMaxSelf;
    private final Setting box;
    private float lastYaw;
    private AxisAlignedBB lastBB;
    private float lastPitch;
    private final Setting maxDura;
    private final Setting slowMinDamage;
    private final Setting outlineAlpha;
    private final Setting placeDelay;
    private final Setting breakOnlyHasCrystal;
    private final Setting breakDelay;
    private final Setting fadeTime;
    private final Setting render;
    private final Setting targetRange;
    private final Setting updateDelay;
    private final Setting placeMinDamage;
    private final Timer placeTimer;
    private BigDecimal lastDamage;
    private final Setting animationTime;
    private final Setting noUsing;
    private final Setting maxTarget;
    private BlockPos renderPos;
    private final Setting preferBreakDamage;
    private final Setting page;
    private final Setting antiSuicide;
    public static CatCrystal INSTANCE;
    private final Setting predictTicks;
    private final Setting breakMinDamage;
    private final Timer delayTimer;
    private final Setting armorBreaker;
    private final Setting startTime;
    private final FadeUtils fadeUtils;
    private int lastHotbar;
    private final Setting place;
    private final Setting switchCooldown;
    private AxisAlignedBB nowBB;
    private final FadeUtils animation;
    private final Setting crystalRange;
    private final Setting armorBreakerDamage;
    private final Setting extraPlace;
    private final Setting placeRange;
    private final Setting breakRange;
    private final Setting placeMaxSelf;
    private final Setting autoSwap;
    private final Setting wallRange;
    public static BlockPos lastPos;
    private final Timer switchTimer;
    
    private boolean lambda$new$25(final Boolean b) {
        return this.page.getValue() == Pages.Render;
    }
    
    private boolean lambda$new$33(final Integer n) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    @Override
    public String getInfo() {
        if (this.displayTarget != null) {
            return this.displayTarget.getName();
        }
        return null;
    }
    
    private boolean lambda$new$32(final Integer n) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    public CatCrystal() {
        super("CatCrystal", "ez", Category.COMBAT);
        this.page = this.add(new Setting("Page", Pages.General));
        this.rotate = this.add(new Setting("Rotate", true, this::lambda$new$0));
        this.noUsing = this.add(new Setting("NoUsing", true, this::lambda$new$1).setParent());
        this.onlyFood = this.add(new Setting("OnlyFood", false, this::lambda$new$2));
        this.switchCooldown = this.add(new Setting("SwitchCooldown", 100, 0, 1000, this::lambda$new$3));
        this.antiSuicide = this.add(new Setting("AntiSuicide", 3.0, 0.0, 10.0, this::lambda$new$4));
        this.wallRange = this.add(new Setting("WallRange", 3.0, 0.0, 6.0, this::lambda$new$5));
        this.maxTarget = this.add(new Setting("MaxTarget", 3, 1, 6, this::lambda$new$6));
        this.crystalRange = this.add(new Setting("CrystalRange", 6.0f, 0.0f, 16.0f, this::lambda$new$7));
        this.targetRange = this.add(new Setting("TargetRange", 6.0, 0.0, 16.0, this::lambda$new$8));
        this.updateDelay = this.add(new Setting("UpdateDelay", 50, 0, 1000, this::lambda$new$9));
        this.place = this.add(new Setting("Place", true, this::lambda$new$10));
        this.placeDelay = this.add(new Setting("PlaceDelay", 300, 0, 1000, this::lambda$new$11));
        this.placeRange = this.add(new Setting("PlaceRange", 5.0, 0.0, 6, this::lambda$new$12));
        this.placeMinDamage = this.add(new Setting("PlaceMin", 5.0, 0.0, 36.0, this::lambda$new$13));
        this.placeMaxSelf = this.add(new Setting("PlaceMaxSelf", 12.0, 0.0, 36.0, this::lambda$new$14));
        this.autoSwap = this.add(new Setting("AutoSwap", SwapMode.OFF, this::lambda$new$15));
        this.extraPlace = this.add(new Setting("ExtraPlace", true, this::lambda$new$16).setParent());
        this.extraPlaceDamage = this.add(new Setting("ExtraDamage", 8.0, 0.0, 36.0, this::lambda$new$17));
        this.Break = this.add(new Setting("Break", true, this::lambda$new$18));
        this.breakDelay = this.add(new Setting("BreakDelay", 300, 0, 1000, this::lambda$new$19));
        this.breakRange = this.add(new Setting("BreakRange", 5.0, 0.0, 6.0, this::lambda$new$20));
        this.breakMinDamage = this.add(new Setting("BreakMin", 4.0, 0.0, 36.0, this::lambda$new$21));
        this.breakMaxSelf = this.add(new Setting("SelfBreak", 12.0, 0.0, 36.0, this::lambda$new$22));
        this.preferBreakDamage = this.add(new Setting("PreferBreak", 8.0, 0.0, 36.0, this::lambda$new$23));
        this.breakOnlyHasCrystal = this.add(new Setting("OnlyHasCrystal", false, this::lambda$new$24));
        this.render = this.add(new Setting("Render", true, this::lambda$new$25));
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$26).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 150, 0, 255, this::lambda$new$27));
        this.box = this.add(new Setting("Box", true, this::lambda$new$28).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 70, 0, 255, this::lambda$new$29));
        this.text = this.add(new Setting("DamageText", true, this::lambda$new$30));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255), this::lambda$new$31).hideAlpha());
        this.animationTime = this.add(new Setting("AnimationTime", 500, 0, 3000, this::lambda$new$32));
        this.startTime = this.add(new Setting("StartFadeTime", 300, 0, 2000, this::lambda$new$33));
        this.fadeTime = this.add(new Setting("FadeTime", 300, 0, 2000, this::lambda$new$34));
        this.predictTicks = this.add(new Setting("PredictTicks", 4, 0, 10, this::lambda$new$35));
        this.collision = this.add(new Setting("Collision", false, this::lambda$new$36));
        this.terrainIgnore = this.add(new Setting("TerrainIgnore", false, this::lambda$new$37));
        this.slowFace = this.add(new Setting("SlowFace", true, this::lambda$new$38).setParent());
        this.slowDelay = this.add(new Setting("SlowDelay", 600, 0, 2000, this::lambda$new$39));
        this.slowMinDamage = this.add(new Setting("SlowMin", 3.0, 0.0, 36.0, this::lambda$new$40));
        this.armorBreaker = this.add(new Setting("ArmorBreaker", true, this::lambda$new$41).setParent());
        this.maxDura = this.add(new Setting("MaxDura", 8, 0, 100, this::lambda$new$42));
        this.armorBreakerDamage = this.add(new Setting("BreakerDamage", 3.0, 0.0, 36.0, this::lambda$new$43));
        this.switchTimer = new Timer();
        this.delayTimer = new Timer();
        this.placeTimer = new Timer();
        this.faceTimer = new Timer();
        this.lastYaw = 0.0f;
        this.lastPitch = 0.0f;
        this.lastHotbar = -1;
        this.noPosTimer = new Timer();
        this.renderPos = null;
        this.lastBB = null;
        this.nowBB = null;
        this.fadeUtils = new FadeUtils(500L);
        this.animation = new FadeUtils(500L);
        CatCrystal.INSTANCE = this;
    }
    
    private boolean lambda$new$27(final Integer n) {
        return this.outline.isOpen() && this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private boolean lambda$new$30(final Boolean b) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private boolean lambda$new$40(final Double n) {
        return this.page.getValue() == Pages.Misc && this.slowFace.isOpen();
    }
    
    private boolean lambda$new$8(final Double n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$16(final Boolean b) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    private boolean lambda$new$20(final Double n) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    private boolean lambda$new$35(final Integer n) {
        return this.page.getValue() == Pages.Predict;
    }
    
    private boolean lambda$new$9(final Integer n) {
        return this.page.getValue() == Pages.General;
    }
    
    private void doBreak(final EntityEnderCrystal entityEnderCrystal, final double n) {
        this.faceTimer.reset();
        if (!(boolean)this.Break.getValue()) {
            return;
        }
        CatCrystal.lastPos = EntityUtil.getEntityPos((Entity)entityEnderCrystal);
        final float[] calcAngle = MathUtil.calcAngle(CatCrystal.mc.player.getPositionEyes(CatCrystal.mc.getRenderPartialTicks()), new Vec3d(entityEnderCrystal.posX, entityEnderCrystal.posY + 0.25, entityEnderCrystal.posZ));
        this.lastYaw = calcAngle[0];
        this.lastPitch = calcAngle[1];
        this.lastDamage = BigDecimal.valueOf(n).setScale(1, RoundingMode.UP);
        if (!CombatUtil.breakTimer.passedMs((long)(int)this.breakDelay.getValue())) {
            return;
        }
        CombatUtil.breakTimer.reset();
        CatCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)entityEnderCrystal));
        CatCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
        if (this.rotate.getValue()) {
            EntityUtil.faceXYZ(entityEnderCrystal.posX, entityEnderCrystal.posY + 0.25, entityEnderCrystal.posZ);
        }
        if (!this.placeTimer.passedMs((long)(int)this.placeDelay.getValue()) || !(boolean)this.extraPlace.getValue() || n < (double)this.extraPlaceDamage.getValue()) {
            return;
        }
        this.placeTimer.reset();
        if (CatCrystal.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) || CatCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL)) {
            BlockUtil.placeCrystal(CatCrystal.lastPos, (boolean)this.rotate.getValue());
        }
        else if (this != 0) {
            final int currentItem = CatCrystal.mc.player.inventory.currentItem;
            int n2 = -1;
            if (this.autoSwap.getValue() == SwapMode.NORMAL || this.autoSwap.getValue() == SwapMode.SILENT) {
                n2 = InventoryUtil.findItemInHotbar(Items.END_CRYSTAL);
                if (n2 == -1) {
                    return;
                }
                InventoryUtil.doSwap(InventoryUtil.findItemInHotbar(Items.END_CRYSTAL));
            }
            if (this.autoSwap.getValue() == SwapMode.BYPASS) {
                n2 = InventoryUtil.findItemInventorySlot(Items.END_CRYSTAL, true, true);
                if (n2 == -1) {
                    return;
                }
                CatCrystal.mc.playerController.windowClick(0, n2, currentItem, ClickType.SWAP, (EntityPlayer)CatCrystal.mc.player);
            }
            BlockUtil.placeCrystal(CatCrystal.lastPos, (boolean)this.rotate.getValue());
            if (this.autoSwap.getValue() == SwapMode.SILENT && n2 != -1) {
                InventoryUtil.doSwap(currentItem);
            }
            if (this.autoSwap.getValue() == SwapMode.BYPASS && n2 != -1) {
                CatCrystal.mc.playerController.windowClick(0, n2, currentItem, ClickType.SWAP, (EntityPlayer)CatCrystal.mc.player);
            }
        }
    }
    
    private boolean lambda$new$39(final Integer n) {
        return this.page.getValue() == Pages.Misc && this.slowFace.isOpen();
    }
    
    private boolean lambda$new$43(final Double n) {
        return this.page.getValue() == Pages.Misc && this.armorBreaker.isOpen();
    }
    
    @SubscribeEvent
    public void onUpdateWalk(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.update();
    }
    
    private boolean lambda$new$38(final Boolean b) {
        return this.page.getValue() == Pages.Misc;
    }
    
    private boolean lambda$new$28(final Boolean b) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$31(final Color color) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private double getPlaceDamage(final EntityPlayer entityPlayer) {
        if ((boolean)this.slowFace.getValue() && this.faceTimer.passedMs((long)(int)this.slowDelay.getValue())) {
            return (double)this.slowMinDamage.getValue();
        }
        if (this.armorBreaker.getValue()) {
            final ItemStack getStack = entityPlayer.inventoryContainer.getSlot(5).getStack();
            final ItemStack getStack2 = entityPlayer.inventoryContainer.getSlot(6).getStack();
            final ItemStack getStack3 = entityPlayer.inventoryContainer.getSlot(7).getStack();
            final ItemStack getStack4 = entityPlayer.inventoryContainer.getSlot(8).getStack();
            if ((!getStack.isEmpty && EntityUtil.getDamagePercent(getStack) <= (int)this.maxDura.getValue()) || (!getStack2.isEmpty && EntityUtil.getDamagePercent(getStack2) <= (int)this.maxDura.getValue()) || (!getStack3.isEmpty && EntityUtil.getDamagePercent(getStack3) <= (int)this.maxDura.getValue()) || (!getStack4.isEmpty && EntityUtil.getDamagePercent(getStack4) <= (int)this.maxDura.getValue())) {
                return (double)this.armorBreakerDamage.getValue();
            }
        }
        return (double)this.placeMinDamage.getValue();
    }
    
    private boolean lambda$new$36(final Boolean b) {
        return this.page.getValue() == Pages.Predict;
    }
    
    private boolean lambda$new$17(final Double n) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue() && this.extraPlace.isOpen();
    }
    
    private boolean lambda$new$42(final Integer n) {
        return this.page.getValue() == Pages.Misc && this.armorBreaker.isOpen();
    }
    
    private boolean lambda$new$6(final Integer n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$23(final Double n) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    private boolean lambda$new$24(final Boolean b) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    private boolean lambda$new$29(final Integer n) {
        return this.box.isOpen() && this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private boolean lambda$new$22(final Double n) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getPacket() instanceof CPacketHeldItemChange && ((CPacketHeldItemChange)send.getPacket()).getSlotId() != this.lastHotbar) {
            this.lastHotbar = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
            this.switchTimer.reset();
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$41(final Boolean b) {
        return this.page.getValue() == Pages.Misc;
    }
    
    private void doPlace(BlockPos down) {
        if (!(boolean)this.place.getValue()) {
            return;
        }
        if (!CatCrystal.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) && !CatCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) && this != 0) {
            CatCrystal.lastPos = null;
            return;
        }
        CatCrystal.lastPos = down;
        final RayTraceResult rayTraceBlocks = CatCrystal.mc.world.rayTraceBlocks(new Vec3d(CatCrystal.mc.player.posX, CatCrystal.mc.player.posY + CatCrystal.mc.player.getEyeHeight(), CatCrystal.mc.player.posZ), new Vec3d(down.getX() + 0.5, down.getY() - 0.5, down.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        down = down.down();
        this.lastDamage = BigDecimal.valueOf(CrystalUtil.calculateDamage(down, (Entity)this.displayTarget, (int)this.predictTicks.getValue(), (boolean)this.collision.getValue(), (boolean)this.terrainIgnore.getValue())).setScale(1, RoundingMode.UP);
        final float[] legitRotations = EntityUtil.getLegitRotations(new Vec3d((Vec3i)down.up()).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getOpposite().getDirectionVec()).scale(0.5)));
        this.lastYaw = legitRotations[0];
        this.lastPitch = legitRotations[1];
        if (!this.placeTimer.passedMs((long)(int)this.placeDelay.getValue())) {
            return;
        }
        this.placeTimer.reset();
        if (CatCrystal.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) || CatCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL)) {
            BlockUtil.placeCrystal(down.up(), (boolean)this.rotate.getValue());
        }
        else if (this != 0) {
            final int currentItem = CatCrystal.mc.player.inventory.currentItem;
            int n = -1;
            if (this.autoSwap.getValue() == SwapMode.NORMAL || this.autoSwap.getValue() == SwapMode.SILENT) {
                n = InventoryUtil.findItemInHotbar(Items.END_CRYSTAL);
                if (n == -1) {
                    return;
                }
                InventoryUtil.doSwap(n);
            }
            if (this.autoSwap.getValue() == SwapMode.BYPASS) {
                n = InventoryUtil.findItemInventorySlot(Items.END_CRYSTAL, true, true);
                if (n == -1) {
                    return;
                }
                CatCrystal.mc.playerController.windowClick(0, n, currentItem, ClickType.SWAP, (EntityPlayer)CatCrystal.mc.player);
            }
            BlockUtil.placeCrystal(down.up(), (boolean)this.rotate.getValue());
            if (this.autoSwap.getValue() == SwapMode.SILENT && n != -1) {
                InventoryUtil.doSwap(currentItem);
            }
            if (this.autoSwap.getValue() == SwapMode.BYPASS && n != -1) {
                CatCrystal.mc.playerController.windowClick(0, n, currentItem, ClickType.SWAP, (EntityPlayer)CatCrystal.mc.player);
            }
        }
    }
    
    private boolean lambda$new$11(final Integer n) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    @Override
    public void onUpdate() {
        this.update();
    }
    
    private boolean lambda$new$21(final Double n) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    private boolean lambda$new$26(final Boolean b) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    private boolean lambda$new$5(final Double n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$4(final Double n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$18(final Boolean b) {
        return this.page.getValue() == Pages.Break;
    }
    
    private void update() {
        if (fullNullCheck()) {
            return;
        }
        this.fadeUtils.setLength((long)(int)this.fadeTime.getValue());
        if (CatCrystal.lastPos != null) {
            this.lastBB = CatCrystal.mc.world.getBlockState(new BlockPos((Vec3i)CatCrystal.lastPos.down())).getSelectedBoundingBox((World)CatCrystal.mc.world, new BlockPos((Vec3i)CatCrystal.lastPos.down()));
            this.noPosTimer.reset();
            if (this.nowBB == null) {
                this.nowBB = this.lastBB;
            }
            if (this.renderPos == null || !this.renderPos.equals((Object)CatCrystal.lastPos)) {
                this.animation.setLength(((int)this.animationTime.getValue() <= 0) ? 0L : ((long)((Math.abs(this.nowBB.minX - this.lastBB.minX) + Math.abs(this.nowBB.minY - this.lastBB.minY) + Math.abs(this.nowBB.minZ - this.lastBB.minZ)) * (int)this.animationTime.getValue())));
                this.animation.reset();
                this.renderPos = CatCrystal.lastPos;
            }
        }
        if (!this.noPosTimer.passedMs((long)(int)this.startTime.getValue())) {
            this.fadeUtils.reset();
        }
        final double easeInQuad = this.animation.easeInQuad();
        if (this.nowBB != null && this.lastBB != null) {
            if (Math.abs(this.nowBB.minX - this.lastBB.minX) + Math.abs(this.nowBB.minY - this.lastBB.minY) + Math.abs(this.nowBB.minZ - this.lastBB.minZ) > 5.0) {
                this.nowBB = this.lastBB;
            }
            this.nowBB = new AxisAlignedBB(this.nowBB.minX + (this.lastBB.minX - this.nowBB.minX) * easeInQuad, this.nowBB.minY + (this.lastBB.minY - this.nowBB.minY) * easeInQuad, this.nowBB.minZ + (this.lastBB.minZ - this.nowBB.minZ) * easeInQuad, this.nowBB.maxX + (this.lastBB.maxX - this.nowBB.maxX) * easeInQuad, this.nowBB.maxY + (this.lastBB.maxY - this.nowBB.maxY) * easeInQuad, this.nowBB.maxZ + (this.lastBB.maxZ - this.nowBB.maxZ) * easeInQuad);
        }
        if (!this.delayTimer.passedMs((long)(int)this.updateDelay.getValue())) {
            return;
        }
        if ((boolean)this.noUsing.getValue() && (EntityUtil.isEating() || (CatCrystal.mc.player.isHandActive() && !(boolean)this.onlyFood.getValue()))) {
            CatCrystal.lastPos = null;
            return;
        }
        if (!this.switchTimer.passedMs((long)(int)this.switchCooldown.getValue())) {
            CatCrystal.lastPos = null;
            return;
        }
        if ((boolean)this.breakOnlyHasCrystal.getValue() && !CatCrystal.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) && !CatCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) && this != 0) {
            CatCrystal.lastPos = null;
            return;
        }
        this.delayTimer.reset();
        int n = 0;
        final EntityEnderCrystal entityEnderCrystal = null;
        final float n2 = 0.0f;
        final BlockPos blockPos = null;
        final float n3 = 0.0f;
        final EntityPlayer entityPlayer = null;
        final EntityPlayer entityPlayer2 = null;
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)CatCrystal.mc.world.playerEntities.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer3 = iterator.next();
            if (EntityUtil.invalid((Entity)entityPlayer3, (double)this.targetRange.getValue())) {
                return;
            }
            if (n < (int)this.maxTarget.getValue()) {
                ++n;
                final Iterator iterator2 = CatCrystal.mc.world.loadedEntityList.iterator();
                if (!iterator2.hasNext()) {
                    if (CatCrystal.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL) || CatCrystal.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) || this != 0) {
                        final Iterator iterator3 = BlockUtil.getBox((float)this.crystalRange.getValue(), EntityUtil.getEntityPos((Entity)entityPlayer3).down()).iterator();
                        if (iterator3.hasNext()) {
                            final BlockPos blockPos2 = iterator3.next();
                            if (!BlockUtil.canPlaceCrystal(blockPos2)) {
                                return;
                            }
                            if (blockPos2 != null) {
                                return;
                            }
                            if (CatCrystal.mc.player.getDistance(blockPos2.getX() + 0.5, (double)blockPos2.getY(), blockPos2.getZ() + 0.5) > (double)this.placeRange.getValue()) {
                                return;
                            }
                            final float calculateDamage = CrystalUtil.calculateDamage(blockPos2.down(), (Entity)entityPlayer3, (int)this.predictTicks.getValue(), (boolean)this.collision.getValue(), (boolean)this.terrainIgnore.getValue());
                            final float calculateDamage2 = DamageUtil.calculateDamage(blockPos2.down(), (Entity)CatCrystal.mc.player);
                            if (calculateDamage2 > (double)this.placeMaxSelf.getValue()) {
                                return;
                            }
                            if ((double)this.antiSuicide.getValue() > 0.0 && calculateDamage2 > CatCrystal.mc.player.getHealth() + CatCrystal.mc.player.getAbsorptionAmount() - (double)this.antiSuicide.getValue()) {
                                return;
                            }
                            if (calculateDamage < this.getPlaceDamage(entityPlayer3)) {
                                return;
                            }
                            if (blockPos == null || calculateDamage > n3) {}
                        }
                    }
                    return;
                }
                final Entity entity = iterator2.next();
                if (!(entity instanceof EntityEnderCrystal)) {
                    return;
                }
                if (entityPlayer3.getDistance(entity) > (float)this.crystalRange.getValue()) {
                    return;
                }
                if (CatCrystal.mc.player.getDistance(entity) > (double)this.breakRange.getValue()) {
                    return;
                }
                if (!CatCrystal.mc.player.canEntityBeSeen(entity) && CatCrystal.mc.player.getDistance(entity) > (double)this.wallRange.getValue()) {
                    return;
                }
                final float calculateDamage3 = CrystalUtil.calculateDamage((EntityEnderCrystal)entity, (Entity)entityPlayer3, (int)this.predictTicks.getValue(), (boolean)this.collision.getValue(), (boolean)this.terrainIgnore.getValue());
                final float calculateDamage4 = DamageUtil.calculateDamage(entity, (Entity)CatCrystal.mc.player);
                if (calculateDamage4 > (double)this.breakMaxSelf.getValue()) {
                    return;
                }
                if ((double)this.antiSuicide.getValue() > 0.0 && calculateDamage4 > CatCrystal.mc.player.getHealth() + CatCrystal.mc.player.getAbsorptionAmount() - (double)this.antiSuicide.getValue()) {
                    return;
                }
                if (calculateDamage3 < this.getBreakDamage(entityPlayer3)) {
                    return;
                }
                if (entityEnderCrystal == null || calculateDamage3 > n2) {
                    final EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
                }
                return;
            }
        }
        if (blockPos == null && entityEnderCrystal == null) {
            CatCrystal.lastPos = null;
            this.displayTarget = null;
            return;
        }
        if (entityEnderCrystal == null) {
            this.displayTarget = entityPlayer;
            this.doPlace(blockPos);
            return;
        }
        if (blockPos == null) {
            this.displayTarget = entityPlayer2;
            this.doBreak(entityEnderCrystal, n2);
            return;
        }
        if (n2 >= n3 || n2 >= (double)this.preferBreakDamage.getValue()) {
            this.displayTarget = entityPlayer2;
            this.doBreak(entityEnderCrystal, n2);
        }
        else {
            this.displayTarget = entityPlayer;
            this.doPlace(blockPos);
        }
    }
    
    private boolean lambda$new$7(final Float n) {
        return this.page.getValue() == Pages.General;
    }
    
    private boolean lambda$new$13(final Double n) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    private boolean lambda$new$14(final Double n) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    private boolean lambda$new$12(final Number n) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.noUsing.isOpen() && this.page.getValue() == Pages.General;
    }
    
    @SubscribeEvent
    public void onMotion(final MotionEvent motionEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.update();
        if (CatCrystal.lastPos == null || !(boolean)this.rotate.getValue()) {
            return;
        }
        motionEvent.setYaw(this.lastYaw);
        motionEvent.setPitch(this.lastPitch);
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Pages.General;
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.update();
        if (this.nowBB != null && (boolean)this.render.getValue() && this.fadeUtils.easeOutQuad() < 1.0) {
            if (this.box.getValue()) {
                RenderUtil.drawBBFill(this.nowBB, (Color)this.color.getValue(), (int)((int)this.boxAlpha.getValue() * Math.abs(this.fadeUtils.easeOutQuad() - 1.0)));
            }
            if (this.outline.getValue()) {
                RenderUtil.drawBBBox(this.nowBB, (Color)this.color.getValue(), (int)((int)this.outlineAlpha.getValue() * Math.abs(this.fadeUtils.easeOutQuad() - 1.0)));
            }
            if ((boolean)this.text.getValue() && CatCrystal.lastPos != null) {
                RenderUtil.drawText(this.nowBB, String.valueOf(this.lastDamage));
            }
        }
    }
    
    private boolean lambda$new$19(final Integer n) {
        return this.page.getValue() == Pages.Break && (boolean)this.Break.getValue();
    }
    
    private boolean lambda$new$10(final Boolean b) {
        return this.page.getValue() == Pages.Place;
    }
    
    private boolean lambda$new$34(final Integer n) {
        return this.page.getValue() == Pages.Render && (boolean)this.render.getValue();
    }
    
    @Override
    public void onTick() {
        this.update();
    }
    
    private boolean lambda$new$37(final Boolean b) {
        return this.page.getValue() == Pages.Predict;
    }
    
    private double getBreakDamage(final EntityPlayer entityPlayer) {
        if ((boolean)this.slowFace.getValue() && this.faceTimer.passedMs((long)(int)this.slowDelay.getValue())) {
            return (double)this.slowMinDamage.getValue();
        }
        if (this.armorBreaker.getValue()) {
            final ItemStack getStack = entityPlayer.inventoryContainer.getSlot(5).getStack();
            final ItemStack getStack2 = entityPlayer.inventoryContainer.getSlot(6).getStack();
            final ItemStack getStack3 = entityPlayer.inventoryContainer.getSlot(7).getStack();
            final ItemStack getStack4 = entityPlayer.inventoryContainer.getSlot(8).getStack();
            if ((!getStack.isEmpty && EntityUtil.getDamagePercent(getStack) <= (int)this.maxDura.getValue()) || (!getStack2.isEmpty && EntityUtil.getDamagePercent(getStack2) <= (int)this.maxDura.getValue()) || (!getStack3.isEmpty && EntityUtil.getDamagePercent(getStack3) <= (int)this.maxDura.getValue()) || (!getStack4.isEmpty && EntityUtil.getDamagePercent(getStack4) <= (int)this.maxDura.getValue())) {
                return (double)this.armorBreakerDamage.getValue();
            }
        }
        return (double)this.breakMinDamage.getValue();
    }
    
    private boolean lambda$new$15(final SwapMode swapMode) {
        return this.page.getValue() == Pages.Place && (boolean)this.place.getValue();
    }
    
    public enum Pages
    {
        General("General", 0), 
        Place("Place", 1), 
        Misc("Misc", 3);
        
        private static final Pages[] $VALUES;
        
        Render("Render", 5), 
        Break("Break", 2), 
        Predict("Predict", 4);
        
        static {
            $VALUES = new Pages[] { Pages.General, Pages.Place, Pages.Break, Pages.Misc, Pages.Predict, Pages.Render };
        }
        
        private Pages(final String s, final int n) {
        }
    }
    
    public enum SwapMode
    {
        BYPASS("BYPASS", 3), 
        NORMAL("NORMAL", 1), 
        SILENT("SILENT", 2);
        
        private static final SwapMode[] $VALUES;
        
        OFF("OFF", 0);
        
        static {
            $VALUES = new SwapMode[] { SwapMode.OFF, SwapMode.NORMAL, SwapMode.SILENT, SwapMode.BYPASS };
        }
        
        private SwapMode(final String s, final int n) {
        }
    }
}
