//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click;

import me.rebirthclient.mod.*;
import java.util.function.*;
import me.rebirthclient.mod.gui.click.items.buttons.*;
import me.rebirthclient.mod.gui.click.items.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.util.*;

public class Component extends Mod
{
    public static int[] counter1;
    private boolean hidden;
    private int x2;
    public final Map colorMap;
    public boolean drag;
    private int width;
    private int y2;
    private int angle;
    private int y;
    private final ArrayList items;
    private int height;
    private int x;
    private boolean open;
    
    public boolean isOpen() {
        return this.open;
    }
    
    static {
        Component.counter1 = new int[] { 1 };
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setupItems() {
    }
    
    public Component(final String s, final int x, final int y, final boolean open) {
        super(s);
        this.colorMap = new HashMap();
        this.items = new ArrayList();
        this.angle = 180;
        this.x = x;
        this.y = y;
        this.width = 88;
        this.height = 18;
        this.open = open;
        this.setupItems();
    }
    
    public void mouseClicked(final int p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifne            50
        //     4: aload_0        
        //     5: iload_1        
        //     6: iload_2        
        //     7: ifeq            50
        //    10: aload_0        
        //    11: aload_0        
        //    12: getfield        me/rebirthclient/mod/gui/click/Component.x:I
        //    15: iload_1        
        //    16: isub           
        //    17: putfield        me/rebirthclient/mod/gui/click/Component.x2:I
        //    20: aload_0        
        //    21: aload_0        
        //    22: getfield        me/rebirthclient/mod/gui/click/Component.y:I
        //    25: iload_2        
        //    26: isub           
        //    27: putfield        me/rebirthclient/mod/gui/click/Component.y2:I
        //    30: getstatic       me/rebirthclient/mod/gui/screen/Gui.INSTANCE:Lme/rebirthclient/mod/gui/screen/Gui;
        //    33: invokevirtual   me/rebirthclient/mod/gui/screen/Gui.getComponents:()Ljava/util/ArrayList;
        //    36: invokedynamic   BootstrapMethod #0, accept:()Ljava/util/function/Consumer;
        //    41: invokevirtual   java/util/ArrayList.forEach:(Ljava/util/function/Consumer;)V
        //    44: aload_0        
        //    45: iconst_1       
        //    46: putfield        me/rebirthclient/mod/gui/click/Component.drag:Z
        //    49: return         
        //    50: iload_3        
        //    51: iconst_1       
        //    52: if_icmpne       94
        //    55: aload_0        
        //    56: iload_1        
        //    57: iload_2        
        //    58: ifeq            94
        //    61: aload_0        
        //    62: aload_0        
        //    63: getfield        me/rebirthclient/mod/gui/click/Component.open:Z
        //    66: ifne            73
        //    69: iconst_1       
        //    70: goto            74
        //    73: iconst_0       
        //    74: putfield        me/rebirthclient/mod/gui/click/Component.open:Z
        //    77: getstatic       me/rebirthclient/mod/gui/click/Component.mc:Lnet/minecraft/client/Minecraft;
        //    80: invokevirtual   net/minecraft/client/Minecraft.getSoundHandler:()Lnet/minecraft/client/audio/SoundHandler;
        //    83: getstatic       net/minecraft/init/SoundEvents.UI_BUTTON_CLICK:Lnet/minecraft/util/SoundEvent;
        //    86: fconst_1       
        //    87: invokestatic    net/minecraft/client/audio/PositionedSoundRecord.getMasterRecord:(Lnet/minecraft/util/SoundEvent;F)Lnet/minecraft/client/audio/PositionedSoundRecord;
        //    90: invokevirtual   net/minecraft/client/audio/SoundHandler.playSound:(Lnet/minecraft/client/audio/ISound;)V
        //    93: return         
        //    94: aload_0        
        //    95: getfield        me/rebirthclient/mod/gui/click/Component.open:Z
        //    98: ifne            102
        //   101: return         
        //   102: aload_0        
        //   103: invokevirtual   me/rebirthclient/mod/gui/click/Component.getItems:()Ljava/util/ArrayList;
        //   106: iload_1        
        //   107: iload_2        
        //   108: iload_3        
        //   109: invokedynamic   BootstrapMethod #1, accept:(III)Ljava/util/function/Consumer;
        //   114: invokevirtual   java/util/ArrayList.forEach:(Ljava/util/function/Consumer;)V
        //   117: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0094 (coming from #0058).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (!this.open) {
            return;
        }
        this.getItems().forEach(Component::lambda$onKeyTyped$3);
    }
    
