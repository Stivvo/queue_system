public class launch 
{
	public static void main(String[] args) 
	{
		serverApplication.main();
		System.out.println("serverapplication");
		
		Delaer.main();
		System.out.println("dealer");
		
		// qui si genera un exception
		
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Exception in sleep MAIN");
		    Thread.currentThread().interrupt();
		}
		
		sportelloAvanzato.main();
		System.out.println("sportelloavanzato");
		
		/*sportelloAvanzato.main();
		System.out.println("sportelloavanzato");*/
	}
}
