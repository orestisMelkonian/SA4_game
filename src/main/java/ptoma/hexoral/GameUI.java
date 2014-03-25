
package ptoma.hexoral;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class GameUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameUI window = new GameUI();
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
	public GameUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mapPanel = new JPanel();
		frame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		
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
		GridBagLayout gbl_userInfoTab = new GridBagLayout();
		gbl_userInfoTab.columnWidths = new int[] {100, 0, 0};
		gbl_userInfoTab.rowHeights = new int[] {15, 15, 0};
		gbl_userInfoTab.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_userInfoTab.rowWeights = new double[]{0.0, 0.0};
		userInfoTab.setLayout(gbl_userInfoTab);
		
		JLabel unitCounterFxd = new JLabel("Unit counter");
		unitCounterFxd.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_unitCounterFxd = new GridBagConstraints();
		gbc_unitCounterFxd.fill = GridBagConstraints.BOTH;
		gbc_unitCounterFxd.insets = new Insets(0, 0, 5, 5);
		gbc_unitCounterFxd.gridx = 0;
		gbc_unitCounterFxd.gridy = 0;
		userInfoTab.add(unitCounterFxd, gbc_unitCounterFxd);
		
		JLabel unitCounterValue = new JLabel("0");
		unitCounterValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_unitCounterValue = new GridBagConstraints();
		gbc_unitCounterValue.fill = GridBagConstraints.BOTH;
		gbc_unitCounterValue.insets = new Insets(0, 0, 5, 0);
		gbc_unitCounterValue.gridx = 1;
		gbc_unitCounterValue.gridy = 0;
		userInfoTab.add(unitCounterValue, gbc_unitCounterValue);
		
		JLabel buildCounterFxd = new JLabel("Build counter");
		buildCounterFxd.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_buildCounterFxd = new GridBagConstraints();
		gbc_buildCounterFxd.fill = GridBagConstraints.BOTH;
		gbc_buildCounterFxd.insets = new Insets(0, 0, 0, 5);
		gbc_buildCounterFxd.gridx = 0;
		gbc_buildCounterFxd.gridy = 1;
		userInfoTab.add(buildCounterFxd, gbc_buildCounterFxd);
		
		JLabel buildCounterValue = new JLabel("0");
		buildCounterValue.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_buildCounterValue = new GridBagConstraints();
		gbc_buildCounterValue.fill = GridBagConstraints.BOTH;
		gbc_buildCounterValue.gridx = 1;
		gbc_buildCounterValue.gridy = 1;
		userInfoTab.add(buildCounterValue, gbc_buildCounterValue);
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Schedule Actions", null, scheduleTab, null);
		
		JList scheduleList = new JList();
		scheduleTab.add(scheduleList);
		
		JPanel unitTab = new JPanel();
		tabbedPane.addTab("Unit Info", null, unitTab, null);
	}

}
