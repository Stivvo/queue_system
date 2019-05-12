import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import sun.util.calendar.ZoneInfo;

public class serverDealer extends Thread{
	
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];
	
	public serverDealer(queue[] q1, Semaforo[] s1) throws IOException {
		super();
		setName("ThreadServerDealer");
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = q1[i];
			s[i] = s1[i];
		}
		
		ss = new ServerSocket(8076);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public int switchCar(String s)
	{
		int i = -1;
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
		
		while (true) 
		{
			try 
			{
				operate = reader.readLine();
				i = this.switchCar(operate);
				
				if (i != -1)
				{
					s[i].p();
					q[i].NEWENTRY( Integer.parseInt(operate.substring(1)), ZonedDateTime.now(ZoneId.of("Europe/Paris")) );
					s[i].v();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
