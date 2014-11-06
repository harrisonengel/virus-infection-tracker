package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import panels.DeletePatientPanel;
import panels.GetPathPanel;
import panels.patientPanel;
import view.GUI;
import model.DiseaseManipulator;

/* Mediator
 * The Mediator object is the controller, the 
 * object that sits between the DiseseManipulater(Model)
 * and the GUI(View).
 */
public class Mediator implements ActionListener {
	private DiseaseManipulator diseaseModel;
	private GUI view;
	
	public Mediator(){
	}
	
	public void addActionListeners(final GUI view){
		view.btnAddFile.addActionListener(this); 
		view.btnAddPatient.addActionListener(this);
		view.btnRemovePatient.addActionListener(this);
		view.btnGetPath.addActionListener(this);
		view.btnPrintPreorder.addActionListener(this);
		view.btnPrintPreorder.addActionListener(this);
		view.btnPrintInfo.addActionListener(this);
		view.btnAllInfectedBy.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		view.textArea_1.setText(null);
		if (e.getSource() == view.btnAddFile) this.addDiseaseFile();
		if (e.getSource() == view.btnAddPatient) this.addPatient();
		if (e.getSource() == view.btnRemovePatient) this.removePatient();
		if (e.getSource() == view.btnGetPath) this.getPath();
		if (e.getSource() == view.btnPrintPreorder) this.printPreorder();
		if (e.getSource() == view.btnPrintInfo) this.printInfo();
		if (e.getSource() == view.btnAllInfectedBy) this.getAllInfectedBy();
	}
	
	public void setView(GUI view){
		this.view = view;
	}
	
	public void setModel(DiseaseManipulator model){
		this.diseaseModel = model;
	}
	

	
	private void addDiseaseFile(){
			try {
				String fileName = fileManipulator.getFileName();
				if (fileName != null) {
					diseaseModel.createDiseaseForest(fileName);
					
				}
			} catch (NullPointerException np) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			} catch (NumberFormatException nfp) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			}
	}

	private void addPatient(){
		String[] patientData = patientPanel.patientPrompt();
		diseaseModel.addInfectedAt(patientData[1], patientData[0], patientData[2], Integer.parseInt(patientData[3]));
	}
	
	private void removePatient(){
		String[] patientData = DeletePatientPanel.deletePatientPrompt();
		
		diseaseModel.removeInfected(patientData[0], patientData[1]);
	}
	private void getPath(){
		String[] patientData = GetPathPanel.getPathPrompt();
		diseaseModel.printPatientPath(patientData[0], patientData[1], view.textArea_1);
	}

	private void printPreorder(){
		diseaseModel.getNodesInorder(view.textArea_1);
	}
	
	private void printInfo(){
		String[] patientInfo = GetPathPanel.getPathPrompt();
		diseaseModel.printData(patientInfo[0], patientInfo[1], view.textArea_1);
	}
	
	private void getAllInfectedBy(){
		String[] patientInfo = GetPathPanel.getPathPrompt();
		diseaseModel.getAllInfectedBy(patientInfo[0], patientInfo[1], view.textArea_1);
	}

}
