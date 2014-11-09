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

public class PatientNode {
	public String first, middle, last, ssn, age, gender, city, state, date;
	public int depthNumber;
	public PatientNode siblingPointer, childPointer;
	public boolean rightThread, leftThread, isPatientZero;

	private PatientNode() {
	}

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
	
	public void addSibling(DiseaseNode disease, PatientNode newPatient){
		disease.incrementPatients();
		newPatient.setSibling(this.getSibling(), this.isSiblingThread());
		newPatient.setChild(this, true);
		this.setSibling(newPatient, false);
	}
	
	// TODO implement the full functionality of inserting a new patient between a parent-child.
	public void addChild(DiseaseNode disease, PatientNode newPatient){
		disease.incrementPatients();
		newPatient.setChild(this.getChild(), this.isChildThread());
		this.setChild(newPatient, false);
		newPatient.setSibling(this, true);
	}
	
	//Returns the functional Patient Node for patientZero
	public void makePatientZero(){
		this.isPatientZero= true;
		
	}
	
	public void makeNormalPatient(){
		this.isPatientZero = false;
	}

	public void setSibling(PatientNode sibling, boolean isThread) {
		
		this.siblingPointer = sibling;
		this.rightThread = isThread;
	}

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

	public PatientNode getIndorderSuccessor() {
		PatientNode cur = this.getSibling();
		if (this.rightThread)
			return cur;

		while (!cur.leftThread) {
			cur = cur.getChild();
		}
		return cur;
	}
	
	public PatientNode getPreorderSuccessor(){
		if (!this.isChildThread())
			return this.getChild();
		else {
			PatientNode findNext = this;
			while(findNext.isSiblingThread()){
				findNext = findNext.getSibling();
				if (findNext.isPatientZero()) return findNext;
			}
			return findNext.getSibling();
		}
	}
	
	public void deleteChild(DiseaseNode disease){
		if (this.isPatientZero){
			disease.setPatientPointer(null);
		}
		PatientNode getThread = this;
		while (!getThread.isChildThread()){
			getThread = getThread.getChild();
		}
		getThread = getThread.getChild();
		this.setChild(getThread, true);
		disease.decrementPatients();
	}
	
	public void deleteSibling(DiseaseNode disease){
		
		PatientNode toDelete = this.getSibling();
		PatientNode findRight = toDelete.getSibling();
		this.setSibling(findRight, toDelete.isSiblingThread());
		disease.decrementPatients();
	}
	
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
	
	public String toString() {
		return ( this.first + " " + this.last);
	}
	
	// TODO create a real implementation of copy() with slightly less overhead.
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

}
