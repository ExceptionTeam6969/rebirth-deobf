//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm;

import net.minecraftforge.fml.relauncher.*;
import me.rebirthclient.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

@IFMLLoadingPlugin.Name("Rebirth")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class MixinLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public MixinLoader() {
        Rebirth.LOGGER.info("Loading rebirth mixins...\n");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.rebirth.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Rebirth.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map map) {
        MixinLoader.isObfuscatedEnvironment = map.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
