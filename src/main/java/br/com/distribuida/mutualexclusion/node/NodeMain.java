package br.com.distribuida.mutualexclusion.node;

import br.com.distribuida.mutualexclusion.resource.ResourceMain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class NodeMain extends Thread {
    private final String coordinatorHost;
    private final String id;
    private final int port;
    private final DatagramSocket socket;
    private static final Integer ITERATIONS = 50;

    public NodeMain(final String coordinatorHost, final int port, final String id) throws SocketException {
        this.coordinatorHost = coordinatorHost;
        this.port = port;
        this.socket = new DatagramSocket(port);
        this.id = id;
    }


//    public static void main(String[] args) throws IOException, InterruptedException {
//        NodeMain nodeMain = new NodeMain("127.0.0.1", "");
//        nodeMain.execute(args[1]);
//    }

    public void run() {
        System.out.println("Começou o processo "+id);
        while (true) {
            try {
                System.out.println("Processo "+id+" tentou bloquear");
                if (lock()) {
                    System.out.println("Processo " + id + " bloqueou");
                    int i = 0;
                    while (i < ITERATIONS) {
                        Integer lastValue = ResourceMain.readLastValueFile();
                        ResourceMain.writeValue(lastValue + 100, id, i);
                        i++;
                    }
                    unlock();
                    System.out.println("Processo " + id + " desbloqueou");
                }
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private boolean lock() throws IOException {
        byte[] buffer = "lock".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(coordinatorHost), 4500);
        socket.send(packet);
        buffer = new byte[8192];
        while (true) {
            try {
                packet = new DatagramPacket(buffer, buffer.length);
                socket.setSoTimeout(500);
                socket.receive(packet);
                return Boolean.parseBoolean(new String(packet.getData(), 0, packet.getLength()));
            } catch (IOException e) {
                System.out.print(".");
            }
        }
    }

    private void unlock() throws IOException {
        byte[] buffer = "unlock".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(coordinatorHost), 4500);
        socket.send(packet);
    }
}
