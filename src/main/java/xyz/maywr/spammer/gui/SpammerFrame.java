package xyz.maywr.spammer.gui;

import xyz.maywr.spammer.Main;
import xyz.maywr.spammer.SpamThread;
import xyz.maywr.spammer.language.Strings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SpammerFrame extends JFrame {


    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int WIDTH = 405, HEIGHT = 355;

    public static JButton startButton;

    @SuppressWarnings( "deprecation" )
    public SpammerFrame() {
        super(Main.getTitle());
        try {
            this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/z.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBounds(screenSize.width / 2 - (WIDTH / 2), screenSize.height / 2 - (HEIGHT / 2) , WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JLabel countLabel = new JLabel(Strings.get("msgcount"));
        countLabel.setBounds(10, 205, 180, 25);
        font(countLabel);
        this.add(countLabel);

        JSlider msgCount = new JSlider(5, 70);
        msgCount.setValue(35);
        msgCount.setBounds(5, 225, 375, 40);
        msgCount.setMajorTickSpacing(5);
        msgCount.setPaintTrack(true);
        //msgCount.setPaintTicks(true);
        msgCount.setPaintLabels(true);
        font(msgCount);
        this.add(msgCount);

        JTextArea customText = new JTextArea(Strings.get("customtext"));
        customText.setBounds(170, 20, 210, 175);
        customText.setLineWrap(true);
        customText.setWrapStyleWord(true);
        font(customText);
        this.add(customText);

        JTextField pingField = new JTextField("@everyone");
        pingField.setBounds(10, 169, 150, 25);
        pingField.setEditable(false);
        font(pingField);
        this.add(pingField);

        JCheckBox pingEnabled = new JCheckBox(Strings.get("enabled"));
        pingEnabled.setBounds(7, 144, 130, 25);
        pingEnabled.addActionListener(e -> pingField.setEditable(pingEnabled.isSelected()));
        font(pingEnabled);
        this.add(pingEnabled);


        JLabel pingLabel = new JLabel(Strings.get("ping"));
        pingLabel.setBounds(10, 125, 90, 25);
        font(pingLabel);
        this.add(pingLabel);

        JLabel modeLabel = new JLabel(Strings.get("mode"));
        modeLabel.setBounds(10, 47, 90, 25);
        font(modeLabel);
        this.add(modeLabel);

        JComboBox<String> swearMode = new JComboBox<>(new String[] {Strings.get("defaultswear"), Strings.get("longswear")});
        swearMode.setBounds(10, 100, 150, 25);
        font(swearMode);
        this.add(swearMode);

        JComboBox<String> mode = new JComboBox<>(new String[] {Strings.get("swear"), Strings.get("customtext"), Strings.get("scally")});
        mode.setBounds(10, 70, 150, 25);
        font(mode);
        mode.addItemListener(e -> {
            swearMode.setEnabled(mode.getSelectedIndex() == 0);
        });
        this.add(mode);

        JLabel delayLabel = new JLabel(Strings.get("delay"));
        delayLabel.setBounds(10, 20, 90, 25);
        font(delayLabel);
        this.add(delayLabel);

        JSpinner delayChooser = new JSpinner(new SpinnerNumberModel(100, 30, 10000, 20));
        delayChooser.setBounds(92, 20, 70, 25);
        font(delayChooser);
        this.add(delayChooser);

        startButton = new JButton(Strings.get("start"));
        startButton.setBounds(5, 275, 375, 25);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                SpamThread spamThread = new SpamThread((Integer)delayChooser.getValue(),
                        mode.getSelectedIndex() == 0, swearMode.getSelectedIndex() == 1 ,mode.getSelectedIndex() == 2,
                        pingEnabled.isSelected(), pingField.getText(),
                        customText.getText(), msgCount.getValue());
                if (startButton.getText().equals(Strings.get("start"))) {
                    JOptionPane.showMessageDialog(null, Strings.get("spamstart"), Main.getTitle(), JOptionPane.PLAIN_MESSAGE);
                    spamThread.start();
                    startButton.setText(Strings.get("stop"));
                } else if (startButton.getText().equals(Strings.get("stop"))) {
                    spamThread.stop();
                    startButton.setText(Strings.get("start"));
                }
            }
        });
        font(startButton);
        this.add(startButton);
    }

    public void font(Component component) {
        component.setFont(Main.montserrat);
    }
}
