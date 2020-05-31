package br.com.distribuida.mutualexclusion.node;

import br.com.distribuida.mutualexclusion.coordinator.CoordinatorMain;
import br.com.distribuida.mutualexclusion.resource.ResourceMain;

import javax.xml.soap.Node;
import java.io.IOException;

public class NodeMain {
    private final String coordinatorHost = "";
    private final String resourceHost = "";
    private static final Integer ITERATIONS = 50;

//    public NodeMain(final String coordinatorHost, final String resourceHost) {
//        this.coordinatorHost = coordinatorHost;
//        this.resourceHost = resourceHost;
//    }
    public NodeMain(){}

    public void execute() throws IOException, InterruptedException {
        CoordinatorMain coordinator = new CoordinatorMain();
        while(true){
            if(coordinator.lock()){
                System.out.println("bloqueado");
                int i=0;
                while (i<ITERATIONS) {
                    System.out.println(i);
                    Integer lastValue = ResourceMain.readLastValueFile();
                    ResourceMain.writeValue(lastValue + 100);
                    i++;
                }
                coordinator.unlock();
                System.out.println("desbloqueado");
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NodeMain nodeMain = new NodeMain();
        nodeMain.execute();
    }
}
