//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import java.util.*;
import java.lang.reflect.*;

class NoGenericTypeParametersValidator
{
    private final Method fMethod;
    
    NoGenericTypeParametersValidator(final Method fMethod) {
        this.fMethod = fMethod;
    }
    
    void validate(final List list) {
        final Type[] genericParameterTypes = this.fMethod.getGenericParameterTypes();
        for (int length = genericParameterTypes.length, i = 0; i < length; ++i) {
            this.validateNoTypeParameterOnType(genericParameterTypes[i], list);
        }
    }
    
    private void validateNoTypeParameterOnType(final Type type, final List list) {
        if (type instanceof TypeVariable) {
            list.add(new Exception("Method " + this.fMethod.getName() + "() contains unresolved type variable " + type));
        }
        else if (type instanceof ParameterizedType) {
            this.validateNoTypeParameterOnParameterizedType((ParameterizedType)type, list);
        }
        else if (type instanceof WildcardType) {
            this.validateNoTypeParameterOnWildcardType((WildcardType)type, list);
        }
        else if (type instanceof GenericArrayType) {
            this.validateNoTypeParameterOnGenericArrayType((GenericArrayType)type, list);
        }
    }
    
    private void validateNoTypeParameterOnParameterizedType(final ParameterizedType parameterizedType, final List list) {
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int length = actualTypeArguments.length, i = 0; i < length; ++i) {
            this.validateNoTypeParameterOnType(actualTypeArguments[i], list);
        }
    }
    
    private void validateNoTypeParameterOnWildcardType(final WildcardType wildcardType, final List list) {
        final Type[] upperBounds = wildcardType.getUpperBounds();
        for (int length = upperBounds.length, i = 0; i < length; ++i) {
            this.validateNoTypeParameterOnType(upperBounds[i], list);
        }
        final Type[] lowerBounds = wildcardType.getLowerBounds();
        for (int length2 = lowerBounds.length, j = 0; j < length2; ++j) {
            this.validateNoTypeParameterOnType(lowerBounds[j], list);
        }
    }
    
    private void validateNoTypeParameterOnGenericArrayType(final GenericArrayType genericArrayType, final List list) {
        this.validateNoTypeParameterOnType(genericArrayType.getGenericComponentType(), list);
    }
}
