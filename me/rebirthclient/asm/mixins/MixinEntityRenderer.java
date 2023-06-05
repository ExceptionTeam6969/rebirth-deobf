//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.lwjgl.util.glu.*;
import me.rebirthclient.mod.modules.impl.render.*;
import me.rebirthclient.api.util.math.*;
import me.rebirthclient.api.util.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;
import java.util.*;

@Mixin({ EntityRenderer.class })
public class MixinEntityRenderer
{
    final Minecraft mc;
    @Shadow
    private ItemStack itemActivationItem;
    @Shadow
    @Final
    private int[] lightmapColors;
    
    public MixinEntityRenderer() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List getEntitiesInAABBexcluding(final WorldClient worldClient, final Entity entity, final AxisAlignedBB axisAlignedBB, final Predicate predicate) {
        final NoHitBox instance = NoHitBox.INSTANCE;
        if (instance.isOn() && ((this.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && (boolean)instance.pickaxe.getValue()) || (this.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL && (boolean)instance.crystal.getValue()) || (this.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE && (boolean)instance.gapple.getValue()) || (this.mc.player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN) && (boolean)instance.obby.getValue()) || this.mc.player.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL || this.mc.player.getHeldItemMainhand().getItem() == Items.TNT_MINECART)) {
            return new ArrayList();
        }
        return worldClient.getEntitiesInAABBexcluding(entity, axisAlignedBB, predicate);
    }
    
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffect(final float n, final CallbackInfo callbackInfo) {
        if (NoRender.INSTANCE.isOn() && (boolean)NoRender.INSTANCE.hurtCam.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderItemActivation" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemActivationHook(final CallbackInfo callbackInfo) {
        if (this.itemActivationItem != null && NoRender.INSTANCE.isOn() && (boolean)NoRender.INSTANCE.totemPops.getValue() && this.itemActivationItem.getItem() == Items.TOTEM_OF_UNDYING) {
            callbackInfo.cancel();
        }
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 3, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double changeCameraDistanceHook(final double n) {
        if (CameraClip.INSTANCE.isOn()) {
            return (double)CameraClip.INSTANCE.distance.getValue();
        }
        return n;
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 7, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double orientCameraHook(final double n) {
        if (CameraClip.INSTANCE.isOn()) {
            return (double)CameraClip.INSTANCE.distance.getValue();
        }
        return n;
    }
    
    @Redirect(method = { "setupCameraTransform" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onSetupCameraTransform(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAngle(), n3, n4);
    }
    
    @Redirect(method = { "renderWorldPass" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderWorldPass(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAngle(), n3, n4);
    }
    
    @Redirect(method = { "renderCloudsCheck" }, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderCloudsCheck(final float n, final float n2, final float n3, final float n4) {
        final PerspectiveEvent perspectiveEvent = new PerspectiveEvent(this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective(n, perspectiveEvent.getAngle(), n3, n4);
    }
    
    @Inject(method = { "updateLightmap" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/DynamicTexture;updateDynamicTexture()V", shift = At.Shift.BEFORE) })
    public void updateTextureHook(final float n, final CallbackInfo callbackInfo) {
        if (Ambience.INSTANCE.isOn() && Ambience.INSTANCE.lightMap.booleanValue) {
            for (int i = 0; i < this.lightmapColors.length; ++i) {
                final Color color = Ambience.INSTANCE.getColor();
                final float n2 = color.getAlpha() / 255.0f;
                final int[] rgbaArray = MathUtil.toRGBAArray(this.lightmapColors[i]);
                final Vector3f mix = MathUtil.mix(new Vector3f(rgbaArray[2] / 255.0f, rgbaArray[1] / 255.0f, rgbaArray[0] / 255.0f), new Vector3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f), n2);
                this.lightmapColors[i] = (0xFF000000 | (int)(mix.x * 255.0f) << 16 | (int)(mix.y * 255.0f) << 8 | (int)(mix.z * 255.0f));
            }
        }
    }
    
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getRenderViewEntity()Lnet/minecraft/entity/Entity;"))
    private Entity redirectMouseOver(final Minecraft minecraft) {
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        if (freecamEvent.isCanceled() && Keyboard.isKeyDown(56)) {
            return (Entity)minecraft.player;
        }
        return minecraft.getRenderViewEntity();
    }
    
    @Redirect(method = { "updateCameraAndRender" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;turn(FF)V"))
    private void redirectTurn(final EntityPlayerSP entityPlayerSP, final float n, final float n2) {
        try {
            final Minecraft getMinecraft = Minecraft.getMinecraft();
            final FreecamEvent freecamEvent = new FreecamEvent();
            MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
            if (freecamEvent.isCanceled()) {
                if (Keyboard.isKeyDown(56)) {
                    getMinecraft.player.turn(n, n2);
                }
                else {
                    Objects.requireNonNull(getMinecraft.getRenderViewEntity(), "Render Entity").turn(n, n2);
                }
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        entityPlayerSP.turn(n, n2);
    }
    
    @Redirect(method = { "renderWorldPass" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isSpectator()Z"))
    public boolean redirectIsSpectator(final EntityPlayerSP entityPlayerSP) {
        final FreecamEvent freecamEvent = new FreecamEvent();
        MinecraftForge.EVENT_BUS.post((Event)freecamEvent);
        return freecamEvent.isCanceled() || (entityPlayerSP != null && entityPlayerSP.isSpectator());
    }
}
