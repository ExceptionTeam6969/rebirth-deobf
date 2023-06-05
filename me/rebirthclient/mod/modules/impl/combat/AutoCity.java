//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.*;
import net.minecraft.block.*;
import net.minecraft.init.*;

public class AutoCity extends Module
{
    public static EntityPlayer target;
    private final Setting range;
    public final Setting burrow;
    private final Setting keyMode;
    private final Setting keyBind;
    private final Setting toggle;
    
    private boolean lambda$new$0(final Bind bind) {
        return (boolean)this.keyMode.getValue();
    }
    
    @Override
    public String getInfo() {
        if (AutoCity.target != null) {
            return AutoCity.target.getName();
        }
        return null;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return !(boolean)this.keyMode.getValue();
    }
    
    public AutoCity() {
        super("AutoCity", "Automatically break the enemy's surround", Category.COMBAT);
        this.burrow = this.add(new Setting("MineBurrow", true));
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 8.0f));
        this.keyMode = this.add(new Setting("KeyMode", true));
        this.keyBind = this.add(new Setting("Enable", new Bind(-1), this::lambda$new$0));
        this.toggle = this.add(new Setting("Toggle", false, this::lambda$new$1));
    }
    
    private void mineBlock(final BlockPos blockPos) {
        CombatUtil.mineBlock(blockPos);
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return AutoCity.mc.world.getBlockState(blockPos).getBlock();
    }
    
    @Override
    public void onUpdate() {
        if ((boolean)this.keyMode.getValue() && !((Bind)this.keyBind.getValue()).isDown()) {
            return;
        }
        AutoCity.target = CombatUtil.getTarget((double)(float)this.range.getValue(), 10.0);
        if (AutoCity.target == null) {
            return;
        }
        final BlockPos blockPos = new BlockPos(AutoCity.target.posX, AutoCity.target.posY + 0.5, AutoCity.target.posZ);
        if (!AutoCity.mc.world.isAirBlock(blockPos) && (boolean)this.burrow.getValue()) {
            this.mineBlock(blockPos);
        }
        else if (!AutoCity.mc.world.isAirBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3)) && (boolean)this.burrow.getValue()) {
            this.mineBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3));
        }
        else if (!AutoCity.mc.world.isAirBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3)) && (boolean)this.burrow.getValue()) {
            this.mineBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ - 0.3));
        }
        else if (!AutoCity.mc.world.isAirBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3)) && (boolean)this.burrow.getValue()) {
            this.mineBlock(new BlockPos(AutoCity.target.posX - 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3));
        }
        else if (!AutoCity.mc.world.isAirBlock(new BlockPos(AutoCity.target.posX + 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ + 0.3)) && (boolean)this.burrow.getValue()) {
            this.mineBlock(new BlockPos(AutoCity.target.posX - 0.3, AutoCity.target.posY + 0.5, AutoCity.target.posZ - 0.3));
        }
        else if (this == AutoCity.target) {
            if (this.getBlock(blockPos.add(0, 1, 2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, 1));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, -1));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, -2));
            }
            else if (this.getBlock(blockPos.add(0, 1, 2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, 2));
            }
            else if (this.getBlock(blockPos.add(2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-2, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-2, 0, 0));
            }
            else if (this.getBlock(blockPos.add(0, 1, -2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, -2));
            }
            else if (this.getBlock(blockPos.add(0, 1, 2)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, 2));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, 1));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, 1));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 0, -1));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-1, 0, 0));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, -1));
            }
            else if (this.getBlock(blockPos.add(1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(-1, 2, 0)) == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-1, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 2, -1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, -1));
            }
            else if (this.getBlock(blockPos.add(0, 2, 1)) == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, 1));
            }
            else if (this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-2, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-2, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-2, 1, 0));
            }
            else if (this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(2, 1, 0)) != Blocks.AIR && this.getBlock(blockPos.add(2, 1, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(2, 1, 0));
            }
            else if (this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, 2)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, 2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, 2));
            }
            else if (this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 0, -2)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -2)) != Blocks.AIR && this.getBlock(blockPos.add(0, 1, -2)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 1, -2));
            }
            else if (this.getBlock(blockPos.add(-1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 1, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(-1, 2, 0)) != Blocks.AIR && this.getBlock(blockPos.add(-1, 2, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(-1, 2, 0));
            }
            else if (this.getBlock(blockPos.add(1, 0, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 1, 0)) != Blocks.BEDROCK && this.getBlock(blockPos.add(1, 2, 0)) != Blocks.AIR && this.getBlock(blockPos.add(1, 2, 0)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(1, 2, 0));
            }
            else if (this.getBlock(blockPos.add(0, 0, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, 1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, 1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 2, 1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 2, 1));
            }
            else if (this.getBlock(blockPos.add(0, 0, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 1, -1)) != Blocks.BEDROCK && this.getBlock(blockPos.add(0, 2, -1)) != Blocks.AIR && this.getBlock(blockPos.add(0, 2, -1)) != Blocks.BEDROCK) {
                this.mineBlock(blockPos.add(0, 2, -1));
            }
        }
        if ((boolean)this.toggle.getValue() && !(boolean)this.keyMode.getValue()) {
            this.disable();
        }
    }
}
