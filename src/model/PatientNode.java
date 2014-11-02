package model;

import java.util.StringTokenizer;

public class PatientNode {
	private String first, middle, last, ssn, age, gender, city, state, date, depthNumber;
	private PatientNode siblingPointer, childPointer;
	private boolean rightThread, leftThread, isPatientZero;
	
	private PatientNode(){
	}
	
	public static PatientNode createPatient(String patientDataString){
		PatientNode newPatient = new PatientNode();
		StringTokenizer st = new StringTokenizer(patientDataString, "/");
		newPatient.depthNumber = st.nextToken();
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
	
	public static PatientNode createPatientZero(String patientDataString){
		PatientNode newPatient = createPatient(patientDataString);
		newPatient.isPatientZero = true;
		return newPatient;
	}
	
	
	public void setSibling(PatientNode sibling, boolean isThread){
		this.siblingPointer = sibling;
		this.rightThread = isThread;
	}
	public void setChild(PatientNode child, boolean isThread){
		this.childPointer = child;
		this.leftThread = isThread;
	}
	public PatientNode getSibling(){
		return siblingPointer;
	}
	public PatientNode getChild(){
		return childPointer;
	}
	
	public boolean isSiblingThread(){
		return rightThread;
	}
	public boolean isChildThread(){
		return leftThread;
	}
	public boolean isPatientZero(){
		return isPatientZero;
	}
	
	public PatientNode getIndorderSuccessor(){
		PatientNode cur = this.getSibling();
		if(this.rightThread) return cur;
		
		while(!cur.leftThread){
			cur = cur.getChild();
		}
		return cur;
	}
	 
}
