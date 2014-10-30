package controller;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public abstract class fileManipulator {

	// Uses a JFileCooser to get a path, which can then be passed into an
	// EasyReader object.
	public static String getFileName(JFrame jf) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(jf);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// This message to the user will be replaced
			// by an error message immediately afterwards
			// if there is an error reading in the file.
			return fc.getSelectedFile().getPath();
		} else {
			return null;
		}
	}
}
