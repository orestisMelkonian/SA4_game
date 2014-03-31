package ptoma.hexoral;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import ptoma.hexoral.game.Game;
import ptoma.hexoral.game.action.Action;
import ptoma.hexoral.game.action.AttackAction;
import ptoma.hexoral.map.*;
import ptoma.hexoral.units.Soldier;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

public class MyMain {

	static JFrame aFrame;
	static JSpinner widthArea;
	protected WorldMap map;
	protected int hexagonSize;
	static JSpinner HeightArea;
	static JSpinner groundArea;
	static Canvas cnv;
	static WorldMap localmap;
	static MapGenerator gen;
	static JCheckBox Lake;
	static JCheckBox River;
	static JSpinner waterArea;
	static JList armySummaryList;
	static JList scheduleList;
	static DefaultListModel model = new DefaultListModel();
	static Game game = new Game(100, 100);
	static Player player = new Player("Vitor", game);
	static Player player2 = new Player("Lokesh", game);
	static Action action = new AttackAction(player, new Soldier(player, new Point(10,10)), new Soldier(player2, new Point(10 ,10)));
	static ArrayList<Action> listOfActions = new ArrayList<Action>();
	static JMenuItem anItem;
	static JPopupMenu popup = new JPopupMenu();
	
	
	
	public MyMain(Canvas canvas, WorldMap localMap){
		
		this.cnv = canvas;
		this.localmap = localMap;
		
		initialize();
		this.aFrame.setVisible(true);
		
		
	}

	public static void initialize() {

		localmap = new WorldMap(50, 50);
		cnv = new Visualize(32, localmap);
		aFrame = new JFrame("Border Layout");
		aFrame.setTitle("Island Generator");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		((JFrame) aFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.pack();
		aFrame.setVisible(true);
		aFrame.setLayout(new GridLayout(1, 2));
		JPanel left = new JPanel(new GridLayout(3, 2));
		JPanel bottom = new JPanel(new GridLayout(4, 2));
		aFrame.setSize(1024, 493);
		try {
			aFrame.setIconImage(ImageIO.read(new File(
					"src/main/resources/Logo_w_space.png")));
		} catch (IOException e1) {
			System.out.println("Icon Image not found");
		}

//		JButton generate = new JButton();
//		generate.setText("Generate");
//		generate.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//								
		localmap.setSizeY((Integer) GameUISettings.widthSpinner.getValue());
		localmap.setSizeX((Integer) GameUISettings.heightSpinner.getValue());
		localmap.erase();
		String biome = "test.bio";
		
		gen = new MapGenerator(localmap, biome);
		
		gen.applyParameters((Integer) GameUISettings.percentageOfGroundSpinner.getValue(),
				(Integer) GameUISettings.percentageOfWaterInsideSpinner.getValue(), GameUISettings.lakeCheckBox.isSelected(),
				GameUISettings.riverCheckBox.isSelected(),(Integer) GameUISettings.percentageOfResourceSpinner.getValue());
		//gen.printPercentage();
		cnv.repaint();
		Thread clean = new Thread(new Runnable() {

			public void run() {
				System.gc();

			}
		});
		clean.start();
//			}
//		});

		
		
		JButton player1 = new JButton("Player 1");
		
		JButton player2 = new JButton("Player 2");
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		
		
		JPanel userInfoTab = new JPanel();
		tabbedPane.addTab("User Info", null, userInfoTab, null);
		userInfoTab.setLayout(new GridLayout(0, 1, 0, 0));
		
		armySummaryList = new JList();
		userInfoTab.add(armySummaryList);
		
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Schedule Actions", null, scheduleTab, null);
		
		scheduleList = new JList(model);
		scheduleTab.add(scheduleList);
		anItem = new JMenuItem("Delete Action");
		popup.add(anItem);
		scheduleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				popup.show(null, e.getXOnScreen(), e.getYOnScreen());
			}
		});
		
		
		
		JPanel unitInfoTab = new JPanel();
		tabbedPane.addTab("Unit Info", null, unitInfoTab, null);
		
		
		
		
		
		
		// width
//		JPanel WidthPanel = new JPanel(new GridLayout(1, 2));
//		widthArea = new JSpinner();
//		widthArea.setValue(50);
//		WidthPanel.add(new JLabel("Width"));
//		WidthPanel.add(widthArea);

		// height
//		JPanel HeightPanel = new JPanel(new GridLayout(1, 2));
//		HeightArea = new JSpinner();
//		HeightPanel.add(new JLabel("Height"));
//		HeightArea.setValue(50);
//		HeightPanel.add(HeightArea);

		// checkbox
//		JPanel checkBoxes = new JPanel(new GridLayout(1, 2));
//		Lake = new JCheckBox();
//		Lake.setText("Lake");
//		River = new JCheckBox();
//		River.setText("River");
//		checkBoxes.add(Lake);
//		checkBoxes.add(River);

		// ground
//		JPanel groundPanel = new JPanel(new GridLayout(1, 2));
//		groundArea = new JSpinner(new SpinnerNumberModel(100, 20, 100, 1));
//		groundPanel.add(new JLabel("%Ground"));
//		groundPanel.add(groundArea);

		// water inside
//		JPanel waterPanel = new JPanel(new GridLayout(1, 2));
//		if ((River.isSelected()) && !(Lake.isSelected()))
//			waterArea = new JSpinner(new SpinnerNumberModel(30, 0, 30, 1));
//		else if ((Lake.isSelected()) && !(River.isSelected()))
//			waterArea = new JSpinner(new SpinnerNumberModel(70, 0, 70, 1));
//		else
//			waterArea = new JSpinner(new SpinnerNumberModel(50, 0, 50, 1));
//		waterPanel.add(new JLabel("%Water Inside"));
//		waterPanel.add(waterArea);

//		left.add(WidthPanel);
//		left.add(HeightPanel);
//		left.add(checkBoxes);
//		left.add(groundPanel);
//		left.add(waterPanel);
		left.add(player1);
		left.add(player2);
		left.add(tabbedPane, gbc_tabbedPane);
		//left.add(generate);
		aFrame.add(left, BorderLayout.WEST);
		cnv.setBackground(Color.GRAY);
		aFrame.add(cnv);
		//aFrame.add(bottom, BorderLayout.WEST);
		action.drawActions();
		action.drawActions();
		
		
		aFrame.setVisible(true);
		
	}

	public static int counter = 0;
	public static void addToScheduleList(String type, Action action){
		model.addElement(type + counter);
		counter++;
	}
	
	
}
