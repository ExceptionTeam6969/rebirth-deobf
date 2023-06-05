//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.resources.*;
import net.minecraft.potion.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.managers.impl.*;
import java.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.util.math.*;
import java.util.function.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.client.gui.*;
import java.text.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

public class HUD extends Module
{
    private final Setting idWatermark;
    private final Setting watermarkVerColor;
    private final Setting greeterNameColor;
    private final Setting waterMarkY;
    private final Setting textRadar;
    private final Setting watermarkShort;
    private final Setting watermark;
    public final Setting potionIcons;
    private final Setting armor;
    private final Setting ping;
    public final Setting combatCount;
    private final Setting greeterMode;
    private final Setting direction;
    public final Setting onlyBind;
    private Map players;
    private final Setting forgeHax;
    private final Setting greeterText;
    private final Setting skeetBar;
    private final Setting tps;
    public final Setting lowerCase;
    private final Setting page;
    private final Setting hideInChat;
    private final Setting time;
    private final Setting arrayListGlow;
    private final Setting arrayListRect;
    public final Setting ordering;
    public final Setting combatCountX;
    public static HUD INSTANCE;
    public final Setting combatCountY;
    private final Timer timer;
    private final Setting arrayList;
    private final Setting lag;
    private final Setting arrayListLine;
    private final Setting speed;
    private final Setting greeter;
    private int color;
    public final Setting lagTime;
    private final Setting jamieArray;
    private final Setting pvp;
    private final Setting grayColors;
    public final Setting watermarkString;
    private final Setting potionColor;
    private final Setting coords;
    private final Setting potions;
    private final Setting arrayListOffset;
    private final Setting arrayListRectColor;
    private final Setting jamie;
    private final Setting fps;
    private final Setting renderingUp;
    public final Setting space;
    
    private boolean lambda$new$5(final Integer n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$27(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private void drawWelcomer() {
        final int scaledWidth = Managers.TEXT.scaledWidth;
        final String s = this.greeterNameColor.getValue() ? String.valueOf(ChatFormatting.WHITE) : "";
        String string = this.lowerCase.getValue() ? "Welcome, ".toLowerCase() : "Welcome, ";
        if (this.greeterMode.getValue() == GreeterMode.PLAYER) {
            if (this.greeter.getValue()) {
                string = string + s + HUD.mc.player.getDisplayNameString();
            }
            if (ClickGui.INSTANCE.rainbow.getValue()) {
                if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                    Managers.TEXT.drawString(string + ChatFormatting.RESET + " :')", scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 2.0f, 2.0f, Managers.COLORS.getRainbow().getRGB(), true);
                }
                else if (this.greeterNameColor.getValue()) {
                    this.drawDoubleRainbowRollingString(((boolean)this.lowerCase.getValue()) ? "Welcome,".toLowerCase() : "Welcome,", (FontMod.INSTANCE.isOn() ? "" : " ") + ChatFormatting.WHITE + HUD.mc.player.getDisplayNameString(), scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 2.0f, 2.0f);
                    Managers.TEXT.drawRollingRainbowString(" :')", scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 1.5f + Managers.TEXT.getStringWidth(string) - (FontMod.INSTANCE.isOn() ? 1.5f : 0.0f), 2.0f, true);
                }
                else {
                    Managers.TEXT.drawRollingRainbowString((this.lowerCase.getValue() ? "Welcome,".toLowerCase() : "Welcome, ") + HUD.mc.player.getDisplayNameString() + " :')", scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 2.0f, 2.0f, true);
                }
            }
            else {
                Managers.TEXT.drawString(string + ChatFormatting.RESET + " :')", scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 2.0f, 2.0f, this.color, true);
            }
        }
        else {
            String s2 = (String)this.greeterText.getValue();
            if (this.greeter.getValue()) {
                s2 = (String)this.greeterText.getValue();
            }
            if (ClickGui.INSTANCE.rainbow.getValue()) {
                if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                    Managers.TEXT.drawString(s2, scaledWidth / 2.0f - Managers.TEXT.getStringWidth(s2) / 2.0f + 2.0f, 2.0f, Managers.COLORS.getRainbow().getRGB(), true);
                }
                else {
                    Managers.TEXT.drawRollingRainbowString(s2, scaledWidth / 2.0f - Managers.TEXT.getStringWidth(s2) / 2.0f + 2.0f, 2.0f, true);
                }
            }
            else {
                Managers.TEXT.drawString(s2, scaledWidth / 2.0f - Managers.TEXT.getStringWidth(s2) / 2.0f + 2.0f, 2.0f, this.color, true);
            }
        }
    }
    
