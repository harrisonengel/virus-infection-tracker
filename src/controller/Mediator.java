package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import view.GUI;
import model.DiseaseManipulator;

/* Mediator
 * The Mediator object is the controller, the 
 * object that sits between the DiseseManipulater(Model)
 * and the GUI(View).
 */
public class Mediator implements ActionListener {
	private DiseaseManipulator diseaseModel;
	private GUI view;
	
	public Mediator(){
	}
	
	public void addActionListeners(final GUI view){
		view.btnAddFile.addActionListener(this); 
		view.btnAddPatient.addActionListener(this);
		view.btnRemovePatient.addActionListener(this);
		view.btnSaveTrees.addActionListener(this);
		view.btnPrintPreorder.addActionListener(this);
		view.tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)view.tree.getLastSelectedPathComponent();
				if (node == null) return;
			}
		});
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == view.btnAddFile) this.addDiseaseFile();
		if (e.getSource() == view.btnAddPatient) this.addPatient();
		if (e.getSource() == view.btnRemovePatient) this.removePatient();
		if (e.getSource() == view.btnSaveTrees) this.saveTrees();
	}
	
	public void setView(GUI view){
		this.view = view;
	}
	
	public void setModel(DiseaseManipulator model){
		this.diseaseModel = model;
	}
	

	
	private void addDiseaseFile(){
			try {
				String fileName = fileManipulator.getFileName();
				if (fileName != null) {
					diseaseModel.createDiseaseForest(fileName);
					view.tree.setModel(diseaseModel.getTreeModel());
				}
			} catch (NullPointerException np) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			} catch (NumberFormatException nfp) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			}
	}

	private void addPatient(){
		String[] patientData = patientPanel.patientPrompt();
		diseaseModel.addInfectedAt(patientData[1], patientData[0], patientData[2], Integer.parseInt(patientData[3]));
		
		
		
	}
	private void removePatient(){
		
	}
	private void saveTrees(){
		
	}

	
	public JTree getTree(){
		return view.tree;
	}
	public DefaultTreeModel getTreeModel(){
		return diseaseModel.getTreeModel();
	}
	
}
