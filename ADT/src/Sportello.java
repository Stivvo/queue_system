import java.util.Random;

import javax.swing.JLabel;

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
	JLabel l;
	
	public Sportello(queue q, Semaforo s, JLabel l)
	{//passare la coda da smistare e il numero della coda
		cont = 0;
		this.q = q;
		this.s = s;
		this.l = l;
		
		setName(Integer.valueOf(n).toString());
		n++;
		start();
	}
	
	public int getN() {return Integer.valueOf(getName()).intValue();}
	
	public void run()
	{	
		int t = 0;
		while(true)
		{
			if (!q.isEmpty())
			{
				Random rand = new Random();
				t = rand.nextInt(9000) + 100;
				s.p();
				l.setText(Integer.valueOf(q.NEXT()).toString());
				s.v();				
			}
			else
				t = 1000;
			
			try
			{
				Thread.sleep(t);
			}//devo dormire anche quando non faccio nulla altrimenti la cpu va al 100%
			catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}
