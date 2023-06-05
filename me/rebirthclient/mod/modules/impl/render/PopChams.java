//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.client.entity.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import net.minecraft.client.model.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.common.*;

public class PopChams extends Module
{
    private final Setting fillColor;
    public static Setting self;
    double alphaFill;
    ModelPlayer playerModel;
    EntityOtherPlayerMP player;
    private final Setting outlineColor;
    public static Setting onlyOneEsp;
    public static PopChams INSTANCE;
    double alphaLine;
    public static Setting fadetime;
    public static Setting fadestart;
    public static Setting elevatorMode;
    public static Setting elevator;
    Long startTime;
    
    public static float handleRotationFloat() {
        return 0.0f;
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    private static boolean lambda$new$0(final Object o) {
        return PopChams.elevator.isOpen();
    }
    
    public static float prepareScale(final EntityLivingBase entityLivingBase, final float n) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        GlStateManager.scale(n + (entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX), (double)(n * entityLivingBase.height), n + (entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ));
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        return 0.0625f;
    }
    
    public static void renderLivingAt(final double n, final double n2, final double n3) {
        GlStateManager.translate(n, n2, n3);
    }
    
    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(1.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    static Setting access$000(final PopChams popChams) {
        return popChams.outlineColor;
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (EarthPopChams.INSTANCE.isOn()) {
            this.disable();
            return;
        }
        if (PopChams.onlyOneEsp.getValue()) {
            if (this.player == null || PopChams.mc.world == null || PopChams.mc.player == null) {
                return;
            }
            if (PopChams.elevator.getValue()) {
                if (PopChams.elevatorMode.getValue() == ElevatorMode.UP) {
                    final EntityOtherPlayerMP player = this.player;
                    player.posY += 0.05f * renderWorldLastEvent.getPartialTicks();
                }
                else if (PopChams.elevatorMode.getValue() == ElevatorMode.DOWN) {
                    final EntityOtherPlayerMP player2 = this.player;
                    player2.posY -= 0.05f * renderWorldLastEvent.getPartialTicks();
                }
            }
            GL11.glLineWidth(1.0f);
            final Color color = (Color)this.outlineColor.getValue();
            final Color color2 = (Color)this.fillColor.getValue();
            int alpha = color.getAlpha();
            int alpha2 = color2.getAlpha();
            final long n = System.currentTimeMillis() - this.startTime - ((Number)PopChams.fadestart.getValue()).longValue();
            if (System.currentTimeMillis() - this.startTime > ((Number)PopChams.fadestart.getValue()).longValue()) {
                final double n2 = -MathHelper.clamp(this.normalize((double)n, ((Number)PopChams.fadetime.getValue()).doubleValue()), 0.0, 1.0) + 1.0;
                alpha *= (int)n2;
                alpha2 *= (int)n2;
            }
            final Color alpha3 = newAlpha(color, alpha);
            final Color alpha4 = newAlpha(color2, alpha2);
            if (this.player != null && this.playerModel != null) {
                prepareGL();
                GL11.glPushAttrib(1048575);
                GL11.glEnable(2881);
                GL11.glEnable(2848);
                if (this.alphaFill > 1.0) {
                    this.alphaFill -= (float)PopChams.fadetime.getValue();
                }
                final Color color3 = new Color(alpha4.getRed(), alpha4.getGreen(), alpha4.getBlue(), (int)this.alphaFill);
                if (this.alphaLine > 1.0) {
                    this.alphaLine -= (float)PopChams.fadetime.getValue();
                }
                final Color color4 = new Color(alpha3.getRed(), alpha3.getGreen(), alpha3.getBlue(), (int)this.alphaLine);
                glColor(color3);
                GL11.glPolygonMode(1032, 6914);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, 1);
                glColor(color4);
                GL11.glPolygonMode(1032, 6913);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, 1);
                GL11.glPolygonMode(1032, 6914);
                GL11.glPopAttrib();
                releaseGL();
            }
        }
    }
    
    public PopChams() {
        super("PopChams", "Pop rendering", Category.RENDER);
        this.outlineColor = this.add(new Setting("Outline Color", new Color(255, 255, 255, 100)));
        this.fillColor = this.add(new Setting("Fill Color", new Color(255, 255, 255, 100)));
        PopChams.INSTANCE = this;
        PopChams.self = this.add(new Setting("Self", true));
        PopChams.elevator = this.add(new Setting("Travel", true).setParent());
        PopChams.elevatorMode = this.add(new Setting("Elevator", ElevatorMode.UP, PopChams::lambda$new$0));
        PopChams.fadestart = this.add(new Setting("Fade Start", 0, 0, 255));
        PopChams.fadetime = this.add(new Setting("Fade Time", 0.5f, 0.0f, 2.0f));
        PopChams.onlyOneEsp = this.add(new Setting("Only Render One", true));
    }
    
    public static Color newAlpha(final Color color, final int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled() || fullNullCheck()) {
            return;
        }
        if (EarthPopChams.INSTANCE.isOn()) {
            this.disable();
            return;
        }
        final SPacketEntityStatus sPacketEntityStatus;
        if (receive.getPacket() instanceof SPacketEntityStatus && (sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket()).getOpCode() == 35) {
            sPacketEntityStatus.getEntity((World)PopChams.mc.world);
            if ((boolean)PopChams.self.getValue() || sPacketEntityStatus.getEntity((World)PopChams.mc.world).getEntityId() != PopChams.mc.player.getEntityId()) {
                (this.player = new EntityOtherPlayerMP((World)PopChams.mc.world, new GameProfile(PopChams.mc.player.getUniqueID(), ""))).copyLocationAndAnglesFrom(sPacketEntityStatus.getEntity((World)PopChams.mc.world));
                this.playerModel = new ModelPlayer(0.0f, false);
                this.startTime = System.currentTimeMillis();
                this.playerModel.bipedHead.showModel = false;
                this.playerModel.bipedBody.showModel = false;
                this.playerModel.bipedLeftArmwear.showModel = false;
                this.playerModel.bipedLeftLegwear.showModel = false;
                this.playerModel.bipedRightArmwear.showModel = false;
                this.playerModel.bipedRightLegwear.showModel = false;
                this.alphaFill = ((Color)this.fillColor.getValue()).getAlpha();
                this.alphaLine = ((Color)this.outlineColor.getValue()).getAlpha();
                if (!(boolean)PopChams.onlyOneEsp.getValue()) {
                    new TotemPopChams(this.player, this.playerModel, this.startTime, this.alphaFill);
                }
            }
        }
    }
    
    static Setting access$100(final PopChams popChams) {
        return popChams.fillColor;
    }
    
    double normalize(final double n, final double n2) {
        return (n - 0.0) / (n2 - 0.0);
    }
    
    public static void renderEntity(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float n, final float n2, final int n3) {
        final float getRenderPartialTicks = PopChams.mc.getRenderPartialTicks();
        final double n4 = entityLivingBase.posX - PopChams.mc.getRenderManager().viewerPosX;
        double n5 = entityLivingBase.posY - PopChams.mc.getRenderManager().viewerPosY;
        final double n6 = entityLivingBase.posZ - PopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            n5 -= 0.125;
        }
        renderLivingAt(n4, n5, n6);
        final float handleRotationFloat = handleRotationFloat();
        prepareRotations(entityLivingBase);
        final float prepareScale = prepareScale(entityLivingBase, (float)n3);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, n, n2, getRenderPartialTicks);
        modelBase.setRotationAngles(n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale);
        GlStateManager.popMatrix();
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    public static void prepareRotations(final EntityLivingBase entityLivingBase) {
        GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
    }
    
    public enum ElevatorMode
    {
        private static final ElevatorMode[] $VALUES;
        
        DOWN("DOWN", 1), 
        UP("UP", 0);
        
        static {
            $VALUES = new ElevatorMode[] { ElevatorMode.UP, ElevatorMode.DOWN };
        }
        
        private ElevatorMode(final String s, final int n) {
        }
    }
    
    public static class TotemPopChams
    {
        double alphaLine;
        final EntityOtherPlayerMP player;
        double alphaFill;
        final Long startTime;
        final ModelPlayer playerModel;
        
        public static float prepareScale(final EntityLivingBase entityLivingBase, final float n) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0f, -1.0f, 1.0f);
            GlStateManager.scale(n + (entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX), (double)(n * entityLivingBase.height), n + (entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ));
            GlStateManager.translate(0.0f, -1.501f, 0.0f);
            return 0.0625f;
        }
        
        public static void glColor(final Color color) {
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        }
        
        public static void renderLivingAt(final double n, final double n2, final double n3) {
            GlStateManager.translate((float)n, (float)n2, (float)n3);
        }
        
        public static float handleRotationFloat() {
            return 0.0f;
        }
        
        public static void prepareRotations(final EntityLivingBase entityLivingBase) {
            GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
        }
        
        double normalize(final double n, final double n2) {
            return (n - 0.0) / (n2 - 0.0);
        }
        
        public static Color newAlpha(final Color color, final int n) {
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
        }
        
        @SubscribeEvent
        public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
            if (this.player == null || Wrapper.mc.world == null || Wrapper.mc.player == null) {
                return;
            }
            GL11.glLineWidth(1.0f);
            final Color color = (Color)PopChams.access$000(PopChams.INSTANCE).getValue();
            final Color color2 = (Color)PopChams.access$100(PopChams.INSTANCE).getValue();
            int alpha = color.getAlpha();
            int alpha2 = color2.getAlpha();
            final long n = System.currentTimeMillis() - this.startTime - (long)PopChams.fadestart.getValue();
            if (System.currentTimeMillis() - this.startTime > (long)PopChams.fadestart.getValue()) {
                final double n2 = -MathHelper.clamp(this.normalize((double)n, (double)PopChams.fadetime.getValue()), 0.0, 1.0) + 1.0;
                alpha *= (int)n2;
                alpha2 *= (int)n2;
            }
            final Color alpha3 = newAlpha(color, alpha);
            final Color alpha4 = newAlpha(color2, alpha2);
            if (this.playerModel != null) {
                PopChams.prepareGL();
                GL11.glPushAttrib(1048575);
                GL11.glEnable(2881);
                GL11.glEnable(2848);
                if (this.alphaFill > 1.0) {
                    this.alphaFill -= (float)PopChams.fadetime.getValue();
                }
                final Color color3 = new Color(alpha4.getRed(), alpha4.getGreen(), alpha4.getBlue(), (int)this.alphaFill);
                if (this.alphaLine > 1.0) {
                    this.alphaLine -= (float)PopChams.fadetime.getValue();
                }
                final Color color4 = new Color(alpha3.getRed(), alpha3.getGreen(), alpha3.getBlue(), (int)this.alphaLine);
                glColor(color3);
                GL11.glPolygonMode(1032, 6914);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, 1.0f);
                glColor(color4);
                GL11.glPolygonMode(1032, 6913);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, 1.0f);
                GL11.glPolygonMode(1032, 6914);
                GL11.glPopAttrib();
                PopChams.releaseGL();
            }
        }
        
        public TotemPopChams(final EntityOtherPlayerMP player, final ModelPlayer playerModel, final Long startTime, final double n) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            this.player = player;
            this.playerModel = playerModel;
            this.startTime = startTime;
            this.alphaFill = n;
            this.alphaLine = n;
        }
        
        public static void renderEntity(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float n, final float n2, final float n3) {
            final float getRenderPartialTicks = Wrapper.mc.getRenderPartialTicks();
            final double n4 = entityLivingBase.posX - Wrapper.mc.getRenderManager().viewerPosX;
            double n5 = entityLivingBase.posY - Wrapper.mc.getRenderManager().viewerPosY;
            final double n6 = entityLivingBase.posZ - Wrapper.mc.getRenderManager().viewerPosZ;
            GlStateManager.pushMatrix();
            if (entityLivingBase.isSneaking()) {
                n5 -= 0.125;
            }
            renderLivingAt(n4, n5, n6);
            final float handleRotationFloat = handleRotationFloat();
            prepareRotations(entityLivingBase);
            final float prepareScale = prepareScale(entityLivingBase, n3);
            GlStateManager.enableAlpha();
            modelBase.setLivingAnimations(entityLivingBase, n, n2, getRenderPartialTicks);
            modelBase.setRotationAngles(n, n2, handleRotationFloat, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, prepareScale, (Entity)entityLivingBase);
            modelBase.render((Entity)entityLivingBase, n, n2, handleRotationFloat, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, prepareScale);
            GlStateManager.popMatrix();
        }
    }
}
