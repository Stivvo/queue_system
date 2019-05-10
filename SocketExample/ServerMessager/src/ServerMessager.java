import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Semaphore;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerMessager {

	private JFrame frmServerProgram;
	private JTextField textField;
	private JTextArea textArea;
	private ServerSocket serverSocket;
	private Socket socket;
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
					ServerMessager window = new ServerMessager();
					window.frmServerProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ServerMessager() throws IOException {
		initialize();
		serverSocket = new ServerSocket(4999);
		socket = serverSocket.accept();
		mutex = new Semaphore(0);
		send = new sender(textField, socket.getOutputStream(), mutex);
		receive = new receiver(textArea, socket.getInputStream());
		
		send.start();
		receive.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServerProgram = new JFrame();
		frmServerProgram.setTitle("Server Program");
		frmServerProgram.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				send.interrupt();
				receive.interrupt();
				try {
					socket.close();
					serverSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		frmServerProgram.setBounds(100, 100, 450, 300);
		frmServerProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerProgram.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(40);
		textArea.setBounds(34, 33, 189, 162);
		frmServerProgram.getContentPane().add(textArea);
		
		textField = new JTextField();
		textField.setBounds(286, 74, 108, 20);
		frmServerProgram.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mutex.release();
			}
		});
		btnSend.setBounds(298, 138, 89, 23);
		frmServerProgram.getContentPane().add(btnSend);
	}

}
