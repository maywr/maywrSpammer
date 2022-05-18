package xyz.maywr.spammer;

import xyz.maywr.spammer.gui.SpammerFrame;
import xyz.maywr.spammer.language.Strings;
import xyz.maywr.spammer.util.RobotUtil;
import xyz.maywr.spammer.words.Scally;
import xyz.maywr.spammer.words.Swears;

import javax.swing.*;

public class SpamThread extends Thread {

    boolean swear, bigSwear, scally, ping;
    String pingWho, customText;
    int count, delay;

    public SpamThread(int delay,
                      boolean swear, boolean bigSwear, boolean scally, boolean ping,
                      String pingWho, String customText, int count) {
        this.count = count;
        this.swear = swear;
        this.bigSwear = bigSwear;
        this.scally = scally;
        this.ping = ping;
        this.pingWho = pingWho;
        this.customText = customText;
        this.delay = delay;
    }

    @Override
    public void run() {
        try { sleep(5000); } catch (InterruptedException ignored) {}

        for (int i = 0; i < count; i++) {
            if (swear) {
                RobotUtil.sendMessage(!ping ? Swears.getSwear(bigSwear) : Swears.getSwear(bigSwear) + " " + pingWho, ping);
            } else if (scally) {
                RobotUtil.sendMessage(!ping ? Scally.getScallyLine() : Scally.getScallyLine() + " " + pingWho, ping);
            } else {
                RobotUtil.sendMessage(!ping ? customText : customText + " " + pingWho, ping);
            }
            try { sleep(delay); } catch (InterruptedException ignored) {}
        }
        JOptionPane.showMessageDialog(null, Strings.get("spamend"), Main.getTitle(), JOptionPane.PLAIN_MESSAGE);
        SpammerFrame.startButton.setText(Strings.get("start"));
    }

}
