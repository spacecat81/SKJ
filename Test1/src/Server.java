import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        //TCP
        Log.PREFIX = "S";
        Log.log("Starting");
        Log.log("Server port opening");
        ServerSocket serverSocket = new ServerSocket(PORT);
        Log.log("Server port opened");
      //  Thread.sleep(100000);
        Log.log("Server listening and accepting incoming connections " +
                "from the clients on port: " + PORT);
        Socket clientSocket = serverSocket.accept();
        Log.log("Client connected: " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
        Log.log("Streams collecting");
        InputStream in = clientSocket.getInputStream();
        OutputStream out = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(in);
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        Log.log("Streams collected");
        Log.log("Application logic");

        String line = br.readLine();
        Log.log(line);
        line = br.readLine();
        Log.log(line);
        line = br.readLine();
        Log.log(line);
        line = br.readLine();
        Log.log(line);
        bw.write("<body><html>Congratulations</html></body>");
        bw.newLine();
        bw.flush();

        Log.log("Client port closing");
        clientSocket.close();
        Log.log("Client port closed");

        Log.log("Server port closing");
        serverSocket.close();
        Log.log("Server port closed");
        Log.log("Finalizing");
    }
}