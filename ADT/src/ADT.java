import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ADT {

	private JFrame frame;
	JLabel lblA;
	JLabel lblB;
	JLabel lblC;
	
	int n;
	private Sportello[] p;
	private queue[] q;
	private Semaforo[] s;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADT window = new ADT();
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
	public ADT() {		
		lblA = new JLabel();
		lblB = new JLabel();
		lblC = new JLabel();
		
		initialize();
		n = 3;
		
		p = new Sportello[n];
		q = new queue[n];
		s = new Semaforo[n];
		
		for (int i = 0; i < n; i++)
		{
			q[i] = new queue();
			s[i] = new Semaforo(1);
			
			switch (i) {
			case 0:
				System.out.println("costruito sportello 0");
				p[i] = new Sportello(q[i], s[i], lblA);
				break;
			case 1:
				System.out.println("costruito sportello 1");
				p[i] = new Sportello(q[i], s[i], lblB);
				break;
			case 2:
				System.out.println("costruito sportello 2");
				p[i] = new Sportello(q[i], s[i], lblC);
				break;
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblA = new JLabel("A");
		lblA.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(57, 38, 61, 40);
		frame.getContentPane().add(lblA);
		
		lblB = new JLabel("B");
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblB.setBounds(188, 38, 61, 40);
		frame.getContentPane().add(lblB);
		
		lblC = new JLabel("C");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblC.setBounds(324, 38, 61, 40);
		frame.getContentPane().add(lblC);
		
		JButton btnPrenotaSportelloA = new JButton("Prenota Sportello A");
		btnPrenotaSportelloA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				q[0].NEWENTRY();
			}
		});
		btnPrenotaSportelloA.setBounds(23, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloA);
		
		JButton btnPrenotaSportelloB = new JButton("Prenota Sportello B");
		btnPrenotaSportelloB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				q[1].NEWENTRY();
			}
		});
		btnPrenotaSportelloB.setBounds(160, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloB);
		
		JButton btnPrenotaSportelloC = new JButton("Prenota Sportello C");
		btnPrenotaSportelloC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				q[2].NEWENTRY();
			}
		});
		btnPrenotaSportelloC.setBounds(297, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloC);
	}
}
