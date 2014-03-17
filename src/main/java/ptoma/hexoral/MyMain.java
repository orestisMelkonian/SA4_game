package ptoma.hexoral;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import ptoma.hexoral.map.*;

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

	public static void main(String[] args) {

		localmap = new WorldMap(50, 50);
		gen = new MapGenerator();
		gen.generateMap(localmap, 50, "test.bio");
		cnv = new Visualize(32, localmap);
		aFrame = new JFrame("Border Layout");
		aFrame.setTitle("Island Generator");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		// TESTING
		((JFrame) aFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.pack();
		aFrame.setVisible(true);
		aFrame.setLayout(new GridLayout(1, 2));
		JPanel left = new JPanel(new GridLayout(6, 2));
		aFrame.setSize(1024, 493);
		try {
			aFrame.setIconImage(ImageIO.read(new File(
					"src/main/resources/Logo_w_space.png")));
		} catch (IOException e1) {
			System.out.println("Icon Image not found");
		}

		JButton generate = new JButton();
		generate.setText("Generate");
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				localmap.setSizeY((Integer) widthArea.getValue());
				localmap.setSizeX((Integer) HeightArea.getValue());
				localmap.erase();
				String biome = "test.bio";
				gen.generateMap(localmap, (Integer) groundArea.getValue(),
						biome);
				localmap.applyGroundPercentage((Integer) groundArea.getValue() % 101);
				localmap.cleanIsland();
				localmap.cleanIsland();
				localmap.cleanSea();
				localmap.cleanUp();
				int waterPercentage = (Integer) waterArea.getValue();
				if ((River.isSelected()) && (River.isSelected())) {
					int lakeNo = localmap.decideLakeNo(waterPercentage / 2);
					int riverNo = localmap.decideRiverNo(waterPercentage / 2);
					for (int k = 0; k < lakeNo; k++)
						localmap.createLake();
					for (int k = 0; k < riverNo; k++)
						localmap.createRiver();
				} else if (River.isSelected()) {
					int riverNo = localmap.decideRiverNo(waterPercentage);
					for (int k = 0; k < riverNo; k++)
						localmap.createRiver();
				} else if (Lake.isSelected()) {
					int lakeNo = localmap.decideLakeNo(waterPercentage);
					for (int k = 0; k < lakeNo; k++)
						localmap.createLake();
				}
	
				cnv.repaint();
				Thread clean = new Thread(new Runnable() {

					@Override
					public void run() {
						System.gc();

					}
				});
				clean.start();
			}
		});

		// width
		JPanel WidthPanel = new JPanel(new GridLayout(1, 2));
		widthArea = new JSpinner();
		widthArea.setValue(50);
		WidthPanel.add(new JLabel("Width"));
		WidthPanel.add(widthArea);

		// height
		JPanel HeightPanel = new JPanel(new GridLayout(1, 2));
		HeightArea = new JSpinner();
		HeightPanel.add(new JLabel("Height"));
		HeightArea.setValue(50);
		HeightPanel.add(HeightArea);

		// checkbox
		JPanel checkBoxes = new JPanel(new GridLayout(1, 2));
		Lake = new JCheckBox();
		Lake.setText("Lake");
		River = new JCheckBox();
		River.setText("River");
		checkBoxes.add(Lake);
		checkBoxes.add(River);

		// ground
		JPanel groundPanel = new JPanel(new GridLayout(1, 2));
		groundArea = new JSpinner();
		groundPanel.add(new JLabel("%Ground"));
		groundArea.setValue(50);
		groundPanel.add(groundArea);

		// water inside
		JPanel waterPanel = new JPanel(new GridLayout(1, 2));
		waterArea = new JSpinner();
		waterPanel.add(new JLabel("%Water Inside"));
		waterArea.setValue(10);
		waterPanel.add(waterArea);

		left.add(WidthPanel);
		left.add(HeightPanel);
		left.add(checkBoxes);
		left.add(groundPanel);
		left.add(waterPanel);
		left.add(generate);
		aFrame.add(left);
		aFrame.add(cnv);
		aFrame.setVisible(true);
	}

}
