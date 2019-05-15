import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class sportelloAvanzato {

	private JFrame frame;
	private Socket s;
	private PrintWriter p;
	/**
	 * Launch the application.
	 */
	public static void main() 
	{
		System.out.println("MAIN sportelloAvanzato");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sportelloAvanzato window = new sportelloAvanzato();
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
	 * @throws UnknownHostException 
	 */
	public sportelloAvanzato() throws UnknownHostException, IOException {
		initialize();
		
		s = new Socket("localhost", 8045);
		p = new PrintWriter(s.getOutputStream());
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		frame = new JFrame();
		frame.setTitle("Counter");
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					s.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnA = new JButton("A");
		
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('A');
				p.flush();
			}
		});
		btnA.setBounds(159, 38, 105, 27);
		frame.getContentPane().add(btnA);
		
		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('B');
				p.flush();
			}
		});
		btnB.setBounds(159, 94, 105, 27);
		frame.getContentPane().add(btnB);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('C');
				p.flush();
			}
		});
		
		btnC.setBounds(159, 150, 105, 27);
		frame.getContentPane().add(btnC);
		
		JButton btnD = new JButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('D');
				p.flush();
			}
		});
		
		btnD.setBounds(159, 204, 105, 27);
		frame.getContentPane().add(btnD);
		
		JButton btnE = new JButton("E");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('E');
				p.flush();
			}
		});
		
		btnE.setBounds(159, 258, 105, 27);
		frame.getContentPane().add(btnE);
		
		btnA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() < '1' || e.getKeyChar() > '5') {
					e.consume();
				}
				
				switch (e.getKeyChar()) {
					case '1':
						p.print('A');
						p.flush();
						break;
					
					case '2':
						p.print('B');
						p.flush();
						break;
						
					case '3':
						p.print('C');
						p.flush();
						break;
						
					case '4':
						p.print('D');
						p.flush();
						break;
						
					case '5':
						p.print('E');
						p.flush();
						break;
				}
			}
		});
		
		btnB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() < '1' || e.getKeyChar() > '5') {
					e.consume();
				}
				
				switch (e.getKeyChar()) {
					case '1':
						p.print('A');
						p.flush();
						break;
					
					case '2':
						p.print('B');
						p.flush();
						break;
						
					case '3':
						p.print('C');
						p.flush();
						break;
						
					case '4':
						p.print('D');
						p.flush();
						break;
						
					case '5':
						p.print('E');
						p.flush();
						break;
				}
			}
		});
		
		btnC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() < '1' || e.getKeyChar() > '5') {
					e.consume();
				}
				
				switch (e.getKeyChar()) {
					case '1':
						p.print('A');
						p.flush();
						break;
					
					case '2':
						p.print('B');
						p.flush();
						break;
						
					case '3':
						p.print('C');
						p.flush();
						break;
						
					case '4':
						p.print('D');
						p.flush();
						break;
						
					case '5':
						p.print('E');
						p.flush();
						break;
				}
			}
		});
		
		btnD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() < '1' || e.getKeyChar() > '5') {
					e.consume();
				}
				
				switch (e.getKeyChar()) {
					case '1':
						p.print('A');
						p.flush();
						break;
					
					case '2':
						p.print('B');
						p.flush();
						break;
						
					case '3':
						p.print('C');
						p.flush();
						break;
						
					case '4':
						p.print('D');
						p.flush();
						break;
						
					case '5':
						p.print('E');
						p.flush();
						break;
				}
			}
		});
		
		btnE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() < '1' || e.getKeyChar() > '5') {
					e.consume();
				}
				
				switch (e.getKeyChar()) {
					case '1':
						p.print('A');
						p.flush();
						break;
					
					case '2':
						p.print('B');
						p.flush();
						break;
						
					case '3':
						p.print('C');
						p.flush();
						break;
						
					case '4':
						p.print('D');
						p.flush();
						break;
						
					case '5':
						p.print('E');
						p.flush();
						break;
				}
			}
		});
	}

}
