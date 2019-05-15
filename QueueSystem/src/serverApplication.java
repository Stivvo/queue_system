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
	
	private JLabel lblCounter1;
	private JLabel lblCounter2;
	private JLabel lblCounter3;
	
	private JLabel lblWaiting1;
	private JLabel lblWaiting2;
	private JLabel lblWaiting3;
	
	private serverDealer thDealerCommunicator;
	private serverCounter thCounterCommunicator;

	public static void main() {
		System.out.println("MAIN serverApplication");
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
		
		thDealerCommunicator = new serverDealer(q, s, lblWaiting1, lblWaiting2, lblWaiting3);
		thCounterCommunicator = new serverCounter(q, s, lblAdministration, lblCommunication, lblPackage,
												lblCounter1, lblCounter2, lblCounter3,
												lblWaiting1, lblWaiting2, lblWaiting3);
		
		JLabel lblAssisting = new JLabel("Assisting");
		lblAssisting.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssisting.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAssisting.setBounds(293, 26, 109, 38);
		frame.getContentPane().add(lblAssisting);
		
		JLabel lblCounterN = new JLabel("Counter N\u00B0");
		lblCounterN.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCounterN.setBounds(437, 26, 115, 38);
		frame.getContentPane().add(lblCounterN);
		
		JLabel lblPeopleInThe = new JLabel("People in the Queue");
		lblPeopleInThe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPeopleInThe.setBounds(580, 26, 198, 38);
		frame.getContentPane().add(lblPeopleInThe);
		
		thDealerCommunicator.start();
		thCounterCommunicator.start();
		
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 26));
		frame.setTitle("Server Application");
		frame.setBounds(100, 100, 804, 439);
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
		lblAdministration.setBounds(303, 58, 77, 62);
		frame.getContentPane().add(lblAdministration);
		
		lblCommunication = new JLabel("0");
		lblCommunication.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommunication.setForeground(Color.BLACK);
		lblCommunication.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCommunication.setBounds(303, 145, 77, 62);
		frame.getContentPane().add(lblCommunication);
		
		lblPackage = new JLabel("0");
		lblPackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackage.setForeground(Color.BLACK);
		lblPackage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPackage.setBounds(303, 240, 77, 62);
		frame.getContentPane().add(lblPackage);
		
		// --- counter ---
		
		lblCounter1 = new JLabel("0");
		lblCounter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter1.setBounds(447, 58, 77, 62);
		frame.getContentPane().add(lblCounter1);
		
		lblCounter2 = new JLabel("0");
		lblCounter2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter2.setBounds(447, 145, 77, 62);
		frame.getContentPane().add(lblCounter2);
		
		lblCounter3 = new JLabel("0");
		lblCounter3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter3.setBounds(447, 240, 77, 62);
		frame.getContentPane().add(lblCounter3);
		
		// --- waiting --- 
		
		lblWaiting1 = new JLabel("0");
		lblWaiting1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting1.setBounds(646, 58, 77, 62);
		frame.getContentPane().add(lblWaiting1);
		
		lblWaiting2 = new JLabel("0");
		lblWaiting2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting2.setBounds(646, 145, 77, 62);
		frame.getContentPane().add(lblWaiting2);
		
		lblWaiting3 = new JLabel("0");
		lblWaiting3.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting3.setBounds(646, 228, 77, 62);
		frame.getContentPane().add(lblWaiting3);
	}
}
