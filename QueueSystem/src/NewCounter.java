import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class NewCounter extends Thread
{
	private JFrame frame;
	private Socket sock;
	private PrintWriter writer;
	private BufferedReader read;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {public void run() {
				try {
					NewCounter window = new NewCounter();
					window.frame.setVisible(true);
					window.connect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCounter() {
		initialize();
		connect();
	}

	public void createCounter(char c, JSpinner s)
	{
		infoCounter temp = new infoCounter(c, 
			Integer.valueOf(s.getValue().toString().toString()).intValue());
		
		writer.println(c+  "" +temp.getNum() );
		writer.flush();
		
		String readed = "";
		try {
			readed = read.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (readed.compareTo("ok") == 0) {
			String[] argh = { "" + temp.getType(), "" +temp.getNum() };

			try {
				Counter.main(argh);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void connect() 
	{
		try {
			sock = new Socket("localhost", 8055);
			writer = new PrintWriter(sock.getOutputStream());
			read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SpinnerModel sm = new SpinnerNumberModel(0, 0, 100, 1);
		
		JSpinner spinner = new JSpinner(sm);
		spinner.setBounds(41, 93, 105, 22);
		spinner.getModel();
		frame.getContentPane().add(spinner);
		
		JButton btnFinance = new JButton("Finance");
		
		btnFinance.setBounds(276, 130, 150, 27);
		frame.getContentPane().add(btnFinance);
		
		btnFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('A', spinner);
			}});
		
		JButton btnComunication = new JButton("Comunication");
		btnComunication.setBounds(276, 90, 150, 27);
		frame.getContentPane().add(btnComunication);
		
		btnComunication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('B', spinner);
			}});
		
		JButton btnPackage = new JButton("Package");
		btnPackage.setBounds(276, 169, 150, 27);
		frame.getContentPane().add(btnPackage);
		
		btnPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('C', spinner);
			}});
		
		JButton btnPolifunzione = new JButton("Multipurpose");
		btnPolifunzione.setBounds(276, 51, 150, 27);
		frame.getContentPane().add(btnPolifunzione);
		
		btnPolifunzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('D', spinner);
			}});
		
		JLabel lblNumeroSportello = new JLabel("Counter number");
		lblNumeroSportello.setBounds(41, 56, 150, 17);
		frame.getContentPane().add(lblNumeroSportello);
	}
}
