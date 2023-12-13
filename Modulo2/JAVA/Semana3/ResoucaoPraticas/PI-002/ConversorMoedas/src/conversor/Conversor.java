package conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conversor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField valorDolarTextField;
    private JTextField taxaCambioTextField;
    private JTextField resultadoTextField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conversor frame = new Conversor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Conversor() {
        setTitle("Conversor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 321, 300);

        JPanel conversorPanel = new JPanel();
        getContentPane().add(conversorPanel, BorderLayout.CENTER);
        conversorPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Qtd em dólares:");
        lblNewLabel.setBounds(12, 33, 150, 16);
        conversorPanel.add(lblNewLabel);

        valorDolarTextField = new JTextField();
        valorDolarTextField.setBounds(185, 30, 130, 22);
        conversorPanel.add(valorDolarTextField);
        valorDolarTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Taxa de câmbio:");
        lblNewLabel_1.setBounds(12, 82, 150, 16);
        conversorPanel.add(lblNewLabel_1);

        taxaCambioTextField = new JTextField();
        taxaCambioTextField.setBounds(185, 79, 130, 22);
        conversorPanel.add(taxaCambioTextField);
        taxaCambioTextField.setColumns(10);

        JButton btnNewButton = new JButton("Converter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                converterMoeda();
            }
        });
        btnNewButton.setBounds(12, 128, 303, 25);
        conversorPanel.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("Valor convertido:");
        lblNewLabel_2.setBounds(12, 179, 150, 16);
        conversorPanel.add(lblNewLabel_2);

        resultadoTextField = new JTextField();
        resultadoTextField.setEditable(false);
        resultadoTextField.setBounds(185, 176, 130, 22);
        conversorPanel.add(resultadoTextField);
        resultadoTextField.setColumns(10);
    }

    private void converterMoeda() {
        try {
            double valorDolar = Double.parseDouble(valorDolarTextField.getText().replace(",", "."));
            double taxaCambio = Double.parseDouble(taxaCambioTextField.getText().replace(",", "."));
            double valorConvertido = valorDolar * taxaCambio;

            resultadoTextField.setText(String.format("%.2f", valorConvertido));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos.");
        }
    }
}
