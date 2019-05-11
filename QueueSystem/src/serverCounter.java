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
	
	private queue queueAdmistration;
	private queue queueCommunication;
	private queue queuePackage;
	
	private Semaforo mutexAdministration;
	private Semaforo mutexCommunication;
	private Semaforo mutexPackage;
	
	private JLabel lAdministration;
	private JLabel lCommunication;
	private JLabel lPackage;
	
	public serverCounter(queue queueA, queue queueC, queue queueP, 
						Semaforo mutexA, Semaforo mutexC, Semaforo mutexP,
						JLabel lAdmin, JLabel lCom, JLabel lPack) throws IOException {
		super();
		
		queueAdmistration = queueA;
		queueCommunication = queueC;
		queuePackage = queueP;
		
		mutexAdministration = mutexA;
		mutexCommunication = mutexC;
		mutexPackage = mutexP;
		
		lAdministration = lAdmin;
		lCommunication = lCom;
		lPackage = lPack;
		
		ss = new ServerSocket(8045);
		sock = ss.accept();
		InputStreamReader inp = new InputStreamReader(sock.getInputStream());
		reader = new BufferedReader(inp);
	}
	
	
	public void run()  {
		String operate = " ";
		while (true) {
			try {
				operate = reader.readLine();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
			if (operate.equals("next Administration")) {
				mutexAdministration.p();
				lAdministration.setText(""+queueAdmistration.NEXT());
				mutexAdministration.v();
			}
			
			if (operate.equals("next Communication")) {
				mutexCommunication.p();
				lCommunication.setText(""+queueCommunication.NEXT());
				mutexCommunication.v();
			}
			if (operate.equals("next Package")) {
				mutexPackage.v();
				lPackage.setText(""+queuePackage.NEXT());
				mutexPackage.v();
			}
		}
		
	}
}
