//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories.suppliers;

import org.junit.experimental.theories.*;
import java.util.*;

public class TestedOnSupplier extends ParameterSupplier
{
    public List getValueSources(final ParameterSignature parameterSignature) {
        final ArrayList<PotentialAssignment> list = new ArrayList<PotentialAssignment>();
        final int[] ints;
        final int[] array = ints = ((TestedOn)parameterSignature.getAnnotation((Class)TestedOn.class)).ints();
        for (int length = ints.length, i = 0; i < length; ++i) {
            list.add(PotentialAssignment.forValue(Arrays.asList(new int[][] { array }).toString(), (Object)ints[i]));
        }
        return list;
    }
}
