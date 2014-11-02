package model;

import controller.EasyReader;

public abstract class DiseaseManipulator {
	
	public DiseaseNode createDiseaseForest(EasyReader er){
		
		String curLine = er.readLine();
		DiseaseNode firstNode = DiseaseNode.createDiseaseNode(curLine, null, null);
		
		curLine = er.readLine();
		while(curLine != null){
			if (curLine.contains("*****")){
				curLine = er.readLine();
				addDiseaseNode(firstNode, curLine);
				continue;
			} else{
				
				
				
			}
		}
		
		return firstNode;
	}
	
	private void addDiseaseNode(DiseaseNode head, String newDisease){
		DiseaseNode dPtr = head;
		 while(dPtr.getDiseasePtr() != null){
			dPtr = dPtr.getDiseasePtr();
		}
		 DiseaseNode newNode = DiseaseNode.createDiseaseNode(newDisease, null, null);
		 dPtr.setDiseasePtr(newNode);
	}
	
	private void addSibling(DiseaseNode disease, PatientNode prevSibling, String newPatient){
		disease.incrementPatients();
		PatientNode patient = PatientNode.createPatient(newPatient);
		patient.setSibling(prevSibling.getSibling(), prevSibling.isSiblingThread());
		prevSibling.setSibling(patient, false);
	}
	
	private void addChild(DiseaseNode disease, PatientNode parent, String newPatient){
		disease.incrementPatients();
		PatientNode patient = PatientNode.createPatient(newPatient);
		
		if (parent.isChildThread()) {
			patient.setChild(parent.getChild(), true);
			parent.setChild(patient, false);
			patient.setSibling(parent, true);
		}
		else{
			this.addSibling(disease, parent.getChild(), newPatient);
		}
	}
	
}

