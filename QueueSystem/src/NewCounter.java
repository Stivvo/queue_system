import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class NewCounter {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCounter window = new NewCounter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewCounter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void createCounter(char c, JSpinner s)
	{
		//'A', Integer.valueOf(spinner.getValue().toString()).intValue()
		//should do some socket operations
		
		String[] argh = {"" + c, s.getValue().toString()}; 
		Counter.main(argh);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(41, 93, 105, 22);
		frame.getContentPane().add(spinner);
		
		JButton btnFinance = new JButton("Finance");
		btnFinance.setBounds(276, 130, 150, 27);
		frame.getContentPane().add(btnFinance);
		
		btnFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('A', spinner);
			}
		});
		
		JButton btnComunication = new JButton("Comunication");
		btnComunication.setBounds(276, 90, 150, 27);
		frame.getContentPane().add(btnComunication);
		
		btnComunication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('B', spinner);
			}
		});
		
		JButton btnPackage = new JButton("Package");
		btnPackage.setBounds(276, 169, 150, 27);
		frame.getContentPane().add(btnPackage);
		
		btnPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('C', spinner);
			}
		});
		
		JButton btnPolifunzione = new JButton("Multipurpose");
		btnPolifunzione.setBounds(276, 51, 150, 27);
		frame.getContentPane().add(btnPolifunzione);
		
		btnPolifunzione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCounter('D', spinner);
			}
		});
		
		JLabel lblNumeroSportello = new JLabel("Counter number");
		lblNumeroSportello.setBounds(41, 56, 150, 17);
		frame.getContentPane().add(lblNumeroSportello);
		
	}
}
