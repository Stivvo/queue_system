import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class counterSleeper extends Thread {
	
	public list working;
	public list sleeping;
	public int[] nCounter;
	public Semaforo mutexList;
	public queue[] q;
	public Semaforo[] s;
	
	public counterSleeper(list working, list sleeping, int[] nCounter, Semaforo mutexList, Semaforo[] s, queue[] q) {
		this.working = working;
		this.sleeping = sleeping;
		this.nCounter = nCounter;
		this.mutexList = mutexList;
		this.s = s;
		this.q = q;
	}
	
	public void lock()
	{
		for (int i = 0; i < 3; i++)
			s[i].p();
	}
	
	public void unLock()
	{
		for (int i = 0; i < 3; i++)
			s[i].v();
	}
	
	public int isSomeoneWaiting() 
	{
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		int flag = 0;
		
		lock();
		
		for (int i = 0; i < 3; i++)
		{
			if (i != 1)
			{
				if (!q[i].isEmpty()) 
				{
					if (now.getEpochSecond() - 
							q[i].front().getInfo().getT().getEpochSecond() 
							>= 5)
						flag++;
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
		
		for (int i = 0; i < 3; i++)
		{
			if (!q[i].isEmpty())
			{
				ltemp = now.getEpochSecond() - 
						q[i].front().getInfo().getT().getEpochSecond();
				
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
	
	public void run () 
	{
		infoCounter t;
		int i = 0, j = 0;
		
		while (true)
		{ //Searches for inactive counters and closes them
			t = working.search();
			PrintWriter p;
			
			if (t.getNum() != -1) {
				j = (int)t.getType() - 65;
				
				if (nCounter[j] > 1) {
					try {
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.in(t);
						working.rm(t);
						System.out.println("suspend " + t.getNum());
						p.println("d" + t.getNum());
						p.flush();
						System.out.println("sent");
						
						nCounter[j]--;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} //end if (nCounter[j] > 1)
			} else {
				try {
					Thread.sleep(1000); //reduce CPU usage	
				} catch (InterruptedException e) {
					System.out.println("ERROR in counterSleeper sleep after search");
					throw new RuntimeException();
				}
			}
			
			int flag = isSomeoneWaiting();
			if (flag > 0)
			{
				if (flag == 1) {
					t = sleeping.search(65+getIndexBlockedQueue());
				} else {
					t = sleeping.search('D');
				}
				
				
				if (t.getNum() != -1)
				{
					try {
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.rm(t);
						working.in(t);
						p.print("i" + t.getNum());
						p.flush();
						System.out.println("wake up " + t.getNum());
						nCounter[Integer.valueOf(t.getType() + "") - 65]++;						
					}catch (IOException e) {
						e.printStackTrace();
					}  
				}
			}
			
				
			i = (i + 1) % 4;
		}
	}
}
