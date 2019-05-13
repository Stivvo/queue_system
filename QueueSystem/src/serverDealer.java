import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JLabel;


public class serverDealer extends Thread{
	
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];
	private JLabel[] LW = new JLabel[3];
	
	public serverDealer(
			queue[] q1, Semaforo[] s1,
			JLabel LW1, JLabel LW2, JLabel LW3) 
			throws IOException 
	{
		super();
		setName("ThreadServerDealer");
		
		q = q1;
		s = s1;
		
		LW[0] = LW1;
		LW[1] = LW2;
		LW[2] = LW3;
		
		ss = new ServerSocket(8076);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public int switchCar(String s)
	{
		int i = -1;
		
		if (s.length() <= 0)
			return -1;
		
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
				
				if (i != -1)
				{
					System.out.println("pushhhh");
					s[i].p();
					q[i].NEWENTRY( Integer.parseInt(operate.substring(1)), ZonedDateTime.now(ZoneId.of("Europe/Paris")) );
					LW[i].setText("" + q[i].getDim());
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
