//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.entity.*;
import me.rebirthclient.api.util.math.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import java.util.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.util.*;

public class MovementUtil implements Wrapper
{
    public static double[] strafe(final double n) {
        return strafe((Entity)MovementUtil.mc.player, n);
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = MathUtil.mc.player.movementInput.moveForward;
        float moveStrafe = MathUtil.mc.player.movementInput.moveStrafe;
        float n2 = MathUtil.mc.player.prevRotationYaw + (MathUtil.mc.player.rotationYaw - MathUtil.mc.player.prevRotationYaw) * MovementUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static void setMoveSpeed(final double n) {
        double n2 = MovementUtil.mc.player.movementInput.moveForward;
        double n3 = MovementUtil.mc.player.movementInput.moveStrafe;
        float rotationYaw = MovementUtil.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            MovementUtil.mc.player.motionX = 0.0;
            MovementUtil.mc.player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            MovementUtil.mc.player.motionX = n2 * n * -Math.sin(Math.toRadians(rotationYaw)) + n3 * n * Math.cos(Math.toRadians(rotationYaw));
            MovementUtil.mc.player.motionZ = n2 * n * Math.cos(Math.toRadians(rotationYaw)) - n3 * n * -Math.sin(Math.toRadians(rotationYaw));
        }
    }
    
    public static boolean isJumping() {
        return MovementUtil.mc.gameSettings.keyBindJump.isKeyDown() || (InventoryMove.INSTANCE.isOn() && Keyboard.isKeyDown(MovementUtil.mc.gameSettings.keyBindJump.getKeyCode()) && !(MovementUtil.mc.currentScreen instanceof GuiChat));
    }
    
    public static void strafe(final MoveEvent p0, final double p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dload_1        
        //     4: invokestatic    me/rebirthclient/api/util/MovementUtil.strafe:(D)[D
        //     7: astore_3       
        //     8: aload_0        
        //     9: aload_3        
        //    10: iconst_0       
        //    11: daload         
        //    12: invokevirtual   me/rebirthclient/api/events/impl/MoveEvent.setX:(D)V
        //    15: aload_0        
        //    16: aload_3        
        //    17: iconst_1       
        //    18: daload         
        //    19: invokevirtual   me/rebirthclient/api/events/impl/MoveEvent.setZ:(D)V
        //    22: goto            35
        //    25: aload_0        
        //    26: dconst_0       
        //    27: invokevirtual   me/rebirthclient/api/events/impl/MoveEvent.setX:(D)V
        //    30: aload_0        
        //    31: dconst_0       
        //    32: invokevirtual   me/rebirthclient/api/events/impl/MoveEvent.setZ:(D)V
        //    35: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static double getDistance3D() {
        final double n = MovementUtil.mc.player.posX - MovementUtil.mc.player.prevPosX;
        final double n2 = MovementUtil.mc.player.posY - MovementUtil.mc.player.prevPosY;
        final double n3 = MovementUtil.mc.player.posZ - MovementUtil.mc.player.prevPosZ;
        return Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    public static double getJumpSpeed() {
        double n = 0.0;
        if (MovementUtil.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            n += (MovementUtil.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1;
        }
        return n;
    }
    
    public static boolean noMovementKeys() {
        return !MovementUtil.mc.player.movementInput.forwardKeyDown && !MovementUtil.mc.player.movementInput.backKeyDown && !MovementUtil.mc.player.movementInput.rightKeyDown && !MovementUtil.mc.player.movementInput.leftKeyDown;
    }
    
    public static boolean isInMovementDirection(final double n, final double n2, final double n3) {
        return (MovementUtil.mc.player.motionX == 0.0 && MovementUtil.mc.player.motionZ == 0.0) || new BlockPos((Entity)MovementUtil.mc.player).add(MovementUtil.mc.player.motionX * 10000.0, 0.0, MovementUtil.mc.player.motionZ * 10000.0).distanceSq(n, n2, n3) < new BlockPos((Entity)MovementUtil.mc.player).add(MovementUtil.mc.player.motionX * -10000.0, 0.0, MovementUtil.mc.player.motionY * -10000.0).distanceSq(n, n2, n3);
    }
    
    public static double getSpeed(final boolean b) {
        double n = 0.2873;
        if (MovementUtil.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        if (b && MovementUtil.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
            n /= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SLOWNESS)).getAmplifier() + 1);
        }
        if (SneakManager.isSneaking) {
            n /= 5.0;
        }
        return n;
    }
    
    public static double getSpeed() {
        return getSpeed(false);
    }
    
    public static double[] strafe(final Entity entity, final MovementInput movementInput, final double n) {
        float moveForward = movementInput.moveForward;
        float moveStrafe = movementInput.moveStrafe;
        float n2 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * MovementUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        return new double[] { moveForward * n * -Math.sin(Math.toRadians(n2)) + moveStrafe * n * Math.cos(Math.toRadians(n2)), moveForward * n * Math.cos(Math.toRadians(n2)) - moveStrafe * n * -Math.sin(Math.toRadians(n2)) };
    }
    
    public static double getSpeed(final boolean b, double n) {
        if (MovementUtil.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        if (b && MovementUtil.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
            n /= 1.0 + 0.2 * (Objects.requireNonNull(MovementUtil.mc.player.getActivePotionEffect(MobEffects.SLOWNESS)).getAmplifier() + 1);
        }
        if (SneakManager.isSneaking) {
            n /= 5.0;
        }
        return n;
    }
    
    public static double getDistance2D() {
        final double n = MovementUtil.mc.player.posX - MovementUtil.mc.player.prevPosX;
        final double n2 = MovementUtil.mc.player.posZ - MovementUtil.mc.player.prevPosZ;
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static double[] strafe(final Entity entity, final double n) {
        return strafe(entity, MovementUtil.mc.player.movementInput, n);
    }
    
    public static boolean anyMovementKeys() {
        return MovementUtil.mc.player.movementInput.forwardKeyDown || MovementUtil.mc.player.movementInput.backKeyDown || MovementUtil.mc.player.movementInput.leftKeyDown || MovementUtil.mc.player.movementInput.rightKeyDown || MovementUtil.mc.player.movementInput.jump || MovementUtil.mc.player.movementInput.sneak;
    }
    
    public static MovementInput inverse(final Entity entity, final double n) {
        final MovementInput movementInput = new MovementInput();
        movementInput.sneak = entity.isSneaking();
        final float n2 = -1.0f;
        if (n2 <= 1.0f) {
            final float n3 = -1.0f;
            if (n3 <= 1.0f) {
                final MovementInput movementInput2 = new MovementInput();
                movementInput2.moveForward = n2;
                movementInput2.moveStrafe = n3;
                movementInput2.sneak = entity.isSneaking();
                final double[] strafe = strafe(entity, movementInput2, n);
                if (entity.isSneaking()) {
                    final double[] array = strafe;
                    final int n4 = 0;
                    array[n4] *= 0.30000001192092896;
                    final double[] array2 = strafe;
                    final int n5 = 1;
                    array2[n5] *= 0.30000001192092896;
                }
                final double n6 = strafe[0];
                final double n7 = strafe[1];
                if (n6 < 0.0) {
                    if (entity.motionX > n6) {
                        return null;
                    }
                }
                else if (entity.motionX < n6) {
                    return null;
                }
                if (n7 < 0.0) {
                    if (entity.motionZ > n7) {
                        return null;
                    }
                }
                else if (entity.motionZ < n7) {
                    return null;
                }
                movementInput.moveForward = n2;
                movementInput.moveStrafe = n3;
                return null;
            }
            return null;
        }
        return movementInput;
    }
}
