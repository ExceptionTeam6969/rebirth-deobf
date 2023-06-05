//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import java.io.*;

public class TemporaryFolder extends ExternalResource
{
    private File folder;
    
    protected void before() throws Throwable {
        this.create();
    }
    
    protected void after() {
        this.delete();
    }
    
    public void create() throws IOException {
        this.folder = this.newFolder();
    }
    
    public File newFile(final String s) throws IOException {
        final File file = new File(this.getRoot(), s);
        file.createNewFile();
        return file;
    }
    
    public File newFile() throws IOException {
        return File.createTempFile("junit", null, this.folder);
    }
    
    public File newFolder(final String... array) {
        File root = this.getRoot();
        for (int length = array.length, i = 0; i < length; ++i) {
            root = new File(root, array[i]);
            root.mkdir();
        }
        return root;
    }
    
    public File newFolder() throws IOException {
        final File tempFile = File.createTempFile("junit", "", this.folder);
        tempFile.delete();
        tempFile.mkdir();
        return tempFile;
    }
    
    public File getRoot() {
        if (this.folder == null) {
            throw new IllegalStateException("the temporary folder has not yet been created");
        }
        return this.folder;
    }
    
    public void delete() {
        this.recursiveDelete(this.folder);
    }
    
    private void recursiveDelete(final File file) {
        final File[] listFiles = file.listFiles();
        if (listFiles != null) {
            final File[] array = listFiles;
            for (int length = array.length, i = 0; i < length; ++i) {
                this.recursiveDelete(array[i]);
            }
        }
        file.delete();
    }
}
