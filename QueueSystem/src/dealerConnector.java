import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;

public class dealerConnector extends Thread{
	private ServerSocket ss;
	private queue[] q;
	private Semaforo[] s;
	private JLabel[] LW;
	
	public dealerConnector(queue[] q1, Semaforo[] s1, JLabel[] LW) 
			throws IOException {
		super();
		setName("counterConnector");
		q = q1;
		s = s1;
		this.LW = LW;
		
		ss = new ServerSocket(8076);
	}
	
	public void run() {
		Socket sock;
		for(;;) {
			serverDealer thSCounter;
			try {
				 sock = ss.accept();
				 thSCounter = new serverDealer(q, s, LW, sock);
				 thSCounter.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
