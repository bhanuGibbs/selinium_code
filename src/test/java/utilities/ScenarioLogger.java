package utilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ScenarioLogger {

    // Buffer to capture logs per scenario
    private static final ThreadLocal<ByteArrayOutputStream> logStream = ThreadLocal.withInitial(ByteArrayOutputStream::new);
    private static final ThreadLocal<PrintStream> printStream = ThreadLocal.withInitial(() -> new PrintStream(logStream.get()));

    // Get the PrintStream to redirect logs
    public static PrintStream getPrintStream() {
        return printStream.get();
    }

    // Get captured logs as string
    public static String getLogs() {
        return logStream.get().toString();
    }

    // Clear logs before next scenario
    public static void clearLogs() {
        logStream.get().reset();
    }
}