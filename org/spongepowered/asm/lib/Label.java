//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib;

public class Label
{
    static final int DEBUG = 1;
    static final int RESOLVED = 2;
    static final int RESIZED = 4;
    static final int PUSHED = 8;
    static final int TARGET = 16;
    static final int STORE = 32;
    static final int REACHABLE = 64;
    static final int JSR = 128;
    static final int RET = 256;
    static final int SUBROUTINE = 512;
    static final int VISITED = 1024;
    static final int VISITED2 = 2048;
    public Object info;
    int status;
    int line;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int inputStackTop;
    int outputStackMax;
    Frame frame;
    Label successor;
    Edge successors;
    Label next;
    
    public int getOffset() {
        if ((this.status & 0x2) == 0x0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.position;
    }
    
    void put(final MethodWriter methodWriter, final ByteVector byteVector, final int n, final boolean b) {
        if ((this.status & 0x2) == 0x0) {
            if (b) {
                this.addReference(-1 - n, byteVector.length);
                byteVector.putInt(-1);
            }
            else {
                this.addReference(n, byteVector.length);
                byteVector.putShort(-1);
            }
        }
        else if (b) {
            byteVector.putInt(this.position - n);
        }
        else {
            byteVector.putShort(this.position - n);
        }
    }
    
    private void addReference(final int n, final int n2) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        if (this.referenceCount >= this.srcAndRefPositions.length) {
            final int[] srcAndRefPositions = new int[this.srcAndRefPositions.length + 6];
            System.arraycopy(this.srcAndRefPositions, 0, srcAndRefPositions, 0, this.srcAndRefPositions.length);
            this.srcAndRefPositions = srcAndRefPositions;
        }
        this.srcAndRefPositions[this.referenceCount++] = n;
        this.srcAndRefPositions[this.referenceCount++] = n2;
    }
    
    boolean resolve(final MethodWriter methodWriter, final int position, final byte[] array) {
        boolean b = false;
        this.status |= 0x2;
        this.position = position;
        int i = 0;
        while (i < this.referenceCount) {
            final int n = this.srcAndRefPositions[i++];
            int n2 = this.srcAndRefPositions[i++];
            if (n >= 0) {
                final int n3 = position - n;
                if (n3 < -32768 || n3 > 32767) {
                    final int n4 = array[n2 - 1] & 0xFF;
                    if (n4 <= 168) {
                        array[n2 - 1] = (byte)(n4 + 49);
                    }
                    else {
                        array[n2 - 1] = (byte)(n4 + 20);
                    }
                    b = true;
                }
                array[n2++] = (byte)(n3 >>> 8);
                array[n2] = (byte)n3;
            }
            else {
                final int n5 = position + n + 1;
                array[n2++] = (byte)(n5 >>> 24);
                array[n2++] = (byte)(n5 >>> 16);
                array[n2++] = (byte)(n5 >>> 8);
                array[n2] = (byte)n5;
            }
        }
        return b;
    }
    
    Label getFirst() {
        return (this.frame == null) ? this : this.frame.owner;
    }
    
    void addToSubroutine(final long n, final int n2) {
        if ((this.status & 0x400) == 0x0) {
            this.status |= 0x400;
            this.srcAndRefPositions = new int[n2 / 32 + 1];
        }
        final int[] srcAndRefPositions = this.srcAndRefPositions;
        final int n3 = (int)(n >>> 32);
        srcAndRefPositions[n3] |= (int)n;
    }
    
