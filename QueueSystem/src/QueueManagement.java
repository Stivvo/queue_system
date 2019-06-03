import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class QueueManagement {
	
	public static void lock(Semaforo[] s)
	{
		for (int i = 0; i < 3; i++)
			s[i].p();
	}
	
	public static void unLock(Semaforo[] s)
	{
		for (int i = 0; i < 3; i++)
			s[i].v();
	}
	
	public static int isSomeoneWaiting(queue[] q, Semaforo[] s)
	{
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		int flag = 0;
		
		lock(s);
		
		for (int i = 0; i < 3; i++)
		{
				if (!q[i].isEmpty())
				{
					if (now.getEpochSecond() - 
							q[i].front().getInfo().getT().getEpochSecond() 
							>= 5)
						flag++;
				}				
			
		}
		unLock(s);
		return flag;
	}
	
	public static int getIndexBlockedQueue(queue[] q, Semaforo[] s)
	{
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		Instant now = zdt.toInstant();
		int iMax = 0;
		long longest = 0;
		long ltemp = 0;
		
		lock(s);
		
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
		unLock(s);
		return iMax;
	}
}
