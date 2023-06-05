//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class TNTTime extends Module
{
    public TNTTime() {
        super("TNTTime", "show tnt fuse", Category.MISC);
    }
    
    @Override
    public void onUpdate() {
        final Iterator<Entity> iterator = (Iterator<Entity>)TNTTime.mc.world.loadedEntityList.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final Entity entity = iterator.next();
        if (!(entity instanceof EntityTNTPrimed)) {
            return;
        }
        String s = ChatFormatting.GREEN + "";
        if (((EntityTNTPrimed)entity).getFuse() / 20.0 > 0.0) {
            s = ChatFormatting.DARK_RED + "";
        }
        if (((EntityTNTPrimed)entity).getFuse() / 20.0 > 1.0) {
            s = ChatFormatting.RED + "";
        }
        if (((EntityTNTPrimed)entity).getFuse() / 20.0 > 2.0) {
            s = ChatFormatting.YELLOW + "";
        }
        if (((EntityTNTPrimed)entity).getFuse() / 20.0 > 3.0) {
            s = ChatFormatting.GREEN + "";
        }
        entity.setCustomNameTag(s + String.valueOf(((EntityTNTPrimed)entity).getFuse() / 20.0).substring(0, 3) + "s");
        entity.setAlwaysRenderNameTag(true);
    }
}
