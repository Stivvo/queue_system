import java.io.IOException;
import java.io.PrintWriter;

public class counterSleeper extends Thread {
	
	public list working;
	public list sleeping;
	public int[] nCounter;
	public Semaforo mutexList;
	public queue[] q;
	public Semaforo[] s;
	
	public counterSleeper(list working, list sleeping, Semaforo mutexList, Semaforo[] s, queue[] q) {
		this.working = working;
		this.sleeping = sleeping;
		this.mutexList = mutexList;
		this.s = s;
		this.q = q;
	}
	
	public void run () 
	{
		infoCounter t = new infoCounter('A', -1);
		int i = 0;
		
		while (true)
		{ //Searches for inactive counters and closes them
			System.out.println("print working");
			working.stampa();
			System.out.println("print sleeping");
			sleeping.stampa();
			
			t.set(working.search());
			PrintWriter p;
			
			if (t.getNum() != -1) {					
				try {
					
					if (t == null)
						System.out.println("t null ");
					
					if (t.getSocket() == null)
						System.out.println("t socket null " + t.print());
					
					p = new PrintWriter(t.getSocket().getOutputStream());
					sleeping.in(t);
					working.rm(t);
					p.println("d" + t.getNum());
					p.flush();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
			int flag = QueueManagement.getIndexAvg(q, s);
			t = new infoCounter('A', -1);
			
			if (flag != -1) { 
				t.set(sleeping.search((char)(65 + flag)));
				
				if (t.getNum() == -1)  
				{//apro un polifunzionale se non c'è nessuno specifico oppure ci sono più code in attesa
					System.out.println("May open polifunc");
					t.set(sleeping.search('D'));
				}
				
				if (t.getNum() != -1) {
					try {			
						p = new PrintWriter(t.getSocket().getOutputStream());
						sleeping.rm(t);
						t.setT(QueueManagement.getNow());
						working.in(t);
						
						System.out.println("opening " + t.print());
						p.println("i" + t.getNum());
						p.flush();
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
