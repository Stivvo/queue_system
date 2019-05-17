public class launch 
{
	public static void main(String[] args) 
	{
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
		
		s.p();
		sportelloAvanzato.main(arg);
		s.v();
		//System.out.println("sportelloavanzato");
	}
}
/*80 45?
8076?*/

//quando si avviano singolarmente, poi la console di eclipse si spegne e si riaccende due volte