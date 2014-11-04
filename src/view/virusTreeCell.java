package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

public class virusTreeCell extends DefaultTreeCellRenderer{
	
	public virusTreeCell(){
		setLeafIcon(new ImageIcon(GUI.class.getResource("/view/lilVirus.jpg")));
		setOpenIcon(new ImageIcon(GUI.class
				.getResource("/view/lilVirus.jpg")));
		setClosedIcon(new ImageIcon(GUI.class
				.getResource("/view/lilVirus.jpg")));
		setBackgroundNonSelectionColor(Color.LIGHT_GRAY);
		setBackgroundSelectionColor(Color.LIGHT_GRAY);
		setBorderSelectionColor(Color.GREEN);
		setFont(new Font("DialogInput", Font.PLAIN, 14));
		setTextSelectionColor(Color.green);
	}

}
