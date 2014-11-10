package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

/**************************** Mediator************************************/
 /* The Mediator object is the controller in the program's MVC model. It
 * sits between the DiseseManipulater(Model) and the GUI(View), acting 
 * as the method by which the user interacts with the Model and then
 * sees the results displayed to the View.
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
		view.getDisplayArea().setText(null);
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
	
	/******************** addDiseaseFile ****************************/
	/* This method is called by the Add File button. It gets the 
	 * file name and passes it onto the diseaseModel for processing.
	 * It also catches errors that the model could throw trying to 
	 * open the file.
	 */
	private void addDiseaseFile() throws IncorrectInputException{
			try {
				String fileName = fileManipulator.getFileName();
				if (fileName != null){ 
					diseaseModel.createDiseaseForest(fileName);
					this.printPreorder();
				}
			} catch (NullPointerException np) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			} catch (NumberFormatException nfp) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			}
	}

	/************************* addPatient ******************************/
	/* This method is called by the Add Patient button. It calls the 
	 * patientPrompt to get the data about the new Patient being added,
	 * and then passes that information to the Model via addInfectedAt
	 */
	private void addPatient(){
		try{
			String[] patientData = patientPanel.patientPrompt(diseaseModel);
			diseaseModel.addInfectedAt(patientData[1], patientData[0], patientData[2], Integer.parseInt(patientData[3]));
			this.printPreorder();
		} catch (IncorrectInputException iie){
			
		} catch (CancelOptionSelected cos){
			
		}
	}
	
	/*************************** removePatient **************************/
	/* This method is called by the Remove Patient Button. It calls 
	 * the DeletePatientPanel.getPrompt to get the patient that will be
	 * deleted, then passes this information over to the model via 
	 * removeInfected. 
	 */
	private void removePatient() throws IncorrectInputException {
		try{
			String[] patientData = DeletePatientPanel.getPrompt(diseaseModel);
			diseaseModel.removeInfected(patientData[0], patientData[1]);
			this.printPreorder();
		} catch (CancelOptionSelected cos){
			
		}
	}
	
	/***************************getPath **************************/
	/* Uses a GetPathPanel to get the information about the patient
	 * the user wants to display a path from. This is then passed
	 * to the model via printpatientPath, and the model returns 
	 * an ArrayList of all the patients to be printed.
	 * The controller then prints these to the View (GUI).
	 */
	private void getPath() throws IncorrectInputException, CancelOptionSelected{
			String[] patientData = GetPathPanel.getPrompt(diseaseModel);
			ArrayList<PatientNode> toPrint = diseaseModel.printPatientPath(patientData[0], patientData[1]);
			
			// Print to GUI
			for(int i=0; i<toPrint.size(); i++) {
				String space = "";
				for(int j=0; j<i; j++) space = space + "    ";
				view.getDisplayArea().append(space + toPrint.get(i).toString() + "\n");
			}
	}

	/*********************** printPreorder ************************/
	// Calls to the Model to get all of the patients in preorder
	// and prints them.
	private void printPreorder() throws IncorrectInputException{
		for(String toPrint: diseaseModel.getAllNodesPreorder()){
			view.getDisplayArea().append(toPrint + "\n");
		}
	}
	
	/*********************** printInfo************************/
	/* Uses getPrompt to get information about the patient a
	 * user wants to print information about. This is passed
	 * to the Model, which returns an ArrayList of all the 
	 * lines needed. This includes the information of the 
	 * Patient the user was looking for along with the info
	 * of patients directly infected by this patient.
	 */
	private void printInfo() throws IncorrectInputException, CancelOptionSelected{
			String[] patientInfo = GetPathPanel.getPrompt(diseaseModel);
			for(String s: diseaseModel.printData(patientInfo[0], patientInfo[1])){
				view.getDisplayArea().append(s);
			}
	}
	
	/*********************** getAllInfectedBy ************************/
	/* Uses getPrompt to get information about the patient from a user.
	 * Then, it prints the first and last name of all of those who
	 * this patient is responsible for infecting.
	 */
	private void getAllInfectedBy() throws IncorrectInputException, CancelOptionSelected{
			String[] patientInfo = GetPathPanel.getPrompt(diseaseModel);
			DiseaseNode disease = diseaseModel.findDisease(patientInfo[1]);
			PatientNode patient = diseaseModel.findPatient(patientInfo[0], disease);
			
			if (patient.isChildThread()){
				view.getDisplayArea().append("No one was infected by " + patient.toString());
				return;
			}
			for(PatientNode infected : diseaseModel.getAllInfectedBy(patient, disease)){
				view.getDisplayArea().append(infected.toString() + "\n");
		} 
	}
}
