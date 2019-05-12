import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class serverDealer extends Thread{
	
	private ServerSocket ss;
	private Socket sock;
	private BufferedReader reader;
	
	private queue[] q = new queue[3];
	private Semaforo[] s = new Semaforo[3];	
	
	public serverDealer(queue[] q1, Semaforo[] s1) throws IOException {
		super();
		setName("ThreadServerDealer");
		
		for (int i = 0; i < 3; i++)
		{
			q[i] = q1[i];
			s[i] = s1[i];
		}
		
		ss = new ServerSocket(8000);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run() {
		String operate = "";
		int i = 0;
		while (true) {
			try {
				operate = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch (operate.charAt(0)) 
			{
			case 'A':
				i = 0;
				break;
			case 'B':
				i = 1;
				break;
			case 'C':
				i = 2;
				break;
			}
			
			s[i].p();
			q[i].NEWENTRY(Integer.parseInt(operate.substring(1)));
			s[i].v();
		}
	}
}
