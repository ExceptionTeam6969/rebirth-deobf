//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import java.util.*;
import java.io.*;
import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.launch.*;

public class EnvironmentStateTweaker implements ITweaker
{
    public void acceptOptions(final List list, final File file, final File file2, final String s) {
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader launchClassLoader) {
        MixinBootstrap.getPlatform().inject();
    }
    
    public String getLaunchTarget() {
        return "";
    }
    
    public String[] getLaunchArguments() {
        MixinEnvironment.gotoPhase(MixinEnvironment.Phase.DEFAULT);
        return new String[0];
    }
}
