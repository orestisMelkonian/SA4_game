package building;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class HQ {
	
	private Player p;
	
	public static void main(String[] args){
		new HQ();
	}
		
	
	public HQ(){
		createGUI();
	}
	
	public Unit createUnit(){
		return new Unit(p);
	}

	public void createGUI(){
		//System.out.println("GAY");
		final JFrame frame = new JFrame("HQ");
		JPanel leftPanel = new JPanel(new BorderLayout());
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JButton HQButton = new JButton("HQ");
		leftPanel.add(HQButton, BorderLayout.NORTH);
		frame.add(leftPanel, BorderLayout.WEST);
		
		HQButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent ae) {
            	JButton createUnitButton = new JButton("Create Unit");
            	JPanel rightPanel = new JPanel(new BorderLayout());
            	rightPanel.add(createUnitButton, BorderLayout.NORTH);
            	Container content = frame.getContentPane();
            	content.add(rightPanel);
            	content.repaint();
            }     
        });
		frame.setSize(500,500);
		
		frame.setVisible(true);
		
	}
}
