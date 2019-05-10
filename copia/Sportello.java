import java.util.Random;
import javax.swing.JLabel;

/**
 * @author stefano
 *
 */

public class Sportello extends Thread
{
	int cont; //numero di ticket prossimo da assegnare
	static char n = 'A'; //numero identificativo dello sportello
	queue q;
	Semaforo s;
	JLabel l;
	JLabel ln;
	
	public Sportello(queue q, Semaforo s, JLabel l, JLabel ln)
	{//passare la coda da smistare e il numero della coda
		cont = 0;
		this.q = q;
		this.s = s;
		this.l = l;
		this.ln = ln;
		
		setName(Character.valueOf(n).toString());
		n++;
		start();
	}
	
	public void run()
	{	
		int t = 0;
		String temp;
		
		while(true)
		{
			if (!q.isEmpty())
			{
				Random rand = new Random();
				t = rand.nextInt(7000) + 1000;
				
				
				s.p();
				temp = Integer.valueOf(q.NEXT()).toString();
				ln.setText(q.getDim() + "");
				s.v();
				
				for (int i = 0; i < 2; i++)
				{
					if (temp.length() < 3)
						temp = "0" + temp;
						
				}
				l.setText(getName() + temp);
			}
			else
				t = 2000;
			
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
