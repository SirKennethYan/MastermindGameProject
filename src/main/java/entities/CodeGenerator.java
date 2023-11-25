package main.java.entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import main.java.app.MastermindGame;

public class CodeGenerator {
    public static final String API_URL = "https://www.random.org/integers";

    public int[] generateSecretCode() {
        int[] code = new int[MastermindGame.NUM_DIGITS];
        try {
            URL url = new URL(API_URL + "?num=" + MastermindGame.NUM_DIGITS + "&min=" + MastermindGame.MIN_VALUE
                    + "&max=" + MastermindGame.MAX_VALUE +
                    "&col=1&base=10&format=plain&rnd=new");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            for (int i = 0; i < MastermindGame.NUM_DIGITS; i++) {
                code[i] = Integer.parseInt(reader.readLine());
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }

}
