
package ptoma.hexoral;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JTabbedPane;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.map.MapGenerator;
import ptoma.hexoral.map.WorldMap;

import java.awt.GridLayout;

public class GameUI {

	private JFrame frame;
	Canvas mapCanvas;
	WorldMap localMap;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GameUI window = new GameUI(new Canvas());
//					window.frame.setVisible(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param cnv 
	 */
	public GameUI(Canvas cnv, WorldMap localmap) {
		initialize();
		mapCanvas = cnv;
		localMap = localmap;
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		JPanel mapPanel = new JPanel();
//		frame.getContentPane().add(mapPanel, BorderLayout.WEST);
		
		//mapPanel.add(mapCanvas);
		
		
		
		
		
		
		
		JPanel leftMenu = new JPanel();
		frame.getContentPane().add(leftMenu, BorderLayout.WEST);
		GridBagLayout gbl_leftMenu = new GridBagLayout();
		gbl_leftMenu.columnWidths = new int[]{0, 0};
		gbl_leftMenu.rowHeights = new int[]{0, 0, 0};
		gbl_leftMenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_leftMenu.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		leftMenu.setLayout(gbl_leftMenu);
		
		JButton btnP1 = new JButton("PLAYER 1");
		GridBagConstraints gbc_btnP1 = new GridBagConstraints();
		gbc_btnP1.insets = new Insets(0, 0, 5, 0);
		gbc_btnP1.gridx = 0;
		gbc_btnP1.gridy = 0;
		leftMenu.add(btnP1, gbc_btnP1);
		
		JButton btnP2 = new JButton("PLAYER 2");
		GridBagConstraints gbc_btnP2 = new GridBagConstraints();
		gbc_btnP2.gridx = 0;
		gbc_btnP2.gridy = 1;
		leftMenu.add(btnP2, gbc_btnP2);
		
		JPanel bottomMenu = new JPanel();
		frame.getContentPane().add(bottomMenu, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomMenu = new GridBagLayout();
		gbl_bottomMenu.columnWidths = new int[]{0, 0};
		gbl_bottomMenu.rowHeights = new int[]{0, 0};
		gbl_bottomMenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottomMenu.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		bottomMenu.setLayout(gbl_bottomMenu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		bottomMenu.add(tabbedPane, gbc_tabbedPane);
		
		JPanel userInfoTab = new JPanel();
		tabbedPane.addTab("User Info", null, userInfoTab, null);
		userInfoTab.setLayout(new GridLayout(0, 1, 0, 0));
		
		JList armySummaryList = new JList();
		userInfoTab.add(armySummaryList);
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Schedule Actions", null, scheduleTab, null);
		
		JList scheduleList = new JList();
		scheduleTab.add(scheduleList);
		
		JPanel unitInfoTab = new JPanel();
		tabbedPane.addTab("Unit Info", null, unitInfoTab, null);
		
		
		Container content = frame.getContentPane();
		content.add(mapCanvas, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
