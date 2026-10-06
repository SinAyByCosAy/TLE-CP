package DPBootcamp.Random.Sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5054);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        String msg = "Tanay";
        out.write(msg.getBytes());

        byte[] buffer = new byte[1024];
        int readsBuffer = in.read(buffer);
        String res = new String(buffer, 0, readsBuffer);

        System.out.println(res + " : res");
        socket.close();
    }
}
