//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient;

import net.minecraftforge.fml.common.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.gui.screen.*;
import net.minecraftforge.fml.common.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import java.nio.*;
import me.rebirthclient.api.util.render.*;
import java.io.*;
import org.apache.logging.log4j.*;

@Mod(modid = "rebirth", name = "Rebirth", version = "alpha")
public class Rebirth
{
    public static final Logger LOGGER;
    public static final String MODID;
    public static final String MODNAME;
    public static final String MODVERISON;
    @Mod.Instance
    public static Rebirth INSTANCE;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        load();
    }
    
    public static void load() {
        Rebirth.LOGGER.info("Loading Rebirth alpha...");
        Managers.load();
        if (Gui.INSTANCE == null) {
            Gui.INSTANCE = new Gui();
        }
        Rebirth.LOGGER.info("Rebirth alpha successfully loaded!\n");
    }
    
    public static void unload(final boolean b) {
        Rebirth.LOGGER.info("Unloading Rebirth alpha...");
        Managers.unload(b);
        Rebirth.LOGGER.info("Rebirth alpha successfully unloaded!\n");
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        Display.setTitle("Rebirth alpha: Loading...");
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try {
                final InputStream resourceAsStream = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/rebirth/constant/icon16x.png");
                Throwable t = null;
                try {
                    final InputStream resourceAsStream2 = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/rebirth/constant/icon32x.png");
                    Throwable t2 = null;
                    Label_0150: {
                        try {
                            Display.setIcon(new ByteBuffer[] { RenderUtil.readImageToBuffer(resourceAsStream), RenderUtil.readImageToBuffer(resourceAsStream2) });
                            if (resourceAsStream2 == null) {
                                break Label_0150;
                            }
                            if (t2 != null) {
                                try {
                                    resourceAsStream2.close();
                                }
                                catch (Throwable t3) {
                                    t2.addSuppressed(t3);
                                }
                                break Label_0150;
                            }
                            resourceAsStream2.close();
                            break Label_0150;
                        }
                        catch (Throwable t4) {
                            t2 = t4;
                            throw t4;
                        }
                        throw;
                    }
                    if (resourceAsStream == null) {
                        return;
                    }
                    if (t != null) {
                        try {
                            resourceAsStream.close();
                        }
                        catch (Throwable t5) {
                            t.addSuppressed(t5);
                        }
                        return;
                    }
                    resourceAsStream.close();
                    return;
                }
                catch (Throwable t6) {
                    t = t6;
                    throw t6;
                }
                throw;
            }
            catch (Exception ex) {
                Rebirth.LOGGER.error("Rebirth alpha couldn't set the window icon!", (Throwable)ex);
            }
        }
    }
    
    static {
        MODID = "rebirth";
        MODVERISON = "alpha";
        MODNAME = "Rebirth";
        LOGGER = LogManager.getLogger("Rebirth");
    }
}
