import java.io.IOException;
import java.io.PrintWriter;

public class counterSleeper extends Thread {
	
	public list working;
	public list sleeping;
	public list counter;
	public int[] nCounter;
	public Semaforo mutexList;
	public queue[] q;
	public Semaforo[] s;
	
	public counterSleeper(list working, list sleeping, list counter, Semaforo mutexList, Semaforo[] s, queue[] q) {
		this.working = working;
		this.sleeping = sleeping;
		this.counter = counter;
		this.mutexList = mutexList;
		this.s = s;
		this.q = q;
	}
	
	public void run () 
	{
		infoCounter t = new infoCounter('A', -1);
		infoCounter t2 = new infoCounter('A', -1);
		
		while (true)
		{ //Searches for inactive counters and closes them
			System.out.println("print working");
			working.stampa();
			System.out.println("print sleeping");
			sleeping.stampa();
			
			t = working.search();
			PrintWriter p;
			
			if (t.getNum() != -1) {
				try {
					if (t.getSocket() == null)
						t.setSock(counter.search(t, false).getSocket());
					
					p = new PrintWriter(t.getSocket().getOutputStream());
					sleeping.in(t);
					working.rm(t);
					p.println("d" + t.getNum());
					p.flush();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			if (QueueManagement.isSomeoneWaiting(q, s) > 0) {
				int flag = QueueManagement.getIndexBlockedQueue(q, s);
				t2 = new infoCounter('A', -1);
				
				if (flag != -1) { 
					t2 = sleeping.search((char)(65 + flag));
					
					if (t2.getNum() == -1)  
					{//apro un polifunzionale se non c'è nessuno specifico oppure ci sono più code in attesa
						System.out.println("May open polifunc");
						t2 = sleeping.search('D');
					}
					
					if (t2.getNum() != -1) {
						try {
							if (t2.getSocket() == null)
								t2.setSock(counter.search(t, false).getSocket());
							
							p = new PrintWriter(t2.getSocket().getOutputStream());
							sleeping.rm(t2);
							t2.setT(QueueManagement.getNow());
							working.in(t2);
							
							System.out.println("opening " + t2.print());
							p.println("i" + t2.getNum());
							p.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} 
				}
			} //end if QueueManagement.isSomeoneWaiting();
			try {
				Thread.sleep(1000); //reduce CPU usage
			} catch (InterruptedException e) {
				System.out.println("ERROR in counterSleeper sleep after search");
				throw new RuntimeException();
			}
		}
	}
}
