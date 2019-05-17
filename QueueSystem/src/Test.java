import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 
 */

/**
 * @author stefano
 *
 */
public class Test 
{
	/**
	 * @param args
	 */
	private int i = 0;
	
	public boolean tQueue()
	{
		queue q = new queue();
		int[] a = {7, 3, 9, 1};
		
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
	
	/*public boolean tDealer() 
	{
		Delaer d = new Delaer();
		d.main();
	}*/

}
