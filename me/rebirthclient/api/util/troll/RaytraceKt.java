//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.troll;

import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public final class RaytraceKt
{
    public static RayTraceResult rayTrace(final World world, final Vec3d vec3d, final Vec3d vec3d2, final int n, final Function2 function2) {
        final double x = vec3d.x;
        final double y = vec3d.y;
        final double z = vec3d.z;
        final int n2 = (int)(x + 1.073741824E9) - 1073741824;
        final int n3 = (int)(y + 1.073741824E9) - 1073741824;
        final int n4 = (int)(z + 1.073741824E9) - 1073741824;
        final BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(n2, n3, n4);
        final IBlockState getBlockState = world.getBlockState((BlockPos)mutableBlockPos);
        final double x2 = vec3d2.x;
        final double y2 = vec3d2.y;
        final double z2 = vec3d2.z;
        final RayTraceAction rayTraceAction = (RayTraceAction)function2.invoke((Object)mutableBlockPos, (Object)getBlockState);
        if (rayTraceAction == RayTraceAction.Null.INSTANCE) {
            return null;
        }
        if (rayTraceAction == RayTraceAction.Calc.INSTANCE) {
            final RayTraceResult raytrace = raytrace(getBlockState, world, mutableBlockPos, x, y, z, x2, y2, z2);
            if (raytrace != null) {
                return raytrace;
            }
        }
        else if (rayTraceAction instanceof RayTraceAction.Result) {
            return ((RayTraceAction.Result)rayTraceAction).getRayTraceResult();
        }
        final int n5 = (int)(x2 + 1.073741824E9) - 1073741824;
        final int n6 = (int)(y2 + 1.073741824E9) - 1073741824;
        final int n7 = (int)(z2 + 1.073741824E9) - 1073741824;
        int n8 = n;
        if (n8-- < 0) {
            return null;
        }
        if (n2 == n5 && n3 == n6 && n4 == n7) {
            return null;
        }
        int n9 = 999;
        int n10 = 999;
        int n11 = 999;
        double n12 = 999.0;
        double n13 = 999.0;
        double n14 = 999.0;
        final double n15 = vec3d2.x - x;
        final double n16 = vec3d2.y - y;
        final double n17 = vec3d2.z - z;
        if (n5 > n2) {
            n9 = n2 + 1;
            n12 = (n9 - x) / n15;
        }
        else if (n5 < n2) {
            n9 = n2;
            n12 = (n9 - x) / n15;
        }
        if (n6 > n3) {
            n10 = n3 + 1;
            n13 = (n10 - y) / n16;
        }
        else if (n6 < n3) {
            n10 = n3;
            n13 = (n10 - y) / n16;
        }
        if (n7 > n4) {
            n11 = n4 + 1;
            n14 = (n11 - z) / n17;
        }
        else if (n7 < n4) {
            n11 = n4;
            n14 = (n11 - z) / n17;
        }
        double n18;
        double n19;
        double n20;
        int n21;
        int n22;
        int n23;
        if (n12 < n13 && n12 < n14) {
            n18 = n9;
            n19 = y + n16 * n12;
            n20 = z + n17 * n12;
            n21 = n9 - (n5 - n2 >>> 31);
            n22 = (int)(n19 + 1.073741824E9) - 1073741824;
            n23 = (int)(n20 + 1.073741824E9) - 1073741824;
        }
        else if (n13 < n14) {
            n18 = x + n15 * n13;
            n19 = n10;
            n20 = z + n17 * n13;
            n21 = (int)(n18 + 1.073741824E9) - 1073741824;
            n22 = n10 - (n6 - n3 >>> 31);
            n23 = (int)(n20 + 1.073741824E9) - 1073741824;
        }
        else {
            n18 = x + n15 * n14;
            n19 = y + n16 * n14;
            n20 = n11;
            n21 = (int)(n18 + 1.073741824E9) - 1073741824;
            n22 = (int)(n19 + 1.073741824E9) - 1073741824;
            n23 = n11 - (n7 - n4 >>> 31);
        }
        mutableBlockPos.setPos(n21, n22, n23);
        final IBlockState getBlockState2 = world.getBlockState((BlockPos)mutableBlockPos);
        final RayTraceAction rayTraceAction2 = (RayTraceAction)function2.invoke((Object)mutableBlockPos, (Object)getBlockState2);
        if (rayTraceAction2 == RayTraceAction.Null.INSTANCE) {
            return null;
        }
        if (rayTraceAction2 == RayTraceAction.Calc.INSTANCE) {
            final RayTraceResult raytrace2 = raytrace(getBlockState2, world, mutableBlockPos, n18, n19, n20, x2, y2, z2);
            if (raytrace2 != null) {
                return raytrace2;
            }
        }
        else if (rayTraceAction2 instanceof RayTraceAction.Result) {
            return ((RayTraceAction.Result)rayTraceAction2).getRayTraceResult();
        }
        return null;
    }
    
    private static RayTraceResult raytrace(final IBlockState blockState, final World world, final BlockPos.MutableBlockPos mutableBlockPos, final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        final float n7 = (float)(n - mutableBlockPos.getX());
        final float n8 = (float)(n2 - mutableBlockPos.getY());
        final float n9 = (float)(n3 - mutableBlockPos.getZ());
        final AxisAlignedBB getBoundingBox = blockState.getBoundingBox((IBlockAccess)world, (BlockPos)mutableBlockPos);
        final float n10 = (float)getBoundingBox.minX;
        final float n11 = (float)getBoundingBox.minY;
        final float n12 = (float)getBoundingBox.minZ;
        final float n13 = (float)getBoundingBox.maxX;
        final float n14 = (float)getBoundingBox.maxY;
        final float n15 = (float)getBoundingBox.maxZ;
        final float n16 = (float)(n4 - mutableBlockPos.getX()) - n7;
        final float n17 = (float)(n5 - mutableBlockPos.getY()) - n8;
        final float n18 = (float)(n6 - mutableBlockPos.getZ()) - n9;
        float n19 = Float.NaN;
        float n20 = Float.NaN;
        float n21 = Float.NaN;
        EnumFacing enumFacing = EnumFacing.WEST;
        int n22 = 1;
        if (n16 * n16 >= 1.0E-7f) {
            final float n23 = (n10 - n7) / n16;
            if (0.0 <= n23 && n23 <= 1.0) {
                final float n24 = n8 + n17 * n23;
                final float n25 = n9 + n18 * n23;
                if (n11 <= n24 && n24 <= n14 && n12 <= n25 && n25 <= n15) {
                    n19 = n7 + n16 * n23;
                    n20 = n24;
                    n21 = n25;
                    n22 = 0;
                }
            }
            else {
                final float n26 = (n13 - n7) / n16;
                if (0.0 <= n26 && n26 <= 1.0) {
                    final float n27 = n8 + n17 * n26;
                    final float n28 = n9 + n18 * n26;
                    if (n11 <= n27 && n27 <= n14 && n12 <= n28 && n28 <= n15) {
                        n19 = n7 + n16 * n26;
                        n20 = n27;
                        n21 = n28;
                        enumFacing = EnumFacing.EAST;
                        n22 = 0;
                    }
                }
            }
        }
        Label_0811: {
            if (n17 * n17 >= 1.0E-7f) {
                final float n29 = (n11 - n8) / n17;
                if (0.0f <= n29 && n29 <= 1.0f) {
                    final float n30 = n7 + n16 * n29;
                    final float n31 = n9 + n18 * n29;
                    if (n10 <= n30 && n30 <= n13 && n12 <= n31 && n31 <= n15) {
                        final float n32 = n8 + n17 * n29;
                        if (n22 == 0) {
                            final float n33 = n30 - n7;
                            final float n34 = n33 * n33;
                            final float n35 = n32 - n8;
                            final float n36 = n34 + n35 * n35;
                            final float n37 = n31 - n9;
                            final float n38 = n36 + n37 * n37;
                            final float n39 = n19 - n7;
                            final float n40 = n39 * n39;
                            final float n41 = n20 - n8;
                            final float n42 = n40 + n41 * n41;
                            final float n43 = n21 - n9;
                            if (n38 >= n42 + n43 * n43) {
                                break Label_0811;
                            }
                        }
                        n19 = n30;
                        n20 = n32;
                        n21 = n31;
                        enumFacing = EnumFacing.DOWN;
                        n22 = 0;
                    }
                }
                else {
                    final float n44 = (n14 - n8) / n17;
                    if (0.0f <= n44 && n44 <= 1.0f) {
                        final float n45 = n7 + n16 * n44;
                        final float n46 = n9 + n18 * n44;
                        if (n10 <= n45 && n45 <= n13 && n12 <= n46 && n46 <= n15) {
                            final float n47 = n8 + n17 * n44;
                            if (n22 == 0) {
                                final float n48 = n45 - n7;
                                final float n49 = n48 * n48;
                                final float n50 = n47 - n8;
                                final float n51 = n49 + n50 * n50;
                                final float n52 = n46 - n9;
                                final float n53 = n51 + n52 * n52;
                                final float n54 = n19 - n7;
                                final float n55 = n54 * n54;
                                final float n56 = n20 - n8;
                                final float n57 = n55 + n56 * n56;
                                final float n58 = n21 - n9;
                                if (n53 >= n57 + n58 * n58) {
                                    break Label_0811;
                                }
                            }
                            n19 = n45;
                            n20 = n47;
                            n21 = n46;
                            enumFacing = EnumFacing.UP;
                            n22 = 0;
                        }
                    }
                }
            }
        }
        Label_1255: {
            if (n18 * n18 >= 1.0E-7) {
                final float n59 = (n12 - n9) / n18;
                if (0.0f <= n59 && n59 <= 1.0f) {
                    final float n60 = n7 + n16 * n59;
                    final float n61 = n8 + n17 * n59;
                    if (n10 <= n60 && n60 <= n13 && n11 <= n61 && n61 <= n14) {
                        final float n62 = n9 + n18 * n59;
                        if (n22 == 0) {
                            final float n63 = n60 - n7;
                            final float n64 = n63 * n63;
                            final float n65 = n61 - n8;
                            final float n66 = n64 + n65 * n65;
                            final float n67 = n62 - n9;
                            final float n68 = n66 + n67 * n67;
                            final float n69 = n19 - n7;
                            final float n70 = n69 * n69;
                            final float n71 = n20 - n8;
                            final float n72 = n70 + n71 * n71;
                            final float n73 = n21 - n9;
                            if (n68 >= n72 + n73 * n73) {
                                break Label_1255;
                            }
                        }
                        n19 = n60;
                        n20 = n61;
                        n21 = n62;
                        enumFacing = EnumFacing.NORTH;
                        n22 = 0;
                    }
                }
                else {
                    final float n74 = (n15 - n9) / n18;
                    if (0.0f <= n74 && n74 <= 1.0f) {
                        final float n75 = n7 + n16 * n74;
                        final float n76 = n8 + n17 * n74;
                        if (n10 <= n75 && n75 <= n13 && n11 <= n76 && n76 <= n14) {
                            final float n77 = n9 + n18 * n74;
                            if (n22 == 0) {
                                final float n78 = n75 - n7;
                                final float n79 = n78 * n78;
                                final float n80 = n76 - n8;
                                final float n81 = n79 + n80 * n80;
                                final float n82 = n77 - n9;
                                final float n83 = n81 + n82 * n82;
                                final float n84 = n19 - n7;
                                final float n85 = n84 * n84;
                                final float n86 = n20 - n8;
                                final float n87 = n85 + n86 * n86;
                                final float n88 = n21 - n9;
                                if (n83 >= n87 + n88 * n88) {
                                    break Label_1255;
                                }
                            }
                            n19 = n75;
                            n20 = n76;
                            n21 = n77;
                            enumFacing = EnumFacing.SOUTH;
                            n22 = 0;
                        }
                    }
                }
            }
        }
        RayTraceResult rayTraceResult;
        if (n22 == 0) {
            rayTraceResult = new RayTraceResult(new Vec3d(n19 + (double)mutableBlockPos.getX(), n20 + (double)mutableBlockPos.getY(), n21 + (double)mutableBlockPos.getZ()), enumFacing, mutableBlockPos.toImmutable());
        }
        else {
            rayTraceResult = null;
        }
        return rayTraceResult;
    }
}
