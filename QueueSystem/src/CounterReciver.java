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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		String message = "";
		String off = " (UNACTIVE)";
		
		while (true) {
			try {
				message = reader.readLine();
				System.out.println("CounterReciver message: " + message);
			} catch (IOException e) {
				System.out.println("error reading message through BufferedReader");
			}
			if (Integer.parseInt(message.substring(1)) == this.id) {
				
				if (message.charAt(0) == 'd') {
					active.setUnactive();
					F.setTitle(F.getTitle() + off);
				} else if (message.charAt(0) == 'i') {
					System.out.println("Sleeper told me to wake up, but i'm too lazy");
					active.setActive();
					F.setTitle(F.getTitle().substring(0, F.getTitle().indexOf(off)));
				}
				
				if (active.isActive())
					System.out.println("ATTIVA id: " + this.id);
				else
					System.out.println("DISATTIVA id: " + this.id);
			}
		}
	}
}
