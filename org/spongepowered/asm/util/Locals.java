//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.util.throwables.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.util.asm.*;
import org.spongepowered.asm.lib.tree.analysis.*;
import java.util.*;

public final class Locals
{
    private static final Map calculatedLocalVariables;
    
    private Locals() {
    }
    
    public static void loadLocals(final Type[] array, final InsnList list, int n, int n2) {
        while (n < array.length && n2 > 0) {
            if (array[n] != null) {
                list.add((AbstractInsnNode)new VarInsnNode(array[n].getOpcode(21), n));
                --n2;
            }
            ++n;
        }
    }
    
    public static LocalVariableNode[] getLocalsAt(final ClassNode classNode, final MethodNode methodNode, AbstractInsnNode nextNode) {
        for (int n = 0; n < 3 && (nextNode instanceof LabelNode || nextNode instanceof LineNumberNode); nextNode = nextNode(methodNode.instructions, nextNode), ++n) {}
        final ClassInfo forName = ClassInfo.forName(classNode.name);
        if (forName == null) {
            throw new LVTGeneratorException("Could not load class metadata for " + classNode.name + " generating LVT for " + methodNode.name);
        }
        final ClassInfo.Method method = forName.findMethod(methodNode);
        if (method == null) {
            throw new LVTGeneratorException("Could not locate method metadata for " + methodNode.name + " generating LVT in " + classNode.name);
        }
        final List frames = method.getFrames();
        final LocalVariableNode[] array = new LocalVariableNode[methodNode.maxLocals];
        int n2 = 0;
        int n3 = 0;
        if ((methodNode.access & 0x8) == 0x0) {
            array[n2++] = new LocalVariableNode("this", classNode.name, (String)null, (LabelNode)null, (LabelNode)null, 0);
        }
        for (final Type type : Type.getArgumentTypes(methodNode.desc)) {
            array[n2] = new LocalVariableNode("arg" + n3++, type.toString(), (String)null, (LabelNode)null, (LabelNode)null, n2);
            n2 += type.getSize();
        }
        final int n4 = n2;
        int n5 = -1;
        int n6 = 0;
        for (final AbstractInsnNode abstractInsnNode : methodNode.instructions) {
            if (abstractInsnNode instanceof FrameNode) {
                ++n5;
                final FrameNode frameNode = (FrameNode)abstractInsnNode;
                final ClassInfo.FrameData frameData = (n5 < frames.size()) ? frames.get(n5) : null;
                n6 = ((frameData != null && frameData.type == 0) ? Math.min(n6, frameData.locals) : frameNode.local.size());
                for (int n7 = 0, j = 0; j < array.length; ++j, ++n7) {
                    final Object o = (n7 < frameNode.local.size()) ? frameNode.local.get(n7) : null;
                    if (o instanceof String) {
                        array[j] = getLocalVariableAt(classNode, methodNode, nextNode, j);
                    }
                    else if (o instanceof Integer) {
                        final boolean b = o == Opcodes.UNINITIALIZED_THIS || o == Opcodes.NULL;
                        final boolean b2 = o == Opcodes.INTEGER || o == Opcodes.FLOAT;
                        final boolean b3 = o == Opcodes.DOUBLE || o == Opcodes.LONG;
                        if (o != Opcodes.TOP) {
                            if (b) {
                                array[j] = null;
                            }
                            else {
                                if (!b2 && !b3) {
                                    throw new LVTGeneratorException("Unrecognised locals opcode " + o + " in locals array at position " + n7 + " in " + classNode.name + "." + methodNode.name + methodNode.desc);
                                }
                                array[j] = getLocalVariableAt(classNode, methodNode, nextNode, j);
                                if (b3) {
                                    ++j;
                                    array[j] = null;
                                }
                            }
                        }
                    }
                    else {
                        if (o != null) {
                            throw new LVTGeneratorException("Invalid value " + o + " in locals array at position " + n7 + " in " + classNode.name + "." + methodNode.name + methodNode.desc);
                        }
                        if (j >= n4 && j >= n6 && n6 > 0) {
                            array[j] = null;
                        }
                    }
                }
            }
            else if (abstractInsnNode instanceof VarInsnNode) {
                final VarInsnNode varInsnNode = (VarInsnNode)abstractInsnNode;
                array[varInsnNode.var] = getLocalVariableAt(classNode, methodNode, nextNode, varInsnNode.var);
            }
            if (abstractInsnNode == nextNode) {
                break;
            }
        }
        for (int k = 0; k < array.length; ++k) {
            if (array[k] != null && array[k].desc == null) {
                array[k] = null;
            }
        }
        return array;
    }
    
