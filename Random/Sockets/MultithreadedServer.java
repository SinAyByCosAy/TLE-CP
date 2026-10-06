package DPBootcamp.Random.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
//          Wait for a client
            Socket socket = serverSocket.accept();
//          Handle this client with its own thread
            Thread thread = new Thread(() -> handleSocket(socket));
            thread.start();
        }
    }
    public static void handleSocket(Socket socket){
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String msg = new String(buffer, 0, bytesRead);
            msg += " bulla!";
            out.write(msg.getBytes());

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
