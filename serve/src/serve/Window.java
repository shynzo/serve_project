package serve;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField txtIP;
	private JTextField txtPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Test_IP a = new Test_IP();
		a.setIp();
		frame = new JFrame();
		frame.setBounds(100, 100, 204, 208);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 168, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP Address");
		lblNewLabel.setBounds(53, 11, 52, 14);
		panel.add(lblNewLabel);
		
		txtIP = new JTextField();
		txtIP.setBounds(21, 27, 119, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);
		txtIP.setText(a.getIp());
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(68, 50, 20, 14);
		panel.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setBounds(39, 66, 86, 20);
		panel.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a;
				a = Integer.parseInt(txtPort.getText());
				ConnectServer serv = new ConnectServer(a);
			}
		});
		btnConnect.setBounds(48, 122, 89, 23);
		frame.getContentPane().add(btnConnect);
	} 
}
