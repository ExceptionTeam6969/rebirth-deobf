//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.impl.render.*;
import org.spongepowered.asm.mixin.injection.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

@Mixin({ ModelPlayer.class })
public class MixinModelPlayer extends ModelBiped
{
    @Shadow
    public ModelRenderer bipedLeftArmwear;
    @Shadow
    public ModelRenderer bipedRightArmwear;
    @Shadow
    public ModelRenderer bipedLeftLegwear;
    @Shadow
    public ModelRenderer bipedRightLegwear;
    @Shadow
    public ModelRenderer bipedBodyWear;
    public ModelRenderer left_leg;
    public ModelRenderer right_leg;
    public ModelRenderer body;
    public ModelRenderer eye;
    public ModelRenderer rabbitBone;
    public ModelRenderer rabbitRleg;
    public ModelRenderer rabbitLarm;
    public ModelRenderer rabbitRarm;
    public ModelRenderer rabbitLleg;
    public ModelRenderer rabbitHead;
    public ModelRenderer fredhead;
    public ModelRenderer armLeft;
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer armRight;
    public ModelRenderer fredbody;
    public ModelRenderer armLeftpad2;
    public ModelRenderer torso;
    public ModelRenderer earRightpad_1;
    public ModelRenderer armRightpad2;
    public ModelRenderer legLeftpad;
    public ModelRenderer hat;
    public ModelRenderer legLeftpad2;
    public ModelRenderer armRight2;
    public ModelRenderer legRight2;
    public ModelRenderer earRightpad;
    public ModelRenderer armLeft2;
    public ModelRenderer frednose;
    public ModelRenderer earLeft;
    public ModelRenderer footRight;
    public ModelRenderer legRightpad2;
    public ModelRenderer legRightpad;
    public ModelRenderer armLeftpad;
    public ModelRenderer legLeft2;
    public ModelRenderer footLeft;
    public ModelRenderer hat2;
    public ModelRenderer armRightpad;
    public ModelRenderer earRight;
    public ModelRenderer crotch;
    public ModelRenderer jaw;
    public ModelRenderer handRight;
    public ModelRenderer handLeft;
    
