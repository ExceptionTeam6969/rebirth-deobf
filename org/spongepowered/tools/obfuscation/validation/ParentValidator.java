//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.validation;

import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;
import javax.lang.model.element.*;

public class ParentValidator extends MixinValidator
{
    public ParentValidator(final IMixinAnnotationProcessor mixinAnnotationProcessor) {
        super(mixinAnnotationProcessor, IMixinValidator.ValidationPass.EARLY);
    }
    
    public boolean validate(final TypeElement typeElement, final AnnotationHandle annotationHandle, final Collection collection) {
        if (typeElement.getEnclosingElement().getKind() != ElementKind.PACKAGE && !typeElement.getModifiers().contains(Modifier.STATIC)) {
            this.error("Inner class mixin must be declared static", (Element)typeElement);
        }
        return true;
    }
}
