import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * this class veryfies if queue, list and node are working
 */
public class Test 
{
	private int i = 0;
	private infoCounter temp;
	int len = 4;
	int[] a = {4, 8, 9, 10};
	
	public void tQueue()
	{
		queue q = new queue('A');
		
		for (i = 0; i < len; i++)
		{
			q.NEWENTRY(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
			
			try {
			    Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception in sleep");
			    Thread.currentThread().interrupt();
			}
		}
		i = 0;
		
		while (!q.isEmpty())
		{
			System.out.println(q.front().getInfo().getTicket() + 
					", " + q.front().getInfo().getT().getEpochSecond());
		}
			
	}
	
	public boolean tList()
	{
		list l = new list();

		for (i = 0; i < a.length; i++)
		{
			System.out.println(a[i] + " inserted");
			temp = new infoCounter(String.valueOf("" + (65 + i)).charAt(0), a[i]);
			l.in(temp);
		}
		
		for (i = 0; i < a.length; i++)
		{
			temp = new infoCounter(String.valueOf("" + (65 + i)).charAt(0), a[i]);
			
			if (l.search(temp, false).getNum() == -1)
			{
				System.out.println(
						"search: " + a[i] + " not found");
				return false;
			}
		}
		
		for (i = 0; i < a.length; i++)
		{
			if (l.search(i
				).getNum() == -1)
			{
				System.out.println(
						"search: " + String.valueOf("" + (65 + i)).charAt(0) + " not found");
				return false;
			}
		}
		
		for (i = 0; i < a.length; i++)
		{
			temp = new infoCounter(String.valueOf("" + (65 + i)).charAt(0), a[i]);
			
			if (l.rm(temp).getNum() == -1)
			{
				System.out.println(
						"rm: " + a[i] + " not found");
				return false;
			}
		}
		
		if (!l.isEmpty())
		{
			System.out.println("list is not empty");
			return false;
		}
		return true;
	}
}
