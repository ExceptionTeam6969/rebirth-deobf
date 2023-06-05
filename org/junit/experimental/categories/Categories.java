//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.categories;

import org.junit.runners.*;
import org.junit.runners.model.*;
import org.junit.runner.manipulation.*;
import org.junit.runner.*;
import java.util.*;
import java.lang.annotation.*;

public class Categories extends Suite
{
    public Categories(final Class clazz, final RunnerBuilder runnerBuilder) throws InitializationError {
        super(clazz, runnerBuilder);
        try {
            this.filter(new CategoryFilter(this.getIncludedCategory(clazz), this.getExcludedCategory(clazz)));
        }
        catch (NoTestsRemainException ex) {
            throw new InitializationError(ex);
        }
        this.assertNoCategorizedDescendentsOfUncategorizeableParents(this.getDescription());
    }
    
    private Class getIncludedCategory(final Class clazz) {
        final IncludeCategory includeCategory = clazz.getAnnotation(IncludeCategory.class);
        return (includeCategory == null) ? null : includeCategory.value();
    }
    
    private Class getExcludedCategory(final Class clazz) {
        final ExcludeCategory excludeCategory = clazz.getAnnotation(ExcludeCategory.class);
        return (excludeCategory == null) ? null : excludeCategory.value();
    }
    
    private void assertNoCategorizedDescendentsOfUncategorizeableParents(final Description description) throws InitializationError {
        if (description != 0) {
            this.assertNoDescendantsHaveCategoryAnnotations(description);
        }
        final Iterator<Description> iterator = description.getChildren().iterator();
        while (iterator.hasNext()) {
            this.assertNoCategorizedDescendentsOfUncategorizeableParents(iterator.next());
        }
    }
    
    private void assertNoDescendantsHaveCategoryAnnotations(final Description description) throws InitializationError {
        for (final Description description2 : description.getChildren()) {
            if (description2.getAnnotation(Category.class) != null) {
                throw new InitializationError("Category annotations on Parameterized classes are not supported on individual methods.");
            }
            this.assertNoDescendantsHaveCategoryAnnotations(description2);
        }
    }
    
    public static class CategoryFilter extends Filter
    {
        private final Class fIncluded;
        private final Class fExcluded;
        
        public static CategoryFilter include(final Class clazz) {
            return new CategoryFilter(clazz, null);
        }
        
        public CategoryFilter(final Class fIncluded, final Class fExcluded) {
            this.fIncluded = fIncluded;
            this.fExcluded = fExcluded;
        }
        
        @Override
        public String describe() {
            return "category " + this.fIncluded;
        }
        
        private List categories(final Description description) {
            final ArrayList list = new ArrayList();
            list.addAll(Arrays.asList((Class[])this.directCategories(description)));
            list.addAll(Arrays.asList((Class[])this.directCategories(this.parentDescription(description))));
            return list;
        }
        
        private Description parentDescription(final Description description) {
            final Class testClass = description.getTestClass();
            if (testClass == null) {
                return null;
            }
            return Description.createSuiteDescription(testClass);
        }
        
        private Class[] directCategories(final Description description) {
            if (description == null) {
                return new Class[0];
            }
            final Category category = (Category)description.getAnnotation(Category.class);
            if (category == null) {
                return new Class[0];
            }
            return category.value();
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExcludeCategory {
        Class value();
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IncludeCategory {
        Class value();
    }
}