    public static LocalVariableNode getLocalVariableAt(final ClassNode classNode, final MethodNode methodNode, final AbstractInsnNode abstractInsnNode, final int n) {
        return getLocalVariableAt(classNode, methodNode, methodNode.instructions.indexOf(abstractInsnNode), n);
    }
    
    private static LocalVariableNode getLocalVariableAt(final ClassNode p0, final MethodNode p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload_0        
        //     7: aload_1        
        //     8: invokestatic    org/spongepowered/asm/util/Locals.getLocalVariableTable:(Lorg/spongepowered/asm/lib/tree/ClassNode;Lorg/spongepowered/asm/lib/tree/MethodNode;)Ljava/util/List;
        //    11: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    16: astore          6
        //    18: aload           6
        //    20: invokeinterface java/util/Iterator.hasNext:()Z
        //    25: ifeq            81
        //    28: aload           6
        //    30: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    35: checkcast       Lorg/spongepowered/asm/lib/tree/LocalVariableNode;
        //    38: astore          7
        //    40: aload           7
        //    42: getfield        org/spongepowered/asm/lib/tree/LocalVariableNode.index:I
        //    45: iload_3        
        //    46: if_icmpeq       52
        //    49: goto            18
        //    52: aload_1        
        //    53: getfield        org/spongepowered/asm/lib/tree/MethodNode.instructions:Lorg/spongepowered/asm/lib/tree/InsnList;
        //    56: aload           7
        //    58: iload_2        
        //    59: if_icmpge       69
        //    62: aload           7
        //    64: astore          4
        //    66: goto            78
        //    69: aload           4
        //    71: ifnonnull       78
        //    74: aload           7
        //    76: astore          5
        //    78: goto            18
        //    81: aload           4
        //    83: ifnonnull       158
        //    86: aload_1        
        //    87: getfield        org/spongepowered/asm/lib/tree/MethodNode.localVariables:Ljava/util/List;
        //    90: invokeinterface java/util/List.isEmpty:()Z
        //    95: ifne            158
        //    98: aload_0        
        //    99: aload_1        
        //   100: invokestatic    org/spongepowered/asm/util/Locals.getGeneratedLocalVariableTable:(Lorg/spongepowered/asm/lib/tree/ClassNode;Lorg/spongepowered/asm/lib/tree/MethodNode;)Ljava/util/List;
        //   103: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   108: astore          6
        //   110: aload           6
        //   112: invokeinterface java/util/Iterator.hasNext:()Z
        //   117: ifeq            158
        //   120: aload           6
        //   122: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   127: checkcast       Lorg/spongepowered/asm/lib/tree/LocalVariableNode;
        //   130: astore          7
        //   132: aload           7
        //   134: getfield        org/spongepowered/asm/lib/tree/LocalVariableNode.index:I
        //   137: iload_3        
        //   138: if_icmpne       155
        //   141: aload_1        
        //   142: getfield        org/spongepowered/asm/lib/tree/MethodNode.instructions:Lorg/spongepowered/asm/lib/tree/InsnList;
        //   145: aload           7
        //   147: iload_2        
        //   148: if_icmpge       155
        //   151: aload           7
        //   153: astore          4
        //   155: goto            110
        //   158: aload           4
        //   160: ifnull          168
        //   163: aload           4
        //   165: goto            170
        //   168: aload           5
        //   170: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0155 (coming from #0148).
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
    
    public static List getLocalVariableTable(final ClassNode classNode, final MethodNode methodNode) {
        if (methodNode.localVariables.isEmpty()) {
            return getGeneratedLocalVariableTable(classNode, methodNode);
        }
        return methodNode.localVariables;
    }
    
    public static List getGeneratedLocalVariableTable(final ClassNode classNode, final MethodNode methodNode) {
        final String format = String.format("%s.%s%s", classNode.name, methodNode.name, methodNode.desc);
        final List list = Locals.calculatedLocalVariables.get(format);
        if (list != null) {
            return list;
        }
        final List generateLocalVariableTable = generateLocalVariableTable(classNode, methodNode);
        Locals.calculatedLocalVariables.put(format, generateLocalVariableTable);
        return generateLocalVariableTable;
    }
    
    public static List generateLocalVariableTable(final ClassNode classNode, final MethodNode methodNode) {
        List<Type> list = null;
        if (classNode.interfaces != null) {
            list = new ArrayList<Type>();
            final Iterator<String> iterator = classNode.interfaces.iterator();
            while (iterator.hasNext()) {
                list.add(Type.getObjectType((String)iterator.next()));
            }
        }
        Type objectType = null;
        if (classNode.superName != null) {
            objectType = Type.getObjectType(classNode.superName);
        }
        final Analyzer analyzer = new Analyzer((Interpreter)new MixinVerifier(Type.getObjectType(classNode.name), objectType, (List)list, false));
        try {
            analyzer.analyze(classNode.name, methodNode);
        }
        catch (AnalyzerException ex) {
            ex.printStackTrace();
        }
        final Frame[] frames = analyzer.getFrames();
        final int size = methodNode.instructions.size();
        final ArrayList<LocalVariableNode> list2 = new ArrayList<LocalVariableNode>();
        final LocalVariableNode[] array = new LocalVariableNode[methodNode.maxLocals];
        final BasicValue[] array2 = new BasicValue[methodNode.maxLocals];
        final LabelNode[] array3 = new LabelNode[size];
        final String[] array4 = new String[methodNode.maxLocals];
        for (int i = 0; i < size; ++i) {
            final Frame frame = frames[i];
            if (frame != null) {
                LabelNode labelNode = null;
                for (int j = 0; j < frame.getLocals(); ++j) {
                    final BasicValue basicValue = (BasicValue)frame.getLocal(j);
                    if (basicValue != null || array2[j] != null) {
                        if (basicValue == null || !basicValue.equals((Object)array2[j])) {
                            if (labelNode == null) {
                                final AbstractInsnNode value = methodNode.instructions.get(i);
                                if (value instanceof LabelNode) {
                                    labelNode = (LabelNode)value;
                                }
                                else {
                                    labelNode = (array3[i] = new LabelNode());
                                }
                            }
                            if (basicValue == null && array2[j] != null) {
                                list2.add(array[j]);
                                array[j].end = labelNode;
                                array[j] = null;
                            }
                            else if (basicValue != null) {
                                if (array2[j] != null) {
                                    list2.add(array[j]);
                                    array[j].end = labelNode;
                                    array[j] = null;
                                }
                                final String s = (basicValue.getType() != null) ? basicValue.getType().getDescriptor() : array4[j];
                                array[j] = new LocalVariableNode("var" + j, s, (String)null, labelNode, (LabelNode)null, j);
                                if (s != null) {
                                    array4[j] = s;
                                }
                            }
                            array2[j] = basicValue;
                        }
                    }
                }
            }
        }
        LabelNode end = null;
        for (int k = 0; k < array.length; ++k) {
            if (array[k] != null) {
                if (end == null) {
                    end = new LabelNode();
                    methodNode.instructions.add((AbstractInsnNode)end);
                }
                array[k].end = end;
                list2.add(array[k]);
            }
        }
        for (int l = size - 1; l >= 0; --l) {
            if (array3[l] != null) {
                methodNode.instructions.insert(methodNode.instructions.get(l), (AbstractInsnNode)array3[l]);
            }
        }
        return list2;
    }
    
    private static AbstractInsnNode nextNode(final InsnList list, final AbstractInsnNode abstractInsnNode) {
        final int n = list.indexOf(abstractInsnNode) + 1;
        if (n > 0 && n < list.size()) {
            return list.get(n);
        }
        return abstractInsnNode;
    }
    
    static {
        calculatedLocalVariables = new HashMap();
    }
}
