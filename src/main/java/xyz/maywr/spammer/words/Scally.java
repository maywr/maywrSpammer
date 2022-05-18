package xyz.maywr.spammer.words;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Scally {

    private final static List<String> scallys = new ArrayList<>();
    private final static Random random = new Random();

    static {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Swears.class.getClassLoader().getResourceAsStream("assets/words/scally.txt"))));
            String s;
            while ((s = reader.readLine()) != null) {
                scallys.add(new String(s.getBytes("windows-1251"), StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getScallyLine() {
        return scallys.get(random.nextInt(scallys.size()));
    }
}
