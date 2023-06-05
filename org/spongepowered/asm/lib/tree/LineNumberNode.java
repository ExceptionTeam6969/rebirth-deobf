//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class LineNumberNode extends AbstractInsnNode
{
    public int line;
    public LabelNode start;
    
    public LineNumberNode(final int line, final LabelNode start) {
        super(-1);
        this.line = line;
        this.start = start;
    }
    
    public int getType() {
        return 15;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitLineNumber(this.line, this.start.getLabel());
    }
    
    public AbstractInsnNode clone(final Map map) {
        return new LineNumberNode(this.line, clone(this.start, map));
    }
}
