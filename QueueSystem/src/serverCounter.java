import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JLabel;

public class serverCounter extends Thread {
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q;
	private Semaforo[] s;
	private JLabel LS[] = new JLabel[3];
	private JLabel LC[] = new JLabel[3];
	private JLabel LW[] = new JLabel[3];
	
	private int i;
	
	public serverCounter(queue[] q1, Semaforo[] s1, JLabel[] LS,
						JLabel[] LC,JLabel[] LW, Socket socket) throws IOException 
	{
		super();
		q = q1;
		s = s1;
		this.LS = LS;
		this.LC = LC;
		this.LW = LW;
		sock = socket;
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void lock()
	{
		for (i = 0; i < 3; i++)
			s[i].p();
	}
	
	public void unLock()
	{
		for (i = 0; i < 3; i++)
			s[i].v();
	}
	
	
	public Boolean isSomeoneWaiting() 
	{
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		Boolean flag = false;
		
		lock();
		
		for (i = 0; i < 3; i++)
		{
			if (i != 1)
			{
				if (!q[i].isEmpty()) 
				{
					if (now.getEpochSecond() - q[i].front().getInfo().getT().getEpochSecond() >= 5)
						flag = true;
				}				
			}
		}
		unLock();
		return flag;
	}
	
	public int getIndexBlockedQueue() 
	{
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		int iMax = 0;
		long longest = 0;
		long ltemp = 0;
		
		lock();
		
		for (i = 0; i < 3; i++)
		{
			if (!q[i].isEmpty())
			{
				ltemp = now.getEpochSecond() - q[i].front().getInfo().getT().getEpochSecond();
				
				if (ltemp > longest)
				{
					longest = ltemp;
					iMax = i;
				}
			}
		}
		
		unLock();
		return iMax;
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
			System.out.println("i = " + i);
			
			if (i == 3 || i == 4)
			{
				if (!q[1].isEmpty() && !isSomeoneWaiting()) {
					System.out.println("j = 1");
					j = 1;
				} else {
					j = getIndexBlockedQueue();
					System.out.println("j2 = " + j);
				}	
			}
			else
				j = i;
			
			if (i >= 0 && i < 4)
			{					
				s[j].p();
				
				if (!q[j].isEmpty())
				{
					LS[j].setText("" + q[j].NEXT());
					LC[j].setText("" + (i+1));
					LW[j].setText("" + q[j].getDim());
				}
				s[j].v();
				System.out.println("operate: " + operate + "\n");
			}
		}
	}
}
