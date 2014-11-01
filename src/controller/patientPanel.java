package controller;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JTextField;

import java.util.InputMismatchException;

import javax.swing.JRadioButton;

import java.awt.Choice;
import java.awt.Label;

public class patientPanel extends JPanel {
	private JTextField textFieldFirstName;
	private JTextField textFieldMiddleName;
	private JTextField textFieldLastName;
	private JTextField textFieldSsn;
	private JTextField textFieldAge;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JLabel lblInputPatientInfo;
	private JTextField textFieldYear;
	private JRadioButton rdbtnMale;
	private Choice choiceMonth;
	private Choice choiceDay;

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

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(140, 228, 175, 25);
		add(textFieldCity);

		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(140, 266, 175, 25);
		add(textFieldState);

		lblInputPatientInfo = new JLabel("INPUT PATIENT INFO");
		lblInputPatientInfo.setForeground(Color.GREEN);
		lblInputPatientInfo.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblInputPatientInfo.setBounds(54, 364, 261, 25);
		add(lblInputPatientInfo);
		
		rdbtnMale = new JRadioButton("MALE");
		rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnMale.setForeground(new Color(0, 255, 0));
		rdbtnMale.setBackground(Color.DARK_GRAY);
		rdbtnMale.setBounds(140, 193, 74, 23);
		add(rdbtnMale);
		rdbtnMale.setSelected(true);
		
		JRadioButton rdbtnFemale = new JRadioButton("FEMALE");
		rdbtnFemale.setForeground(Color.GREEN);
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFemale.setBackground(Color.DARK_GRAY);
		rdbtnFemale.setBounds(235, 193, 109, 23);
		add(rdbtnFemale);
		
		
		ButtonGroup genderButtons = new ButtonGroup();
		genderButtons.add(rdbtnMale);
		genderButtons.add(rdbtnFemale);
		
		
		choiceMonth = new Choice();
		choiceMonth.setFont(new Font("DialogInput", Font.PLAIN, 12));
		choiceMonth.setForeground(Color.DARK_GRAY);
		choiceMonth.setBounds(183, 304, 46, 20);
		add(choiceMonth);
		String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		for(int i=0; i<12; i++){
			choiceMonth.add(months[i]);
		}
		
		choiceDay = new Choice();
		choiceDay.setFont(new Font("DialogInput", Font.PLAIN, 12));
		choiceDay.setForeground(Color.DARK_GRAY);
		choiceDay.setBounds(276, 304, 39, 20);
		add(choiceDay);
		String[] days = {"01","02","03","04","05","06","07","08","09","10","11","12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31"};
		for (int i=0; i<31; i++){
		choiceDay.add(days[i]);
		}

		
		JLabel lblMm = new JLabel("month");
		lblMm.setFont(new Font("DialogInput", Font.PLAIN, 14));
		lblMm.setForeground(new Color(0, 255, 0));
		lblMm.setBounds(140, 310, 46, 14);
		add(lblMm);
		
		JLabel lblDd = new JLabel("day");
		lblDd.setForeground(Color.GREEN);
		lblDd.setFont(new Font("DialogInput", Font.PLAIN, 14));
		lblDd.setBounds(251, 310, 46, 14);
		add(lblDd);
		
		JLabel lblYear = new JLabel("year");
		lblYear.setForeground(Color.GREEN);
		lblYear.setFont(new Font("DialogInput", Font.PLAIN, 14));
		lblYear.setBounds(168, 339, 46, 14);
		add(lblYear);
		
		textFieldYear= new JTextField();
		textFieldYear.setFont(new Font("DialogInput", Font.PLAIN, 11));
		textFieldYear.setForeground(Color.DARK_GRAY);
		textFieldYear.setBounds(201, 333, 74, 20);
		add(textFieldYear);
		textFieldYear.setColumns(10);
		

	}

	private String getFirst() {
		return textFieldFirstName.getText();
	}

	private String getLast() {
		return textFieldLastName.getText();
	}

	private String getMiddle() {
		return textFieldMiddleName.getText();
	}

	private String getSsn() {
		String trySsn = textFieldSsn.getText();
		try{
			int isSsn = Integer.parseInt(trySsn);
			if (trySsn.length() != 9) return null;
			return textFieldSsn.getText();
		} catch(InputMismatchException ime){
			JOptionPane.showMessageDialog(null,
					"Incorect Social Security Number. Make sure it is 9 didgets long and only contains numbers.");
			return null;
		}
	}

	public int getAge() { // returns -1 if entered an impossible age, -2 if
							// given a string that cannot be an int
		try {
			int age = Integer.parseInt(textFieldAge.getText());
			if (age >= 0 && age < 125)
				return age;
			else{
				JOptionPane.showMessageDialog(null,
						"Impossible age for patient (greater than 125 or less than 0)");
				return -1;
			}
		} catch (InputMismatchException ime) {
			JOptionPane.showMessageDialog(null,
					"Incorrectly formated entry in AGE field. Make sure AGE is a number and contains no letters.");
			return -2;
		}
	}

	/* NEED TO FIX TO IDENTIFY IF A CORRECT GENDER WAS ENTERED */

	public String getGender() {
		if (rdbtnMale.isSelected())return "M";
		else return "F";
	}

	public String getCity() {
		return textFieldCity.getText();
	}

	public String getState() {
		return textFieldState.getText();
	}

	// NEED TO FIX TO IDENTIFY IF A CORRECT DATE WAS ENTERED
	public String getDate() {
		String tryYear = textFieldYear.getText();
		try {
			int year = Integer.parseInt(tryYear);
			return (choiceMonth.getSelectedItem() + "." +
					choiceDay.getSelectedItem() + "." + year);
		} catch (InputMismatchException ime){
			return null;
		}
		
	}

	/*
	 * Bug: Throws error message on X.
	 */
	public static String patientPrompt() {
		patientPanel prompt = new patientPanel();

		try {
			String[] options = { "ENTER", "CANCEL" };
			int promptSelect = JOptionPane.showOptionDialog(null, prompt,
					"VIRUS", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, "ENTER");
			if (promptSelect == JOptionPane.OK_OPTION){
			return (prompt.getFirst() + "/" + prompt.getMiddle() + "/"
					+ prompt.getLast() + "/" + prompt.getSsn() + "/"
					+ prompt.getAge() + "/" + prompt.getGender() + "/"
					+ prompt.getCity() + "/" + prompt.getState() + "/"
					+ prompt.getDate() + "/");
			} else return "NULL";
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
