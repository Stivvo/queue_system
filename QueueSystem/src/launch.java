public class launch 
{
	public static void main(String[] args) 
	{
		runTests(); //uncomment if you don't want running tests
		
		String[] arg = {"a", "b"};
		Semaforo s = new Semaforo(1);
		
		//try {
		s.p();
		serverApplication.main(arg);
		s.v();
		/*System.out.println("serverapplication");
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("serverapplication BLOCK");
			Thread.currentThread().interrupt();
			return;
		}*/
		
		//try {
			s.p();
			Delaer.main(arg);
			s.v();
			/*System.out.println("dealer");
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("dealer BLOCK");
			Thread.currentThread().interrupt();
			return;
		}*/
		
		/*s.p();
		sportelloAvanzato.main(arg);
		s.v();*/
		//System.out.println("sportelloavanzato");
	}
	
	public static void runTests()
	{
		Test t = new Test();
		
		if (t.tQueue())
			System.out.println("queue WORKING\n");
		else
			System.out.println("queue NOT working\n");
		
		if (t.tList())
			System.out.println("list WORKING\n");
		else
			System.out.println("list NOT working\n");
	}
}
/*80 45?
8076?*/