    public MixinModelPlayer(final float n, final boolean b) {
        super(n, 0.0f, 64, 64);
        (this.body = new ModelRenderer((ModelBase)this)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureOffset(34, 8).addBox(-4.0f, 6.0f, -3.0f, 8, 12, 6);
        this.body.setTextureOffset(15, 10).addBox(-3.0f, 9.0f, 3.0f, 6, 8, 3);
        this.body.setTextureOffset(26, 0).addBox(-3.0f, 5.0f, -3.0f, 6, 1, 6);
        this.eye = new ModelRenderer((ModelBase)this);
        this.eye.setTextureOffset(0, 10).addBox(-3.0f, 7.0f, -4.0f, 6, 4, 1);
        (this.left_leg = new ModelRenderer((ModelBase)this)).setRotationPoint(-2.0f, 18.0f, 0.0f);
        this.left_leg.setTextureOffset(0, 0).addBox(2.9f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        (this.right_leg = new ModelRenderer((ModelBase)this)).setRotationPoint(2.0f, 18.0f, 0.0f);
        this.right_leg.setTextureOffset(13, 0).addBox(-5.9f, 0.0f, -1.5f, 3, 6, 3);
        (this.footRight = new ModelRenderer((ModelBase)this, 22, 39)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.footRight.addBox(-2.5f, 0.0f, -6.0f, 5, 3, 8, 0.0f);
        this.setRotationAngle(this.footRight, -0.034906585f, 0.0f, 0.0f);
        (this.earRight = new ModelRenderer((ModelBase)this, 8, 0)).setRotationPoint(-4.5f, -5.5f, 0.0f);
        this.earRight.addBox(-1.0f, -3.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotationAngle(this.earRight, 0.05235988f, 0.0f, -1.0471976f);
        (this.legLeftpad = new ModelRenderer((ModelBase)this, 48, 39)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legLeftpad.addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6, 0.0f);
        (this.earRightpad_1 = new ModelRenderer((ModelBase)this, 40, 39)).setRotationPoint(0.0f, -1.0f, 0.0f);
        this.earRightpad_1.addBox(-2.0f, -5.0f, -1.0f, 4, 4, 2, 0.0f);
        (this.legLeft = new ModelRenderer((ModelBase)this, 54, 10)).setRotationPoint(3.3f, 12.5f, 0.0f);
        this.legLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        (this.armRightpad2 = new ModelRenderer((ModelBase)this, 0, 26)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armRightpad2.addBox(-2.5f, 0.0f, -2.5f, 5, 7, 5, 0.0f);
        (this.handLeft = new ModelRenderer((ModelBase)this, 58, 56)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.handLeft.addBox(-2.0f, 0.0f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotationAngle(this.handLeft, 0.0f, 0.0f, 0.05235988f);
        (this.armLeft = new ModelRenderer((ModelBase)this, 62, 10)).setRotationPoint(6.5f, -8.0f, 0.0f);
        this.armLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        this.setRotationAngle(this.armLeft, 0.0f, 0.0f, -0.2617994f);
        (this.legRight = new ModelRenderer((ModelBase)this, 90, 8)).setRotationPoint(-3.3f, 12.5f, 0.0f);
        this.legRight.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        (this.armLeft2 = new ModelRenderer((ModelBase)this, 90, 48)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.armLeft2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.armLeft2, -0.17453292f, 0.0f, 0.0f);
        (this.legRight2 = new ModelRenderer((ModelBase)this, 20, 35)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.legRight2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.legRight2, 0.034906585f, 0.0f, 0.0f);
        (this.armLeftpad2 = new ModelRenderer((ModelBase)this, 0, 58)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armLeftpad2.addBox(-2.5f, 0.0f, -2.5f, 5, 7, 5, 0.0f);
        (this.legLeft2 = new ModelRenderer((ModelBase)this, 72, 48)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.legLeft2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.legLeft2, 0.034906585f, 0.0f, 0.0f);
        (this.hat = new ModelRenderer((ModelBase)this, 70, 24)).setRotationPoint(0.0f, -8.4f, 0.0f);
        this.hat.addBox(-3.0f, -0.5f, -3.0f, 6, 1, 6, 0.0f);
        this.setRotationAngle(this.hat, -0.017453292f, 0.0f, 0.0f);
        (this.earRightpad = new ModelRenderer((ModelBase)this, 85, 0)).setRotationPoint(0.0f, -1.0f, 0.0f);
        this.earRightpad.addBox(-2.0f, -5.0f, -1.0f, 4, 4, 2, 0.0f);
        (this.crotch = new ModelRenderer((ModelBase)this, 56, 0)).setRotationPoint(0.0f, 9.5f, 0.0f);
        this.crotch.addBox(-5.5f, 0.0f, -3.5f, 11, 3, 7, 0.0f);
        (this.torso = new ModelRenderer((ModelBase)this, 8, 0)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.torso.addBox(-6.0f, -9.0f, -4.0f, 12, 18, 8, 0.0f);
        this.setRotationAngle(this.torso, 0.017453292f, 0.0f, 0.0f);
        (this.armRight2 = new ModelRenderer((ModelBase)this, 90, 20)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.armRight2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.armRight2, -0.17453292f, 0.0f, 0.0f);
        (this.handRight = new ModelRenderer((ModelBase)this, 20, 26)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.handRight.addBox(-2.0f, 0.0f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotationAngle(this.handRight, 0.0f, 0.0f, -0.05235988f);
        (this.fredbody = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(0.0f, -9.0f, 0.0f);
        this.fredbody.addBox(-1.0f, -14.0f, -1.0f, 2, 24, 2, 0.0f);
        (this.fredhead = new ModelRenderer((ModelBase)this, 39, 22)).setRotationPoint(0.0f, -13.0f, -0.5f);
        this.fredhead.addBox(-5.5f, -8.0f, -4.5f, 11, 8, 9, 0.0f);
        (this.legRightpad = new ModelRenderer((ModelBase)this, 73, 33)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legRightpad.addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6, 0.0f);
        (this.frednose = new ModelRenderer((ModelBase)this, 17, 67)).setRotationPoint(0.0f, -2.0f, -4.5f);
        this.frednose.addBox(-4.0f, -2.0f, -3.0f, 8, 4, 3, 0.0f);
        (this.legLeftpad2 = new ModelRenderer((ModelBase)this, 16, 50)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legLeftpad2.addBox(-2.5f, 0.0f, -3.0f, 5, 7, 6, 0.0f);
        (this.armRightpad = new ModelRenderer((ModelBase)this, 70, 10)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armRightpad.addBox(-2.5f, 0.0f, -2.5f, 5, 9, 5, 0.0f);
        (this.armLeftpad = new ModelRenderer((ModelBase)this, 38, 54)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armLeftpad.addBox(-2.5f, 0.0f, -2.5f, 5, 9, 5, 0.0f);
        (this.hat2 = new ModelRenderer((ModelBase)this, 78, 61)).setRotationPoint(0.0f, 0.1f, 0.0f);
        this.hat2.addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotationAngle(this.hat2, -0.017453292f, 0.0f, 0.0f);
        (this.legRightpad2 = new ModelRenderer((ModelBase)this, 0, 39)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legRightpad2.addBox(-2.5f, 0.0f, -3.0f, 5, 7, 6, 0.0f);
        (this.jaw = new ModelRenderer((ModelBase)this, 49, 65)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.jaw.addBox(-5.0f, 0.0f, -4.5f, 10, 3, 9, 0.0f);
        this.setRotationAngle(this.jaw, 0.08726646f, 0.0f, 0.0f);
        (this.armRight = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(-6.5f, -8.0f, 0.0f);
        this.armRight.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        this.setRotationAngle(this.armRight, 0.0f, 0.0f, 0.2617994f);
        (this.footLeft = new ModelRenderer((ModelBase)this, 72, 50)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.footLeft.addBox(-2.5f, 0.0f, -6.0f, 5, 3, 8, 0.0f);
        this.setRotationAngle(this.footLeft, -0.034906585f, 0.0f, 0.0f);
        (this.earLeft = new ModelRenderer((ModelBase)this, 40, 0)).setRotationPoint(4.5f, -5.5f, 0.0f);
        this.earLeft.addBox(-1.0f, -3.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotationAngle(this.earLeft, 0.05235988f, 0.0f, 1.0471976f);
        this.legRight2.addChild(this.footRight);
        this.fredhead.addChild(this.earRight);
        this.legLeft.addChild(this.legLeftpad);
        this.earLeft.addChild(this.earRightpad_1);
        this.fredbody.addChild(this.legLeft);
        this.armRight2.addChild(this.armRightpad2);
        this.armLeft2.addChild(this.handLeft);
        this.fredbody.addChild(this.armLeft);
        this.fredbody.addChild(this.legRight);
        this.armLeft.addChild(this.armLeft2);
        this.legRight.addChild(this.legRight2);
        this.armLeft2.addChild(this.armLeftpad2);
        this.legLeft.addChild(this.legLeft2);
        this.fredhead.addChild(this.hat);
        this.earRight.addChild(this.earRightpad);
        this.fredbody.addChild(this.crotch);
        this.fredbody.addChild(this.torso);
        this.armRight.addChild(this.armRight2);
        this.armRight2.addChild(this.handRight);
        this.fredbody.addChild(this.fredhead);
        this.legRight.addChild(this.legRightpad);
        this.fredhead.addChild(this.frednose);
        this.legLeft2.addChild(this.legLeftpad2);
        this.armRight.addChild(this.armRightpad);
        this.armLeft.addChild(this.armLeftpad);
        this.hat.addChild(this.hat2);
        this.legRight2.addChild(this.legRightpad2);
        this.fredhead.addChild(this.jaw);
        this.fredbody.addChild(this.armRight);
        this.legLeft2.addChild(this.footLeft);
        this.fredhead.addChild(this.earLeft);
    }
    
    public void generatemodel() {
        (this.body = new ModelRenderer((ModelBase)this)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureOffset(34, 8).addBox(-4.0f, 6.0f, -3.0f, 8, 12, 6);
        this.body.setTextureOffset(15, 10).addBox(-3.0f, 9.0f, 3.0f, 6, 8, 3);
        this.body.setTextureOffset(26, 0).addBox(-3.0f, 5.0f, -3.0f, 6, 1, 6);
        this.eye = new ModelRenderer((ModelBase)this);
        this.eye.setTextureOffset(0, 10).addBox(-3.0f, 7.0f, -4.0f, 6, 4, 1);
        (this.left_leg = new ModelRenderer((ModelBase)this)).setRotationPoint(-2.0f, 18.0f, 0.0f);
        this.left_leg.setTextureOffset(0, 0).addBox(2.9f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        (this.right_leg = new ModelRenderer((ModelBase)this)).setRotationPoint(2.0f, 18.0f, 0.0f);
        this.right_leg.setTextureOffset(13, 0).addBox(-5.9f, 0.0f, -1.5f, 3, 6, 3);
        (this.rabbitBone = new ModelRenderer((ModelBase)this)).setRotationPoint(0.0f, 24.0f, 0.0f);
        this.rabbitBone.cubeList.add(new ModelBox(this.rabbitBone, 28, 45, -5.0f, -13.0f, -5.0f, 10, 11, 8, 0.0f, false));
        (this.rabbitRleg = new ModelRenderer((ModelBase)this)).setRotationPoint(-3.0f, -2.0f, -1.0f);
        this.rabbitBone.addChild(this.rabbitRleg);
        this.rabbitRleg.cubeList.add(new ModelBox(this.rabbitRleg, 0, 0, -2.0f, 0.0f, -2.0f, 4, 2, 4, 0.0f, false));
        (this.rabbitLarm = new ModelRenderer((ModelBase)this)).setRotationPoint(5.0f, -13.0f, -1.0f);
        this.setRotationAngle(this.rabbitLarm, 0.0f, 0.0f, -0.0873f);
        this.rabbitBone.addChild(this.rabbitLarm);
        this.rabbitLarm.cubeList.add(new ModelBox(this.rabbitLarm, 0, 0, 0.0f, 0.0f, -2.0f, 2, 8, 4, 0.0f, false));
        (this.rabbitRarm = new ModelRenderer((ModelBase)this)).setRotationPoint(-5.0f, -13.0f, -1.0f);
        this.setRotationAngle(this.rabbitRarm, 0.0f, 0.0f, 0.0873f);
        this.rabbitBone.addChild(this.rabbitRarm);
        this.rabbitRarm.cubeList.add(new ModelBox(this.rabbitRarm, 0, 0, -2.0f, 0.0f, -2.0f, 2, 8, 4, 0.0f, false));
        (this.rabbitLleg = new ModelRenderer((ModelBase)this)).setRotationPoint(3.0f, -2.0f, -1.0f);
        this.rabbitBone.addChild(this.rabbitLleg);
        this.rabbitLleg.cubeList.add(new ModelBox(this.rabbitLleg, 0, 0, -2.0f, 0.0f, -2.0f, 4, 2, 4, 0.0f, false));
        (this.rabbitHead = new ModelRenderer((ModelBase)this)).setRotationPoint(0.0f, -14.0f, -1.0f);
        this.rabbitBone.addChild(this.rabbitHead);
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 0, 0, -3.0f, 0.0f, -4.0f, 6, 1, 6, 0.0f, false));
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 56, 0, -5.0f, -9.0f, -5.0f, 2, 3, 2, 0.0f, false));
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 56, 0, 3.0f, -9.0f, -5.0f, 2, 3, 2, 0.0f, true));
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 0, 45, -4.0f, -11.0f, -4.0f, 8, 11, 8, 0.0f, false));
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 46, 0, 1.0f, -20.0f, 0.0f, 3, 9, 1, 0.0f, false));
        this.rabbitHead.cubeList.add(new ModelBox(this.rabbitHead, 46, 0, -4.0f, -20.0f, 0.0f, 3, 9, 1, 0.0f, false));
        this.textureWidth = 100;
        this.textureHeight = 80;
        (this.footRight = new ModelRenderer((ModelBase)this, 22, 39)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.footRight.addBox(-2.5f, 0.0f, -6.0f, 5, 3, 8, 0.0f);
        this.setRotationAngle(this.footRight, -0.034906585f, 0.0f, 0.0f);
        (this.earRight = new ModelRenderer((ModelBase)this, 8, 0)).setRotationPoint(-4.5f, -5.5f, 0.0f);
        this.earRight.addBox(-1.0f, -3.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotationAngle(this.earRight, 0.05235988f, 0.0f, -1.0471976f);
        (this.legLeftpad = new ModelRenderer((ModelBase)this, 48, 39)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legLeftpad.addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6, 0.0f);
        (this.earRightpad_1 = new ModelRenderer((ModelBase)this, 40, 39)).setRotationPoint(0.0f, -1.0f, 0.0f);
        this.earRightpad_1.addBox(-2.0f, -5.0f, -1.0f, 4, 4, 2, 0.0f);
        (this.legLeft = new ModelRenderer((ModelBase)this, 54, 10)).setRotationPoint(3.3f, 12.5f, 0.0f);
        this.legLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        (this.armRightpad2 = new ModelRenderer((ModelBase)this, 0, 26)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armRightpad2.addBox(-2.5f, 0.0f, -2.5f, 5, 7, 5, 0.0f);
        (this.handLeft = new ModelRenderer((ModelBase)this, 58, 56)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.handLeft.addBox(-2.0f, 0.0f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotationAngle(this.handLeft, 0.0f, 0.0f, 0.05235988f);
        (this.armLeft = new ModelRenderer((ModelBase)this, 62, 10)).setRotationPoint(6.5f, -8.0f, 0.0f);
        this.armLeft.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        this.setRotationAngle(this.armLeft, 0.0f, 0.0f, -0.2617994f);
        (this.legRight = new ModelRenderer((ModelBase)this, 90, 8)).setRotationPoint(-3.3f, 12.5f, 0.0f);
        this.legRight.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        (this.armLeft2 = new ModelRenderer((ModelBase)this, 90, 48)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.armLeft2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.armLeft2, -0.17453292f, 0.0f, 0.0f);
        (this.legRight2 = new ModelRenderer((ModelBase)this, 20, 35)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.legRight2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.legRight2, 0.034906585f, 0.0f, 0.0f);
        (this.armLeftpad2 = new ModelRenderer((ModelBase)this, 0, 58)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armLeftpad2.addBox(-2.5f, 0.0f, -2.5f, 5, 7, 5, 0.0f);
        (this.legLeft2 = new ModelRenderer((ModelBase)this, 72, 48)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.legLeft2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.legLeft2, 0.034906585f, 0.0f, 0.0f);
        (this.hat = new ModelRenderer((ModelBase)this, 70, 24)).setRotationPoint(0.0f, -8.4f, 0.0f);
        this.hat.addBox(-3.0f, -0.5f, -3.0f, 6, 1, 6, 0.0f);
        this.setRotationAngle(this.hat, -0.017453292f, 0.0f, 0.0f);
        (this.earRightpad = new ModelRenderer((ModelBase)this, 85, 0)).setRotationPoint(0.0f, -1.0f, 0.0f);
        this.earRightpad.addBox(-2.0f, -5.0f, -1.0f, 4, 4, 2, 0.0f);
        (this.crotch = new ModelRenderer((ModelBase)this, 56, 0)).setRotationPoint(0.0f, 9.5f, 0.0f);
        this.crotch.addBox(-5.5f, 0.0f, -3.5f, 11, 3, 7, 0.0f);
        (this.torso = new ModelRenderer((ModelBase)this, 8, 0)).setRotationPoint(0.0f, 0.0f, 0.0f);
        this.torso.addBox(-6.0f, -9.0f, -4.0f, 12, 18, 8, 0.0f);
        this.setRotationAngle(this.torso, 0.017453292f, 0.0f, 0.0f);
        (this.armRight2 = new ModelRenderer((ModelBase)this, 90, 20)).setRotationPoint(0.0f, 9.6f, 0.0f);
        this.armRight2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotationAngle(this.armRight2, -0.17453292f, 0.0f, 0.0f);
        (this.handRight = new ModelRenderer((ModelBase)this, 20, 26)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.handRight.addBox(-2.0f, 0.0f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotationAngle(this.handRight, 0.0f, 0.0f, -0.05235988f);
        (this.fredbody = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(0.0f, -9.0f, 0.0f);
        this.fredbody.addBox(-1.0f, -14.0f, -1.0f, 2, 24, 2, 0.0f);
        (this.fredhead = new ModelRenderer((ModelBase)this, 39, 22)).setRotationPoint(0.0f, -13.0f, -0.5f);
        this.fredhead.addBox(-5.5f, -8.0f, -4.5f, 11, 8, 9, 0.0f);
        (this.legRightpad = new ModelRenderer((ModelBase)this, 73, 33)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legRightpad.addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6, 0.0f);
        (this.frednose = new ModelRenderer((ModelBase)this, 17, 67)).setRotationPoint(0.0f, -2.0f, -4.5f);
        this.frednose.addBox(-4.0f, -2.0f, -3.0f, 8, 4, 3, 0.0f);
        (this.legLeftpad2 = new ModelRenderer((ModelBase)this, 16, 50)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legLeftpad2.addBox(-2.5f, 0.0f, -3.0f, 5, 7, 6, 0.0f);
        (this.armRightpad = new ModelRenderer((ModelBase)this, 70, 10)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armRightpad.addBox(-2.5f, 0.0f, -2.5f, 5, 9, 5, 0.0f);
        (this.armLeftpad = new ModelRenderer((ModelBase)this, 38, 54)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.armLeftpad.addBox(-2.5f, 0.0f, -2.5f, 5, 9, 5, 0.0f);
        (this.hat2 = new ModelRenderer((ModelBase)this, 78, 61)).setRotationPoint(0.0f, 0.1f, 0.0f);
        this.hat2.addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotationAngle(this.hat2, -0.017453292f, 0.0f, 0.0f);
        (this.legRightpad2 = new ModelRenderer((ModelBase)this, 0, 39)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.legRightpad2.addBox(-2.5f, 0.0f, -3.0f, 5, 7, 6, 0.0f);
        (this.jaw = new ModelRenderer((ModelBase)this, 49, 65)).setRotationPoint(0.0f, 0.5f, 0.0f);
        this.jaw.addBox(-5.0f, 0.0f, -4.5f, 10, 3, 9, 0.0f);
        this.setRotationAngle(this.jaw, 0.08726646f, 0.0f, 0.0f);
        (this.armRight = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(-6.5f, -8.0f, 0.0f);
        this.armRight.addBox(-1.0f, 0.0f, -1.0f, 2, 10, 2, 0.0f);
        this.setRotationAngle(this.armRight, 0.0f, 0.0f, 0.2617994f);
        (this.footLeft = new ModelRenderer((ModelBase)this, 72, 50)).setRotationPoint(0.0f, 8.0f, 0.0f);
        this.footLeft.addBox(-2.5f, 0.0f, -6.0f, 5, 3, 8, 0.0f);
        this.setRotationAngle(this.footLeft, -0.034906585f, 0.0f, 0.0f);
        (this.earLeft = new ModelRenderer((ModelBase)this, 40, 0)).setRotationPoint(4.5f, -5.5f, 0.0f);
        this.earLeft.addBox(-1.0f, -3.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotationAngle(this.earLeft, 0.05235988f, 0.0f, 1.0471976f);
        this.legRight2.addChild(this.footRight);
        this.fredhead.addChild(this.earRight);
        this.legLeft.addChild(this.legLeftpad);
        this.earLeft.addChild(this.earRightpad_1);
        this.fredbody.addChild(this.legLeft);
        this.armRight2.addChild(this.armRightpad2);
        this.armLeft2.addChild(this.handLeft);
        this.fredbody.addChild(this.armLeft);
        this.fredbody.addChild(this.legRight);
        this.armLeft.addChild(this.armLeft2);
        this.legRight.addChild(this.legRight2);
        this.armLeft2.addChild(this.armLeftpad2);
        this.legLeft.addChild(this.legLeft2);
        this.fredhead.addChild(this.hat);
        this.earRight.addChild(this.earRightpad);
        this.fredbody.addChild(this.crotch);
        this.fredbody.addChild(this.torso);
        this.armRight.addChild(this.armRight2);
        this.armRight2.addChild(this.handRight);
        this.fredbody.addChild(this.fredhead);
        this.legRight.addChild(this.legRightpad);
        this.fredhead.addChild(this.frednose);
        this.legLeft2.addChild(this.legLeftpad2);
        this.armRight.addChild(this.armRightpad);
        this.armLeft.addChild(this.armLeftpad);
        this.hat.addChild(this.hat2);
        this.legRight2.addChild(this.legRightpad2);
        this.fredhead.addChild(this.jaw);
        this.fredbody.addChild(this.armRight);
        this.legLeft2.addChild(this.footLeft);
        this.fredhead.addChild(this.earLeft);
    }
    
    public void setRotationAngle(final ModelRenderer modelRenderer, final float rotateAngleX, final float rotateAngleY, final float rotateAngleZ) {
        modelRenderer.rotateAngleX = rotateAngleX;
        modelRenderer.rotateAngleY = rotateAngleY;
        modelRenderer.rotateAngleZ = rotateAngleZ;
    }
    
    @Inject(method = { "render" }, at = { @At("HEAD") }, cancellable = true)
    public void renderHook(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final CallbackInfo callbackInfo) {
        if (Models.INSTANCE.isOn()) {
            callbackInfo.cancel();
            this.renderCustom(entity, n, n2, n3, n4, n5, n6);
        }
    }
    
    private void glColor(final Color color) {
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public void renderCustom(final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.left_leg == null) {
            this.generatemodel();
        }
        final Models instance = Models.INSTANCE;
        GlStateManager.pushMatrix();
        if ((!(boolean)instance.onlySelf.getValue() || entity == Minecraft.getMinecraft().player || (Managers.FRIENDS.isFriend(entity.getName()) && (boolean)instance.friends.getValue())) && instance.isOn()) {
            if (instance.Mode.getValue() == Models.mode.AmongUs) {
                final boolean b = entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getTicksElytraFlying() > 4;
                this.bipedHead.rotateAngleY = n4 * 0.017453292f;
                if (b) {
                    this.bipedHead.rotateAngleX = -0.7853982f;
                }
                else {
                    this.bipedHead.rotateAngleX = n5 * 0.017453292f;
                }
                this.bipedBody.rotateAngleY = 0.0f;
                this.bipedRightArm.rotationPointZ = 0.0f;
                this.bipedRightArm.rotationPointX = -5.0f;
                this.bipedLeftArm.rotationPointZ = 0.0f;
                this.bipedLeftArm.rotationPointX = 5.0f;
                float n7 = 1.0f;
                if (b) {
                    final float n8 = (float)(entity.motionX * entity.motionX + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ) / 0.2f;
                    n7 = n8 * n8 * n8;
                }
                if (n7 < 1.0f) {
                    n7 = 1.0f;
                }
                this.bipedRightArm.rotateAngleX = MathHelper.cos(n * 0.6662f + 3.1415927f) * 2.0f * n2 * 0.5f / n7;
                this.bipedLeftArm.rotateAngleX = MathHelper.cos(n * 0.6662f) * 2.0f * n2 * 0.5f / n7;
                this.bipedRightArm.rotateAngleZ = 0.0f;
                this.bipedLeftArm.rotateAngleZ = 0.0f;
                this.right_leg.rotateAngleX = MathHelper.cos(n * 0.6662f) * 1.4f * n2 / n7;
                this.left_leg.rotateAngleX = MathHelper.cos(n * 0.6662f + 3.1415927f) * 1.4f * n2 / n7;
                this.right_leg.rotateAngleY = 0.0f;
                this.left_leg.rotateAngleY = 0.0f;
                this.right_leg.rotateAngleZ = 0.0f;
                this.left_leg.rotateAngleZ = 0.0f;
                Color green = (Color)instance.bodyColor.getValue();
                Color white = (Color)instance.eyeColor.getValue();
                Color green2 = (Color)instance.legsColor.getValue();
                if ((boolean)instance.friendHighlight.getValue() && Managers.FRIENDS.isFriend(entity.getName())) {
                    green = Color.GREEN;
                    white = Color.WHITE;
                    green2 = Color.GREEN;
                }
                if (this.isChild) {
                    GlStateManager.scale(0.5f, 0.5f, 0.5f);
                    GlStateManager.translate(0.0f, 24.0f * n6, 0.0f);
                    this.body.render(n6);
                    this.left_leg.render(n6);
                    this.right_leg.render(n6);
                }
                else {
                    GlStateManager.translate(0.0, -0.8, 0.0);
                    GlStateManager.scale(1.8, 1.6, 1.6);
                    this.glColor(green);
                    GlStateManager.translate(0.0, 0.15, 0.0);
                    this.body.render(n6);
                    this.glColor(white);
                    this.eye.render(n6);
                    this.glColor(green2);
                    GlStateManager.translate(0.0, -0.15, 0.0);
                    this.left_leg.render(n6);
                    this.right_leg.render(n6);
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                }
            }
            else if (instance.isOn() && instance.Mode.getValue() == Models.mode.Rabbit) {
                GlStateManager.pushMatrix();
                GlStateManager.scale(1.25, 1.25, 1.25);
                GlStateManager.translate(0.0, -0.3, 0.0);
                this.rabbitHead.rotateAngleX = this.bipedHead.rotateAngleX;
                this.rabbitHead.rotateAngleY = this.bipedHead.rotateAngleY;
                this.rabbitHead.rotateAngleZ = this.bipedHead.rotateAngleZ;
                this.rabbitLarm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
                this.rabbitLarm.rotateAngleY = this.bipedLeftArm.rotateAngleY;
                this.rabbitLarm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
                this.rabbitRarm.rotateAngleX = this.bipedRightArm.rotateAngleX;
                this.rabbitRarm.rotateAngleY = this.bipedRightArm.rotateAngleY;
                this.rabbitRarm.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
                this.rabbitRleg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
                this.rabbitRleg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
                this.rabbitRleg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
                this.rabbitLleg.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
                this.rabbitLleg.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
                this.rabbitLleg.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
                this.rabbitBone.render(n6);
                GlStateManager.popMatrix();
            }
            else if (instance.isOn() && instance.Mode.getValue() == Models.mode.Freddy) {
                this.fredhead.rotateAngleX = this.bipedHead.rotateAngleX;
                this.fredhead.rotateAngleY = this.bipedHead.rotateAngleY;
                this.fredhead.rotateAngleZ = this.bipedHead.rotateAngleZ;
                this.armLeft.rotateAngleX = this.bipedLeftArm.rotateAngleX;
                this.armLeft.rotateAngleY = this.bipedLeftArm.rotateAngleY;
                this.armLeft.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
                this.legRight.rotateAngleX = this.bipedRightLeg.rotateAngleX;
                this.legRight.rotateAngleY = this.bipedRightLeg.rotateAngleY;
                this.legRight.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
                this.legLeft.rotateAngleX = this.bipedLeftLeg.rotateAngleX;
                this.legLeft.rotateAngleY = this.bipedLeftLeg.rotateAngleY;
                this.legLeft.rotateAngleZ = this.bipedLeftLeg.rotateAngleZ;
                this.armRight.rotateAngleX = this.bipedRightArm.rotateAngleX;
                this.armRight.rotateAngleY = this.bipedRightArm.rotateAngleY;
                this.armRight.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.75, 0.65, 0.75);
                GlStateManager.translate(0.0, 0.85, 0.0);
                this.fredbody.render(n6);
                GlStateManager.popMatrix();
            }
        }
        else {
            super.render(entity, n, n2, n3, n4, n5, n6);
            if (this.isChild) {
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                GlStateManager.translate(0.0f, 24.0f * n6, 0.0f);
            }
            else if (entity.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            this.bipedLeftLegwear.render(n6);
            this.bipedRightLegwear.render(n6);
            this.bipedLeftArmwear.render(n6);
            this.bipedRightArmwear.render(n6);
            this.bipedBodyWear.render(n6);
        }
        GlStateManager.popMatrix();
    }
}
