import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZonedDateTime;

import javax.swing.JLabel;

public class counterConnector extends Thread{
	private ServerSocket ss;
	private queue[] q;
	private Semaforo[] s;
	private JLabel[] LS;
	private JLabel[] LC;
	private JLabel[] LW;
	private list working;
	private Semaforo mutexL;
	private int[] nCounter;
	
	public counterConnector(
			queue[] q1, Semaforo[] s1, JLabel[] LS, JLabel[] LC, JLabel[] LW,
			list working, Semaforo mutexL, int[] nCounter)
			throws IOException {
		super();
		setName("counterConnector");
		q = q1;
		s = s1;
		this.LS = LS;
		this.LC = LC;
		this.LW = LW;
		this.working = working;
		this.nCounter = nCounter;
		ss = new ServerSocket(8045);
	}
	
	public void run() {
		Socket sock;
		for(;;) {
			serverCounter thSCounter;

			try {
				 sock = ss.accept();
				 thSCounter = new serverCounter(q, s, LS, LC, LW, sock);
				 BufferedReader read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				 String readed = read.readLine();
				 
				 int t =  readed.charAt(0) - 65;
				 
				 nCounter[t]++;
				 
				 infoCounter counter = new infoCounter(readed.charAt(0), Integer.parseInt(readed.substring(1)), ZonedDateTime.now(), sock);
				
				 mutexL.p();
				 working.in(counter);
				 mutexL.v();
				 
				 thSCounter.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
