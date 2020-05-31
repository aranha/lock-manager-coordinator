package br.com.distribuida.mutualexclusion.node;

import br.com.distribuida.mutualexclusion.coordinator.CoordinatorMain;

public class NodeMain {
    private final String coordinatorHost;
    private final String resourceHost;
    private static final Integer ITERATIONS = 50;

    public NodeMain(final String coordinatorHost, final String resourceHost) {
        this.coordinatorHost = coordinatorHost;
        this.resourceHost = resourceHost;
    }

    public void execute(){
        CoordinatorMain coordinator = new CoordinatorMain();
        while(true){
            if(coordinator.isResourceLocked()){
                int i=0;
                while (i<ITERATIONS) {

                    i++;
                }
            }
        }
    }
}
