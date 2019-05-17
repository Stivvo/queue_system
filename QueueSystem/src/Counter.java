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
		char Type = args[0].charAt(0);
		int Num = args[1].charAt(0);
		String[] Name = new String[1];
		
		switch(Type)
		{
		case 'A':
			Name[0] = "1. Finance";
			break;
		case 'B':
			Name[0] = "2. Comunication";
			break;
		case 'C':
			Name[0] = "3. Package";
			break;
		case 'D':
			Name[0] = "4. Multipurpose";
			break;
		}//end switch Type
		
		System.out.println("MAIN sportelloAvanzato");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Counter window = new Counter(Type, Num, Name);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Counter(char type, int num, String[] name) throws UnknownHostException, IOException {
		initialize();
		
		this.num = num;
		this.type = type;
		this.name = name[0];
		
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
