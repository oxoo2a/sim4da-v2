package org.ag_syssoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class sim4da {
    private static final String version = "sim4da V2.0";
    public static void main(String[] args) {
        System.setProperty("PID", String.valueOf(ProcessHandle.current().pid())); // Needed for logback
        logger = LoggerFactory.getLogger(sim4da.class);
        System.out.println(version);
        logger.info(version + " - Simulation started.");

        logger.info(version + " - Simulation ended.");
    }

    private static Logger logger;
}