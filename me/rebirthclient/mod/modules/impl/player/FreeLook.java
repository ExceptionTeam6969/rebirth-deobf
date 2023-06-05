//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.client.event.*;

public class FreeLook extends Module
{
    boolean enabled;
    private float dPitch;
    private float dYaw;
    public final Setting bind;
    
    @SubscribeEvent
    public void onTurn(final TurnEvent turnEvent) {
        if (FreeLook.mc.gameSettings.thirdPersonView > 0 && this.enabled) {
            this.dYaw += (float)(turnEvent.getYaw() * 0.15);
            this.dPitch -= (float)(turnEvent.getPitch() * 0.15);
            this.dPitch = MathHelper.clamp(this.dPitch, -90.0f, 90.0f);
            turnEvent.setCanceled(true);
        }
    }
    
    @Override
    public void onDisable() {
        this.enabled = false;
        FreeLook.mc.gameSettings.thirdPersonView = 0;
    }
    
    @Override
    public void onTick() {
        if (FreeLook.mc.currentScreen == null && ((Bind)this.bind.getValue()).isDown()) {
            if (!this.enabled) {
                this.dYaw = 0.0f;
                this.dPitch = 0.0f;
                FreeLook.mc.gameSettings.thirdPersonView = 1;
            }
            this.enabled = true;
        }
        else {
            if (this.enabled) {
                FreeLook.mc.gameSettings.thirdPersonView = 0;
            }
            this.enabled = false;
        }
        if (FreeLook.mc.gameSettings.thirdPersonView != 1 && this.enabled) {
            this.enabled = false;
            FreeLook.mc.gameSettings.thirdPersonView = 0;
        }
    }
    
    public FreeLook() {
        super("FreeLook", "Rotate your camera and not your player in 3rd person", Category.PLAYER);
        this.bind = this.add(new Setting("Bind", new Bind(-1)));
        this.enabled = false;
    }
    
    @SubscribeEvent
    public void onCameraSetup(final EntityViewRenderEvent.CameraSetup cameraSetup) {
        if (FreeLook.mc.gameSettings.thirdPersonView > 0 && this.enabled) {
            cameraSetup.setYaw(cameraSetup.getYaw() + this.dYaw);
            cameraSetup.setPitch(cameraSetup.getPitch() + this.dPitch);
        }
    }
}