    public void addButton(final Button button) {
        this.items.add(button);
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        if (n3 == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(Component::lambda$mouseReleased$2);
    }
    
    public static float calculateRotation(float n) {
        if ((n %= 360.0f) >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    private static void lambda$onKeyTyped$3(final char c, final int n, final Item item) {
        item.onKeyTyped(c, n);
    }
    
    private static void lambda$mouseClicked$1(final int n, final int n2, final int n3, final Item item) {
        item.mouseClicked(n, n2, n3);
    }
    
    private void drawOutline(final int n) {
        float n2 = 0.0f;
        if (this.open) {
            n2 = this.getTotalItemHeight() - 2.0f;
        }
        RenderUtil.drawLine((float)this.x, this.y - 1.5f, (float)this.x, this.y + this.height + n2, 1.0f, ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? Managers.COLORS.getRainbow().getRGB() : n);
        RenderUtil.drawLine((float)(this.x + this.width), this.y - 1.5f, (float)(this.x + this.width), this.y + this.height + n2, 1.0f, ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? Managers.COLORS.getRainbow().getRGB() : n);
        RenderUtil.drawLine((float)this.x, this.y - 1.5f, (float)(this.x + this.width), this.y - 1.5f, 1.0f, ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? Managers.COLORS.getRainbow().getRGB() : n);
        RenderUtil.drawLine((float)this.x, this.y + this.height + n2, (float)(this.x + this.width), this.y + this.height + n2, 1.0f, ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? ColorUtil.rainbow(500).getRGB() : n);
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    private float getTotalItemHeight() {
        final float n = 0.0f;
        final Iterator<Item> iterator = this.getItems().iterator();
        if (iterator.hasNext()) {
            final float n2 = n + (iterator.next().getHeight() + 1.5f);
            return 0.0f;
        }
        return n;
    }
    
    public final ArrayList getItems() {
        return this.items;
    }
    
    private void drag(final int n, final int n2) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + n;
        this.y = this.y2 + n2;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drag(n, n2);
        Component.counter1 = new int[] { 1 };
        final float n4 = this.open ? (this.getTotalItemHeight() - 2.0f) : 0.0f;
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final int argb = ColorUtil.toARGB(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue(), b ? 99 : 120);
        Gui.drawRect(this.x, this.y - 1, this.x + this.width, this.y + this.height - 6, ((boolean)ClickGui.INSTANCE.rainbow.getValue()) ? Managers.COLORS.getCurrentWithAlpha(b ? 99 : 150) : argb);
        if (b) {
            this.drawArrow();
        }
        if (this.open) {
            if (ClickGui.INSTANCE.line.getValue()) {
                if ((boolean)ClickGui.INSTANCE.rainbow.getValue() && (boolean)ClickGui.INSTANCE.rollingLine.getValue()) {
                    final float n5 = (float)(int)ClickGui.INSTANCE.rainbowDelay.getValue();
                    final int scaledHeight = Managers.TEXT.scaledHeight;
                    final float n6 = n5;
                    int n7 = 0;
                    if (n7 <= scaledHeight) {
                        this.colorMap.put(n7, Color.HSBtoRGB(n6, ((Float)ClickGui.INSTANCE.rainbowSaturation.getValue()).intValue() / 255.0f, 1.0f));
                        final float n8 = n6 + 1.0f / scaledHeight * 5.0f;
                        ++n7;
                        return;
                    }
                    GL11.glLineWidth(1.0f);
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel(7425);
                    GL11.glBegin(1);
                    final Color color = new Color(Managers.COLORS.getCurrentWithAlpha(150));
                    GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    GL11.glVertex3f((float)this.x, this.y - 1.5f, 0.0f);
                    final float n9 = this.getHeight() - 1.5f;
                    final Iterator<Item> iterator = (Iterator<Item>)this.getItems().iterator();
                    if (iterator.hasNext()) {
                        final Item item = iterator.next();
                        float n10;
                        final Color color2 = (ClickGui.INSTANCE.rainbowMode.getValue() != ClickGui.Rainbow.NORMAL) ? ColorUtil.rainbow(MathUtil.clamp((int)(this.y + (n10 = n9 + (item.getHeight() + 1.5f))), 0, Managers.TEXT.scaledHeight)) : new Color(this.colorMap.get(MathUtil.clamp((int)(this.y + (n10 = n9 + (item.getHeight() + 1.5f))), 0, Managers.TEXT.scaledHeight)));
                        GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)this.x, this.y + n10, 0.0f);
                        GL11.glVertex3f((float)this.x, this.y + n10, 0.0f);
                        return;
                    }
                    final Color color3 = (ClickGui.INSTANCE.rainbowMode.getValue() != ClickGui.Rainbow.NORMAL) ? ColorUtil.rainbow(MathUtil.clamp((int)(this.y + this.height + n4), 0, Managers.TEXT.scaledHeight)) : new Color(this.colorMap.get(MathUtil.clamp((int)(this.y + this.height + n4), 0, Managers.TEXT.scaledHeight)));
                    GL11.glColor4f(color3.getRed() / 255.0f, color3.getGreen() / 255.0f, color3.getBlue() / 255.0f, color3.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n4, 0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n4, 0.0f);
                    final Iterator<Item> iterator2 = (Iterator<Item>)this.getItems().iterator();
                    if (iterator2.hasNext()) {
                        final Item item2 = iterator2.next();
                        float n11;
                        final Color color4 = (ClickGui.INSTANCE.rainbowMode.getValue() != ClickGui.Rainbow.NORMAL) ? ColorUtil.rainbow(MathUtil.clamp((int)(this.y + (n11 = n9 - (item2.getHeight() + 1.5f))), 0, Managers.TEXT.scaledHeight)) : new Color(this.colorMap.get(MathUtil.clamp((int)(this.y + (n11 = n9 - (item2.getHeight() + 1.5f))), 0, Managers.TEXT.scaledHeight)));
                        GL11.glColor4f(color4.getRed() / 255.0f, color4.getGreen() / 255.0f, color4.getBlue() / 255.0f, color4.getAlpha() / 255.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + n11, 0.0f);
                        GL11.glVertex3f((float)(this.x + this.width), this.y + n11, 0.0f);
                        return;
                    }
                    final Color color5 = new Color(Managers.COLORS.getCurrentWithAlpha(150));
                    GL11.glColor4f(color5.getRed() / 255.0f, color5.getGreen() / 255.0f, color5.getBlue() / 255.0f, color5.getAlpha() / 255.0f);
                    GL11.glVertex3f((float)(this.x + this.width), this.y - 1.5f, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel(7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }
                else {
                    this.drawOutline(argb);
                }
            }
            if (ClickGui.INSTANCE.rect.getValue()) {
                RenderUtil.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.y + this.height + n4, ((boolean)ClickGui.INSTANCE.colorRect.getValue()) ? Managers.COLORS.getCurrentWithAlpha(30) : ColorUtil.toARGB(10, 10, 10, 30));
            }
        }
        if (ClickGui.INSTANCE.icon.getValue()) {
            final String name = this.getName();
            int n12 = -1;
            switch (name.hashCode()) {
                case 2024008468: {
                    if (Integer.valueOf("Combat".hashCode()).equals(name.hashCode())) {
                        n12 = 0;
                        break;
                    }
                    break;
                }
                case 2398476: {
                    if (Integer.valueOf("Misc".hashCode()).equals(name.hashCode())) {
                        n12 = 1;
                        break;
                    }
                    break;
                }
                case -1850724938: {
                    if (Integer.valueOf("Render".hashCode()).equals(name.hashCode())) {
                        n12 = 2;
                        break;
                    }
                    break;
                }
                case -39033649: {
                    if (Integer.valueOf("Movement".hashCode()).equals(name.hashCode())) {
                        n12 = 3;
                        break;
                    }
                    break;
                }
                case -1901885695: {
                    if (Integer.valueOf("Player".hashCode()).equals(name.hashCode())) {
                        n12 = 4;
                        break;
                    }
                    break;
                }
                case 355504491: {
                    if (Integer.valueOf("Exploit".hashCode()).equals(name.hashCode())) {
                        n12 = 5;
                        break;
                    }
                    break;
                }
                case 2021122027: {
                    if (Integer.valueOf("Client".hashCode()).equals(name.hashCode())) {
                        n12 = 6;
                        break;
                    }
                    break;
                }
                case 71895: {
                    if (Integer.valueOf("HUD".hashCode()).equals(name.hashCode())) {
                        n12 = 7;
                        break;
                    }
                    break;
                }
                case 68597: {
                    if (Integer.valueOf("Dev".hashCode()).equals(name.hashCode())) {
                        n12 = 8;
                        break;
                    }
                    break;
                }
            }
            switch (n12) {
                case 0: {
                    Managers.TEXT.drawStringIcon("b", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 1: {
                    Managers.TEXT.drawStringIcon("[", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 2: {
                    Managers.TEXT.drawStringIcon("a", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 3: {
                    Managers.TEXT.drawStringIcon("8", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 4: {
                    Managers.TEXT.drawStringIcon("5", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 5: {
                    Managers.TEXT.drawStringIcon("!", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 6:
                case 7: {
                    Managers.TEXT.drawStringIcon("7", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
                case 8: {
                    Managers.TEXT.drawStringIcon("6", this.x + 3.0f, this.y - 5.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
                    break;
                }
            }
        }
        Managers.TEXT.drawStringWithShadow(this.getName(), ((boolean)ClickGui.INSTANCE.icon.getValue()) ? (this.x + 17.0f) : (this.x + 3.0f), this.y - 4.0f - me.rebirthclient.mod.gui.screen.Gui.INSTANCE.getTextOffset(), -1);
        if (this.open) {
            final float n13 = this.getY() + this.getHeight() - 3.0f;
            final Iterator<Item> iterator3 = (Iterator<Item>)this.getItems().iterator();
            if (iterator3.hasNext()) {
                final Item item3 = iterator3.next();
                ++Component.counter1[0];
                if (item3.isHidden()) {
                    return;
                }
                item3.setLocation(this.x + 2.0f, n13);
                item3.setHeight(ClickGui.INSTANCE.getButtonHeight());
                item3.setWidth(this.getWidth() - 4);
                item3.drawScreen(n, n2, n3);
                final float n14 = n13 + (item3.getHeight() + 1.5f);
            }
        }
    }
    
    private static void lambda$mouseReleased$2(final int n, final int n2, final int n3, final Item item) {
        item.mouseReleased(n, n2, n3);
    }
    
    private static void lambda$mouseClicked$0(final Component component) {
        if (component.drag) {
            component.drag = false;
        }
    }
    
    public void drawArrow() {
        if (!this.open) {
            if (this.angle > 0) {
                this.angle -= 6;
            }
        }
        else if (this.angle < 180) {
            this.angle += 6;
        }
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        RenderUtil.glColor(new Color(255, 255, 255, 255));
        Component.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/arrow.png"));
        GlStateManager.translate((float)(this.getX() + this.getWidth() - 7), this.getY() + 6 - 0.3f, 0.0f);
        GlStateManager.rotate(calculateRotation((float)this.angle), 0.0f, 0.0f, 0.0f);
        RenderUtil.drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
