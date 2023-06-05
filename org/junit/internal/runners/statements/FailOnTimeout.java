//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.statements;

import org.junit.runners.model.*;

public class FailOnTimeout extends Statement
{
    private final Statement fOriginalStatement;
    private final long fTimeout;
    
    public FailOnTimeout(final Statement fOriginalStatement, final long fTimeout) {
        this.fOriginalStatement = fOriginalStatement;
        this.fTimeout = fTimeout;
    }
    
    @Override
    public void evaluate() throws Throwable {
        final StatementThread evaluateStatement = this.evaluateStatement();
        if (!StatementThread.access$000(evaluateStatement)) {
            this.throwExceptionForUnfinishedThread(evaluateStatement);
        }
    }
    
    private StatementThread evaluateStatement() throws InterruptedException {
        final StatementThread statementThread = new StatementThread(this.fOriginalStatement);
        statementThread.start();
        statementThread.join(this.fTimeout);
        statementThread.interrupt();
        return statementThread;
    }
    
    private void throwExceptionForUnfinishedThread(final StatementThread statementThread) throws Throwable {
        if (StatementThread.access$100(statementThread) != null) {
            throw StatementThread.access$100(statementThread);
        }
        this.throwTimeoutException(statementThread);
    }
    
    private void throwTimeoutException(final StatementThread statementThread) throws Exception {
        final Exception ex = new Exception(String.format("test timed out after %d milliseconds", this.fTimeout));
        ex.setStackTrace(statementThread.getStackTrace());
        throw ex;
    }
    
    private static class StatementThread extends Thread
    {
        private final Statement fStatement;
        private boolean fFinished;
        private Throwable fExceptionThrownByOriginalStatement;
        
        public StatementThread(final Statement fStatement) {
            this.fFinished = false;
            this.fExceptionThrownByOriginalStatement = null;
            this.fStatement = fStatement;
        }
        
        @Override
        public void run() {
            try {
                this.fStatement.evaluate();
                this.fFinished = true;
            }
            catch (InterruptedException ex) {}
            catch (Throwable fExceptionThrownByOriginalStatement) {
                this.fExceptionThrownByOriginalStatement = fExceptionThrownByOriginalStatement;
            }
        }
        
        static boolean access$000(final StatementThread statementThread) {
            return statementThread.fFinished;
        }
        
        static Throwable access$100(final StatementThread statementThread) {
            return statementThread.fExceptionThrownByOriginalStatement;
        }
    }
}
