package fytyr.idea_projects.course_java.view;

import fytyr.idea_projects.course_java.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTextField textFieldIn;
    private JTextField textFieldOut;
    private JComboBox comboIn;
    private JComboBox comboOut;
    private JButton button;
    private Controller controller;

    public View() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Перевод температуры");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            int width = 700;
            int height = 200;
            frame.setSize(width, height);
            frame.setMinimumSize(new Dimension(width, height));

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - width) / 2;
            int y = (screenSize.height - height) / 2;

            frame.setBounds(x, y, width, height);
            frame.setVisible(true);

            textFieldIn = new JTextField("00.00", 6);
            textFieldIn.setHorizontalAlignment(JTextField.CENTER);

            textFieldOut = new JTextField("00.00", 6);
            textFieldOut.setHorizontalAlignment(JTextField.CENTER);
            textFieldOut.setEnabled(false);

            String[] elements = new String[]{"Фаренгейт", "Цельсий", "Кельвин",};
            comboIn = new JComboBox<>(elements);
            comboOut = new JComboBox<>(elements);

            button = new JButton("Перевести");
            button.setBorderPainted(false);
            button.addActionListener(e -> controller.initModel());

            JPanel panelLeft = new JPanel(new BorderLayout());
            panelLeft.setBorder(new EmptyBorder(60, 30, 60, 30));
            frame.add(panelLeft, BorderLayout.LINE_START);

            panelLeft.add(textFieldIn, BorderLayout.LINE_START);
            panelLeft.add(comboIn);

            JPanel panelCenter = new JPanel(new BorderLayout());
            panelCenter.setBorder(new EmptyBorder(60, 30, 60, 30));

            frame.add(panelCenter, BorderLayout.CENTER);
            panelCenter.add(button);

            JPanel panelRight = new JPanel(new BorderLayout());
            panelRight.setBorder(new EmptyBorder(60, 30, 60, 30));
            frame.add(panelRight, BorderLayout.LINE_END);

            panelRight.add(textFieldOut, BorderLayout.LINE_START);
            panelRight.add(comboOut);
        });
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

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showWarningMessage() {
        JOptionPane.showMessageDialog(frame,
                "Введенные данные должны быть в числовом формате",
                "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
