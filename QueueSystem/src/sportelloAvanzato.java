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

	public sportelloAvanzato() throws UnknownHostException, IOException {
		initialize();
		
		s = new Socket("localhost", 8045);
		p = new PrintWriter(s.getOutputStream());
	}

	void fKey(KeyEvent e)
	{
		char c = e.getKeyChar();
		
		if (c < '1' || c > '5')
			e.consume();
		else
		{
			switch (c) 
			{
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
	}
	
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
		
		JButton btnA = new JButton("1. Finance");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('A');
				p.flush();
			}
		});
		btnA.setBounds(159, 38, 105, 27);
		frame.getContentPane().add(btnA);
		
		JButton btnB = new JButton("2. Comunication");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('B');
				p.flush();
			}
		});
		btnB.setBounds(159, 94, 105, 27);
		frame.getContentPane().add(btnB);
		
		JButton btnC = new JButton("3. Packages");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('C');
				p.flush();
			}
		});
		
		btnC.setBounds(159, 150, 105, 27);
		frame.getContentPane().add(btnC);
		
		JButton btnD = new JButton("4. Multipuropse");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.print('D');
				p.flush();
			}
		});
		
		btnD.setBounds(159, 204, 105, 27);
		frame.getContentPane().add(btnD);
		
		JButton btnE = new JButton("5. Multipurpose");
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
				fKey(e);
			}
		});
		
		btnB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fKey(e);
			}
		});
		
		btnC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fKey(e);
			}
		});
		
		btnD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fKey(e);
			}
		});
		
		btnE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fKey(e);
			}
		});
	}

}
