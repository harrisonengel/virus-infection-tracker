package controller;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.util.InputMismatchException;

public class patientPanel extends JPanel {
	private JTextField textFieldFirstName;
	private JTextField textFieldMiddleName;
	private JTextField textFieldLastName;
	private JTextField textFieldSsn;
	private JTextField textFieldAge;
	private JTextField textFieldGender;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldDate;
	private JLabel lblInputPatientInfo;

	/**
	 * Create the panel.
	 */
	public patientPanel() {

		Dimension minimumSize = new Dimension(350, 400);
		setPreferredSize(new Dimension(350, 400));
		setMinimumSize(minimumSize);

		setBackground(Color.DARK_GRAY);
		setForeground(new Color(0, 255, 0));
		setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(new Color(0, 255, 0));
		lblFirstName.setBackground(new Color(0, 255, 0));
		lblFirstName.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblFirstName.setBounds(10, 11, 100, 25);
		add(lblFirstName);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(140, 12, 175, 25);
		add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setForeground(new Color(0, 255, 0));
		lblMiddleName.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblMiddleName.setBounds(10, 47, 100, 25);
		add(lblMiddleName);

		textFieldMiddleName = new JTextField();
		textFieldMiddleName.setBounds(140, 48, 175, 25);
		add(textFieldMiddleName);
		textFieldMiddleName.setColumns(10);

		JLabel labelLastName = new JLabel("Last Name");
		labelLastName.setForeground(new Color(0, 255, 0));
		labelLastName.setFont(new Font("DialogInput", Font.BOLD, 14));
		labelLastName.setBounds(10, 83, 100, 25);
		add(labelLastName);

		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setForeground(new Color(0, 255, 0));
		lblSsn.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblSsn.setBounds(10, 119, 100, 25);
		add(lblSsn);

		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(new Color(0, 255, 0));
		lblAge.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblAge.setBounds(10, 155, 100, 25);
		add(lblAge);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(new Color(0, 255, 0));
		lblGender.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblGender.setBounds(10, 191, 100, 25);
		add(lblGender);

		JLabel lblInfectionCity = new JLabel("Infection City");
		lblInfectionCity.setForeground(new Color(0, 255, 0));
		lblInfectionCity.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblInfectionCity.setBounds(10, 227, 118, 25);
		add(lblInfectionCity);

		JLabel lblInfectionState = new JLabel("Infection State");
		lblInfectionState.setForeground(new Color(0, 255, 0));
		lblInfectionState.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblInfectionState.setBounds(10, 263, 128, 25);
		add(lblInfectionState);

		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(0, 255, 0));
		lblDate.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblDate.setBounds(10, 299, 100, 25);
		add(lblDate);

		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(140, 84, 175, 25);
		add(textFieldLastName);

		textFieldSsn = new JTextField();
		textFieldSsn.setColumns(10);
		textFieldSsn.setBounds(140, 120, 175, 25);
		add(textFieldSsn);

		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(140, 156, 175, 25);
		add(textFieldAge);

		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		textFieldGender.setBounds(140, 192, 175, 25);
		add(textFieldGender);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(140, 228, 175, 25);
		add(textFieldCity);

		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(140, 266, 175, 25);
		add(textFieldState);

		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(140, 300, 175, 25);
		add(textFieldDate);

		lblInputPatientInfo = new JLabel("INPUT PATIENT INFO");
		lblInputPatientInfo.setForeground(Color.GREEN);
		lblInputPatientInfo.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblInputPatientInfo.setBounds(54, 364, 261, 25);
		add(lblInputPatientInfo);

	}

	public String getFirst() {
		return textFieldFirstName.getText();
	}

	public String getLast() {
		return textFieldLastName.getText();
	}

	public String getMiddle() {
		return textFieldMiddleName.getText();
	}

	public String getSsn() {
		return textFieldSsn.getText();
	}

	public int getAge() { // returns -1 if entered an impossible age, -2 if
							// given a string that cannot be an int
		try {
			int age = Integer.parseInt(textFieldAge.getText());
			if (age >= 0 && age < 125)
				return age;
			else
				return -1;
		} catch (InputMismatchException ime) {
			return -2;
		}
	}

	/* NEED TO FIX TO IDENTIFY IF A CORRECT GENDER WAS ENTERED */

	public String getGender() {
		return textFieldGender.getText();
	}

	public String getCity() {
		return textFieldCity.getText();
	}

	public String getState() {
		return textFieldState.getText();
	}

	// NEED TO FIX TO IDENTIFY IF A CORRECT DATE WAS ENTERED
	public String getDate() {
		return textFieldDate.getText();
	}

	/*
	 * Bug: Throws error message on X.
	 */
	public static String patientPrompt() {
		patientPanel prompt = new patientPanel();

		try {
			JOptionPane.showConfirmDialog(null, prompt);

			return (prompt.getFirst() + "/" + prompt.getMiddle() + "/"
					+ prompt.getLast() + "/" + prompt.getSsn() + "/"
					+ prompt.getAge() + "/" + prompt.getGender() + "/"
					+ prompt.getCity() + "/" + prompt.getState() + "/"
					+ prompt.getDate() + "/");
		} catch (NumberFormatException nFE) {
			// Display error message saying something went terribly wrong!!
			JOptionPane
					.showMessageDialog(
							null,
							"Error, incorrect input. Make sure your age is an integer number, not a spelled out number (like \"two\").");
			return "ERROR";
		}

	}
}
