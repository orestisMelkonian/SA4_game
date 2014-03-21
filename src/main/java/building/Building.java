
/*
 * author : Hanieh
*/

package building;
import java.util.ArrayList;

import ptoma.hexoral.units.Unit;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import ptoma.hexoral.user.Player;

public class Building {
	
	ArrayList<Unit> unit = new ArrayList<Unit>();
	public int BaseunitCapacity;
	public int defenseBonus;
	// for the GUI to check 
	private Player p;
	
	// for testing 
	public static void main(String[] args){
		new Building();
	}
	
	public Building(){
		createGUI();
	}
	
	public Unit createUnit(){
		return new Unit(p);
	}

	public void createGUI(){
		//System.out.println("GAY");
		final JFrame frame = new JFrame("BUILDING");
		JPanel leftPanel = new JPanel(new BorderLayout());
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JButton bButton = new JButton("building");
		JButton InfoButton = new JButton("buildingInfo");
		
		leftPanel.add(bButton, BorderLayout.SOUTH);
		frame.add(leftPanel, BorderLayout.WEST);
		
		InfoButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent ae) {
            	JButton createUnitButton = new JButton("Create Unit");
            	JPanel rightPanel = new JPanel(new BorderLayout());
            	rightPanel.add(createUnitButton, BorderLayout.SOUTH);
            	//rightPanel.add(bButton, BorderLayout.SOUTH);
            	
            	Container content = frame.getContentPane();
            	content.add(rightPanel);
            	content.repaint();
            	//frame.setVisible(true);
            }     
        });
		frame.setSize(500,500);
		
		frame.setVisible(true);
		
	}

		
	

	/*
	 *  Building Constructor
	 */
	
	public Building(ArrayList<Unit> unit, int unitCapacity,int defenceBonus, int BaseunitCapacity ) {
		
		this.unit = unit;
		this.BaseunitCapacity = BaseunitCapacity;
		this.defenseBonus = defenceBonus;
		
	}
	

/*
 * getter and setter for fields
 */
	
	public int getUnitCapacity() {
		return BaseunitCapacity;
	}
	
	public void setUnitCapacity(int unitCapacity) {
		this.BaseunitCapacity = unitCapacity;
	}
	
	public void setDefenseBonus(int defenseBonus) {
		this.defenseBonus = defenseBonus;
	}
	
	public int getDefenseBonus() {
		return defenseBonus;
	}
	
	
/*
 * it checks if the building has the capacity for the new unit inside.
 */
	public boolean EnterUnit(Unit e) {
		if (BaseunitCapacity > 0) {
			System.out.println("The building does not have place for the new unit!!");
			return false;
		}
		else{
			BaseunitCapacity = BaseunitCapacity -1;
			unit.add(e);
			
			
			return true;
			
		
	}
		
		
		
		
}
}
	

