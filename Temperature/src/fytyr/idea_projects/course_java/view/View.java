package fytyr.idea_projects.course_java.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {
    private JTextField textFieldIn;
    private JTextField textFieldOut;
    private JComboBox comboIn;
    private JComboBox comboOut;
    private JButton button;

    public View() {
        super("Перевод температуры");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textFieldIn = new JTextField("00.00", 6);
        textFieldIn.setHorizontalAlignment(JTextField.CENTER);

        textFieldOut = new JTextField("00.00", 6);
        textFieldOut.setHorizontalAlignment(JTextField.CENTER);
        textFieldOut.setEnabled(false);

        final String[] elements = new String[]{"Фаренгейт", "Цельсий", "Кельвин"};
        comboIn = new JComboBox(elements);
        comboOut = new JComboBox(elements);

        button = new JButton("Перевести");
        button.setBorderPainted(false);

        JPanel panelLeft = new JPanel(new BorderLayout());
        panelLeft.setBorder(new EmptyBorder(60, 30, 60, 30));
        add(panelLeft, BorderLayout.LINE_START);

        panelLeft.add(textFieldIn, BorderLayout.LINE_START);
        panelLeft.add(comboIn);

        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBorder(new EmptyBorder(60, 30, 60, 30));

        add(panelCenter, BorderLayout.CENTER);
        panelCenter.add(button);

        JPanel panelRight = new JPanel(new BorderLayout());
        panelRight.setBorder(new EmptyBorder(60, 30, 60, 30));
        add(panelRight, BorderLayout.LINE_END);

        panelRight.add(textFieldOut, BorderLayout.LINE_START);
        panelRight.add(comboOut);

        setSize(700, 200);
        setVisible(true);
    }

    public JTextField getTextFieldIn() {
        return textFieldIn;
    }

    public JTextField getTextFieldOut() {
        return textFieldOut;
    }

    public JComboBox getComboIn() {
        return comboIn;
    }

    public JComboBox getComboOut() {
        return comboOut;
    }

    public JButton getButton() {
        return button;
    }

    public void showWarningMessage() {
        JOptionPane.showMessageDialog(this,
                "Введенные данные должны быть в числовом формате",
                "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
