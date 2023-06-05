//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class InterpolationUtil implements Wrapper
{
    public static Vec3d getInterpolatedPos(final Entity entity, final float n, final boolean b) {
        final Vec3d add = new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n, (entity.posZ - entity.lastTickPosZ) * n));
        if (b) {
            return add.subtract(InterpolationUtil.mc.getRenderManager().renderPosX, InterpolationUtil.mc.getRenderManager().renderPosY, InterpolationUtil.mc.getRenderManager().renderPosZ);
        }
        return add;
    }
    
    public static AxisAlignedBB getInterpolatedAxis(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - InterpolationUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - InterpolationUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - InterpolationUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - InterpolationUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - InterpolationUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - InterpolationUtil.mc.getRenderManager().viewerPosZ);
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return interpolateEntity(entity, n).subtract(InterpolationUtil.mc.getRenderManager().renderPosX, InterpolationUtil.mc.getRenderManager().renderPosY, InterpolationUtil.mc.getRenderManager().renderPosZ);
    }
    
    public static double getInterpolatedDouble(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    public static float getInterpolatedFloat(final float n, final float n2, final float n3) {
        return n + (n2 - n) * n3;
    }
}
