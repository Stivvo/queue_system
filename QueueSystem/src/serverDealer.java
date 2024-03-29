import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JLabel;

public class serverDealer extends Thread{
	
	private Socket sock;
	private BufferedReader reader;
	private PrintWriter pr;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];
	private JLabel[] LW = new JLabel[3];
	
	public serverDealer(queue[] q1, Semaforo[] s1,JLabel[] LW, Socket socket) 
			throws IOException 
	{
		super();
		setName("ThreadServerDealer");
		
		q = q1;
		s = s1;
		
		this.LW = LW;
		
		sock = socket;
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
		pr = new PrintWriter(sock.getOutputStream());
	}
	
	public int switchCar(String s)
	{
		int i = -1;
		
		if (s.length() <= 0)
			return i;
		
		switch (s.charAt(0)) 
		{
		case 'A':
			i = 0;
			break;
		case 'B':
			i = 1;
			break;
		case 'C':
			i = 2;
			break;
		default:
			i = -1;
		}
		return i;
	}
	
	public void run() {
		String operate = "";
		int i = 0;
		int flag = 0;
		while (true) 
		{
			try 
			{
				operate = reader.readLine();
				i = this.switchCar(operate);
				
				if (i != -1) {
					s[i].p();
					q[i].NEWENTRY(QueueManagement.getNow());
					LW[i].setText("" + q[i].getDim());
					Cliente client = q[i].Rear();
					
					if (client != null) {
						pr.println(client.getTicket());
					} else {
						pr.println("---");
					}
					pr.flush();
					s[i].v();
				}
			}  catch (SocketException e) {
				try {
					sock.close();
					flag = 1;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (flag == 1)
				break;
		}
	}
}
