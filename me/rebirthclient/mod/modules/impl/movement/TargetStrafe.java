//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.events.impl.*;

public class TargetStrafe extends Module
{
    EntityPlayer strafeTarget;
    private final Setting addddd;
    private final Setting reversedDistance;
    private final Setting strafeBoost;
    private final Setting autoJump;
    private final Setting autoThirdPerson;
    private final Setting spd;
    int velocity;
    private final Setting drawRadius;
    private final Setting velocityUse;
    private final Setting boostTicks;
    private final Setting speedIfUsing;
    private final Setting speedIfPotion;
    private final Setting reduction;
    float speedy;
    private final Setting whenMoving;
    private final Setting potionSpeed;
    int boostticks;
    private final Setting smartStrafe;
    private final Setting targetRange;
    private final Setting range;
    private final Setting reversed;
    private float wrap;
    private final Setting usingItemCheck;
    private final Setting boostDecr;
    private boolean switchDir;
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)receive.getPacket()).getEntityID() == TargetStrafe.mc.player.getEntityId()) {
            final SPacketEntityVelocity sPacketEntityVelocity = (SPacketEntityVelocity)receive.getPacket();
            int getMotionX = sPacketEntityVelocity.getMotionX();
            int getMotionZ = sPacketEntityVelocity.getMotionZ();
            if (getMotionX < 0) {
                getMotionX *= -1;
            }
            if (getMotionZ < 0) {
                getMotionZ *= -1;
            }
            this.velocity = getMotionX + getMotionZ;
        }
    }
    
    private float toDegree(final double n, final double n2) {
        return (float)(Math.atan2(n2 - TargetStrafe.mc.player.posZ, n - TargetStrafe.mc.player.posX) * 180.0 / 3.141592653589793) - 90.0f;
    }
    
    @Override
    public void onEnable() {
        this.wrap = 0.0f;
        this.switchDir = true;
        Managers.TIMER.set(1.0f);
        this.velocity = 0;
    }
    
    public TargetStrafe() {
        super("TargetStrafe", "\u8899\u8909\u90aa\u8916\u90aa\u890c\u891c\u890b\u891f \u80c1\u82af\u6cfb\u8909\u890d\u8c10 \u8911\u68b0\u8c22\u61c8", Category.MOVEMENT);
        this.reversedDistance = this.add(new Setting("ReversedDistance", 3.0f, 1.0f, 6.0f));
        this.speedIfUsing = this.add(new Setting("Speed if using", 0.1f, 0.1f, 2.0f));
        this.range = this.add(new Setting("StrafeDistance", 2.4f, 0.1f, 6.0f));
        this.spd = this.add(new Setting("StrafeSpeed", 0.23f, 0.1f, 2.0f));
        this.reversed = this.add(new Setting("Reversed", false));
        this.whenMoving = this.add(new Setting("WhenMoving", true));
        this.autoJump = this.add(new Setting("AutoJump", true));
        this.smartStrafe = this.add(new Setting("SmartStrafe", true));
        this.usingItemCheck = this.add(new Setting("EatingSlowDown", false));
        this.speedIfPotion = this.add(new Setting("Speed if Potion ", true));
        this.potionSpeed = this.add(new Setting("PotionSpeed", 0.45f, 0.1f, 2.0f, this::lambda$new$0));
        this.autoThirdPerson = this.register(new Setting("AutoThirdPerson", Boolean.TRUE));
        this.targetRange = this.register(new Setting("TargetRange", 3.8f, 0.1f, 7.0f));
        this.drawRadius = this.add(new Setting("drawRadius", true));
        this.strafeBoost = this.add(new Setting("StrafeBoost", false));
        this.addddd = this.add(new Setting("add", false));
        this.reduction = this.add(new Setting("reduction", 2.0f, 1.0f, 5.0f));
        this.velocityUse = this.add(new Setting("velocityUse", 50000.0f, 0.1f, 100000.0f));
        this.boostTicks = this.register(new Setting("BoostTicks", 5, 0, 60));
        this.boostDecr = this.register(new Setting("BoostDecr", 5, 0, 5000));
        this.strafeTarget = null;
        this.boostticks = 0;
        this.speedy = 1.0f;
        this.velocity = 0;
        this.wrap = 0.0f;
        this.switchDir = true;
    }
    
    @SubscribeEvent
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Aura.target != null && (boolean)this.drawRadius.getValue()) {
            RenderUtil.drawCircle((float)Aura.target.posX - 0.5f, (float)(Aura.target.posY + 1.0), (float)Aura.target.posZ - 0.5f, (float)this.range.getValue(), new Color(255, 255, 255, 255));
        }
    }
    
    @Override
    public void onUpdate() {
        if ((boolean)this.whenMoving.getValue() && !MovementUtil.isMoving()) {
            return;
        }
        if (Aura.target != null) {
            if (!(Aura.target instanceof EntityPlayer)) {
                return;
            }
            this.strafeTarget = (EntityPlayer)Aura.target;
        }
        else {
            this.strafeTarget = null;
        }
    }
    
    @Override
    public void onDisable() {
        if (this.autoThirdPerson.getValue()) {
            TargetStrafe.mc.gameSettings.thirdPersonView = 0;
        }
    }
    
    private boolean lambda$new$0(final Float n) {
        return (boolean)this.speedIfPotion.getValue();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(final MotionEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.whenMoving:Lme/rebirthclient/mod/modules/settings/Setting;
        //     4: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //     7: checkcast       Ljava/lang/Boolean;
        //    10: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    13: ifeq            23
        //    16: invokestatic    me/rebirthclient/api/util/MovementUtil.isMoving:()Z
        //    19: ifne            23
        //    22: return         
        //    23: aload_0        
        //    24: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //    27: ifnonnull       31
        //    30: return         
        //    31: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //    34: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    37: aload_0        
        //    38: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //    41: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistanceSq:(Lnet/minecraft/entity/Entity;)D
        //    44: ldc2_w          0.2
        //    47: dcmpg          
        //    48: ifge            52
        //    51: return         
        //    52: aload_0        
        //    53: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.autoThirdPerson:Lme/rebirthclient/mod/modules/settings/Setting;
        //    56: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    59: checkcast       Ljava/lang/Boolean;
        //    62: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    65: ifeq            156
        //    68: aload_0        
        //    69: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //    72: invokevirtual   net/minecraft/entity/player/EntityPlayer.getHealth:()F
        //    75: fconst_0       
        //    76: fcmpl          
        //    77: ifle            146
        //    80: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //    83: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    86: aload_0        
        //    87: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //    90: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //    93: aload_0        
        //    94: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.targetRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //    97: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   100: checkcast       Ljava/lang/Float;
        //   103: invokevirtual   java/lang/Float.floatValue:()F
        //   106: fcmpg          
        //   107: ifgt            146
        //   110: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   113: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   116: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHealth:()F
        //   119: fconst_0       
        //   120: fcmpl          
        //   121: ifle            146
        //   124: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.INSTANCE:Lme/rebirthclient/mod/modules/impl/combat/Aura;
        //   127: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Aura.isOn:()Z
        //   130: ifeq            156
        //   133: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   136: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   139: iconst_1       
        //   140: putfield        net/minecraft/client/settings/GameSettings.thirdPersonView:I
        //   143: goto            156
        //   146: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   149: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   152: iconst_0       
        //   153: putfield        net/minecraft/client/settings/GameSettings.thirdPersonView:I
        //   156: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   159: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   162: aload_0        
        //   163: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   166: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   169: aload_0        
        //   170: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.targetRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //   173: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   176: checkcast       Ljava/lang/Float;
        //   179: invokevirtual   java/lang/Float.floatValue:()F
        //   182: fcmpg          
        //   183: ifgt            1064
        //   186: aload_0        
        //   187: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   190: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   193: fconst_0       
        //   194: fcmpl          
        //   195: ifle            244
        //   198: aload_0        
        //   199: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.autoJump:Lme/rebirthclient/mod/modules/settings/Setting;
        //   202: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   205: checkcast       Ljava/lang/Boolean;
        //   208: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   211: ifeq            244
        //   214: getstatic       me/rebirthclient/mod/modules/impl/combat/Aura.INSTANCE:Lme/rebirthclient/mod/modules/impl/combat/Aura;
        //   217: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Aura.isOn:()Z
        //   220: ifeq            244
        //   223: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   226: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   229: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   232: ifeq            244
        //   235: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   238: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   241: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.jump:()V
        //   244: aload_0        
        //   245: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   248: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   251: fconst_0       
        //   252: fcmpl          
        //   253: ifle            1064
        //   256: aload_0        
        //   257: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   260: astore_2       
        //   261: aload_2        
        //   262: ifnull          279
        //   265: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   268: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   271: getfield        net/minecraft/client/entity/EntityPlayerSP.ticksExisted:I
        //   274: bipush          20
        //   276: if_icmpge       280
        //   279: return         
        //   280: aload_0        
        //   281: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedIfPotion:Lme/rebirthclient/mod/modules/settings/Setting;
        //   284: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   287: checkcast       Ljava/lang/Boolean;
        //   290: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   293: ifeq            360
        //   296: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   299: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   302: ldc_w           "speed"
        //   305: invokestatic    net/minecraft/potion/Potion.getPotionFromResourceLocation:(Ljava/lang/String;)Lnet/minecraft/potion/Potion;
        //   308: invokestatic    java/util/Objects.requireNonNull:(Ljava/lang/Object;)Ljava/lang/Object;
        //   311: checkcast       Lnet/minecraft/potion/Potion;
        //   314: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isPotionActive:(Lnet/minecraft/potion/Potion;)Z
        //   317: ifeq            340
        //   320: aload_0        
        //   321: aload_0        
        //   322: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.potionSpeed:Lme/rebirthclient/mod/modules/settings/Setting;
        //   325: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   328: checkcast       Ljava/lang/Float;
        //   331: invokevirtual   java/lang/Float.floatValue:()F
        //   334: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedy:F
        //   337: goto            377
        //   340: aload_0        
        //   341: aload_0        
        //   342: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.spd:Lme/rebirthclient/mod/modules/settings/Setting;
        //   345: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   348: checkcast       Ljava/lang/Float;
        //   351: invokevirtual   java/lang/Float.floatValue:()F
        //   354: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedy:F
        //   357: goto            377
        //   360: aload_0        
        //   361: aload_0        
        //   362: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.spd:Lme/rebirthclient/mod/modules/settings/Setting;
        //   365: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   368: checkcast       Ljava/lang/Float;
        //   371: invokevirtual   java/lang/Float.floatValue:()F
        //   374: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedy:F
        //   377: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   380: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   383: getfield        net/minecraft/client/settings/GameSettings.keyBindUseItem:Lnet/minecraft/client/settings/KeyBinding;
        //   386: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //   389: ifeq            424
        //   392: aload_0        
        //   393: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.usingItemCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   396: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   399: checkcast       Ljava/lang/Boolean;
        //   402: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   405: ifeq            424
        //   408: aload_0        
        //   409: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedIfUsing:Lme/rebirthclient/mod/modules/settings/Setting;
        //   412: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   415: checkcast       Ljava/lang/Float;
        //   418: invokevirtual   java/lang/Float.floatValue:()F
        //   421: goto            428
        //   424: aload_0        
        //   425: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.speedy:F
        //   428: fstore_3       
        //   429: aload_0        
        //   430: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   433: i2f            
        //   434: aload_0        
        //   435: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocityUse:Lme/rebirthclient/mod/modules/settings/Setting;
        //   438: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   441: checkcast       Ljava/lang/Float;
        //   444: invokevirtual   java/lang/Float.floatValue:()F
        //   447: fcmpl          
        //   448: ifle            580
        //   451: aload_0        
        //   452: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeBoost:Lme/rebirthclient/mod/modules/settings/Setting;
        //   455: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   458: checkcast       Ljava/lang/Boolean;
        //   461: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   464: ifeq            580
        //   467: aload_0        
        //   468: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   471: ifge            479
        //   474: aload_0        
        //   475: iconst_0       
        //   476: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   479: aload_0        
        //   480: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.addddd:Lme/rebirthclient/mod/modules/settings/Setting;
        //   483: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   486: checkcast       Ljava/lang/Boolean;
        //   489: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   492: ifeq            524
        //   495: fload_3        
        //   496: aload_0        
        //   497: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   500: i2f            
        //   501: ldc_w           8000.0
        //   504: fdiv           
        //   505: aload_0        
        //   506: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.reduction:Lme/rebirthclient/mod/modules/settings/Setting;
        //   509: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   512: checkcast       Ljava/lang/Float;
        //   515: invokevirtual   java/lang/Float.floatValue:()F
        //   518: fdiv           
        //   519: fadd           
        //   520: fstore_3       
        //   521: goto            548
        //   524: aload_0        
        //   525: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   528: i2f            
        //   529: ldc_w           8000.0
        //   532: fdiv           
        //   533: aload_0        
        //   534: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.reduction:Lme/rebirthclient/mod/modules/settings/Setting;
        //   537: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   540: checkcast       Ljava/lang/Float;
        //   543: invokevirtual   java/lang/Float.floatValue:()F
        //   546: fdiv           
        //   547: fstore_3       
        //   548: aload_0        
        //   549: dup            
        //   550: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostticks:I
        //   553: iconst_1       
        //   554: iadd           
        //   555: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostticks:I
        //   558: aload_0        
        //   559: aload_0        
        //   560: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   563: aload_0        
        //   564: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostDecr:Lme/rebirthclient/mod/modules/settings/Setting;
        //   567: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   570: checkcast       Ljava/lang/Integer;
        //   573: invokevirtual   java/lang/Integer.intValue:()I
        //   576: isub           
        //   577: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   580: aload_0        
        //   581: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostticks:I
        //   584: aload_0        
        //   585: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostTicks:Lme/rebirthclient/mod/modules/settings/Setting;
        //   588: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   591: checkcast       Ljava/lang/Integer;
        //   594: invokevirtual   java/lang/Integer.intValue:()I
        //   597: if_icmplt       610
        //   600: aload_0        
        //   601: iconst_0       
        //   602: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.boostticks:I
        //   605: aload_0        
        //   606: iconst_0       
        //   607: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.velocity:I
        //   610: aload_0        
        //   611: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   614: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   617: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   620: aload_2        
        //   621: getfield        net/minecraft/entity/EntityLivingBase.posZ:D
        //   624: dsub           
        //   625: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   628: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   631: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   634: aload_2        
        //   635: getfield        net/minecraft/entity/EntityLivingBase.posX:D
        //   638: dsub           
        //   639: invokestatic    java/lang/Math.atan2:(DD)D
        //   642: d2f            
        //   643: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   646: aload_0        
        //   647: dup            
        //   648: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   651: aload_0        
        //   652: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.switchDir:Z
        //   655: ifeq            673
        //   658: fload_3        
        //   659: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   662: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   665: aload_2        
        //   666: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   669: fdiv           
        //   670: goto            686
        //   673: fload_3        
        //   674: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   677: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   680: aload_2        
        //   681: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   684: fdiv           
        //   685: fneg           
        //   686: fadd           
        //   687: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   690: aload_2        
        //   691: getfield        net/minecraft/entity/EntityLivingBase.posX:D
        //   694: aload_0        
        //   695: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   698: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   701: checkcast       Ljava/lang/Float;
        //   704: invokevirtual   java/lang/Float.floatValue:()F
        //   707: f2d            
        //   708: aload_0        
        //   709: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   712: f2d            
        //   713: invokestatic    java/lang/Math.cos:(D)D
        //   716: dmul           
        //   717: dadd           
        //   718: dstore          4
        //   720: aload_2        
        //   721: getfield        net/minecraft/entity/EntityLivingBase.posZ:D
        //   724: aload_0        
        //   725: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   728: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   731: checkcast       Ljava/lang/Float;
        //   734: invokevirtual   java/lang/Float.floatValue:()F
        //   737: f2d            
        //   738: aload_0        
        //   739: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   742: f2d            
        //   743: invokestatic    java/lang/Math.sin:(D)D
        //   746: dmul           
        //   747: dadd           
        //   748: dstore          6
        //   750: aload_0        
        //   751: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.smartStrafe:Lme/rebirthclient/mod/modules/settings/Setting;
        //   754: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   757: checkcast       Ljava/lang/Boolean;
        //   760: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   763: ifeq            896
        //   766: aload_0        
        //   767: dload           4
        //   769: dload           6
        //   771: ifne            896
        //   774: aload_0        
        //   775: aload_0        
        //   776: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.switchDir:Z
        //   779: ifne            786
        //   782: iconst_1       
        //   783: goto            787
        //   786: iconst_0       
        //   787: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.switchDir:Z
        //   790: aload_0        
        //   791: dup            
        //   792: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   795: fconst_2       
        //   796: aload_0        
        //   797: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.switchDir:Z
        //   800: ifeq            818
        //   803: fload_3        
        //   804: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   807: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   810: aload_2        
        //   811: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   814: fdiv           
        //   815: goto            831
        //   818: fload_3        
        //   819: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   822: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   825: aload_2        
        //   826: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   829: fdiv           
        //   830: fneg           
        //   831: fmul           
        //   832: fadd           
        //   833: putfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   836: aload_2        
        //   837: getfield        net/minecraft/entity/EntityLivingBase.posX:D
        //   840: aload_0        
        //   841: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   844: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   847: checkcast       Ljava/lang/Float;
        //   850: invokevirtual   java/lang/Float.floatValue:()F
        //   853: f2d            
        //   854: aload_0        
        //   855: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   858: f2d            
        //   859: invokestatic    java/lang/Math.cos:(D)D
        //   862: dmul           
        //   863: dadd           
        //   864: dstore          4
        //   866: aload_2        
        //   867: getfield        net/minecraft/entity/EntityLivingBase.posZ:D
        //   870: aload_0        
        //   871: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   874: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   877: checkcast       Ljava/lang/Float;
        //   880: invokevirtual   java/lang/Float.floatValue:()F
        //   883: f2d            
        //   884: aload_0        
        //   885: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.wrap:F
        //   888: f2d            
        //   889: invokestatic    java/lang/Math.sin:(D)D
        //   892: dmul           
        //   893: dadd           
        //   894: dstore          6
        //   896: aload_0        
        //   897: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.reversed:Lme/rebirthclient/mod/modules/settings/Setting;
        //   900: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   903: checkcast       Ljava/lang/Boolean;
        //   906: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   909: ifeq            948
        //   912: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   915: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   918: aload_0        
        //   919: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.strafeTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   922: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //   925: aload_0        
        //   926: getfield        me/rebirthclient/mod/modules/impl/movement/TargetStrafe.reversedDistance:Lme/rebirthclient/mod/modules/settings/Setting;
        //   929: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   932: checkcast       Ljava/lang/Float;
        //   935: invokevirtual   java/lang/Float.floatValue:()F
        //   938: fcmpg          
        //   939: ifge            948
        //   942: ldc_w           -90.0
        //   945: goto            949
        //   948: fconst_0       
        //   949: fstore          8
        //   951: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   954: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   957: getfield        net/minecraft/client/settings/GameSettings.keyBindLeft:Lnet/minecraft/client/settings/KeyBinding;
        //   960: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //   963: ifne            986
        //   966: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   969: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   972: getfield        net/minecraft/client/settings/GameSettings.keyBindRight:Lnet/minecraft/client/settings/KeyBinding;
        //   975: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //   978: ifne            986
        //   981: fload           8
        //   983: goto            987
        //   986: fconst_0       
        //   987: fstore          9
        //   989: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //   992: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   995: fload_3        
        //   996: f2d            
        //   997: aload_0        
        //   998: dload           4
        //  1000: fload           9
        //  1002: f2d            
        //  1003: dadd           
        //  1004: dload           6
        //  1006: fload           9
        //  1008: f2d            
        //  1009: dadd           
        //  1010: invokespecial   me/rebirthclient/mod/modules/impl/movement/TargetStrafe.toDegree:(DD)F
        //  1013: f2d            
        //  1014: invokestatic    java/lang/Math.toRadians:(D)D
        //  1017: d2f            
        //  1018: f2d            
        //  1019: invokestatic    java/lang/Math.sin:(D)D
        //  1022: dneg           
        //  1023: dmul           
        //  1024: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //  1027: getstatic       me/rebirthclient/mod/modules/impl/movement/TargetStrafe.mc:Lnet/minecraft/client/Minecraft;
        //  1030: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1033: fload_3        
        //  1034: f2d            
        //  1035: aload_0        
        //  1036: dload           4
        //  1038: fload           9
        //  1040: f2d            
        //  1041: dadd           
        //  1042: dload           6
        //  1044: fload           9
        //  1046: f2d            
        //  1047: dadd           
        //  1048: invokespecial   me/rebirthclient/mod/modules/impl/movement/TargetStrafe.toDegree:(DD)F
        //  1051: f2d            
        //  1052: invokestatic    java/lang/Math.toRadians:(D)D
        //  1055: d2f            
        //  1056: f2d            
        //  1057: invokestatic    java/lang/Math.cos:(D)D
        //  1060: dmul           
        //  1061: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //  1064: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0896 (coming from #0771).
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
}
