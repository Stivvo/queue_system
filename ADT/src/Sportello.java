import java.util.Random;

/**
 * @author stefano
 *
 */

public class Sportello extends Thread
{
	int cont; //numero di ticket prossimo da assegnare
	static int n = 0; //numero identificativo dello sportello
	queue q;
	Semaforo s;
	
	public Sportello(queue q, Semaforo s)
	{//passare la coda da smistare e il numero della coda
		cont = 0;
		this.q = q;
		this.s = s;
		
		setName(Integer.valueOf(n).toString());
		n++;
		start();
	}
	
	public int getN() {return Integer.valueOf(getName()).intValue();}
	
	public void run()
	{		
		while(!q.isEmpty())
		{
			Random rand = new Random();
			
			try
			{
				Thread.sleep(rand.nextInt(9000) + 1000);
			}
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
			
			s.p();
			System.out.println("Sportello: " + q.NEXT() + " servito");
			s.v();
		}
	}
}
