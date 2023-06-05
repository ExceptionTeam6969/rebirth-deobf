//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.entity.*;

@Cancelable
public class RenderItemInFirstPersonEvent extends Event
{
    public ItemStack stack;
    public final ItemCameraTransforms.TransformType transformType;
    public final boolean leftHanded;
    public final EntityLivingBase entity;
    
    public void setStack(final ItemStack stack) {
        this.stack = stack;
    }
    
    public ItemCameraTransforms.TransformType getTransformType() {
        return this.transformType;
    }
    
    public ItemStack getStack() {
        return this.stack;
    }
    
    public RenderItemInFirstPersonEvent(final EntityLivingBase entity, final ItemStack stack, final ItemCameraTransforms.TransformType transformType, final boolean leftHanded, final int n) {
        super(n);
        this.entity = entity;
        this.stack = stack;
        this.transformType = transformType;
        this.leftHanded = leftHanded;
    }
    
    public EntityLivingBase getEntity() {
        return this.entity;
    }
}
