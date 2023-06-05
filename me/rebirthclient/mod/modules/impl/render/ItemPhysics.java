//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class ItemPhysics extends Module
{
    public static ItemPhysics INSTANCE;
    public final Setting rotateSpeed;
    public final Setting shulkerBox;
    public final Setting Scaling;
    
    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items", Category.RENDER);
        this.Scaling = this.add(new Setting("Scale", 0.5f, 0.1f, 1.0f));
        this.rotateSpeed = this.add(new Setting("RotateSpeed", 0.5f, 0.0f, 1.0f));
        this.shulkerBox = this.add(new Setting("ShulkerBoxScale", 0.5f, 0.0f, 4.0f));
        ItemPhysics.INSTANCE = this;
    }
    
    static {
        ItemPhysics.INSTANCE = new ItemPhysics();
    }
}
