import java.io.IOException;
import java.io.BufferedReader;
import java.net.*;
import java.io.InputStreamReader;
import java.awt.*;

public class CounterReciver extends Thread{

	private CounterState active;
	private Frame F;
	private BufferedReader reader;
	private Socket s1;
	private int id;
	
	public CounterReciver(CounterState a, Frame f, int n, Socket S) {
		this.active = a;
		this.F = f;
		id = n;
		s1 = S;
		InputStreamReader inp;
		try {
			inp = new InputStreamReader(s1.getInputStream());
			reader = new BufferedReader(inp);
			System.out.println("Counter reader creato");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Counter Receiver creato ");
	}
	
	public void run()
	{
		String message = "";
		String off = "(UNACTIVE)";
		System.out.println("CounterReciver exists");
		
		while (true)
		{
			System.out.println("CounterReciver is working");
			try {
				message = reader.readLine();
				System.out.println("received");
				System.out.println("CounterReciver message: " + message);
			} catch (IOException e) {
				System.out.println("error reading message through BufferedReader");
			}
			if (Integer.parseInt(message.substring(1)) == this.id)
			{
				System.out.println("CounterReciver recived "+message);
				if (message.charAt(0) == 'd')
				{
					active.setState(false);
					F.setTitle(F.getTitle() + off);
					
				}
				else if (message.charAt(0) == 'i')
				{
					
					active.setState(true);
					F.setTitle(F.getTitle().substring(0, F.getTitle().indexOf(off)));
				}
				if (active.isActive())
					System.out.println("ATTIVA");
				else
					System.out.println("DISATTIVA");
			}
		}
	}
}
