import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;


public class NewCounter extends Thread
{
	private JFrame frame;
	private Socket sock;
	private PrintWriter writer;
	private BufferedReader read;
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {public void run() {
				try {
					NewCounter window = new NewCounter();
					window.frame.setVisible(true);
					window.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCounter() {
		initialize();
		connect();
	}

	public void createCounter(char c, JSpinner s)
	{
		infoCounter temp = new infoCounter
				(c, 
				Integer.valueOf(s.getValue().toString().toString()).intValue()
				);
		
		

		
		writer.println(c+  "" +temp.getNum() );
		writer.flush();
		System.out.println(c+  "" +temp.getNum() );
		
		String readed = "";
		try {
			readed = read.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (readed.compareTo("ok") == 0) {
			System.out.println("I enter");
			String[] argh = { "" + temp.getType(), "" +temp.getNum() };
			try {
				Counter.main(argh);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
			System.out.println(readed);
		
	}
	
	/*public void run () 
	{
		infoCounter t;
		int i = 0;
		
		while (true)
		{ //Searches for inactive queue and eventually removes them
			t = working.search();
			PrintWriter p;
			
			if (t.getNum() != -1)
			{
				try {
					p = new PrintWriter(t.getSocket().getOutputStream());
					sleeping.in(t);
					working.rm(t);
					p.print("d" + t.getNum());
					p.flush();
					nCounter[Integer.valueOf(t.getType() + "") - 65]--;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (nCounter[i] >= 20) // nCounter[i] >= 20 is placeholder
			{
				t = sleeping.search(
					String.valueOf("" + (i + 35)).charAt(0)
				);
				
				if (t.getNum() != -1)
				{
					try {
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.rm(t);
						working.in(t);
						p.print("i" + t.getNum());
						p.flush();
						nCounter[Integer.valueOf(t.getType() + "") - 65]++;						
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
				
			}
				
			i = (i + 1) % 4;
		}
	}*/
	
	public void connect() 
	{
		try {
			sock = new Socket("localhost", 8055);
			writer = new PrintWriter(sock.getOutputStream());
			read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
