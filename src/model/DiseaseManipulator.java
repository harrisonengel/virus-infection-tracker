/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package model;

import controller.EasyReader;

public abstract class DiseaseManipulator {
	
	public static DiseaseNode createDiseaseForest(EasyReader er){
		
		String curLine = er.readLine();
		DiseaseNode diseaseHead = DiseaseNode.createDiseaseNode(curLine, null, null);
		DiseaseNode curDisease = diseaseHead;
		PatientNode prevPatient = null;
		while(curLine != null){
			curLine = er.readLine();
			if (curLine.contains("*****")){
				curLine = er.readLine();
				curDisease = DiseaseNode.createDiseaseNode(curLine, null, null);
				addDiseaseNode(diseaseHead, curDisease);
				continue;
			} else{
				PatientNode temp = PatientNode.createPatient(curLine);
				if (temp.getDepth() == 1) {
					curDisease.addPatientZero(temp);
					System.out.println(curDisease.getName() + " " + temp.toString());
				} else if (temp.getDepth() > prevPatient.getDepth()){
					prevPatient.addChild(curDisease, temp);
					System.out.println(curDisease.getName() + " " + temp.toString());
				} else if (temp.getDepth() == prevPatient.getDepth()){
					prevPatient.addSibling(curDisease,  temp);
					System.out.println(curDisease.getName() + " " + temp.toString());
				} else {
					while (temp.getDepth() != prevPatient.getDepth()){
						prevPatient = prevPatient.getSibling();
					}
					prevPatient.addSibling(curDisease, temp);
					System.out.println(curDisease.getName() + " " + temp.toString());
				}
				prevPatient = temp;
			}
		}
		
		return diseaseHead;
	}
	
	private static void addDiseaseNode(DiseaseNode head, DiseaseNode newDisease){
		DiseaseNode dPtr = head;
		 while(dPtr.getDiseasePtr() != null){
			dPtr = dPtr.getDiseasePtr();
		}
		 dPtr.setDiseasePtr(newDisease);
	}
	
	
	
	
}

