package ptoma.hexoral.map;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Visualize extends Canvas {

	protected Map map;
	protected int hexagonSize;

	public Visualize(Map map,int hexSize) {
		setSize(map.sizeX * hexagonSize + hexagonSize, map.sizeY
				* hexagonSize);
		this.map = map;
		this.hexagonSize = hexSize;
	}

	public Map getMap() {
		return this.map;
	}

	public int getHexSize() {
		return this.hexagonSize;
	}

	public void print() {
		Visualize cnv = new Visualize(this.map,this.hexagonSize);
		// this.map = map;
		Frame aFrame = new Frame();
		aFrame.setTitle("Map visual");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		System.out.println(this.map.sizeX + " : " + this.map.sizeY);
		aFrame.setSize((this.map.sizeX + 2)* hexagonSize, (this.map.sizeY + 2)
				* hexagonSize);
		aFrame.add(cnv);

		aFrame.setVisible(true);
	}

	private void internalPaint(Graphics g) {
		Map map = this.getMap();
		int hexagonSize = this.getHexSize();
		BufferedImage sea = null;
		BufferedImage land = null;
		BufferedImage mountain = null;
		try {
			File s = new File("src/main/resources/seastroke.png");
			File d = new File("src/main/resources/grassstroke.png");
			File m = new File("src/main/resources/mountainstroke.png");
			sea = ImageIO.read(s);
			land = ImageIO.read(d);
			mountain = ImageIO.read(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < map.sizeX; i++) {
			for (int j = 0; j < map.sizeY; j++) {
				int offsetX = (j % 2) * hexagonSize / 2;
				int offsetY = j * hexagonSize / 4;// j*4;//
				String cellType = map.matrix[i][j].getType();
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
				}

			}
		}
	}
	@Override
	public void paint(Graphics g) {
		this.internalPaint(g);
	}
}