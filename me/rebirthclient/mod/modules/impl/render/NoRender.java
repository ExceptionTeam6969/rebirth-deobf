//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.asm.accessors.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraftforge.client.event.*;

public class NoRender extends Module
{
    public final Setting noWeather;
    boolean gamma;
    public final Setting explosion;
    public final Setting fire;
    public final Setting totemPops;
    public final Setting nausea;
    public final Setting blocks;
    public final Setting hurtCam;
    public final Setting exp;
    public final Setting blind;
    public Setting skyLight;
    public Setting advancements;
    float oldBright;
    public static NoRender INSTANCE;
    private static final ResourceLocation GUI_BARS_TEXTURES;
    public Setting boss;
    public final Setting armor;
    public final Setting fog;
    public Setting scale;
    public final Setting night;
    
    private boolean lambda$new$0(final Float n) {
        return this.boss.getValue() == Boss.MINIMIZE || this.boss.getValue() == Boss.STACK;
    }
    
    @SubscribeEvent
    public void onRenderPost(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
            if (this.boss.getValue() == Boss.MINIMIZE) {
                final Map mapBossInfos = ((IGuiBossOverlay)NoRender.mc.ingameGUI.getBossOverlay()).getMapBossInfos();
                if (mapBossInfos == null) {
                    return;
                }
                final int getScaledWidth = new ScaledResolution(NoRender.mc).getScaledWidth();
                final int n = 12;
                final Iterator<Map.Entry<K, BossInfoClient>> iterator = mapBossInfos.entrySet().iterator();
                if (iterator.hasNext()) {
                    final BossInfoClient bossInfoClient = iterator.next().getValue();
                    final String getFormattedText = bossInfoClient.getName().getFormattedText();
                    final int n2 = (int)(getScaledWidth / (float)this.scale.getValue() / 2.0f - 91.0f);
                    GL11.glScaled((double)(float)this.scale.getValue(), (double)(float)this.scale.getValue(), 1.0);
                    if (!post.isCanceled()) {
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        NoRender.mc.getTextureManager().bindTexture(NoRender.GUI_BARS_TEXTURES);
                        ((IGuiBossOverlay)NoRender.mc.ingameGUI.getBossOverlay()).invokeRender(n2, n, (BossInfo)bossInfoClient);
                        NoRender.mc.fontRenderer.drawStringWithShadow(getFormattedText, getScaledWidth / (float)this.scale.getValue() / 2.0f - NoRender.mc.fontRenderer.getStringWidth(getFormattedText) / 2.0f, (float)(n - 9), 16777215);
                    }
                    GL11.glScaled(1.0 / (float)this.scale.getValue(), 1.0 / (float)this.scale.getValue(), 1.0);
                    final int n3 = n + (10 + NoRender.mc.fontRenderer.FONT_HEIGHT);
                }
            }
            else if (this.boss.getValue() == Boss.STACK) {
                final Map mapBossInfos2 = ((IGuiBossOverlay)NoRender.mc.ingameGUI.getBossOverlay()).getMapBossInfos();
                final HashMap<Object, Pair> hashMap = new HashMap<Object, Pair>();
                final Iterator<Map.Entry<K, BossInfoClient>> iterator2 = mapBossInfos2.entrySet().iterator();
                if (iterator2.hasNext()) {
                    final Map.Entry<K, BossInfoClient> entry = iterator2.next();
                    final String getFormattedText2 = entry.getValue().getName().getFormattedText();
                    if (hashMap.containsKey(getFormattedText2)) {
                        final Pair pair = hashMap.get(getFormattedText2);
                        hashMap.put(getFormattedText2, new Pair(pair.getKey(), (int)pair.getValue() + 1));
                    }
                    else {
                        hashMap.put(getFormattedText2, new Pair(entry.getValue(), 1));
                    }
                    return;
                }
                final int getScaledWidth2 = new ScaledResolution(NoRender.mc).getScaledWidth();
                final int n4 = 12;
                final Iterator<Map.Entry<String, Pair>> iterator3 = hashMap.entrySet().iterator();
                if (iterator3.hasNext()) {
                    final Map.Entry<String, Pair> entry2 = iterator3.next();
                    final String s = entry2.getKey();
                    final BossInfoClient bossInfoClient2 = (BossInfoClient)entry2.getValue().getKey();
                    final String string = s + " x" + (int)entry2.getValue().getValue();
                    final int n5 = (int)(getScaledWidth2 / (float)this.scale.getValue() / 2.0f - 91.0f);
                    GL11.glScaled((double)(float)this.scale.getValue(), (double)(float)this.scale.getValue(), 1.0);
                    if (!post.isCanceled()) {
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        NoRender.mc.getTextureManager().bindTexture(NoRender.GUI_BARS_TEXTURES);
                        ((IGuiBossOverlay)NoRender.mc.ingameGUI.getBossOverlay()).invokeRender(n5, n4, (BossInfo)bossInfoClient2);
                        NoRender.mc.fontRenderer.drawStringWithShadow(string, getScaledWidth2 / (float)this.scale.getValue() / 2.0f - NoRender.mc.fontRenderer.getStringWidth(string) / 2.0f, (float)(n4 - 9), 16777215);
                    }
                    GL11.glScaled(1.0 / (float)this.scale.getValue(), 1.0 / (float)this.scale.getValue(), 1.0);
                    final int n6 = n4 + (10 + NoRender.mc.fontRenderer.FONT_HEIGHT);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity fogDensity) {
        if (!(boolean)this.fog.getValue()) {
            fogDensity.setDensity(0.0f);
            fogDensity.setCanceled(true);
        }
    }
    
    @Override
    public void onUpdate() {
        if ((boolean)this.blind.getValue() && NoRender.mc.player.isPotionActive(MobEffects.BLINDNESS)) {
            NoRender.mc.player.removePotionEffect(MobEffects.BLINDNESS);
        }
        if ((boolean)this.noWeather.getValue() && NoRender.mc.world.isRaining()) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
        if (NoRender.mc.player.isPotionActive(MobEffects.NAUSEA) && (boolean)this.nausea.getValue()) {
            NoRender.mc.player.removePotionEffect(MobEffects.NAUSEA);
        }
        if (this.night.getValue()) {
            this.gamma = true;
            NoRender.mc.gameSettings.gammaSetting = 100.0f;
        }
        else if (this.gamma) {
            NoRender.mc.gameSettings.gammaSetting = this.oldBright;
            this.gamma = false;
        }
    }
    
    @Override
    public void onEnable() {
        this.oldBright = NoRender.mc.gameSettings.gammaSetting;
    }
    
    @Override
    public void onDisable() {
        NoRender.mc.gameSettings.gammaSetting = this.oldBright;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled()) {
            return;
        }
        if ((boolean)this.explosion.getValue() && receive.getPacket() instanceof SPacketExplosion) {
            receive.setCanceled(true);
            return;
        }
        if ((boolean)this.exp.getValue() && receive.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getCategory() == SoundCategory.NEUTRAL && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_EXPERIENCE_BOTTLE_THROW) {
                receive.setCanceled(true);
            }
        }
    }
    
