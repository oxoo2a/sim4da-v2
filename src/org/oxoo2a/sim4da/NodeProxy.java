package org.oxoo2a.sim4da;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Each NodeProxy is associated with a NetworkConnection and is responsible for all the node
// functionality visible to the Network. Adding that functionality to the NetworkConnection
// would add methods that should not be accessible to the actual simulation of a
// distributed algorithm.
public class NodeProxy {
    public NodeProxy ( NetworkConnection nc ) {
        this.nc = nc;
    }

    public void deliver ( Message message, NetworkConnection sender ) {
        synchronized (messages) {
            messages.add(new ReceivedMessage(message, sender));
            messages.notify();
        }
    }

    public Message receive () {
        synchronized (messages) {
            while (messages.isEmpty()) {
                try {
                    messages.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst().message;
        }
    }
    private record ReceivedMessage ( Message message, NetworkConnection sender ) {};

    private final List<ReceivedMessage> messages = Collections.synchronizedList(new ArrayList<>());
    private final NetworkConnection nc;
}
