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

public class Counter 
{
	private JFrame frame;
	private Socket s;
	private PrintWriter p;
	
	private char type;
	private int num;
	private String name;
	
	public int getNum() {return num;}
	public char getType() {return type;}
	public String getName() {return name;}

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
							args[0].charAt(0), Integer.valueOf(args[1]).intValue());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Counter(char type, int num) throws UnknownHostException, IOException {
		
		this.num = num;
		this.type = type;
		
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
			this.name = new String("0"); //shouldn't be necessary, NewCounter should manage that
		}//end switch Type
		
		/*NewCounter should comunicate with the server 
		 * to establish if parameters are valid
		 * when the users try to create a new counter
		 */
		
		/*following two lines should be UNCOMMENTED when properly using the application
		s = new Socket("localhost", 8045);
		p = new PrintWriter(s.getOutputStream());*/
		
		
		System.out.println("COUNTER, Type: " + this.getType() + ", Num: " + this.getNum() + ", name: " + this.getName());
		
		initialize();
		//initialize should come after because class attributes to put text in the button 
	}
	
	private void stampa()
	{
		p.print(this.getType());
		p.flush();
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