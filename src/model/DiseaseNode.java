package model;

public class DiseaseNode {
	private String diseaseName;
	private int numPatients;
	private DiseaseNode diseasePtr;
	private PatientNode patientPtr;
	
	private DiseaseNode(){
		
	}
	
	public static DiseaseNode createDiseaseNode(String name, DiseaseNode dPtr, PatientNode pPtr){
		DiseaseNode newNode = new DiseaseNode();
		newNode.diseaseName = name.substring(2);
		newNode.numPatients = 0;
		newNode.diseasePtr = dPtr;
		newNode.patientPtr = pPtr;
		return newNode;
	}
	
	public DiseaseNode getDiseasePtr(){
		return this.diseasePtr;
	}
	
	
	public PatientNode getPatientZero(){
		return this.patientPtr;
	}

	public void createPatientZero(String patientDataString){
		PatientNode patientZeroHeader = PatientNode.createPatientZero(patientDataString);
		PatientNode patientZero = PatientNode.createPatient(patientDataString);
		patientZeroHeader.setChild(patientZero, false);
		patientZero.setSibling(patientZeroHeader, true);
		patientZero.setChild(patientZeroHeader, true);
		this.patientPtr = patientZero;
	}
	public void setDiseasePtr(DiseaseNode d){
		this.diseasePtr = d;
	}
	
	public void incrementPatients(){
		this.numPatients++;
	}
	public void decrementPatients(){
		this.numPatients--;
	}
	public String getName(){
		return this.diseaseName;
	}
}
