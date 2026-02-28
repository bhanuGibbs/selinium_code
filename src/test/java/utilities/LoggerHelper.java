package utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {

    public static Logger getLogger(Class<?> cls) {
        return LoggerFactory.getLogger(cls);
    }
}