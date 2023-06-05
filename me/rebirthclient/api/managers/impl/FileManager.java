//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.*;
import java.nio.file.attribute.*;

public class FileManager extends Mod
{
    private final Path config;
    private final Path base;
    
    public Path getMkBaseResolve(final String... array) {
        final Path baseResolve = this.getBaseResolve(array);
        this.createDirectory(baseResolve.getParent());
        return baseResolve;
    }
    
    public Path getBasePath() {
        return this.base;
    }
    
    public Path getBaseResolve(final String... array) {
        final String[] array2 = this.expandPaths(array).toArray(FileManager::lambda$getBaseResolve$0);
        if (array2.length < 1) {
            throw new IllegalArgumentException("missing path");
        }
        return this.lookupPath(this.getBasePath(), array2);
    }
    
    private Path getRoot() {
        return Paths.get("", new String[0]);
    }
    
    public FileManager() {
        this.base = this.getMkDirectory(this.getRoot(), "Rebirth");
        this.config = this.getMkDirectory(this.base, "config");
        this.getMkDirectory(this.base, "pvp");
        final Iterator<Category> iterator = Managers.MODULES.getCategories().iterator();
        if (iterator.hasNext()) {
            this.getMkDirectory(this.config, iterator.next().getName());
            throw null;
        }
    }
    
    public static void appendTextFile(final String s, final String s2) {
        try {
            final Path value = Paths.get(s2, new String[0]);
            Files.write(value, Collections.singletonList(s), StandardCharsets.UTF_8, Files.exists(value, new LinkOption[0]) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        }
        catch (IOException ex) {
            System.out.println("WARNING: Unable to write file: " + s2);
        }
    }
    
    public Path getMkBaseDirectory(final String... array) {
        return this.getMkDirectory(this.getBasePath(), this.expandPaths(array).collect(Collectors.joining(File.separator)));
    }
    
    private String[] expandPath(final String s) {
        return s.split(":?\\\\\\\\|\\/");
    }
    
    private Stream expandPaths(final String... array) {
        return Arrays.stream(array).map((Function<? super String, ?>)this::expandPath).flatMap((Function<? super Object, ? extends Stream<?>>)Arrays::stream);
    }
    
    public Path getCache() {
        return this.getBasePath().resolve("cache");
    }
    
    private Path getMkDirectory(final Path path, final String... array) {
        if (array.length < 1) {
            return path;
        }
        final Path lookupPath = this.lookupPath(path, array);
        this.createDirectory(lookupPath);
        return lookupPath;
    }
    
    private static String[] lambda$getBaseResolve$0(final int n) {
        return new String[n];
    }
    
    public static List readTextFileAllLines(final String s) {
        try {
            return Files.readAllLines(Paths.get(s, new String[0]), StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            System.out.println("WARNING: Unable to read file, creating new file: " + s);
            appendTextFile("", s);
            return Collections.emptyList();
        }
    }
    
    private Path lookupPath(final Path path, final String... array) {
        return Paths.get(path.toString(), array);
    }
    
    public Path getConfig() {
        return this.getBasePath().resolve("config");
    }
    
    public Path getMkConfigDirectory(final String... array) {
        return this.getMkDirectory(this.getConfig(), this.expandPaths(array).collect(Collectors.joining(File.separator)));
    }
    
    private void createDirectory(final Path path) {
        try {
            if (!Files.isDirectory(path, new LinkOption[0])) {
                if (Files.exists(path, new LinkOption[0])) {
                    Files.delete(path);
                }
                Files.createDirectories(path, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
