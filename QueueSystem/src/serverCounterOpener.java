import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

public class serverCounterOpener extends Thread 
{
	private list working;
	private list sleeping;
	private Semaforo mutexL;
	
	private PrintWriter write;
	private BufferedReader read;
	private Socket s;
	
	public serverCounterOpener(Socket sock, list working, list sleeping, Semaforo mutexList)
	{
		super();
		s = sock;
		this.working = working;
		this.sleeping = sleeping;
		mutexL = mutexList;
		
		try {
			write = new PrintWriter(s.getOutputStream());
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void run() 
	{
		while (true) {
			String toRead = " ";
			try {
				 toRead = read.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			infoCounter inf = new infoCounter(
				toRead.charAt(0),
				Integer.valueOf(toRead.substring(1)).intValue());
		
			mutexL.p();
			
			if (working.search(inf.getNum()).getNum() != -1 ||
				sleeping.search(inf.getNum()).getNum() != -1)
				write.println("already exists");
			else
				write.println("ok"); 
						
			write.flush();
			mutexL.v();
		}
	}
	
}
