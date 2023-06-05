//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping.mcp;

import org.spongepowered.tools.obfuscation.mapping.common.*;
import javax.annotation.processing.*;
import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.tools.obfuscation.mapping.*;
import java.io.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class MappingWriterSrg extends MappingWriter
{
    public MappingWriterSrg(final Messager messager, final Filer filer) {
        super(messager, filer);
    }
    
    public void write(final String s, final ObfuscationType obfuscationType, final IMappingConsumer.MappingSet set, final IMappingConsumer.MappingSet set2) {
        if (s == null) {
            return;
        }
        PrintWriter openFileWriter = null;
        try {
            openFileWriter = this.openFileWriter(s, obfuscationType + " output SRGs");
            this.writeFieldMappings(openFileWriter, set);
            this.writeMethodMappings(openFileWriter, set2);
            if (openFileWriter != null) {
                try {
                    openFileWriter.close();
                }
                catch (Exception ex2) {}
            }
            return;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            if (openFileWriter != null) {
                try {
                    openFileWriter.close();
                }
                catch (Exception ex3) {}
            }
            return;
        }
        throw;
    }
    
    protected void writeFieldMappings(final PrintWriter printWriter, final IMappingConsumer.MappingSet set) {
        final Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            printWriter.println(this.formatFieldMapping(iterator.next()));
        }
    }
    
    protected void writeMethodMappings(final PrintWriter printWriter, final IMappingConsumer.MappingSet set) {
        final Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            printWriter.println(this.formatMethodMapping(iterator.next()));
        }
    }
    
    protected String formatFieldMapping(final IMappingConsumer.MappingSet.Pair pair) {
        return String.format("FD: %s/%s %s/%s", ((MappingField)pair.from).getOwner(), ((MappingField)pair.from).getName(), ((MappingField)pair.to).getOwner(), ((MappingField)pair.to).getName());
    }
    
    protected String formatMethodMapping(final IMappingConsumer.MappingSet.Pair pair) {
        return String.format("MD: %s %s %s %s", ((MappingMethod)pair.from).getName(), ((MappingMethod)pair.from).getDesc(), ((MappingMethod)pair.to).getName(), ((MappingMethod)pair.to).getDesc());
    }
}
