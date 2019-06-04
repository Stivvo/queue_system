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
	
	public static Instant getNow() {
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		return zdt.toInstant();
	}
	
	public static int isSomeoneWaiting(queue[] q, Semaforo[] s)
	{
		int flag = 0;
		
		lock(s);
		
		for (int i = 0; i < 3; i++)
		{
				if (!q[i].isEmpty())
				{
					if (q[i].rear().getInfo().getT().getEpochSecond() -
							q[i].front().getInfo().getT().getEpochSecond() 
							>= 30)
						flag++;
				}
		}
		unLock(s);
		return flag;
	}
	
	public static int getIndexBlockedQueue(queue[] q, Semaforo[] s)
	{
		int iMax = 0;
		long longest = 0;
		long ltemp = 0;
		
		lock(s);
		
		for (int i = 0; i < 3; i++)
		{
			if (!q[i].isEmpty())
			{
				ltemp = q[i].front().getInfo().getT().getEpochSecond() - 
						q[i].rear().getInfo().getT().getEpochSecond();
				
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
	
	public static int getIndexAvg(queue[] q, Semaforo[] s) {
		int iMax = -1;
		long max = -1;
		int tmp = 0;
		
		lock(s);
		
		for (int i = 0; i < 3; i++) {
			tmp = q[i].getAvg();
			if (tmp != -1 && tmp > max) {
				max = tmp;
				iMax = i;
			}
		}
		unLock(s);
		return iMax;		
	}
	
}
