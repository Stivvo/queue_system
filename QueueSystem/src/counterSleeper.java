import java.io.IOException;
import java.io.PrintWriter;

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
				
				if (nCounter[j] > 1 && QueueManagement.isSomeoneWaiting(q, s) > 0) {
					
					try {
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.in(t);
						working.rm(t);
						p.println("d" + t.getNum());
						p.flush();
						nCounter[j]--;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} //end if (nCounter[j] > 1)
			} 
			int flag = QueueManagement.isSomeoneWaiting(q, s);
			
			if (flag > 0) {
				if (flag == 1) {
					t = sleeping.search( (65+QueueManagement.getIndexBlockedQueue(q, s)));
				} else {
					t = sleeping.search('D');
				}
				System.out.println("t = sleeping.search() " + t.print());
				
				if (t.getNum() != -1) {
					try {			
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.rm(t);
						t.setT(QueueManagement.getNow());
						working.in(t);
						
						p.println("i" + t.getNum());
						p.flush();
						nCounter[t.getType() - 65]++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} 
			}
				
			i = (i + 1) % 4;
			try {
				Thread.sleep(1000); //reduce CPU usage
			} catch (InterruptedException e) {
				System.out.println("ERROR in counterSleeper sleep after search");
				throw new RuntimeException();
			}
		}
	}
}
