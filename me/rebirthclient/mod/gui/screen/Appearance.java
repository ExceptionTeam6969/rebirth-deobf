//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.screen;

import net.minecraft.client.gui.*;
import me.rebirthclient.mod.gui.click.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.gui.click.items.*;
import java.io.*;
import me.rebirthclient.mod.*;
import java.util.*;
import java.util.function.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.mod.gui.click.items.buttons.*;

public class Appearance extends GuiScreen
{
    private final ArrayList components;
    private static Appearance INSTANCE;
    
    static {
        Appearance.INSTANCE = new Appearance();
    }
    
    public void onGuiClosed() {
        try {
            super.onGuiClosed();
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Component getComponentByName(final String s) {
        final Iterator<Component> iterator = (Iterator<Component>)this.components.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        final Component component = iterator.next();
        if (!Integer.valueOf(s.toUpperCase().hashCode()).equals(component.getName().toUpperCase().hashCode())) {
            return null;
        }
        return component;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        this.components.forEach(Appearance::lambda$mouseReleased$3);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        this.components.forEach(Appearance::lambda$mouseClicked$2);
    }
    
    private static void lambda$keyTyped$6(final char c, final int n, final Component component) {
        component.onKeyTyped(c, n);
    }
    
    private static void lambda$checkMouseWheel$4(final Component component) {
        component.setY(component.getY() - 10);
    }
    
    public Appearance() {
        this.components = new ArrayList();
        (Appearance.INSTANCE = this).load();
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    private static void lambda$mouseReleased$3(final int n, final int n2, final int n3, final Component component) {
        component.mouseReleased(n, n2, n3);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(Appearance::lambda$checkMouseWheel$4);
        }
        else if (dWheel > 0) {
            this.components.forEach(Appearance::lambda$checkMouseWheel$5);
        }
    }
    
    private static void lambda$drawScreen$1(final int n, final int n2, final float n3, final Component component) {
        component.drawScreen(n, n2, n3);
    }
    
    public final ArrayList getComponents() {
        return this.components;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.checkMouseWheel();
        this.components.forEach(Appearance::lambda$drawScreen$1);
    }
    
    private static void lambda$mouseClicked$2(final int n, final int n2, final int n3, final Component component) {
        component.mouseClicked(n, n2, n3);
    }
    
    public static Appearance getInstance() {
        if (Appearance.INSTANCE == null) {
            Appearance.INSTANCE = new Appearance();
        }
        return Appearance.INSTANCE;
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
    
    public static Appearance getClickGui() {
        return getInstance();
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(Appearance::lambda$keyTyped$6);
    }
    
    private static void lambda$load$0(final Component component) {
        component.getItems().sort(Comparator.comparing((Function<? super E, ? extends Comparable>)Mod::getName));
    }
    
    private void load() {
        int n = -84;
        final Iterator<Category> iterator = (Iterator<Category>)Managers.MODULES.getCategories().iterator();
        if (iterator.hasNext()) {
            final Category category = iterator.next();
            if (category == Category.HUD) {
                final ArrayList components = this.components;
                final String name = category.getName();
                n += 90;
                components.add(new Component(name, n, 4, true, category) {
                    final Appearance this$0;
                    final Category val$category;
                    
                    public void setupItems() {
                        Appearance$1.counter1 = new int[] { 1 };
                        Managers.MODULES.getModulesByCategory(this.val$category).forEach(this::lambda$setupItems$0);
                    }
                    
                    private void lambda$setupItems$0(final Module module) {
                        this.addButton((Button)new ModuleButton(module));
                    }
                });
            }
            return;
        }
        this.components.forEach(Appearance::lambda$load$0);
    }
    
    private static void lambda$checkMouseWheel$5(final Component component) {
        component.setY(component.getY() + 10);
    }
}
