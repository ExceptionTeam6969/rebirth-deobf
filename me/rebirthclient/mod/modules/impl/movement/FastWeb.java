//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.client.entity.*;

public class FastWeb extends Module
{
    private final Setting fastSpeed;
    private final Setting mode;
    private final Setting onlySneak;
    
    public FastWeb() {
        super("FastWeb", "So you don't need to keep timer on keybind", Category.MOVEMENT);
        this.mode = this.add(new Setting("Mode", Mode.FAST));
        this.fastSpeed = this.add(new Setting("FastSpeed", 3.0f, 0.0f, 5.0f, this::lambda$new$0));
        this.onlySneak = this.add(new Setting("OnlySneak", false));
    }
    
    @Override
    public void onUpdate() {
        if (FastWeb.mc.player.isInWeb) {
            if ((this.mode.getValue() == Mode.FAST && FastWeb.mc.gameSettings.keyBindSneak.isKeyDown()) || !(boolean)this.onlySneak.getValue()) {
                Managers.TIMER.reset();
                final EntityPlayerSP player = FastWeb.mc.player;
                player.motionY -= (float)this.fastSpeed.getValue();
            }
            else if ((this.mode.getValue() == Mode.STRICT && !FastWeb.mc.player.onGround && FastWeb.mc.gameSettings.keyBindSneak.isKeyDown()) || !(boolean)this.onlySneak.getValue()) {
                Managers.TIMER.set(8.0f);
            }
            else if ((ElytraFly.INSTANCE.isOff() || !(boolean)ElytraFly.INSTANCE.boostTimer.getValue()) && !NewStep.timer) {
                Managers.TIMER.reset();
            }
        }
        else if ((ElytraFly.INSTANCE.isOff() || !(boolean)ElytraFly.INSTANCE.boostTimer.getValue()) && !NewStep.timer) {
            Managers.TIMER.reset();
        }
    }
    
    @Override
    public void onDisable() {
        Managers.TIMER.reset();
    }
    
    @Override
    public String getInfo() {
        return Managers.TEXT.normalizeCases(this.mode.getValue());
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.mode.getValue() == Mode.FAST;
    }
    
    private enum Mode
    {
        STRICT("STRICT", 1), 
        FAST("FAST", 0);
        
        private static final Mode[] $VALUES;
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.FAST, Mode.STRICT };
        }
    }
}
