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
import nodes.DiseaseNode;
import nodes.PatientNode;
import execptions.IncorrectInputException;
import java.util.Stack;

/********************* DiseaseManipulator **************/
/* The DiseasManipulator class acts as the Model in the
   MVC setup of this program. Its only field is the 
   head node, or root, of the entire disease forest.
   It contains methods that traverse, collect data, and
   populate the associated disease tree.               */
/*******************************************************/


public class DiseaseManipulator {
	private DiseaseNode head;

	
	public DiseaseManipulator(){

	}
	
	/****************** createDiseaseForest*****************************/
	/* This method reads in a file containing the information about a 
	 * disease forest. 
	 */
	public void createDiseaseForest(String fileName){
		try{
			BufferedReader er = new BufferedReader(new FileReader(fileName), 1024);

			String curLine = er.readLine();
			// The first line of a file should always be the first disease.
			DiseaseNode diseaseHead = DiseaseNode.createDiseaseNode(curLine, null, null);
			this.head =diseaseHead;
			DiseaseNode curDisease = diseaseHead;

			
			PatientNode prevPatient = null;
			// Read the entire file.
			while((curLine = er.readLine()) != null){
				
				if (curLine.contains("*****")){
					curLine = er.readLine();
					if (curLine == null){ // will happen if the last line of the file 
						er.close();
						return;
					}
					
					// Create a disease node based on the line and add it to the rest of 
					// the disease nodes
					curDisease = DiseaseNode.createDiseaseNode(curLine, null, null);
					head.addDiseaseNode(curDisease);
					continue;
					
				} else{// The line represents a patient.
					PatientNode temp = PatientNode.createPatient(curLine);
					
					if (temp.getDepth() == 1) { // If depth == 1, this is patient zero
						curDisease.addPatientZero(temp);
					} 
					//If the depth is greater than previous depth, the patient is a child
					else if (temp.getDepth() > prevPatient.getDepth()){ 
						prevPatient.addChild(curDisease, temp);
					} 
					// If the depths are equal, they are siblings
					else if (temp.getDepth() == prevPatient.getDepth()){
						prevPatient.addSibling(curDisease,  temp);
					} 
					// If none of these cases, we go back up the to a previous level by 
					// following threads until at the right level.
					else {
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
			System.err.println("Can not read from " + fileName);
		}
		
	}

	
	
	/********************* getNodesInorder *****************************/
	/* This method returns an ArrayList used to print out all of the 
	 	patients in a hierarchical order representing the pre-order
	 	traversal of the disease trees.
	 */
	public ArrayList<String> getAllNodesPreorder() throws IncorrectInputException{
		
		ArrayList<String> toReturn = new ArrayList<String>();
		// Throws an exception if there is no data. This prevents the program from imploding from
		// other methods trying to act on a null ArrayList.
		if (this.head == null) throw new IncorrectInputException("No Disease data. Make sure to read in a file first.");
		
		DiseaseNode curDisease = this.head;
		// Do-while loop moves through diseases
		do{	
			
			toReturn.add("************" + curDisease.toString() + "************");
			if (curDisease.getPatientZero() == null) continue;
			
			// Get an array list using getAllInfectedBy to return all those infected by the PatientZeroHeader
			// which includes the first patient (Different from Header).
			ArrayList<PatientNode> curPatients = this.getAllInfectedBy(curDisease.getPatientZero(), curDisease);
			
			// This for loop appends the proper spacing so that preorder printout is formatted nicely.
			for(PatientNode p : curPatients){
				String depthString = "";
				for(int i=1; i<p.getDepth(); i++) depthString = depthString + "     ";
				toReturn.add(depthString + p.toString());
			}
		}while((curDisease = curDisease.getDiseasePtr()) != null);
		
		return toReturn;
	}
	
	
	/************************* removeInfected **************************/
	/* This method removes a given patient. It takes in the First and Last 
	 * name of the patient to be removed as the first parameter and the 
	 * name of the disease as the second string.
	 */
	public void removeInfected(String toRemoveString, String diseaseString){
		
		DiseaseNode disease = this.findDisease(diseaseString);
		
		PatientNode prev = disease.getPatientZero();
		PatientNode toRemove = this.findPatient(toRemoveString, disease);
		
		/* This while loop is used to find either the parent or the 
		 * "older" sibling of the patient to be removed. It does this
		 * by searching through the tree until either a node's right
		 * or left pointer points to the node to be removed. 
		 */
		while(prev.getSibling()!= toRemove && prev.getChild() != toRemove){
			prev = prev.getPreorderSuccessor();
			if (prev==disease.getPatientZero())return;
		}
		
		// Then, once found, take action depending on whether the node to
		// be removed is a child(leftChild) or a sibling(rightChild).
		if (prev.getChild() == toRemove){
			prev.deleteChild(disease);
		} else if (prev.getSibling() == toRemove){
			prev.deleteSibling(disease);
		} 
		
	}
	
	/******************** addInfectedAt **********************/
	/* This method adds a child patient (Exposee) to the 
	 * specified parent as the kth patient under that parent.
	 * 
	 * NOTE: K is very strictly controlled in the DeletePatientPanel
	 * and as such, there are no mechanisms to catch incorrect inputs
	 * in this method. They may need to be implemented if this method
	 * is used elswhere.
	 */
	public void addInfectedAt(String diseaseString, String patientToAdd,
			String parent, int k) {
		
		DiseaseNode disease = findDisease(diseaseString);
		PatientNode infector = findPatient(parent, disease);
		
		// Makes sure the new patient is set at the appropriate depth.
		patientToAdd = (infector.getDepth() + 1) + "/" + patientToAdd; 
		
		PatientNode newPatient = PatientNode.createPatient(patientToAdd);
		
		
		if (k==1) 
			infector.addChild(disease, newPatient);
		else { // moves to the appropriate sibling and then adds the new patient
			PatientNode prevChild = infector.getChild();
			for (int i = 1; i < (k-1); i++) {
				prevChild = prevChild.getSibling();
			}
			prevChild.addSibling(disease, newPatient);
		}
	}
	
	/*************************** findPatient ******************************/
	/* This method finds a PatientNode given a string containing the first 
	 * and last name of the patient and the DiseaseNode of the disease
	 * it belongs to.
	 * NOTE: there are no mechanisms to make sure the patient exists in the 
	 * tree. This is because the user is heavily restricted by comboBoxes
	 * into only selecting Patients that for sure exist in a certain disease
	 * tree. 
	 */
	public PatientNode findPatient(String firstAndLast, DiseaseNode disease){
		
			PatientNode patientZeroHeader = disease.getPatientZero();
			PatientNode curPatient = patientZeroHeader.getChild();
			// Searches through the 
			while (!curPatient.toString().equalsIgnoreCase(firstAndLast)){
				curPatient = curPatient.getPreorderSuccessor();
			}
			return curPatient;
	}
	
	/*************************** findDisease *******************************/
	/* This method finds a DiseaseNode given the name of that disease.    
	   NOTE: There are no mechanisms to do something if the disease is not 
	   in the forest of diseases. This is because the user is heavily 
	   restricted by the use of ComboBoxes.                                */
	public DiseaseNode findDisease(String diseaseName){
		DiseaseNode curDisease = this.head;
		//Uses the toString() method to find the correct node by name.
		while(!curDisease.toString().equalsIgnoreCase(diseaseName)){
			curDisease = curDisease.getDiseasePtr();
		}
		return curDisease;
	}
	
	
	/******************************* getAllDiseases ***********************************/
	// Returns an ArrayList containing all of the available diseases.
	public ArrayList<DiseaseNode> getAllDiseases() throws IncorrectInputException{
		// If not populated, throws an exception.
		if (head == null) throw new IncorrectInputException("No Diseases currently in the system.");
		
		ArrayList<DiseaseNode> nodesToReturn = new ArrayList<DiseaseNode>();
		DiseaseNode cur = head;
		
		//Loops through nodes, adding each to toReturn.
		do{
			nodesToReturn.add(cur);
			cur = cur.getDiseasePtr();
		} while(cur != null);
		
		return nodesToReturn;
	}
	
	/******************************* pritnPatientPath ***********************************/
	/*	This method returns an ArrayList of the disease path to a specific patient.
	 * 	It uses a stack to hold onto all of the nodes in path to whatever node the 
	 *  method is visiting as it searches for the correct PatientNode.
	 *  
	 */
	public ArrayList<PatientNode> printPatientPath(String patientString, 
									String diseaseString) throws IncorrectInputException{
		
		
		Stack<PatientNode> st = new Stack<PatientNode>();
		DiseaseNode disease = this.findDisease(diseaseString);
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		// Starts searching at the first non Header (disease.getPatientZero.getChild()).
		PatientNode curPatient = disease.getPatientZero().getChild();
		// While not visiting the desired Patient
		while(!curPatient.toString().equalsIgnoreCase(patientString)){
			
			// If the child is not a thread, and therefore is the next preorder successor,
			// push the current node onto the stack and move down to the child.
			if(!curPatient.isChildThread()) {
				st.push(curPatient);
				curPatient = curPatient.getChild();
			} else {
				// If a leaf, follow sibling to preorder successor.
				if (curPatient.isSiblingThread()){
					while(curPatient.isSiblingThread()){
						curPatient = curPatient.getSibling();
					}
					
					// Pop patients off the stack until the top patient is the one we are currently visiting
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
		// To get the path, pop nodes off the stack and insert them into the ArrayList.
		while(!st.isEmpty()){
			toReturn.add((PatientNode)st.pop());
		}
		return toReturn;
		
	}
	
	/*************************** dataOf *************************/
	/* Returns a String containing the data of a specified Patient
	 * in a readable format.
	 */
	private String dataOf(PatientNode patient){
		String toReturn = "********************" + patient.getFirst() + " " + patient.getMiddle() + " " 
				+ patient.getLast() + "********************\n"+ "SSN: " + patient.getSsn() 
				+ "\n" + "AGE: " + patient.getAge() + "\n" + "GENDER: " + patient.getGender() + "\n" +
				"CITY: "+ patient.getCity() + "\n" + "STATE: "+ patient.getState() + "\n" +
				"INFECTED ON: " + patient.getDate() + "\n\n";
		return toReturn;
	}
	
	
	/************************** printData ******************************/
	/* This method Returns an array of Strings representing all of the 
	 * data for a specified infector and those that infector directly
	 * infected.
	 */
	public ArrayList<String> printData(String patientString, String diseaseString){
		
		ArrayList<String> toReturn = new ArrayList<String>();
		DiseaseNode disease = this.findDisease(diseaseString);
		PatientNode curPatient = this.findPatient(patientString, disease);
		
		toReturn.add("************************INFECTOR**************************\n");
		toReturn.add(dataOf(curPatient));
		// All of the exposees of a given patient can be found by going down once 
		// left and then right until it reaches a thread.
		if(!curPatient.isChildThread()){
			toReturn.add("********************INFECTED***********************\n");
			curPatient = curPatient.getChild();
			while(!curPatient.isSiblingThread()){
				toReturn.add(dataOf(curPatient));
				curPatient = curPatient.getSibling();
			}
			toReturn.add(dataOf(curPatient));
		}
		return toReturn;
	}
	
	/************************** printData ******************************/
	/* This method returns an ArrayList of all patients who can all trace their 
	 * infection back to a given patient in preorder. It works by making the 
	 * infector a "head" node for the duration of the algorithm and traversing
	 * its subtree in pre-order.
	 */
	public ArrayList<PatientNode> getAllInfectedBy(PatientNode infector, DiseaseNode disease) throws IncorrectInputException{
		
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		PatientNode curPatient = infector;
		boolean wasPatientZero = curPatient.isPatientZero();
		curPatient.makePatientZero(); // Now the subtree can be traversed with getPreorderSuccessor()
		
		// If the node has no left children, return nothing.
		if (curPatient.getChild() == null || curPatient.isChildThread()) return toReturn;
		curPatient = curPatient.getChild();
		
		// Loops through the entire subtree under the infector and adds it to toReturn in preorder.
		do{
			toReturn.add(curPatient);
			curPatient = curPatient.getPreorderSuccessor();
		} while(curPatient != infector);
		
		if (!wasPatientZero) infector.makeNormalPatient(); //Return the infector node to its previous status 

		return toReturn;
	}
	
}

