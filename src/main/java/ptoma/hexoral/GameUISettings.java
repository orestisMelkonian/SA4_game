package ptoma.hexoral;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;

import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.Visualize;
import ptoma.hexoral.map.WorldMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameUISettings {
	protected WorldMap map;
	protected int hexagonSize;
	static Canvas cnv;
	static JSpinner percentageOfWaterInsideSpinner;
	static WorldMap localmap;
	static MapGenerator gen;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		localmap = new WorldMap(50, 50);
		cnv = new Visualize(32, localmap);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUISettings window = new GameUISettings();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameUISettings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelOfSettings = new JPanel();
		frame.getContentPane().add(panelOfSettings, BorderLayout.CENTER);
		panelOfSettings.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblWidth = new JLabel("Width");
		panelOfSettings.add(lblWidth);
		
		final JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 1000, 1));
		panelOfSettings.add(widthSpinner);
		
		JLabel lblHeight = new JLabel("Height");
		panelOfSettings.add(lblHeight);
		
		final JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 1000, 1));
		panelOfSettings.add(heightSpinner);
		
		final JCheckBox lakeCheckBox = new JCheckBox("Lake");
		panelOfSettings.add(lakeCheckBox);
		
		final JCheckBox riverCheckBox = new JCheckBox("River");
		panelOfSettings.add(riverCheckBox);
		
		JLabel lblPercentageOfGround = new JLabel("Percentage Of Ground");
		panelOfSettings.add(lblPercentageOfGround);
		
		final JSpinner percentageOfGroundSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 100, 1));
		panelOfSettings.add(percentageOfGroundSpinner);
		
		JLabel lblPercentageOfWaterInside = new JLabel("Percentage Of Water Inside");
		panelOfSettings.add(lblPercentageOfWaterInside);

		
		
		
//		JSpinner percentageOfWaterInsideSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 100, 1));
//		panelOfSettings.add(percentageOfWaterInsideSpinner);
		
		JLabel lblPercentageOfResource = new JLabel("Percentage of Resource");
		panelOfSettings.add(lblPercentageOfResource);
		
		final JSpinner percentageOfResourceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		panelOfSettings.add(percentageOfResourceSpinner);
		
		
		if ((riverCheckBox.isSelected()) && !(lakeCheckBox.isSelected())){
			percentageOfWaterInsideSpinner = new JSpinner(new SpinnerNumberModel(30, 0, 30, 1));
			panelOfSettings.add(percentageOfWaterInsideSpinner);
		}
		else if ((lakeCheckBox.isSelected()) && !(riverCheckBox.isSelected())){
			percentageOfWaterInsideSpinner = new JSpinner(new SpinnerNumberModel(70, 0, 70, 1));
			panelOfSettings.add(percentageOfWaterInsideSpinner);
		}
		else{
			percentageOfWaterInsideSpinner = new JSpinner(new SpinnerNumberModel(50, 0, 50, 1));
			panelOfSettings.add(percentageOfWaterInsideSpinner);
		}
		
		
		
		JPanel panelOfButton = new JPanel();
		frame.getContentPane().add(panelOfButton, BorderLayout.SOUTH);
		panelOfButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton buttonPanel = new JButton("Generate Game");
		buttonPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				localmap.setSizeY((Integer) widthSpinner.getValue());
				localmap.setSizeX((Integer) heightSpinner.getValue());
				localmap.erase();
				String biome = "test.bio";
				
				gen = new MapGenerator(localmap, biome);
				
				gen.applyParameters((Integer) percentageOfGroundSpinner.getValue(),
						(Integer) percentageOfWaterInsideSpinner.getValue(), lakeCheckBox.isSelected(),
						riverCheckBox.isSelected());
				//gen.printPercentage();
				cnv.repaint();
				new GameUI(cnv, localmap);
				frame.setVisible(false);
				
				System.out.println("Generating the bukake map");
				Thread clean = new Thread(new Runnable() {
				
					public void run() {
						System.gc();
				
					}
				});
				clean.start();
				}
		});
		panelOfButton.add(buttonPanel);
		buttonPanel.setVerticalAlignment(SwingConstants.BOTTOM);
	}

}
