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
import ptoma.hexoral.game.scheduler.Scheduler;
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
	static DefaultListModel armyModel = new DefaultListModel();
	static Game game = new Game(100, 100);
	static Player p1;
	static Player p2;
	static Point point = new Point(10, 20);
	static Action action; 
	static ArrayList<Action> listOfActions = new ArrayList<Action>();
	static JMenuItem anItem;
	static JPopupMenu popup = new JPopupMenu();
	static JPanel userInfoTab;
	static Scheduler scheduler;
	
	
	
	public MyMain(Canvas canvas, WorldMap localMap){
		
		this.cnv = canvas;
		this.localmap = localMap;
		p1 = new Player(GameUISettings.textField.getText(), game);
		p2 = new Player(GameUISettings.textField_1.getText(), game);
		action = new AttackAction(p1, new Soldier(p1, point), new Soldier(p2, point));
		scheduler = new Scheduler(game, p1);
		p1.getSchedule().addAction(action);
		game.createUnit(p1, new Soldier(p1, point));
		initialize();
		addToScheduleList(p1);
		addToPlayerUnitList(p1);
		this.aFrame.setVisible(true);
		
		
	}
	
	public MyMain(Game game){
		this.game = game;
		this.localmap = game.island;
		cnv = new Visualize(32, localmap);
		initializenewWindow();
		refresh();
		this.aFrame.setVisible(true);
	}
	
	public static void initializenewWindow(){
		
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
		aFrame.getContentPane().setLayout(new GridLayout(1, 2));
		JPanel left = new JPanel(new GridLayout(3, 2));
		JPanel bottom = new JPanel(new GridLayout(4, 2));
		aFrame.setSize(1024, 493);
		try {
			aFrame.setIconImage(ImageIO.read(new File(
					"src/main/resources/Logo_w_space.png")));
		} catch (IOException e1) {
			System.out.println("Icon Image not found");
		}
								
	
		cnv.repaint();
		Thread clean = new Thread(new Runnable() {

			public void run() {
				System.gc();

			}
		});
		clean.start();
		
		JButton executeTurn = new JButton("Execute Turn");
		
		executeTurn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				//TODO
			}
		});
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		
		
		JPanel userInfoTab = new JPanel();
		tabbedPane.addTab("User Info", null, userInfoTab, null);
		userInfoTab.setLayout(new GridLayout(0, 1, 0, 0));
		
		armySummaryList = new JList(armyModel);
		userInfoTab.add(armySummaryList);
		
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Turn Viewer", null, scheduleTab, null);
		
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
		tabbedPane.addTab("Army Summary", null, unitInfoTab, null);
		

		
		left.add(executeTurn);
		left.add(tabbedPane, gbc_tabbedPane);
		aFrame.getContentPane().add(left, BorderLayout.WEST);
		cnv.setBackground(Color.GRAY);
		aFrame.getContentPane().add(cnv);
		aFrame.setVisible(true);
		
	}
	

	public static void initialize() {

		//localmap = new WorldMap(50, 50);
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
		aFrame.getContentPane().setLayout(new GridLayout(1, 2));
		JPanel left = new JPanel(new GridLayout(3, 2));
		JPanel bottom = new JPanel(new GridLayout(4, 2));
		aFrame.setSize(1024, 493);
		try {
			aFrame.setIconImage(ImageIO.read(new File(
					"src/main/resources/Logo_w_space.png")));
		} catch (IOException e1) {
			System.out.println("Icon Image not found");
		}
								
		localmap.setSizeY((Integer) GameUISettings.widthSpinner.getValue());
		localmap.setSizeX((Integer) GameUISettings.heightSpinner.getValue());
		localmap.erase();
		
		
		localmap.randomizeIsland((Integer) GameUISettings.percentageOfGroundSpinner.getValue(),
				(Integer) GameUISettings.percentageOfWaterInsideSpinner.getValue(), GameUISettings.lakeCheckBox.isSelected(),
				GameUISettings.riverCheckBox.isSelected(),(Integer) GameUISettings.percentageOfResourceSpinner.getValue());
		cnv.repaint();
		Thread clean = new Thread(new Runnable() {

			public void run() {
				System.gc();

			}
		});
		clean.start();

		
		JButton player1 = new JButton("Player 1");
		
		JButton player2 = new JButton("Player 2");
		
		JButton executeTurn = new JButton("Execute Turn");
		
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		
		
		JPanel userInfoTab = new JPanel();
		tabbedPane.addTab("User Info", null, userInfoTab, null);
		userInfoTab.setLayout(new GridLayout(0, 1, 0, 0));
		
		armySummaryList = new JList(armyModel);
		userInfoTab.add(armySummaryList);
		
		
		JPanel scheduleTab = new JPanel();
		tabbedPane.addTab("Turn Viewer", null, scheduleTab, null);
		
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
		tabbedPane.addTab("Army Summary", null, unitInfoTab, null);
		

		
		left.add(executeTurn);
		left.add(tabbedPane, gbc_tabbedPane);
		aFrame.getContentPane().add(left, BorderLayout.WEST);
		cnv.setBackground(Color.GRAY);
		aFrame.getContentPane().add(cnv);
		aFrame.setVisible(true);
		
	}

	public static int counter = 0;
	
	public static void addToPlayerUnitList(Player player){
		armyModel.removeAllElements();
		
		for(int i =0; i < game.getPlayerUnits(player).size() ; i++){
			armyModel.addElement("Unit: " + game.getPlayerUnits(player).get(i));
		}
		//userInfoTab.add(armySummaryList);
	}
	
	public static void addToScheduleList(Player player){
		
		model.removeAllElements();
		
		for( Action a : player.getSchedule().toArray()){
			model.addElement(a.toString());
			
		}
	}
	
	public static void refresh(){
		
		for(int i = 0; i < game.getAllPlayers().size(); i++){
			addToScheduleList(game.getAllPlayers().get(i));
			addToPlayerUnitList(game.getAllPlayers().get(i));
		}
		
	}
	
	
}
