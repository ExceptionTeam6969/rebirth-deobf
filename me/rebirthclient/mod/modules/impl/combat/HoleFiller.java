//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.util.math.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.concurrent.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HoleFiller extends Module
{
    private final Setting range;
    private final Setting smartRange;
    private final Setting smart;
    private EntityPlayer closestTarget;
    private final Setting packet;
    private final Map renderBlocks;
    private final Setting autoDisable;
    private final Setting webs;
    private final Setting logic;
    private final Setting rotate;
    private final Setting render;
    private final Timer renderTimer;
    private final Setting box;
    private final Setting line;
    
    @Override
    public String getInfo() {
        if (this.smart.getValue()) {
            return Managers.TEXT.normalizeCases(this.logic.getValue());
        }
        return "Normal";
    }
    
    private BlockPos getClosestTargetPos(final EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.render.getValue()) {
            this.renderTimer.reset();
            this.renderBlocks.forEach(this::lambda$onRender3D$4);
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    private boolean lambda$new$0(final Logic logic) {
        return this.smart.isOpen();
    }
    
    private boolean isInRange(final BlockPos blockPos) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)this.getSphere(this.getPlayerPos(), ((Double)this.range.getValue()).floatValue()).stream().filter(this::isHole).collect(Collectors.toList()));
        return create.contains((Object)blockPos);
    }
    
    @Override
    public void onDisable() {
        this.closestTarget = null;
        Managers.ROTATIONS.resetRotationsPacket();
    }
    
    @Override
    public void onUpdate() {
        if (HoleFiller.mc.world == null) {
            return;
        }
        if (this.smart.getValue()) {
            this.findClosestTarget();
        }
        final List placePositions = this.getPlacePositions();
        final BlockPos blockPos = null;
        final int hotbarClass = InventoryUtil.findHotbarClass((Class)BlockObsidian.class);
        final int hotbarClass2 = InventoryUtil.findHotbarClass((Class)BlockEnderChest.class);
        final int hotbarClass3 = InventoryUtil.findHotbarClass((Class)BlockWeb.class);
        if (!(boolean)this.webs.getValue() && hotbarClass == -1 && hotbarClass2 == -1) {
            return;
        }
        if ((boolean)this.webs.getValue() && hotbarClass3 == -1 && hotbarClass == -1 && hotbarClass2 == -1) {
            return;
        }
        final int currentItem = HoleFiller.mc.player.inventory.currentItem;
        final Iterator<BlockPos> iterator = placePositions.iterator();
        if (!iterator.hasNext()) {
            if (blockPos != null && HoleFiller.mc.player.onGround) {
                InventoryUtil.doSwap(((boolean)this.webs.getValue()) ? ((hotbarClass3 == -1) ? ((hotbarClass == -1) ? hotbarClass2 : hotbarClass) : hotbarClass3) : ((hotbarClass == -1) ? hotbarClass2 : hotbarClass));
                this.renderBlocks.put(blockPos, System.currentTimeMillis());
                Managers.INTERACTIONS.placeBlock(blockPos, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false);
                if (HoleFiller.mc.player.inventory.currentItem != currentItem) {
                    InventoryUtil.doSwap(currentItem);
                }
                HoleFiller.mc.player.swingArm(EnumHand.MAIN_HAND);
                HoleFiller.mc.player.inventory.currentItem = currentItem;
            }
            if (blockPos == null && (boolean)this.autoDisable.getValue() && !(boolean)this.smart.getValue()) {
                this.disable();
            }
            return;
        }
        final BlockPos blockPos2 = iterator.next();
        if (!HoleFiller.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos2)).isEmpty()) {
            return;
        }
        if ((boolean)this.smart.getValue() && this.isInRange(blockPos2)) {
            return;
        }
        if ((boolean)this.smart.getValue() && this.isInRange(blockPos2) && this.logic.getValue() == Logic.HOLE && this.closestTarget.getDistanceSq(blockPos2) <= (int)this.smartRange.getValue()) {
            return;
        }
    }
    
    private void lambda$onRender3D$4(final BlockPos blockPos, final Long n) {
        final int n2 = 255;
        final int n3 = 80;
        if (System.currentTimeMillis() - n > 400L) {
            this.renderTimer.reset();
            this.renderBlocks.remove(blockPos);
        }
        else {
            final double n4 = -MathHelper.clamp(MathUtil.normalize((double)(System.currentTimeMillis() - n - 100L), 0.0, 500.0), 0.0, 1.0) + 1.0;
            RenderUtil.drawBoxESP(new BlockPos((Vec3i)blockPos), Managers.COLORS.getCurrent(), true, new Color(255, 255, 255, (int)(n4 * n2)), 0.7f, (boolean)this.line.getValue(), (boolean)this.box.getValue(), (int)(n4 * n3), true, 0.0);
        }
    }
    
    private List getSphere(final BlockPos blockPos, final float n) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        int n2 = getX - (int)n;
        if (n2 > getX + n) {
            return list;
        }
        int n3 = getZ - (int)n;
        if (n3 > getZ + n) {
            ++n2;
            return null;
        }
        int n4 = getY - (int)n;
        if (n4 >= getY + n) {
            ++n3;
            return null;
        }
        if ((getX - n2) * (getX - n2) + (getZ - n3) * (getZ - n3) + (getY - n4) * (getY - n4) < (double)(n * n)) {
            list.add(new BlockPos(n2, n4, n3));
        }
        ++n4;
        return null;
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.smart.isOpen();
    }
    
    public HoleFiller() {
        super("HoleFiller", "Fills all safe spots in radius", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", false));
        this.packet = this.add(new Setting("Packet", false));
        this.webs = this.add(new Setting("Webs", false));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.range = this.add(new Setting("Radius", 4.0, 0.0, 6));
        this.smart = this.add(new Setting("Smart", false).setParent());
        this.logic = this.add(new Setting("Logic", Logic.PLAYER, this::lambda$new$0));
        this.smartRange = this.add(new Setting("EnemyRange", 4, 0, 6, this::lambda$new$1));
        this.render = this.add(new Setting("Render", true).setParent());
        this.box = this.add(new Setting("Box", true, this::lambda$new$2));
        this.line = this.add(new Setting("Line", true, this::lambda$new$3));
        this.renderBlocks = new ConcurrentHashMap();
        this.renderTimer = new Timer();
    }
    
    private void findClosestTarget() {
        final List playerEntities = HoleFiller.mc.world.playerEntities;
        this.closestTarget = null;
        for (final EntityPlayer entityPlayer : playerEntities) {
            if (entityPlayer != HoleFiller.mc.player && !Managers.FRIENDS.isFriend(entityPlayer.getName()) && EntityUtil.isLiving((Entity)entityPlayer)) {
                if (entityPlayer.getHealth() <= 0.0f) {
                    return;
                }
                if (this.closestTarget == null) {
                    this.closestTarget = entityPlayer;
                    return;
                }
                if (HoleFiller.mc.player.getDistance((Entity)entityPlayer) >= HoleFiller.mc.player.getDistance((Entity)this.closestTarget)) {
                    return;
                }
                this.closestTarget = entityPlayer;
            }
        }
    }
    
    private boolean isHole(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 0, 0);
        final BlockPos add3 = blockPos.add(0, 0, -1);
        final BlockPos add4 = blockPos.add(1, 0, 0);
        final BlockPos add5 = blockPos.add(-1, 0, 0);
        final BlockPos add6 = blockPos.add(0, 0, 1);
        final BlockPos add7 = blockPos.add(0, 2, 0);
        final BlockPos add = blockPos.add(0.5, 0.5, 0.5);
        final BlockPos add8 = blockPos.add(0, -1, 0);
        return HoleFiller.mc.world.getBlockState(add).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(add2).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(add7).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(add3).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add3).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add4).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add4).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add5).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add5).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add6).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add6).getBlock() == Blocks.BEDROCK) && HoleFiller.mc.world.getBlockState(add).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(add8).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add8).getBlock() == Blocks.BEDROCK);
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketBlockChange && this.renderBlocks.containsKey(((SPacketBlockChange)receive.getPacket()).getBlockPosition())) {
            this.renderTimer.reset();
            if (((SPacketBlockChange)receive.getPacket()).getBlockState().getBlock() != Blocks.AIR && this.renderTimer.passedMs(400L)) {
                this.renderBlocks.remove(((SPacketBlockChange)receive.getPacket()).getBlockPosition());
            }
        }
    }
    
    private BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleFiller.mc.player.posX), Math.floor(HoleFiller.mc.player.posY), Math.floor(HoleFiller.mc.player.posZ));
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.render.isOpen();
    }
    
    private List getPlacePositions() {
        final NonNullList create = NonNullList.create();
        if ((boolean)this.smart.getValue() && this.closestTarget != null) {
            create.addAll((Collection)this.getSphere(this.getClosestTargetPos(this.closestTarget), (float)this.smartRange.getValue()).stream().filter(this::isHole).filter(this::isInRange).collect(Collectors.toList()));
        }
        else if (!(boolean)this.smart.getValue()) {
            create.addAll((Collection)this.getSphere(this.getPlayerPos(), ((Double)this.range.getValue()).floatValue()).stream().filter(this::isHole).collect(Collectors.toList()));
        }
        return (List)create;
    }
    
    private enum Logic
    {
        PLAYER("PLAYER", 0), 
        HOLE("HOLE", 1);
        
        private static final Logic[] $VALUES;
        
        private Logic(final String s, final int n) {
        }
        
        static {
            $VALUES = new Logic[] { Logic.PLAYER, Logic.HOLE };
        }
    }
}
