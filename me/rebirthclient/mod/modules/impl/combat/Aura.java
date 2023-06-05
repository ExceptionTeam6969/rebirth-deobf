//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.inventory.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class Aura extends Module
{
    private final Setting onlyGhasts;
    private final Setting weaponOnly;
    public static Entity target;
    private final Setting neutrals;
    private final Setting yawStep;
    private final Setting maxCps;
    private final Timer timer;
    private final Setting whileEating;
    private final Setting rotate;
    private final Setting oneEight;
    private final Setting others;
    private final Setting targetColor;
    private final Setting fovCheck;
    private final Setting multi32k;
    private final Setting wallRange;
    public final Setting range;
    private final Setting randomPitch;
    private final Setting tpsSync;
    private final Setting angle;
    public static Aura INSTANCE;
    private final Setting randomDelay;
    private final Setting hostiles;
    private final Setting time32k;
    private final Setting render;
    private final Setting delay32k;
    private final Setting targetMode;
    private final Setting packetAmount32k;
    private final Setting lookBack;
    private final Setting amplitude;
    private final Setting animals;
    private final Setting swing;
    private final Setting minCps;
    private final Setting pitchAdd;
    private final Setting packet;
    private final Setting armorBreak;
    private final Setting players;
    private final Setting page;
    private final Setting stopSprint;
    private final Setting sneak;
    private final Setting projectiles;
    
    private void teekayAttack(final Entity entity) {
        int n = 0;
        if (n < (int)this.packetAmount32k.getValue()) {
            this.startEntityAttackThread(entity, n * (int)this.time32k.getValue());
            ++n;
        }
    }
    
    private boolean lambda$new$28(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    private void lambda$startEntityAttackThread$36(final int n, final Entity entity) {
        new Timer().reset();
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        Managers.INTERACTIONS.attackEntity(entity, true, (boolean)this.swing.getValue());
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0 && (boolean)this.rotate.getValue() && Aura.target != null) {
            final float[] injectYawStep = Managers.ROTATIONS.injectYawStep(MathUtil.calcAngle(Aura.mc.player.getPositionEyes(Aura.mc.getRenderPartialTicks()), Aura.target.getPositionEyes(Aura.mc.getRenderPartialTicks())), (float)this.yawStep.getValue());
            Managers.ROTATIONS.setRotations(injectYawStep[0], injectYawStep[1] + (float)this.pitchAdd.getValue() + (this.randomPitch.getValue() ? ((float)Math.random() * (float)this.amplitude.getValue()) : 0.0f));
        }
        this.doAura();
        if ((boolean)this.rotate.getValue() && (boolean)this.lookBack.getValue()) {
            Managers.ROTATIONS.resetRotations();
        }
    }
    
    private boolean lambda$new$8(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.rotate.isOpen() && (boolean)this.randomPitch.getValue();
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$6(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.rotate.isOpen();
    }
    
    private boolean lambda$new$14(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.fovCheck.isOpen();
    }
    
    private boolean lambda$new$32(final Boolean b) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$13(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$30(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    private boolean lambda$new$22(final Boolean b) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$11(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.oneEight.isOpen();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Aura.target != null) {
            if (this.render.getValue() == RenderMode.OLD) {
                RenderUtil.drawEntityBoxESP(Aura.target, (Color)this.targetColor.getValue(), true, new Color(255, 255, 255, 130), 0.7f, true, true, 35);
            }
            else if (this.render.getValue() == RenderMode.JELLO) {
                final double n = 1500.0;
                final double n2 = System.currentTimeMillis() % n;
                final boolean b = n2 > n / 2.0;
                final double n3 = n2 / (n / 2.0);
                double n4;
                if (!b) {
                    n4 = 1.0 - n3;
                }
                else {
                    n4 = n3 - 1.0;
                }
                final double easeInOutQuad = this.easeInOutQuad(n4);
                Aura.mc.entityRenderer.disableLightmap();
                GL11.glPushMatrix();
                GL11.glDisable(3553);
                GL11.glBlendFunc(770, 771);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GL11.glDisable(2929);
                GL11.glDisable(2884);
                GL11.glShadeModel(7425);
                Aura.mc.entityRenderer.disableLightmap();
                final double n5 = Aura.target.width;
                final double n6 = Aura.target.height + 0.1;
                final double n7 = Aura.target.lastTickPosX + (Aura.target.posX - Aura.target.lastTickPosX) * Aura.mc.getRenderPartialTicks() - Aura.mc.renderManager.viewerPosX;
                final double n8 = Aura.target.lastTickPosY + (Aura.target.posY - Aura.target.lastTickPosY) * Aura.mc.getRenderPartialTicks() - Aura.mc.renderManager.viewerPosY + n6 * easeInOutQuad;
                final double n9 = Aura.target.lastTickPosZ + (Aura.target.posZ - Aura.target.lastTickPosZ) * Aura.mc.getRenderPartialTicks() - Aura.mc.renderManager.viewerPosZ;
                final double n10 = n6 / 3.0 * ((easeInOutQuad > 0.5) ? (1.0 - easeInOutQuad) : easeInOutQuad) * (b ? -1 : 1);
                int n11 = 0;
                if (n11 < 360) {
                    final Color color = (Color)this.targetColor.getValue();
                    final double n12 = n7 - Math.sin(n11 * 3.141592653589793 / 180.0) * n5;
                    final double n13 = n9 + Math.cos(n11 * 3.141592653589793 / 180.0) * n5;
                    final double n14 = n7 - Math.sin((n11 - 5) * 3.141592653589793 / 180.0) * n5;
                    final double n15 = n9 + Math.cos((n11 - 5) * 3.141592653589793 / 180.0) * n5;
                    GL11.glBegin(7);
                    GL11.glColor4f(ColorUtil.pulseColor(color, 200, 1).getRed() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getGreen() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getBlue() / 255.0f, 0.0f);
                    GL11.glVertex3d(n12, n8 + n10, n13);
                    GL11.glVertex3d(n14, n8 + n10, n15);
                    GL11.glColor4f(ColorUtil.pulseColor(color, 200, 1).getRed() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getGreen() / 255.0f, ColorUtil.pulseColor(color, 200, 1).getBlue() / 255.0f, 200.0f);
                    GL11.glVertex3d(n14, n8, n15);
                    GL11.glVertex3d(n12, n8, n13);
                    GL11.glEnd();
                    GL11.glBegin(2);
                    GL11.glVertex3d(n14, n8, n15);
                    GL11.glVertex3d(n12, n8, n13);
                    GL11.glEnd();
                    n11 += 5;
                    return;
                }
                GL11.glEnable(2884);
                GL11.glShadeModel(7424);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glEnable(2929);
                GL11.glDisable(2848);
                GL11.glDisable(3042);
                GL11.glEnable(3553);
                GL11.glPopMatrix();
            }
        }
    }
    
    private boolean lambda$new$7(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL && this.rotate.isOpen();
    }
    
    private boolean lambda$new$12(final Float n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$29(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    @Override
    public String getInfo() {
        return Managers.TEXT.normalizeCases(this.targetMode.getValue()) + ((Aura.target instanceof EntityPlayer) ? (", " + Aura.target.getName()) : "");
    }
    
    private boolean lambda$new$23(final RenderMode renderMode) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private void doAura() {
        if ((boolean)this.weaponOnly.getValue() && !EntityUtil.isHoldingWeapon((EntityPlayer)Aura.mc.player)) {
            Aura.target = null;
            return;
        }
        if (!this.timer.passedMs((long)(((boolean)this.oneEight.getValue() || (EntityUtil.isHolding32k((EntityPlayer)Aura.mc.player) && !(boolean)this.delay32k.getValue())) ? ((int)(MathUtil.randomBetween((float)this.minCps.getValue(), (float)this.maxCps.getValue()) - new Random().nextInt(10) + new Random().nextInt(10) * 100 * (this.tpsSync.getValue() ? Managers.SERVER.getTpsFactor() : 1.0f))) : ((int)(EntityUtil.getHitCoolDown((EntityPlayer)Aura.mc.player) + (float)Math.random() * (float)this.randomDelay.getValue() * 100.0f * (this.tpsSync.getValue() ? Managers.SERVER.getTpsFactor() : 1.0f))))) || (!(boolean)this.whileEating.getValue() && Aura.mc.player.isHandActive() && (!Aura.mc.player.getHeldItemOffhand().getItem().equals(Items.SHIELD) || Aura.mc.player.getActiveHand() != EnumHand.OFF_HAND))) {
            return;
        }
        Aura.target = this.getTarget();
        if (Aura.target == null) {
            return;
        }
        if (EntityUtil.isHolding32k((EntityPlayer)Aura.mc.player) && !(boolean)this.delay32k.getValue()) {
            if (this.multi32k.getValue()) {
                final Iterator<EntityPlayer> iterator = Aura.mc.world.playerEntities.iterator();
                if (iterator.hasNext()) {
                    final EntityPlayer entityPlayer = iterator.next();
                    if (EntityUtil.isValid((Entity)entityPlayer, (double)(float)this.range.getValue())) {
                        this.teekayAttack((Entity)entityPlayer);
                    }
                    return;
                }
            }
            else {
                this.teekayAttack(Aura.target);
            }
            this.timer.reset();
            return;
        }
        if (this.armorBreak.getValue()) {
            Aura.mc.playerController.windowClick(Aura.mc.player.inventoryContainer.windowId, 9, Aura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Aura.mc.player);
            Managers.INTERACTIONS.attackEntity(Aura.target, (boolean)this.packet.getValue(), (boolean)this.swing.getValue());
            Aura.mc.playerController.windowClick(Aura.mc.player.inventoryContainer.windowId, 9, Aura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Aura.mc.player);
            Managers.INTERACTIONS.attackEntity(Aura.target, (boolean)this.packet.getValue(), (boolean)this.swing.getValue());
        }
        else {
            final boolean isSneaking = SneakManager.isSneaking;
            final boolean isSprinting = Aura.mc.player.isSprinting();
            if (this.sneak.getValue()) {
                if (isSneaking) {
                    Aura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Aura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                if (isSprinting) {
                    Aura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Aura.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                }
            }
            Managers.INTERACTIONS.attackEntity(Aura.target, (boolean)this.packet.getValue(), (boolean)this.swing.getValue());
            if (this.sneak.getValue()) {
                if (isSprinting) {
                    Aura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Aura.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (isSneaking) {
                    Aura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Aura.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
            }
            if (this.stopSprint.getValue()) {
                Aura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Aura.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
        }
        this.timer.reset();
    }
    
    private boolean lambda$new$35(final Boolean b) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$24(final Color color) {
        return this.page.getValue() == Page.GLOBAL && this.render.getValue() != RenderMode.OFF;
    }
    
    private boolean lambda$new$5(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.rotate.isOpen();
    }
    
    private boolean lambda$new$16(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$27(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    private boolean lambda$new$19(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    public Aura() {
        super("KnifeBot", "Attacks entities in radius", Category.COMBAT);
        this.page = this.add(new Setting("Settings", Page.GLOBAL));
        this.range = this.add(new Setting("Range", 6.0f, 0.1f, 7.0f, this::lambda$new$0));
        this.targetMode = this.add(new Setting("Filter", TargetMode.DISTANCE, this::lambda$new$1));
        this.wallRange = this.add(new Setting("WallRange", 3.0f, 0.1f, 7.0f, this::lambda$new$2));
        this.rotate = this.add(new Setting("Rotate", true, this::lambda$new$3).setParent());
        this.lookBack = this.add(new Setting("LookBack", true, this::lambda$new$4));
        this.yawStep = this.add(new Setting("YawStep", 0.3f, 0.1f, 1.0f, this::lambda$new$5));
        this.pitchAdd = this.add(new Setting("PitchAdd", 0.0f, 0.0f, 25.0f, this::lambda$new$6));
        this.randomPitch = this.add(new Setting("RandomizePitch", false, this::lambda$new$7));
        this.amplitude = this.add(new Setting("Amplitude", 3.0f, -5.0f, 5.0f, this::lambda$new$8));
        this.oneEight = this.add(new Setting("OneEight", false, this::lambda$new$9).setParent());
        this.minCps = this.add(new Setting("MinCps", 6.0f, 0.0f, 20.0f, this::lambda$new$10));
        this.maxCps = this.add(new Setting("MaxCps", 9.0f, 0.0f, 20.0f, this::lambda$new$11));
        this.randomDelay = this.add(new Setting("RandomDelay", 0.0f, 0.0f, 5.0f, this::lambda$new$12));
        this.fovCheck = this.add(new Setting("FovCheck", false, this::lambda$new$13).setParent());
        this.angle = this.add(new Setting("Angle", 180.0f, 0.0f, 180.0f, this::lambda$new$14));
        this.stopSprint = this.add(new Setting("StopSprint", true, this::lambda$new$15));
        this.armorBreak = this.add(new Setting("ArmorBreak", false, this::lambda$new$16));
        this.whileEating = this.add(new Setting("WhileEating", true, this::lambda$new$17));
        this.weaponOnly = this.add(new Setting("WeaponOnly", true, this::lambda$new$18));
        this.tpsSync = this.add(new Setting("TpsSync", true, this::lambda$new$19));
        this.packet = this.add(new Setting("Packet", false, this::lambda$new$20));
        this.swing = this.add(new Setting("Swing", true, this::lambda$new$21));
        this.sneak = this.add(new Setting("Sneak", false, this::lambda$new$22));
        this.render = this.add(new Setting("Render", RenderMode.JELLO, this::lambda$new$23));
        this.targetColor = this.add(new Setting("TargetColor", new Color(255, 255, 255, 255), this::lambda$new$24));
        this.players = this.add(new Setting("Players", true, this::lambda$new$25));
        this.animals = this.add(new Setting("Animals", false, this::lambda$new$26));
        this.neutrals = this.add(new Setting("Neutrals", false, this::lambda$new$27));
        this.others = this.add(new Setting("Others", false, this::lambda$new$28));
        this.projectiles = this.add(new Setting("Projectiles", false, this::lambda$new$29));
        this.hostiles = this.add(new Setting("Hostiles", true, this::lambda$new$30).setParent());
        this.onlyGhasts = this.add(new Setting("OnlyGhasts", false, this::lambda$new$31));
        this.delay32k = this.add(new Setting("32kDelay", false, this::lambda$new$32));
        this.packetAmount32k = this.add(new Setting("32kPackets", 2, this::lambda$new$33));
        this.time32k = this.add(new Setting("32kTime", 5, 1, 50, this::lambda$new$34));
        this.multi32k = this.add(new Setting("Multi32k", false, this::lambda$new$35));
        this.timer = new Timer();
        Aura.INSTANCE = this;
    }
    
    private boolean lambda$new$17(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$33(final Integer n) {
        return !(boolean)this.delay32k.getValue() && this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$18(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private void startEntityAttackThread(final Entity entity, final int n) {
        new Thread(this::lambda$startEntityAttackThread$36).start();
    }
    
    private double easeInOutQuad(final double n) {
        return (n < 0.5) ? (2.0 * n * n) : (1.0 - Math.pow(-2.0 * n + 2.0, 2.0) / 2.0);
    }
    
    private boolean lambda$new$21(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$1(final TargetMode targetMode) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$25(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    private boolean lambda$new$20(final Boolean b) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$31(final Boolean b) {
        return this.hostiles.isOpen() && this.page.getValue() == Page.TARGETS;
    }
    
    private Entity getTarget() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: dconst_0       
        //     3: dstore_2       
        //     4: ldc2_w          36.0
        //     7: dstore          4
        //     9: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //    12: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    15: getfield        net/minecraft/client/multiplayer/WorldClient.loadedEntityList:Ljava/util/List;
        //    18: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    23: astore          6
        //    25: aload           6
        //    27: invokeinterface java/util/Iterator.hasNext:()Z
        //    32: ifeq            514
        //    35: aload           6
        //    37: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    42: checkcast       Lnet/minecraft/entity/Entity;
        //    45: astore          7
        //    47: aload_0        
        //    48: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.players:Lme/rebirthclient/mod/modules/settings/Setting;
        //    51: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    54: checkcast       Ljava/lang/Boolean;
        //    57: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    60: ifeq            71
        //    63: aload           7
        //    65: instanceof      Lnet/minecraft/entity/player/EntityPlayer;
        //    68: ifne            231
        //    71: aload_0        
        //    72: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.animals:Lme/rebirthclient/mod/modules/settings/Setting;
        //    75: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    78: checkcast       Ljava/lang/Boolean;
        //    81: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    84: ifeq            95
        //    87: aload           7
        //    89: invokestatic    me/rebirthclient/api/util/EntityUtil.isPassive:(Lnet/minecraft/entity/Entity;)Z
        //    92: ifne            231
        //    95: aload_0        
        //    96: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.neutrals:Lme/rebirthclient/mod/modules/settings/Setting;
        //    99: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   102: checkcast       Ljava/lang/Boolean;
        //   105: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   108: ifeq            119
        //   111: aload           7
        //   113: invokestatic    me/rebirthclient/api/util/EntityUtil.isNeutralMob:(Lnet/minecraft/entity/Entity;)Z
        //   116: ifne            231
        //   119: aload_0        
        //   120: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.hostiles:Lme/rebirthclient/mod/modules/settings/Setting;
        //   123: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   126: checkcast       Ljava/lang/Boolean;
        //   129: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   132: ifeq            143
        //   135: aload           7
        //   137: invokestatic    me/rebirthclient/api/util/EntityUtil.isMobAggressive:(Lnet/minecraft/entity/Entity;)Z
        //   140: ifne            231
        //   143: aload_0        
        //   144: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.hostiles:Lme/rebirthclient/mod/modules/settings/Setting;
        //   147: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   150: checkcast       Ljava/lang/Boolean;
        //   153: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   156: ifeq            183
        //   159: aload_0        
        //   160: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.onlyGhasts:Lme/rebirthclient/mod/modules/settings/Setting;
        //   163: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   166: checkcast       Ljava/lang/Boolean;
        //   169: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   172: ifeq            183
        //   175: aload           7
        //   177: instanceof      Lnet/minecraft/entity/monster/EntityGhast;
        //   180: ifne            231
        //   183: aload_0        
        //   184: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.others:Lme/rebirthclient/mod/modules/settings/Setting;
        //   187: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   190: checkcast       Ljava/lang/Boolean;
        //   193: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   196: ifeq            207
        //   199: aload           7
        //   201: invokestatic    me/rebirthclient/api/util/EntityUtil.isVehicle:(Lnet/minecraft/entity/Entity;)Z
        //   204: ifne            231
        //   207: aload_0        
        //   208: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.projectiles:Lme/rebirthclient/mod/modules/settings/Setting;
        //   211: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   214: checkcast       Ljava/lang/Boolean;
        //   217: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   220: ifeq            512
        //   223: aload           7
        //   225: invokestatic    me/rebirthclient/api/util/EntityUtil.isProjectile:(Lnet/minecraft/entity/Entity;)Z
        //   228: ifeq            512
        //   231: aload           7
        //   233: aload_0        
        //   234: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   237: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   240: checkcast       Ljava/lang/Float;
        //   243: invokevirtual   java/lang/Float.floatValue:()F
        //   246: f2d            
        //   247: invokestatic    me/rebirthclient/api/util/EntityUtil.invalid:(Lnet/minecraft/entity/Entity;D)Z
        //   250: ifeq            255
        //   253: aconst_null    
        //   254: areturn        
        //   255: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   258: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   261: aload           7
        //   263: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.canEntityBeSeen:(Lnet/minecraft/entity/Entity;)Z
        //   266: ifne            311
        //   269: aload           7
        //   271: invokestatic    me/rebirthclient/api/util/EntityUtil.isFeetVisible:(Lnet/minecraft/entity/Entity;)Z
        //   274: ifne            311
        //   277: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   280: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   283: aload           7
        //   285: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistanceSq:(Lnet/minecraft/entity/Entity;)D
        //   288: aload_0        
        //   289: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.wallRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //   292: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   295: checkcast       Ljava/lang/Float;
        //   298: invokevirtual   java/lang/Float.floatValue:()F
        //   301: f2d            
        //   302: invokestatic    me/rebirthclient/api/util/math/MathUtil.square:(D)D
        //   305: dcmpl          
        //   306: ifle            311
        //   309: aconst_null    
        //   310: areturn        
        //   311: aload_0        
        //   312: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.fovCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   315: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   318: checkcast       Ljava/lang/Boolean;
        //   321: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   324: ifeq            349
        //   327: aload_0        
        //   328: aload           7
        //   330: aload_0        
        //   331: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.angle:Lme/rebirthclient/mod/modules/settings/Setting;
        //   334: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   337: checkcast       Ljava/lang/Float;
        //   340: invokevirtual   java/lang/Float.intValue:()I
        //   343: i2f            
        //   344: ifle            349
        //   347: aconst_null    
        //   348: areturn        
        //   349: aload_1        
        //   350: ifnonnull       380
        //   353: aload           7
        //   355: astore_1       
        //   356: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   359: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   362: aload           7
        //   364: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   367: f2d            
        //   368: dstore_2       
        //   369: aload           7
        //   371: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   374: f2d            
        //   375: dstore          4
        //   377: goto            512
        //   380: aload           7
        //   382: instanceof      Lnet/minecraft/entity/player/EntityPlayer;
        //   385: ifeq            407
        //   388: aload           7
        //   390: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //   393: bipush          10
        //   395: invokestatic    me/rebirthclient/api/util/EntityUtil.isArmorLow:(Lnet/minecraft/entity/player/EntityPlayer;I)Z
        //   398: ifeq            407
        //   401: aload           7
        //   403: astore_1       
        //   404: goto            514
        //   407: aload_0        
        //   408: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.targetMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //   411: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   414: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura$TargetMode.HEALTH:Lme/rebirthclient/mod/modules/impl/combat/Aura$TargetMode;
        //   417: if_acmpne       458
        //   420: aload           7
        //   422: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   425: f2d            
        //   426: dload           4
        //   428: dcmpg          
        //   429: ifge            458
        //   432: aload           7
        //   434: astore_1       
        //   435: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   438: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   441: aload           7
        //   443: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   446: f2d            
        //   447: dstore_2       
        //   448: aload           7
        //   450: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   453: f2d            
        //   454: dstore          4
        //   456: aconst_null    
        //   457: areturn        
        //   458: aload_0        
        //   459: getfield        me/rebirthclient/mod/modules/impl/combat/Aura.targetMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //   462: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   465: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura$TargetMode.DISTANCE:Lme/rebirthclient/mod/modules/impl/combat/Aura$TargetMode;
        //   468: if_acmpne       512
        //   471: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   474: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   477: aload           7
        //   479: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   482: f2d            
        //   483: dload_2        
        //   484: dcmpg          
        //   485: ifge            512
        //   488: aload           7
        //   490: astore_1       
        //   491: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.mc:Lnet/minecraft/client/Minecraft;
        //   494: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   497: aload           7
        //   499: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   502: f2d            
        //   503: dstore_2       
        //   504: aload           7
        //   506: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   509: f2d            
        //   510: dstore          4
        //   512: aconst_null    
        //   513: areturn        
        //   514: aload_1        
        //   515: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0349 (coming from #0344).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL && this.rotate.isOpen();
    }
    
    @Override
    public void onTick() {
        if (Aura.target != null && EntityUtil.invalid(Aura.target, (double)(float)this.range.getValue())) {
            Aura.target = null;
        }
        if (!(boolean)this.rotate.getValue()) {
            this.doAura();
        }
        if ((float)this.maxCps.getValue() < (float)this.minCps.getValue()) {
            this.maxCps.setValue(this.minCps.getValue());
        }
    }
    
    private boolean lambda$new$15(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$34(final Integer n) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$26(final Boolean b) {
        return this.page.getValue() == Page.TARGETS;
    }
    
    private boolean lambda$new$10(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.oneEight.isOpen();
    }
    
    private enum TargetMode
    {
        HEALTH("HEALTH", 1), 
        DISTANCE("DISTANCE", 0);
        
        private static final TargetMode[] $VALUES;
        
        private TargetMode(final String s, final int n) {
        }
        
        static {
            $VALUES = new TargetMode[] { TargetMode.DISTANCE, TargetMode.HEALTH };
        }
    }
    
    private enum RenderMode
    {
        JELLO("JELLO", 1);
        
        private static final RenderMode[] $VALUES;
        
        OFF("OFF", 2), 
        OLD("OLD", 0);
        
        static {
            $VALUES = new RenderMode[] { RenderMode.OLD, RenderMode.JELLO, RenderMode.OFF };
        }
        
        private RenderMode(final String s, final int n) {
        }
    }
    
    private enum Page
    {
        private static final Page[] $VALUES;
        
        GLOBAL("GLOBAL", 0), 
        ADVANCED("ADVANCED", 2), 
        TARGETS("TARGETS", 1);
        
        static {
            $VALUES = new Page[] { Page.GLOBAL, Page.TARGETS, Page.ADVANCED };
        }
        
        private Page(final String s, final int n) {
        }
    }
}
