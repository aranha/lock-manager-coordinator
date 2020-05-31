package br.com.distribuida.mutualexclusion;

import br.com.distribuida.mutualexclusion.coordinator.CoordinatorMain;
import br.com.distribuida.mutualexclusion.node.NodeMain;

import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws SocketException {
        CoordinatorMain coordinatorMain = new CoordinatorMain();
        coordinatorMain.start();

        NodeMain nodeMain1 = new NodeMain("127.0.0.1",4501, "1");
        NodeMain nodeMain2 = new NodeMain("127.0.0.1",4502, "2");
        NodeMain nodeMain3 = new NodeMain("127.0.0.1",4503, "3");
        NodeMain nodeMain4 = new NodeMain("127.0.0.1",4504, "4");
        NodeMain nodeMain5 = new NodeMain("127.0.0.1",4505, "5");

        nodeMain1.start();
        nodeMain2.start();
        nodeMain3.start();
        nodeMain4.start();
        nodeMain5.start();
    }
}
