//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class CombatSetting extends Module
{
    public final Setting strictPlace;
    public static final Timer timer;
    public final Setting resetRotation;
    public final Setting resetPosition;
    public final Setting checkRaytrace;
    public final Setting attackDelay;
    public static Vec3d vec;
    private final Setting rotateTimer;
    public static CombatSetting INSTANCE;
    
    static {
        timer = new Timer();
    }
    
    private void faceVector(final Vec3d vec3d, final MotionEvent motionEvent) {
        final float[] legitRotations = EntityUtil.getLegitRotations(vec3d);
        motionEvent.setRotation(legitRotations[0], legitRotations[1]);
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.strictPlace.isOpen();
    }
    
    @SubscribeEvent
    public final void onMotion(final MotionEvent motionEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!CombatSetting.timer.passedMs((long)(int)this.rotateTimer.getValue()) && CombatSetting.vec != null) {
            this.faceVector(CombatSetting.vec, motionEvent);
        }
    }
    
    public CombatSetting() {
        super("CombatSetting", "idk", Category.COMBAT);
        this.strictPlace = this.add(new Setting("StrictPlace", false).setParent());
        this.checkRaytrace = this.add(new Setting("CheckRaytrace", false, this::lambda$new$0));
        this.resetRotation = this.add(new Setting("ResetRotation", false));
        this.resetPosition = this.add(new Setting("ResetPosition", false));
        this.attackDelay = this.add(new Setting("AttackDelay", 300, 0, 1000));
        this.rotateTimer = this.add(new Setting("RotateTimer", 300, 0, 1000));
        CombatSetting.INSTANCE = this;
    }
}
