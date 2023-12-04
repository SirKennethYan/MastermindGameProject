package main.java.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Responsible for printing game instructions to the console.
public class GameInstructions {

    public static void printInstructions() {
        try {
            // To obtain an InputStream from the file location. Wraps the InputStream with a
            // BufferedReader to efficiently read text from the file.
            InputStream inputStream = GameInstructions.class.getResourceAsStream("instructions.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}