//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.screen;

import net.minecraft.client.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.gui.click.*;
import me.rebirthclient.mod.gui.click.items.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.mod.gui.click.items.buttons.*;
import me.rebirthclient.mod.gui.click.items.other.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.io.*;
import me.rebirthclient.mod.*;
import java.util.*;
import java.util.function.*;

public class Gui extends GuiScreen
{
    final Minecraft mc;
    private final ArrayList components;
    public static Gui INSTANCE;
    private final ArrayList snow;
    
    public void onGuiClosed() {
        super.onGuiClosed();
        if (this.mc.entityRenderer.isShaderActive()) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(Gui::lambda$checkMouseWheel$5);
        }
        else if (dWheel > 0) {
            this.components.forEach(Gui::lambda$checkMouseWheel$6);
        }
    }
    
    private static void lambda$mouseReleased$4(final int n, final int n2, final int n3, final Component component) {
        component.mouseReleased(n, n2, n3);
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    public final ArrayList getComponents() {
        return this.components;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        this.components.forEach(Gui::lambda$mouseReleased$4);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        this.components.forEach(Gui::lambda$mouseClicked$3);
    }
    
    private static void lambda$checkMouseWheel$6(final Component component) {
        component.setY(component.getY() + 10);
    }
    
    public void updateModule(final Module module) {
        final Iterator<Component> iterator = this.components.iterator();
        if (iterator.hasNext()) {
            for (final Item item : iterator.next().getItems()) {
                if (!(item instanceof ModuleButton)) {
                    return;
                }
                final ModuleButton moduleButton = (ModuleButton)item;
                final Module module2 = moduleButton.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(module2)) {
                    return;
                }
                moduleButton.initSettings();
            }
        }
    }
    
    private static void lambda$drawScreen$1(final int n, final int n2, final float n3, final Component component) {
        component.drawScreen(n, n2, n3);
    }
    
    private static void lambda$mouseClicked$3(final int n, final int n2, final int n3, final Component component) {
        component.mouseClicked(n, n2, n3);
    }
    
    private static void lambda$checkMouseWheel$5(final Component component) {
        component.setY(component.getY() - 10);
    }
    
    private void onLoad() {
        Gui.INSTANCE = this;
        int n = -84;
        final Iterator<Category> iterator = (Iterator<Category>)Managers.MODULES.getCategories().iterator();
        if (iterator.hasNext()) {
            final Category category = iterator.next();
            if (category != Category.HUD) {
                final ArrayList components = this.components;
                final String name = category.getName();
                n += 90;
                components.add(new Component(name, n, 4, true, category) {
                    final Category val$category;
                    final Gui this$0;
                    
                    private void lambda$setupItems$0(final Module module) {
                        this.addButton((Button)new ModuleButton(module));
                    }
                    
                    public void setupItems() {
                        Gui$1.counter1 = new int[] { 1 };
                        Managers.MODULES.getModulesByCategory(this.val$category).forEach(this::lambda$setupItems$0);
                    }
                });
            }
            return;
        }
        this.components.forEach(Gui::lambda$onLoad$0);
        final Random random = new Random();
        int n2 = 0;
        if (n2 >= 100) {
            return;
        }
        int n3 = 0;
        if (n3 < 3) {
            this.snow.add(new Snow(25 * n2, n3 * -50, random.nextInt(3) + 1, random.nextInt(2) + 1));
            ++n3;
            return;
        }
        ++n2;
    }
    
    private static void lambda$drawScreen$2(final ScaledResolution scaledResolution, final Snow snow) {
        snow.drawSnow(scaledResolution);
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.checkMouseWheel();
        if (this.mc.world != null) {
            this.drawDefaultBackground();
        }
        else {
            net.minecraft.client.gui.Gui.drawRect(0, 0, 1920, 1080, ColorUtil.injectAlpha(new Color(-1072689136), 150).getRGB());
        }
        if ((boolean)ClickGui.INSTANCE.background.getValue() && this.mc.currentScreen instanceof Gui && this.mc.world != null) {
            RenderUtil.drawVGradientRect(0.0f, 0.0f, (float)Managers.TEXT.scaledWidth, (float)Managers.TEXT.scaledHeight, new Color(0, 0, 0, 0).getRGB(), Managers.COLORS.getCurrentWithAlpha(60));
        }
        final float n4 = (float)ClickGui.animation.easeOutQuad();
        GlStateManager.pushMatrix();
        GL11.glScaled((double)n4, (double)n4, (double)n4);
        this.components.forEach(Gui::lambda$drawScreen$1);
        GlStateManager.popMatrix();
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (!this.snow.isEmpty() && (boolean)ClickGui.INSTANCE.snow.getValue()) {
            this.snow.forEach(Gui::lambda$drawScreen$2);
        }
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(Gui::lambda$keyTyped$7);
    }
    
    private static void lambda$onLoad$0(final Component component) {
        component.getItems().sort(Comparator.comparing((Function<? super E, ? extends Comparable>)Mod::getName));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    private static void lambda$keyTyped$7(final char c, final int n, final Component component) {
        component.onKeyTyped(c, n);
    }
    
    public Gui() {
        this.mc = Minecraft.getMinecraft();
        this.snow = new ArrayList();
        this.components = new ArrayList();
        this.onLoad();
    }
}
