//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.init.*;

public class AntiGlide extends Module
{
    private final Setting ice;
    private final Setting onGround;
    
    @Override
    public void onUpdate() {
        if (HoleSnap.INSTANCE.isOn() || AutoCenter.INSTANCE.isOn()) {
            return;
        }
        if ((boolean)this.onGround.getValue() && !AntiGlide.mc.player.onGround) {
            return;
        }
        final MovementInput movementInput = AntiGlide.mc.player.movementInput;
        if (movementInput.moveForward == 0.0 && movementInput.moveStrafe == 0.0) {
            AntiGlide.mc.player.motionX = 0.0;
            AntiGlide.mc.player.motionZ = 0.0;
        }
        if ((boolean)this.ice.getValue() && AntiGlide.mc.player.getRidingEntity() == null) {
            this.setIceSlipperiness(0.6f);
        }
        else {
            this.setIceSlipperiness(0.98f);
        }
    }
    
    @Override
    public void onDisable() {
        this.setIceSlipperiness(0.98f);
    }
    
    public AntiGlide() {
        super("AntiGlide", "Prevents inertial moving", Category.MOVEMENT);
        this.onGround = this.add(new Setting("OnGround", true));
        this.ice = this.add(new Setting("Ice", true));
    }
    
    private void setIceSlipperiness(final float defaultSlipperiness) {
        Blocks.ICE.setDefaultSlipperiness(defaultSlipperiness);
        Blocks.FROSTED_ICE.setDefaultSlipperiness(defaultSlipperiness);
        Blocks.PACKED_ICE.setDefaultSlipperiness(defaultSlipperiness);
    }
}
