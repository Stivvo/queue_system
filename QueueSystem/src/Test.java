/**
 * this class veryfies if queue, list and node are working
 */
public class Test 
{
	private int i = 0;
	private infoCounter temp = new infoCounter('0', -1);
	int len = 4;
	
	public void tQueue()
	{
		queue q = new queue('A');
		
		for (i = 0; i < len; i++)
		{
			q.NEWENTRY(QueueManagement.getNow());
			
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
		int[] a = {4, 8, 9, 10};

		for (i = 0; i < a.length; i++)
		{
			System.out.println(a[i] + " inserted");
			temp = new infoCounter((char)(65 + i), a[i]);
			l.in(temp);
		}
		
		System.out.println("stampa...");
		l.stampa();
		
		for (i = 0; i < a.length; i++) {
			if (l.search(a[i]).getNum() == -1) {
				System.out.println("number " + a[i] + " not found");
				return false;
			}
		}
		
		for (i = 0; i < a.length; i++) {
			if (l.search((char)(65 + i)).getNum() == -1) {
				System.out.println("letter " + i + " not found");
				return false;
			}
		}
		
		for (i = 0; i < a.length; i++) {
			temp = new infoCounter((char)(65 + i), a[i]);
			System.out.println("temp: " + temp.print());
			if (l.search(temp, false).getNum() == -1) {
				System.out.println(temp.getType() + ", " + temp.getNum() + " not found");
				return false;
			}
		}
		
		return true;
	}
}
