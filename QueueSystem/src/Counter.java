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
import java.awt.BorderLayout;

public class Counter extends Thread
{
	private JFrame frame;
	private Socket s;
	private Socket s1;
	private PrintWriter p;
	private BufferedReader reader;
	
	private char type;
	private int num;
	private String name;
	private Boolean active;
	
	public int getNum() {return num;}
	public char getType() {return type;}
	public String getNAme() {return name;}

	public static void main(String[] args) 
	{
		/*
		 * main args:
		 * [0]: char, Type
		 * [1]: int num
		 * 
		 * in java, every word separated by spaces 
		 * you give through command line becomes a String element in the String array args
		 */
		System.out.println("arg1: " + args[0]);
		System.out.println("arg2: " + args[1]);
		
		System.out.println("MAIN sportelloAvanzato");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Counter window = new Counter(
							args[0].charAt(0), 
							Integer.valueOf(args[1]).intValue());
					window.connectNewCounter();
					window.frame.setVisible(true);
					
					window.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void run()
	{
		String message = "";
		try
		{
			message = reader.readLine();			
		}
		catch (IOException e) {
			System.out.println("error reading message through BufferedReader");
		}
		while (true)
		{
			if (message.charAt(0) == 'd' && 
					Integer.valueOf(
							message.substring(1, message.length())
					).intValue() == this.getNum()
				)
			{
				active = false;
				frame.setTitle(frame.getTitle() + " (UNACTIVE)");
			}
		}
	}
	
	public Counter(char type, int num) throws UnknownHostException, IOException {
		
		this.num = num;
		this.type = type;
		this.active = true;
		
		InputStreamReader inp = new InputStreamReader(s1.getInputStream());
		reader = new BufferedReader(inp);
		
		switch(this.type)
		{
		case 'A':
			this.name = new String("Finance");
			break;
		case 'B':
			this.name = new String("Comunication");
			break;
		case 'C':
			this.name = new String("Package");
			break;
		case 'D':
			this.name = new String("Multipurpose");
			break;
		default: 
			this.name = new String("0");
			break;
		}//end switch Type
		
		/*
		 * NewCounter should comunicate with the server 
		 * to establish if parameters are valid
		 * when the users try to create a new counter
		 */
		//following two lines should be UNCOMMENTED when properly using the application
		
		System.out.println("COUNTER, Type: " + this.getType() + ", Num: " + this.getNum() + ", name: " + this.getName());
		initialize();
	}
	
	public void connect() throws UnknownHostException, IOException {
		s = new Socket("localhost", 8045);
		p = new PrintWriter(s.getOutputStream());
	}
	
	
	public void connectNewCounter() throws UnknownHostException, IOException {
		s1 = new Socket("localhost", 8055);
		
	}
	private void stampa()
	{
		if (active)
		{
			p.print(this.getType());
			p.flush();
		}
	}
	
	private void initialize() throws UnknownHostException, IOException {
		frame = new JFrame();
		frame.setTitle("Counter " /*+ this.getNum() + " " + this.getName()*/);
		
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
		
		JButton btn = new JButton("New button");
		frame.getContentPane().add(btn, BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa();
			}
		});
		
		btn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				char c = e.getKeyChar();
				
				if (c != '\n')
					e.consume();
				else
					stampa();
			}
		});
	}
}
