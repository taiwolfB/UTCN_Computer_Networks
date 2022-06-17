import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class Node1 {


    private SocketAddress socketAddress;
    private Socket socket;
    private BufferedReader in;
    private ObjectOutputStream  out;
    private int value = 0;


//    public Node1(Socket socketServer1, Socket socketServer2) {
//        this.socketServer1 = socketServer1;
//        this.socketServer2 = socketServer2;
//
//    }

    public Node1()
    {

    }


    public void startConnection(String ip, int port) throws IOException {

        socket = new Socket(ip, port);
        out = new ObjectOutputStream (socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
    }
    public void runClient() throws IOException {
        ++value;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public ObjectOutputStream  getOut() {
        return out;
    }

    public int getValue() {
        return value;
    }
}
