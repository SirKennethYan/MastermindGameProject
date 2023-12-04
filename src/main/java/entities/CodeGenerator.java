package main.java.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import main.java.app.MastermindGame;

// Responsible for obtaining a secret code from an external API.
public class CodeGenerator {
    public static final String API_URL = "https://www.random.org/integers";

    // Generates a secret code by making a request to the external API. Opens
    // connections -> Reads random integers using BufferedReader -> Closes
    // BufferedReader.
    public int[] generateSecretCode() {
        int[] code = new int[MastermindGame.NUM_DIGITS];
        try {
            URL url = buildApiUrl();
            HttpURLConnection connection = openConnection(url);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
                code[i] = parseIntFromReader(reader);
            }

            closeReader(reader);
        } catch (IOException e) {
            handleException(e);
        }

        return code;
    }

    // Constructs the API URL based on the Mastermind game parameters like the
    // number of digits, minimum, and maximum values.
    private URL buildApiUrl() throws IOException {
        return new URL(API_URL + "?num=" + MastermindGame.NUM_DIGITS + "&min=" + MastermindGame.MIN_VALUE
                + "&max=" + MastermindGame.MAX_VALUE + "&col=1&base=10&format=plain&rnd=new");
    }

    // Opens a connection to the API using HttpURLConnection.
    private HttpURLConnection openConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    // Reads an integer from the BufferedReader.
    private int parseIntFromReader(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    // Closes the BufferedReader.
    private void closeReader(BufferedReader reader) throws IOException {
        reader.close();
    }

    // Prints the exception stack trace when an IOException occurs.
    private void handleException(Exception e) {
        e.printStackTrace();
    }
}
