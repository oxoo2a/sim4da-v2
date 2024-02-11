package org.oxoo2a.sim4da;

public class NetworkConnection {

    protected NetworkConnection(String node_name ) {
        this.node_name = node_name;
        network.registerConnection(this);
    }

    public String NodeName () {
        return node_name;
    }

    public void engage ( Runnable node_main ) {
        thread = new Thread(node_main);
        thread.start();
    }

    public Message receive () {
        return network.receive(this);
    }
    public void send ( Message message, String to_node_name ) throws UnknownNodeException {
        network.send(message, this, to_node_name);
    }
    private final String node_name;
    private final Simulator simulator = Simulator.getInstance();
    private final Network network = Network.getInstance();
    private Thread thread = null;
}
