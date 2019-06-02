public class launch 
{
	public static void main(String[] args) 
	{
		//runTests(); //uncomment if you  want running tests
		
		String[] arg = {"a", "b"};
		Semaforo s = new Semaforo(1);
		
		s.p();
		serverApplication.main(arg);
		s.v();

		s.p();
		Delaer.main(arg);
		s.v();
			
		s.p();
		NewCounter.main(arg);
		s.v();
	}
	
	public static void runTests()
	{
		Test t = new Test();
		
		if (t.tList())
			System.out.println("list WORKING\n");
		else
			System.out.println("list NOT working\n");
	}
}
