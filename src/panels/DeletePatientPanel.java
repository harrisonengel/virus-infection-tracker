package panels;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import model.DiseaseManipulator;
import execptions.IncorrectInputException;

/*This panel extends the GetPathPanel, but with a different title
 * for use in deleting a patient.
 * @see panels#patientPanel
 */

public class DeletePatientPanel extends GetPathPanel{
	
	private static final long serialVersionUID = 1L;

	public DeletePatientPanel(DiseaseManipulator dm) throws IncorrectInputException {
		super(dm);
		super.lblTitle = new JLabel("DELETE PATIENT");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("DialogInput", Font.PLAIN, 24));
			lblTitle.setForeground(Color.GREEN);
			lblTitle.setBounds(10, 11, 430, 33);
		add(lblTitle);
	}

}
