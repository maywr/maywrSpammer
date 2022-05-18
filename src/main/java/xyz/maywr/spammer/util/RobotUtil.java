package xyz.maywr.spammer.util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotUtil {

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void pressKey(Integer... key) {
        for (int k : key) {
            robot.keyPress(k);
        }
        for (int k : key) {
            robot.keyRelease(k);
        }
    }

    public static void copy(String message) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(message), null);
    }

    public static void sendMessage(String message, boolean doubleEnter) {
        try {
            copy(message);
        } catch (Exception e) {
            return;
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        pressKey(KeyEvent.VK_ENTER);
        if (doubleEnter) pressKey(KeyEvent.VK_ENTER);
    }
}
