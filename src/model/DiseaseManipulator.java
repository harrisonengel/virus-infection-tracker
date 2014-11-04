/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package model;

import java.util.ArrayList;

import controller.EasyReader;

public class DiseaseManipulator {
	private DiseaseNode head;
	private ArrayList<DiseaseNode> allDiseases;
	
	public DiseaseManipulator(){
		
	}
	
	public DiseaseManipulator(EasyReader er){
		allDiseases = new ArrayList<DiseaseNode>();
		createDiseaseForest(er);
	}
	
	public void createDiseaseForest(EasyReader er){
		
		String curLine = er.readLine();
		DiseaseNode diseaseHead = DiseaseNode.createDiseaseNode(curLine, null, null);
		DiseaseNode curDisease = diseaseHead;
		allDiseases.add(curDisease);
		PatientNode prevPatient = null;
		while(curLine != null){
			curLine = er.readLine();
			if (curLine.contains("*****")){
				curLine = er.readLine();
				curDisease = DiseaseNode.createDiseaseNode(curLine, null, null);
				allDiseases.add(curDisease);
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
		
		head = diseaseHead;
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
		if (toRemove == infector.getChild()){
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
	/* addInfected
	 * 
	 */
	public void addInfected(DiseaseNode disease, PatientNode toAdd,
			PatientNode parent, int k) {
		if (getInfected(parent).size() < (k - 1))
			System.err.println("k-1 > number of children ");
		if (parent.isChildThread())
			parent.addChild(disease, toAdd);
		else {
			PatientNode prevChild = parent.getChild();
			for (int i = 1; i < k; i++) {
				prevChild = prevChild.getSibling();
			}
			prevChild.setSibling(toAdd, prevChild.isSiblingThread());
		}
	}

	
	
}

