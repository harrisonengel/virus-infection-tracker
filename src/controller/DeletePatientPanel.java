package controller;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class DeletePatientPanel extends JPanel {
	private JTextField textFieldDisease;
	private JTextField textFieldName;


	public DeletePatientPanel() {
		setPreferredSize(new Dimension(425, 150));
		setBackground(Color.GRAY);
		setForeground(Color.GREEN);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REMOVE PATIENT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DialogInput", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(10, 11, 430, 33);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Disease");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("DialogInput", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(37, 54, 100, 18);
		add(lblNewLabel_1);
		
		JLabel lblPatientName = new JLabel("Patient Name");
		lblPatientName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPatientName.setForeground(Color.GREEN);
		lblPatientName.setFont(new Font("DialogInput", Font.PLAIN, 16));
		lblPatientName.setBounds(-1, 99, 138, 18);
		add(lblPatientName);
		
		textFieldDisease = new JTextField();
		textFieldDisease.setBounds(179, 55, 194, 20);
		add(textFieldDisease);
		textFieldDisease.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(179, 97, 194, 20);
		add(textFieldName);

	}
	
	public static String[] deletePatientPrompt(){
		DeletePatientPanel newPanel = new DeletePatientPanel();
		String[] options = { "ENTER", "CANCEL" };
		int promptSelect = JOptionPane.showOptionDialog(null, newPanel,
				"VIRUS", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, "ENTER");
		if (promptSelect == JOptionPane.OK_OPTION){
			newPanel.setVisible(true);
			String patientName = newPanel.textFieldName.getText().trim();
			String diseaseName = newPanel.textFieldDisease.getText().trim();
			return (new String[]{patientName, diseaseName});
		} else return null; //TODO catch statement/do something for null
	}

}
