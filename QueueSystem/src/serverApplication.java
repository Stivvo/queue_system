import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class serverApplication {

	private JFrame frame;
	
	private queue[] q;
	private Semaforo[] s;
	
	private JLabel lblAdministration;
	private JLabel lblCommunication;
	private JLabel lblPackage;
	
	private JLabel lblCounterNumber1;
	private JLabel lblCounterNumber2;
	private JLabel lblCounterNumber3;
	
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
	public serverApplication() throws IOException 
	{
		initialize();
		q = new queue[3];
		s = new Semaforo[3];
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = new queue();
			s[i] = new Semaforo(1);
		}
		
		thDealerCommunicator = new serverDealer(q, s);
		thCounterCommunicator = new serverCounter(q, s, lblAdministration, lblCommunication, lblPackage,
												lblCounterNumber1, lblCounterNumber2, lblCounterNumber3);
		
		
		
		thDealerCommunicator.start();
		thCounterCommunicator.start();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 26));
		frame.setTitle("Server Application");
		frame.setBounds(100, 100, 600, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblA = new JLabel("Administration");
		lblA.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblA.setHorizontalAlignment(SwingConstants.LEFT);
		lblA.setBounds(70, 58, 187, 62);
		frame.getContentPane().add(lblA);
		
		JLabel lblC = new JLabel("Comunication");
		lblC.setHorizontalAlignment(SwingConstants.LEFT);
		lblC.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblC.setBounds(70, 145, 187, 62);
		frame.getContentPane().add(lblC);
		
		JLabel lblP = new JLabel("Packages");
		lblP.setHorizontalAlignment(SwingConstants.LEFT);
		lblP.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblP.setBounds(70, 240, 187, 62);
		frame.getContentPane().add(lblP);
		
		lblAdministration = new JLabel("0");
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAdministration.setForeground(Color.BLACK);
		lblAdministration.setBounds(324, 58, 77, 62);
		frame.getContentPane().add(lblAdministration);
		
		lblCommunication = new JLabel("0");
		lblCommunication.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommunication.setForeground(Color.BLACK);
		lblCommunication.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCommunication.setBounds(324, 145, 77, 62);
		frame.getContentPane().add(lblCommunication);
		
		lblPackage = new JLabel("0");
		lblPackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackage.setForeground(Color.BLACK);
		lblPackage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPackage.setBounds(324, 240, 77, 62);
		frame.getContentPane().add(lblPackage);
		
		JLabel lblNSportello = new JLabel("N\u00B0 Sportello");
		lblNSportello.setHorizontalAlignment(SwingConstants.CENTER);
		lblNSportello.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNSportello.setBounds(434, 11, 123, 38);
		frame.getContentPane().add(lblNSportello);
		
		lblCounterNumber1 = new JLabel("0");
		lblCounterNumber1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterNumber1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCounterNumber1.setBounds(465, 58, 77, 62);
		frame.getContentPane().add(lblCounterNumber1);
		
		lblCounterNumber2 = new JLabel("0");
		lblCounterNumber2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterNumber2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCounterNumber2.setBounds(465, 145, 77, 62);
		frame.getContentPane().add(lblCounterNumber2);
		
		lblCounterNumber3 = new JLabel("0");
		lblCounterNumber3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounterNumber3.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCounterNumber3.setBounds(465, 240, 77, 62);
		frame.getContentPane().add(lblCounterNumber3);
	}
}
