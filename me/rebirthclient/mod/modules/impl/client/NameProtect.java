//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class NameProtect extends Module
{
    public static NameProtect INSTANCE;
    public final Setting name;
    
    public NameProtect() {
        super("NameProtect", "To keep your alts in secret", Category.CLIENT);
        this.name = this.add(new Setting("Name", "Me"));
        NameProtect.INSTANCE = this;
    }
}
