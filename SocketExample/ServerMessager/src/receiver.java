import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
public class receiver extends Thread {
	private InputStreamReader inp;
	private BufferedReader reader;
	private JTextArea text;
	
	public receiver(JTextArea area, InputStream stream) {
		super();
		text = area;
		inp = new InputStreamReader(stream);
		reader = new BufferedReader(inp);
	}
	
	
	public void run() {
		while (true) {
			try {
				text.insert(reader.readLine() + "\n", 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}