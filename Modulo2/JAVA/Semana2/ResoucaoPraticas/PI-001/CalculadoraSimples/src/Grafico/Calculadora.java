package Grafico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtField1;
	private JButton btnSomar;
	private JButton btnSubtrair;
	private JButton btnMultipicar;
	private JButton btnDividir;
	private JButton btnValor3;
	private JButton btnValor6;
	private JButton btnValor9;
	private JButton btnResultado;
	private JButton btnValor2;
	private JButton btnValor5;
	private JButton btnValor8;
	private JButton btnValor0;
	private JButton btnValor1;
	private JButton btnValor4;
	private JButton btnValor7;
	private JButton btnPonto;
	private double valor1 = 0;
	private double valor2 = 0;
	private double resultado = 0;
	private String operacao= "";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora frame = new Calculadora();
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
	public Calculadora() {
		

		
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtField1 = new JTextField();
		txtField1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtField1.setFont(new Font("aakar", Font.BOLD, 18));
		txtField1.setBackground(new Color(255, 255, 255));
		txtField1.setBounds(0, 12, 268, 40);
		contentPane.add(txtField1);
		txtField1.setColumns(10);
		
		
		JButton btnLimpar = new JButton("C");
		btnLimpar.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtField1.setText("");
			}
		});
		btnLimpar.setBounds(201, 62, 67, 57);
		contentPane.add(btnLimpar);
		
		btnSomar = new JButton("+");
		btnSomar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setValor1(Double.parseDouble(txtField1.getText().replace(",", ".")));
				txtField1.setText("");
				operacao = "+";
				
			}
		});
		btnSomar.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnSomar.setForeground(new Color(165, 29, 45));
		btnSomar.setBounds(201, 119, 67, 57);
		contentPane.add(btnSomar);
		
		btnSubtrair = new JButton("-");
		btnSubtrair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setValor1(Double.parseDouble(txtField1.getText().replace(",", ".")));
				txtField1.setText("");
				operacao = "-";
			}
		});
		btnSubtrair.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnSubtrair.setForeground(new Color(165, 29, 45));
		btnSubtrair.setBounds(201, 176, 67, 57);
		contentPane.add(btnSubtrair);
		
		btnMultipicar = new JButton("x");
		btnMultipicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setValor1(Double.parseDouble(txtField1.getText().replace(",", ".")));
				txtField1.setText("");
				operacao = "*";
			}
		});
		btnMultipicar.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnMultipicar.setForeground(new Color(165, 29, 45));
		btnMultipicar.setBounds(201, 233, 67, 57);
		contentPane.add(btnMultipicar);
		
		btnDividir = new JButton("/");
		btnDividir.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnDividir.setForeground(new Color(165, 29, 45));
		btnDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setValor1(Double.parseDouble(txtField1.getText().replace(",", ".")));
				txtField1.setText("");
				operacao = "/";
			}
		});
		btnDividir.setBounds(201, 290, 67, 57);
		contentPane.add(btnDividir);
		
		btnValor3 = new JButton("3");
		btnValor3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor3.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor3.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor3.setBounds(134, 119, 67, 57);
		contentPane.add(btnValor3);
		
		btnValor6 = new JButton("6");
		btnValor6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor6.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor6.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor6.setBounds(134, 176, 67, 57);
		contentPane.add(btnValor6);
		
		btnValor9 = new JButton("9");
		btnValor9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor9.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor9.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor9.setBounds(134, 233, 67, 57);
		contentPane.add(btnValor9);
		
		btnResultado = new JButton("=");
		btnResultado.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnResultado.setForeground(new Color(165, 29, 45));
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!operacao.isEmpty()) { // Verifica se uma operação foi selecionada
		            setValor2(Double.parseDouble(txtField1.getText()));
		            
		            switch (operacao) {
		                case "+":
		                    setResultado(getValor1() + getValor2());
		                    break;
		                case "-":
		                    setResultado(getValor1() - getValor2());
		                    break;
		                case "*":
		                    setResultado(getValor1() * getValor2());
		                    break;
		                case "/":
		                    setResultado(getValor1() / getValor2());
		                    break;
		                default:
		                    break;
		            }
		            
		            setValor1(getResultado());
		            txtField1.setText(String.format("%.2f", getResultado()));
		            operacao = ""; // Limpa a operação após o cálculo
		        }
			}
		});
		btnResultado.setBounds(134, 290, 67, 57);
		contentPane.add(btnResultado);
		
		btnValor2 = new JButton("2");
		btnValor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor2.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor2.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor2.setBounds(67, 119, 67, 57);
		contentPane.add(btnValor2);
		
		btnValor5 = new JButton("5");
		btnValor5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor5.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor5.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor5.setBounds(67, 176, 67, 57);
		contentPane.add(btnValor5);
		
		btnValor8 = new JButton("8");
		btnValor8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor8.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor8.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor8.setBounds(67, 233, 67, 57);
		contentPane.add(btnValor8);
		
		btnValor0 = new JButton("0");
		btnValor0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor0.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor0.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor0.setBounds(67, 290, 67, 57);
		contentPane.add(btnValor0);
		
		btnValor1 = new JButton("1");
		btnValor1.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor1.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor1.setBounds(0, 119, 67, 57);
		contentPane.add(btnValor1);
		
		btnValor4 = new JButton("4");
		btnValor4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor4.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor4.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor4.setBounds(0, 176, 67, 57);
		contentPane.add(btnValor4);
		
		btnValor7 = new JButton("7");
		btnValor7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnValor7.getText();
				txtField1.setText(concatena);
			}
		});
		btnValor7.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnValor7.setBounds(0, 233, 67, 57);
		contentPane.add(btnValor7);
		
		btnPonto = new JButton(".");
		btnPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String concatena = txtField1.getText() + btnPonto.getText();
				txtField1.setText(concatena);
			}
		});
		btnPonto.setFont(new Font("Abyssinica SIL", Font.BOLD, 18));
		btnPonto.setForeground(new Color(165, 29, 45));
		btnPonto.setBounds(0, 290, 67, 57);
		contentPane.add(btnPonto);
	}

	public double getValor1() {
		return valor1;
	}

	public void setValor1(double valor1) {
		this.valor1 = valor1;
	}

	public double getValor2() {
		return valor2;
	}

	public void setValor2(double valor2) {
		this.valor2 = valor2;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
}
