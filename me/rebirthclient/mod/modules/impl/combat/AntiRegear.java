//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.tileentity.*;
import java.util.*;

public class AntiRegear extends Module
{
    private final Setting safeRange;
    private final Setting range;
    
    @Override
    public void onUpdate() {
        if (PacketMine.breakPos != null && BlockUtil.shulkerList.contains(AntiRegear.mc.world.getBlockState(PacketMine.breakPos).getBlock())) {
            return;
        }
        if (this.getBlock() != null) {
            CombatUtil.mineBlock(this.getBlock().getPos());
        }
    }
    
    public AntiRegear() {
        super("AntiRegear", "Shulker nuker", Category.COMBAT);
        this.safeRange = this.add(new Setting("SafeRange", 2, 0, 8));
        this.range = this.add(new Setting("Range", 5, 0, 8));
    }
    
    private TileEntity getBlock() {
        final TileEntity tileEntity = null;
        final Iterator<TileEntity> iterator = (Iterator<TileEntity>)AntiRegear.mc.world.loadedTileEntityList.iterator();
        if (iterator.hasNext()) {
            final TileEntity tileEntity2 = iterator.next();
            if (tileEntity2 instanceof TileEntityShulkerBox) {
                if (AntiRegear.mc.player.getDistance(tileEntity2.getPos().getX() + 0.5, tileEntity2.getPos().getY() + 0.5, tileEntity2.getPos().getZ() + 0.5) <= (int)this.safeRange.getValue()) {
                    return null;
                }
                if (AntiRegear.mc.player.getDistance(tileEntity2.getPos().getX() + 0.5, tileEntity2.getPos().getY() + 0.5, tileEntity2.getPos().getZ() + 0.5) <= (int)this.range.getValue()) {}
            }
            return null;
        }
        return tileEntity;
    }
}
