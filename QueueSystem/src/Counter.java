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
		initialize();
		
		this.num = num;
		this.type = type;
		
		switch(this.type)
		{
		case 'A':
			this.name = new String("1. Finance");
			break;
		case 'B':
			this.name = new String("2. Comunication");
			break;
		case 'C':
			this.name = new String("3. Package");
			break;
		case 'D':
			this.name = new String("4. Multipurpose");
			break;
		default: 
			this.name = new String("0"); //shouldn't be necessary, NewCounter should manage that
		}//end switch Type
		
		/*NewCounter should comunicate with the server 
		 * to establish if parameters are valid
		 * when the users try to create a new counter
		 */
		
		s = new Socket("localhost", 8045);
		p = new PrintWriter(s.getOutputStream());
	}
	
	private void stampa()
	{
		p.print(this.getType());
		p.flush();
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
		
		JButton btn = new JButton(this.getName());
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stampa();
			}
		});
		btn.setBounds(159, 38, 105, 27);
		frame.getContentPane().add(btn);
		
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
