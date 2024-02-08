package org.ag_syssoft;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class APITest {

    @Node(name="Tokenring", n_instances=1)
    public class Tokenring {
        public Tokenring () {
            v = 42;
        }

        private int v;
    }

    @Test
    public void testTokenring() {
        Simulator.sewSimulation();
    }
}