    void visitSubroutine(final Label p0, final long p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: aload           5
        //     5: ifnull          221
        //     8: aload           5
        //    10: astore          6
        //    12: aload           6
        //    14: getfield        org/spongepowered/asm/lib/Label.next:Lorg/spongepowered/asm/lib/Label;
        //    17: astore          5
        //    19: aload           6
        //    21: aconst_null    
        //    22: putfield        org/spongepowered/asm/lib/Label.next:Lorg/spongepowered/asm/lib/Label;
        //    25: aload_1        
        //    26: ifnull          126
        //    29: aload           6
        //    31: getfield        org/spongepowered/asm/lib/Label.status:I
        //    34: sipush          2048
        //    37: iand           
        //    38: ifeq            44
        //    41: goto            3
        //    44: aload           6
        //    46: dup            
        //    47: getfield        org/spongepowered/asm/lib/Label.status:I
        //    50: sipush          2048
        //    53: ior            
        //    54: putfield        org/spongepowered/asm/lib/Label.status:I
        //    57: aload           6
        //    59: getfield        org/spongepowered/asm/lib/Label.status:I
        //    62: sipush          256
        //    65: iand           
        //    66: ifeq            143
        //    69: aload           6
        //    71: aload_1        
        //    72: ifeq            143
        //    75: new             Lorg/spongepowered/asm/lib/Edge;
        //    78: dup            
        //    79: invokespecial   org/spongepowered/asm/lib/Edge.<init>:()V
        //    82: astore          7
        //    84: aload           7
        //    86: aload           6
        //    88: getfield        org/spongepowered/asm/lib/Label.inputStackTop:I
        //    91: putfield        org/spongepowered/asm/lib/Edge.info:I
        //    94: aload           7
        //    96: aload_1        
        //    97: getfield        org/spongepowered/asm/lib/Label.successors:Lorg/spongepowered/asm/lib/Edge;
        //   100: getfield        org/spongepowered/asm/lib/Edge.successor:Lorg/spongepowered/asm/lib/Label;
        //   103: putfield        org/spongepowered/asm/lib/Edge.successor:Lorg/spongepowered/asm/lib/Label;
        //   106: aload           7
        //   108: aload           6
        //   110: getfield        org/spongepowered/asm/lib/Label.successors:Lorg/spongepowered/asm/lib/Edge;
        //   113: putfield        org/spongepowered/asm/lib/Edge.next:Lorg/spongepowered/asm/lib/Edge;
        //   116: aload           6
        //   118: aload           7
        //   120: putfield        org/spongepowered/asm/lib/Label.successors:Lorg/spongepowered/asm/lib/Edge;
        //   123: goto            143
        //   126: aload           6
        //   128: lload_2        
        //   129: ifeq            135
        //   132: goto            3
        //   135: aload           6
        //   137: lload_2        
        //   138: iload           4
        //   140: invokevirtual   org/spongepowered/asm/lib/Label.addToSubroutine:(JI)V
        //   143: aload           6
        //   145: getfield        org/spongepowered/asm/lib/Label.successors:Lorg/spongepowered/asm/lib/Edge;
        //   148: astore          7
        //   150: aload           7
        //   152: ifnull          218
        //   155: aload           6
        //   157: getfield        org/spongepowered/asm/lib/Label.status:I
        //   160: sipush          128
        //   163: iand           
        //   164: ifeq            180
        //   167: aload           7
        //   169: aload           6
        //   171: getfield        org/spongepowered/asm/lib/Label.successors:Lorg/spongepowered/asm/lib/Edge;
        //   174: getfield        org/spongepowered/asm/lib/Edge.next:Lorg/spongepowered/asm/lib/Edge;
        //   177: if_acmpeq       208
        //   180: aload           7
        //   182: getfield        org/spongepowered/asm/lib/Edge.successor:Lorg/spongepowered/asm/lib/Label;
        //   185: getfield        org/spongepowered/asm/lib/Label.next:Lorg/spongepowered/asm/lib/Label;
        //   188: ifnonnull       208
        //   191: aload           7
        //   193: getfield        org/spongepowered/asm/lib/Edge.successor:Lorg/spongepowered/asm/lib/Label;
        //   196: aload           5
        //   198: putfield        org/spongepowered/asm/lib/Label.next:Lorg/spongepowered/asm/lib/Label;
        //   201: aload           7
        //   203: getfield        org/spongepowered/asm/lib/Edge.successor:Lorg/spongepowered/asm/lib/Label;
        //   206: astore          5
        //   208: aload           7
        //   210: getfield        org/spongepowered/asm/lib/Edge.next:Lorg/spongepowered/asm/lib/Edge;
        //   213: astore          7
        //   215: goto            150
        //   218: goto            3
        //   221: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0003 (coming from #0218).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}
