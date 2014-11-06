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
import controller.Mediator;
public class DiseaseManipulator {
	private DiseaseNode head;
	private Mediator controller;

	
	public DiseaseManipulator(){

	}
	public void setController(Mediator controller){
		this.controller = controller;
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
	
	public void getNodesInorder(JTextArea printTo){
		//this.top = new DefaultMutableTreeNode("Diseases");

		DiseaseNode curDisease = this.head;
		//DefaultMutableTreeNode diseaseTreeNode = new DefaultMutableTreeNode(curDisease);
		PatientNode curPatient;
		//DefaultMutableTreeNode patientTreeNode = null;
		do{	
			PatientNode head = curDisease.getPatientZero();
			curPatient = head.getChild();

			printTo.append("************" + curDisease.toString() + "****************" + "\n");
			do{
				
				String indent = "";
				for (int i=1; i<curPatient.getDepth(); i++){
					indent = indent + "      ";
				}
				printTo.append(indent + curPatient.toString() + "\n" );
				
			} while((curPatient = curPatient.getPreorderSuccessor()) != head);
			curDisease = curDisease.getDiseasePtr();
		}while(curDisease != null);
	}
	
	public void displayFromNode(DiseaseNode startingDisease){
		DiseaseNode curDisease = this.head;
		
	}

	
	private void addDiseaseNode(DiseaseNode newDisease){
		DiseaseNode dPtr = this.head;
		 while(dPtr.getDiseasePtr() != null){
			dPtr = dPtr.getDiseasePtr();
		}
		 dPtr.setDiseasePtr(newDisease);
	}
	
	public ArrayList<PatientNode> getInfected(PatientNode infector){
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		if (infector.isChildThread()) {
			return toReturn;
		} else {
			PatientNode cur = infector.getChild();
			toReturn.add(cur);
			while(!cur.isSiblingThread()){
				cur = cur.getSibling();
				toReturn.add(cur);
			}
		}
		return toReturn;
	}
	
	public void removeInfected(PatientNode toRemove, PatientNode infector){
		if (!getInfected(infector).contains(toRemove)){
			System.err.println("Patient to Remove is not a child of the infector.");
			return;
		}
		if (toRemove.toString().equals(infector.getChild().toString())){
			infector.setChild(toRemove.getSibling(), toRemove.isSiblingThread());
			System.out.println(infector.getChild().toString());
		} else {
			PatientNode prev = infector;
			while (prev.getSibling() != toRemove){
				prev = prev.getSibling();
			}
			prev.setSibling(toRemove.getSibling(), toRemove.isSiblingThread());
			System.out.println(prev.getSibling().toString());
		}
	}

	public void addInfectedAt(String diseaseString, String patientToAdd,
			String parent, int k) {
		DiseaseNode disease = findDisease(diseaseString);
		PatientNode newPatient = PatientNode.createPatient(patientToAdd);
		PatientNode infector = findPatient(parent, disease);
		
		if (getInfected(infector).size() < (k - 1))
			System.err.println("k-1 > number of children ");
		if (infector.isChildThread() || k==1)
			infector.addChild(disease, newPatient);
		else {
			PatientNode prevChild = infector.getChild();
			for (int i = 1; i < (k-1); i++) {
				prevChild = prevChild.getSibling();
			}
			prevChild.setSibling(newPatient, prevChild.isSiblingThread());
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
	
}

