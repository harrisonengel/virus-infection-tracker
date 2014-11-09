package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import nodes.DiseaseNode;
import nodes.PatientNode;
import execptions.CancelOptionSelected;
import execptions.IncorrectInputException;
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
		try{ 
			if (e.getSource() == view.btnAddFile) this.addDiseaseFile();
			if (e.getSource() == view.btnAddPatient) this.addPatient();
			if (e.getSource() == view.btnRemovePatient) this.removePatient();
			if (e.getSource() == view.btnGetPath) this.getPath();
			if (e.getSource() == view.btnPrintPreorder) this.printPreorder();
			if (e.getSource() == view.btnPrintInfo) this.printInfo();
			if (e.getSource() == view.btnAllInfectedBy) this.getAllInfectedBy();
		} catch (IncorrectInputException iie){
			
		} catch (CancelOptionSelected cos){
			
		}
		
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
		try{
			String[] patientData = patientPanel.patientPrompt(diseaseModel);
			diseaseModel.addInfectedAt(patientData[1], patientData[0], patientData[2], Integer.parseInt(patientData[3]));
		} catch (IncorrectInputException iie){
			
		} catch (CancelOptionSelected cos){
			
		}
	}
	
	private void removePatient() throws IncorrectInputException {
		try{
			String[] patientData = DeletePatientPanel.getPrompt(diseaseModel);
			diseaseModel.removeInfected(patientData[0], patientData[1]);
		} catch (CancelOptionSelected cos){
			
		}
	}
	private void getPath() throws IncorrectInputException, CancelOptionSelected{
			String[] patientData = GetPathPanel.getPrompt(diseaseModel);
			diseaseModel.printPatientPath(patientData[0], patientData[1], view.textArea_1);
	}

	private void printPreorder() throws IncorrectInputException{
		for(String toPrint: diseaseModel.getNodesInorder()){
			view.textArea_1.append(toPrint + "\n");
		}
	}
	
	private void printInfo() throws IncorrectInputException, CancelOptionSelected{
			String[] patientInfo = GetPathPanel.getPrompt(diseaseModel);
			diseaseModel.printData(patientInfo[0], patientInfo[1], view.textArea_1);
	}
	
	private void getAllInfectedBy() throws IncorrectInputException, CancelOptionSelected{
			String[] patientInfo = GetPathPanel.getPrompt(diseaseModel);
			DiseaseNode disease = diseaseModel.findDisease(patientInfo[1]);
			PatientNode patient = diseaseModel.findPatient(patientInfo[0], disease);
			for(PatientNode infected : diseaseModel.getAllInfected(patient, disease)){
				view.textArea_1.append(infected.toString() + "\n");
		} 
	}
}
