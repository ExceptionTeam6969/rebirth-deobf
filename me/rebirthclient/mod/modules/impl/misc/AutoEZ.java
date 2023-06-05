//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;

public class AutoEZ extends Module
{
    public static AutoEZ INSTANCE;
    public final Setting EzString;
    public final Setting whenSelf;
    public final Setting poped;
    public final Setting SelfString;
    public final Setting whenFriend;
    
    @Override
    public void onDeath(final EntityPlayer entityPlayer) {
        if (PopCounter.INSTANCE.isOn()) {
            return;
        }
        PopCounter.INSTANCE.onDeath(entityPlayer);
    }
    
    static {
        AutoEZ.INSTANCE = new AutoEZ();
    }
    
    @Override
    public void onTotemPop(final EntityPlayer entityPlayer) {
        if (PopCounter.INSTANCE.isOn()) {
            return;
        }
        PopCounter.INSTANCE.onTotemPop(entityPlayer);
    }
    
    public AutoEZ() {
        super("AutoEZ", "say ez for enemy dead", Category.MISC);
        this.EzString = this.add(new Setting("String", "EZ"));
        this.poped = this.add(new Setting("popCounter", true));
        this.whenFriend = this.add(new Setting("whenFriend", false));
        this.whenSelf = this.add(new Setting("whenSelf", false));
        this.SelfString = this.add(new Setting("SelfString", "potato server nice lag"));
        AutoEZ.INSTANCE = this;
    }
}
