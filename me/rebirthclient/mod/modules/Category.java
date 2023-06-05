//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules;

public enum Category
{
    EXPLOIT("EXPLOIT", 5, "Exploit");
    
    private final String name;
    
    PLAYER("PLAYER", 4, "Player"), 
    CLIENT("CLIENT", 6, "Client"), 
    MISC("MISC", 1, "Misc"), 
    RENDER("RENDER", 2, "Render");
    
    private static final Category[] $VALUES;
    
    MOVEMENT("MOVEMENT", 3, "Movement"), 
    HUD("HUD", 7, "HUD"), 
    COMBAT("COMBAT", 0, "Combat");
    
    static {
        $VALUES = new Category[] { Category.COMBAT, Category.MISC, Category.RENDER, Category.MOVEMENT, Category.PLAYER, Category.EXPLOIT, Category.CLIENT, Category.HUD };
    }
    
    private Category(final String s, final int n, final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
