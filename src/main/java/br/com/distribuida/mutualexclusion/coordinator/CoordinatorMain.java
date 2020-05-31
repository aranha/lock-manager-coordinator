package br.com.distribuida.mutualexclusion.coordinator;

public class CoordinatorMain {
    private static final String SERVER_HOST = "127.0.0.1";
    private boolean resourceLocked;

    public CoordinatorMain(){}

    private void setResourceLocked(final boolean resourceLocked){
        this.resourceLocked = resourceLocked;
    }

    public void lock(){
        setResourceLocked(true);
    }

    public void unlock(){
        setResourceLocked(false);
    }

    public boolean isResourceLocked(){
        return this.resourceLocked;
    }
}
