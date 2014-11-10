/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package nodes;

/*********************** Disease Node ************************/
 /* Disease Nodes represent diseases in the disease forest. */

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
	
	// Adds a disease node at the end of the disease LinkedList.
	public void addDiseaseNode(DiseaseNode newDisease){
		
		DiseaseNode dPtr = this;
		// Goes to the end of the linked list.
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
	
	public void setPatientPointer(PatientNode pn){
		this.patientPtr = pn;
	}

	/************ addPatientZero ******************/
	/*This method is used to add the very first 
	 PatientNode to a disease. It requires a special
	 method because there needs to be a special 
	 "Header" object that is used as a stopping point
	 for searches in the tree along with the "usable"
	 PatientNode. 
	 It also sets up the two basic threads that will
	 be copied down as the tree is built to make it 
	 properly threaded.
	 @see README for more information.              */
	/************************************************/
	public void addPatientZero(PatientNode patient){
		
		//Creates a copy to be used as the header in-between
		//the disease node and the "usable" patient zero.
		PatientNode patientZeroHeader = patient.clone();
		patientZeroHeader.makePatientZero();
		
		patientZeroHeader.setChild(patient, false);
		
		//This 
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
	
	@Override
	public String toString(){
		return this.diseaseName;
	}
	public int getNumPatients(){
		return numPatients;
	}
}
