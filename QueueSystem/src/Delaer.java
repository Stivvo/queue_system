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
	public void stampa(int i)
	{
		pr.println(""+car[i] + cont[i]);
		cont[i]++;
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 360, 236);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(0);
			}
		});
		btnA.setBounds(12, 97, 98, 25);
		frame.getContentPane().add(btnA);
		
		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(1);
			}
		});
		btnB.setBounds(122, 97, 98, 25);
		frame.getContentPane().add(btnB);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa(2);
			}
		});
		btnC.setBounds(232, 97, 98, 25);
		frame.getContentPane().add(btnC);
		
		JLabel lblScegliereSportelloDa = new JLabel("Scegliere sportello da prenotare: ");
		lblScegliereSportelloDa.setBounds(12, 12, 326, 37);
		frame.getContentPane().add(lblScegliereSportelloDa);
	}
}
