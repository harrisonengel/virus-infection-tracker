/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package nodes;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*********************** Patient Node ***************************/
/* The PatientNode class is the basic unit that represents a patient
 * in their respective disease tree. They are doubly threaded, as 
 * represented by two boolean values, each stating whether or not
 * the right or left pointers are threads or not.
 * 
 * Another important feature is the use of a flag (isPatientZero) to
 * flag the patient as a head node. This doesn't necessarily mean that
 * the patient is a direct descendant of a disease and can be used to 
 * single out a specific subtree by making any Patient node a "patient
 * zero" in an algorithm and then reverting it back afterward.
 */
 /*****************************************************************/

public class PatientNode {
	private String first, middle, last, ssn, age, gender, city, state, date;
	private int depthNumber;
	private PatientNode siblingPointer, childPointer;
	//siblingPointer = Right Pointer; childPointer = Left Pointer
	private boolean rightThread, leftThread, isPatientZero;

	private PatientNode() {
	}
	
	/* The createPatient(String) method is the main method of creating
	 * a new PatientNode. The String that is expected to be formatted as:
	 * depth/First/Middle/Last/SSN/AGE/GENDER/CITY/STATE/DATE/
	 * with the date formated as "mm.dd.yyyy"
	 * 
	 */
	public static PatientNode createPatient(String patientDataString) {
		PatientNode newPatient = new PatientNode();
		StringTokenizer st = new StringTokenizer(patientDataString, "/");
		newPatient.depthNumber = Integer.parseInt(st.nextToken());
		newPatient.first = st.nextToken();
		newPatient.middle = st.nextToken();
		newPatient.last = st.nextToken();
		newPatient.ssn = st.nextToken();
		newPatient.age = st.nextToken();
		newPatient.gender = st.nextToken();
		newPatient.city = st.nextToken();
		newPatient.state = st.nextToken();
		newPatient.date = st.nextToken();
		newPatient.isPatientZero = false;
		return newPatient;
	}

	
	/* Adds a sibling to node after a specific patient. It will be
	 * properly inserted between two siblings or make its right
	 * pointer the proper thread by moving the properties of the
	 * previous right pointer to the node being inserted.
	 */
	public void addSibling(DiseaseNode disease, PatientNode newPatient){
		disease.incrementPatients();
		newPatient.setSibling(this.getSibling(), this.isSiblingThread());
		newPatient.setChild(this, true);
		this.setSibling(newPatient, false);
	}
	
	
	/* Adds a child PatientNode after a specified patient. It will properly
	 * thread the inserted node by copying the parents previous left 
	 * pointer thread property, and making the inserted node's right pointer
	 * a thread that points back to the parent node.
	 */
	public void addChild(DiseaseNode disease, PatientNode newPatient){
		disease.incrementPatients();
		newPatient.setChild(this.getChild(), this.isChildThread());
		this.setChild(newPatient, false);
		newPatient.setSibling(this, true);
	}
	
	// Makes a specific PatientNode a "head" node.
	public void makePatientZero(){
		this.isPatientZero= true;
		
	}
	
	// Makes a specific PatientNode a "normal" node.
	public void makeNormalPatient(){
		this.isPatientZero = false;
	}

	/*Used to set a specific Node as the Sibling Node. It copies
	 * its parents sibling pointer in the process, continuing
	 * the proper threading of the tree.
	 */
	public void setSibling(PatientNode sibling, boolean isThread) {
		
		this.siblingPointer = sibling;
		this.rightThread = isThread;
	}

	/*Used to set a specific Node as the Child Node. It copies
	 * its parents child pointer in the process, continuing
	 * the proper threading of the tree.
	 */
	public void setChild(PatientNode child, boolean isThread) {
		this.childPointer = child;
		this.leftThread = isThread;
	}

	
	public PatientNode getSibling() {
		return siblingPointer;
	}

	public PatientNode getChild() {
		return childPointer;
	}

	public int getDepth() {
		return this.depthNumber;
	}

	public boolean isSiblingThread() {
		return rightThread;
	}

	public boolean isChildThread() {
		return leftThread;
	}

	public boolean isPatientZero() {
		return isPatientZero;
	}

	//Returns the Preorder Successor of a given node.
	public PatientNode getPreorderSuccessor(){
		//If the left child is not a thread, return the child
		if (!this.isChildThread())
			return this.getChild();
		//else go right until the right pointer isn't a thread and
		//return the right child of that node.
		else {
			PatientNode findNext = this;
			while(findNext.isSiblingThread()){
				findNext = findNext.getSibling();
				if (findNext.isPatientZero()) return findNext;
			}
			return findNext.getSibling();
		}
	}
	
	/* Deletes the child of a specified node and 
	 * then sets the right pointer to the appropriate
	 * sibling.
	 */
	public void deleteChild(DiseaseNode disease){
		// If deleting the first patient, set the disease to point to null
		if (this.isPatientZero){
			disease.setPatientPointer(null);
			return;
		}
		
		/* If the child isn't the only Exposee of this patient,
		 remove it by setting the infector's child pointer 
		 to the next child.*/
		if(!this.getChild().isSiblingThread()){
			this.setChild(this.getChild().getSibling(), false);
			return;
		}
		
		/* If neither of the above apply, the child to be removed
		 * is a leaf. The while loop finds the farthest left thread
		 * so that it can be retained. Then, the child is removed.
		 */
		PatientNode getThread = this;
		while (!getThread.isChildThread()){
			getThread = getThread.getChild();
		}
		getThread = getThread.getChild();
		this.setChild(getThread, true);
		
		disease.decrementPatients(); //Decrease patient count by 1
	}
	
	// Deletes the sibling while maintaining a properly
	//threaded tree.
	public void deleteSibling(DiseaseNode disease){
		
		PatientNode toDelete = this.getSibling();
		PatientNode findRight = toDelete.getSibling();
		this.setSibling(findRight, toDelete.isSiblingThread());
		
		disease.decrementPatients(); //Decrease patient count by 1
	}
	
	//Returns an ArrayList of all those directly infected by a given patient.
	public ArrayList<PatientNode> getInfected(){
		
		ArrayList<PatientNode> toReturn = new ArrayList<PatientNode>();
		if (!this.isChildThread()) {
			PatientNode cur = this.getChild();
			toReturn.add(cur);
			while(!cur.isSiblingThread()){
				cur = cur.getSibling();
				toReturn.add(cur);
			}
		}
		return toReturn;
	}
	
	@Override
	public String toString() {
		return (this.first + " " + this.last);
	}
	
	@Override
	public PatientNode clone(){
		PatientNode newClone = new PatientNode();
		newClone.depthNumber = this.depthNumber;
		newClone.first = this.first;
		newClone.middle = this.middle;
		newClone.last = this.last;
		newClone.ssn = this.ssn;
		newClone.age = this.age;
		newClone.gender= this.gender;
		newClone.city = this.city;
		newClone.state = this.state;
		newClone.date = this.date;
		
		return newClone;
	}
	
	
	/***************** "Getter" Methods ********************/
	public String getFirst(){
		return this.first;
	}
	
	public String getMiddle(){
		return this.middle;
	}
	
	public String getLast(){
		return this.last;
	}
	
	public String getSsn(){
		return this.ssn;
	}
	
	public String getAge(){
		return this.age;
	}
	
	public String getGender(){
		return this.gender;
	}
	
	public String getCity(){
		return this.city;
	}

	public String getState(){
		return this.state;
	}
	
	public String getDate(){
		return this.date;
	}
}
