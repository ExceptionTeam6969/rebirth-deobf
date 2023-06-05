//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import net.minecraft.entity.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.client.settings.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.client.event.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;

public class Freecam extends Module
{
    private final Setting hSpeed;
    private final MovementInput cameraMovement;
    private int lastActiveTick;
    private final Setting copyInventory;
    private FreecamCamera camera;
    private final Setting vSpeed;
    private final Setting follow;
    private Entity oldRenderEntity;
    public final Setting movePlayer;
    private final MovementInput playerMovement;
    private Entity cachedActiveEntity;
    public static Freecam INSTANCE;
    
    public Freecam() {
        super("Freecam", "Control your camera separately to your body", Category.PLAYER);
        this.movePlayer = this.add(new Setting("Control", new Bind(56)));
        this.follow = this.add(new Setting("Follow", false));
        this.copyInventory = this.add(new Setting("CopyInv", false));
        this.hSpeed = this.add(new Setting("HSpeed", 1.0f, 0.2f, 2.0f));
        this.vSpeed = this.add(new Setting("VSpeed", 1.0f, 0.2f, 2.0f));
        this.cameraMovement = (MovementInput)new MovementInputFromOptions(Freecam.mc.gameSettings) {
            final Freecam this$0;
            
            public void updatePlayerMoveState() {
                if (!((Bind)this.this$0.movePlayer.getValue()).isDown()) {
                    super.updatePlayerMoveState();
                }
                else {
                    this.moveStrafe = 0.0f;
                    this.moveForward = 0.0f;
                    this.forwardKeyDown = false;
                    this.backKeyDown = false;
                    this.leftKeyDown = false;
                    this.rightKeyDown = false;
                    this.jump = false;
                    this.sneak = false;
                }
            }
        };
        this.playerMovement = (MovementInput)new MovementInputFromOptions(Freecam.mc.gameSettings) {
            final Freecam this$0;
            
            public void updatePlayerMoveState() {
                if (((Bind)this.this$0.movePlayer.getValue()).isDown()) {
                    super.updatePlayerMoveState();
                }
                else {
                    this.moveStrafe = 0.0f;
                    this.moveForward = 0.0f;
                    this.forwardKeyDown = false;
                    this.backKeyDown = false;
                    this.leftKeyDown = false;
                    this.rightKeyDown = false;
                    this.jump = false;
                    this.sneak = false;
                }
            }
        };
        this.cachedActiveEntity = null;
        this.lastActiveTick = -1;
        this.oldRenderEntity = null;
        this.camera = null;
        Freecam.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onFreecamEntity(final FreecamEntityEvent freecamEntityEvent) {
        if (this.getActiveEntity() != null) {
            freecamEntityEvent.setEntity(this.getActiveEntity());
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Unload unload) {
        Freecam.mc.setRenderViewEntity((Entity)Freecam.mc.player);
        this.disable();
    }
    
    @Override
    public void onUpdate() {
        this.camera.setCopyInventory((boolean)this.copyInventory.getValue());
        this.camera.setFollow((boolean)this.follow.getValue());
        this.camera.sethSpeed((float)this.hSpeed.getValue());
        this.camera.setvSpeed((float)this.vSpeed.getValue());
    }
    
    @Override
    public void onDisable() {
        if (Freecam.mc.player == null) {
            return;
        }
        Freecam.mc.world.removeEntityFromWorld(-1234567);
        if (this.camera != null) {
            Freecam.mc.world.removeEntity((Entity)this.camera);
        }
        this.camera = null;
        Freecam.mc.player.movementInput = (MovementInput)new MovementInputFromOptions(Freecam.mc.gameSettings);
        Freecam.mc.setRenderViewEntity(this.oldRenderEntity);
        Freecam.mc.renderChunksMany = true;
    }
    
    public Entity getActiveEntity() {
        if (this.cachedActiveEntity == null) {
            this.cachedActiveEntity = (Entity)Freecam.mc.player;
        }
        final int ticksExisted = Freecam.mc.player.ticksExisted;
        if (this.lastActiveTick != ticksExisted) {
            this.lastActiveTick = ticksExisted;
            if (this.isOn()) {
                if (((Bind)this.movePlayer.getValue()).isDown()) {
                    this.cachedActiveEntity = (Entity)Freecam.mc.player;
                }
                else {
                    this.cachedActiveEntity = (Entity)((Freecam.mc.getRenderViewEntity() == null) ? Freecam.mc.player : Freecam.mc.getRenderViewEntity());
                }
            }
            else {
                this.cachedActiveEntity = (Entity)Freecam.mc.player;
            }
        }
        return this.cachedActiveEntity;
    }
    
    @SubscribeEvent
    public void onRenderOverlay(final RenderHandEvent renderHandEvent) {
        renderHandEvent.setCanceled(true);
    }
    
    @SubscribeEvent
    public void onFreecam(final FreecamEvent freecamEvent) {
        freecamEvent.setCanceled(true);
    }
    
    @Override
    public void onLogin() {
        if (this.isOn()) {
            this.disable();
        }
    }
    
    @Override
    public void onEnable() {
        if (Freecam.mc.player == null) {
            return;
        }
        this.camera = new FreecamCamera((boolean)this.copyInventory.getValue(), (boolean)this.follow.getValue(), (float)this.hSpeed.getValue(), (float)this.vSpeed.getValue());
        this.camera.movementInput = this.cameraMovement;
        Freecam.mc.player.movementInput = this.playerMovement;
        Freecam.mc.world.addEntityToWorld(-921, (Entity)this.camera);
        this.oldRenderEntity = Freecam.mc.getRenderViewEntity();
        Freecam.mc.setRenderViewEntity((Entity)this.camera);
        Freecam.mc.renderChunksMany = false;
    }
    
    public static class FreecamCamera extends EntityPlayerSP
    {
        private boolean follow;
        private float hSpeed;
        private float vSpeed;
        private boolean copyInventory;
        private final Minecraft mc;
        
        public void setCopyInventory(final boolean copyInventory) {
            this.copyInventory = copyInventory;
        }
        
        public boolean isPotionActive(final Potion potion) {
            return this.mc.player.isPotionActive(potion);
        }
        
        public float getvSpeed() {
            return this.vSpeed;
        }
        
        public AxisAlignedBB getCollisionBox(final Entity entity) {
            return null;
        }
        
        public void setFollow(final boolean follow) {
            this.follow = follow;
        }
        
        public void doBlockCollisions() {
        }
        
        public boolean getIsInvulnerable() {
            return true;
        }
        
        public void setMotion(float n, float n2, float n3) {
            final float n4 = n * n + n2 * n2 + n3 * n3;
            if (n4 >= 1.0E-4f) {
                float sqrt = MathHelper.sqrt(n4);
                if (sqrt < 1.0f) {
                    sqrt = 1.0f;
                }
                final float n5 = sqrt / 2.0f;
                n *= n5;
                n2 *= n5;
                n3 *= n5;
                final float sin = MathHelper.sin(this.rotationYaw * 0.017453292f);
                final float cos = MathHelper.cos(this.rotationYaw * 0.017453292f);
                this.motionX = (n * cos - n3 * sin) * this.hSpeed;
                this.motionY = n2 * (double)this.vSpeed;
                this.motionZ = (n3 * cos + n * sin) * this.hSpeed;
            }
        }
        
        public boolean canBeRidden(final Entity entity) {
            return false;
        }
        
        public void writeEntityToNBT(final NBTTagCompound nbtTagCompound) {
        }
        
        public AxisAlignedBB getEntityBoundingBox() {
            return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        }
        
        public Collection getActivePotionEffects() {
            return this.mc.player.getActivePotionEffects();
        }
        
        public PotionEffect getActivePotionEffect(final Potion potion) {
            return this.mc.player.getActivePotionEffect(potion);
        }
        
        public Map getActivePotionMap() {
            return this.mc.player.getActivePotionMap();
        }
        
        public boolean isFollow() {
            return this.follow;
        }
        
        public void onLivingUpdate() {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            this.movementInput.updatePlayerMoveState();
            this.setMotion(this.movementInput.moveStrafe, this.movementInput.jump ? 1.0f : (this.movementInput.sneak ? -1.0f : 0.0f), this.movementInput.moveForward);
            if (this.mc.gameSettings.keyBindSprint.isKeyDown()) {
                this.motionX *= 2.0;
                this.motionY *= 2.0;
                this.motionZ *= 2.0;
                this.setSprinting(true);
            }
            else {
                this.setSprinting(false);
            }
            if (this.follow) {
                if (Math.abs(this.motionX) <= 9.99999993922529E-9) {
                    this.posX += this.mc.player.posX - this.mc.player.prevPosX;
                }
                if (Math.abs(this.motionY) <= 9.99999993922529E-9) {
                    this.motionY += this.mc.player.posY - this.mc.player.prevPosY;
                }
                if (Math.abs(this.motionZ) <= 9.99999993922529E-9) {
                    this.motionZ += this.mc.player.posZ - this.mc.player.prevPosZ;
                }
            }
            this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        }
        
        public int getTotalArmorValue() {
            return this.mc.player.getTotalArmorValue();
        }
        
        public void readEntityFromNBT(final NBTTagCompound nbtTagCompound) {
        }
        
        public boolean canRenderOnFire() {
            return false;
        }
        
        public boolean canBePushed() {
            return false;
        }
        
        public boolean isCopyInventory() {
            return this.copyInventory;
        }
        
        public boolean isInsideOfMaterial(final Material material) {
            return this.mc.player.isInsideOfMaterial(material);
        }
        
        public boolean hasNoGravity() {
            return true;
        }
        
        public boolean attackEntityFrom(final DamageSource damageSource, final float n) {
            return false;
        }
        
        public void sethSpeed(final float hSpeed) {
            this.hSpeed = hSpeed;
        }
        
        public void applyEntityCollision(final Entity entity) {
        }
        
        public EnumPushReaction getPushReaction() {
            return EnumPushReaction.IGNORE;
        }
        
        public boolean canBeAttackedWithItem() {
            return false;
        }
        
        public FreecamCamera(final World world) {
            super(Minecraft.getMinecraft(), world, Minecraft.getMinecraft().getConnection(), Minecraft.getMinecraft().player.getStatFileWriter(), Minecraft.getMinecraft().player.getRecipeBook());
            this.mc = Minecraft.getMinecraft();
        }
        
        public FreecamCamera(final boolean copyInventory, final boolean follow, final float hSpeed, final float vSpeed) {
            super(Minecraft.getMinecraft(), (World)Minecraft.getMinecraft().world, Minecraft.getMinecraft().getConnection(), Minecraft.getMinecraft().player.getStatFileWriter(), Minecraft.getMinecraft().player.getRecipeBook());
            this.mc = Minecraft.getMinecraft();
            this.copyInventory = copyInventory;
            this.follow = follow;
            this.hSpeed = hSpeed;
            this.vSpeed = vSpeed;
            this.noClip = true;
            this.setHealth(this.mc.player.getHealth());
            this.posX = this.mc.player.posX;
            this.posY = this.mc.player.posY;
            this.posZ = this.mc.player.posZ;
            this.prevPosX = this.mc.player.prevPosX;
            this.prevPosY = this.mc.player.prevPosY;
            this.prevPosZ = this.mc.player.prevPosZ;
            this.lastTickPosX = this.mc.player.lastTickPosX;
            this.lastTickPosY = this.mc.player.lastTickPosY;
            this.lastTickPosZ = this.mc.player.lastTickPosZ;
            this.rotationYaw = this.mc.player.rotationYaw;
            this.rotationPitch = this.mc.player.rotationPitch;
            this.rotationYawHead = this.mc.player.rotationYawHead;
            this.prevRotationYaw = this.mc.player.prevRotationYaw;
            this.prevRotationPitch = this.mc.player.prevRotationPitch;
            this.prevRotationYawHead = this.mc.player.prevRotationYawHead;
            if (this.copyInventory) {
                this.inventory = this.mc.player.inventory;
                this.inventoryContainer = this.mc.player.inventoryContainer;
                this.setHeldItem(EnumHand.MAIN_HAND, this.mc.player.getHeldItemMainhand());
                this.setHeldItem(EnumHand.OFF_HAND, this.mc.player.getHeldItemOffhand());
            }
            final NBTTagCompound nbtTagCompound = new NBTTagCompound();
            this.mc.player.capabilities.writeCapabilitiesToNBT(nbtTagCompound);
            this.capabilities.readCapabilitiesFromNBT(nbtTagCompound);
            this.capabilities.isFlying = true;
            this.attackedAtYaw = this.mc.player.attackedAtYaw;
            this.movementInput = (MovementInput)new MovementInputFromOptions(this.mc.gameSettings);
        }
        
        public boolean canTriggerWalking() {
            return false;
        }
        
        public void setvSpeed(final float vSpeed) {
            this.vSpeed = vSpeed;
        }
        
        public boolean canBeCollidedWith() {
            return false;
        }
        
        public AxisAlignedBB getCollisionBoundingBox() {
            return null;
        }
        
        public void updateFallState(final double n, final boolean b, final IBlockState blockState, final BlockPos blockPos) {
        }
        
        public float getAbsorptionAmount() {
            return this.mc.player.getAbsorptionAmount();
        }
        
        public float gethSpeed() {
            return this.hSpeed;
        }
    }
}
