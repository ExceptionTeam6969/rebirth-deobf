//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import net.minecraft.tileentity.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;

public class TileESP extends Module
{
    private int count;
    private final Setting signs;
    private final Setting eChests;
    private final Setting beds;
    private final Setting shulkers;
    private final Setting dispensers;
    private final Setting hoppers;
    private final Setting chests;
    private final Setting furnaces;
    
    public TileESP() {
        super("TileESP", "Highlights tile entities such as storages and signs", Category.RENDER);
        this.beds = this.add(new Setting("Beds", true));
        this.chests = this.add(new Setting("Chests", true));
        this.eChests = this.add(new Setting("EChests", true));
        this.shulkers = this.add(new Setting("Shulkers", true));
        this.signs = this.add(new Setting("Signs", true));
        this.dispensers = this.add(new Setting("Dispensers", true));
        this.hoppers = this.add(new Setting("Hoppers", true));
        this.furnaces = this.add(new Setting("Furnaces", true));
    }
    
    private Color getColor(final TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return new Color(155, 127, 77, 100);
        }
        if (tileEntity instanceof TileEntityBed) {
            return new Color(190, 49, 49, 100);
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return new Color(124, 37, 196, 100);
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return new Color(255, 1, 175, 100);
        }
        if (tileEntity instanceof TileEntityFurnace || tileEntity instanceof TileEntityDispenser || tileEntity instanceof TileEntityHopper) {
            return new Color(150, 150, 150, 100);
        }
        return new Color(255, 255, 255, 100);
    }
    
    @Override
    public String getInfo() {
        return String.valueOf(this.count);
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.count = 0;
        final Iterator<TileEntity> iterator = TileESP.mc.world.loadedTileEntityList.iterator();
        if (iterator.hasNext()) {
            final TileEntity tileEntity = iterator.next();
            if (tileEntity != 0) {
                RenderUtil.drawSelectionBoxESP(tileEntity.getPos(), this.getColor(tileEntity), false, new Color(-1), 1.0f, true, true, 100, false);
                ++this.count;
            }
        }
    }
}
