package org.oxoo2a.sim4da;

public class Node {

    public Node ( String name ) {
        nc = new NetworkConnection(name);
    }

    protected void engage ( Runnable node_main ) {
        nc.engage(node_main);
    }

    protected void send ( Message message, String to_node_name ) throws UnknownNodeException {
        nc.send(message, to_node_name);
    }

    protected void sendBlindly ( Message message, String to_node_name ) {
        nc.sendBlindly(message, to_node_name);
    }


    protected void broadcast ( Message message ) {
        nc.send(message);
    }

    protected Message receive () {
        return nc.receive();
    }
    
    protected String NodeName () {
        return nc.NodeName();
    }

    private NetworkConnection nc = null;
}
