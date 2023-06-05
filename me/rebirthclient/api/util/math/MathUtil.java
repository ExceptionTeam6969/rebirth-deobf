//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.math;

import java.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.*;

public class MathUtil implements Wrapper
{
    public static Vec3d calculateLine(final Vec3d vec3d, final Vec3d vec3d2, final double n) {
        final double sqrt = Math.sqrt(multiply(vec3d2.x - vec3d.x) + multiply(vec3d2.y - vec3d.y) + multiply(vec3d2.z - vec3d.z));
        return new Vec3d(vec3d.x + (vec3d2.x - vec3d.x) / sqrt * n, vec3d.y + (vec3d2.y - vec3d.y) / sqrt * n, vec3d.z + (vec3d2.z - vec3d.z) / sqrt * n);
    }
    
    public static Integer increaseNumber(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n + n3;
        }
        return n2;
    }
    
    public static Map sortByValue(final Map map, final boolean b) {
        final LinkedList<Object> list = new LinkedList<Object>(map.entrySet());
        if (b) {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue(Comparator.reverseOrder()));
        }
        else {
            list.sort((Comparator<? super Object>)Map.Entry.comparingByValue());
        }
        final LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        final Iterator<Map.Entry<Object, V>> iterator = list.iterator();
        if (iterator.hasNext()) {
            final Map.Entry<Object, V> entry = iterator.next();
            linkedHashMap.put(entry.getKey(), entry.getValue());
            return null;
        }
        return linkedHashMap;
    }
    
    public static Float increaseNumber(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n + n3;
        }
        return n2;
    }
    
    public static float[] calcAngleNoY(final Vec3d vec3d, final Vec3d vec3d2) {
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d2.z - vec3d.z, vec3d2.x - vec3d.x)) - 90.0) };
    }
    
    public static double multiply(final double n) {
        return n * n;
    }
    
    public static double animate(final double n, double n2, double n3) {
        final boolean b = n > n2;
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        else if (n3 > 1.0) {
            n3 = 1.0;
        }
        double n4 = (Math.max(n, n2) - Math.min(n, n2)) * n3;
        if (n4 < 0.1) {
            n4 = 0.1;
        }
        if (b) {
            n2 += n4;
        }
        else {
            n2 -= n4;
        }
        return n2;
    }
    
    public static double round(final double n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException();
        }
        return BigDecimal.valueOf(n).setScale(n2, RoundingMode.FLOOR).doubleValue();
    }
    
    public static int[] toRGBAArray(final int n) {
        return new int[] { n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF };
    }
    
    public static int clamp(final int n, final int n2, final int n3) {
        return (n < n2) ? n2 : Math.min(n, n3);
    }
    
    public static float clamp(final float n, final float n2, final float n3) {
        return (n < n2) ? n2 : Math.min(n, n3);
    }
    
    public static Vec3d[] convertVectors(final Vec3d vec3d, final Vec3d[] array) {
        final Vec3d[] array2 = new Vec3d[array.length];
        int n = 0;
        if (n < array.length) {
            array2[n] = vec3d.add(array[n]);
            ++n;
            return null;
        }
        return array2;
    }
    
    public static int randomBetween(final int n, final int n2) {
        return n + new Random().nextInt() * (n2 - n);
    }
    
    public static Float decreaseNumber(final float n, final float n2, final float n3) {
        if (n > n2) {
            return n - n3;
        }
        return n2;
    }
    
    public static double square(final double n) {
        return n * n;
    }
    
    public static Vec3d extrapolatePlayerPosition(final EntityPlayer entityPlayer, final int n) {
        final Vec3d calculateLine = calculateLine(new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ), new Vec3d(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ), (multiply(entityPlayer.motionX) + multiply(entityPlayer.motionY) + multiply(entityPlayer.motionZ)) * n);
        return new Vec3d(calculateLine.x, entityPlayer.posY, calculateLine.z);
    }
    
    public static float animate(final float n, final float n2, final float n3) {
        float n4 = (n2 - n) / Math.max((float)Minecraft.getDebugFPS(), 5.0f) * 15.0f;
        if (n4 > 0.0f) {
            n4 = Math.min(n2 - n, Math.max(n3, n4));
        }
        else if (n4 < 0.0f) {
            n4 = Math.max(n2 - n, Math.min(-n3, n4));
        }
        return n + n4;
    }
    
    public static float randomBetween(final float n, final float n2) {
        return n + new Random().nextFloat() * (n2 - n);
    }
    
    public static float random(final float n, final float n2) {
        return (float)(Math.random() * (n2 - n) + n);
    }
    
    public static float round(final float n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException();
        }
        return BigDecimal.valueOf(n).setScale(n2, RoundingMode.FLOOR).floatValue();
    }
    
    public static double clamp(final double n, final double n2, final double n3) {
        return (n < n2) ? n2 : Math.min(n, n3);
    }
    
    public static Integer decreaseNumber(final int n, final int n2, final int n3) {
        if (n > n2) {
            return n - n3;
        }
        return n2;
    }
    
    public static List getBlockBlocks(final Entity entity) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final AxisAlignedBB getEntityBoundingBox = entity.getEntityBoundingBox();
        final double posY = entity.posY;
        final double round = round(getEntityBoundingBox.minX, 0);
        final double round2 = round(getEntityBoundingBox.minZ, 0);
        final double round3 = round(getEntityBoundingBox.maxX, 0);
        final double round4 = round(getEntityBoundingBox.maxZ, 0);
        if (round != round3) {
            list.add(new Vec3d(round, posY, round2));
            list.add(new Vec3d(round3, posY, round2));
            if (round2 != round4) {
                list.add(new Vec3d(round, posY, round4));
                list.add(new Vec3d(round3, posY, round4));
                return list;
            }
        }
        else if (round2 != round4) {
            list.add(new Vec3d(round, posY, round2));
            list.add(new Vec3d(round, posY, round4));
            return list;
        }
        list.add(entity.getPositionVector());
        return list;
    }
    
    public static Vector3f mix(final Vector3f vector3f, final Vector3f vector3f2, final float n) {
        return new Vector3f(vector3f.x * (1.0f - n) + vector3f2.x * n, vector3f.y * (1.0f - n) + vector3f2.y * n, vector3f.z * (1.0f - n) + vector3f.z * n);
    }
    
    public static Vec3d roundVec(final Vec3d vec3d, final int n) {
        return new Vec3d(round(vec3d.x, n), round(vec3d.y, n), round(vec3d.z, n));
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double n = vec3d2.x - vec3d.x;
        final double n2 = (vec3d2.y - vec3d.y) * -1.0;
        final double n3 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(n3, n)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(n2, MathHelper.sqrt(n * n + n3 * n3)))) };
    }
    
    public static double normalize(final double n, final double n2, final double n3) {
        return (n - n2) / (n3 - n2);
    }
}
