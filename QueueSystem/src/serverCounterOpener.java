import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;


public class serverCounterOpener extends Thread {
	private list working;
	private list sleeping;
	private Semaforo mutexL;
	
	private PrintWriter write;
	private BufferedReader read;
	
	
	private Socket s;
	
	public serverCounterOpener(Socket sock, list working, list sleeping, Semaforo mutexList)   {
		super();
		s = sock;
		this.working = working;
		this.sleeping = sleeping;
		mutexL = mutexList;
		try {
			write = new PrintWriter(s.getOutputStream());
			read = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		while (true) {
			String toRead = " ";
			try {
				 toRead = read.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			infoCounter inf = new infoCounter(toRead.charAt(0),  Integer.parseInt(toRead.substring(1)));
		
			
			mutexL.p();
			
			if (working.search(inf.getNum()) == true || sleeping.search(inf.getNum()) == true) {
				write.println("already exists");
				
			} else {
			
					write.println("ok"); 
			}
			
			
			write.flush();
			mutexL.v();
		}
	}
	
}
