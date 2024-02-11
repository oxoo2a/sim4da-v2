package org.oxoo2a.sim4da;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Simulator {
    private final String version = "sim4da V2.0";
    private  Simulator () {
        System.setProperty("PID", String.valueOf(ProcessHandle.current().pid())); // Needed for logback
        logger = LoggerFactory.getLogger(sim4da.class);
        System.out.println(version);
        logger.info(version + " - Simulation started.");
    }

    public static Simulator getInstance() {
        if (instance == null) {
            synchronized (Simulator.class) {
                if (instance == null) {
                    instance = new Simulator();
                }
            }
        }
        return instance;
    }

    public void shutdown () {
        logger.info(version + " - Simulation ended.");
    }
    private static Simulator instance = null;
    private final Logger logger;
}