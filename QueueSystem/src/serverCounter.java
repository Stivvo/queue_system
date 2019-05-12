import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JLabel;

public class serverCounter extends Thread {
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];
	private JLabel l[] = new JLabel[3];
	private JLabel LC[] = new JLabel[3];
	
	public serverCounter(queue[] q1, Semaforo[] s1, JLabel L1, JLabel L2, JLabel L3, JLabel LC1, JLabel LC2, JLabel LC3) throws IOException {
		super();
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = q1[i];
			s[i] = s1[i];
			
			switch (i)
			{
			case 0:
				l[0] = L1;
				LC[0] = LC1;
				break;
			case 1:
				l[1] = L2;
				LC[1] = LC2;
				break;
			case 2:
				l[2] = L3;
				LC[2] = LC3;
				break;
			}
		}
		ss = new ServerSocket(8045);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run()  
	{
		int operate = 0;
		int i = 0, j = 0;
		int flag = 0;
		while (true) 
		{
			try {
				operate = reader.read();
			} catch (SocketException e) {
				try {
					sock.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				flag = 1;
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			if (flag == 1)
				break;
			
			i = operate - 65;
			
			if (i == 3 || i == 4)
			{
				j = 0;
				
				while (j < 3 &&
						q[j].rear().getInfo().getT().getEpochSecond() - 
						q[j].front().getInfo().getT().getEpochSecond()
						<= 1)
					j++;
				
				if (j == 3)
					j = 1;
			}
			else 
				j = i;
			
			System.out.println("polifunzionale " + j);
			s[j].p();
			
			if (!q[j].isEmpty())
			{
				l[j].setText("" + q[j].NEXT());
				LC[j].setText(""+ (i+1));				
			}
			s[j].v();
			System.out.println(operate + "\n");
		}
		
	}
}
