import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class NewCounter extends Thread
{
	private JFrame frame;
	private list nUsed;
	private list sleeping;
	
	private Socket sock;
	private ServerSocket ss;
	private PrintWriter p;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {public void run() {
				try {
					NewCounter window = new NewCounter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCounter() {
		initialize();
		nUsed = new list();
		sleeping = new list();
		connect();
	}

	public void createCounter(char c, JSpinner s)
	{
		infoCounter temp = new infoCounter
				(c, 
				Integer.valueOf(s.getValue().toString().toString()).intValue()
				);
		if (nUsed.search(temp, false).getNum() == -1)
			System.out.println(temp.print() + " already used");
		else
		{
			String[] argh = { "" + temp.getType() + temp.getNum() };
			nUsed.in(temp);
			Counter.main(argh, sock);
		}
	}
	
	public void run () 
	{
		infoCounter t;
		
		while (true)
		{ //Searches for inactive queue and eventually removes them
			t = nUsed.search();
			
			if (t.getNum() != -1)
			{
				sleeping.in(t);
				nUsed.rm(t);
			}
		}
	}
	
	public void connect() 
	{
		try {
			ss = new ServerSocket(8045);
			 sock = ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(41, 93, 105, 22);
		frame.getContentPane().add(spinner);
		
		JButton btnFinance = new JButton("Finance");
		btnFinance.setBounds(276, 130, 150, 27);
		frame.getContentPane().add(btnFinance);
		
		btnFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('A', spinner);
			}
		});
		
		JButton btnComunication = new JButton("Comunication");
		btnComunication.setBounds(276, 90, 150, 27);
		frame.getContentPane().add(btnComunication);
		
		btnComunication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('B', spinner);
			}
		});
		
		JButton btnPackage = new JButton("Package");
		btnPackage.setBounds(276, 169, 150, 27);
		frame.getContentPane().add(btnPackage);
		
		btnPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('C', spinner);
			}
		});
		
		JButton btnPolifunzione = new JButton("Multipurpose");
		btnPolifunzione.setBounds(276, 51, 150, 27);
		frame.getContentPane().add(btnPolifunzione);
		
		btnPolifunzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('D', spinner);
			}
		});
		
		JLabel lblNumeroSportello = new JLabel("Counter number");
		lblNumeroSportello.setBounds(41, 56, 150, 17);
		frame.getContentPane().add(lblNumeroSportello);
		
	}
}
