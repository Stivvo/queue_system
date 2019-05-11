import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class serverApplication {

	private JFrame frame;
	
	private queue queueAdmistration;
	private queue queueCommunication;
	private queue queuePackage;
	
	private Semaforo mutexAdministration;
	private Semaforo mutexCommunication;
	private Semaforo mutexPackage;
	
	private JLabel lblAdministration;
	private JLabel lblCommunication;
	private JLabel lblPackage;
	
	private serverDealer thDealerCommunicator;
	private serverCounter thCounterCommunicator;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverApplication window = new serverApplication();
					window.frame.setVisible(true);
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
	public serverApplication() throws IOException {
		initialize();
		queueAdmistration = new queue();
		queueCommunication = new queue();
		queuePackage = new queue();
		
		mutexAdministration = new Semaforo(1);
		mutexCommunication = new Semaforo(1);
		mutexPackage = new Semaforo(1);
		thDealerCommunicator = new serverDealer(queueAdmistration, queueCommunication, queuePackage,
								 				mutexAdministration, mutexCommunication, mutexPackage);
		
		thCounterCommunicator = new serverCounter(queueAdmistration, queueCommunication, queuePackage, 
												mutexAdministration, mutexCommunication, mutexPackage, 
												lblAdministration, lblCommunication, lblPackage);
		
		thDealerCommunicator.start();
		thCounterCommunicator.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(70, 18, 77, 62);
		frame.getContentPane().add(lblA);
		
		JLabel lblC = new JLabel("C");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblC.setBounds(70, 87, 77, 62);
		frame.getContentPane().add(lblC);
		
		JLabel lblP = new JLabel("P");
		lblP.setHorizontalAlignment(SwingConstants.CENTER);
		lblP.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblP.setBounds(70, 167, 77, 62);
		frame.getContentPane().add(lblP);
		
		lblAdministration = new JLabel("0");
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAdministration.setForeground(Color.BLACK);
		lblAdministration.setBounds(257, 18, 77, 62);
		frame.getContentPane().add(lblAdministration);
		
		lblCommunication = new JLabel("0");
		lblCommunication.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommunication.setForeground(Color.BLACK);
		lblCommunication.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCommunication.setBounds(257, 87, 77, 62);
		frame.getContentPane().add(lblCommunication);
		
		lblPackage = new JLabel("0");
		lblPackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackage.setForeground(Color.BLACK);
		lblPackage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPackage.setBounds(257, 167, 77, 62);
		frame.getContentPane().add(lblPackage);
	}
}
