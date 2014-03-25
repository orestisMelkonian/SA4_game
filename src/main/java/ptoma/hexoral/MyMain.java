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
import javax.swing.SpinnerNumberModel;

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

			public void actionPerformed(ActionEvent e) {
								
				localmap.setSizeY((Integer) widthArea.getValue());
				localmap.setSizeX((Integer) HeightArea.getValue());
				localmap.erase();
				String biome = "test.bio";
				
				gen = new MapGenerator(localmap, biome);
				
				gen.applyParameters((Integer) groundArea.getValue(),
						(Integer) waterArea.getValue(), Lake.isSelected(),
						River.isSelected());
				//gen.printPercentage();
				cnv.repaint();
				Thread clean = new Thread(new Runnable() {

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
		groundArea = new JSpinner(new SpinnerNumberModel(100, 20, 100, 1));
		groundPanel.add(new JLabel("%Ground"));
		groundPanel.add(groundArea);

		// water inside
		JPanel waterPanel = new JPanel(new GridLayout(1, 2));
		if ((River.isSelected()) && !(Lake.isSelected()))
			waterArea = new JSpinner(new SpinnerNumberModel(30, 0, 30, 1));
		else if ((Lake.isSelected()) && !(River.isSelected()))
			waterArea = new JSpinner(new SpinnerNumberModel(70, 0, 70, 1));
		else
			waterArea = new JSpinner(new SpinnerNumberModel(50, 0, 50, 1));
		waterPanel.add(new JLabel("%Water Inside"));
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
