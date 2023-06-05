//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.*;
import com.google.common.collect.*;
import net.minecraft.entity.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoLag extends Module
{
    public final Setting scoreBoards;
    private static final Set BLACKLIST;
    public final Setting armor;
    public final Setting antiPopLag;
    public final Setting glowing;
    public final Setting parrots;
    public final Setting skulls;
    private final Setting crystals;
    private final Setting antiSound;
    public final Setting antiSpam;
    public final Setting tnt;
    public static NoLag INSTANCE;
    
    public NoLag() {
        super("NoLag", "Removes several things that may cause fps drops", Category.RENDER);
        this.antiSpam = this.add(new Setting("AntiSpam", true));
        this.antiPopLag = this.add(new Setting("AntiPopLag", true));
        this.skulls = this.add(new Setting("WitherSkulls", false));
        this.tnt = this.add(new Setting("PrimedTNT", false));
        this.scoreBoards = this.add(new Setting("ScoreBoards", true));
        this.glowing = this.add(new Setting("GlowingEntities", true));
        this.parrots = this.add(new Setting("Parrots", true));
        this.antiSound = this.add(new Setting("AntiSound", true).setParent());
        this.armor = this.add(new Setting("Armor", true, this::lambda$new$0));
        this.crystals = this.add(new Setting("Crystals", true, this::lambda$new$1));
        NoLag.INSTANCE = this;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.antiSound.isOpen();
    }
    
    @SubscribeEvent
    public void onRenderEntity(final RenderEntityEvent renderEntityEvent) {
        if (renderEntityEvent.getEntity() != null) {
            if ((boolean)this.skulls.getValue() && renderEntityEvent.getEntity() instanceof EntityWitherSkull) {
                renderEntityEvent.setCanceled(true);
            }
            if ((boolean)this.tnt.getValue() && renderEntityEvent.getEntity() instanceof EntityTNTPrimed) {
                renderEntityEvent.setCanceled(true);
            }
            if ((boolean)this.parrots.getValue() && renderEntityEvent.getEntity() instanceof EntityParrot) {
                renderEntityEvent.setCanceled(true);
            }
        }
    }
    
    static {
        BLACKLIST = Sets.newHashSet((Object[])new SoundEvent[] { SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER });
    }
    
    @Override
    public void onTick() {
        if (!(boolean)this.glowing.getValue()) {
            return;
        }
        final Iterator<Entity> iterator = NoLag.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if (entity.isGlowing()) {
                entity.setGlowing(false);
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketChat && (boolean)this.antiPopLag.getValue()) {
            final String getUnformattedText = ((SPacketChat)receive.getPacket()).chatComponent.getUnformattedText();
            if (getUnformattedText.contains("\u3f01") || getUnformattedText.contains("\u3801") || getUnformattedText.contains("\u1b01") || getUnformattedText.contains("\u1201") || getUnformattedText.contains("\u0101") || getUnformattedText.contains("\u5b01") || getUnformattedText.contains("\u61fa")) {
                receive.setCanceled(true);
                ((SPacketChat)receive.getPacket()).chatComponent = null;
                this.sendMessage(ChatFormatting.RED + "Removed lag text");
                return;
            }
        }
        if (receive.getPacket() instanceof SPacketChat && (boolean)this.antiSpam.getValue()) {
            final String getUnformattedText2 = ((SPacketChat)receive.getPacket()).chatComponent.getUnformattedText();
            if (getUnformattedText2.contains("Sorry, but you can't change that here.") || getUnformattedText2.contains("Sorry, but you can't place things here.") || getUnformattedText2.contains("Sorry, but you can't break that block here.") || getUnformattedText2.contains("Sorry, but you can't use that here.") || getUnformattedText2.contains("[AdvancedPortals] You don't have permission to build here!") || getUnformattedText2.contains("Sorry, but you can't PvP here.") || getUnformattedText2.contains("You cannot teleport while in spectator mode.") || getUnformattedText2.contains("\u4f60\u83ab\u5f97 S.use \u6743\u9650!")) {
                receive.setCanceled(true);
                ((SPacketChat)receive.getPacket()).chatComponent = null;
                return;
            }
        }
        if (receive.getPacket() instanceof SPacketSoundEffect && (boolean)this.antiSound.getValue()) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if ((boolean)this.crystals.getValue() && sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                receive.setCanceled(true);
                return;
            }
            if (NoLag.BLACKLIST.contains(sPacketSoundEffect.getSound()) && (boolean)this.armor.getValue()) {
                receive.setCanceled(true);
            }
        }
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.antiSound.isOpen();
    }
}
