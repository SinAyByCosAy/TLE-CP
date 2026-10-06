package DPBootcamp.Random.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MultithreadedClient {
    public static void main(String[] args) throws IOException{
        for(int i = 1; i <= 100; i++) {
            int currThread = i;
            Thread thread = new Thread(() -> {
                try {
                    Socket socket = new Socket("localhost", 8000);
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
                    String msg = "Tanay";
                    out.write(msg.getBytes());
                    byte[] buffer = new byte[1024];
                    int readBytes = in.read(buffer);
                    String res = new String(buffer, 0, readBytes);
                    System.out.println(res);
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}
