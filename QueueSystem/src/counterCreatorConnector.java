import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class counterCreatorConnector extends Thread{
	private ServerSocket ss;
	private list counter;
	private Semaforo mutexList;
	
	public counterCreatorConnector(list counter,Semaforo mutexList) throws IOException {
		this.mutexList = mutexList;
		this.counter = counter;
		ss = new ServerSocket(8055);
	}
	
	public void run() {
		for(;;) {
			serverCounterOpener thSOpener;
			Socket sock;
			try {
				 sock = ss.accept();
				 thSOpener = new serverCounterOpener(sock, counter, mutexList);
				 thSOpener.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
