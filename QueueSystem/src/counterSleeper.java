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
			t = working.search();
			PrintWriter p;
			
			if (QueueManagement.isSomeoneWaiting(q, s) > 0 && t.getNum() != -1) {
				
				if (t.getType() < 'D') {//se è un counter specifico
					s[t.getType() - 65].p();
					int avg = q[t.getType() - 65].getAvg();
					if (avg > 20) {//non chiude se c'è un attesa maggiore di 20 minuti
						t = new infoCounter('A', -1);
					}
					s[t.getType() - 65].v();
				} else { // se lo sportello è polifunzionale
					if (QueueManagement.isSomeoneWaiting(q, s) > 1) { /*non chiude se ci sono più code 
																	con attesa maggiore a 20 minuti*/
						t = new infoCounter('A', -1);
					} else {
						int j = QueueManagement.getIndexBlockedQueue(q, s);
						if (working.search('A'+j).getNum() == -1) {/* non chiude se non c'è lo sportello specializzato*/
							t = new infoCounter('A', -1);
						}
					}
				}
				
				
			}
			if (t.getNum() != -1) {	//chiusura dello sportello
				
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
				

			t2 = new infoCounter('A', -1);
			if (QueueManagement.isSomeoneWaiting(q, s) > 0) { 
				
				
				
				if (QueueManagement.isSomeoneWaiting(q, s) > 1)  // più code con attesa maggiore a 20 minuti
				{
					t2 = sleeping.search('D');//tentativo di apertura di un sportello polifunzionale
					
					if (t2.getNum() == -1) {
						for (int j = 0; j < 3; j++) {/*tentativo di apertura dei sportelli specializzati per le code
						 							con attesa maggiore a 20 minuti*/
							s[j].p();
							if (q[j].getAvg() >= 10) {
								closeCounter(sleeping.search((char) ('A' + j)));
							}
							s[j].v();
						}
					}
				} else {
					t2 = sleeping.search((char)(65 + QueueManagement.getIndexBlockedQueue(q, s)));
					{/*tentativo di apertura dello sportello specializzato per la coda
							con attesa maggiore a 20 minuti*/
					closeCounter(t2);
				}	
				
			}
				
			try {
				Thread.sleep(1000); //reduce CPU usage
			} catch (InterruptedException e) {
				System.out.println("ERROR in counterSleeper sleep after search");
				throw new RuntimeException();
			}
		}
		}
	}


public void closeCounter(infoCounter t) {
	if (t.getNum() != -1) {
		try {
			if (t.getSocket() == null)
				t.setSock(counter.search(t, false).getSocket());
			
			PrintWriter p = new PrintWriter(t.getSocket().getOutputStream());
			sleeping.rm(t);
			t.setT(QueueManagement.getNow());
			working.in(t);
			p.println("i" + t.getNum());
			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
}