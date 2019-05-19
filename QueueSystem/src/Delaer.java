import java.awt.EventQueue;
import java.net.Socket;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Delaer {

	private JFrame frame;
	
	int[] cont = {0, 0, 0};
	char[] car = {'A', 'B', 'C'};
	Socket s;
	public PrintWriter pr;

	public void stampa(int i, JButton l)
	{
		cont[i]++;
		pr.println("" + car[i] + cont[i]);
		l.setText("" + car[i] + cont[i]);
		pr.flush();
	}
	
	public void connect() {
		
		try {
			s = new Socket("localhost", 8076);
		} catch (SocketException exception) {
			System.out.println(exception);
	    } catch (IOException exception) {
	    	System.out.println(exception);
	    }
		
		try {
			pr = new PrintWriter(s.getOutputStream());
		} catch (SocketException exception) {
		    System.out.println(exception);
		} catch (IOException exception) {
			System.out.println(exception);
		}
		
	}
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delaer window = new Delaer();
					window.frame.setVisible(true);
					window.connect();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Delaer() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void fKey(KeyEvent e, JButton btnA, JButton btnB, JButton btnC)
	{
		char c = e.getKeyChar();
		
		if (e.getKeyChar() < 'a' || e.getKeyChar() > 'c')
			e.consume();
		else
		{
			switch(c) 
			{
			case 'a':
				System.out.println("a");
				stampa(0, btnA);
				break;
			case 'b':
				System.out.println("b");
				stampa(1, btnB);
				break;
			case 'c':
				System.out.println("c");
				stampa(2, btnC);
				break;
			}			
		}
	}
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("Dealer");
		frame.setBounds(100, 100, 360, 236);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// -- A ---
		
		JButton btnA = new JButton("A0");
		
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(0, btnA);
			}
		});
		btnA.setBounds(12, 97, 98, 25);
		frame.getContentPane().add(btnA);
		
		// -- B ---
		
		JButton btnB = new JButton("B0");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(1, btnB);
			}
		});
		btnB.setBounds(122, 97, 98, 25);
		frame.getContentPane().add(btnB);
		
		// -- C ---
		
		JButton btnC = new JButton("C0");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(2, btnC);
			}
		});
		btnC.setBounds(232, 97, 98, 25);
		frame.getContentPane().add(btnC);
		
		// -- label scegli sportello
		
		JLabel lblScegliereSportelloDa = new JLabel("Scegliere sportello da prenotare: ");
		lblScegliereSportelloDa.setBounds(12, 12, 326, 37);
		frame.getContentPane().add(lblScegliereSportelloDa);
		
		btnA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				fKey(e, btnA, btnB, btnC);
			}
		});
		
		btnB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				fKey(e, btnA, btnB, btnC);
			}
		});
		
		btnC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				fKey(e, btnA, btnB, btnC);
			}
		});
	}
}
