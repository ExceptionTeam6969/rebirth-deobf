//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.entity.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import java.util.*;

public class PullCrystal extends Module
{
    private final Setting pistonPacket;
    private final Setting onlyGround;
    private final Setting onlyStatic;
    public static BlockPos powerPos;
    private EntityPlayer target;
    private final Setting updateDelay;
    private final Setting packet;
    public final Setting pauseWeb;
    private final Setting range;
    public static BlockPos crystalPos;
    private final Setting autoDisable;
    public static PullCrystal INSTANCE;
    private final Setting fire;
    private final Setting noEating;
    private final Setting multiPlace;
    private final Timer timer;
    
    public PullCrystal() {
        super("PullCrystal", "use piston pull crystal and boom", Category.COMBAT);
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 8.0f));
        this.pistonPacket = this.add(new Setting("PistonPacket", false));
        this.packet = this.add(new Setting("Packet", true));
        this.fire = this.add(new Setting("Fire", true));
        this.pauseWeb = this.add(new Setting("PauseWeb", true));
        this.noEating = this.add(new Setting("NoEating", true));
        this.multiPlace = this.add(new Setting("MultiPlace", false));
        this.onlyGround = this.add(new Setting("NoAir", true));
        this.onlyStatic = this.add(new Setting("NoMoving", true));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.updateDelay = this.add(new Setting("UpdateDelay", 100, 0, 500));
        this.target = null;
        this.timer = new Timer();
        PullCrystal.INSTANCE = this;
    }
    //梅梅小姐怎么你了?
}
