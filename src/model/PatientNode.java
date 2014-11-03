/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package model;

import java.util.StringTokenizer;

public class PatientNode {
	private String first, middle, last, ssn, age, gender, city, state, date;
	private int depthNumber;
	private PatientNode siblingPointer, childPointer;
	private boolean rightThread, leftThread, isPatientZero;

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
	public static PatientNode makePatientZero(PatientNode patientZero){
		patientZero.isPatientZero= true;
		patientZero.setChild(patientZero, true);
		patientZero.setSibling(patientZero, true);
		return patientZero;
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

	public String toString() {
		return ( this.depthNumber + "/" + this.first + "/"
				+ this.middle + "/" + this.last + "/" + this.ssn + "/"
				+ this.age + "/" + this.gender + "/" + this.city + "/"
				+ this.state + "/" + this.date + "/");
	}
	
	// TODO create a real implementation of copy() with slightly less overhead.
	public PatientNode copy(){
		return createPatient(this.toString());
	}

}
