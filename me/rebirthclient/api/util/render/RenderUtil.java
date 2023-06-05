//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render;

import java.awt.*;
import java.nio.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.client.renderer.culling.*;
import org.lwjgl.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.client.gui.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.*;

public class RenderUtil implements Wrapper
{
    public static final ICamera camera;
    private static final FloatBuffer screenCoords;
    private static final FloatBuffer projection;
    private static final IntBuffer viewport;
    private static final FloatBuffer modelView;
    
    public static void drawText(final AxisAlignedBB axisAlignedBB, final String s) {
        if (axisAlignedBB == null || s == null) {
            return;
        }
        drawText(s, axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) / 2.0 - RenderUtil.mc.getRenderManager().renderPosX, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0 - RenderUtil.mc.getRenderManager().renderPosY - 1.5, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) / 2.0 - RenderUtil.mc.getRenderManager().renderPosZ, new Color(255, 255, 255, 255));
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final int n4) {
        final float n5 = (n4 >> 24 & 0xFF) / 255.0f;
        final float n6 = (n4 >> 16 & 0xFF) / 255.0f;
        final float n7 = (n4 >> 8 & 0xFF) / 255.0f;
        final float n8 = (n4 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GL11.glColor4f(n6, n7, n8, n5);
        GL11.glBegin(9);
        int n9 = 0;
        if (n9 <= 360) {
            GL11.glVertex2d(n + Math.sin(n9 * 3.141526 / 180.0) * n3, n2 + Math.cos(n9 * 3.141526 / 180.0) * n3);
            ++n9;
            return;
        }
        GL11.glEnd();
        GlStateManager.resetColor();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static ByteBuffer readImageToBuffer(final InputStream inputStream) throws IOException {
        final BufferedImage read = ImageIO.read(inputStream);
        final int[] rgb = read.getRGB(0, 0, read.getWidth(), read.getHeight(), null, 0, read.getWidth());
        final ByteBuffer allocate = ByteBuffer.allocate(4 * rgb.length);
        Arrays.stream(rgb).map(RenderUtil::lambda$readImageToBuffer$0).forEach(allocate::putInt);
        allocate.flip();
        return allocate;
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4, final double n3) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n2), n3, false, false, 0);
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4, n3, false, false, 0, false);
        }
    }
    
    private static void buildPosColor(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final double n9, final double n10, final double n11, final double n12, final double n13, final double n14) {
        bufferBuilder.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n10, n11).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n12, n10, n14).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n9, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n11).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
        bufferBuilder.pos(n12, n13, n14).color(n5, n6, n7, n8).endVertex();
    }
    
    public static void drawGradientLine(final float n, final float n2, final float n3, final float n4, final float n5, final int n6, final int n7) {
        final float n8 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n6 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n7 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n7 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n7 & 0xFF) / 255.0f;
        final float n15 = (n7 >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(n5);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n11).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n8, n9, n10, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n12, n13, n14, n15).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n12, n13, n14, n15).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static void drawSelectionBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4) {
        final AxisAlignedBB grow = RenderUtil.mc.world.getBlockState(blockPos).getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.002);
        final AxisAlignedBB interpolatedAxis = InterpolationUtil.getInterpolatedAxis(grow);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(grow)) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            if (b4) {
                GlStateManager.enableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(true);
            }
            else {
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
            }
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            if (b3) {
                RenderGlobal.renderFilledBox(interpolatedAxis, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n2 / 255.0f);
            }
            if (b2) {
                drawBlockOutline(interpolatedAxis, b ? color2 : color, n, b4);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void testESP(final AxisAlignedBB axisAlignedBB, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4) {
        final AxisAlignedBB interpolatedAxis = InterpolationUtil.getInterpolatedAxis(axisAlignedBB);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(axisAlignedBB)) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            if (b4) {
                GlStateManager.enableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(true);
            }
            else {
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
            }
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            if (b3) {
                RenderGlobal.renderFilledBox(interpolatedAxis, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n2 / 255.0f);
            }
            if (b2) {
                drawBlockOutline(interpolatedAxis, b ? color2 : color, n, false);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    static {
        camera = (ICamera)new Frustum();
        screenCoords = BufferUtils.createFloatBuffer(3);
        viewport = BufferUtils.createIntBuffer(16);
        modelView = BufferUtils.createFloatBuffer(16);
        projection = BufferUtils.createFloatBuffer(16);
    }
    
    public static void drawFadingBox(final BlockPos blockPos, final Color color, final Color color2, final double n) {
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        int n2 = 0;
        if (n2 < length) {
            final EnumFacing enumFacing = values[n2];
            if (enumFacing != EnumFacing.UP) {
                drawFadingSide(blockPos, enumFacing, color, color2, n);
            }
            ++n2;
        }
    }
    
    private static int lambda$readImageToBuffer$0(final int n) {
        return n << 8 | (n >> 24 & 0xFF);
    }
    
    public static void drawGlow(final double n, final double n2, final double n3, final double n4, final int n5) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        drawVGradientRect((float)(int)n, (float)(int)n2, (float)(int)n3, (float)(int)(n2 + (n4 - n2) / 2.0), ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0), n5);
        drawVGradientRect((float)(int)n, (float)(int)(n2 + (n4 - n2) / 2.0), (float)(int)n3, (float)(int)n4, n5, ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0));
        final int n6 = (int)((n4 - n2) / 2.0);
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n6, 0, n5, ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0));
        drawPolygonPart(n, n2 + (n4 - n2) / 2.0, n6, 1, n5, ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0));
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n6, 2, n5, ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0));
        drawPolygonPart(n3, n2 + (n4 - n2) / 2.0, n6, 3, n5, ColorUtil.toRGBA(new Color(n5).getRed(), new Color(n5).getGreen(), new Color(n5).getBlue(), 0));
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawEntityBoxESP(final Entity entity, Color friendColor, final boolean b, final Color color, final float n, final boolean b2, final boolean b3, final int n2) {
        final Vec3d interpolatedPos = InterpolationUtil.getInterpolatedPos(entity, RenderUtil.mc.getRenderPartialTicks(), true);
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interpolatedPos.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interpolatedPos.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interpolatedPos.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interpolatedPos.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interpolatedPos.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interpolatedPos.z);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        if (entity instanceof EntityPlayer && Managers.FRIENDS.isFriend(entity.getName())) {
            friendColor = Managers.COLORS.getFriendColor(friendColor.getAlpha());
        }
        if (b3) {
            RenderGlobal.renderFilledBox(axisAlignedBB, friendColor.getRed() / 255.0f, friendColor.getGreen() / 255.0f, friendColor.getBlue() / 255.0f, n2 / 255.0f);
        }
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        if (b2) {
            drawBlockOutline(axisAlignedBB, b ? color : friendColor, n, false);
        }
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void drawBBFill(final AxisAlignedBB axisAlignedBB, final Color color, final int n) {
        final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB2.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB2, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBlockWireframe(final BlockPos blockPos, final Color color, final float n, final double n2, final boolean b) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final double n3 = blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX;
        final double n4 = blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY;
        final double n5 = blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ;
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha = color.getAlpha();
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n3, n4, n5, n3 + 1.0, n4 + 1.0 + n2, n5 + 1.0);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
        if (!b) {
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
            getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(red, green, blue, alpha).endVertex();
        }
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOutline(final AxisAlignedBB axisAlignedBB, final Color color, final float n, final boolean b) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        if (b) {
            GlStateManager.enableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(true);
        }
        else {
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
        }
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks());
            drawBlockOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolateEntity.x, -interpolateEntity.y, -interpolateEntity.z), color, n);
        }
    }
    
    public static void drawVGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n8, n9, n10, n7).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n12, n13, n14, n11).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawBBBox(final AxisAlignedBB axisAlignedBB, final Color color, final int n) {
        final AxisAlignedBB interpolateAxis = interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ));
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = n / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth((float)RenderSetting.INSTANCE.outlineWidth.getValue());
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4) {
        if (b3) {
            drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n2));
        }
        if (b2) {
            drawBlockOutline(blockPos, b ? color2 : color, n, b4);
        }
    }
    
    public static void drawBoxESP(final BlockPos blockPos, final Color color, final int n) {
        drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n));
    }
    
    public static void drawBlockOutline(final AxisAlignedBB axisAlignedBB, final Color color, final float n) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n2, n3, n4, n5).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBlockOutline(final BlockPos blockPos, final Color color, final float n, final boolean b, final double n2, final boolean b2, final boolean b3, final int n3, final boolean b4) {
        if (b2) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n3);
            drawFadingOutline(blockPos, b3 ? color : color2, b3 ? color2 : color, n, n2);
            return;
        }
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((b || getBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            drawBlockOutline(new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY + n2, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ).grow(0.0020000000949949026), color, n, b4);
        }
    }
    
    public static AxisAlignedBB interpolateAxis(final AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }
    
    private static void buildPosColor(final BufferBuilder bufferBuilder, final float n, final float n2, final float n3, final float n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10) {
        buildPosColor(bufferBuilder, n, n2, n3, n4, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public static void drawSexyBoxPhobosIsRetardedFuckYouESP(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final float n, final boolean b, final boolean b2, final boolean b3, float n2, final float n3, final float n4) {
        final double n5 = 0.5 * (1.0f - n3);
        final AxisAlignedBB interpolateAxis = interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX + n5, axisAlignedBB.minY + n5 + (1.0f - n4), axisAlignedBB.minZ + n5, axisAlignedBB.maxX - n5, axisAlignedBB.maxY - n5, axisAlignedBB.maxZ - n5));
        float n6 = color.getRed() / 255.0f;
        float n7 = color.getGreen() / 255.0f;
        float n8 = color.getBlue() / 255.0f;
        final float n9 = color.getAlpha() / 255.0f;
        float n10 = color2.getRed() / 255.0f;
        float n11 = color2.getGreen() / 255.0f;
        float n12 = color2.getBlue() / 255.0f;
        final float n13 = color2.getAlpha() / 255.0f;
        if (b3) {
            n6 = Managers.COLORS.getCurrent().getRed() / 255.0f;
            n7 = Managers.COLORS.getCurrent().getGreen() / 255.0f;
            n8 = Managers.COLORS.getCurrent().getBlue() / 255.0f;
            n10 = Managers.COLORS.getCurrent().getRed() / 255.0f;
            n11 = Managers.COLORS.getCurrent().getGreen() / 255.0f;
            n12 = Managers.COLORS.getCurrent().getBlue() / 255.0f;
        }
        if (n2 > 1.0f) {
            n2 = 1.0f;
        }
        final float n14 = n9 * n2;
        final float n15 = n13 * n2;
        if (b2) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(interpolateAxis, n6, n7, n8, n14);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        if (b) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            final Tessellator getInstance = Tessellator.getInstance();
            final BufferBuilder getBuffer = getInstance.getBuffer();
            getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.maxZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.minY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.maxX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getBuffer.pos(interpolateAxis.minX, interpolateAxis.maxY, interpolateAxis.minZ).color(n10, n11, n12, n15).endVertex();
            getInstance.draw();
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawRectangleCorrectly(final int n, final int n2, final int n3, final int n4, final int n5) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(n, n2, n + n3, n2 + n4, n5);
    }
    
    public static void drawCircleVertices(final AxisAlignedBB axisAlignedBB, final float n, final Color color) {
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
        int n6 = 0;
        if (n6 < 360) {
            getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            getBuffer.pos(axisAlignedBB.getCenter().x + Math.sin(n6 * 3.1415926 / 180.0) * n, axisAlignedBB.minY, axisAlignedBB.getCenter().z + Math.cos(n6 * 3.1415926 / 180.0) * n).color(n2, n3, n4, n5).endVertex();
            getBuffer.pos(axisAlignedBB.getCenter().x + Math.sin((n6 + 1) * 3.1415926 / 180.0) * n, axisAlignedBB.minY, axisAlignedBB.getCenter().z + Math.cos((n6 + 1) * 3.1415926 / 180.0) * n).color(n2, n3, n4, n5).endVertex();
            getInstance.draw();
            ++n6;
            return;
        }
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox(final AxisAlignedBB axisAlignedBB, final Color color) {
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawModalRect(final int n, final int n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8, final float n9, final float n10) {
        Gui.drawScaledCustomSizeModalRect(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public static void drawFadingOutline(final AxisAlignedBB axisAlignedBB, final Color color, final Color color2, final float n, final double n2) {
        final float n3 = color.getRed() / 255.0f;
        final float n4 = color.getGreen() / 255.0f;
        final float n5 = color.getBlue() / 255.0f;
        final float n6 = color.getAlpha() / 255.0f;
        final float n7 = color2.getRed() / 255.0f;
        final float n8 = color2.getGreen() / 255.0f;
        final float n9 = color2.getBlue() / 255.0f;
        final float n10 = color2.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(n);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY + n2, axisAlignedBB.minZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY + n2, axisAlignedBB.maxZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY + n2, axisAlignedBB.maxZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY + n2, axisAlignedBB.maxZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY + n2, axisAlignedBB.maxZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY + n2, axisAlignedBB.minZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY + n2, axisAlignedBB.minZ).color(n3, n4, n5, n6).endVertex();
        getBuffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY + n2, axisAlignedBB.minZ).color(n3, n4, n5, n6).endVertex();
        getInstance.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawText(final AxisAlignedBB axisAlignedBB, final String s, final Color color) {
        if (axisAlignedBB == null || s == null) {
            return;
        }
        drawText(s, axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) / 2.0 - RenderUtil.mc.getRenderManager().renderPosX, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0 - RenderUtil.mc.getRenderManager().renderPosY - 1.5, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) / 2.0 - RenderUtil.mc.getRenderManager().renderPosZ, color);
    }
    
    public static void glColor(final int n) {
        GL11.glColor4f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawBoxESP(final AxisAlignedBB axisAlignedBB, final Color color, final boolean b, final Color color2, final float n, final boolean b2, final boolean b3, final int n2, final boolean b4) {
        final AxisAlignedBB offset = axisAlignedBB.offset(-RenderUtil.mc.getRenderManager().viewerPosX, -RenderUtil.mc.getRenderManager().viewerPosY, -RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(axisAlignedBB)) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            if (b4) {
                GlStateManager.enableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(true);
            }
            else {
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
            }
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(n);
            if (b3) {
                RenderGlobal.renderFilledBox(offset, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n2 / 255.0f);
            }
            if (b2) {
                drawBlockOutline(offset, b ? color2 : color, n, b4);
            }
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawFadingOutline(final BlockPos blockPos, final Color color, final Color color2, final float n, final double n2) {
        final IBlockState getBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        final Vec3d interpolatedPos = InterpolationUtil.getInterpolatedPos((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks(), false);
        drawFadingOutline(getBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolatedPos.x, -interpolatedPos.y, -interpolatedPos.z).expand(0.0, 0.0, 0.0), color, color2, n, n2);
    }
    
    public static void drawLine(final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        final float n7 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n6 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GL11.glLineWidth(n5);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n10).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static Vec3d get2DPos(final double n, final double n2, final double n3) {
        GL11.glGetFloat(2982, RenderUtil.modelView);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        if (GLU.gluProject((float)n, (float)n2, (float)n3, RenderUtil.modelView, RenderUtil.projection, RenderUtil.viewport, RenderUtil.screenCoords)) {
            return new Vec3d((double)RenderUtil.screenCoords.get(0), (double)(Display.getHeight() - RenderUtil.screenCoords.get(1)), (double)RenderUtil.screenCoords.get(2));
        }
        return null;
    }
    
    public static void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawNameTagOutline(float n, float n2, float n3, float n4, final float n5, final int n6, final int n7, final boolean b) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        n *= 2.0f;
        n3 *= 2.0f;
        n2 *= 2.0f;
        n4 *= 2.0f;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawLine(n, n2, n, n4, n5, b ? ColorUtil.rainbow(5000).getRGB() : n6);
        drawLine(n3, n2, n3, n4, n5, b ? ColorUtil.rainbow(1000).getRGB() : n7);
        drawGradientLine(n, n2, n3, n2, n5, b ? ColorUtil.rainbow(5000).getRGB() : n6, b ? ColorUtil.rainbow(1000).getRGB() : n7);
        drawGradientLine(n, n4, n3, n4, n5, b ? ColorUtil.rainbow(5000).getRGB() : n6, b ? ColorUtil.rainbow(1000).getRGB() : n7);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawCircle(final float n, final float n2, final float n3, final float n4, final Color color) {
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n - RenderUtil.mc.getRenderManager().viewerPosX, n2 - RenderUtil.mc.getRenderManager().viewerPosY, n3 - RenderUtil.mc.getRenderManager().viewerPosZ, n + 1.0f - RenderUtil.mc.getRenderManager().viewerPosX, n2 + 1.0f - RenderUtil.mc.getRenderManager().viewerPosY, n3 + 1.0f - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            drawCircleVertices(axisAlignedBB, n4, color);
        }
    }
    
    public static void drawBox(final BlockPos blockPos, final Color color, final double n, final boolean b, final boolean b2, final int n2) {
        if (b) {
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n2);
            drawFadingBox(blockPos, b2 ? color2 : color, b2 ? color : color2, n);
            return;
        }
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, blockPos.getX() + 1 - RenderUtil.mc.getRenderManager().viewerPosX, blockPos.getY() + 1 - RenderUtil.mc.getRenderManager().viewerPosY + n, blockPos.getZ() + 1 - RenderUtil.mc.getRenderManager().viewerPosZ);
        RenderUtil.camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (RenderUtil.camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            RenderGlobal.renderFilledBox(axisAlignedBB, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glDisable(2848);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }
    
    public static void drawHGradientRect(final float n, final float n2, final float n3, final float n4, final int n5, final int n6) {
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final float n10 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n13 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n10).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n11, n12, n13, n10).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n11, n12, n13, n10).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawPolygonPart(final double n, final double n2, final int n3, final int n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos(n, n2, 0.0).color(n8, n9, n10, n7).endVertex();
        int n15 = n4 * 90;
        if (n15 <= n4 * 90 + 90) {
            final double n16 = 6.283185307179586 * n15 / 360.0 + Math.toRadians(180.0);
            getBuffer.pos(n + Math.sin(n16) * n3, n2 + Math.cos(n16) * n3, 0.0).color(n12, n13, n14, n11).endVertex();
            ++n15;
            return;
        }
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawText(final String s, final double n, final double n2, final double n3, final Color color) {
        final Entity getRenderViewEntity = RenderUtil.mc.getRenderViewEntity();
        if (getRenderViewEntity == null) {
            return;
        }
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = getRenderViewEntity.prevPosX;
        getRenderViewEntity.posY = getRenderViewEntity.prevPosY;
        getRenderViewEntity.posZ = getRenderViewEntity.prevPosZ;
        final int n4 = Managers.TEXT.getMCStringWidth(s) / 2;
        final double n5 = 0.027999999999999997;
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n2 + 1.4f, (float)n3);
        GlStateManager.rotate(-RenderUtil.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(RenderUtil.mc.getRenderManager().playerViewX, (RenderUtil.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n5, -n5, n5);
        GlStateManager.disableDepth();
        Managers.TEXT.drawMCString(s, (float)(-n4), (float)(-(Managers.TEXT.getFontHeight() - 1)), ColorUtil.toRGBA(color), true);
        GlStateManager.enableDepth();
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public static void drawFadingSide(final BlockPos blockPos, final EnumFacing enumFacing, final Color color, final Color color2, final double n) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        final IBlockState state = BlockUtil.getState(blockPos);
        final Vec3d interpolatedPos = InterpolationUtil.getInterpolatedPos((Entity)RenderUtil.mc.player, RenderUtil.mc.getRenderPartialTicks(), false);
        final AxisAlignedBB expand = state.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).grow(0.0020000000949949026).offset(-interpolatedPos.x, -interpolatedPos.y, -interpolatedPos.z).expand(0.0, 0.0, 0.0);
        final float n2 = color.getRed() / 255.0f;
        final float n3 = color.getGreen() / 255.0f;
        final float n4 = color.getBlue() / 255.0f;
        final float n5 = color.getAlpha() / 255.0f;
        final float n6 = color2.getRed() / 255.0f;
        final float n7 = color2.getGreen() / 255.0f;
        final float n8 = color2.getBlue() / 255.0f;
        final float n9 = color2.getAlpha() / 255.0f;
        double n10 = 0.0;
        double n11 = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        double minY = 0.0;
        double n14 = 0.0;
        if (enumFacing == EnumFacing.DOWN) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            minY = expand.minY;
            n12 = expand.minZ;
            n14 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.UP) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.maxY + n;
            minY = expand.maxY + n;
            n12 = expand.minZ;
            n14 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.EAST) {
            n10 = expand.maxX;
            n13 = expand.maxX;
            n11 = expand.minY;
            minY = expand.maxY + n;
            n12 = expand.minZ;
            n14 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.WEST) {
            n10 = expand.minX;
            n13 = expand.minX;
            n11 = expand.minY;
            minY = expand.maxY + n;
            n12 = expand.minZ;
            n14 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.SOUTH) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            minY = expand.maxY + n;
            n12 = expand.maxZ;
            n14 = expand.maxZ;
        }
        else if (enumFacing == EnumFacing.NORTH) {
            n10 = expand.minX;
            n13 = expand.maxX;
            n11 = expand.minY;
            minY = expand.maxY + n;
            n12 = expand.minZ;
            n14 = expand.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
        getBuffer.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            buildPosColor(getBuffer, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, minY, n14);
        }
        else if (enumFacing == EnumFacing.UP) {
            buildPosColor(getBuffer, n6, n7, n8, n9, n10, n11, n12, n13, minY, n14);
        }
        else if (enumFacing == EnumFacing.DOWN) {
            buildPosColor(getBuffer, n2, n3, n4, n5, n10, n11, n12, n13, minY, n14);
        }
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    public static void drawArrowPointer(final float n, final float n2, final float n3, final float n4, final float n5, final boolean b, final float n6, final int n7) {
        final boolean glIsEnabled = GL11.glIsEnabled(3042);
        final float n8 = (n7 >> 24 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        glColor(n7);
        GL11.glBegin(7);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)(n - n3 / n4), (double)(n2 + n3));
        GL11.glVertex2d((double)n, (double)(n2 + n3 / n5));
        GL11.glVertex2d((double)(n + n3 / n4), (double)(n2 + n3));
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glEnd();
        if (b) {
            GL11.glLineWidth(n6);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, n8);
            GL11.glBegin(2);
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glVertex2d((double)(n - n3 / n4), (double)(n2 + n3));
            GL11.glVertex2d((double)n, (double)(n2 + n3 / n5));
            GL11.glVertex2d((double)(n + n3 / n4), (double)(n2 + n3));
            GL11.glVertex2d((double)n, (double)n2);
            GL11.glEnd();
        }
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        if (!glIsEnabled) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2848);
    }
}
