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
	
	JLabel lblNa;
	JLabel lblNb;
	JLabel lblNc;
	
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
				p[i] = new Sportello(q[i], s[i], lblA, lblNa);
				break;
			case 1:
				p[i] = new Sportello(q[i], s[i], lblB, lblNb);
				break;
			case 2:
				p[i] = new Sportello(q[i], s[i], lblC, lblNc);
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
		//A
		lblA = new JLabel("A");
		lblA.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setBounds(57, 38, 61, 40);
		frame.getContentPane().add(lblA);
		
		lblNa = new JLabel("0");
		lblNa.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setBounds(57, 61, 61, 40);
		frame.getContentPane().add(lblNa);
		//B
		lblB = new JLabel("B");
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		lblB.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblB.setBounds(188, 38, 61, 40);
		frame.getContentPane().add(lblB);
		
		lblNb = new JLabel("0");
		lblNb.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNb.setHorizontalAlignment(SwingConstants.CENTER);
		lblNb.setBounds(188, 61, 61, 40);
		frame.getContentPane().add(lblNb);
		//C
		lblC = new JLabel("C");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblC.setBounds(324, 38, 61, 40);
		frame.getContentPane().add(lblC);
		
		lblNc = new JLabel("0");
		lblNc.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNc.setVerticalAlignment(SwingConstants.CENTER);
		lblNc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNc.setBounds(324, 61, 61, 40);
		frame.getContentPane().add(lblNc);
		
		JButton btnPrenotaSportelloA = new JButton("Prenota Sportello A");
		btnPrenotaSportelloA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s[0].p();
				q[0].NEWENTRY();
				lblNa.setText(q[0].getDim() + "");
				s[0].v();
			}
		});
		btnPrenotaSportelloA.setBounds(23, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloA);
		
		JButton btnPrenotaSportelloB = new JButton("Prenota Sportello B");
		btnPrenotaSportelloB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s[1].p();
				q[1].NEWENTRY();
				lblNb.setText(q[1].getDim() + "");
				s[1].v();
			}
		});
		btnPrenotaSportelloB.setBounds(160, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloB);
		
		JButton btnPrenotaSportelloC = new JButton("Prenota Sportello C");
		btnPrenotaSportelloC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s[2].p();
				q[2].NEWENTRY();
				lblNc.setText(q[2].getDim() + "");
				s[2].v();
			}
		});
		btnPrenotaSportelloC.setBounds(297, 140, 127, 23);
		frame.getContentPane().add(btnPrenotaSportelloC);
	}
}
