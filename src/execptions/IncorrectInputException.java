package execptions;

import javax.swing.JOptionPane;

public class IncorrectInputException extends Exception {

	private static final long serialVersionUID = 1L;

	public IncorrectInputException() {
		JOptionPane.showMessageDialog(null, getCause(), "Incorrect Input Exception", JOptionPane.ERROR_MESSAGE);
	}

	public IncorrectInputException(String arg0) {
		JOptionPane.showMessageDialog(null, arg0, "Incorrect Input Exception", JOptionPane.ERROR_MESSAGE);
	}

	public IncorrectInputException(Throwable arg0) {
		super(arg0);
	}

	public IncorrectInputException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public IncorrectInputException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
