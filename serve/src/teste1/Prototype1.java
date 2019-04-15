package teste1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Prototype1 {

	private JFrame frame;
	private JTextField txtPort;
	private List lista;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype1 window = new Prototype1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	
	 */
	
	
	public Prototype1() {
		initialize();
	}
	
	public String mens, aesB, ivB, mend;
	public Decrypt decrypt = new Decrypt();
	public int port;
	public Socket client;
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(38, 66, 46, 14);
		frame.getContentPane().add(lblPorta);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(24, 144, 46, 14);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblSConex = new JLabel("Fechado");
		lblSConex.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSConex.setForeground(Color.RED);
		lblSConex.setBounds(75, 139, 101, 23);
		frame.getContentPane().add(lblSConex);
		
		txtPort = new JTextField();
		txtPort.setBounds(81, 63, 70, 20);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		
		
		
		JButton btnAbrirCon = new JButton("Conectar");
		btnAbrirCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				port = Integer.parseInt(txtPort.getText());					
				lista.add("Esperando conexão com o cliente na porta " + port);
				lblSConex.setText("Aberto");
				lblSConex.setForeground(Color.YELLOW);
				try {
				ServerSocket server = new ServerSocket(port);
				client = server.accept();
				lista.add("Nova conexão com o cliente: " + client.getInetAddress().getHostAddress());
				} catch (IOException e1) { lista.add(e1.toString()); }
				
					btnAbrirCon.setText("Conectado!");
					lblSConex.setText("Conectado");
					lblSConex.setForeground(Color.GREEN);
					DataInputStream in = new DataInputStream(client.getInputStream());
					while(!server.isClosed()) {
						aesB = in.readUTF();
						ivB = in.readUTF();
						mens = in.readUTF();
						try {
							mend = decrypt.decrypt(mens, aesB, ivB);
							lista.add("Cliente: " + mend);
						} catch (Exception e) {
							lista.add(e.toString());
						}
					}
					server.close();
				
			}
		});
		btnAbrirCon.setBounds(41, 94, 110, 23);
		frame.getContentPane().add(btnAbrirCon);
		
		lista = new List();
		lista.setBounds(198, 10, 212, 241);
		frame.getContentPane().add(lista);
		
		
		
	}
}
