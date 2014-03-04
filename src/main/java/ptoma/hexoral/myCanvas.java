package ptoma.hexoral;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;





public class myCanvas extends Canvas{
	
	Color color;
	
	public myCanvas() {
		// TODO Auto-generated constructor stub
		
		super();
		
		JFrame appWindow = new JFrame("painting here!");
		appWindow.setSize(600, 800);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.setBackground(Color.darkGray);
		appWindow.setVisible(true);
		appWindow.setLayout(new GridLayout());
		appWindow.add(this);
		setVisible(true);
		color = Color.WHITE;
		setBackground(color);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}