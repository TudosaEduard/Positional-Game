package gui.game;

import javax.swing.*;

public class Settings extends JFrame{
    private JPanel settingsPanel;
    private JComboBox<Double> probabilityBox;
    private JComboBox<String> playerBox;
    private JButton backButton;
    private JSpinner nodesSpinner;

    public int valueNodes = 8;

    public Settings(JFrame frame) {
        super();
        setTitle("Settings");
        setContentPane(settingsPanel);
        setMinimumSize(new java.awt.Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nodesSpinner.setModel(new SpinnerNumberModel(8, 5, 30, 1));
        playerBox.setModel(new DefaultComboBoxModel<>(new String[] {"AI", "Player"}));
        probabilityBox.setModel(new DefaultComboBoxModel<>(new Double[] {0.3, 0.5, 0.8, 1.0}));

        backButton.addActionListener(e -> {
            dispose();
            Menu menu = new Menu(null, valueNodes, (double) probabilityBox.getSelectedItem(), (String) playerBox.getSelectedItem());
        });

        nodesSpinner.addChangeListener(e -> {
            valueNodes = (int) nodesSpinner.getValue();
            System.out.println(valueNodes);
        });

        setVisible(true);
    }



}
