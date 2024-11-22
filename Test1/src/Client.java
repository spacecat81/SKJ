import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static int PORT = 11111;
    public static String HOST = "172.21.40.125";

    public static void main(String[] args) throws IOException, InterruptedException {
        //TCP
        Log.PREFIX = "Client";
        Log.log("Starting");
        Log.log("Client port opening");
        Socket clientSocket = new Socket(HOST, PORT);
        Log.log("Client port opened");
        Log.log("Streams collecting");
        InputStream in = clientSocket.getInputStream();
        OutputStream out = clientSocket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(in);
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        Log.log("Streams collected");
        Log.log("Application logic");

        bw.write("s27714 iGdRyCRR0z");
        bw.newLine();
        bw.flush();
        //zad1
        Log.log("1st task");
        int evenSum = 0;
        int oddSum = 0;
        int totalSum = 0;
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            Log.log("Received line " + (i + 1) + ": " + line);

            String[] numbers = line.split(" ");
            int num1 = Integer.parseInt(numbers[0]);
            int num2 = Integer.parseInt(numbers[1]);

            if (num1 % 2 == 0) {
                evenSum += num1;
            } else {
                oddSum += num1;
            }
            totalSum += num1;

            if (num2 % 2 == 0) {
                evenSum += num2;
            } else {
                oddSum += num2;
            }
            totalSum += num2;
        }


        Log.log("Sum of even numbers: " + evenSum);
        Log.log("Sum of odd numbers: " + oddSum);
        Log.log("Sum of all numbers: " + totalSum);

        bw.write(String.valueOf(evenSum));
        bw.newLine();
        bw.write(String.valueOf(oddSum));
        bw.newLine();
        bw.write(String.valueOf(totalSum));
        bw.newLine();
        bw.flush();

        String answerFromServer = br.readLine();
        Log.log("Server response: " + answerFromServer);

        // zad2
        Log.log("2nd Task");

        List<Integer> numbers = new ArrayList<>();
        int lastPositiveNumber = -1;

        while (true) {
            String line = br.readLine();
            Log.log("Received number: " + line);

            int num = Integer.parseInt(line);
            if (num < 0) {
                break;
            }
            numbers.add(num);
            lastPositiveNumber = num;
        }

        int count = 0;
        for (int num : numbers) {
            if (num == lastPositiveNumber) {
                count++;
            }
        }
        Log.log("Last positive number: " + lastPositiveNumber + " appears " + count + " times.");

        for (int i = 0; i < count; i++) {
            bw.write(String.valueOf(count));
            bw.newLine();
        }
        bw.flush();

        String answerFromServer2 = br.readLine();
        Log.log("Server response: " + answerFromServer2);

        // zad3
        Log.log("3nd Task");

        boolean isProcessing = true;

        while (isProcessing) {
            String line1 = br.readLine();
            String line2 = br.readLine();
            Log.log("received: " + line1 +" " + line2);

            String[] parts1 = line1.split(" ");
            String[] parts2 = line2.split(" ");
            int num1 = Integer.parseInt(parts1[0]);
            int num2 = Integer.parseInt(parts1[1]);
            int num3 = Integer.parseInt(parts2[0]);
            int num4 = Integer.parseInt(parts2[1]);

            if (num1 < num2 && num2 < num3 && num3 < num4) {
                Log.log("Sequence is ascending");
                bw.write("TAK");
                bw.newLine();
                bw.flush();

                Log.log("Next numbers: ");
            } else {
                Log.log("Sequence is NOT ascending");
                bw.write("NIE");
                bw.newLine();
                bw.flush();
                isProcessing = false;
            }
        }
        bw.flush();

        String answerFromServer3 = br.readLine();
        Log.log("Server response: " + answerFromServer3);


        Log.log("Client port closing");
        clientSocket.close();
        Log.log("Client port closed");


        Log.log("Finalizing");
    }
}