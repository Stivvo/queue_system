import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private list working;
	private list counter;
	private Semaforo mutexL;
	
	public counterConnector(
			queue[] q1, Semaforo[] s1, JLabel[] LS, JLabel[] LC, JLabel[] LW,
			list working, list counter, Semaforo mutexL)
			throws IOException {
		super();
		setName("counterConnector");
		q = q1;
		s = s1;
		this.counter = counter;
		this.LS = LS;
		this.LC = LC;
		this.LW = LW;
		this.mutexL = mutexL;
		this.working = working;
		ss = new ServerSocket(8045);
	}
	
	public void run() {
		Socket sock;
		for(;;) {
			serverCounter thSCounter;

			try {
				sock = ss.accept();
				if (sock == null)
					System.out.println("ss.accept() == null ");
				BufferedReader read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String readed = read.readLine();
				thSCounter = new serverCounter(q, s, LS, LC, LW, sock, working);
				
				
				infoCounter counter = new infoCounter(
					readed.charAt(0), Integer.parseInt
						(readed.substring(1)), QueueManagement.getNow(), sock);
				
				mutexL.p();
				working.in(counter);
				this.counter.in(new infoCounter(
					readed.charAt(0), Integer.parseInt
						(readed.substring(1)), QueueManagement.getNow(), sock));
				mutexL.v();
				 
				thSCounter.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