    public NoRender() {
        super("NoRender", "Prevent some animation", Category.RENDER);
        this.night = this.add(new Setting("Night", true));
        this.armor = this.add(new Setting("Armor", true));
        this.fire = this.add(new Setting("Fire", true));
        this.blind = this.add(new Setting("Blind", true));
        this.nausea = this.add(new Setting("Nausea", true));
        this.fog = this.add(new Setting("Fog", true));
        this.noWeather = this.add(new Setting("Weather", true));
        this.hurtCam = this.add(new Setting("HurtCam", true));
        this.totemPops = this.add(new Setting("TotemPop", true));
        this.blocks = this.add(new Setting("Block", true));
        this.exp = this.add(new Setting("Exp", true));
        this.explosion = this.add(new Setting("Explosion[!]", false));
        this.skyLight = this.add(new Setting("SkyLight", false));
        this.advancements = this.add(new Setting("Advancements", false));
        this.boss = this.add(new Setting("BossBars", Boss.NONE));
        this.scale = this.add(new Setting("Scale", 0.5f, 0.5f, 1.0f, this::lambda$new$0));
        this.gamma = false;
        NoRender.INSTANCE = this;
    }
    
    static {
        NoRender.INSTANCE = new NoRender();
        GUI_BARS_TEXTURES = new ResourceLocation("textures/gui/bars.png");
    }
    
    @SubscribeEvent
    public void onRenderPre(final RenderGameOverlayEvent.Pre pre) {
        if (pre.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
            pre.setCanceled(true);
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (this.exp.getValue()) {
            final Iterator<Entity> iterator = NoRender.mc.world.getLoadedEntityList().iterator();
            if (iterator.hasNext()) {
                final Entity entity = iterator.next();
                if (entity instanceof EntityExpBottle) {
                    NoRender.mc.world.removeEntity(entity);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void blockOverlayEventListener(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!(boolean)this.fire.getValue() && !(boolean)this.blocks.getValue()) {
            return;
        }
        if (renderBlockOverlayEvent.getOverlayType() != RenderBlockOverlayEvent.OverlayType.FIRE && !(boolean)this.blocks.getValue()) {
            return;
        }
        renderBlockOverlayEvent.setCanceled(true);
    }
    
    public enum Boss
    {
        STACK("STACK", 2), 
        NONE("NONE", 0), 
        REMOVE("REMOVE", 1);
        
        private static final Boss[] $VALUES;
        
        MINIMIZE("MINIMIZE", 3);
        
        static {
            $VALUES = new Boss[] { Boss.NONE, Boss.REMOVE, Boss.STACK, Boss.MINIMIZE };
        }
        
        private Boss(final String s, final int n) {
        }
    }
    
    public static class Pair
    {
        Object value;
        Object key;
        
        public void setKey(final Object key) {
            this.key = key;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Pair(final Object key, final Object value) {
            this.key = key;
            this.value = value;
        }
        
        public Object getKey() {
            return this.key;
        }
        
        public void setValue(final Object value) {
            this.value = value;
        }
    }
}
