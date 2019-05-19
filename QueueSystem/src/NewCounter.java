import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class NewCounter extends Thread
{
	private JFrame frame;
	private list working;
	private list sleeping;
	
	private int[] number = {0, 0, 0, 0};
	//a position for the number of each type of sounter
	
	
	private ServerSocket ss;

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
		working = new list();
		sleeping = new list();
		connect();
	}

	public void createCounter(char c, JSpinner s)
	{
		infoCounter temp = new infoCounter
				(c, 
				Integer.valueOf(s.getValue().toString().toString()).intValue()
				);
		int i = Integer.valueOf(temp.getType() + "").intValue() - 35;
		
		if (number[i] > 1 && working.search(temp, false).getNum() == -1)
			System.out.println(temp.print() + " already used");
		else
		{
			String[] argh = { "" + temp.getType() + temp.getNum() };
			Counter.main(argh);
			try {
				temp.setSock(ss.accept());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			working.in(temp);
			
			number[i]++;
		}
	}
	
	public void run () 
	{
		infoCounter t;
		
		while (true)
		{ //Searches for inactive queue and eventually removes them
			t = working.search();
			
			if (t.getNum() != -1)
			{
				PrintWriter p;
				try {
					p = new PrintWriter(t.getSocket().getOutputStream());
					sleeping.in(t);
					working.rm(t);
					p.print("d" + t.getNum());
					number[Integer.valueOf(t.getType() + "").intValue() - 35]--;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void connect() 
	{
		try {
			ss = new ServerSocket(8055);
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
