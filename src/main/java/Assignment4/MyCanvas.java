

/*author :Hanieh */

 
package Assignment4;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class MyCanvas extends Canvas  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Just so that the buttonEventhandler has easy access to the values in the Spinners:
    private JSpinner spnWidth;
    private JSpinner spnHeight;
    private JSpinner spnWater;
    private JSpinner spnTerrain;
    private JSpinner spnTerHeight;   
    
    
    
    
    MyCanvas() {        
        JFrame AppWin = new JFrame();
        AppWin.setTitle("Map Generator");
        AppWin.setBackground(Color.WHITE );
        AppWin.setSize(800, 600);
        AppWin.setVisible(true);
        AppWin.setLocationRelativeTo(null);
        AppWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        


        AppWin.setLayout(new GridLayout(1,2));
        
        JPanel leftSide = new JPanel( new BorderLayout() );
        
        //A panel produce with GridLayout
        JPanel panelLabel = new JPanel( new GridLayout(7,1) );
        panelLabel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        
        
        
        
        JLabel lableWidth = new JLabel("Width");
        
        JLabel lableHeight = new JLabel("Height");
        JLabel lableWater = new JLabel("%Water");
        JLabel lableLake = new JLabel("Lake");
        JLabel lableRiver = new JLabel("%River");
        JLabel lableTerrain = new JLabel("%Terrain");
        JLabel lableTerHeight = new JLabel("Height of Terrain");
        
        
      //Create the lake check boxe.
        JCheckBox lakeBox = new JCheckBox("lake");
        lakeBox.setMnemonic(KeyEvent.VK_C);
        lakeBox.setSelected(true);
        
        
       //create the river check box.
        JCheckBox riverBox = new JCheckBox("River");
        lakeBox.setMnemonic(KeyEvent.VK_C);
        lakeBox.setSelected(true);
        
        

        
     
        
        
        
        
        
        SpinnerNumberModel numModelWi = new SpinnerNumberModel( 50, 20, 1000, 1 ); // Number, min, max, step
        SpinnerNumberModel numModelHe = new SpinnerNumberModel( 50, 20, 1000, 1 ); // Number, min, max, step
        SpinnerNumberModel numModelWa = new SpinnerNumberModel( 30, 0, 100, 1 ); // Number, min, max, step
        SpinnerNumberModel numModelTe = new SpinnerNumberModel( 65, 0, 100, 1 ); // Number, min, max, step
        SpinnerNumberModel numModelTH = new SpinnerNumberModel( 2, 0, 5, 1 ); // Number, min, max, step
        
        spnWidth = new JSpinner(numModelWi);
        JSpinner spnHeight = new JSpinner(numModelHe);
        JSpinner spnWater = new JSpinner(numModelWa);
        JSpinner spnTerrain = new JSpinner(numModelTe);
        JSpinner spnTerHeight = new JSpinner(numModelTH);
        
        //additional
        //lableLake.setBounds(new java.awt.Rectangle(0, 20, 0, 0));
        
       
        
        // Add the label on the left and the corresponding spinner on the right:
        panelLabel.add(lableWidth);
        panelLabel.add(spnWidth);
        
        panelLabel.add(lableHeight);
        panelLabel.add(spnHeight);
        
        panelLabel.add(lakeBox);
        
        panelLabel.add(riverBox);
        
        
        panelLabel.add(lableWater);
        panelLabel.add(spnWater);        
        
        panelLabel.add(lableTerrain);
        panelLabel.add(spnTerrain);        
        
        panelLabel.add(lableTerHeight);
        panelLabel.add(spnTerHeight); 
        
        //panelLabel.add(lakeBox);
        
        //today
        //panelLabel.add(checkPanel, BorderLayout.LINE_START);
       
        
        
        

        
        leftSide.add( panelLabel, BorderLayout.CENTER );
        
        // now the button on the left bottom:
        JButton button = new JButton("Map Generator");
        leftSide.add( button, BorderLayout.SOUTH );
        
        
        
        //add today
        //leftSide.add(lakeBox);
        
        // **** Display map in here: ****
        JPanel panelMap = new JPanel( );
        //add today
        JScrollPane scrollPane = new JScrollPane(panelMap);
        scrollPane.getViewport().add( panelMap );
      
        
        
        
        
        AppWin.setLayout(new BorderLayout());
        AppWin.add(new JScrollPane(panelMap));
        //frame.pack();
        
        
        
        // AppWin.add(panelLabel, BorderLayout.WEST);
        AppWin.add(leftSide, BorderLayout.WEST);
        AppWin.add(panelMap, BorderLayout.EAST);
        
        setVisible(true);       
    }
    
    
   
    
    

    private void addButtonListener(JButton b) {
        b.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent ae) {
                // For now ignore the exact actionCommand:
                // createWorld( ae.getActionCommand() );
                createWorld( );
            }     
        });
    }
    
    private void createWorld( ) {
    	 JSpinner _spiNumOfAIs = null;
		((Integer)_spiNumOfAIs.getValue()).intValue();
    }


	public JSpinner getSpnHeight() {
		return spnHeight;
	}


	public void setSpnHeight(JSpinner spnHeight) {
		this.spnHeight = spnHeight;
	}
}
