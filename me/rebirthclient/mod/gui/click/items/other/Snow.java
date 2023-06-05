//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.other;

import net.minecraft.client.gui.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;

public class Snow
{
    private int x;
    private int y;
    private int fallingSpeed;
    private int size;
    
    public void drawSnow(final ScaledResolution scaledResolution) {
        RenderUtil.drawRect((float)this.getX(), (float)this.getY(), (float)(this.getX() + this.size), (float)(this.getY() + this.size), -1714829883);
        this.setY(this.getY() + this.fallingSpeed);
        if (this.getY() > scaledResolution.getScaledHeight() + 10 || this.getY() < -10) {
            this.setY(-10);
            final Random random = new Random();
            this.fallingSpeed = random.nextInt(10) + 1;
            this.size = random.nextInt(4) + 1;
        }
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Snow(final int x, final int y, final int fallingSpeed, final int size) {
        this.x = x;
        this.y = y;
        this.fallingSpeed = fallingSpeed;
        this.size = size;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
}
