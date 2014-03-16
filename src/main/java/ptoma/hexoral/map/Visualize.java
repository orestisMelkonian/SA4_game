package ptoma.hexoral.map;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Visualize extends Canvas {

	protected WorldMap map;
	protected int hexagonSize;

	public Visualize(WorldMap map,int hexSize) {
		setSize(map.sizeX * hexagonSize + hexagonSize, map.sizeY
				* hexagonSize);
		this.map = map;
		this.hexagonSize = hexSize;
	}

	public WorldMap getMap() {
		return this.map;
	}

	public int getHexSize() {
		return this.hexagonSize;
	}

	public void print() {
		Visualize cnv = new Visualize(this.map,this.hexagonSize);
		// this.map = map;
		JFrame aFrame = new JFrame("Border Layout");
		aFrame.setTitle("Island Generator");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		System.out.println(this.map.sizeX + " : " + this.map.sizeY);
		
		//TESTING
		((JFrame) aFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.pack();
		aFrame.setVisible(true);
		//-------DONE
		
		
		aFrame.setLayout(new GridLayout(1,2));  
		//aFrame.getContentPane().add(cnv, BorderLayout.WEST);
        //aFrame.add(cnv, BorderLayout.EAST);  
		
		
		//aFrame.setSize((this.map.sizeX + 2)* hexagonSize + 50, (this.map.sizeY + 2)* hexagonSize);
		//aFrame.setSize(1400,1000);
		aFrame.setSize(1000, 500);
		JButton jb=new JButton();
	    jb.setText("Generate");
	    aFrame.add(jb);
	    aFrame.add(cnv);
		aFrame.setVisible(true);
	}

	private Image internalPaint() {
		WorldMap map = this.getMap();
		int hexagonSize = this.getHexSize();
		BufferedImage sea = null;
		BufferedImage land = null;
		BufferedImage mountain = null;
		BufferedImage lake = null;
		BufferedImage output = new BufferedImage(map.sizeX*hexagonSize+hexagonSize/2, map.sizeY*hexagonSize-map.sizeY*hexagonSize/6, BufferedImage.TYPE_INT_RGB);
		try {
			File s = new File("src/main/resources/seastroke.png");
			File d = new File("src/main/resources/grassstroke.png");
			File m = new File("src/main/resources/mountainstroke.png");
			File l = new File("src/main/resources/lakestroke.png");
			sea = ImageIO.read(s);
			land = ImageIO.read(d);
			mountain = ImageIO.read(m);
			lake = ImageIO.read(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics g = output.getGraphics();
		for (int i = 0; i < map.sizeX; i++) {
			for (int j = 0; j < map.sizeY; j++) {
				int offsetX = (j % 2) * hexagonSize / 2;
				int offsetY = j * hexagonSize / 4;// j*4;//
				String cellType = map.matrix.get(i, j).getType();
				if (cellType == "SEA") {
					g.drawImage(sea, i * hexagonSize + offsetX, j
							* hexagonSize - offsetY, hexagonSize,
							hexagonSize, null);
				} else if (cellType == "LAND") {
					g.drawImage(land, i * hexagonSize + offsetX, j
							* hexagonSize - offsetY, hexagonSize,
							hexagonSize, null);
				} else if (cellType == "MOUNTAIN") {
					g.drawImage(mountain, i * hexagonSize + offsetX, j
							* hexagonSize - offsetY, hexagonSize,
							hexagonSize, null);
				} else if (cellType == "LAKE") {
					g.drawImage(lake, i * hexagonSize + offsetX, j
							* hexagonSize - offsetY, hexagonSize,
							hexagonSize, null);
				}

			}
		}
		return output;
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(this.internalPaint(), 0, 0, 500, 500, null);
	}
}