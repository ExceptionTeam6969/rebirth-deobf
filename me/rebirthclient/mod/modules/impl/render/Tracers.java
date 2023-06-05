//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.client.renderer.culling.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.events.impl.*;
import java.util.function.*;
import net.minecraft.entity.*;
import java.util.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.util.render.*;
import com.google.common.collect.*;

public class Tracers extends Module
{
    private final Setting radius;
    private final Setting size;
    private final Setting color;
    private final Setting range;
    private final Frustum frustum;
    private final Setting lineWidth;
    private final Setting outline;
    private final Setting mode;
    private final EntityListener entityListener;
    
    private boolean lambda$new$1(final Integer n) {
        return this.mode.getValue() == Mode.ARROW;
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.mode.getValue() == Mode.ARROW;
    }
    
    public Tracers() {
        super("Tracers", "Points to the players on your screen", Category.RENDER);
        this.mode = this.add(new Setting("Mode", Mode.ARROW));
        this.range = this.add(new Setting("Range", 100, 10, 200));
        this.color = this.add(new Setting("Color", new Color(11935519)));
        this.lineWidth = this.add(new Setting("LineWidth", 1.0f, 0.5f, 2.0f, this::lambda$new$0));
        this.radius = this.add(new Setting("Radius", 80, 10, 200, this::lambda$new$1));
        this.size = this.add(new Setting("Size", 7.5f, 5.0f, 25.0f, this::lambda$new$2));
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$3));
        this.entityListener = new EntityListener(null);
        this.frustum = new Frustum();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Tracers.mc.getRenderViewEntity() == null) {
            return;
        }
        if (this.mode.getValue() == Mode.TRACER) {
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth((float)this.lineWidth.getValue());
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glBegin(1);
            final Iterator<EntityPlayer> iterator = Tracers.mc.world.playerEntities.iterator();
            if (iterator.hasNext()) {
                final EntityPlayer entityPlayer = iterator.next();
                if (entityPlayer != Tracers.mc.player) {
                    this.drawTraces((Entity)entityPlayer);
                }
                return;
            }
            GL11.glEnd();
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.mode.getValue() == Mode.ARROW;
    }
    
    private void lambda$onRender2D$4(final Entity p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: instanceof      Lnet/minecraft/entity/player/EntityPlayer;
        //     4: ifeq            355
        //     7: aload_0        
        //     8: aload_1        
        //     9: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //    12: ifeq            355
        //    15: aload_1        
        //    16: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //    19: astore_2       
        //    20: aload_0        
        //    21: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.entityListener:Lme/rebirthclient/mod/modules/impl/render/Tracers$EntityListener;
        //    24: invokevirtual   me/rebirthclient/mod/modules/impl/render/Tracers$EntityListener.getEntityLowerBounds:()Ljava/util/Map;
        //    27: aload_2        
        //    28: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    33: checkcast       Lnet/minecraft/util/math/Vec3d;
        //    36: astore_3       
        //    37: aload_3        
        //    38: ifnull          355
        //    41: aload_0        
        //    42: aload_3        
        //    43: ifeq            355
        //    46: aload_0        
        //    47: aload_2        
        //    48: ifne            355
        //    51: ldc_w           255.0
        //    54: ldc_w           255.0
        //    57: aload_0        
        //    58: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //    61: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/Integer;
        //    67: invokevirtual   java/lang/Integer.intValue:()I
        //    70: i2f            
        //    71: fdiv           
        //    72: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //    75: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    78: aload_2        
        //    79: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //    82: fmul           
        //    83: fsub           
        //    84: ldc_w           100.0
        //    87: ldc_w           255.0
        //    90: invokestatic    net/minecraft/util/math/MathHelper.clamp:(FFF)F
        //    93: f2i            
        //    94: istore          4
        //    96: new             Ljava/awt/Color;
        //    99: dup            
        //   100: iconst_0       
        //   101: sipush          191
        //   104: sipush          255
        //   107: invokespecial   java/awt/Color.<init>:(III)V
        //   110: astore          5
        //   112: getstatic       me/rebirthclient/api/managers/Managers.FRIENDS:Lme/rebirthclient/api/managers/impl/FriendManager;
        //   115: aload_2        
        //   116: invokevirtual   net/minecraft/entity/player/EntityPlayer.getName:()Ljava/lang/String;
        //   119: invokevirtual   me/rebirthclient/api/managers/impl/FriendManager.isFriend:(Ljava/lang/String;)Z
        //   122: ifeq            135
        //   125: aload           5
        //   127: iload           4
        //   129: invokestatic    me/rebirthclient/api/util/render/ColorUtil.injectAlpha:(Ljava/awt/Color;I)Ljava/awt/Color;
        //   132: goto            150
        //   135: aload_0        
        //   136: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   139: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   142: checkcast       Ljava/awt/Color;
        //   145: iload           4
        //   147: invokestatic    me/rebirthclient/api/util/render/ColorUtil.injectAlpha:(Ljava/awt/Color;I)Ljava/awt/Color;
        //   150: astore          6
        //   152: invokestatic    org/lwjgl/opengl/Display.getWidth:()I
        //   155: iconst_2       
        //   156: idiv           
        //   157: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //   160: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   163: getfield        net/minecraft/client/settings/GameSettings.guiScale:I
        //   166: ifne            173
        //   169: iconst_1       
        //   170: goto            182
        //   173: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //   176: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   179: getfield        net/minecraft/client/settings/GameSettings.guiScale:I
        //   182: idiv           
        //   183: istore          7
        //   185: invokestatic    org/lwjgl/opengl/Display.getHeight:()I
        //   188: iconst_2       
        //   189: idiv           
        //   190: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //   193: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   196: getfield        net/minecraft/client/settings/GameSettings.guiScale:I
        //   199: ifne            206
        //   202: iconst_1       
        //   203: goto            215
        //   206: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //   209: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   212: getfield        net/minecraft/client/settings/GameSettings.guiScale:I
        //   215: idiv           
        //   216: istore          8
        //   218: aload_0        
        //   219: aload_2        
        //   220: invokespecial   me/rebirthclient/mod/modules/impl/render/Tracers.getRotations:(Lnet/minecraft/entity/EntityLivingBase;)F
        //   223: getstatic       me/rebirthclient/mod/modules/impl/render/Tracers.mc:Lnet/minecraft/client/Minecraft;
        //   226: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   229: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   232: fsub           
        //   233: fstore          9
        //   235: iload           7
        //   237: i2f            
        //   238: iload           8
        //   240: i2f            
        //   241: fconst_0       
        //   242: invokestatic    org/lwjgl/opengl/GL11.glTranslatef:(FFF)V
        //   245: fload           9
        //   247: fconst_0       
        //   248: fconst_0       
        //   249: fconst_1       
        //   250: invokestatic    org/lwjgl/opengl/GL11.glRotatef:(FFFF)V
        //   253: iload           7
        //   255: ineg           
        //   256: i2f            
        //   257: iload           8
        //   259: ineg           
        //   260: i2f            
        //   261: fconst_0       
        //   262: invokestatic    org/lwjgl/opengl/GL11.glTranslatef:(FFF)V
        //   265: iload           7
        //   267: i2f            
        //   268: iload           8
        //   270: aload_0        
        //   271: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.radius:Lme/rebirthclient/mod/modules/settings/Setting;
        //   274: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   277: checkcast       Ljava/lang/Integer;
        //   280: invokevirtual   java/lang/Integer.intValue:()I
        //   283: isub           
        //   284: i2f            
        //   285: aload_0        
        //   286: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.size:Lme/rebirthclient/mod/modules/settings/Setting;
        //   289: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   292: checkcast       Ljava/lang/Float;
        //   295: invokevirtual   java/lang/Float.floatValue:()F
        //   298: fconst_2       
        //   299: fconst_1       
        //   300: aload_0        
        //   301: getfield        me/rebirthclient/mod/modules/impl/render/Tracers.outline:Lme/rebirthclient/mod/modules/settings/Setting;
        //   304: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   307: checkcast       Ljava/lang/Boolean;
        //   310: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   313: ldc_w           1.1
        //   316: aload           6
        //   318: invokevirtual   java/awt/Color.getRGB:()I
        //   321: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawArrowPointer:(FFFFFZFI)V
        //   324: iload           7
        //   326: i2f            
        //   327: iload           8
        //   329: i2f            
        //   330: fconst_0       
        //   331: invokestatic    org/lwjgl/opengl/GL11.glTranslatef:(FFF)V
        //   334: fload           9
        //   336: fneg           
        //   337: fconst_0       
        //   338: fconst_0       
        //   339: fconst_1       
        //   340: invokestatic    org/lwjgl/opengl/GL11.glRotatef:(FFFF)V
        //   343: iload           7
        //   345: ineg           
        //   346: i2f            
        //   347: iload           8
        //   349: ineg           
        //   350: i2f            
        //   351: fconst_0       
        //   352: invokestatic    org/lwjgl/opengl/GL11.glTranslatef:(FFF)V
        //   355: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0355 (coming from #0012).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void drawTraces(final Entity entity) {
        if (Tracers.mc.getRenderViewEntity() == null) {
            return;
        }
        final double n = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * Tracers.mc.getRenderPartialTicks() - Tracers.mc.getRenderManager().viewerPosX;
        final double n2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * Tracers.mc.getRenderPartialTicks() - Tracers.mc.getRenderManager().viewerPosY;
        final double n3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * Tracers.mc.getRenderPartialTicks() - Tracers.mc.getRenderManager().viewerPosZ;
        final Vec3d rotateYaw = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Tracers.mc.getRenderViewEntity().rotationPitch)).rotateYaw(-(float)Math.toRadians(Tracers.mc.getRenderViewEntity().rotationYaw));
        final boolean friend = Managers.FRIENDS.isFriend(entity.getName());
        GL11.glColor4f((friend ? 0.0f : ((float)((Color)this.color.getValue()).getRed())) / 255.0f, (friend ? 191.0f : ((float)((Color)this.color.getValue()).getGreen())) / 255.0f, (friend ? 255.0f : ((float)((Color)this.color.getValue()).getBlue())) / 255.0f, MathHelper.clamp(255.0f - 255.0f / (int)this.range.getValue() * Tracers.mc.player.getDistance(entity), 100.0f, 255.0f));
        GL11.glVertex3d(rotateYaw.x, rotateYaw.y + Tracers.mc.getRenderViewEntity().getEyeHeight(), rotateYaw.z);
        GL11.glVertex3d(n, n2, n3);
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.mode.getValue() == Mode.TRACER;
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (this.mode.getValue() == Mode.ARROW) {
            EntityListener.access$100(this.entityListener);
            Tracers.mc.world.loadedEntityList.forEach(this::lambda$onRender2D$4);
        }
    }
    
    private float getRotations(final EntityLivingBase entityLivingBase) {
        return (float)(-(Math.atan2(entityLivingBase.posX - Tracers.mc.player.posX, entityLivingBase.posZ - Tracers.mc.player.posZ) * 57.29577951308232));
    }
    
    @Override
    public String getInfo() {
        return String.valueOf(this.mode.getValue());
    }
    
    private static class EntityListener
    {
        private final Map entityLowerBounds;
        private final Map entityUpperBounds;
        
        private Vec3d getEntityRenderPosition(final Entity entity) {
            final double n = Wrapper.mc.timer.renderPartialTicks;
            return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n - Wrapper.mc.getRenderManager().viewerPosX, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n - Wrapper.mc.getRenderManager().viewerPosY, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n - Wrapper.mc.getRenderManager().viewerPosZ);
        }
        
        private void render() {
            if (!this.entityUpperBounds.isEmpty()) {
                this.entityUpperBounds.clear();
            }
            if (!this.entityLowerBounds.isEmpty()) {
                this.entityLowerBounds.clear();
            }
            for (final Entity entity : Wrapper.mc.world.loadedEntityList) {
                final Vec3d entityRenderPosition = this.getEntityRenderPosition(entity);
                entityRenderPosition.add(new Vec3d(0.0, entity.height + 0.2, 0.0));
                final Vec3d get2DPos = RenderUtil.get2DPos(entityRenderPosition.x, entityRenderPosition.y, entityRenderPosition.z);
                final Vec3d get2DPos2 = RenderUtil.get2DPos(entityRenderPosition.x, entityRenderPosition.y - 2.0, entityRenderPosition.z);
                if (get2DPos != null) {
                    if (get2DPos2 == null) {
                        return;
                    }
                    this.entityUpperBounds.put(entity, get2DPos);
                    this.entityLowerBounds.put(entity, get2DPos2);
                }
            }
        }
        
        public Map getEntityLowerBounds() {
            return this.entityLowerBounds;
        }
        
        private EntityListener() {
            this.entityUpperBounds = Maps.newHashMap();
            this.entityLowerBounds = Maps.newHashMap();
        }
        
        EntityListener(final Tracers$1 object) {
            this();
        }
        
        static void access$100(final EntityListener entityListener) {
            entityListener.render();
        }
    }
    
    private enum Mode
    {
        TRACER("TRACER", 0);
        
        private static final Mode[] $VALUES;
        
        ARROW("ARROW", 1);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.TRACER, Mode.ARROW };
        }
    }
}
