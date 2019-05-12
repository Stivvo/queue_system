import java.awt.EventQueue;
import java.net.Socket;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

public class Delaer {

	private JFrame frame;
	
	int[] cont = {0, 0, 0};
	char[] car = {'A', 'B', 'C'};
	Socket s;
	public PrintWriter pr;

	/**
	 * Launch the application.
	 */
	public void stampa(int i, JButton l)
	{
		cont[i]++;
		pr.println("" + car[i] + cont[i]);
		l.setText("" + car[i] + cont[i]);
		pr.flush();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delaer window = new Delaer();
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
	public Delaer() 
	{
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
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
	}
}
