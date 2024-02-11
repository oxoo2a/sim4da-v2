package org.oxoo2a.sim4da;

import org.junit.jupiter.api.Test;

class TokenRingTest {
    public class TokenRingNode {
        public TokenRingNode( int id, int next_id ) {
            this.id = id;
            this.name = "TokenRingNode" + id;
            this.next_id = next_id;
            nc = new NetworkConnection(name);
        }

        public void engage () {
            nc.engage(this::start);
        }
        private void start() {
            Message m = new Message().add("token", "start");
            try {
                nc.send(m, "TokenRingNode" + next_id);
            }
            catch (UnknownNodeException e) {
                e.printStackTrace();
            }
            m = nc.receive();
            System.out.println(m);
        }
        private final NetworkConnection nc;
        private final String name;
        private final int id;
        private final int next_id;
    }

    @Test
    void testTokenRing() {
        final int ringSize = 5;
        Simulator simulator = Simulator.getInstance();
        TokenRingNode nodes[] = new TokenRingNode[ringSize];
        for (int i = 0; i < ringSize; i++) {
            nodes[i] = new TokenRingNode(i, (i+1) % ringSize);
        }
        for (TokenRingNode n : nodes) {
            n.engage();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simulator.shutdown();
    }
}
