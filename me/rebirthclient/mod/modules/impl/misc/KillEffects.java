//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.effect.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class KillEffects extends Module
{
    private final Setting killSound;
    private final Timer timer;
    private final Setting oneself;
    private final Setting lightning;
    
    public KillEffects() {
        super("KillEffects", "jajaja hypixel mode", Category.MISC);
        this.lightning = this.add(new Setting("Lightning", Lightning.NORMAL));
        this.killSound = this.add(new Setting("KillSound", KillSound.OFF));
        this.oneself = this.add(new Setting("Oneself", true));
        this.timer = new Timer();
    }
    
    private SoundEvent getSound() {
        switch ((KillSound)this.killSound.getValue()) {
            case CS: {
                return new SoundEvent(new ResourceLocation("rebirth", "kill_sound_cs"));
            }
            case NEVERLOSE: {
                return new SoundEvent(new ResourceLocation("rebirth", "kill_sound_nl"));
            }
            case HYPIXEL: {
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public void onDeath(final EntityPlayer entityPlayer) {
        if (entityPlayer == null || (entityPlayer == KillEffects.mc.player && !(boolean)this.oneself.getValue()) || entityPlayer.getHealth() > 0.0f || KillEffects.mc.player.isDead || nullCheck() || fullNullCheck()) {
            return;
        }
        if (this.timer.passedMs(1500L)) {
            if (this.lightning.getValue() != Lightning.OFF) {
                KillEffects.mc.world.spawnEntity((Entity)new EntityLightningBolt((World)KillEffects.mc.world, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, true));
                if (this.lightning.getValue() == Lightning.NORMAL) {
                    KillEffects.mc.player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 0.5f, 1.0f);
                }
            }
            if (this.killSound.getValue() != KillSound.OFF) {
                final SoundEvent sound = this.getSound();
                if (sound != null) {
                    KillEffects.mc.player.playSound(sound, 1.0f, 1.0f);
                }
            }
            this.timer.reset();
        }
    }
    
    private enum KillSound
    {
        private static final KillSound[] $VALUES;
        
        HYPIXEL("HYPIXEL", 2), 
        NEVERLOSE("NEVERLOSE", 1), 
        CS("CS", 0), 
        OFF("OFF", 3);
        
        static {
            $VALUES = new KillSound[] { KillSound.CS, KillSound.NEVERLOSE, KillSound.HYPIXEL, KillSound.OFF };
        }
        
        private KillSound(final String s, final int n) {
        }
    }
    
    private enum Lightning
    {
        private static final Lightning[] $VALUES;
        
        NORMAL("NORMAL", 0), 
        OFF("OFF", 2), 
        SILENT("SILENT", 1);
        
        static {
            $VALUES = new Lightning[] { Lightning.NORMAL, Lightning.SILENT, Lightning.OFF };
        }
        
        private Lightning(final String s, final int n) {
        }
    }
}
