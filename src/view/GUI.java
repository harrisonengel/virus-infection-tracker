/*******************************************************************/
/*   Program Name:     Lab 2    VIRUS                              */
/*                                                                 */
/*   Student Name:     Harrison Engel                              */
/*   Semester:         Fall 2014                                   */
/*   Class-Section:    COSC 20803-035                              */
/*   Instructor:       Dr. Comer                                   */
/*******************************************************************/

package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import controller.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Font;

import javax.swing.JTextArea;

public class GUI extends JFrame {

	public Mediator controller;
	public JTree tree;	
	private JPanel panel_header, panel_buttons, panel_display;
	private JLabel lblTitle, lblVirusImageL, lblVirusImageR;
	public JButton btnAddFile, btnAddPatient,  btnRemovePatient, btnSaveTrees, btnPrintPreorder;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public GUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setBounds(100, 100, 700, 576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		tree = new JTree();
		tree.setForeground(Color.GREEN);
		tree.setFont(new Font("DialogInput", Font.PLAIN, 14));
		tree.setBackground(Color.LIGHT_GRAY);
		tree.setShowsRootHandles(true);
		tree.setCellRenderer(new virusTreeCell());
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		/*tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if (node == null) return;
			}
		});*/

		panel_header = new JPanel();
		panel_header.setBackground(Color.GRAY);
		panel_header.setBounds(0, 0, 684, 156);
		this.getContentPane().add(panel_header);
		panel_header.setLayout(null);

		lblTitle = new JLabel();
		lblTitle.setBounds(215, -38, 259, 194);
		lblTitle.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus 2.jpg")));
		panel_header.add(lblTitle);

		lblVirusImageL = new JLabel();
		lblVirusImageL.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus3.jpg")));
		lblVirusImageL.setBounds(10, 0, 205, 156);
		panel_header.add(lblVirusImageL);

		lblVirusImageR = new JLabel("New label");
		lblVirusImageR.setIcon(new ImageIcon(GUI.class
				.getResource("/view/virus3.jpg")));
		lblVirusImageR.setBounds(474, 0, 200, 156);
		panel_header.add(lblVirusImageR);

		panel_buttons = new JPanel();
		panel_buttons.setBackground(Color.DARK_GRAY);
		panel_buttons.setBounds(10, 167, 209, 360);
		this.getContentPane().add(panel_buttons);
		panel_buttons.setLayout(null);

		btnAddFile = new JButton("ADD DISEASE TREES");
		btnAddFile.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnAddFile.setBackground(new Color(0, 255, 0));
		btnAddFile.setBounds(10, 11, 189, 38);
		panel_buttons.add(btnAddFile);

		btnAddPatient = new JButton("ADD PATIENT");
		btnAddPatient.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnAddPatient.setBackground(new Color(0, 255, 0));
		btnAddPatient.setBounds(10, 63, 189, 38);
		panel_buttons.add(btnAddPatient);

		btnRemovePatient = new JButton("REMOVE PATIENT");
		btnRemovePatient.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnRemovePatient.setBackground(new Color(0, 255, 0));
		btnRemovePatient.setBounds(10, 117, 189, 38);
		panel_buttons.add(btnRemovePatient);

		btnSaveTrees = new JButton("SAVE TREES");
		btnSaveTrees.setFont(new Font("DialogInput", Font.PLAIN, 12));
		/*btnSaveTrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {

					} catch (IOException i) {
						JOptionPane.showMessageDialog(frame,
										"Error saving file. Make sure you are using a .txt extension!");
					}
				} 
				
			}
		});*/
		btnSaveTrees.setBackground(new Color(0, 255, 0));
		btnSaveTrees.setBounds(10, 172, 189, 38);
		panel_buttons.add(btnSaveTrees);
		
		btnPrintPreorder = new JButton("PRINT PREORDER");
		btnPrintPreorder.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnPrintPreorder.setBackground(Color.GREEN);
		btnPrintPreorder.setBounds(10, 221, 189, 38);
		panel_buttons.add(btnPrintPreorder);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.GREEN);
		textArea.setFont(new Font("DialogInput", Font.PLAIN, 12));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 270, 189, 79);
		panel_buttons.add(textArea);

		panel_display = new JPanel();
		panel_display.setBackground(new Color(192, 192, 192));
		panel_display.setBounds(229, 167, 445, 360);
		this.getContentPane().add(panel_display);
		panel_display.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 445, 322);
		panel_display.add(scrollPane);
		
		scrollPane.setColumnHeaderView(tree);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setVisible(true);
	}
	
	public void setController(Mediator controller){
		this.controller = controller;
		this.tree.setModel(controller.getTreeModel());
	}
}
