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

import ptoma.hexoral.game.Game;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.Visualize;
import ptoma.hexoral.map.WorldMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

public class GameUISettings {
	protected WorldMap map;
	protected int hexagonSize;
	static Canvas cnv;
	static JSpinner percentageOfWaterInsideSpinner;
	static WorldMap localmap;
	static MapGenerator gen;
	private JFrame frame;
	static JSpinner widthSpinner;
	static JSpinner heightSpinner;
	static JCheckBox lakeCheckBox;
	static JCheckBox riverCheckBox;
	static JSpinner percentageOfGroundSpinner;
	static JSpinner percentageOfResourceSpinner;
	public static MyMain WINDOW;
	static JTextField textField;
	static JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		localmap = new WorldMap(50, 50);
		//cnv = new Visualize(32, localmap);
		
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
	 * Create the application.
	 */
	public GameUISettings(Game game) {
		WINDOW = new MyMain(game);
		
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
		
		widthSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 1000, 1));
		panelOfSettings.add(widthSpinner);
		
		JLabel lblHeight = new JLabel("Height");
		panelOfSettings.add(lblHeight);
		
		heightSpinner = new JSpinner(new SpinnerNumberModel(50, 1, 1000, 1));
		panelOfSettings.add(heightSpinner);
		
		lakeCheckBox = new JCheckBox("Lake");
		panelOfSettings.add(lakeCheckBox);
		
		riverCheckBox = new JCheckBox("River");
		panelOfSettings.add(riverCheckBox);
		
		JLabel lblPercentageOfGround = new JLabel("Percentage Of Ground");
		panelOfSettings.add(lblPercentageOfGround);
		
		percentageOfGroundSpinner = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1));
		panelOfSettings.add(percentageOfGroundSpinner);
		
		

		
			
		JLabel lblPercentageOfResource = new JLabel("Percentage of Resource");
		panelOfSettings.add(lblPercentageOfResource);
		
		percentageOfResourceSpinner = new JSpinner(new SpinnerNumberModel(20, 0, 100, 1));
		panelOfSettings.add(percentageOfResourceSpinner);
		
		
		JLabel lblPercentageOfWaterInside = new JLabel("Percentage Of Water Inside");
		panelOfSettings.add(lblPercentageOfWaterInside);
		
		percentageOfWaterInsideSpinner = new JSpinner(new SpinnerNumberModel(30, 0, 100, 1));
		panelOfSettings.add(percentageOfWaterInsideSpinner);
		
		
		
		
		
		JPanel panelOfButton = new JPanel();
		frame.getContentPane().add(panelOfButton, BorderLayout.SOUTH);
		panelOfButton.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton buttonPanel = new JButton("Generate Game");
		buttonPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WINDOW = new MyMain(cnv, localmap);
				}
			
		});
		panelOfButton.add(buttonPanel);
		buttonPanel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel player1 = new JLabel("Player 1");
		panel.add(player1);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1);
		
		JLabel player2 = new JLabel("Player 2");
		panel.add(player2);
	}

	
	
}
