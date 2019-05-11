public class Semaforo 
{
	private int n;
	
	public Semaforo(int n)
	{
		this.n = n;
	}
	
	synchronized public void p()
	{ 
		while (n <= 0)
		{
			try 
			{ 
				wait();
			} 
			catch (InterruptedException e) 
			{
				System.out.println("Error in semaphore");
			}
		}
		n--;
	}
	
	synchronized public void v()
	{
		n++;
		notify();
	}
	
	public int getN() {return n;}
}
