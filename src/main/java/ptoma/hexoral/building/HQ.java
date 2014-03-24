package ptoma.hexoral.building;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;
import ptoma.hexoral.user.User;

public class HQ {
	
	/**
	 * Refernce to the owner of the HQ
	 */
	private Player owner;

	/**
	 * The position of the HQ
	 */
	private Point position;
	
	/**
	 * The health of the HQ.
	 */
	protected long health;
		
	
	public HQ(Player owner, int x, int y){
		this.owner = owner;
		this.position = new Point(x, y);
	}
	
	/**
	 * Returns a reference of the owner of the unit.
	 * 
	 * @return Player the owner.
	 */
	public Player getOwner() {
		return owner;
	}
	
	public Point getPosition() {
		return position;
	}
	

	public long getHealth(){
		return health;
	}
	
//	public Unit createNewUnit(){
//		return new Unit(owner, position);
//	}
//	

//	public void createGUI(){
//		//System.out.println("GAY");
//		final JFrame frame = new JFrame("HQ");
//		final JPanel leftPanel = new JPanel(new BorderLayout());
//		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//		JButton HQButton = new JButton("HQ");
//		leftPanel.add(HQButton, BorderLayout.NORTH);
//		final JPanel rightPanel = new JPanel(new BorderLayout());
//		HQButton.addActionListener(new ActionListener() { 
//            public void actionPerformed(ActionEvent ae) {
//            	JButton createUnitButton = new JButton("Create Unit");
//            	rightPanel.add(createUnitButton, BorderLayout.NORTH);
//            	Container content = frame.getContentPane();
//            	content.add(rightPanel);
//            	content.repaint();
//            	createUnitButton.addActionListener(new ActionListener() { 
//                    public void actionPerformed(ActionEvent ae) {
//                    	Unit unit = createUnit();
//                    	
//                    }     
//                });
//            }     
//        });
//		Container content = frame.getContentPane();
//    	content.add(rightPanel);
//    	
//    	frame.add(leftPanel, BorderLayout.WEST);
//		frame.setSize(500,500);
//		
//		frame.setVisible(true);
//		
//	}
}
