package xyz.maywr.spammer.words;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Swears {

    private final static List<String> swears = new ArrayList<>();
    private final static Random random = new Random();

    static {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Swears.class.getClassLoader().getResourceAsStream("assets/words/shit.txt"))));
            String s;
            while ((s = reader.readLine()) != null) {
                swears.add(new String(s.getBytes("windows-1251"), StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSwear(boolean huge) {
        if (huge) {
            for (String swear : swears) {
                if (swear.length() >= 300) {
                    Collections.shuffle(swears);
                    return swear;
                }
            }
        }
        return swears.get(random.nextInt(swears.size()));
    }
}
