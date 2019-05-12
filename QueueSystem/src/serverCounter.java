import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;

public class serverCounter extends Thread {
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];
	private JLabel[] l = new JLabel[3];
	private String[] n = {"Administration", "Comunication", "Package"};
	
	public serverCounter(queue[] q1, Semaforo[] s1, JLabel l1, JLabel l2, JLabel l3) throws IOException {
		super();
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = q1[i];
			s[i] = s1[i];
			switch (i)
			{
			case 0:
				l[i] = l1;
				break;
			case 1:
				l[i] = l2;
				break;
			case 2:
				l[i] = l3;
				break;
			}
		}
		
		ss = new ServerSocket(8045);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	
	public void run()  {
		int i = 0;
		int operate = 0;
		while (true) {
			try {
				operate = reader.read();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
			i = operate - 65;
			
			s[i].p();
			if (!q[i].isEmpty()) {
				l[i].setText(""+q[i].NEXT());
			}
			
			s[i].v();
			System.out.println(operate + "\n");
		}
		
	}
}
