package br.com.distribuida.mutualexclusion.coordinator;

public class CoordinatorMain {
    private static final String SERVER_HOST = "127.0.0.1";
    private boolean resourceLocked = false;

    public boolean lock(){
        if(resourceLocked){
            return false;
        }else{
            resourceLocked = true;
            return true;
        }
    }

    public boolean unlock(){
        resourceLocked = false;
        return true;
    }
}
