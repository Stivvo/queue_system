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
				
			} catch (IOException e) {
				System.out.println("error reading message through BufferedReader");
			}
			if (Integer.valueOf(message.substring(1)).intValue() == this.id) {
				
				if (message.charAt(0) == 'd') {
					active.setUnactive();
					F.setTitle(F.getTitle() + off);
				} else if (message.charAt(0) == 'i') {
					active.setActive();
					F.setTitle(F.getTitle().substring(0, F.getTitle().indexOf(off)));
				}
			}
		}
	}
}
