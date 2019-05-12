import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JLabel;

public class serverCounter extends Thread {
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q;
	private Semaforo[] s;
	private JLabel l[] = new JLabel[3];
	private JLabel LC[] = new JLabel[3];
	private JLabel LW[] = new JLabel[3];
	
	public serverCounter(queue[] q1, Semaforo[] s1, 
			JLabel L1, JLabel L2, JLabel L3, 
			JLabel LC1, JLabel LC2, JLabel LC3,
			JLabel LW1, JLabel LW2, JLabel LW3) 
			throws IOException 
	{
		super();
		q = q1;
		s = s1;
		for (int i = 0; i < 3; i++)
		{	
			switch (i)
			{
			case 0:
				l[i] = L1;
				LC[i] = LC1;
				LW[i] = LW1;
				break;
			case 1:
				l[i] = L2;
				LC[i] = LC2;
				LW[i] = LW2;
				break;
			case 2:
				l[i] = L3;
				LC[i] = LC3;
				LW[i] = LW3;
				break;
			}
		}
		ss = new ServerSocket(8045);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public Boolean isSomeoneWaiting() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		Boolean flag = false;
		
		s[0].p();
		if (!q[0].isEmpty()) 
		{
			if (now.getEpochSecond() - q[0].front().getInfo().getT().getEpochSecond() >= 5)
				flag = true;			
		}
		s[0].v();
		
		s[2].p();
		if (!q[2].isEmpty()) 
		{
			if (now.getEpochSecond() - q[2].front().getInfo().getT().getEpochSecond() >= 5)
				flag = true;
		}
		s[2].v();
		
		return flag;
	}
	
	public int getIndexBlockedQueue() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		long q0 = 0, q1 = 0, q2 = 0;
		
		s[0].p();
		if (!q[0].isEmpty()) {
			q0 = now.getEpochSecond() - q[0].front().getInfo().getT().getEpochSecond();
		}
		s[0].v();
		
		s[1].p();
		if (!q[1].isEmpty()) {
			q1 = now.getEpochSecond() - q[1].front().getInfo().getT().getEpochSecond();
		}
		s[1].v();
		
		s[2].p();
		if (!q[2].isEmpty()) {
			q2 = now.getEpochSecond() - q[2].front().getInfo().getT().getEpochSecond();
		}
		
		s[2].v();
		

		if (q0 > q2 && q0 > q1) 
			return 0;
		
		if (q2 > q0 && q2 > q1)
			return 2;
		
		return 1;
	}
	
	public void run()  
	{
		int operate = 0;
		int i = 0, j = 0;
		int flag = 0;
		while (true) 
		{
			try {
				operate = reader.read();
			} catch (SocketException e) {
				try {
					sock.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				flag = 1;
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			if (flag == 1)
				break;
			
			i = operate - 65;
			
			if (i == 3 || i == 4)
			{
				if (!isSomeoneWaiting() && !q[1].isEmpty()) {
					System.out.println("j = 1");
					j = 1;
				} else {
					j = getIndexBlockedQueue();
					System.out.println("j2 = "+j );
				}	
			}
			else 
				j = i;
			
			s[j].p();
			
			if (!q[j].isEmpty())
			{
				l[j].setText("" + ((char) (j + 65)) + q[j].NEXT());
				LC[j].setText("" + (i+1));
				LW[j].setText("" + q[i].getDim());
			}
			s[j].v();
			System.out.println(operate + "\n");
		}
		
	}
}
