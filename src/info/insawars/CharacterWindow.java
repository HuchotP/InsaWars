package info.insawars;

import java.awt.*;
import javax.swing.*;

public class CharacterWindow {

	private int patate;

	public CharacterWindow() {

		JFrame window = new JFrame("Insawars");

		window.setSize(200,400);
		
		Box globalBox = Box.createVerticalBox();
		
		JLabel title = new JLabel("INSAWARS");
		JLabel force = new JLabel("Force");
		title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		force.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		title.setHorizontalAlignment(JLabel.CENTER);
		
		globalBox.add(Box.createVerticalGlue());
		globalBox.add(title, JLabel.CENTER);
		globalBox.add(Box.createVerticalGlue());
		globalBox.add(force);

		
		window.add(globalBox);
		window.setVisible(true);
	}

	@Override
	public String toString() {
		return "CharacterWindow [patate=" + patate + "]";
	}

}
