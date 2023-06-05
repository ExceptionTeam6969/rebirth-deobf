//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.debug;

import java.io.*;
import java.util.concurrent.*;

public class RuntimeDecompilerAsync extends RuntimeDecompiler implements Runnable, Thread.UncaughtExceptionHandler
{
    private final BlockingQueue queue;
    private final Thread thread;
    private boolean run;
    
    public RuntimeDecompilerAsync(final File file) {
        super(file);
        this.queue = new LinkedBlockingQueue();
        this.run = true;
        (this.thread = new Thread(this, "Decompiler thread")).setDaemon(true);
        this.thread.setPriority(1);
        this.thread.setUncaughtExceptionHandler(this);
        this.thread.start();
    }
    
    public void decompile(final File file) {
        if (this.run) {
            this.queue.offer(file);
        }
        else {
            super.decompile(file);
        }
    }
    
    public void run() {
        while (this.run) {
            try {
                super.decompile((File)this.queue.take());
            }
            catch (InterruptedException ex2) {
                this.run = false;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void uncaughtException(final Thread thread, final Throwable t) {
        this.logger.error("Async decompiler encountered an error and will terminate. Further decompile requests will be handled synchronously. {} {}", new Object[] { t.getClass().getName(), t.getMessage() });
        this.flush();
    }
    
    private void flush() {
        this.run = false;
        File file;
        while ((file = (File)this.queue.poll()) != null) {
            this.decompile(file);
        }
    }
}
