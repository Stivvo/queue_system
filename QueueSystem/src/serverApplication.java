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
	private JLabel[] lblService;
	private JLabel[] lblCounter;	
	private JLabel[] lblWaiting;

	private list working;
	private list sleeping;
	private list counter;
	private Semaforo mutexList;
	private dealerConnector thDealerConnect;
	private counterConnector thCounterConnect;
	private counterCreatorConnector thCounterCreatorConnect;
	private counterSleeper sleeper;
	
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

	public serverApplication() throws IOException 
	{
		
		counter = new list();
		working = new list();
		sleeping = new list();
		mutexList = new Semaforo(1);
		lblService = new JLabel[3];
		lblCounter = new JLabel[3];
		lblWaiting = new JLabel[3];
		initialize();
		q = new queue[3];
		s = new Semaforo[3];
		sleeper = new counterSleeper(working, sleeping, counter, mutexList, s, q);
		q[0] = new queue('A');
		q[1] = new queue('C');
		q[2] = new queue('P');
		
		for (int i = 0; i < 3; i++)
			s[i] = new Semaforo(1);
		
		thDealerConnect = new dealerConnector(q, s, lblWaiting);
		thCounterConnect = new counterConnector(q, s, lblService, lblCounter, lblWaiting, working, counter, mutexList/*, nCounter*/);
		thCounterCreatorConnect = new counterCreatorConnector(counter, mutexList);
		
		JLabel lblAssisting = new JLabel("Assisting");
		lblAssisting.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssisting.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAssisting.setBounds(200, 26, 109, 38);
		frame.getContentPane().add(lblAssisting);
		
		JLabel lblCounterN = new JLabel("Counter N\u00B0");
		lblCounterN.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCounterN.setBounds(350, 26, 115, 38);
		frame.getContentPane().add(lblCounterN);
		
		JLabel lblPeopleInThe = new JLabel("N waiting");
		lblPeopleInThe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPeopleInThe.setBounds(500, 26, 198, 38);
		frame.getContentPane().add(lblPeopleInThe);
		
		thDealerConnect.start();
		thCounterConnect.start();
		thCounterCreatorConnect.start();
		sleeper.start();
		
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 26));
		frame.setTitle("Server Application");
		frame.setBounds(100, 100, 631, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblA = new JLabel("Finance");
		lblA.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblA.setHorizontalAlignment(SwingConstants.LEFT);
		lblA.setBounds(12, 50, 187, 62);
		frame.getContentPane().add(lblA);
		
		JLabel lblC = new JLabel("Comunication");
		lblC.setHorizontalAlignment(SwingConstants.LEFT);
		lblC.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblC.setBounds(12, 105, 187, 62);
		frame.getContentPane().add(lblC);
		
		JLabel lblP = new JLabel("Packages");
		lblP.setHorizontalAlignment(SwingConstants.LEFT);
		lblP.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblP.setBounds(12, 160, 187, 62);
		frame.getContentPane().add(lblP);
		
		lblService[0] = new JLabel("0");
		lblService[0].setHorizontalAlignment(SwingConstants.CENTER);
		lblService[0].setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblService[0].setForeground(Color.BLACK);
		lblService[0].setBounds(200, 50, 77, 62);
		frame.getContentPane().add(lblService[0]);
		
		lblService[1] = new JLabel("0");
		lblService[1].setHorizontalAlignment(SwingConstants.CENTER);
		lblService[1].setForeground(Color.BLACK);
		lblService[1].setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblService[1].setBounds(200, 105, 77, 62);
		frame.getContentPane().add(lblService[1]);
		
		lblService[2] = new JLabel("0");
		lblService[2].setHorizontalAlignment(SwingConstants.CENTER);
		lblService[2].setForeground(Color.BLACK);
		lblService[2].setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblService[2].setBounds(200, 160, 77, 62);
		frame.getContentPane().add(lblService[2]);
		
		// --- counter ---
		
		lblCounter[0] = new JLabel("0");
		lblCounter[0].setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter[0].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter[0].setBounds(350, 50, 77, 62);
		frame.getContentPane().add(lblCounter[0]);
		
		lblCounter[1] = new JLabel("0");
		lblCounter[1].setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter[1].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter[1].setBounds(350, 105, 77, 62);
		frame.getContentPane().add(lblCounter[1]);
		
		lblCounter[2] = new JLabel("0");
		lblCounter[2].setHorizontalAlignment(SwingConstants.CENTER);
		lblCounter[2].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCounter[2].setBounds(350, 160, 77, 62);
		frame.getContentPane().add(lblCounter[2]);
		
		// --- waiting --- 
		
		lblWaiting[0] = new JLabel("0");
		lblWaiting[0].setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting[0].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting[0].setBounds(500, 50, 77, 62);
		frame.getContentPane().add(lblWaiting[0]);
		
		lblWaiting[1] = new JLabel("0");
		lblWaiting[1].setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting[1].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting[1].setBounds(500, 105, 77, 62);
		frame.getContentPane().add(lblWaiting[1]);
		
		lblWaiting[2] = new JLabel("0");
		lblWaiting[2].setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting[2].setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWaiting[2].setBounds(500, 160, 77, 62);
		frame.getContentPane().add(lblWaiting[2]);
	}
}
