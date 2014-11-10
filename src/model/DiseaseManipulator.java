/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import nodes.DiseaseNode;
import nodes.PatientNode;
import execptions.IncorrectInputException;
import java.util.Stack;

public class DiseaseManipulator {
	private DiseaseNode head;

	
	public DiseaseManipulator(){

	}
	
	public void createDiseaseForest(String fileName){
		try{
			BufferedReader er = new BufferedReader(new FileReader(fileName), 1024);

			String curLine = er.readLine();
			DiseaseNode diseaseHead = DiseaseNode.createDiseaseNode(curLine, null, null);
			this.head =diseaseHead;
			DiseaseNode curDisease = diseaseHead;

			PatientNode prevPatient = null;
			while((curLine = er.readLine()) != null){
				if (curLine.contains("*****")){
					curLine = er.readLine();
					curDisease = DiseaseNode.createDiseaseNode(curLine, null, null);
					addDiseaseNode(curDisease);
					continue;
				} else{
					PatientNode temp = PatientNode.createPatient(curLine);
					if (temp.getDepth() == 1) {
						curDisease.addPatientZero(temp);
					} else if (temp.getDepth() > prevPatient.getDepth()){
						prevPatient.addChild(curDisease, temp);
					} else if (temp.getDepth() == prevPatient.getDepth()){
						prevPatient.addSibling(curDisease,  temp);
					} else {
						while (temp.getDepth() != prevPatient.getDepth()){
							prevPatient = prevPatient.getSibling();
						}
						prevPatient.addSibling(curDisease, temp);
					}
					prevPatient = temp;
				}
			}
			
			er.close();
		} catch (FileNotFoundException e){
			System.err.println("Can't open " + fileName);
			return;  
		} catch (IOException i){
			//TODO implement Exception
		}
		
	}
	
	public ArrayList<String> getNodesInorder() throws IncorrectInputException{
		ArrayList<String> toReturn = new ArrayList<String>();
		DiseaseNode curDisease = this.head;
		
		do{	
			toReturn.add("************" + curDisease.toString() + "************");
			if (curDisease.getPatientZero() == null) continue;
			ArrayList<PatientNode> curPatients = this.getAllInfected(curDisease.getPatientZero(), curDisease);
			for(PatientNode p : curPatients){
				String depthString = "";
				for(int i=1; i<p.getDepth(); i++) depthString = depthString + "     ";
				toReturn.add(depthString + p.toString());
			}
		}while((curDisease = curDisease.getDiseasePtr()) != null);
		
		return toReturn;
	}

	
	private void addDiseaseNode(DiseaseNode newDisease){
		DiseaseNode dPtr = this.head;
		 while(dPtr.getDiseasePtr() != null){
			dPtr = dPtr.getDiseasePtr();
		}
		 dPtr.setDiseasePtr(newDisease);
	}
	
	
	
	public void removeInfected(String toRemoveString, String diseaseString){
		
		DiseaseNode disease = this.findDisease(diseaseString);
		
		PatientNode prev = disease.getPatientZero();
		PatientNode toRemove = this.findPatient(toRemoveString, disease);
		
		while(prev.getSibling()!= toRemove && prev.getChild() != toRemove){
			prev = prev.getPreorderSuccessor();
			if (prev==disease.getPatientZero())return;
		}
		
		if (prev.getChild() == toRemove){
			prev.deleteChild(disease);
		} else if (prev.getSibling() == toRemove){
			prev.deleteSibling(disease);
		} 
		
	}
	

	public void addInfectedAt(String diseaseString, String patientToAdd,
			String parent, int k) {
		
		DiseaseNode disease = findDisease(diseaseString);
		
		PatientNode infector = findPatient(parent, disease);
		patientToAdd = (infector.getDepth() + 1) + "/" + patientToAdd; 
		PatientNode newPatient = PatientNode.createPatient(patientToAdd);
		
		
		if (infector.getInfected().size() < (k - 1))
			System.err.println("k-1 > number of children ");
		if (infector.isChildThread() || k==1)
			infector.addChild(disease, newPatient);
		else {
			PatientNode prevChild = infector.getChild();
			for (int i = 1; i < (k-1); i++) {
				prevChild = prevChild.getSibling();
			}
			prevChild.addSibling(disease, newPatient);
		}
	}
	
