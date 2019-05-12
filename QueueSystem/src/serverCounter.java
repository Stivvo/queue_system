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
	
	private int i;
	
	public serverCounter(queue[] q1, Semaforo[] s1, 
			JLabel L1, JLabel L2, JLabel L3, 
			JLabel LC1, JLabel LC2, JLabel LC3,
			JLabel LW1, JLabel LW2, JLabel LW3) 
			throws IOException 
	{
		super();
		q = q1;
		s = s1;
		for (i = 0; i < 3; i++)
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
				LW[j].setText("" + q[j].getDim());
			}
			s[j].v();
			System.out.println(operate + "\n");
		}
		
	}
}
