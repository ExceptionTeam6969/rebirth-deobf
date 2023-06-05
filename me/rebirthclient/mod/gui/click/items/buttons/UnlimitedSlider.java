//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.gui.screen.*;

public class UnlimitedSlider extends Button
{
    public final Setting setting;
    
    public boolean getState() {
        return true;
    }
    
    public int getHeight() {
        return 14;
    }
    
    public UnlimitedSlider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void mouseClicked(final int p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iload_1        
        //     2: iload_2        
        //     3: iload_3        
        //     4: invokespecial   me/rebirthclient/mod/gui/click/items/buttons/Button.mouseClicked:(III)V
        //     7: aload_0        
        //     8: iload_1        
        //     9: iload_2        
        //    10: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.isHovering:(II)Z
        //    13: ifeq            280
        //    16: getstatic       me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.mc:Lnet/minecraft/client/Minecraft;
        //    19: invokevirtual   net/minecraft/client/Minecraft.getSoundHandler:()Lnet/minecraft/client/audio/SoundHandler;
        //    22: getstatic       net/minecraft/init/SoundEvents.UI_BUTTON_CLICK:Lnet/minecraft/util/SoundEvent;
        //    25: fconst_1       
        //    26: invokestatic    net/minecraft/client/audio/PositionedSoundRecord.getMasterRecord:(Lnet/minecraft/util/SoundEvent;F)Lnet/minecraft/client/audio/PositionedSoundRecord;
        //    29: invokevirtual   net/minecraft/client/audio/SoundHandler.playSound:(Lnet/minecraft/client/audio/ISound;)V
        //    32: aload_0        
        //    33: iload_1        
        //    34: ifle            160
        //    37: aload_0        
        //    38: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    41: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    44: instanceof      Ljava/lang/Double;
        //    47: ifeq            78
        //    50: aload_0        
        //    51: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    54: aload_0        
        //    55: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    58: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    61: checkcast       Ljava/lang/Double;
        //    64: invokevirtual   java/lang/Double.doubleValue:()D
        //    67: dconst_1       
        //    68: dadd           
        //    69: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    72: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //    75: goto            280
        //    78: aload_0        
        //    79: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    82: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    85: instanceof      Ljava/lang/Float;
        //    88: ifeq            119
        //    91: aload_0        
        //    92: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    95: aload_0        
        //    96: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //    99: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   102: checkcast       Ljava/lang/Float;
        //   105: invokevirtual   java/lang/Float.floatValue:()F
        //   108: fconst_1       
        //   109: fadd           
        //   110: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   113: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //   116: goto            280
        //   119: aload_0        
        //   120: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   123: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   126: instanceof      Ljava/lang/Integer;
        //   129: ifeq            280
        //   132: aload_0        
        //   133: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   136: aload_0        
        //   137: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   140: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   143: checkcast       Ljava/lang/Integer;
        //   146: invokevirtual   java/lang/Integer.intValue:()I
        //   149: iconst_1       
        //   150: iadd           
        //   151: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   154: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //   157: goto            280
        //   160: aload_0        
        //   161: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   164: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   167: instanceof      Ljava/lang/Double;
        //   170: ifeq            201
        //   173: aload_0        
        //   174: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   177: aload_0        
        //   178: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   181: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   184: checkcast       Ljava/lang/Double;
        //   187: invokevirtual   java/lang/Double.doubleValue:()D
        //   190: dconst_1       
        //   191: dsub           
        //   192: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   195: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //   198: goto            280
        //   201: aload_0        
        //   202: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   205: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   208: instanceof      Ljava/lang/Float;
        //   211: ifeq            242
        //   214: aload_0        
        //   215: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   218: aload_0        
        //   219: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   222: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   225: checkcast       Ljava/lang/Float;
        //   228: invokevirtual   java/lang/Float.floatValue:()F
        //   231: fconst_1       
        //   232: fsub           
        //   233: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   236: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //   239: goto            280
        //   242: aload_0        
        //   243: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   246: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   249: instanceof      Ljava/lang/Integer;
        //   252: ifeq            280
        //   255: aload_0        
        //   256: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   259: aload_0        
        //   260: getfield        me/rebirthclient/mod/gui/click/items/buttons/UnlimitedSlider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   263: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   266: checkcast       Ljava/lang/Integer;
        //   269: invokevirtual   java/lang/Integer.intValue:()I
        //   272: iconst_1       
        //   273: isub           
        //   274: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   277: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.setValue:(Ljava/lang/Object;)V
        //   280: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0280 (coming from #0252).
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
    
    public void toggle() {
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW;
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(200) : Managers.COLORS.getCurrentWithAlpha(120));
        RenderUtil.drawLine(this.x + 1.0f, this.y, this.x + 1.0f, this.y + this.height - 0.5f, 0.9f, Managers.COLORS.getCurrentWithAlpha(255));
        Managers.TEXT.drawStringWithShadow(" - " + (b ? (this.setting.getName().toLowerCase() + ":") : this.setting.getName()) + " " + ChatFormatting.GRAY + this.setting.getValue() + ChatFormatting.WHITE + " +", this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
}
