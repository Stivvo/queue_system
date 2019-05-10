import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

import javax.swing.JTextField;

public class sender extends Thread{
	private JTextField text;
	private PrintWriter sender;
	private Semaphore mutex;
	public sender (JTextField textS, OutputStream send, Semaphore mute) {
		super();
		text = textS;
		sender = new PrintWriter(send);
		mutex = mute;
	}
	
	public void run() {
		while (true) {
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sender.println(text.getText());
			sender.flush();
		}
		
	}
	
	
}
