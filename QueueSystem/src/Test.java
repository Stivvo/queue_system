import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * this class veryfies if queue, list and node are working
 */
public class Test 
{
	private int i = 0;
	private int[] a = {7, 3, 9, 1};
	private infoCounter temp;
	
	public boolean tQueue()
	{
		queue q = new queue();
		
		for (i = 0; i < a.length; i++)
		{
			q.NEWENTRY(a[i], ZonedDateTime.now(ZoneId.of("Europe/Paris")));
			
			try {
			    Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Exception in sleep");
			    Thread.currentThread().interrupt();
			    return false;
			}
		}
		i = 0;
		
		while (!q.isEmpty())
		{
			System.out.println(q.front().getInfo().getTicket() + 
					", " + q.front().getInfo().getT().getEpochSecond());
			
			if (q.NEXT() != a[i])
				return false;
				
			i++;
		}
		return true;
			
	}
	
	public boolean tList()
	{
		list l = new list();

		for (i = 0; i < a.length; i++)
		{
			System.out.println(a[i] + " inserted");
			temp = new infoCounter('A', a[i]); 
			l.in(temp);
		}
		
		for (i = 0; i < a.length; i++)
		{
			temp = new infoCounter('A', a[i]);
			
			if (l.search(temp, false).getNum() == -1)
			{
				System.out.println(
						"search: " + a[i] + " not found");
				return false;
			}
		}
		
		for (i = 0; i < a.length; i++)
		{
			temp = new infoCounter('A', a[i]);
			
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
