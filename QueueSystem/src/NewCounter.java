import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSlider;

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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnFinance = new JButton("Finance");
		btnFinance.setBounds(276, 130, 127, 27);
		frame.getContentPane().add(btnFinance);
		
		JButton btnComunication = new JButton("Comunication");
		btnComunication.setBounds(276, 90, 127, 27);
		frame.getContentPane().add(btnComunication);
		
		JButton btnPackage = new JButton("Package");
		btnPackage.setBounds(276, 169, 127, 27);
		frame.getContentPane().add(btnPackage);
		
		JButton btnPolifunzione = new JButton("Polifunzione");
		btnPolifunzione.setBounds(276, 51, 127, 27);
		frame.getContentPane().add(btnPolifunzione);
		
		JLabel lblNumeroSportello = new JLabel("Numero sportello");
		lblNumeroSportello.setBounds(41, 56, 105, 17);
		frame.getContentPane().add(lblNumeroSportello);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(41, 93, 105, 22);
		frame.getContentPane().add(spinner);
	}
}
