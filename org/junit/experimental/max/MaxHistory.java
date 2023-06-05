//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.max;

import java.io.*;
import java.util.*;
import org.junit.runner.notification.*;
import org.junit.runner.*;

public class MaxHistory implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final Map fDurations;
    private final Map fFailureTimestamps;
    private final File fHistoryStore;
    
    public static MaxHistory forFolder(final File file) {
        if (file.exists()) {
            try {
                return readHistory(file);
            }
            catch (CouldNotReadCoreException ex) {
                ex.printStackTrace();
                file.delete();
            }
        }
        return new MaxHistory(file);
    }
    
    private static MaxHistory readHistory(final File file) throws CouldNotReadCoreException {
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            final MaxHistory maxHistory = (MaxHistory)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return maxHistory;
        }
        catch (Exception ex) {
            throw new CouldNotReadCoreException((Throwable)ex);
        }
    }
    
    private MaxHistory(final File fHistoryStore) {
        this.fDurations = new HashMap();
        this.fFailureTimestamps = new HashMap();
        this.fHistoryStore = fHistoryStore;
    }
    
    private void save() throws IOException {
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fHistoryStore));
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }
    
    Long getFailureTimestamp(final Description description) {
        return this.fFailureTimestamps.get(description.toString());
    }
    
    void putTestFailureTimestamp(final Description description, final long n) {
        this.fFailureTimestamps.put(description.toString(), n);
    }
    
    boolean isNewTest(final Description description) {
        return !this.fDurations.containsKey(description.toString());
    }
    
    Long getTestDuration(final Description description) {
        return this.fDurations.get(description.toString());
    }
    
    void putTestDuration(final Description description, final long n) {
        this.fDurations.put(description.toString(), n);
    }
    
    public RunListener listener() {
        return new RememberingListener(null);
    }
    
    public Comparator testComparator() {
        return new TestComparator(null);
    }
    
    static void access$000(final MaxHistory maxHistory) throws IOException {
        maxHistory.save();
    }
    
    private class TestComparator implements Comparator
    {
        final MaxHistory this$0;
        
        private TestComparator(final MaxHistory this$0) {
            this.this$0 = this$0;
        }
        
        public int compare(final Description description, final Description description2) {
            if (this.this$0.isNewTest(description)) {
                return -1;
            }
            if (this.this$0.isNewTest(description2)) {
                return 1;
            }
            final int compareTo = this.getFailure(description2).compareTo(this.getFailure(description));
            return (compareTo != 0) ? compareTo : this.this$0.getTestDuration(description).compareTo(this.this$0.getTestDuration(description2));
        }
        
        private Long getFailure(final Description description) {
            final Long failureTimestamp = this.this$0.getFailureTimestamp(description);
            if (failureTimestamp == null) {
                return 0L;
            }
            return failureTimestamp;
        }
        
        public int compare(final Object o, final Object o2) {
            return this.compare((Description)o, (Description)o2);
        }
        
        TestComparator(final MaxHistory maxHistory, final MaxHistory$1 object) {
            this(maxHistory);
        }
    }
    
    private final class RememberingListener extends RunListener
    {
        private long overallStart;
        private Map starts;
        final MaxHistory this$0;
        
        private RememberingListener(final MaxHistory this$0) {
            this.this$0 = this$0;
            this.overallStart = System.currentTimeMillis();
            this.starts = new HashMap();
        }
        
        @Override
        public void testStarted(final Description description) throws Exception {
            this.starts.put(description, System.nanoTime());
        }
        
        @Override
        public void testFinished(final Description description) throws Exception {
            this.this$0.putTestDuration(description, System.nanoTime() - this.starts.get(description));
        }
        
        @Override
        public void testFailure(final Failure failure) throws Exception {
            this.this$0.putTestFailureTimestamp(failure.getDescription(), this.overallStart);
        }
        
        @Override
        public void testRunFinished(final Result result) throws Exception {
            MaxHistory.access$000(this.this$0);
        }
        
        RememberingListener(final MaxHistory maxHistory, final MaxHistory$1 object) {
            this(maxHistory);
        }
    }
}
