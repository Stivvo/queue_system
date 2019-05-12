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
	private JLabel l1, l2, l3;
	
	public serverCounter(queue[] q1, Semaforo[] s1, JLabel L1, JLabel L2, JLabel L3) throws IOException {
		super();
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = q1[i];
			s[i] = s1[i];
		}
		
		l1 = L1;
		l2 = L2;
		l3 = L3;
		
		ss = new ServerSocket(8045);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run()  
	{
		int operate = 0;
		int i = 0, j = 0;
		while (true) 
		{
			try {
				operate = reader.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			i = operate - 65;
			
			if (i == 4 || i == 5)
			{
				j = 0;
				
				while (j < 3 &&
						q[j].rear().getInfo().getT().getEpochSecond() - 
						q[j].front().getInfo().getT().getEpochSecond()
						<= 1200)
					j++;
				
				if (j == 3)
					j = 1;
			}
			else 
				j = i;
			
			s[j].p();
			
			if (!q[j].isEmpty())
				switch (j)
				{
				case 0:
					l1.setText("" + q[j].NEXT());
					break;
				case 1:
					l2.setText("" + q[j].NEXT());
					break;
				case 2:
					l3.setText("" + q[j].NEXT());
					break;
				}
				
			
			s[j].v();
			System.out.println(operate + "\n");
		}
		
	}
}
