//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;

@InjectionPoint.AtCode("STORE")
public class AfterStoreLocal extends BeforeLoadLocal
{
    public AfterStoreLocal(final InjectionPointData injectionPointData) {
        super(injectionPointData, 54, true);
    }
}
