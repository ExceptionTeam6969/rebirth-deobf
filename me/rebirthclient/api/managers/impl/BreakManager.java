//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.*;

public class BreakManager implements Wrapper
{
    public static final HashMap MineMap;
    
    static {
        MineMap = new HashMap();
    }
    
    public static boolean isMine(final BlockPos blockPos) {
        final Iterator<EntityPlayer> iterator = BreakManager.MineMap.keySet().iterator();
        if (!iterator.hasNext()) {
            return false;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (entityPlayer == null) {
            return false;
        }
        final BlockPos blockPos2 = BreakManager.MineMap.get(entityPlayer);
        return blockPos2 != null && new BlockPos((Vec3i)blockPos2).equals((Object)new BlockPos((Vec3i)blockPos));
    }
}
