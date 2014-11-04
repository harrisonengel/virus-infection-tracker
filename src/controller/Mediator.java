package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
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
	private DefaultTreeModel treeModel;
	
	public Mediator(GUI view, DiseaseManipulator diseaseModel){
		treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Diseases"));
		this.diseaseModel = diseaseModel;
		this.view = view;
		addActionListeners(view);
	}
	
	private void addActionListeners(GUI view){
		view.btnAddFile.addActionListener(this); 
		view.btnAddPatient.addActionListener(this);
		view.btnRemovePatient.addActionListener(this);
		view.btnSaveTrees.addActionListener(this);
		view.btnPrintPreorder.addActionListener(this);
		view.tree.addTreeSelectionListener(tsl);
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == view.btnAddFile){
			try {
				String fileName = fileManipulator.getFileName();
				if (fileName != null) {
					this.setDm(fileManipulator.getEasyReader(fileName));
				}

			} catch (NullPointerException np) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			} catch (NumberFormatException nfp) {
				JOptionPane.showMessageDialog(view,
								"Error reading from file. Make sure it exists and is not corrupted!");
			}
		}
	}
	public void setDm(EasyReader er){
			diseaseModel = new DiseaseManipulator(er);
	}
	
	public DefaultTreeModel getTreeModel(){
		return treeModel;
	}
	
	public void modelCurrentData(){
		treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Diseases"));
	}
	
}
