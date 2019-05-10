import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.*;
import java.util.concurrent.Semaphore;
import java.io.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class ClientMessager {

	private JFrame frmClientProgram;
	private JTextArea text;
	private JTextField textField;
	private Socket s;
	private Semaphore mutex;
	private sender send;
	private receiver receive;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMessager window = new ClientMessager();
					window.frmClientProgram.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public ClientMessager() throws IOException{
		initialize();
		
		s = new Socket("172.20.10.3", 4999);
		mutex = new Semaphore(0);
		send = new sender(textField, s.getOutputStream(), mutex);
		receive = new receiver(text, s.getInputStream());
		
		send.start();
		receive.start();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientProgram = new JFrame();
		frmClientProgram.setTitle("Client Program");
		frmClientProgram.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				send.interrupt();
				receive.interrupt();
				try {
					
					s.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmClientProgram.setBounds(100, 100, 450, 300);
		frmClientProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientProgram.getContentPane().setLayout(null);
		
		text = new JTextArea();
		text.setEditable(false);
		text.setColumns(40);
		text.setBounds(25, 28, 192, 141);
		frmClientProgram.getContentPane().add(text);
		
		textField = new JTextField();
		textField.setBounds(293, 49, 96, 20);
		frmClientProgram.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mutex.release();
			}
		});
		btnSend.setBounds(300, 110, 89, 23);
		frmClientProgram.getContentPane().add(btnSend);
	}
}
