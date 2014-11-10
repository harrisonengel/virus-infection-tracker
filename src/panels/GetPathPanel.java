package panels;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import execptions.CancelOptionSelected;
import execptions.IncorrectInputException;

import javax.swing.JComboBox;

import nodes.DiseaseNode;
import nodes.PatientNode;
import model.DiseaseManipulator;


public class GetPathPanel extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;

	public JLabel lblTitle;
	private JComboBox<DiseaseNode> comboBoxDisease;
	private JComboBox<PatientNode> comboBoxPatient;
	private DiseaseManipulator dm;

	public GetPathPanel(DiseaseManipulator dm) throws IncorrectInputException {
		this.dm = dm;
		setPreferredSize(new Dimension(425, 150));
		setBackground(Color.GRAY);
		setForeground(Color.GREEN);
		setLayout(null);

		lblTitle = new JLabel("SELECT PATIENT");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("DialogInput", Font.PLAIN, 24));
		lblTitle.setForeground(Color.GREEN);
		lblTitle.setBounds(10, 11, 430, 33);
		add(lblTitle);

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

		comboBoxDisease = new JComboBox<DiseaseNode>();
		comboBoxDisease.setBackground(Color.BLACK);
		comboBoxDisease.setForeground(Color.GREEN);
		comboBoxDisease.setFont(new Font("DialogInput", Font.PLAIN, 12));
		comboBoxDisease.setBounds(179, 55, 194, 20);
		add(comboBoxDisease);
		for (DiseaseNode disease : dm.getAllDiseases()) {
			comboBoxDisease.addItem(disease);
		}
		comboBoxDisease.addActionListener(this);

		comboBoxPatient = new JComboBox<PatientNode>();
		comboBoxPatient.setForeground(Color.GREEN);
		comboBoxPatient.setFont(new Font("DialogInput", Font.PLAIN, 12));
		comboBoxPatient.setBackground(Color.BLACK);
		comboBoxPatient.setBounds(179, 100, 194, 20);
		add(comboBoxPatient);

		comboBoxDisease.setSelectedIndex(0);
	}

	public static String[] getPrompt(DiseaseManipulator dm)
			throws CancelOptionSelected, IncorrectInputException {
		GetPathPanel newPanel = new GetPathPanel(dm);
		String[] options = { "ENTER", "CANCEL" };
		int promptSelect = JOptionPane.showOptionDialog(null, newPanel,
				"VIRUS", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, "ENTER");
		if (promptSelect == JOptionPane.OK_OPTION) {
			newPanel.setVisible(true);
			String patientName = newPanel.comboBoxPatient.getSelectedItem()
					.toString();
			String diseaseName = newPanel.comboBoxDisease.getSelectedItem()
					.toString();
			return (new String[] { patientName, diseaseName });
		} else
			throw new CancelOptionSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DiseaseNode disease = (DiseaseNode) comboBoxDisease
					.getSelectedItem();
			ArrayList<PatientNode> getPatients = dm.getAllInfected(disease
					.getPatientZero(), disease);
			comboBoxPatient.removeAllItems();
			for (PatientNode patient : getPatients) {
				comboBoxPatient.addItem(patient);
			}
		} catch (IncorrectInputException iie) {

		}

	}

}
