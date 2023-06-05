//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.math.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class Search extends Module
{
    private final Setting shulker;
    private final Setting frame;
    private final Setting lineWidth;
    private final Setting hopper;
    private final Setting chest;
    private final Setting cart;
    private final Setting outline;
    private final Setting boxAlpha;
    private final Setting dispenser;
    private final Setting portal;
    private final Setting box;
    private final Setting range;
    private final Setting echest;
    
    private boolean lambda$new$0(final Integer n) {
        return (boolean)this.box.getValue();
    }
    
    private int getEntityColor(final Entity entity) {
        if (entity instanceof EntityMinecartChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (entity instanceof EntityItemFrame && ((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return ColorUtil.Colors.WHITE;
        }
        return -1;
    }
    
    private int getTileEntityColor(final TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return ColorUtil.Colors.WHITE;
        }
        if (tileEntity instanceof TileEntityEndPortal) {
            return ColorUtil.Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return ColorUtil.Colors.PURPLE;
        }
        if (tileEntity instanceof TileEntityHopper) {
            return ColorUtil.Colors.DARK_RED;
        }
        if (tileEntity instanceof TileEntityDispenser) {
            return ColorUtil.Colors.ORANGE;
        }
        return -1;
    }
    
    private boolean lambda$new$1(final Float n) {
        return (boolean)this.outline.getValue();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final HashMap<BlockPos, Integer> hashMap = new HashMap<BlockPos, Integer>();
        for (final TileEntity tileEntity : Search.mc.world.loadedTileEntityList) {
            final BlockPos getPos;
            if (((tileEntity instanceof TileEntityEndPortal && (boolean)this.portal.getValue()) || (tileEntity instanceof TileEntityChest && (boolean)this.chest.getValue()) || (tileEntity instanceof TileEntityDispenser && (boolean)this.dispenser.getValue()) || (tileEntity instanceof TileEntityShulkerBox && (boolean)this.shulker.getValue()) || (tileEntity instanceof TileEntityEnderChest && (boolean)this.echest.getValue()) || (tileEntity instanceof TileEntityHopper && (boolean)this.hopper.getValue())) && Search.mc.player.getDistanceSq(getPos = tileEntity.getPos()) <= MathUtil.square((double)(float)this.range.getValue())) {
                final int tileEntityColor;
                if ((tileEntityColor = this.getTileEntityColor(tileEntity)) == -1) {
                    return;
                }
                hashMap.put(getPos, tileEntityColor);
                return;
            }
        }
        for (final Entity entity : Search.mc.world.loadedEntityList) {
            final BlockPos getPosition;
            if (((entity instanceof EntityItemFrame && (boolean)this.frame.getValue()) || (entity instanceof EntityMinecartChest && (boolean)this.cart.getValue())) && Search.mc.player.getDistanceSq(getPosition = entity.getPosition()) <= MathUtil.square((double)(float)this.range.getValue())) {
                final int entityColor;
                if ((entityColor = this.getEntityColor(entity)) == -1) {
                    return;
                }
                hashMap.put(getPosition, entityColor);
                return;
            }
        }
        final Iterator<Map.Entry<BlockPos, Integer>> iterator3 = hashMap.entrySet().iterator();
        if (iterator3.hasNext()) {
            final Map.Entry<BlockPos, Integer> entry = iterator3.next();
            final BlockPos blockPos = entry.getKey();
            final int intValue = entry.getValue();
            RenderUtil.drawBoxESP(blockPos, new Color(intValue), false, new Color(intValue), (float)this.lineWidth.getValue(), (boolean)this.outline.getValue(), (boolean)this.box.getValue(), (int)this.boxAlpha.getValue(), false);
        }
    }
    
    public Search() {
        super("Search", "Highlights Containers", Category.RENDER);
        this.range = this.add(new Setting("Range", 50.0f, 1.0f, 300.0f));
        this.portal = this.add(new Setting("Portal", true));
        this.chest = this.add(new Setting("Chest", true));
        this.dispenser = this.add(new Setting("Dispenser", false));
        this.shulker = this.add(new Setting("Shulker", true));
        this.echest = this.add(new Setting("Ender Chest", false));
        this.hopper = this.add(new Setting("Hopper", false));
        this.cart = this.add(new Setting("Minecart", false));
        this.frame = this.add(new Setting("Item Frame", false));
        this.box = this.add(new Setting("Box", false));
        this.boxAlpha = this.add(new Setting("BoxAlpha", 125, 0, 255, this::lambda$new$0));
        this.outline = this.add(new Setting("Outline", true));
        this.lineWidth = this.add(new Setting("LineWidth", 1.0f, 0.1f, 5.0f, this::lambda$new$1));
    }
}
