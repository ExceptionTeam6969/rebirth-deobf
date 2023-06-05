//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import java.util.concurrent.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.util.math.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.util.*;
import net.minecraft.block.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import java.util.function.*;

public class WebTrap extends Module
{
    private final Setting range;
    private final Setting face;
    private final Setting line;
    public static boolean isPlacing;
    private final Setting packet;
    private int lastHotbarSlot;
    private final Setting feet;
    private final Timer timer;
    private boolean didPlace;
    private BlockPos startPos;
    private final Map renderBlocks;
    private final Setting disable;
    private boolean isSneaking;
    private final Setting delay;
    private final Setting raytrace;
    private final Setting blocksPerPlace;
    private final Setting render;
    private final Timer renderTimer;
    private EntityPlayer target;
    private int placements;
    private final Setting rotate;
    private final Setting box;
    
    private static double lambda$placeList$4(final Vec3d vec3d) {
        return vec3d.y;
    }
    
    public WebTrap() {
        super("WebTrap", "Traps other players in webs", Category.COMBAT);
        this.range = this.add(new Setting("Range", 5.0, 0.0, 7.0));
        this.delay = this.add(new Setting("TickDelay", 50, 0, 250));
        this.blocksPerPlace = this.add(new Setting("BPT", 1, 1, 30));
        this.packet = this.add(new Setting("Packet", false));
        this.disable = this.add(new Setting("AutoDisable", false));
        this.rotate = this.add(new Setting("Rotate", true));
        this.raytrace = this.add(new Setting("Raytrace", false));
        this.feet = this.add(new Setting("Feet", true));
        this.face = this.add(new Setting("Face", false));
        this.render = this.add(new Setting("Render", true).setParent());
        this.box = this.add(new Setting("Box", true, this::lambda$new$0));
        this.line = this.add(new Setting("Line", true, this::lambda$new$1));
        this.timer = new Timer();
        this.renderBlocks = new ConcurrentHashMap();
        this.renderTimer = new Timer();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.render.isOpen();
    }
    
    private EntityPlayer getTarget(final double n) {
        final EntityPlayer entityPlayer = null;
        final double n2 = Math.pow(n, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : WebTrap.mc.world.playerEntities) {
            if (EntityUtil.isValid((Entity)entityPlayer2, n) && !entityPlayer2.isInWeb) {
                if (Managers.SPEED.getPlayerSpeed(entityPlayer2) > 30.0) {
                    return null;
                }
                if (entityPlayer == null) {
                    WebTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                    return null;
                }
                if (WebTrap.mc.player.getDistanceSq((Entity)entityPlayer2) >= n2) {
                    return null;
                }
                WebTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                return null;
            }
        }
        return entityPlayer;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.render.isOpen();
    }
    
    private void lambda$onRender3D$2(final BlockPos blockPos, final Long n) {
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
    
    @Override
    public void onDisable() {
        WebTrap.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        InventoryUtil.doSwap(this.lastHotbarSlot);
    }
    
    private void doWebTrap() {
        this.placeList(this.getPlacements());
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        this.startPos = EntityUtil.getRoundedPos((Entity)WebTrap.mc.player);
        this.lastHotbarSlot = WebTrap.mc.player.inventory.currentItem;
    }
    
    @Override
    public void onTick() {
        this.doTrap();
    }
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    private void placeList(final List list) {
        list.sort(WebTrap::lambda$placeList$3);
        list.sort(Comparator.comparingDouble((ToDoubleFunction<? super Vec3d>)WebTrap::lambda$placeList$4));
        final Iterator<Vec3d> iterator = list.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final BlockPos blockPos = new BlockPos((Vec3d)iterator.next());
        final int placeAbility = BlockUtil.getPlaceAbility(blockPos, (boolean)this.raytrace.getValue());
        if (placeAbility != 3 && placeAbility != 1) {
            return;
        }
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockWeb.class));
        this.renderBlocks.put(blockPos, System.currentTimeMillis());
        this.placeBlock(blockPos);
        InventoryUtil.doSwap(this.lastHotbarSlot);
    }
    
    private List getPlacements() {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (this.feet.getValue()) {
            list.add(getPositionVector);
        }
        if (this.face.getValue()) {
            list.add(getPositionVector.add(0.0, 1.0, 0.0));
        }
        return list;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < (int)this.blocksPerPlace.getValue() && WebTrap.mc.player.getDistanceSq(blockPos) <= MathUtil.square(6.0)) {
            WebTrap.isPlacing = true;
            Managers.INTERACTIONS.placeBlock(blockPos, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), false, true);
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    private static int lambda$placeList$3(final Vec3d vec3d, final Vec3d vec3d2) {
        return Double.compare(WebTrap.mc.player.getDistanceSq(vec3d2.x, vec3d2.y, vec3d2.z), WebTrap.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z));
    }
    
    private void doTrap() {
        if (this != 0) {
            return;
        }
        this.doWebTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.render.getValue()) {
            this.renderTimer.reset();
            this.renderBlocks.forEach(this::lambda$onRender3D$2);
        }
    }
}
