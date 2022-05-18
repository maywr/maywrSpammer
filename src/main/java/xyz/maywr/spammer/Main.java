package xyz.maywr.spammer;

import com.formdev.flatlaf.FlatDarkLaf;
import xyz.maywr.spammer.gui.SpammerFrame;
import xyz.maywr.spammer.language.Strings;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Main {

    public static Font montserrat;

    private static final String NAME = "maywrSpammer";
    private static final String VERSION = "2.1";
    private static final String TITLE = String.format("%s %s", NAME, VERSION);

    private static SpammerFrame spammerFrame;


    public static void main(String[] args) {

        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Main.class.getClassLoader().getResourceAsStream("assets/font.ttf"));
            UIManager.setLookAndFeel(new FlatDarkLaf());
            montserrat = new Font(font.getFontName(), 0, 14);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, Strings.get("themeerror"), Main.getTitle(), JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
        }

        spammerFrame = new SpammerFrame();
        spammerFrame.setVisible(true);
    }

    public static String getTitle() {
        return TITLE;
    }

}
