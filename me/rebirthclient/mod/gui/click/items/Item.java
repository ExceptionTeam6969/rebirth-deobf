//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items;

import me.rebirthclient.mod.*;

public class Item extends Mod
{
    protected float y;
    protected int width;
    protected float x;
    private boolean hidden;
    protected int height;
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    public Item(final String s) {
        super(s);
    }
    
    public void onKeyTyped(final char c, final int n) {
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setLocation(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public float getX() {
        return this.x;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void update() {
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
    }
}
