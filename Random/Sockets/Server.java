package DPBootcamp.Random.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(5054);
        System.out.println("Waiting for client..");

        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        String msg = new String(buffer, 0, bytesRead);

        System.out.println("Received: " + msg);
        String res = msg + "hi";

        out.write(res.getBytes());

        socket.close();
        serverSocket.close();

    }
}
