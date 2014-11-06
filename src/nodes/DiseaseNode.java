/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package nodes;


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
	
	public void addDiseaseNode(DiseaseNode newDisease){
		DiseaseNode dPtr = this;
		 while(dPtr.getDiseasePtr() != null){
			dPtr = dPtr.getDiseasePtr();
		}
		 dPtr.setDiseasePtr(newDisease);
	}
	
	public DiseaseNode getDiseasePtr(){
		return this.diseasePtr;
	}
	
	
	public PatientNode getPatientZero(){
		return this.patientPtr;
	}

	public void addPatientZero(PatientNode patient){
		PatientNode patientZeroHeader = patient.clone();
		patientZeroHeader.makePatientZero();
		
		patientZeroHeader.setChild(patient, false);
		
		patient.setChild(patientZeroHeader, true);
		patient.setSibling(patientZeroHeader, true);
		this.patientPtr = patientZeroHeader;
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
	public String toString(){
		return this.diseaseName;
	}
	public int getNumPatients(){
		return numPatients;
	}
}