    private boolean lambda$new$37(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$17(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.combatCount.isOpen() && this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.combatCount.isOpen() && this.page.getValue() == Page.ELEMENTS;
    }
    
    private String getColoredPotionString(final PotionEffect potionEffect) {
        return I18n.format(potionEffect.getPotion().getName(), new Object[0]) + " " + (potionEffect.getAmplifier() + 1) + " " + ChatFormatting.WHITE + Potion.getPotionDurationString(potionEffect, 1.0f);
    }
    
    private static boolean lambda$drawPvPInfo$46(final ItemStack itemStack) {
        return itemStack.getItem() == Items.TOTEM_OF_UNDYING;
    }
    
    private boolean lambda$new$11(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$43(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$33(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$34(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$41(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$21(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$8(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$42(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$31(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$36(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$22(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private static boolean lambda$onRender2D$45(final Module module) {
        return module.isOn() && module.isDrawn();
    }
    
    @Override
    public void onUpdate() {
        if (this.timer.passedMs(500L)) {
            this.players = this.getTextRadarMap();
            this.timer.reset();
        }
    }
    
    private boolean lambda$new$23(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$26(final String s) {
        return this.greeter.isOpen() && this.greeterMode.getValue() == GreeterMode.CUSTOM && this.page.getValue() == Page.ELEMENTS;
    }
    
    private int getJamieColor(final int n) {
        final int size = Managers.MODULES.getEnabledModules().size();
        final int rgb = new Color(91, 206, 250).getRGB();
        final int rgb2 = Color.WHITE.getRGB();
        final int rgb3 = new Color(245, 169, 184).getRGB();
        final int n2 = size / 5;
        if (n < n2) {
            return rgb;
        }
        if (n < n2 * 2) {
            return rgb3;
        }
        if (n < n2 * 3) {
            return rgb2;
        }
        if (n < n2 * 4) {
            return rgb3;
        }
        if (n < n2 * 5) {
            return rgb;
        }
        return rgb;
    }
    
    public HUD() {
        super("HUD", "HUD elements drawn on your screen", Category.CLIENT);
        this.page = this.add(new Setting("Page", Page.GLOBAL));
        this.potionIcons = this.add(new Setting("NoPotionIcons", false, this::lambda$new$0));
        this.combatCount = this.add(new Setting("ItemsCount", true, this::lambda$new$1).setParent());
        this.combatCountX = this.add(new Setting("X", 125, 0, 300, this::lambda$new$2));
        this.combatCountY = this.add(new Setting("Y", 18, 0, 500, this::lambda$new$3));
        this.ordering = this.add(new Setting("Ordering", ModuleManager.Ordering.LENGTH, this::lambda$new$4));
        this.lagTime = this.add(new Setting("LagTime", 1000, 0, 2000, this::lambda$new$5));
        this.lowerCase = this.add(new Setting("LowerCase", false, this::lambda$new$6));
        this.grayColors = this.add(new Setting("Gray", true, this::lambda$new$7));
        this.renderingUp = this.add(new Setting("RenderingUp", true, this::lambda$new$8));
        this.skeetBar = this.add(new Setting("SkeetMode", false, this::lambda$new$9).setParent());
        this.jamie = this.add(new Setting("JamieColor", false, this::lambda$new$10));
        this.watermark = this.add(new Setting("Watermark", true, this::lambda$new$11).setParent());
        this.watermarkString = this.add(new Setting("Text", "Rebirth", this::lambda$new$12));
        this.watermarkShort = this.add(new Setting("Shorten", false, this::lambda$new$13));
        this.watermarkVerColor = this.add(new Setting("VerColor", true, this::lambda$new$14));
        this.waterMarkY = this.add(new Setting("Height", 2, 2, 12, this::lambda$new$15));
        this.idWatermark = this.add(new Setting("IdWatermark", true, this::lambda$new$16));
        this.pvp = this.add(new Setting("PvpInfo", true, this::lambda$new$17));
        this.textRadar = this.add(new Setting("TextRadar", false, this::lambda$new$18));
        this.coords = this.add(new Setting("Position(XYZ)", false, this::lambda$new$19));
        this.direction = this.add(new Setting("Direction", false, this::lambda$new$20));
        this.armor = this.add(new Setting("Armor", false, this::lambda$new$21));
        this.lag = this.add(new Setting("LagNotifier", false, this::lambda$new$22));
        this.greeter = this.add(new Setting("Welcomer", false, this::lambda$new$23).setParent());
        this.greeterMode = this.add(new Setting("Mode", GreeterMode.PLAYER, this::lambda$new$24));
        this.greeterNameColor = this.add(new Setting("NameColor", true, this::lambda$new$25));
        this.greeterText = this.add(new Setting("WelcomerText", "i sniff coke and smoke dope i got 2 habbits", this::lambda$new$26));
        this.arrayList = this.add(new Setting("ArrayList", true, this::lambda$new$27).setParent());
        this.arrayListOffset = this.add(new Setting("Offset", 50, 0, 200, this::lambda$new$28));
        this.space = this.add(new Setting("Space", true, this::lambda$new$29));
        this.onlyBind = this.add(new Setting("OnlyBind", false, this::lambda$new$30));
        this.jamieArray = this.add(new Setting("JamieArray", false, this::lambda$new$31));
        this.forgeHax = this.add(new Setting("ForgeHax", false, this::lambda$new$32));
        this.arrayListLine = this.add(new Setting("Outline", false, this::lambda$new$33));
        this.arrayListRect = this.add(new Setting("Rect", false, this::lambda$new$34));
        this.arrayListRectColor = this.add(new Setting("ColorRect", false, this::lambda$new$35));
        this.arrayListGlow = this.add(new Setting("Glow", true, this::lambda$new$36));
        this.hideInChat = this.add(new Setting("HideInChat", true, this::lambda$new$37));
        this.potions = this.add(new Setting("Potions", false, this::lambda$new$38).setParent());
        this.potionColor = this.add(new Setting("PotionColor", false, this::lambda$new$39));
        this.ping = this.add(new Setting("Ping", false, this::lambda$new$40));
        this.speed = this.add(new Setting("Speed", false, this::lambda$new$41));
        this.tps = this.add(new Setting("TPS", false, this::lambda$new$42));
        this.fps = this.add(new Setting("FPS", false, this::lambda$new$43));
        this.time = this.add(new Setting("Time", false, this::lambda$new$44));
        this.timer = new Timer();
        this.players = new HashMap();
        HUD.INSTANCE = this;
    }
    
    private boolean lambda$new$19(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$6(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$38(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$10(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.skeetBar.isOpen();
    }
    
    private boolean lambda$new$18(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private Map getTextRadarMap() {
        Map<String, Integer> sortByValue = new HashMap<String, Integer>();
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        final StringBuilder sb = new StringBuilder();
        for (final EntityPlayer entityPlayer : HUD.mc.world.playerEntities) {
            if (!entityPlayer.isInvisible()) {
                if (Integer.valueOf(HUD.mc.player.getName().hashCode()).equals(entityPlayer.getName().hashCode())) {
                    return null;
                }
                final int n = (int)HUD.mc.player.getDistance((Entity)entityPlayer);
                final String format = decimalFormat.format(n);
                if (n >= 25) {
                    sb.append(ChatFormatting.GREEN);
                }
                else if (n > 10) {
                    sb.append(ChatFormatting.YELLOW);
                }
                else {
                    sb.append(ChatFormatting.RED);
                }
                sb.append(format);
                sortByValue.put((Managers.FRIENDS.isCool(entityPlayer.getName()) ? (ChatFormatting.GOLD + "< > " + ChatFormatting.RESET) : "") + (Managers.FRIENDS.isFriend(entityPlayer) ? ChatFormatting.AQUA : ChatFormatting.RESET) + entityPlayer.getName() + " " + ChatFormatting.WHITE + "[" + ChatFormatting.RESET + (Object)sb + "m" + ChatFormatting.WHITE + "] " + ChatFormatting.GREEN, (int)HUD.mc.player.getDistance((Entity)entityPlayer));
                sb.setLength(0);
                return null;
            }
        }
        if (!sortByValue.isEmpty()) {
            sortByValue = (Map<String, Integer>)MathUtil.sortByValue((Map)sortByValue, false);
        }
        return sortByValue;
    }
    
    private boolean lambda$new$7(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$25(final Boolean b) {
        return this.greeter.isOpen() && this.greeterMode.getValue() == GreeterMode.PLAYER && this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$30(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private void drawLagOMeter() {
        final int scaledWidth = Managers.TEXT.scaledWidth;
        if (Managers.SERVER.isServerNotResponding()) {
            final String string = ChatFormatting.RED + (this.lowerCase.getValue() ? "Server is lagging for ".toLowerCase() : "Server is lagging for ") + MathUtil.round(Managers.SERVER.serverRespondingTime() / 1000.0f, 1) + "s.";
            Managers.TEXT.drawString(string, scaledWidth / 2.0f - Managers.TEXT.getStringWidth(string) / 2.0f + 2.0f, 20.0f, this.color, true);
        }
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$24(final GreeterMode greeterMode) {
        return this.page.getValue() == Page.ELEMENTS && this.greeter.isOpen();
    }
    
    private void drawPvPInfo() {
        final float n = Managers.TEXT.scaledHeight / 2.0f;
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(HUD::lambda$drawPvPInfo$46).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        final int ping = Managers.SERVER.getPing();
        final EntityPlayer closestEnemy = EntityUtil.getClosestEnemy(7.0);
        final String string = String.valueOf((sum != 0) ? ChatFormatting.GREEN : ChatFormatting.RED) + sum;
        final String value = String.valueOf((closestEnemy != null && HUD.mc.player.getDistance((Entity)closestEnemy) <= (float)Aura.INSTANCE.range.getValue()) ? ChatFormatting.GREEN : ChatFormatting.DARK_RED);
        final String value2 = String.valueOf((closestEnemy != null && HUD.mc.player.getDistance((Entity)closestEnemy) <= 5.0f && AutoTrap.INSTANCE.isOn()) ? ChatFormatting.GREEN : ChatFormatting.DARK_RED);
        final String s = "HTR";
        final String s2 = "PLR";
        String s3;
        if (ping < 40) {
            s3 = String.valueOf(ChatFormatting.GREEN);
        }
        else if (ping < 65) {
            s3 = String.valueOf(ChatFormatting.DARK_GREEN);
        }
        else if (ping < 80) {
            s3 = String.valueOf(ChatFormatting.YELLOW);
        }
        else if (ping < 110) {
            s3 = String.valueOf(ChatFormatting.RED);
        }
        else if (ping < 160) {
            s3 = String.valueOf(ChatFormatting.DARK_RED);
        }
        else {
            s3 = String.valueOf(ChatFormatting.DARK_RED);
        }
        String s4;
        if (EntityUtil.getUnsafeBlocksList(new Vec3d(HUD.mc.player.posX, HUD.mc.player.posY + 0.5, HUD.mc.player.posZ), 0, false).size() != 0) {
            s4 = String.valueOf(ChatFormatting.DARK_RED);
        }
        else {
            s4 = String.valueOf(ChatFormatting.GREEN);
        }
        Managers.TEXT.drawString(value + s, 2.0f, n - 20.0f, this.color, true);
        Managers.TEXT.drawString(value2 + s2, 2.0f, n - 10.0f, this.color, true);
        Managers.TEXT.drawString(s3 + ping + " MS", 2.0f, n, this.color, true);
        Managers.TEXT.drawString(string, 2.0f, n + 10.0f, this.color, true);
        Managers.TEXT.drawString(s4 + "LBY", 2.0f, n + 20.0f, this.color, true);
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$39(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.potions.isOpen();
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final int scaledWidth = Managers.TEXT.scaledWidth;
        final int scaledHeight = Managers.TEXT.scaledHeight;
        this.color = ColorUtil.toRGBA(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue());
        if (this.watermark.getValue()) {
            final String string = (String)this.watermarkString.getValue() + " ";
            final String string2 = (this.watermarkVerColor.getValue() ? String.valueOf(ChatFormatting.WHITE) : "") + (this.watermarkShort.getValue() ? "" : "alpha");
            if (ClickGui.INSTANCE.rainbow.getValue()) {
                if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                    Managers.TEXT.drawString((this.lowerCase.getValue() ? string.toLowerCase() : string) + string2, 2.0f, (float)(int)this.waterMarkY.getValue(), Managers.COLORS.getRainbow().getRGB(), true);
                }
                else if (this.watermarkVerColor.getValue()) {
                    this.drawDoubleRainbowRollingString(((boolean)this.lowerCase.getValue()) ? string.toLowerCase() : string, string2, 2.0f, (float)(int)this.waterMarkY.getValue());
                }
                else {
                    Managers.TEXT.drawRollingRainbowString((this.lowerCase.getValue() ? string.toLowerCase() : string) + string2, 2.0f, (float)(int)this.waterMarkY.getValue(), true);
                }
            }
            else {
                Managers.TEXT.drawString((this.lowerCase.getValue() ? string.toLowerCase() : string) + string2, 2.0f, (float)(int)this.waterMarkY.getValue(), this.color, true);
            }
        }
        if (this.combatCount.getValue()) {
            this.drawCombatCount();
        }
        final Color color = new Color(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue());
        if (this.skeetBar.getValue()) {
            if (this.jamie.getValue()) {
                RenderUtil.drawHGradientRect(0.0f, 0.0f, scaledWidth / 5.0f, 1.0f, ColorUtil.toRGBA(0, 180, 255), ColorUtil.toRGBA(255, 180, 255));
                RenderUtil.drawHGradientRect(scaledWidth / 5.0f, 0.0f, scaledWidth / 5.0f * 2.0f, 1.0f, ColorUtil.toRGBA(255, 180, 255), ColorUtil.toRGBA(255, 255, 255));
                RenderUtil.drawHGradientRect(scaledWidth / 5.0f * 2.0f, 0.0f, scaledWidth / 5.0f * 3.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255), ColorUtil.toRGBA(255, 255, 255));
                RenderUtil.drawHGradientRect(scaledWidth / 5.0f * 3.0f, 0.0f, scaledWidth / 5.0f * 4.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255), ColorUtil.toRGBA(255, 180, 255));
                RenderUtil.drawHGradientRect(scaledWidth / 5.0f * 4.0f, 0.0f, (float)scaledWidth, 1.0f, ColorUtil.toRGBA(255, 180, 255), ColorUtil.toRGBA(0, 180, 255));
            }
            if ((boolean)ClickGui.INSTANCE.rainbow.getValue() && ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING && !(boolean)this.jamie.getValue()) {
                final int[] array = { 1 };
                RenderUtil.drawHGradientRect(0.0f, 0.0f, scaledWidth / 2.0f, 1.0f, ColorUtil.rainbow(array[0] * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB(), ColorUtil.rainbow(20 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB());
                RenderUtil.drawHGradientRect(scaledWidth / 2.0f, 0.0f, (float)scaledWidth, 1.0f, ColorUtil.rainbow(20 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB(), ColorUtil.rainbow(40 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB());
                ++array[0];
            }
            if (!(boolean)ClickGui.INSTANCE.rainbow.getValue() && !(boolean)this.jamie.getValue()) {
                RenderUtil.drawHGradientRect(0.0f, 0.0f, scaledWidth / 2.0f, 1.0f, ColorUtil.pulseColor(color, 50, 1000).getRGB(), ColorUtil.pulseColor(color, 200, 1).getRGB());
                RenderUtil.drawHGradientRect(scaledWidth / 2.0f, 0.0f, (float)scaledWidth, 1.0f, ColorUtil.pulseColor(color, 200, 1).getRGB(), ColorUtil.pulseColor(color, 50, 1000).getRGB());
            }
        }
        if (this.textRadar.getValue()) {
            this.drawTextRadar(((boolean)this.watermark.getValue()) ? ((int)this.waterMarkY.getValue() + 2) : 2);
        }
        if (this.pvp.getValue()) {
            this.drawPvPInfo();
        }
        this.color = ColorUtil.toRGBA(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue());
        if (this.idWatermark.getValue()) {
            final String s = "Rebirth ";
            final String s2 = "alpha";
            final float n = Managers.TEXT.scaledHeight / 2.0f - 30.0f;
            if (ClickGui.INSTANCE.rainbow.getValue()) {
                if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                    Managers.TEXT.drawString(s + s2, 2.0f, n, Managers.COLORS.getRainbow().getRGB(), true);
                }
                else {
                    Managers.TEXT.drawString(s, 2.0f, n, -1, true);
                    Managers.TEXT.drawRollingRainbowString(s2, (float)(Managers.TEXT.getStringWidth(s) + 2), n, true);
                }
            }
            else {
                Managers.TEXT.drawString(s + ChatFormatting.LIGHT_PURPLE + s2, 2.0f, n, this.color, true);
            }
        }
        int n2 = 20;
        final boolean b = HUD.mc.currentScreen instanceof GuiChat;
        final long count = Managers.MODULES.modules.stream().filter(HUD::lambda$onRender2D$45).count();
        int n3 = (int)((b && !(boolean)this.renderingUp.getValue() && (int)this.arrayListOffset.getValue() < 14) ? 14 : this.arrayListOffset.getValue());
        final int n4 = this.jamieArray.getValue() ? ColorUtil.injectAlpha(this.getJamieColor(n2 + 1), 60) : (this.arrayListRectColor.getValue() ? (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.toRGBA(ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRed(), ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getGreen(), ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getBlue(), 60) : ColorUtil.toRGBA(Managers.COLORS.getRainbow().getRed(), Managers.COLORS.getRainbow().getGreen(), Managers.COLORS.getRainbow().getBlue(), 60)) : ColorUtil.toRGBA(ColorUtil.pulseColor(color, n2, 14).getRed(), ColorUtil.pulseColor(color, n2, 14).getGreen(), ColorUtil.pulseColor(color, n2, 14).getBlue(), 60)) : ColorUtil.toRGBA(10, 10, 10, 60));
        final int n5 = this.jamieArray.getValue() ? ColorUtil.injectAlpha(this.getJamieColor(n2 + 1), 60) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.toRGBA(ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRed(), ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getGreen(), ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getBlue(), 60) : ColorUtil.toRGBA(Managers.COLORS.getRainbow().getRed(), Managers.COLORS.getRainbow().getGreen(), Managers.COLORS.getRainbow().getBlue(), 60)) : ColorUtil.toRGBA(ColorUtil.pulseColor(color, n2, 14).getRed(), ColorUtil.pulseColor(color, n2, 14).getGreen(), ColorUtil.pulseColor(color, n2, 14).getBlue(), 60));
        if (this.arrayList.getValue()) {
            if (this.renderingUp.getValue()) {
                if (b && (boolean)this.hideInChat.getValue()) {
                    Managers.TEXT.drawString(count + " mods enabled", (float)(scaledWidth - 2 - Managers.TEXT.getStringWidth(count + " mods enabled")), (float)(2 + n3), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : this.color, true);
                }
                else if (this.ordering.getValue() == ModuleManager.Ordering.ABC) {
                    int n6 = 0;
                    if (n6 < Managers.MODULES.sortedAbc.size()) {
                        String string3 = Managers.MODULES.sortedAbc.get(n6);
                        if (this.forgeHax.getValue()) {
                            string3 = Managers.MODULES.sortedAbc.get(n6) + ChatFormatting.RESET + "<";
                        }
                        if (this.arrayListRect.getValue()) {
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - 1, (n3 == 0) ? 0 : (2 + n3), scaledWidth, 2 + n3 + 10, n4);
                        }
                        if (this.arrayListGlow.getValue()) {
                            RenderUtil.drawGlow((double)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - 1), (n3 == 0) ? 0.0 : ((double)(2 + n3 - 4)), (double)scaledWidth, (double)(2 + n3 + 15), n5);
                        }
                        if (this.arrayListLine.getValue()) {
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - 2, (n3 == 0) ? 0 : (2 + n3 - 1), scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - 1, 2 + n3 + 10, ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                            int n7 = n6 + 1;
                            if (n7 >= Managers.MODULES.sortedAbc.size()) {
                                n7 = n6;
                            }
                            String string4 = Managers.MODULES.sortedAbc.get(n7);
                            if (this.forgeHax.getValue()) {
                                string4 = Managers.MODULES.sortedAbc.get(n7) + ChatFormatting.RESET + "<";
                            }
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - 2, 2 + (n3 + 1) + 8, (n7 == n6) ? scaledWidth : (scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) + ((this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3)) - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string4.toLowerCase()) : Managers.TEXT.getStringWidth(string4))) - 1), 2 + (n3 + 1) + 9, ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                        }
                        Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string3.toLowerCase() : string3, (float)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string3.toLowerCase()) : Managers.TEXT.getStringWidth(string3))), (float)(3 + n3), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                        ++n6;
                        return;
                    }
                }
                else {
                    int n8 = 0;
                    if (n8 < Managers.MODULES.sortedLength.size()) {
                        String s3 = Managers.MODULES.sortedLength.get(n8).getArrayListInfo();
                        if (this.forgeHax.getValue()) {
                            s3 = s3 + ChatFormatting.RESET + "<";
                        }
                        if (this.arrayListRect.getValue()) {
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - 1, (n3 == 0) ? 0 : (2 + n3), scaledWidth, 2 + n3 + 10, n4);
                        }
                        if (this.arrayListGlow.getValue()) {
                            RenderUtil.drawGlow((double)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - 1), (n3 == 0) ? 0.0 : ((double)(2 + n3 - 4)), (double)scaledWidth, (double)(2 + n3 + 15), n5);
                        }
                        if (this.arrayListLine.getValue()) {
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - 2, (n3 == 0) ? 0 : (2 + n3), scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - 1, 2 + n3 + 11, ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                            int n9 = n8 + 1;
                            if (n9 >= Managers.MODULES.sortedLength.size()) {
                                n9 = n8;
                            }
                            final Module module = Managers.MODULES.sortedLength.get(n9);
                            String s4 = module.getArrayListInfo();
                            if (this.forgeHax.getValue()) {
                                s4 = module.getArrayListInfo() + ChatFormatting.RESET + "<";
                            }
                            Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - 2, 2 + (n3 + 1) + 9, (n9 == n8) ? scaledWidth : (scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) + ((this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3)) - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s4.toLowerCase()) : Managers.TEXT.getStringWidth(s4))) - 1), 2 + (n3 + 1) + 10, ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                        }
                        Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? s3.toLowerCase() : s3, (float)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s3.toLowerCase()) : Managers.TEXT.getStringWidth(s3))), (float)(3 + n3), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                        ++n8;
                        return;
                    }
                }
            }
            else if (b && (boolean)this.hideInChat.getValue()) {
                Managers.TEXT.drawString(count + " mods enabled", (float)(scaledWidth - 2 - Managers.TEXT.getStringWidth(count + " mods enabled")), (float)(scaledHeight - n3 - 11), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : this.color, true);
            }
            else if (this.ordering.getValue() == ModuleManager.Ordering.ABC) {
                int n10 = 0;
                if (n10 < Managers.MODULES.sortedAbc.size()) {
                    String string5 = Managers.MODULES.sortedAbc.get(n10);
                    if (this.forgeHax.getValue()) {
                        string5 = Managers.MODULES.sortedAbc.get(n10) + ChatFormatting.RESET + "<";
                    }
                    n3 += 10;
                    if (this.arrayListRect.getValue()) {
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - 1, scaledHeight - n3, scaledWidth, (n3 == 1) ? scaledHeight : (scaledHeight - n3 + 10), n4);
                    }
                    if (this.arrayListGlow.getValue()) {
                        RenderUtil.drawGlow((double)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - 1), (double)(scaledHeight - n3 - 4), (double)scaledWidth, (n3 == 1) ? ((double)scaledHeight) : ((double)(scaledHeight - n3 + 15)), n5);
                    }
                    if (this.arrayListLine.getValue()) {
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - 2, scaledHeight - n3, scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - 1, (n3 == 1) ? scaledHeight : (scaledHeight - n3 + 10), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                        int n11 = n10 + 1;
                        if (n11 >= Managers.MODULES.sortedAbc.size()) {
                            n11 = n10;
                        }
                        String string6 = Managers.MODULES.sortedAbc.get(n11);
                        if (this.forgeHax.getValue()) {
                            string6 = Managers.MODULES.sortedAbc.get(n11) + ChatFormatting.RESET + "<";
                        }
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - 2, scaledHeight - n3 - 1, (n11 == n10) ? scaledWidth : (scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) + ((this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5)) - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string6.toLowerCase()) : Managers.TEXT.getStringWidth(string6))) - 1), (n3 == 1) ? scaledHeight : (scaledHeight - n3), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                    }
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string5.toLowerCase() : string5, (float)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string5.toLowerCase()) : Managers.TEXT.getStringWidth(string5))), (float)(scaledHeight - n3 + 1), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                    ++n10;
                    return;
                }
            }
            else {
                int n12 = 0;
                if (n12 < Managers.MODULES.sortedLength.size()) {
                    String s5 = Managers.MODULES.sortedLength.get(n12).getArrayListInfo();
                    if (this.forgeHax.getValue()) {
                        s5 = s5 + ChatFormatting.RESET + "<";
                    }
                    n3 += 10;
                    if (this.arrayListRect.getValue()) {
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - 1, scaledHeight - n3, scaledWidth, (n3 == 1) ? scaledHeight : (scaledHeight - n3 + 10), n4);
                    }
                    if (this.arrayListGlow.getValue()) {
                        RenderUtil.drawGlow((double)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - 1), (double)(scaledHeight - n3 - 4), (double)scaledWidth, (n3 == 1) ? ((double)scaledHeight) : ((double)(scaledHeight - n3 + 15)), n5);
                    }
                    if (this.arrayListLine.getValue()) {
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - 2, scaledHeight - n3, scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - 1, (n3 == 1) ? scaledHeight : (scaledHeight - n3 + 10), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                        int n13 = n12 + 1;
                        if (n13 >= Managers.MODULES.sortedLength.size()) {
                            n13 = n12;
                        }
                        final Module module2 = Managers.MODULES.sortedLength.get(n13);
                        String s6 = module2.getArrayListInfo();
                        if (this.forgeHax.getValue()) {
                            s6 = module2.getArrayListInfo() + ChatFormatting.RESET + "<";
                        }
                        Gui.drawRect(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - 2, scaledHeight - n3 - 1, (n13 == n12) ? scaledWidth : (scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) + ((this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5)) - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s6.toLowerCase()) : Managers.TEXT.getStringWidth(s6))) - 1), scaledHeight - n3, ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()));
                    }
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? s5.toLowerCase() : s5, (float)(scaledWidth - 2 - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(s5.toLowerCase()) : Managers.TEXT.getStringWidth(s5))), (float)(scaledHeight - n3 + 1), ((boolean)this.jamieArray.getValue()) ? this.getJamieColor(n2 - 2) : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                    ++n12;
                    return;
                }
            }
        }
        final String s7 = this.grayColors.getValue() ? String.valueOf(ChatFormatting.GRAY) : "";
        int n14 = (HUD.mc.currentScreen instanceof GuiChat && (boolean)this.renderingUp.getValue()) ? 13 : (this.renderingUp.getValue() ? -2 : 0);
        if (this.renderingUp.getValue()) {
            if (this.potions.getValue()) {
                final Iterator<PotionEffect> iterator = (Iterator<PotionEffect>)new ArrayList<Object>(HUD.mc.player.getActivePotionEffects()).iterator();
                if (iterator.hasNext()) {
                    final PotionEffect potionEffect = iterator.next();
                    final String coloredPotionString = this.getColoredPotionString(potionEffect);
                    n14 += 10;
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? coloredPotionString.toLowerCase() : coloredPotionString, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(coloredPotionString.toLowerCase()) : Managers.TEXT.getStringWidth(coloredPotionString)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)this.potionColor.getValue()) ? potionEffect.getPotion().getLiquidColor() : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                    return;
                }
            }
            if (this.speed.getValue()) {
                final String string7 = s7 + "Speed " + ChatFormatting.WHITE + Managers.SPEED.getSpeedKpH() + " km/h";
                n14 += 10;
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string7.toLowerCase() : string7, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string7.toLowerCase()) : Managers.TEXT.getStringWidth(string7)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            if (this.time.getValue()) {
                final String string8 = s7 + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                n14 += 10;
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string8.toLowerCase() : string8, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string8.toLowerCase()) : Managers.TEXT.getStringWidth(string8)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            if (this.tps.getValue()) {
                final String string9 = s7 + "TPS " + ChatFormatting.WHITE + Managers.SERVER.getTPS();
                n14 += 10;
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string9.toLowerCase() : string9, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string9.toLowerCase()) : Managers.TEXT.getStringWidth(string9)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            final String string10 = s7 + "FPS " + ChatFormatting.WHITE + Minecraft.getDebugFPS();
            final String string11 = s7 + "Ping " + ChatFormatting.WHITE + Managers.SERVER.getPing();
            if (Managers.TEXT.getStringWidth(string11) > Managers.TEXT.getStringWidth(string10)) {
                if (this.ping.getValue()) {
                    n14 += 10;
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string11.toLowerCase() : string11, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string11.toLowerCase()) : Managers.TEXT.getStringWidth(string11)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                    ++n2;
                }
                if (this.fps.getValue()) {
                    n14 += 10;
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string10.toLowerCase() : string10, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string10.toLowerCase()) : Managers.TEXT.getStringWidth(string10)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                }
            }
            else {
                if (this.fps.getValue()) {
                    n14 += 10;
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string10.toLowerCase() : string10, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string10.toLowerCase()) : Managers.TEXT.getStringWidth(string10)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                    ++n2;
                }
                if (this.ping.getValue()) {
                    n14 += 10;
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string11.toLowerCase() : string11, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string11.toLowerCase()) : Managers.TEXT.getStringWidth(string11)) - 2), (float)(scaledHeight - 2 - n14), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                }
            }
        }
        else {
            if (this.potions.getValue()) {
                final Iterator<PotionEffect> iterator2 = (Iterator<PotionEffect>)new ArrayList<Object>(HUD.mc.player.getActivePotionEffects()).iterator();
                if (iterator2.hasNext()) {
                    final PotionEffect potionEffect2 = iterator2.next();
                    final String coloredPotionString2 = this.getColoredPotionString(potionEffect2);
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? coloredPotionString2.toLowerCase() : coloredPotionString2, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(coloredPotionString2.toLowerCase()) : Managers.TEXT.getStringWidth(coloredPotionString2)) - 2), (float)(2 + n14++ * 10), ((boolean)this.potionColor.getValue()) ? potionEffect2.getPotion().getLiquidColor() : (ClickGui.INSTANCE.rainbow.getValue() ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB()), true);
                    return;
                }
            }
            if (this.speed.getValue()) {
                final String string12 = s7 + "Speed " + ChatFormatting.WHITE + Managers.SPEED.getSpeedKpH() + " km/h";
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string12.toLowerCase() : string12, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string12.toLowerCase()) : Managers.TEXT.getStringWidth(string12)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            if (this.time.getValue()) {
                final String string13 = s7 + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string13.toLowerCase() : string13, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string13.toLowerCase()) : Managers.TEXT.getStringWidth(string13)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            if (this.tps.getValue()) {
                final String string14 = s7 + "TPS " + ChatFormatting.WHITE + Managers.SERVER.getTPS();
                Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string14.toLowerCase() : string14, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string14.toLowerCase()) : Managers.TEXT.getStringWidth(string14)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                ++n2;
            }
            final String string15 = s7 + "FPS " + ChatFormatting.WHITE + Minecraft.getDebugFPS();
            final String string16 = s7 + "Ping " + ChatFormatting.WHITE + Managers.SERVER.getPing();
            if (Managers.TEXT.getStringWidth(string16) > Managers.TEXT.getStringWidth(string15)) {
                if (this.ping.getValue()) {
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string16.toLowerCase() : string16, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string16.toLowerCase()) : Managers.TEXT.getStringWidth(string16)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                    ++n2;
                }
                if (this.fps.getValue()) {
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string15.toLowerCase() : string15, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string15.toLowerCase()) : Managers.TEXT.getStringWidth(string15)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                }
            }
            else {
                if (this.fps.getValue()) {
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string15.toLowerCase() : string15, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string15.toLowerCase()) : Managers.TEXT.getStringWidth(string15)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                    ++n2;
                }
                if (this.ping.getValue()) {
                    Managers.TEXT.drawString(((boolean)this.lowerCase.getValue()) ? string16.toLowerCase() : string16, (float)(scaledWidth - (this.lowerCase.getValue() ? Managers.TEXT.getStringWidth(string16.toLowerCase()) : Managers.TEXT.getStringWidth(string16)) - 2), (float)(2 + n14++ * 10), ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ((ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.ROLLING) ? ColorUtil.rainbow(n2 * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB() : Managers.COLORS.getRainbow().getRGB()) : ColorUtil.pulseColor(color, n2, 14).getRGB(), true);
                }
            }
        }
        final boolean equals = Integer.valueOf("Hell".hashCode()).equals(HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().hashCode());
        final int n15 = (int)HUD.mc.player.posX;
        final int n16 = (int)HUD.mc.player.posY;
        final int n17 = (int)HUD.mc.player.posZ;
        final float n18 = equals ? 8.0f : 0.125f;
        final int n19 = (int)(HUD.mc.player.posX * n18);
        final int n20 = (int)(HUD.mc.player.posZ * n18);
        final int n21 = (int)MathHelper.wrapDegrees(HUD.mc.player.rotationYaw);
        final int n22 = this.coords.getValue() ? 0 : 11;
        int n23 = (HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0;
        final String string17 = (this.lowerCase.getValue() ? "XYZ: ".toLowerCase() : "XYZ: ") + ChatFormatting.WHITE + (equals ? (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]" + ChatFormatting.WHITE) : (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]"));
        String string18 = this.direction.getValue() ? Managers.ROTATIONS.getDirection4D(false) : "";
        String s8 = this.direction.getValue() ? ((this.lowerCase.getValue() ? "Yaw: ".toLowerCase() : "Yaw: ") + ChatFormatting.WHITE + n21) : "";
        final String s9 = this.coords.getValue() ? string17 : "";
        n23 += 10;
        if (HUD.mc.currentScreen instanceof GuiChat && (boolean)this.direction.getValue()) {
            s8 = "";
            string18 = (this.lowerCase.getValue() ? "Yaw: ".toLowerCase() : "Yaw: ") + ChatFormatting.WHITE + n21 + ChatFormatting.RESET + " " + this.getFacingDirectionShort();
        }
        if (ClickGui.INSTANCE.rainbow.getValue()) {
            final String s10 = this.coords.getValue() ? ((this.lowerCase.getValue() ? "XYZ: ".toLowerCase() : "XYZ: ") + ChatFormatting.WHITE + (equals ? (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]" + ChatFormatting.WHITE) : (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]"))) : "";
            if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                Managers.TEXT.drawString(string18, 2.0f, (float)(scaledHeight - n23 - 11 + n22), Managers.COLORS.getRainbow().getRGB(), true);
                Managers.TEXT.drawString(s8, 2.0f, (float)(scaledHeight - n23 - 22 + n22), Managers.COLORS.getRainbow().getRGB(), true);
                Managers.TEXT.drawString(s10, 2.0f, (float)(scaledHeight - n23), Managers.COLORS.getRainbow().getRGB(), true);
            }
            else {
                if (HUD.mc.currentScreen instanceof GuiChat && (boolean)this.direction.getValue()) {
                    this.drawDoubleRainbowRollingString(((boolean)this.lowerCase.getValue()) ? "Yaw: ".toLowerCase() : "Yaw: ", String.valueOf(ChatFormatting.WHITE) + n21, 2.0f, (float)(scaledHeight - n23 - 11 + n22));
                    Managers.TEXT.drawRollingRainbowString(" " + this.getFacingDirectionShort(), 2.0f + Managers.TEXT.getStringWidth("Yaw: " + ChatFormatting.WHITE + n21), (float)(scaledHeight - n23 - 11 + n22), true);
                }
                else {
                    Managers.TEXT.drawRollingRainbowString(((boolean)this.direction.getValue()) ? string18 : "", 2.0f, (float)(scaledHeight - n23 - 11 + n22), true);
                    this.drawDoubleRainbowRollingString(((boolean)this.direction.getValue()) ? (this.lowerCase.getValue() ? "Yaw: ".toLowerCase() : "Yaw: ") : "", ((boolean)this.direction.getValue()) ? (String.valueOf(ChatFormatting.WHITE) + n21) : "", 2.0f, (float)(scaledHeight - n23 - 22 + n22));
                }
                this.drawDoubleRainbowRollingString(((boolean)this.coords.getValue()) ? (this.lowerCase.getValue() ? "XYZ: ".toLowerCase() : "XYZ: ") : "", ((boolean)this.coords.getValue()) ? (ChatFormatting.WHITE + (equals ? (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]" + ChatFormatting.WHITE) : (n15 + ", " + n16 + ", " + n17 + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + n19 + ", " + n20 + ChatFormatting.GRAY + "]"))) : "", 2.0f, (float)(scaledHeight - n23));
            }
        }
        else {
            Managers.TEXT.drawString(string18, 2.0f, (float)(scaledHeight - n23 - 11 + n22), this.color, true);
            Managers.TEXT.drawString(s8, 2.0f, (float)(scaledHeight - n23 - 22 + n22), this.color, true);
            Managers.TEXT.drawString(s9, 2.0f, (float)(scaledHeight - n23), this.color, true);
        }
        if (this.armor.getValue()) {
            this.drawArmorHUD();
        }
        if (this.greeter.getValue()) {
            this.drawWelcomer();
        }
        if (this.lag.getValue()) {
            this.drawLagOMeter();
        }
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    static {
        HUD.INSTANCE = new HUD();
    }
    
    private boolean lambda$new$14(final Boolean b) {
        return this.watermark.isOpen() && this.page.getValue() == Page.ELEMENTS;
    }
    
    private void drawTextRadar(final int n) {
        if (!this.players.isEmpty()) {
            final int n2 = Managers.TEXT.getFontHeight() + 7 + n;
            final Iterator<Map.Entry<String, V>> iterator = this.players.entrySet().iterator();
            if (iterator.hasNext()) {
                final String string = iterator.next().getKey() + " ";
                final int n3 = Managers.TEXT.getFontHeight() + 1;
                if (ClickGui.INSTANCE.rainbow.getValue()) {
                    if (ClickGui.INSTANCE.hudRainbow.getValue() == ClickGui.HudRainbow.STATIC) {
                        Managers.TEXT.drawString(string, 2.0f, (float)n2, ColorUtil.rainbow((int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB(), true);
                    }
                    else {
                        Managers.TEXT.drawString(string, 2.0f, (float)n2, ColorUtil.rainbow((int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB(), true);
                    }
                }
                else {
                    Managers.TEXT.drawString(string, 2.0f, (float)n2, this.color, true);
                }
            }
        }
    }
    
    private boolean lambda$new$35(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen() && (boolean)this.arrayListRect.getValue();
    }
    
    private boolean lambda$new$28(final Integer n) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$13(final Boolean b) {
        return this.watermark.isOpen() && this.page.getValue() == Page.ELEMENTS;
    }
    
    private String getFacingDirectionShort() {
        final int yaw4D = Managers.ROTATIONS.getYaw4D();
        if (yaw4D == 0) {
            return "(+Z)";
        }
        if (yaw4D == 1) {
            return "(-X)";
        }
        if (yaw4D == 2) {
            return "(-Z)";
        }
        if (yaw4D == 3) {
            return "(+X)";
        }
        return "Loading...";
    }
    
    private void drawCombatCount() {
        final int scaledWidth = Managers.TEXT.scaledWidth;
        final int scaledHeight = Managers.TEXT.scaledHeight;
        final int n = scaledWidth / 2;
        final int n2 = scaledHeight - (int)this.combatCountY.getValue();
        final int n3 = n + (int)this.combatCountX.getValue();
        GlStateManager.enableTexture2D();
        final int itemCount = InventoryUtil.getItemCount(Items.TOTEM_OF_UNDYING);
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.TOTEM_OF_UNDYING), n3, n2);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, new ItemStack(Items.TOTEM_OF_UNDYING), n3, n2, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        Managers.TEXT.drawStringWithShadow(String.valueOf(itemCount), (float)(n3 + 19 - 2 - Managers.TEXT.getStringWidth(String.valueOf(itemCount))), (float)(n2 + 9), 16777215);
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        final int itemCount2 = InventoryUtil.getItemCount(Items.END_CRYSTAL);
        final int n4 = n3 + 20;
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.END_CRYSTAL), n4, n2);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, new ItemStack(Items.END_CRYSTAL), n4, n2, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        Managers.TEXT.drawStringWithShadow(String.valueOf(itemCount2), (float)(n4 + 19 - 2 - Managers.TEXT.getStringWidth(String.valueOf(itemCount2))), (float)(n2 + 9), 16777215);
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        final int itemCount3 = InventoryUtil.getItemCount(Items.EXPERIENCE_BOTTLE);
        final int n5 = n4 + 20;
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.EXPERIENCE_BOTTLE), n5, n2);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, new ItemStack(Items.EXPERIENCE_BOTTLE), n5, n2, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        Managers.TEXT.drawStringWithShadow(String.valueOf(itemCount3), (float)(n5 + 19 - 2 - Managers.TEXT.getStringWidth(String.valueOf(itemCount3))), (float)(n2 + 9), 16777215);
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        final int itemCount4 = InventoryUtil.getItemCount(Items.GOLDEN_APPLE);
        final int n6 = n5 + 20;
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.GOLDEN_APPLE), n6, n2);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, new ItemStack(Items.GOLDEN_APPLE), n6, n2, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        Managers.TEXT.drawStringWithShadow(String.valueOf(itemCount4), (float)(n6 + 19 - 2 - Managers.TEXT.getStringWidth(String.valueOf(itemCount4))), (float)(n2 + 9), 16777215);
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        final int itemCount5 = InventoryUtil.getItemCount(Item.getItemFromBlock(Blocks.OBSIDIAN));
        final int n7 = n6 + 20;
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Blocks.OBSIDIAN), n7, n2);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, new ItemStack(Blocks.OBSIDIAN), n7, n2, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        Managers.TEXT.drawStringWithShadow(String.valueOf(itemCount5), (float)(n7 + 19 - 2 - Managers.TEXT.getStringWidth(String.valueOf(itemCount5))), (float)(n2 + 9), 16777215);
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    private boolean lambda$new$40(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$4(final ModuleManager.Ordering ordering) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$15(final Integer n) {
        return this.page.getValue() == Page.ELEMENTS && this.watermark.isOpen();
    }
    
    private boolean lambda$new$44(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private void drawArmorHUD() {
        final int scaledWidth = Managers.TEXT.scaledWidth;
        final int scaledHeight = Managers.TEXT.scaledHeight;
        GlStateManager.enableTexture2D();
        final int n = scaledWidth / 2;
        int n2 = 0;
        final int n3 = scaledHeight - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
        final Iterator iterator = HUD.mc.player.inventory.armorInventory.iterator();
        if (!iterator.hasNext()) {
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
            return;
        }
        final ItemStack itemStack = iterator.next();
        ++n2;
        if (itemStack.isEmpty()) {
            return;
        }
        final int n4 = n - 90 + (9 - n2) * 20 + 2;
        GlStateManager.enableDepth();
        HUD.mc.getRenderItem().zLevel = 200.0f;
        HUD.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n4, n3);
        HUD.mc.getRenderItem().renderItemOverlayIntoGUI(HUD.mc.fontRenderer, itemStack, n4, n3, "");
        HUD.mc.getRenderItem().zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        final String s = (itemStack.getCount() > 1) ? String.valueOf(itemStack.getCount()) : "";
        Managers.TEXT.drawStringWithShadow(s, (float)(n4 + 19 - 2 - Managers.TEXT.getStringWidth(s)), (float)(n3 + 9), 16777215);
        final float n5 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
        final float n6 = 1.0f - n5;
        final int n7 = 100 - (int)(n6 * 100.0f);
        Managers.TEXT.drawStringWithShadow(String.valueOf(n7), n4 + 8 - Managers.TEXT.getStringWidth(String.valueOf(n7)) / 2.0f, (float)(n3 - 11), ColorUtil.toRGBA((int)(n6 * 255.0f), (int)(n5 * 255.0f), 0));
    }
    
    private boolean lambda$new$20(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private boolean lambda$new$12(final String s) {
        return this.page.getValue() == Page.ELEMENTS && this.watermark.isOpen();
    }
    
    private boolean lambda$new$29(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private void drawDoubleRainbowRollingString(final String s, final String s2, final float n, final float n2) {
        Managers.TEXT.drawRollingRainbowString(s, n, n2, true);
        Managers.TEXT.drawString(s2, n + Managers.TEXT.getStringWidth(s), n2, -1, true);
    }
    
    private boolean lambda$new$32(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS && this.arrayList.isOpen();
    }
    
    private boolean lambda$new$16(final Boolean b) {
        return this.page.getValue() == Page.ELEMENTS;
    }
    
    private enum Page
    {
        ELEMENTS("ELEMENTS", 0), 
        GLOBAL("GLOBAL", 1);
        
        private static final Page[] $VALUES;
        
        private Page(final String s, final int n) {
        }
        
        static {
            $VALUES = new Page[] { Page.ELEMENTS, Page.GLOBAL };
        }
    }
    
    private enum GreeterMode
    {
        CUSTOM("CUSTOM", 1);
        
        private static final GreeterMode[] $VALUES;
        
        PLAYER("PLAYER", 0);
        
        static {
            $VALUES = new GreeterMode[] { GreeterMode.PLAYER, GreeterMode.CUSTOM };
        }
        
        private GreeterMode(final String s, final int n) {
        }
    }
}
