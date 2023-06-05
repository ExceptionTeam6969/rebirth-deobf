//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import java.util.concurrent.atomic.*;
import me.rebirthclient.mod.gui.click.items.other.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.impl.client.*;
import net.minecraftforge.common.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import me.rebirthclient.mod.modules.impl.render.*;
import me.rebirthclient.mod.commands.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.client.event.*;
import net.minecraft.world.*;
import java.util.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import com.google.common.base.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;

public class EventManager extends Mod
{
    private final Timer logoutTimer;
    private final AtomicBoolean tickOngoing;
    private final Particle.Util particles;
    
    @SubscribeEvent
    public void BlockBreak(final DamageBlockEvent damageBlockEvent) {
        if (damageBlockEvent.getPosition().getY() == -1) {
            return;
        }
        final EntityPlayer entityPlayer = (EntityPlayer)EventManager.mc.world.getEntityByID(damageBlockEvent.getBreakerId());
        if (entityPlayer == null || entityPlayer.getDistance(damageBlockEvent.getPosition().getX() + 0.5, (double)damageBlockEvent.getPosition().getY(), damageBlockEvent.getPosition().getZ() + 0.5) > 7.0) {
            return;
        }
        BreakManager.MineMap.put(entityPlayer, damageBlockEvent.getPosition());
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        Title.updateTitle();
        Managers.COLORS.rainbowProgress += (int)ClickGui.INSTANCE.rainbowSpeed.getValue();
        if (fullNullCheck()) {
            return;
        }
        Managers.MODULES.onTick();
        for (final EntityPlayer entityPlayer : EventManager.mc.world.playerEntities) {
            if (entityPlayer != null) {
                if (entityPlayer.getHealth() > 0.0f) {
                    return;
                }
                MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer));
                Managers.MODULES.onDeath(entityPlayer);
                return;
            }
        }
        if (CombatUtil.isHole(EntityUtil.getPlayerPos(), false, 4, true) && Surround.INSTANCE.isOff() && (boolean)Surround.INSTANCE.enableInHole.getValue() && EventManager.mc.player.onGround && !MovementUtil.isJumping()) {
            Surround.INSTANCE.enable();
        }
        if (CombatSetting.INSTANCE.isOff()) {
            CombatSetting.INSTANCE.enable();
        }
        if (RenderSetting.INSTANCE.isOff()) {
            RenderSetting.INSTANCE.enable();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().startsWith(Command.getCommandPrefix())) {
            clientChatEvent.setCanceled(true);
            try {
                EventManager.mc.ingameGUI.getChatGUI().addToSentMessages(clientChatEvent.getMessage());
                if (clientChatEvent.getMessage().length() > 1) {
                    Managers.COMMANDS.executeCommand(clientChatEvent.getMessage().substring(Command.getCommandPrefix().length() - 1));
                }
                else {
                    Command.sendMessage("Please enter a command.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Command.sendMessage(ChatFormatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Text text) {
        if (text.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Managers.MODULES.onRender2D(new Render2DEvent(text.getPartialTicks(), new ScaledResolution(EventManager.mc)));
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    @SubscribeEvent
    public void onFogColor(final EntityViewRenderEvent.FogColors fogColors) {
        final RenderFogColorEvent renderFogColorEvent = new RenderFogColorEvent();
        MinecraftForge.EVENT_BUS.post((Event)renderFogColorEvent);
        if (renderFogColorEvent.isCanceled()) {
            fogColors.setRed(renderFogColorEvent.getColor().getRed() / 255.0f);
            fogColors.setGreen(renderFogColorEvent.getColor().getGreen() / 255.0f);
            fogColors.setBlue(renderFogColorEvent.getColor().getBlue() / 255.0f);
        }
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public boolean ticksOngoing() {
        return this.tickOngoing.get();
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (renderWorldLastEvent.isCanceled()) {
            return;
        }
        Managers.FPS.update();
        EventManager.mc.profiler.startSection("Rebirth");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        Managers.MODULES.onRender3D(new Render3DEvent(renderWorldLastEvent.getPartialTicks()));
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        EventManager.mc.profiler.endSection();
    }
    
    public void onUnload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    private static void lambda$onPacketReceive$1(final SPacketPlayerListItem sPacketPlayerListItem, final SPacketPlayerListItem.AddPlayerData addPlayerData) {
        final UUID id = addPlayerData.getProfile().getId();
        switch (sPacketPlayerListItem.getAction()) {
            case ADD_PLAYER: {
                MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(0, id, addPlayerData.getProfile().getName()));
                break;
            }
            case REMOVE_PLAYER: {
                final EntityPlayer getPlayerEntityByUUID = EventManager.mc.world.getPlayerEntityByUUID(id);
                if (getPlayerEntityByUUID != null) {
                    MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(1, getPlayerEntityByUUID, id, getPlayerEntityByUUID.getName()));
                    break;
                }
                MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(2, id, (String)null));
                break;
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.getStage() != 0) {
            return;
        }
        Managers.SERVER.onPacketReceived();
        if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)EventManager.mc.world) instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)sPacketEntityStatus.getEntity((World)EventManager.mc.world);
                MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(entityPlayer));
                Managers.MODULES.onTotemPop(entityPlayer);
            }
        }
        if (receive.getPacket() instanceof SPacketPlayerListItem && !Mod.fullNullCheck() && this.logoutTimer.passedS(1.0)) {
            final SPacketPlayerListItem sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket();
            if (SPacketPlayerListItem.Action.ADD_PLAYER != sPacketPlayerListItem.getAction() && SPacketPlayerListItem.Action.REMOVE_PLAYER != sPacketPlayerListItem.getAction()) {
                return;
            }
            sPacketPlayerListItem.getEntries().stream().filter(Objects::nonNull).filter(EventManager::lambda$onPacketReceive$0).forEach(EventManager::lambda$onPacketReceive$1);
        }
        if (receive.getPacket() instanceof SPacketTimeUpdate) {
            Managers.SERVER.update();
        }
    }
    
    private static boolean lambda$onPacketReceive$0(final SPacketPlayerListItem.AddPlayerData addPlayerData) {
        return !Strings.isNullOrEmpty(addPlayerData.getProfile().getName()) || addPlayerData.getProfile().getId() != null;
    }
    
    @SubscribeEvent
    public void renderHUD(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            Managers.TEXT.updateResolution();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            Managers.SPEED.updateValues();
            Managers.ROTATIONS.updateRotations();
            Managers.POSITION.updatePosition();
        }
        if (updateWalkingPlayerEvent.getStage() == 1) {
            if (CombatSetting.INSTANCE.resetRotation.getValue()) {
                Managers.ROTATIONS.resetRotations();
            }
            if (CombatSetting.INSTANCE.resetPosition.getValue()) {
                Managers.POSITION.restorePosition();
            }
        }
    }
    
    @SubscribeEvent
    public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        Managers.CONFIGS.saveConfig(Managers.CONFIGS.config.replaceFirst("Rebirth/", ""));
        Managers.MODULES.onLogout();
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!Mod.fullNullCheck() && livingUpdateEvent.getEntity().getEntityWorld().isRemote && livingUpdateEvent.getEntityLiving().equals((Object)EventManager.mc.player)) {
            Managers.MODULES.onUpdate();
            Managers.MODULES.sortModules();
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent renderTickEvent) {
        final GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (currentScreen != null) {
            if (ClickGui.INSTANCE.particles.getValue()) {
                this.particles.drawParticles();
            }
            if (Minecraft.getMinecraft().world == null) {
                Managers.TEXT.drawString("Rebirth " + ChatFormatting.WHITE + "alpha", 1.0f, (float)(currentScreen.height - Managers.TEXT.getFontHeight2()), Managers.COLORS.getNormalCurrent().getRGB(), true);
                Managers.TEXT.drawRollingRainbowString("powered by iMadCat", 1.0f, (float)(currentScreen.height - Managers.TEXT.getFontHeight2() * 2), true);
            }
            else {
                Managers.TEXT.drawString("Rebirth " + ChatFormatting.WHITE + "alpha", currentScreen.width - 1.0f - Managers.TEXT.getStringWidth("Rebirth alpha"), (float)(currentScreen.height - Managers.TEXT.getFontHeight2()), Managers.COLORS.getNormalCurrent().getRGB(), true);
                Managers.TEXT.drawRollingRainbowString("powered by iMadCat", currentScreen.width - 1.0f - Managers.TEXT.getStringWidth("powered by iMadCat"), (float)(currentScreen.height - Managers.TEXT.getFontHeight2() * 2), true);
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState()) {
            Managers.MODULES.onKeyInput(Keyboard.getEventKey());
        }
    }
    
    public EventManager() {
        this.logoutTimer = new Timer();
        this.particles = new Particle.Util(300);
        this.tickOngoing = new AtomicBoolean(false);
    }
    
    @SubscribeEvent
    public void onClientConnect(final FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.logoutTimer.reset();
        Managers.MODULES.onLogin();
    }
}
