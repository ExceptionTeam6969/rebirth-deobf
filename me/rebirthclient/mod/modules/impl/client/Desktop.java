//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import org.lwjgl.opengl.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

public class Desktop extends Module
{
    private final List knownPlayers;
    private final Setting selfPop;
    private final Setting mention;
    private List players;
    final Image image;
    private final Setting dm;
    private final Setting onlyTabbed;
    final TrayIcon icon;
    private final Setting visualRange;
    
    public Desktop() {
        super("Desktop", "Desktop notifications", Category.CLIENT);
        this.image = Toolkit.getDefaultToolkit().createImage("icon.png");
        this.icon = new TrayIcon(this.image, "Rebirth");
        this.onlyTabbed = this.add(new Setting("OnlyTabbed", false));
        this.visualRange = this.add(new Setting("VisualRange", true));
        this.selfPop = this.add(new Setting("TotemPop", true));
        this.mention = this.add(new Setting("Mention", true));
        this.dm = this.add(new Setting("DM", true));
        this.knownPlayers = new ArrayList();
    }
    
    @Override
    public void onEnable() {
        this.addIcon();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck() || !(boolean)this.visualRange.getValue()) {
            return;
        }
        try {
            if (!Display.isActive() && (boolean)this.onlyTabbed.getValue()) {
                return;
            }
        }
        catch (Exception ex) {}
        this.players = (List)Desktop.mc.world.loadedEntityList.stream().filter(Desktop::lambda$onUpdate$0).collect(Collectors.toList());
        try {
            final Iterator<Entity> iterator = this.players.iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (entity instanceof EntityPlayer && !Integer.valueOf(Desktop.mc.player.getName().toUpperCase().hashCode()).equals(entity.getName().toUpperCase().hashCode()) && !this.knownPlayers.contains(entity) && !Managers.FRIENDS.isFriend(entity.getName())) {
                    this.knownPlayers.add(entity);
                    this.icon.displayMessage("Rebirth", entity.getName() + " has entered your visual range!", TrayIcon.MessageType.INFO);
                }
                return;
            }
        }
        catch (Exception ex2) {}
        try {
            this.knownPlayers.removeIf(this::lambda$onUpdate$1);
        }
        catch (Exception ex3) {}
    }
    
    @SubscribeEvent
    public void onClientChatReceived(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (fullNullCheck()) {
            return;
        }
        final String value = String.valueOf(clientChatReceivedEvent.getMessage());
        if (value.contains(Desktop.mc.player.getName()) && (boolean)this.mention.getValue()) {
            this.icon.displayMessage("Rebirth", "New chat mention!", TrayIcon.MessageType.INFO);
        }
        if (value.contains("whispers:") && (boolean)this.dm.getValue()) {
            this.icon.displayMessage("Rebirth", "New direct message!", TrayIcon.MessageType.INFO);
        }
    }
    
    @Override
    public void onTotemPop(final EntityPlayer entityPlayer) {
        if (fullNullCheck() || entityPlayer != Desktop.mc.player || !(boolean)this.selfPop.getValue()) {
            return;
        }
        this.icon.displayMessage("Rebirth", "You are popping!", TrayIcon.MessageType.WARNING);
    }
    
    @Override
    public void onDisable() {
        this.knownPlayers.clear();
        this.removeIcon();
    }
    
    private void removeIcon() {
        SystemTray.getSystemTray().remove(this.icon);
    }
    
    private static boolean lambda$onUpdate$0(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    private void addIcon() {
        final SystemTray systemTray = SystemTray.getSystemTray();
        this.icon.setImageAutoSize(true);
        this.icon.setToolTip("rebirth alpha");
        try {
            systemTray.add(this.icon);
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean lambda$onUpdate$1(final Entity entity) {
        return entity instanceof EntityPlayer && !Integer.valueOf(Desktop.mc.player.getName().toUpperCase().hashCode()).equals(entity.getName().toUpperCase().hashCode()) && !this.players.contains(entity);
    }
    
    @Override
    public void onUnload() {
        this.onDisable();
    }
    
    @Override
    public void onLoad() {
        if (this.isOn()) {
            this.addIcon();
        }
    }
}
