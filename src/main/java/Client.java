import com.google.gson.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 8989;
        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String questionFromServer = in.readLine();
            System.out.println(questionFromServer);
            String answerToServer = scanner.nextLine();
            out.println(answerToServer);
            String uglyJsonFromServer = in.readLine();
            System.out.println(getJson(uglyJsonFromServer));
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String getJson(String uglyJson) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(uglyJson);
        return gson.toJson(jsonElement);
    }
}