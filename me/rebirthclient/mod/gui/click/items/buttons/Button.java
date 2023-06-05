//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.gui.click.items.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.gui.screen.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class Button extends Item
{
    private boolean state;
    
    public boolean getState() {
        return this.state;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final boolean b3 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        if (b) {
            this.x;
            this.y;
            RenderUtil.drawRect(this.x + this.width, this.y + this.height - 0.5f, (float)this, (float)n, (n2 != 0) ? 290805077 : -2007673515);
            Managers.TEXT.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - Gui.INSTANCE.getTextOffset(), this.getState() ? Managers.COLORS.getCurrentGui(240) : -1);
        }
        else if (b3) {
            this.x;
            this.y;
            final float n4 = this.x + this.width;
            final float n5 = this.y + this.height - 0.5f;
            Button button;
            int n6;
            int n7;
            if (this.getState()) {
                button = this;
                n6 = n;
                n7 = ((n2 != 0) ? Managers.COLORS.getCurrentWithAlpha(65) : Managers.COLORS.getCurrentWithAlpha(90));
            }
            else {
                button = this;
                n6 = n;
                n7 = ((n2 != 0) ? Managers.COLORS.getCurrentWithAlpha(26) : Managers.COLORS.getCurrentWithAlpha(35));
            }
            RenderUtil.drawRect(n4, n5, (float)button, (float)n6, n7);
            Managers.TEXT.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - Gui.INSTANCE.getTextOffset(), this.getState() ? Managers.COLORS.getCurrentGui(240) : 11579568);
        }
        else if (b2) {
            this.x;
            this.y;
            final float n8 = this.x + this.width;
            final float n9 = this.y + this.height - 0.5f;
            Button button2;
            int n10;
            int n11;
            if (this.getState()) {
                button2 = this;
                n10 = n;
                n11 = ((n2 != 0) ? Managers.COLORS.getCurrentWithAlpha(99) : Managers.COLORS.getCurrentWithAlpha(120));
            }
            else {
                button2 = this;
                n10 = n;
                n11 = ((n2 != 0) ? Managers.COLORS.getCurrentWithAlpha(26) : Managers.COLORS.getCurrentWithAlpha(55));
            }
            RenderUtil.drawRect(n8, n9, (float)button2, (float)n10, n11);
            Managers.TEXT.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            this.x;
            this.y;
            final float n12 = this.x + this.width;
            final float n13 = this.y + this.height - 0.5f;
            Button button3;
            int n14;
            int n15;
            if (this.getState()) {
                button3 = this;
                n14 = n;
                n15 = ((n2 != 0) ? Managers.COLORS.getCurrentWithAlpha(120) : Managers.COLORS.getCurrentWithAlpha(200));
            }
            else {
                button3 = this;
                n14 = n;
                n15 = ((n2 != 0) ? 290805077 : -2007673515);
            }
            RenderUtil.drawRect(n12, n13, (float)button3, (float)n14, n15);
            Managers.TEXT.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void mouseClicked(final int p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifne            14
        //     4: aload_0        
        //     5: iload_1        
        //     6: iload_2        
        //     7: ifeq            14
        //    10: aload_0        
        //    11: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Button.onMouseClick:()V
        //    14: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0014 (coming from #0007).
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
    
    @Override
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public void toggle() {
    }
    
    public Button(final String s) {
        super(s);
        this.height = ClickGui.INSTANCE.getButtonHeight();
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
}
