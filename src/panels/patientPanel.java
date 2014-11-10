/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package panels;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JRadioButton;

import execptions.CancelOptionSelected;
import execptions.IncorrectInputException;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.DiseaseManipulator;
import nodes.DiseaseNode;
import nodes.PatientNode;

/******************************** Patient Panel *********************************/

public class patientPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldFirstName, textFieldMiddleName,
			textFieldLastName, textFieldSsn, textFieldAge, textFieldCity,
			textFieldState;
	private JTextField textFieldYear;
	private JRadioButton rdbtnMale, rdbtnFemale;
	private Choice choiceMonth, choiceDay;
	private JComboBox<DiseaseNode> comboBoxDisease;
	private JComboBox<PatientNode> comboBoxInfector;
	private JComboBox<Integer> comboBoxExposee;
	private DiseaseManipulator diseaseModel;


	/*The DiseaseManipulator object provides important information about what 
	 * patients and disease exist currently exist in the search tree.*/
	public patientPanel(DiseaseManipulator dm) throws IncorrectInputException {
		
		this.diseaseModel = dm;

		/************************Panel***********************/
		
		Dimension minimumSize = new Dimension(350, 400);
		setPreferredSize(new Dimension(350, 527));
		setMinimumSize(minimumSize);
		setBackground(Color.DARK_GRAY);
		setForeground(new Color(0, 255, 0));
		setLayout(null);

		/************************Labels***********************/
		
		JLabel lblFirstName = new JLabel("First Name");
			lblFirstName.setForeground(new Color(0, 255, 0));
			lblFirstName.setBackground(new Color(0, 255, 0));
			lblFirstName.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblFirstName.setBounds(10, 11, 100, 25);
		add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
			lblMiddleName.setForeground(new Color(0, 255, 0));
			lblMiddleName.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblMiddleName.setBounds(10, 47, 100, 25);
		add(lblMiddleName);
		
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
		
		JLabel lblInputPatientInfo = new JLabel("INPUT PATIENT INFO");
			lblInputPatientInfo.setForeground(Color.GREEN);
			lblInputPatientInfo.setFont(new Font("DialogInput", Font.BOLD, 20));
			lblInputPatientInfo.setBounds(54, 491, 261, 25);
		add(lblInputPatientInfo);
		
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
		
		JLabel lblDisease = new JLabel("Disease");
			lblDisease.setForeground(Color.GREEN);
			lblDisease.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblDisease.setBounds(10, 364, 100, 25);
		add(lblDisease);
		
		JLabel lblExposeeNumber = new JLabel("Exposee Number");
			lblExposeeNumber.setForeground(Color.GREEN);
			lblExposeeNumber.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblExposeeNumber.setBounds(10, 455, 118, 25);
		add(lblExposeeNumber);
		
		JLabel lblInfector = new JLabel("Infector");
			lblInfector.setForeground(Color.GREEN);
			lblInfector.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblInfector.setBounds(10, 407, 118, 25);
		add(lblInfector);
		
		/************************ Text Fields ***********************/
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(140, 12, 175, 25);
		add(textFieldFirstName);
		textFieldFirstName.setColumns(10);

		textFieldMiddleName = new JTextField();
		textFieldMiddleName.setBounds(140, 48, 175, 25);
		add(textFieldMiddleName);
		textFieldMiddleName.setColumns(10);

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

		textFieldYear = new JTextField();
		textFieldYear.setFont(new Font("DialogInput", Font.PLAIN, 11));
		textFieldYear.setForeground(Color.DARK_GRAY);
		textFieldYear.setBounds(201, 333, 74, 20);
		add(textFieldYear);
		textFieldYear.setColumns(10);
		
		/************************ Radio Buttons ***********************/
		
		rdbtnMale = new JRadioButton("MALE");
		rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnMale.setForeground(new Color(0, 255, 0));
		rdbtnMale.setBackground(Color.DARK_GRAY);
		rdbtnMale.setBounds(140, 193, 74, 23);
		add(rdbtnMale);
		rdbtnMale.setSelected(true);

		rdbtnFemale = new JRadioButton("FEMALE");
		rdbtnFemale.setForeground(Color.GREEN);
		rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFemale.setBackground(Color.DARK_GRAY);
		rdbtnFemale.setBounds(235, 193, 109, 23);
		add(rdbtnFemale);

		ButtonGroup genderButtons = new ButtonGroup();
		genderButtons.add(rdbtnMale);
		genderButtons.add(rdbtnFemale);

		/************************ Choices ***********************/
		choiceMonth = new Choice();
		choiceMonth.setFont(new Font("DialogInput", Font.PLAIN, 12));
		choiceMonth.setForeground(Color.DARK_GRAY);
		choiceMonth.setBounds(183, 304, 46, 20);
		add(choiceMonth);
		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12" };
		for (int i = 0; i < 12; i++) {
			choiceMonth.add(months[i]);
		}

		choiceDay = new Choice();
		choiceDay.setFont(new Font("DialogInput", Font.PLAIN, 12));
		choiceDay.setForeground(Color.DARK_GRAY);
		choiceDay.setBounds(276, 304, 39, 20);
		add(choiceDay);
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };
		for (int i = 0; i < 31; i++) {
			choiceDay.add(days[i]);
		}

		

		

		
		/************************ Combo Boxes ***********************/
		comboBoxDisease = new JComboBox<DiseaseNode>();
		comboBoxDisease.setBounds(140, 367, 175, 25);
		add(comboBoxDisease);

		comboBoxInfector = new JComboBox<PatientNode>();
		comboBoxInfector.setBounds(140, 410, 175, 25);
		add(comboBoxInfector);

		comboBoxExposee = new JComboBox<Integer>();
		comboBoxExposee.setBounds(201, 456, 74, 25);
		add(comboBoxExposee);
		
		
		/************************ Set Initial Combo Box Values ***********************/
		for (DiseaseNode disease : dm.getAllDiseases()) {
			comboBoxDisease.addItem(disease);
		}

		comboBoxDisease.addActionListener(this);
		try {
			comboBoxDisease.setSelectedIndex(0);
		} catch (IllegalArgumentException iae) {
			throw new IncorrectInputException(
					"No Disease data to add to. Make sure you read in a file first.");
		}

		comboBoxInfector.addActionListener(this);
		try {
			comboBoxInfector.setSelectedIndex(0);
		} catch (IllegalArgumentException iae) {
			throw new IncorrectInputException(
					"No Disease data to add to. Make sure you read in a file first.");
		}

	}

	/************************ "Getter" Methods ***********************/

	private String getSsn() throws IncorrectInputException {
		String trySsn = textFieldSsn.getText();
		try {
			@SuppressWarnings("unused")
			int isSsn = Integer.parseInt(trySsn);
			if (trySsn.length() != 9)
				throw new IncorrectInputException(
						"Social Security Numbers must be 9 didgetgs long.");
			return textFieldSsn.getText();
		} catch (InputMismatchException ime) {
			throw new IncorrectInputException(
					"Incorect Social Security Number. Make sure it only contains numbers.");
		}
	}

	private int getAge() throws IncorrectInputException { // returns -1 if
															// entered an
															// impossible age,
															// -2 if
		// given a string that cannot be an int
		try {
			int age = Integer.parseInt(textFieldAge.getText());
			if (age >= 0 && age < 125)
				return age;
			else {
				throw new IncorrectInputException(
						"Impossible age for patient (greater than 125 or less than 0)");
			}
		} catch (InputMismatchException ime) {
			throw new IncorrectInputException(
					"Incorrectly formated entry in AGE field. Make sure AGE is a number and contains no letters.");
		}
	}

	/* NEED TO FIX TO IDENTIFY IF A CORRECT GENDER WAS ENTERED */

	private String getGender() {
		if (rdbtnMale.isSelected())
			return "M";
		else
			return "F";
	}

	private String getDisease() {
		return comboBoxDisease.getSelectedItem().toString();
	}

	private int getExposeeNumber() throws IncorrectInputException {
		Integer number = (Integer) comboBoxExposee.getSelectedItem();
		return number.intValue();

	}

	private PatientNode getInfector() {
		return (PatientNode) comboBoxInfector.getSelectedItem();
	}

	/*The only part of the date that the user can type a completely wrong
	 * input is in the year. This is checked for by attempting to convert
	 * the string to an in, and if this doesn't work, then an exception is 
	 * thrown. However, this doesn't stop the user from putting in a stupid 
	 * date like -10000000 or setting the persons infection dated to the 31st of 
	 * February.
	 */
	private String getDate() throws IncorrectInputException {
		String tryYear = textFieldYear.getText();
		try {
			int year = Integer.parseInt(tryYear);
			return (choiceMonth.getSelectedItem() + "."
					+ choiceDay.getSelectedItem() + "." + year);
		} catch (InputMismatchException ime) {
			throw new IncorrectInputException("Year input is not a number.");
		} catch (NumberFormatException nfe) {
			throw new IncorrectInputException("Year input is not a number.");
		}

	}

	
	
	/*********************************** Patient Prompt************************************/
	 /* The Patient Prompt method displays a PatientPanel
	 * and then gets user input about 
	 */
	public static String[] patientPrompt(DiseaseManipulator dm)
			throws IncorrectInputException, CancelOptionSelected {
		
		patientPanel prompt = new patientPanel(dm);
		String[] toReturn = null;
		String[] options = { "ENTER", "CANCEL" };
		int promptSelect = JOptionPane.showOptionDialog(null, prompt, "VIRUS",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, "ENTER");
		if (promptSelect == JOptionPane.OK_OPTION) {
			String patientData = prompt.textFieldFirstName.getText() + "/" + prompt.textFieldMiddleName.getText()
					+ "/" + prompt.textFieldLastName.getText() + "/" + prompt.getSsn() + "/"
					+ prompt.getAge() + "/" + prompt.getGender() + "/"
					+ prompt.textFieldCity.getText() + "/" + prompt.textFieldState.getText() + "/"
					+ prompt.getDate() + "/";
			String disease = prompt.getDisease();
			String exposeeNumber = prompt.getExposeeNumber() + "";
			String infector = prompt.getInfector().toString();
			toReturn = new String[] { patientData, disease, infector,
					exposeeNumber };
			return toReturn;
		} else 
			throw new CancelOptionSelected();
		/*All "Cancel" options in this program throw a custom exception which is caught at the 
		ActionListener object in the controller. This makes sure the program doesn't go into
		any data at all if the 'Cancel' option is selected.*/
	}

	
	/*********************************** Action Listeners ************************************/
	/* The two action listeners on ComboBoxes are used to 
	 * set the contents of those ComboBoxes to the appropriate 
	 * values to eliminate user error. See methods below.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == this.comboBoxDisease)
				this.setPatients(diseaseModel);
			if (e.getSource() == this.comboBoxInfector)
				this.setExposee();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Error: " + nfe.getMessage(), "Number Format Exception", JOptionPane.ERROR_MESSAGE);
		} catch (InterruptedException ie) {
			JOptionPane.showMessageDialog(null, "Error: " + ie.getMessage(), "Interrupted Exception", JOptionPane.ERROR_MESSAGE);
		} catch (IncorrectInputException iie){
			JOptionPane.showMessageDialog(null, "Error: " + iie.getMessage(), "Incorrect Input Exception", JOptionPane.ERROR_MESSAGE);
		}

	}

	/*********************************** Set Methods ************************************/
	/* setPatients gets all the available Patients from the model (DiseaseManipulator)
	 * of the program for a given Disease.*/
	
	private void setPatients(DiseaseManipulator dm) throws IncorrectInputException {

			DiseaseNode disease = (DiseaseNode) comboBoxDisease.getSelectedItem();

			ArrayList<PatientNode> getPatients = dm.getAllInfectedBy(disease.getPatientZero(), disease);

			comboBoxInfector.removeAllItems();
			
			for (PatientNode patient : getPatients) {
				comboBoxInfector.addItem(patient);
			}
	}

	/*setExposee gets the available insert values for a selected
	 * patient, with 1 being the very first Exposee of a patient.
	 */
	private void setExposee() throws NumberFormatException,
			InterruptedException {
		/*This method is triggered multiple times during the
		 * firing of the setPatients method. This if statement makes
		 * sure that it only fires all the way once the comboBox has been populated.
		 */
		if (comboBoxInfector.getSelectedItem() == null)
			return;
		
		PatientNode getPatient = (PatientNode) comboBoxInfector
				.getSelectedItem();
		
		ArrayList<PatientNode> temp = getPatient.getInfected();
		
		int possibleIndexes = 0;
		
		if (temp != null) possibleIndexes = temp.size();
		
		comboBoxExposee.removeAllItems();
		for (int i = 0; i <= possibleIndexes; i++) {
			comboBoxExposee.addItem(new Integer(i + 1));
		}

	}
}
