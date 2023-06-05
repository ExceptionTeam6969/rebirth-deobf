//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.text.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.math.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.item.*;

public class Announcer extends Module
{
    private final Setting delay;
    private int eaten;
    private double lastPositionZ;
    private final Setting breakBlock;
    private int broken;
    private double lastPositionX;
    private double lastPositionY;
    private final Timer delayTimer;
    private final Setting eat;
    private final Setting move;
    
    public Announcer() {
        super("Announcer", "announces yo shit", Category.PLAYER);
        this.move = this.add(new Setting("Move", true));
        this.breakBlock = this.add(new Setting("Break", true));
        this.eat = this.add(new Setting("Eat", true));
        this.delay = this.add(new Setting("Delay", 10.0, 1.0, 30.0));
        this.delayTimer = new Timer();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck() || !spawnCheck()) {
            return;
        }
        final double n = this.lastPositionX - Announcer.mc.player.lastTickPosX;
        final double n2 = this.lastPositionY - Announcer.mc.player.lastTickPosY;
        final double n3 = this.lastPositionZ - Announcer.mc.player.lastTickPosZ;
        final double sqrt = Math.sqrt(n * n + n2 * n2 + n3 * n3);
        if ((boolean)this.move.getValue() && sqrt >= 1.0 && sqrt <= 1000.0 && this.delayTimer.passedS((double)this.delay.getValue())) {
            Announcer.mc.player.sendChatMessage(this.getWalkMessage().replace("{blocks}", new DecimalFormat("0.00").format(sqrt)));
            this.lastPositionX = Announcer.mc.player.lastTickPosX;
            this.lastPositionY = Announcer.mc.player.lastTickPosY;
            this.lastPositionZ = Announcer.mc.player.lastTickPosZ;
            this.delayTimer.reset();
        }
    }
    
    private String getEatMessage() {
        final String[] array = { "I just ate {amount} {name}!" };
        return array[new Random().nextInt(array.length)];
    }
    
    @SubscribeEvent
    public void onBreakBlock(final BreakBlockEvent breakBlockEvent) {
        if (fullNullCheck() || !spawnCheck()) {
            return;
        }
        final int randomBetween = MathUtil.randomBetween(1, 6);
        ++this.broken;
        if ((boolean)this.breakBlock.getValue() && this.broken >= randomBetween && this.delayTimer.passedS((double)this.delay.getValue())) {
            Announcer.mc.player.sendChatMessage(this.getBreakMessage().replace("{amount}", String.valueOf(this.broken)).replace("{name}", BlockUtil.getBlock(breakBlockEvent.getPos()).getLocalizedName()));
            this.broken = 0;
            this.delayTimer.reset();
        }
    }
    
    private String getBreakMessage() {
        final String[] array = { "I just destroyed {amount} {name}!" };
        return array[new Random().nextInt(array.length)];
    }
    
    private String getWalkMessage() {
        final String[] array = { "I just flew over {blocks} blocks!" };
        return array[new Random().nextInt(array.length)];
    }
    
    @SubscribeEvent
    public void onUseItem(final LivingEntityUseItemEvent.Finish finish) {
        if (fullNullCheck() || !spawnCheck()) {
            return;
        }
        final int randomBetween = MathUtil.randomBetween(1, 6);
        if (((boolean)this.eat.getValue() && finish.getEntity() == Announcer.mc.player && finish.getItem().getItem() instanceof ItemFood) || finish.getItem().getItem() instanceof ItemAppleGold) {
            ++this.eaten;
            if (this.eaten >= randomBetween && this.delayTimer.passedS((double)this.delay.getValue())) {
                Announcer.mc.player.sendChatMessage(this.getEatMessage().replace("{amount}", String.valueOf(this.eaten)).replace("{name}", finish.getItem().getDisplayName()));
                this.eaten = 0;
                this.delayTimer.reset();
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.eaten = 0;
        this.broken = 0;
        this.delayTimer.reset();
    }
}
