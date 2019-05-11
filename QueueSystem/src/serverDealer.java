import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class serverDealer extends Thread{
	
	private ServerSocket ss;
	private Socket sock;
	private queue queueAdmistration;
	private queue queueCommunication;
	private queue queuePackage;
	
	private Semaforo mutexAdministration;
	private Semaforo mutexCommunication;
	private Semaforo mutexPackage;
	
	private BufferedReader reader;
	
	
	public serverDealer(queue queueA, queue queueC, queue queueP, 
			Semaforo mutexA, Semaforo mutexC, Semaforo mutexP) throws IOException {
		super();
		setName("ThreadServerDealer");
		queueAdmistration = queueA;
		queueCommunication = queueC;
		queuePackage = queueP;
		mutexAdministration = mutexA;
		mutexCommunication = mutexC;
		mutexPackage = mutexP;
		
		ss = new ServerSocket(8000);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	public void run() {
		String operate = "";
		while (true) {
			try {
				operate = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (operate.equals("newEntry Administration")) {
				mutexAdministration.p();
				queueAdmistration.NEWENTRY(1);
				mutexAdministration.v();
			}
			
			if (operate.equals("newEntry Communication")) {
				mutexCommunication.p();
				queueCommunication.NEWENTRY(1);
				mutexCommunication.v();
			}
			if (operate.equals("newEntry Package")) {
				mutexPackage.v();
				queuePackage.NEWENTRY(1);
				mutexPackage.v();
			}
		}
	}
}
