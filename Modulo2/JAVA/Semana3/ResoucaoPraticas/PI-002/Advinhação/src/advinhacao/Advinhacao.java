package advinhacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;

public class Advinhacao extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField palpiteTextField;
    private JLabel dicaLabel;

    private int numeroAleatorio;
    private int tentativas;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Advinhacao frame = new Advinhacao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Advinhacao() {
    	setTitle("Jogo da advinhação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 329, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        palpiteTextField = new JTextField();
        palpiteTextField.setBounds(121, 53, 86, 20);
        panel.add(palpiteTextField);
        palpiteTextField.setColumns(10);

        JButton btnAdivinhar = new JButton("Adivinhar");
        btnAdivinhar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarPalpite();
            }
        });
        btnAdivinhar.setBounds(107, 84, 112, 23);
        panel.add(btnAdivinhar);

        dicaLabel = new JLabel("Tente adivinhar o número!");
        dicaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dicaLabel.setBounds(23, 121, 284, 14);
        panel.add(dicaLabel);
        
        JLabel lblNewLabel = new JLabel("Tente advinhar um numero de 1 a 100");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(12, 12, 285, 14);
        panel.add(lblNewLabel);

        reiniciarJogo();

    }

    private void reiniciarJogo() {
        Random random = new Random();
        numeroAleatorio = random.nextInt(100) + 1;
        tentativas = 0;
        dicaLabel.setText("Tente adivinhar o número!");
    }

    private void verificarPalpite() {
        try {
            int palpite = Integer.parseInt(palpiteTextField.getText());
            tentativas++;

            if (palpite < numeroAleatorio) {
                dicaLabel.setText("Muito baixo! Tente novamente.");
            } else if (palpite > numeroAleatorio) {
                dicaLabel.setText("Muito alto! Tente novamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Parabéns! Você acertou em " + tentativas + " tentativas.");
                reiniciarJogo();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.");
        }
    }
}
