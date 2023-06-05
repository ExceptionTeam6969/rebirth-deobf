//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers;

import me.rebirthclient.api.managers.impl.*;

public class Managers
{
    public static RotationManager ROTATIONS;
    public static ColorManager COLORS;
    public static FileManager FILES;
    public static FpsManager FPS;
    public static PositionManager POSITION;
    public static TimerManager TIMER;
    private static boolean loaded;
    public static InteractionManager INTERACTIONS;
    public static ReloadManager RELOAD;
    public static EventManager EVENTS;
    public static BreakManager BREAK;
    public static ConfigManager CONFIGS;
    public static SneakManager SNEAK;
    public static ModuleManager MODULES;
    public static CommandManager COMMANDS;
    public static FriendManager FRIENDS;
    public static TextManager TEXT;
    public static ServerManager SERVER;
    public static SpeedManager SPEED;
    
    public static void onUnload() {
        if (isLoaded()) {
            Managers.EVENTS.onUnload();
            Managers.MODULES.onUnloadPre();
            Managers.CONFIGS.saveConfig(Managers.CONFIGS.config.replaceFirst("Rebirth/", ""));
            Managers.MODULES.onUnloadPost();
            Managers.loaded = false;
        }
    }
    
    public static boolean isLoaded() {
        return Managers.loaded;
    }
    
    public static void unload(final boolean b) {
        if (b) {
            (Managers.RELOAD = new ReloadManager()).init((Managers.COMMANDS != null) ? Managers.COMMANDS.getCommandPrefix() : ".");
        }
        onUnload();
        Managers.INTERACTIONS = null;
        Managers.ROTATIONS = null;
        Managers.POSITION = null;
        Managers.COMMANDS = null;
        Managers.CONFIGS = null;
        Managers.MODULES = null;
        Managers.FRIENDS = null;
        Managers.SERVER = null;
        Managers.COLORS = null;
        Managers.EVENTS = null;
        Managers.SPEED = null;
        Managers.TIMER = null;
        Managers.FILES = null;
        Managers.TEXT = null;
        Managers.FPS = null;
    }
    
    public static void load() {
        Managers.loaded = true;
        if (Managers.RELOAD != null) {
            Managers.RELOAD.unload();
            Managers.RELOAD = null;
        }
        Managers.EVENTS = new EventManager();
        Managers.TEXT = new TextManager();
        Managers.INTERACTIONS = new InteractionManager();
        Managers.ROTATIONS = new RotationManager();
        Managers.POSITION = new PositionManager();
        Managers.COMMANDS = new CommandManager();
        Managers.CONFIGS = new ConfigManager();
        Managers.MODULES = new ModuleManager();
        Managers.FRIENDS = new FriendManager();
        Managers.SERVER = new ServerManager();
        Managers.COLORS = new ColorManager();
        Managers.SPEED = new SpeedManager();
        Managers.TIMER = new TimerManager();
        Managers.FILES = new FileManager();
        Managers.FPS = new FpsManager();
        Managers.SNEAK = new SneakManager();
        Managers.BREAK = new BreakManager();
        Managers.MODULES.init();
        Managers.CONFIGS.init();
        Managers.EVENTS.init();
        Managers.TEXT.init();
        Managers.SNEAK.init();
        Managers.MODULES.onLoad();
    }
    
    static {
        Managers.loaded = true;
    }
}
