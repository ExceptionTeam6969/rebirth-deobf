//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import net.minecraft.client.entity.*;
import me.rebirthclient.mod.modules.*;

public class PearlNotify extends Module
{
    private boolean flag;
    
    @Override
    public void onEnable() {
        this.flag = true;
    }
    
    @Override
    public void onUpdate() {
        Entity entity = null;
        final Iterator<Entity> iterator = PearlNotify.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity2 = iterator.next();
            if (!(entity2 instanceof EntityEnderPearl)) {
                return;
            }
            entity = entity2;
        }
        if (entity == null) {
            this.flag = true;
            return;
        }
        final EntityPlayerSP entityPlayerSP = null;
        final Iterator<EntityPlayer> iterator2 = PearlNotify.mc.world.playerEntities.iterator();
        if (iterator2.hasNext()) {
            final EntityPlayer entityPlayer = iterator2.next();
            if (entityPlayerSP != null) {
                if (((EntityPlayer)entityPlayerSP).getDistance(entity) <= entityPlayer.getDistance(entity)) {
                    return;
                }
            }
            return;
        }
        if (entityPlayerSP == PearlNotify.mc.player) {
            this.flag = false;
        }
        if (entityPlayerSP != null && this.flag) {
            String string = entity.getHorizontalFacing().toString();
            if (Integer.valueOf("West".hashCode()).equals(string.hashCode())) {
                string = "East";
            }
            else if (Integer.valueOf("East".hashCode()).equals(string.hashCode())) {
                string = "West";
            }
            this.sendMessageWithID(Managers.FRIENDS.isFriend(((EntityPlayer)entityPlayerSP).getName()) ? (ChatFormatting.AQUA + ((EntityPlayer)entityPlayerSP).getName() + ChatFormatting.GRAY + " has just thrown a pearl heading " + ChatFormatting.AQUA + string + "!") : (ChatFormatting.RED + ((EntityPlayer)entityPlayerSP).getName() + ChatFormatting.GRAY + " has just thrown a pearl heading " + ChatFormatting.RED + string + "!"), ((EntityPlayer)entityPlayerSP).getEntityId());
            this.flag = false;
        }
    }
    
    public PearlNotify() {
        super("PearlNotify", "Notifies pearl throws", Category.MISC);
    }
}
