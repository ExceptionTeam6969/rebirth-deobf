//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.events.impl.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FontMod extends Module
{
    public final Setting size;
    public final Setting metrics;
    public final Setting global;
    public final Setting style;
    public final Setting antiAlias;
    private boolean reload;
    public static FontMod INSTANCE;
    public final Setting font;
    
    @Override
    public void onTick() {
        if (this.reload) {
            Managers.TEXT.init();
            this.reload = false;
        }
    }
    
    public int getFont() {
        switch ((Style)this.style.getValue()) {
            case BOLD: {
                return 1;
            }
            case ITALIC: {
                return 2;
            }
            case BOLDITALIC: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
    
    public FontMod() {
        super("Fonts", "Custom font for all of the clients text. Use the font command", Category.CLIENT);
        this.font = this.add(new Setting("Font", "Arial"));
        this.antiAlias = this.add(new Setting("AntiAlias", true));
        this.metrics = this.add(new Setting("Metrics", true));
        this.global = this.add(new Setting("Global", false));
        this.size = this.add(new Setting("Size", 17, 12, 30));
        this.style = this.add(new Setting("Style", Style.PLAIN));
        FontMod.INSTANCE = this;
    }
    
    @Override
    public String getInfo() {
        return (String)this.font.getValue();
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (fullNullCheck()) {
            return;
        }
        final Setting setting;
        if (clientEvent.getStage() == 2 && (setting = clientEvent.getSetting()) != null && setting.getMod().equals(this)) {
            if (Integer.valueOf("Font".hashCode()).equals(setting.getName().hashCode()) && this < setting.getPlannedValue().toString()) {
                this.sendMessage(ChatFormatting.RED + "That font doesn't exist.");
                clientEvent.setCanceled(true);
                return;
            }
            this.reload = true;
        }
    }
    
    private enum Style
    {
        BOLDITALIC("BOLDITALIC", 3), 
        ITALIC("ITALIC", 2), 
        PLAIN("PLAIN", 0), 
        BOLD("BOLD", 1);
        
        private static final Style[] $VALUES;
        
        private Style(final String s, final int n) {
        }
        
        static {
            $VALUES = new Style[] { Style.PLAIN, Style.BOLD, Style.ITALIC, Style.BOLDITALIC };
        }
    }
}
