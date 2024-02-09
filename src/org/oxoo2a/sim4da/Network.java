package org.oxoo2a.sim4da;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;
public class Network {

    private Network() {
    }

    private record Node ( NetworkConnection nc, NodeProxy np ) {};
    private final Map<String,Node> nodes = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(Network.class);
    private static Network instance = null;
    public static Network getInstance() {
        if (instance == null) {
            synchronized (Network.class) {
                if (instance == null) {
                    instance = new Network();
                }
            }
        }
        return instance;
    }

    public void registerConnection(NetworkConnection networkConnection) {
        logger.debug("Registering connection for " + networkConnection.NodeName());
        Node n = new Node(networkConnection, new NodeProxy(networkConnection));
        nodes.put(networkConnection.NodeName(), n);
    }

    public int numberOfNodes() {
        return nodes.size();
    }

    public void send ( Message message, NetworkConnection sender, String receiver_name ) throws UnknownNodeException {
        if (!nodes.containsKey(receiver_name)) {
            logger.error("Attempt to send message to non-existent node " + receiver_name);
            throw new UnknownNodeException(receiver_name);
        }
        NodeProxy receiver = nodes.get(receiver_name).np;
        receiver.deliver(message, sender);
        logger.debug("Sending unicast message from " + sender.NodeName() + " to " + receiver_name);
    }

    public void send ( Message message, NetworkConnection sender ) throws UnknownNodeException {
        for (Node n : nodes.values()) {
            if (n.nc != sender) {
                n.np.deliver(message, sender);
            }
        }
        logger.debug("Sending broadcast message from " + sender.NodeName());
    }

    public Message receive(NetworkConnection receiver) {
        Node n = nodes.get(receiver.NodeName());
        Message m = n.np.receive();
        logger.debug("Receiving message for " + receiver.NodeName());
        return m;
    }
}
