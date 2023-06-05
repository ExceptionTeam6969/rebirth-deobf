//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import java.util.*;

public class Vector3f
{
    public final float y;
    public final float z;
    public final float x;
    
    public Vector3f(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Vector3f vector3f = (Vector3f)o;
        return Float.compare(vector3f.x, this.x) == 0 && Float.compare(vector3f.y, this.y) == 0 && Float.compare(vector3f.z, this.z) == 0;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
    }
}
