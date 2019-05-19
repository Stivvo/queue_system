import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;

public class counterConnector extends Thread{
	private ServerSocket ss;
	private queue[] q;
	private Semaforo[] s;
	private JLabel[] LS;
	private JLabel[] LC;
	private JLabel[] LW;
	
	public counterConnector(queue[] q1, Semaforo[] s1, JLabel[] LS, JLabel[] LC, JLabel[] LW) 
			throws IOException {
		super();
		setName("counterConnector");
		q = q1;
		s = s1;
		this.LS = LS;
		this.LC = LC;
		this.LW = LW;
		
		ss = new ServerSocket(8045);
	}
	
	public void run() {
		Socket sock;
		for(;;) {
			serverCounter thSCounter;
			try {
				 sock = ss.accept();
				 thSCounter = new serverCounter(q, s, LS, LC, LW, sock);
				 thSCounter.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
