package br.com.distribuida.mutualexclusion.coordinator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class CoordinatorMain extends Thread{
    private boolean resourceLocked = false;

    private DatagramSocket socket;
    private byte[] buffer;
    private DatagramPacket packet;


    public CoordinatorMain() {
        try {
            this.socket = new DatagramSocket(4500);
            buffer = new byte[4096];
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                packet = new DatagramPacket(buffer, buffer.length);
                socket.setSoTimeout(500);
                socket.receive(packet);
                String response = new String(packet.getData(), 0, packet.getLength());

                if (response.equalsIgnoreCase("lock")){
                    byte[] buffer = String.valueOf(lock()).getBytes();
                    DatagramPacket packetToSend = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                    socket.send(packetToSend);
                }

                if (response.equalsIgnoreCase("unlock")){
                    unlock();
                }
            } catch (IOException e) {}
        }
    }

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
