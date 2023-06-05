//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.entity;

import me.rebirthclient.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.model.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public class StaticModelPlayer extends ModelPlayer implements Wrapper
{
    private float limbSwingAmount;
    private float yawHead;
    private float yaw;
    private final EntityPlayer player;
    private float limbSwing;
    private float pitch;
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setLimbSwingAmount(final float limbSwingAmount) {
        this.limbSwingAmount = limbSwingAmount;
    }
    
    public void render(final float n) {
        this.render((Entity)this.player, this.limbSwing, this.limbSwingAmount, (float)this.player.ticksExisted, this.yawHead, this.pitch, n);
    }
    
    private static ModelBiped.ArmPose getArmPose(final EntityPlayer entityPlayer, final ItemStack itemStack) {
        if (itemStack.isEmpty()) {
            return ModelBiped.ArmPose.EMPTY;
        }
        if (itemStack.getItem() instanceof ItemBow && entityPlayer.getItemInUseCount() > 0) {
            return ModelBiped.ArmPose.BOW_AND_ARROW;
        }
        return ModelBiped.ArmPose.ITEM;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setLimbSwing(final float limbSwing) {
        this.limbSwing = limbSwing;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    
    public float getYawHead() {
        return this.yawHead;
    }
    
    public void disableArmorLayers() {
        this.bipedBodyWear.showModel = false;
        this.bipedLeftLegwear.showModel = false;
        this.bipedRightLegwear.showModel = false;
        this.bipedLeftArmwear.showModel = false;
        this.bipedRightArmwear.showModel = false;
        this.bipedHeadwear.showModel = true;
        this.bipedHead.showModel = false;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
    
    public StaticModelPlayer(final EntityPlayer player, final boolean b, final float n) {
        super(n, b);
        this.player = player;
        this.limbSwing = this.player.limbSwing;
        this.limbSwingAmount = this.player.limbSwingAmount;
        this.yaw = this.player.rotationYaw;
        this.yawHead = this.player.rotationYawHead;
        this.pitch = this.player.rotationPitch;
        this.isSneak = this.player.isSneaking();
        this.rightArmPose = getArmPose(this.player, (this.player.getPrimaryHand() == EnumHandSide.RIGHT) ? this.player.getHeldItemMainhand() : this.player.getHeldItemOffhand());
        this.leftArmPose = getArmPose(this.player, (this.player.getPrimaryHand() == EnumHandSide.RIGHT) ? this.player.getHeldItemOffhand() : this.player.getHeldItemMainhand());
        this.swingProgress = this.player.swingProgress;
        this.setLivingAnimations((EntityLivingBase)this.player, this.limbSwing, this.limbSwingAmount, StaticModelPlayer.mc.getRenderPartialTicks());
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public void setYawHead(final float yawHead) {
        this.yawHead = yawHead;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
}
