package execptions;

//A simple exception class used to throw custom exceptions
//and prevent user accidents.
public class CancelOptionSelected extends Exception {

	private static final long serialVersionUID = 1L;

	public CancelOptionSelected() {
		
	}

	public CancelOptionSelected(String arg0) {
		
	}

	public CancelOptionSelected(Throwable arg0) {
		super(arg0);
		
	}

	public CancelOptionSelected(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CancelOptionSelected(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
