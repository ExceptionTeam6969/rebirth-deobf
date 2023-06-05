//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.managers.*;

public class Sprint extends Module
{
    private final Setting mode;
    
    @Override
    public void onTick() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/mod/modules/impl/movement/Sprint.mode:Lme/rebirthclient/mod/modules/settings/Setting;
        //     4: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //     7: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint$Mode.RAGE:Lme/rebirthclient/mod/modules/impl/movement/Sprint$Mode;
        //    10: if_acmpne       29
        //    13: ifeq            29
        //    16: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint.mc:Lnet/minecraft/client/Minecraft;
        //    19: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    22: iconst_1       
        //    23: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.setSprinting:(Z)V
        //    26: goto            85
        //    29: aload_0        
        //    30: getfield        me/rebirthclient/mod/modules/impl/movement/Sprint.mode:Lme/rebirthclient/mod/modules/settings/Setting;
        //    33: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    36: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint$Mode.LEGIT:Lme/rebirthclient/mod/modules/impl/movement/Sprint$Mode;
        //    39: if_acmpne       85
        //    42: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint.mc:Lnet/minecraft/client/Minecraft;
        //    45: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    48: getfield        net/minecraft/client/entity/EntityPlayerSP.moveForward:F
        //    51: ldc             0.1
        //    53: fcmpl          
        //    54: ifle            85
        //    57: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint.mc:Lnet/minecraft/client/Minecraft;
        //    60: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    63: getfield        net/minecraft/client/entity/EntityPlayerSP.collidedHorizontally:Z
        //    66: ifne            85
        //    69: getstatic       me/rebirthclient/api/managers/impl/SneakManager.isSneaking:Z
        //    72: ifne            85
        //    75: getstatic       me/rebirthclient/mod/modules/impl/movement/Sprint.mc:Lnet/minecraft/client/Minecraft;
        //    78: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    81: iconst_1       
        //    82: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.setSprinting:(Z)V
        //    85: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Sprint() {
        super("Sprint", "Sprints", Category.MOVEMENT);
        this.mode = this.add(new Setting("Mode", Mode.RAGE));
    }
    
    @Override
    public String getInfo() {
        return Managers.TEXT.normalizeCases(this.mode.getValue());
    }
    
    private enum Mode
    {
        LEGIT("LEGIT", 1);
        
        private static final Mode[] $VALUES;
        
        RAGE("RAGE", 0);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.RAGE, Mode.LEGIT };
        }
    }
}
