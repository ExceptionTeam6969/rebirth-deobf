//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules;

import me.rebirthclient.mod.*;
import me.rebirthclient.mod.modules.impl.hud.*;
import me.rebirthclient.mod.commands.*;
import net.minecraftforge.common.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.text.*;
import me.rebirthclient.api.events.impl.*;

public abstract class Module extends Mod
{
    public final Setting enabled;
    public final Setting drawn;
    public final Setting bind;
    public final Setting displayerName;
    private final boolean shouldListen;
    private final String description;
    private final Category category;
    
    public void enable() {
        this.enabled.setValue(true);
        this.onEnable();
        Notifications.add(this.getPrefix() + "toggled¡ìa on.");
        Command.sendMessageWithID(this.getPrefix() + "toggled¡ìa on.", 1);
        if (this.isOn() && this.shouldListen) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }
    
    private String getPrefix() {
        return "¡ìf[¡ìr" + this.getDisplayName() + "¡ìf] ";
    }
    
    public void onTick() {
    }
    
    public String getArrayListInfo() {
        return (HUD.INSTANCE.space.getValue() ? Managers.TEXT.capitalSpace(this.getDisplayName()) : this.getDisplayName()) + ChatFormatting.GRAY + ((this.getInfo() != null) ? (" [" + ChatFormatting.WHITE + this.getInfo() + ChatFormatting.GRAY + "]") : "");
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
    }
    
    public void onLoad() {
    }
    
    public String getDisplayName() {
        return (String)this.displayerName.getValue();
    }
    
    public String getInfo() {
        return null;
    }
    
    public void onLogout() {
    }
    
    private boolean shouldEnable() {
        return Integer.valueOf("HUD".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("Title".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("CombatSetting".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("RenderSetting".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("Rotations".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("Chat".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) || Integer.valueOf("GuiAnimation".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode());
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
    }
    
    public boolean isDrawn() {
        return (boolean)this.drawn.getValue();
    }
    
    public Module(final String s, final String description, final Category category) {
        super(s);
        this.enabled = this.add(new Setting("Enabled", this.shouldEnable()));
        this.drawn = this.add(new Setting("Drawn", this.shouldDrawn()));
        this.bind = this.add(new Setting("Keybind", Integer.valueOf("ClickGui".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) ? new Bind(21) : new Bind(-1)));
        this.displayerName = this.add(new Setting("Display", s));
        this.description = description;
        this.category = category;
        this.shouldListen = true;
    }
    
    public boolean isOff() {
        return !(boolean)this.enabled.getValue();
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void onLogin() {
    }
    
    public void onUnload() {
    }
    
    public boolean isOn() {
        return (boolean)this.enabled.getValue();
    }
    
    public boolean isListening() {
        return this.shouldListen && this.isOn();
    }
    
    public void sendMessageWithID(final String s, final int n) {
        if (!nullCheck()) {
            Module.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new Command.ChatMessage(Managers.TEXT.getPrefix() + this.getPrefix() + s), n);
        }
    }
    
    private boolean shouldDrawn() {
        return !Integer.valueOf("CombatSetting".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("Title".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("HUD".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("Rotations".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("RenderSetting".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("Chat".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode()) && !Integer.valueOf("GuiAnimation".toUpperCase().hashCode()).equals(this.getName().toUpperCase().hashCode());
    }
    
    public Bind getBind() {
        return (Bind)this.bind.getValue();
    }
    
    public void sendMessage(final String s) {
        Notifications.notifyList.add(new Notifications.Notifys(s));
        Command.sendSilentMessage(Managers.TEXT.getPrefix() + this.getPrefix() + s);
    }
    
    public void onUpdate() {
    }
    
    public void toggle() {
        if (this.isOn()) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
    }
    
    public void onEnable() {
    }
    
    public void setBind(final int n) {
        this.bind.setValue(new Bind(n));
    }
    
    public void onDisable() {
    }
    
    public void disable() {
        if (this.shouldListen) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue(false);
        this.onDisable();
        Notifications.add(this.getPrefix() + "toggled¡ìc off.");
        Command.sendMessageWithID(this.getPrefix() + "toggled¡ìc off.", 1);
    }
    
    public void onDeath(final EntityPlayer entityPlayer) {
    }
}
