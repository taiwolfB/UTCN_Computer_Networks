import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

//socket server
public class Nodes23 extends Socket {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private SocketAddress socketAddress;
    private ObjectOutputStream  out;
    private BufferedReader in;
    

    public Nodes23(ServerSocket serverSocket, String ip)
    {
        this.serverSocket = serverSocket;
//        this.socketAddress = new InetSocketAddress();
    }


    public void runServer(int multipleOf, int port) throws IOException {

        System.out.println("Entered server checking for multiples of " + multipleOf);
        clientSocket = serverSocket.accept();
        System.out.println("accepted");
        //serverSocket = new ServerSocket(port);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new ObjectOutputStream (clientSocket.getOutputStream());
        clientSocket.getOutputStream().write(12);
//        while(true)
//        {
//            System.out.println("CEAU");
//            String data = in.readLine();
//           // System.out.println(data);
//
////            if(in.read() % multipleOf == 0){
////                out.write("[ACK] from server checking for multiples of " + multipleOf);
////                break;
////            }
//
//        }
//
//        int readValFromClient = in.readInt();
//        if(readValFromClient % multipleOf == 0)
//        {
//                out.writeBytes("[ACK] value = " + readValFromClient + " is multiple of " + multipleOf);
//        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }


}
