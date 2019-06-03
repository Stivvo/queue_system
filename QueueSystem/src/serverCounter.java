import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JLabel;

public class serverCounter extends Thread {
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q;
	private Semaforo[] s;
	private JLabel LS[] = new JLabel[3];
	private JLabel LC[] = new JLabel[3];
	private JLabel LW[] = new JLabel[3];
	private list working = new list();
	
	public serverCounter(queue[] q1, Semaforo[] s1, JLabel[] LS,
		JLabel[] LC,JLabel[] LW, Socket socket, list working) throws IOException 
	{
		super();
		q = q1;
		s = s1;
		this.LS = LS;
		this.LC = LC;
		this.LW = LW;
		this.working = working;
		sock = socket;
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run()  
	{
		String operate = "";
		int i = 0, /*i diventa il tipo di sportello*/j = 0;
		int flag = 0;
		int sub = 0; //numero di sportello
		
		while (true) 
		{
			try {
				operate = reader.readLine();
				System.out.println("operate: " + operate);
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
			
			i = ((int)operate.charAt(0)) - 65;
			sub = Integer.valueOf(operate.substring(1));
			
			System.out.println("i = " + i + " sub: " + sub);
			
			if (i == 3 || i == 4)
			{
				if (!q[1].isEmpty() && QueueManagement.isSomeoneWaiting(q, s) == 0) {
					System.out.println("j = 1");
					j = 1;
				} else {
					j = QueueManagement.getIndexBlockedQueue(q, s);
					System.out.println("j2 = " + j);
				}	
			}
			else
				j = i;
			
			if (i >= 0 && i <= 4)
			{					
				s[j].p();
				
				if (!q[j].isEmpty())
				{
					System.out.println("ready to NEXT");
					
					LS[j].setText("" + q[j].NEXT());
					LC[j].setText("" + sub);
					LW[j].setText("" + q[j].getDim());
					
					infoCounter temp = new infoCounter(operate.charAt(0), sub);
					
					if (working.search(temp, true).getNum() == -1)
						System.out.println("Error while updating time");
					else
						System.out.println("updating " + temp.print());
				}
				s[j].v();
				System.out.println("operate: " + operate + "\n");
			}
		}
	}
}
