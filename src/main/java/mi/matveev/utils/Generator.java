package mi.matveev.utils;

import java.util.Random;

public class Generator {
    public static String generateString(int length) {
        Random random = new Random();
        String availableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder generatedString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            generatedString.append(availableChars.charAt(random.nextInt(availableChars.length())));
        }

        return generatedString.toString();
    }
}
