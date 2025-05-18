package Game1;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) 
	{
		JFrame windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.setResizable(false);
		windowFrame.setTitle("Adventure Game");
		
		GPanel gPanel = new GPanel();
		windowFrame.add(gPanel);
		
		windowFrame.pack();
		
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setVisible(true);
	}
}