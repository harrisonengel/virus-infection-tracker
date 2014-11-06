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
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	public Mediator controller;
	private JPanel panel_header, panel_buttons;
	private JLabel lblTitle, lblVirusImageL, lblVirusImageR;
	public JButton btnAddFile, btnAddPatient, btnRemovePatient, btnGetPath,
			btnPrintPreorder;
	public JScrollPane scrollPanePrinter;
	public JTextArea textArea_1;
	public JButton btnPrintInfo;
	public JButton btnAllInfectedBy;

	public GUI() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1000, 700);

		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setBounds(100, 100, 700, 576);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		Panel panel = new Panel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(225, 162, 449, 365);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		scrollPanePrinter = new JScrollPane();
		scrollPanePrinter
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePrinter
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPanePrinter);

		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("DialogInput", Font.PLAIN, 14));
		textArea_1.setForeground(Color.GREEN);
		textArea_1.setBackground(Color.BLACK);
		scrollPanePrinter.setViewportView(textArea_1);

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

		btnGetPath = new JButton("GET PATH");
		btnGetPath.setFont(new Font("DialogInput", Font.PLAIN, 12));

		btnGetPath.setBackground(new Color(0, 255, 0));
		btnGetPath.setBounds(10, 172, 189, 38);
		panel_buttons.add(btnGetPath);

		btnPrintPreorder = new JButton("PRINT PREORDER");
		btnPrintPreorder.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnPrintPreorder.setBackground(Color.GREEN);
		btnPrintPreorder.setBounds(10, 221, 189, 38);
		panel_buttons.add(btnPrintPreorder);
		
		btnPrintInfo = new JButton("PRINT PATIENT INFO");
		btnPrintInfo.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnPrintInfo.setBackground(Color.GREEN);
		btnPrintInfo.setBounds(10, 270, 189, 38);
		panel_buttons.add(btnPrintInfo);
		
		btnAllInfectedBy = new JButton("ALL INFECTED BY");
		btnAllInfectedBy.setFont(new Font("DialogInput", Font.PLAIN, 12));
		btnAllInfectedBy.setBackground(Color.GREEN);
		btnAllInfectedBy.setBounds(10, 319, 189, 38);
		panel_buttons.add(btnAllInfectedBy);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setVisible(true);
	}

	public JTextArea getDisplayArea() {
		return this.textArea_1;
	}

	public void setController(Mediator controller) {
		this.controller = controller;
	}
}
