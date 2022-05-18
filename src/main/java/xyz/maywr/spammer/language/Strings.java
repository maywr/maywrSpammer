package xyz.maywr.spammer.language;

import xyz.maywr.spammer.words.Swears;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class Strings {

    private static String locale;

    private static final HashMap<String, String> RU = new HashMap<>();
    private static final HashMap<String, String> EN = new HashMap<>();

    static {
        locale = Locale.getDefault().toString().split("_")[0];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Swears.class.getClassLoader().getResourceAsStream("assets/locales/ru.txt"))));
            String s;
            while ((s = reader.readLine()) != null) {
                String[] split = s.split("-");
                RU.put(split[0], split[1]);
            }

            BufferedReader readerEn = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Swears.class.getClassLoader().getResourceAsStream("assets/locales/en.txt"))));
            String sEn;
            while ((sEn = readerEn.readLine()) != null) {
                String[] split = sEn.split("-");
                EN.put(split[0], split[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        try {
            return locale.equals("ru") ? new String(RU.get(key).getBytes("windows-1251"), StandardCharsets.UTF_8) : EN.get(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return EN.get(key);
    }
}
