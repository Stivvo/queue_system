import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class counterCreatorConnector extends Thread{
	private ServerSocket ss;
	private list working;
	private list sleeping;
	private Semaforo mutexList;
	
	public counterCreatorConnector(list working, list sleeping,Semaforo mutexList) throws IOException {
		this.working = working;
		this.sleeping = sleeping;
		this.mutexList = mutexList;
		ss = new ServerSocket(8055);
	}
	
	public void run() {
		for(;;) {
			serverCounterOpener thSOpener;
			Socket sock;
			try {
				 sock = ss.accept();
				 thSOpener = new serverCounterOpener(sock, working, sleeping, mutexList);
				 thSOpener.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
