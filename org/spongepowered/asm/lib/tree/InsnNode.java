//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class InsnNode extends AbstractInsnNode
{
    public InsnNode(final int n) {
        super(n);
    }
    
    public int getType() {
        return 0;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitInsn(this.opcode);
        this.acceptAnnotations(methodVisitor);
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new InsnNode(this.opcode).cloneAnnotations((AbstractInsnNode)this);
    }
}
