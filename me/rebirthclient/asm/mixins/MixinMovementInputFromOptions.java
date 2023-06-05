//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import me.rebirthclient.api.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import net.minecraft.client.settings.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ MovementInputFromOptions.class })
public class MixinMovementInputFromOptions implements Wrapper
{
    @Redirect(method = { "updatePlayerMoveState" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
    public boolean isKeyPressed(final KeyBinding keyBinding) {
        final int getKeyCode = keyBinding.getKeyCode();
        if (getKeyCode <= 0) {
            return keyBinding.isKeyDown();
        }
        if (getKeyCode >= 256) {
            return keyBinding.isKeyDown();
        }
        if (!InventoryMove.INSTANCE.isOn()) {
            return keyBinding.isKeyDown();
        }
        if (Minecraft.getMinecraft().currentScreen == null) {
            return keyBinding.isKeyDown();
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            return keyBinding.isKeyDown();
        }
        if (getKeyCode == 42 && !(boolean)InventoryMove.INSTANCE.sneak.getValue()) {
            return keyBinding.isKeyDown();
        }
        return Keyboard.isKeyDown(getKeyCode);
    }
}
