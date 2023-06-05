//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.util.math.*;
import me.rebirthclient.asm.accessors.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class RotationManager extends Mod
{
    private float pitch;
    private float yaw;
    
    public void lookAtPos(final BlockPos blockPos) {
        final float[] calcAngle = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(Wrapper.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
        this.setRotations(calcAngle[0], calcAngle[1]);
    }
    
    public void setRotations(final float n, final float rotationPitch) {
        RotationManager.mc.player.rotationYaw = n;
        RotationManager.mc.player.rotationYawHead = n;
        RotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public float[] injectYawStep(final float[] array, float n) {
        if (n < 0.1f) {
            n = 0.1f;
        }
        if (n > 1.0f) {
            n = 1.0f;
        }
        if (n < 1.0f && array != null) {
            final float lastReportedYaw = ((IEntityPlayerSP)RotationManager.mc.player).getLastReportedYaw();
            final float wrapDegrees = MathHelper.wrapDegrees(array[0] - lastReportedYaw);
            if (Math.abs(wrapDegrees) > 180.0f * n) {
                array[0] = lastReportedYaw + wrapDegrees * (180.0f * n / Math.abs(wrapDegrees));
            }
        }
        return new float[] { array[0], array[1] };
    }
    
    public void resetRotationsPacket() {
        final float[] array = { RotationManager.mc.player.rotationYaw, RotationManager.mc.player.rotationPitch };
        RotationManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(array[0], array[1], RotationManager.mc.player.onGround));
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public boolean isInFov(final BlockPos blockPos) {
        final int yaw4D = this.getYaw4D();
        return (yaw4D != 0 || blockPos.getZ() - RotationManager.mc.player.getPositionVector().z >= 0.0) && (yaw4D != 1 || blockPos.getX() - RotationManager.mc.player.getPositionVector().x <= 0.0) && (yaw4D != 2 || blockPos.getZ() - RotationManager.mc.player.getPositionVector().z <= 0.0) && (yaw4D != 3 || blockPos.getX() - RotationManager.mc.player.getPositionVector().x >= 0.0);
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public static float[] calculateAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double n = vec3d2.x - vec3d.x;
        final double n2 = (vec3d2.y - vec3d.y) * -1.0;
        final double n3 = vec3d2.z - vec3d.z;
        final double n4 = MathHelper.sqrt(n * n + n3 * n3);
        final float n5 = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(n3, n)) - 90.0);
        float n6 = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(n2, n4)));
        if (n6 > 90.0f) {
            n6 = 90.0f;
        }
        else if (n6 < -90.0f) {
            n6 = -90.0f;
        }
        return new float[] { n5, n6 };
    }
    
    public float[] getAngle(final Vec3d vec3d) {
        final Vec3d vec3d2 = new Vec3d(RotationManager.mc.player.posX, RotationManager.mc.player.posY + RotationManager.mc.player.getEyeHeight(), RotationManager.mc.player.posZ);
        final double n = vec3d.x - vec3d2.x;
        final double n2 = vec3d.y - vec3d2.y;
        final double n3 = vec3d.z - vec3d2.z;
        return new float[] { RotationManager.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(n3, n)) - 90.0f - RotationManager.mc.player.rotationYaw), RotationManager.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(n2, Math.sqrt(n * n + n3 * n3)))) - RotationManager.mc.player.rotationPitch) };
    }
    
    public void resetRotations() {
        RotationManager.mc.player.rotationYaw = this.yaw;
        RotationManager.mc.player.rotationYawHead = this.yaw;
        RotationManager.mc.player.rotationPitch = this.pitch;
    }
    
    public void updateRotations() {
        this.yaw = RotationManager.mc.player.rotationYaw;
        this.pitch = RotationManager.mc.player.rotationPitch;
    }
    
    public int getYaw4D() {
        return MathHelper.floor(RotationManager.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
    }
    
    public void setPlayerRotations(final float n, final float rotationPitch) {
        RotationManager.mc.player.rotationYaw = n;
        RotationManager.mc.player.rotationYawHead = n;
        RotationManager.mc.player.rotationPitch = rotationPitch;
    }
    
    public void lookAtVec3dPacket(final Vec3d vec3d, final boolean b) {
        final float[] angle = this.getAngle(vec3d);
        RotationManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], angle[1], RotationManager.mc.player.onGround));
        if (b) {
            ((IEntityPlayerSP)RotationManager.mc.player).setLastReportedYaw(angle[0]);
            ((IEntityPlayerSP)RotationManager.mc.player).setLastReportedPitch(angle[1]);
        }
    }
    
    public void lookAtVec3d(final Vec3d vec3d) {
        final float[] calcAngle = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(Wrapper.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        this.setRotations(calcAngle[0], calcAngle[1]);
    }
    
    public String getDirection4D(final boolean b) {
        final int yaw4D = this.getYaw4D();
        if (yaw4D == 0) {
            return "South (+Z)";
        }
        if (yaw4D == 1) {
            return "West (-X)";
        }
        if (yaw4D == 2) {
            return (b ? "\u00c2¡ìc" : "") + "North (-Z)";
        }
        if (yaw4D == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }
    
    public void lookAtVec3dPacket(final Vec3d vec3d) {
        final float[] angle = this.getAngle(vec3d);
        RotationManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], angle[1], RotationManager.mc.player.onGround));
    }
}
