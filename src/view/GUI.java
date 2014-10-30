package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.patientPanel;

import java.awt.TextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JPanel panel_header = new JPanel();
		panel_header.setBackground(Color.GRAY);
		panel_header.setBounds(0, 0, 684, 156);
		frame.getContentPane().add(panel_header);
		panel_header.setLayout(null);

		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(215, -38, 259, 194);
		lblTitle.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus 2.jpg")));
		panel_header.add(lblTitle);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus3.jpg")));
		lblNewLabel.setBounds(10, 0, 205, 156);
		panel_header.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus3.jpg")));
		lblNewLabel_1.setBounds(474, 0, 200, 156);
		panel_header.add(lblNewLabel_1);

		JPanel panel_buttons = new JPanel();
		panel_buttons.setBackground(Color.DARK_GRAY);
		panel_buttons.setBounds(10, 167, 209, 345);
		frame.getContentPane().add(panel_buttons);
		panel_buttons.setLayout(null);

		JButton btnAdd = new JButton("ADD DISEASE TREES");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// getFileName() defined below.
					String fileName = getFileName(frame);
					if (fileName != null) {

					}
					// These catches are for users who either don't select a
					// file or select a file
					// that is in the wrong format (not txt, or not a properly
					// formatted txt.
				} catch (NullPointerException np) {
					JOptionPane
							.showMessageDialog(frame,
									"Error reading from file. Make sure it exists and is not corrupted!");
				} catch (NumberFormatException nfp) {
					JOptionPane
							.showMessageDialog(frame,
									"Error reading from file. Make sure it exists and is not corrupted!");
				}
			}
		});
		btnAdd.setBackground(new Color(0, 255, 0));
		btnAdd.setBounds(10, 11, 189, 38);
		panel_buttons.add(btnAdd);

		JButton btnNewButton = new JButton("ADD PATIENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String patientData = patientPanel.patientPrompt();
			}
		});
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setBounds(10, 63, 189, 38);
		panel_buttons.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("REMOVE PATIENT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String patientData = patientPanel.patientPrompt();
			}
		});
		btnNewButton_1.setBackground(new Color(0, 255, 0));
		btnNewButton_1.setBounds(10, 117, 189, 38);
		panel_buttons.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("SAVE TREES");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {

					} catch (IOException i) {
						JOptionPane
								.showMessageDialog(frame,
										"Error saving file. Make sure you are using a .txt extension!");
					}
				}
			}
		});
		btnNewButton_2.setBackground(new Color(0, 255, 0));
		btnNewButton_2.setBounds(10, 172, 189, 38);
		panel_buttons.add(btnNewButton_2);

		TextArea textAreaMessages = new TextArea();
		textAreaMessages.setForeground(new Color(255, 0, 0));
		textAreaMessages.setBackground(new Color(192, 192, 192));
		textAreaMessages.setBounds(0, 224, 209, 111);
		panel_buttons.add(textAreaMessages);

		JPanel panel_display = new JPanel();
		panel_display.setBackground(new Color(192, 192, 192));
		panel_display.setBounds(229, 167, 445, 334);
		frame.getContentPane().add(panel_display);

	}

	

}
