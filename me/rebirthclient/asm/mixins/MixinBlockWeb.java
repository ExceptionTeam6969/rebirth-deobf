//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ BlockWeb.class })
public class MixinBlockWeb extends Block
{
    protected MixinBlockWeb() {
        super(Material.WEB);
    }
    
    @Nullable
    @Overwrite
    public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess blockAccess, final BlockPos blockPos) {
        if (AntiWeb.INSTANCE.isOn() && AntiWeb.INSTANCE.antiModeSetting.getValue() == AntiWeb.AntiMode.Block) {
            return MixinBlockWeb.FULL_BLOCK_AABB;
        }
        return MixinBlockWeb.NULL_AABB;
    }
}
