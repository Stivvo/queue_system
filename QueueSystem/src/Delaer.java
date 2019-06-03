import java.awt.EventQueue;
import java.net.Socket;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class Delaer {

	private JFrame frame;
	
	int[] cont = {0, 0, 0};
	char[] car = {'A', 'B', 'C'};
	Socket s;
	public PrintWriter pr;
	public BufferedReader reader;
	public JLabel ticket;

	public void stampa(int i)
	{
		cont[i]++;
		pr.println(car[i]);
		pr.flush();
		String read = "---";
		try {
			 read = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ticket.setText(read);
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
		InputStreamReader inp;
		try {
			inp = new InputStreamReader(s.getInputStream());
			reader = new BufferedReader(inp);
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public Delaer() 
	{
		initialize();
	}

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
				stampa(0);
				break;
			case 'b':
				stampa(1);
				break;
			case 'c':
				stampa(2);
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
		
		JButton btnA = new JButton("Finance");
		
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(0);
			}
		});
		btnA.setBounds(12, 79, 98, 25);
		frame.getContentPane().add(btnA);
		
		// -- B ---
		
		JButton btnB = new JButton("Communication");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(1);
			}
		});
		btnB.setBounds(120, 79, 98, 25);
		frame.getContentPane().add(btnB);
		
		// -- C ---
		
		JButton btnC = new JButton("Package");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(2);
			}
		});
		btnC.setBounds(230, 79, 98, 25);
		frame.getContentPane().add(btnC);
		
		// -- label scegli sportello
		
		JLabel lblScegliereSportelloDa = new JLabel("Scegliere sportello da prenotare: ");
		lblScegliereSportelloDa.setBounds(12, 12, 326, 37);
		frame.getContentPane().add(lblScegliereSportelloDa);
		
		ticket = new JLabel("---");
		ticket.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ticket.setHorizontalAlignment(SwingConstants.CENTER);
		ticket.setBounds(122, 133, 98, 53);
		frame.getContentPane().add(ticket);
		
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