	public PatientNode findPatient(String firstAndLast, DiseaseNode disease){
			PatientNode patientZeroHeader = disease.getPatientZero();
			PatientNode curPatient = patientZeroHeader.getChild();
			while (!curPatient.toString().equalsIgnoreCase(firstAndLast)){
				curPatient = curPatient.getPreorderSuccessor();
				if (curPatient == patientZeroHeader) return null; //TODO Implement special Error for this.
			}
			return curPatient;
	}
	
	public DiseaseNode findDisease(String diseaseName){
		DiseaseNode curDisease = this.head;
		while(!curDisease.toString().equalsIgnoreCase(diseaseName)){
			if (curDisease.getDiseasePtr()==null) return null; //TODO Implement special Error for this.
			curDisease = curDisease.getDiseasePtr();
		}
		return curDisease;
	}
	
	public ArrayList<DiseaseNode> getAllDiseases() throws IncorrectInputException{
		if (head == null) throw new IncorrectInputException("No Diseases currently in the system.");
		ArrayList<DiseaseNode> nodesToReturn = new ArrayList<DiseaseNode>();
		DiseaseNode cur = head;
		do{
			nodesToReturn.add(cur);
			cur = cur.getDiseasePtr();
		} while(cur != null);
		
		return nodesToReturn;
	}

	public ArrayList<PatientNode> printPatientPath(String patientString, String diseaseString) throws IncorrectInputException{
		Stack<PatientNode> st = new Stack<PatientNode>();
		DiseaseNode disease = this.findDisease(diseaseString);
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		PatientNode curPatient = disease.getPatientZero().getChild();
		while(!curPatient.toString().equalsIgnoreCase(patientString)){
			if(!curPatient.isChildThread()) {
				st.push(curPatient);
				curPatient = curPatient.getChild();
			} else {
				if (curPatient.isSiblingThread()){
					while(curPatient.isSiblingThread()){
						curPatient = curPatient.getSibling();
					}
					
					while(!((PatientNode)st.peek()).toString().equalsIgnoreCase(curPatient.toString())){
						st.pop();
					}
					st.pop();
				}
				curPatient = curPatient.getSibling();
				if(curPatient.isPatientZero()) throw new IncorrectInputException("Patient not found.");
			}
		}
		
		toReturn.add(curPatient);
		while(!st.isEmpty()){
			toReturn.add((PatientNode)st.pop());
		}
		return toReturn;
		
	}
	
	private void printPatientData(PatientNode patient, JTextArea printTo){
		printTo.append("********************" + patient.first + " " + patient.middle + " " + patient.last + "********************\n" );
		printTo.append("SSN: " + patient.ssn + "\n");
		printTo.append("AGE: " + patient.age + "\n");
		printTo.append("GENDER: " + patient.gender + "\n");
		printTo.append("CITY: "+ patient.city + "\n");
		printTo.append("STATE: "+ patient.state + "\n");
		printTo.append("INFECTED ON: " + patient.date + "\n");
		printTo.append("\n");
	}
	
	public void printData(String patientString, String diseaseString, JTextArea printTo){
		DiseaseNode disease = this.findDisease(diseaseString);
		PatientNode curPatient = this.findPatient(patientString, disease);
		
		printTo.append("********************INFECTOR***********************\n");
		printPatientData(curPatient, printTo);
		if(!curPatient.isChildThread()){
			printTo.append("********************INFECTED***********************\n");
			curPatient = curPatient.getChild();
			while(!curPatient.isSiblingThread()){
				printPatientData(curPatient, printTo);
				curPatient = curPatient.getSibling();
			}
			printPatientData(curPatient, printTo);
		}	
	}
	
	//Returns an ArrayList of the tree starting from some patient node (root). 
	public ArrayList<PatientNode> getAllInfected(PatientNode infector, DiseaseNode disease) throws IncorrectInputException{
		
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		PatientNode curPatient = infector;
		boolean wasPatientZero = curPatient.isPatientZero;
		curPatient.makePatientZero();
		if (curPatient.getChild() == null || curPatient.isChildThread()) return toReturn;
		curPatient = curPatient.getChild();
		do{
			toReturn.add(curPatient);
			curPatient = curPatient.getPreorderSuccessor();
		} while(curPatient != infector);
		
		if (!wasPatientZero) infector.makeNormalPatient();
		infector.getChild().makeNormalPatient();
		return toReturn;
	}
	
}

