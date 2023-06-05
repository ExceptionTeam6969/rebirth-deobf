//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class ExtraTab extends Module
{
    public static ExtraTab INSTANCE;
    public final Setting size;
    
    public ExtraTab() {
        super("ExtraTab", "Extends Tab", Category.MISC);
        this.size = this.add(new Setting("Size", 250, 1, 1000));
        ExtraTab.INSTANCE = this;
    }
    
    static {
        ExtraTab.INSTANCE = new ExtraTab();
    }
}
