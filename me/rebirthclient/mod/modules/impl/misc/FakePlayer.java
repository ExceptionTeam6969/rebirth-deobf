//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import net.minecraft.client.entity.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import java.util.*;

public class FakePlayer extends Module
{
    EntityOtherPlayerMP player;
    int ticks2;
    private final Setting gappleDelay;
    boolean pop;
    private final Setting hurtTime;
    private final Setting name;
    int ticks;
    private final Setting damage;
    private final Setting move;
    private final Setting totemHurtTime;
    int ticks3;
    
    private boolean lambda$new$1(final Integer n) {
        return this.damage.isOpen();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketExplosion && (boolean)this.damage.getValue()) {
            if (this.player.getDistance(((SPacketExplosion)receive.getPacket()).posX, ((SPacketExplosion)receive.getPacket()).posY, ((SPacketExplosion)receive.getPacket()).getZ()) > 8.0) {
                return;
            }
            if (this.ticks3 > (int)this.hurtTime.getValue() - 1 && !this.pop) {
                this.doPop(DamageUtil.calculateDamage(new BlockPos(((SPacketExplosion)receive.getPacket()).getX(), ((SPacketExplosion)receive.getPacket()).getY(), ((SPacketExplosion)receive.getPacket()).getZ()).down(), (Entity)this.player));
                this.ticks3 = 0;
            }
            else if (this.ticks3 > (int)this.totemHurtTime.getValue() - 1) {
                this.doPop(DamageUtil.calculateDamage(new BlockPos(((SPacketExplosion)receive.getPacket()).getX(), ((SPacketExplosion)receive.getPacket()).getY(), ((SPacketExplosion)receive.getPacket()).getZ()).down(), (Entity)this.player));
                this.pop = false;
                this.ticks3 = 0;
            }
        }
    }
    
    public FakePlayer() {
        super("FakePlayer", "Summons a client-side fake player", Category.MISC);
        this.name = this.add(new Setting("Name", ""));
        this.move = this.add(new Setting("Move", false));
        this.damage = this.add(new Setting("Damage", true).setParent());
        this.totemHurtTime = this.add(new Setting("TotemHurtTime", 25, 0, 50, this::lambda$new$0));
        this.hurtTime = this.add(new Setting("HurtTime", 10, 0, 50, this::lambda$new$1));
        this.gappleDelay = this.add(new Setting("GappleDelay", 100, 0, 200, this::lambda$new$2));
        this.player = null;
        this.ticks3 = 0;
        this.pop = false;
        this.ticks = 0;
        this.ticks2 = 0;
    }
    
    @Override
    public void onEnable() {
        this.sendMessage("Spawned a fakeplayer with the name " + (String)this.name.getValue() + ".");
        if (FakePlayer.mc.player == null || FakePlayer.mc.player.isDead) {
            this.disable();
            return;
        }
        (this.player = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("0f75a81d-70e5-43c5-b892-f33c524284f2"), (String)this.name.getValue()))).copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.player.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        this.player.rotationYaw = FakePlayer.mc.player.rotationYaw;
        this.player.rotationPitch = FakePlayer.mc.player.rotationPitch;
        this.player.setGameType(GameType.SURVIVAL);
        this.player.inventory.copyInventory(FakePlayer.mc.player.inventory);
        this.player.setHealth(20.0f);
        this.player.setAbsorptionAmount(16.0f);
        FakePlayer.mc.world.addEntityToWorld(-12345, (Entity)this.player);
        this.player.onLivingUpdate();
    }
    
    @Override
    public void onDisable() {
        if (FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-12345);
        }
    }
    
    public void travel(final float n, final float n2, final float n3) {
        final double posY = this.player.posY;
        float n4 = 0.8f;
        float n5 = 0.02f;
        float n6 = (float)EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.player);
        if (n6 > 3.0f) {
            n6 = 3.0f;
        }
        if (!this.player.onGround) {
            n6 *= 0.5f;
        }
        if (n6 > 0.0f) {
            n4 += (0.54600006f - n4) * n6 / 3.0f;
            n5 += (this.player.getAIMoveSpeed() - n5) * n6 / 4.0f;
        }
        this.player.moveRelative(n, n2, n3, n5);
        this.player.move(MoverType.SELF, this.player.motionX, this.player.motionY, this.player.motionZ);
        final EntityOtherPlayerMP player = this.player;
        player.motionX *= n4;
        final EntityOtherPlayerMP player2 = this.player;
        player2.motionY *= 0.800000011920929;
        final EntityOtherPlayerMP player3 = this.player;
        player3.motionZ *= n4;
        if (!this.player.hasNoGravity()) {
            final EntityOtherPlayerMP player4 = this.player;
            player4.motionY -= 0.02;
        }
        if (this.player.collidedHorizontally && this.player.isOffsetPositionInLiquid(this.player.motionX, this.player.motionY + 0.6000000238418579 - this.player.posY + posY, this.player.motionZ)) {
            this.player.motionY = 0.30000001192092896;
        }
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.damage.isOpen();
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.damage.isOpen();
    }
    
    private void doPop(final float n) {
        float n2 = n - this.player.getAbsorptionAmount();
        if (this.player.getHealth() - n2 < 0.1) {
            EarthPopChams.INSTANCE.onTotemPop((EntityPlayer)this.player);
            FakePlayer.mc.world.playSound((EntityPlayer)FakePlayer.mc.player, this.player.posX, this.player.posY, this.player.posZ, SoundEvents.ITEM_TOTEM_USE, FakePlayer.mc.player.getSoundCategory(), 1.0f, 1.0f);
            this.player.setHealth(2.0f);
            this.player.setAbsorptionAmount(8.0f);
            this.pop = true;
        }
        else {
            if (n2 < 0.0f) {
                n2 = 0.0f;
            }
            this.player.setHealth(this.player.getHealth() - n2);
            float n3 = this.player.getAbsorptionAmount() - n;
            if (n3 < 0.0f) {
                n3 = 0.0f;
            }
            this.player.setAbsorptionAmount(n3);
        }
    }
    
    @Override
    public void onLogin() {
        if (this.isOn()) {
            this.disable();
        }
    }
    
    @Override
    public void onLogout() {
        if (this.isOn()) {
            this.disable();
        }
    }
    
    @Override
    public String getInfo() {
        return (String)this.name.getValue();
    }
    
    @Override
    public void onTick() {
        ++this.ticks;
        ++this.ticks2;
        ++this.ticks3;
        if (this.player != null) {
            if (this.ticks > (int)this.gappleDelay.getValue() - 1) {
                this.player.setAbsorptionAmount(16.0f);
                this.ticks = 0;
            }
            if (this.ticks2 > 19) {
                float n = this.player.getHealth() + 1.0f;
                if (n > 20.0f) {
                    n = 20.0f;
                }
                this.player.setHealth(n);
                this.ticks2 = 0;
            }
            final Random random = new Random();
            this.player.moveForward = FakePlayer.mc.player.moveForward + random.nextInt(5) / 10.0f;
            this.player.moveStrafing = FakePlayer.mc.player.moveStrafing + random.nextInt(5) / 10.0f;
            if (this.move.getValue()) {
                this.travel(this.player.moveStrafing, this.player.moveVertical, this.player.moveForward);
            }
        }
    }
}
