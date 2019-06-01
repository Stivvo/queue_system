import java.io.IOException;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.net.*;
import java.io.InputStreamReader;
import java.awt.*;

public class CounterReciver extends Thread{

	private Boolean active;
	private Frame F;
	private BufferedReader reader;
	private Socket s1;
	private int id;
	
	public CounterReciver(Boolean a, Frame f, int n) {
		this.active = a;
		this.F = f;
		id = n;
	}
	
	public void connectNewCounter() throws UnknownHostException, IOException {

		s1 = new Socket("localhost", 8055);
		
		InputStreamReader inp = new InputStreamReader(s1.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run()
	{
		String message = "";
		String off = " (UNACTIVE)";
		System.out.println("CounterReciver exists");
		
		while (true)
		{
			System.out.println("CounterReciver is working");
			try {
				message = reader.readLine();
				System.out.println("CounterReciver message: " + message);
			} catch (IOException e) {
				System.out.println("error reading message through BufferedReader");
			}
			if (Integer.valueOf(message.substring(1)).intValue() == this.id)
			{
				System.out.println("CounterReciver recived "+message);
				if (message.charAt(0) == 'd')
				{
					active = false;
					F.setTitle(F.getTitle() + off);
				}
				else if (message.charAt(0) == 'i')
				{
					active = true;
					F.setTitle(F.getTitle().substring(0, F.getTitle().indexOf(off)));
				}
				if (active)
					System.out.println("ATTIVA");
				else
					System.out.println("DISATTIVA");
			}
		}
	}
}
