/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package controller;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.DiseaseManipulator;
import model.DiseaseNode;

public abstract class fileManipulator {

	// Uses a JFileCooser to get a path, which can then be passed into an
	// EasyReader object.
	public static String getFileName() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// This message to the user will be replaced
			// by an error message immediately afterwards
			// if there is an error reading in the file.
			return fc.getSelectedFile().getPath();
		} else {
			return null;
		}
	}
	
	// TODO Still needs to be implemented
	public static EasyReader getEasyReader(String fileName){
		EasyReader inFile = new EasyReader(fileName);
		if (inFile.bad()) {
			System.err.println("Can't open " + fileName);
			return null;
		} else {
			return inFile;
		}
	}
	
	//Still needs to be implemented
	public static void saveTrees(){
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION)
			//fc.getSelectedFile().getPath();
			return;
	}
}
