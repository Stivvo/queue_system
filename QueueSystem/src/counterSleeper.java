import java.io.IOException;
import java.io.PrintWriter;

public class counterSleeper extends Thread {
	
	public list working;
	public list sleeping;
	public int[] nCounter;
	public Semaforo mutexList;
	
	public counterSleeper(list working, list sleeping, int[] nCounter, Semaforo mutexList) {
		this.working = working;
		this.sleeping = sleeping;
		this.nCounter = nCounter;
		this.mutexList = mutexList;
	}
	
	public void run () 
	{
		infoCounter t;
		int i = 0;
		
		while (true)
		{ //Searches for inactive queue and eventually removes them
			t = working.search();
			PrintWriter p;
			
			if (t.getNum() != -1)
			{
				try {
					p = new PrintWriter(t.getSocket().getOutputStream());
					sleeping.in(t);
					working.rm(t);
					p.print("d" + t.getNum());
					p.flush();
					nCounter[Integer.valueOf(t.getType() + "") - 65]--;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (nCounter[i] >= 20) // nCounter[i] >= 20 is placeholder
			{
				t = sleeping.search(
						new infoCounter(
							String.valueOf("" + (i + 35)).charAt(0), -1
						), true, false);
				
				if (t.getNum() != -1)
				{
					try {
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.rm(t);
						working.in(t);
						p.print("i" + t.getNum());
						p.flush();
						nCounter[Integer.valueOf(t.getType() + "") - 65]++;						
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
				
			}
				
			i = (i + 1) % 4;
		}
	}
}
