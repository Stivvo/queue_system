public class launch 
{
	public static void main(String[] args) 
	{
		serverApplication.main();
		System.out.println("serverapplication");
		
		try {
		    Thread.sleep(200);
		} catch (InterruptedException e) {
			System.out.println("serverapplication BLOCK");
			Thread.currentThread().interrupt();
		    return;
		}
		
		Delaer.main();
		System.out.println("dealer");
		
		try {
		    Thread.sleep(200);
		} catch (InterruptedException e) {
			System.out.println("dealer BLOCK");
			Thread.currentThread().interrupt();
		    return;
		}		
		
		try {
		    Thread.sleep(200);
		} catch (InterruptedException e) {
			System.out.println("dealer BLOCK");
			Thread.currentThread().interrupt();
		    return;
		}
		
		sportelloAvanzato.main();
		System.out.println("sportelloavanzato");
	}
}
/*80 45?
8076?*/