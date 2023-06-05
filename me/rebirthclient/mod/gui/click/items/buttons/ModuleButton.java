//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.*;
import me.rebirthclient.mod.gui.click.items.*;
import me.rebirthclient.mod.gui.click.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.impl.client.*;
import java.util.*;
import me.rebirthclient.mod.modules.settings.*;

public class ModuleButton extends Button
{
    private boolean subOpen;
    private final Module module;
    private List items;
    static final boolean $assertionsDisabled;
    private int progress;
    
    public void onKeyTyped(final char c, final int n) {
        super.onKeyTyped(c, n);
        if (!this.items.isEmpty() && this.subOpen) {
            final Iterator<Item> iterator = this.items.iterator();
            if (iterator.hasNext()) {
                final Item item = iterator.next();
                if (item.isHidden()) {
                    return;
                }
                item.onKeyTyped(c, n);
            }
        }
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (!this.items.isEmpty()) {
            this.drawGear();
            if (this.subOpen) {
                ++this.progress;
                final float n4 = 1.0f;
                final Iterator<Item> iterator = this.items.iterator();
                if (iterator.hasNext()) {
                    final Item item = iterator.next();
                    ++Component.counter1[0];
                    if (!item.isHidden()) {
                        float n5;
                        item.setLocation(this.x + 1.0f, this.y + (n5 = n4 + ClickGui.INSTANCE.getButtonHeight()));
                        item.setHeight(ClickGui.INSTANCE.getButtonHeight());
                        item.setWidth(this.width - 9);
                        item.drawScreen(n, n2, n3);
                        if (item instanceof PickerButton && ((PickerButton)item).setting.open) {
                            if (((PickerButton)item).setting.noRainbow) {
                                n5 += 110.0f;
                            }
                            else {
                                n5 += 120.0f;
                            }
                        }
                        if (item instanceof EnumButton && ((EnumButton)item).setting.open) {
                            final float n6 = n5 + ((EnumButton)item).setting.getValue().getClass().getEnumConstants().length * 12;
                        }
                    }
                    item.update();
                    return;
                }
            }
        }
        if (this.isHovering(n, n2) && ClickGui.INSTANCE.isOn()) {
            final String string = ChatFormatting.GRAY + this.module.getDescription();
            Gui.drawRect(0, ModuleButton.mc.currentScreen.height - 11, Managers.TEXT.getStringWidth(string) + 2, ModuleButton.mc.currentScreen.height, ColorUtil.injectAlpha(new Color(-1072689136), 200).getRGB());
            assert ModuleButton.mc.currentScreen != null;
            Managers.TEXT.drawStringWithShadow(string, 2.0f, (float)(ModuleButton.mc.currentScreen.height - 10), -1);
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (!this.items.isEmpty()) {
            if (n3 == 1 && this.isHovering(n, n2)) {
                this.subOpen = !this.subOpen;
                ModuleButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (this.subOpen) {
                final Iterator<Item> iterator = this.items.iterator();
                if (iterator.hasNext()) {
                    final Item item = iterator.next();
                    if (item.isHidden()) {
                        return;
                    }
                    item.mouseClicked(n, n2, n3);
                }
            }
        }
    }
    
    public void drawGear() {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        if (ClickGui.INSTANCE.gear.getValue()) {
            if (b2) {
                if (!this.subOpen) {
                    this.progress = 0;
                }
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                ModuleButton.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/gear.png"));
                GlStateManager.translate(this.getX() + this.getWidth() - 6.7f, this.getY() + 7.7f - 0.3f, 0.0f);
                GlStateManager.rotate(Component.calculateRotation((float)this.progress), 0.0f, 0.0f, 1.0f);
                RenderUtil.drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
            else {
                final String s = (this.module.isOn() || b) ? "" : String.valueOf(ChatFormatting.GRAY);
                final String s2 = this.subOpen ? "-" : "+";
                Managers.TEXT.drawStringWithShadow(s + s2, this.x - 1.5f + this.width - 7.4f + ((FontMod.INSTANCE.isOn() && Integer.valueOf("-".hashCode()).equals(s2.hashCode())) ? 1.0f : 0.0f), this.y - 2.2f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
            }
        }
    }
    
    public void initSettings() {
        final ArrayList<BooleanButton> items = new ArrayList<BooleanButton>();
        if (!this.module.getSettings().isEmpty()) {
            final Iterator<Setting> iterator = this.module.getSettings().iterator();
            if (iterator.hasNext()) {
                final Setting setting = iterator.next();
                if (setting.getValue() instanceof Boolean && !Integer.valueOf("Enabled".hashCode()).equals(setting.getName().hashCode())) {
                    items.add(new BooleanButton(setting));
                }
                if (setting.getValue() instanceof Bind && !Integer.valueOf("Keybind".toUpperCase().hashCode()).equals(setting.getName().toUpperCase().hashCode()) && !Integer.valueOf("Hud".toUpperCase().hashCode()).equals(this.module.getName().toUpperCase().hashCode())) {
                    items.add((BooleanButton)new BindButton(setting));
                }
                if ((setting.getValue() instanceof String || setting.getValue() instanceof Character) && !Integer.valueOf("displayName".toUpperCase().hashCode()).equals(setting.getName().toUpperCase().hashCode())) {
                    items.add((BooleanButton)new StringButton(setting));
                }
                if (setting.getValue() instanceof Color) {
                    items.add((BooleanButton)new PickerButton(setting));
                }
                if (setting.isNumberSetting() && setting.hasRestriction()) {
                    items.add((BooleanButton)new Slider(setting));
                    return;
                }
                if (!setting.isEnumSetting()) {
                    return;
                }
                items.add((BooleanButton)new EnumButton(setting));
                return;
            }
        }
        items.add((BooleanButton)new BindButton(this.module.getSettingByName("Keybind")));
        this.items = items;
    }
    
    static {
        $assertionsDisabled = !ModuleButton.class.desiredAssertionStatus();
    }
    
    public void toggle() {
        this.module.toggle();
    }
    
    public ModuleButton(final Module module) {
        super(module.getName());
        this.items = new ArrayList();
        this.progress = 0;
        this.module = module;
        this.initSettings();
    }
    
    public boolean getState() {
        return this.module.isOn();
    }
    
    public int getHeight() {
        if (!this.subOpen) {
            return ClickGui.INSTANCE.getButtonHeight() - 1;
        }
        final int n = ClickGui.INSTANCE.getButtonHeight() - 1;
        final Iterator<Item> iterator = (Iterator<Item>)this.items.iterator();
        if (!iterator.hasNext()) {
            return n + 2;
        }
        final Item item = iterator.next();
        if (item.isHidden()) {
            return 0;
        }
        int n2 = n + (item.getHeight() + 1);
        if (item instanceof PickerButton && ((PickerButton)item).setting.open) {
            if (((PickerButton)item).setting.noRainbow) {
                n2 += (int)110.0f;
            }
            else {
                n2 += (int)120.0f;
            }
        }
        if (item instanceof EnumButton && ((EnumButton)item).setting.open) {
            final int n3 = n2 + ((EnumButton)item).setting.getValue().getClass().getEnumConstants().length * 12;
        }
        return 0;
    }
}
