//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import org.lwjgl.input.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;

public class MCF extends Module
{
    private boolean didClick;
    
    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.didClick && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.didClick = true;
        }
        else {
            this.didClick = false;
        }
    }
    
    public MCF() {
        super("MCF", "Middle click your fellow friends", Category.MISC);
    }
    
    private void onClick() {
        final RayTraceResult objectMouseOver = MCF.mc.objectMouseOver;
        final Entity entityHit;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entityHit = objectMouseOver.entityHit) instanceof EntityPlayer) {
            if (Managers.FRIENDS.isFriend(entityHit.getName())) {
                Managers.FRIENDS.removeFriend(entityHit.getName());
                this.sendMessage(ChatFormatting.RED + entityHit.getName() + ChatFormatting.RED + " has been unfriended.");
            }
            else {
                Managers.FRIENDS.addFriend(entityHit.getName());
                this.sendMessage(ChatFormatting.AQUA + entityHit.getName() + ChatFormatting.GREEN + " has been friended.");
            }
        }
        this.didClick = true;
    }
}
