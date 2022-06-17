import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class config {

    public static void main(String[] args) throws IOException {

       // Node1 client = new Node1(new Socket("127.0.0.2",8002),new Socket("127.0.0.3",8002));

        Nodes23 server1 = new Nodes23(new ServerSocket(8002),"127.0.0.2");
        Nodes23 server2 = new Nodes23(new ServerSocket(8003),"127.0.0.3");
        Node1 client = new Node1();
//        server1.runServer(3,8002);
//        server2.runServer(5,8005);


//        server1.bind(new InetSocketAddress("127.0.0.1",8002));
//        server2.bind(new InetSocketAddress("127.0.0.1",8002));
        while(true)
        {
            client.runClient();

            if(Math.random() % 2 == 0)
            {
                //sendtp server1
                System.out.println("SRV1");
                client.startConnection("127.0.0.2", 8003);
                System.out.println("Checkpoint server1 ");
                client.getOut().write(client.getValue());
                server1.runServer(3,8002);
                System.out.println(client.getIn().readLine());
                client.stopConnection();


            }
            else
            {
                System.out.println("SRV2");
                client.startConnection("127.0.0.3", 8003);
                System.out.println("Checkpoint server2");
                client.getOut().write(client.getValue());
                server2.runServer(5,8005);
                System.out.println(client.getIn().readLine());
                client.stopConnection();

            }


        }
    }
}
