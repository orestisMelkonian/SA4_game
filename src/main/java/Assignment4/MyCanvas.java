
/*author :Hanieh */

package Assignment4;

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;

public class MyCanvas extends Canvas  {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyCanvas() {
		
		
		
		JFrame AppWin = new JFrame("Map Generator");
		AppWin.setBackground(Color.WHITE );
		AppWin.setSize(800, 600);
		AppWin.setVisible(true);
		AppWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AppWin.add(this);
		setVisible(true);
		
	}
	

}
