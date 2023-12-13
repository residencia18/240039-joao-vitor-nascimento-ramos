package conversao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Conversao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField textFieldEntrada = new JTextField();
	private JTextField textFieldSaida;
	private double valor;
	private double saida;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversao frame = new Conversao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Conversao() {
		setTitle("Conversão");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 226, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textFieldEntrada.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		textFieldEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldEntrada.setBounds(0, 12, 226, 28);
		contentPane.add(textFieldEntrada);
		textFieldEntrada.setColumns(10);
		
		JLabel lblResultado = new JLabel("");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(24, 141, 160, 27);
		contentPane.add(lblResultado);
		
		textFieldSaida = new JTextField();
		textFieldSaida.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSaida.setBounds(34, 192, 150, 41);
		contentPane.add(textFieldSaida);
		textFieldSaida.setColumns(10);
		textFieldSaida.setEditable(false);
		
		JButton btnCelsiusparaFahrenheit = new JButton("Celsius para Fahrenheit");
		btnCelsiusparaFahrenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = Double.parseDouble(textFieldEntrada.getText().replace(",", "."));
				saida = (valor*9/5)+32;
				lblResultado.setText("Resultado");
				textFieldSaida.setText(String.format("%.2f",saida));
				
			}
		});
		btnCelsiusparaFahrenheit.setBounds(0, 52, 226, 41);
		contentPane.add(btnCelsiusparaFahrenheit);
		
		JButton btnFahrenheitParaCelsius = new JButton("Fahrenheit para Celsius");
		btnFahrenheitParaCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = Double.parseDouble(textFieldEntrada.getText().replace(",", "."));
				saida = (valor - 32) * (5.0 / 9);
				lblResultado.setText("Resultado");
				textFieldSaida.setText(String.format("%.2f",saida));
			}
		});
		btnFahrenheitParaCelsius.setBounds(0, 91, 226, 41);
		contentPane.add(btnFahrenheitParaCelsius);
		

		

	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
