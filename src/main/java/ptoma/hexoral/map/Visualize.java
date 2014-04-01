package ptoma.hexoral.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import ptoma.hexoral.building.Building;
import ptoma.hexoral.exception.InvalidPointException;
import ptoma.hexoral.game.Game;
import ptoma.hexoral.units.Unit;
import ptoma.hexoral.user.Player;

@SuppressWarnings("serial")
public class Visualize extends Canvas {

	static JFrame aFrame;
	JSpinner widthArea;
	protected WorldMap map;
	protected Game game;
	protected int hexagonSize;
	JSpinner HeightArea;
	JSpinner groundArea;

	/**
	 * @wbp.parser.entryPoint
	 */
	public Visualize(int hexSize, Game m) {
		// setSize(map.sizeX * hexagonSize + hexagonSize, map.sizeY *
		// hexagonSize);
		this.game = m;
		this.map = m.island;
		this.hexagonSize = hexSize;

		this.print();
	}

	public WorldMap getMap() {
		return this.map;
	}

	public int getHexSize() {
		return this.hexagonSize;
	}

	public void print() {

	}

	private Image internalPaint() {
		WorldMap map = this.getMap();
		int hexagonSize = this.getHexSize();
		BufferedImage resource = null;
		BufferedImage sea = null;
		BufferedImage land = null;
		BufferedImage mountain = null;
		BufferedImage lake = null;
		BufferedImage soldier = null;
		BufferedImage building = null;
		BufferedImage HQ = null;
		BufferedImage mine = null;
		BufferedImage output = new BufferedImage(map.sizeX * hexagonSize
				+ hexagonSize / 2, map.sizeY * hexagonSize - map.sizeY
				* hexagonSize / 6, BufferedImage.TYPE_INT_RGB);
		try {
			resource = ImageIO.read(new File("src/main/resources/resourcestroke.png"));
			sea = ImageIO.read(new File("src/main/resources/seastroke.png"));
			land = ImageIO.read(new File("src/main/resources/grassstroke.png"));
			mountain = ImageIO.read(new File("src/main/resources/mountainstroke.png"));
			lake = ImageIO.read(new File("src/main/resources/lakestroke.png"));
			soldier = ImageIO.read(new File("src/main/resources/soldier.png"));
			mine = ImageIO.read(new File("src/main/resources/resource.png"));
			HQ = ImageIO.read(new File("src/main/resources/HQ.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics g = output.getGraphics();
		for (int i = 0; i < map.sizeX; i++) {
			for (int j = 0; j < map.sizeY; j++) {
				int offsetX = (j % 2) * hexagonSize / 2;
				int offsetY = j * hexagonSize / 4;// j*4;//
				String cellType = null;
				try {
					cellType = map.getHexagon(i, j).getType();
				} catch (InvalidPointException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(1);
				}
				try {
					if (map.getHexagon(i, j).isResource()){
						g.drawImage(resource, i * hexagonSize + offsetX, j * hexagonSize
								- offsetY, hexagonSize, hexagonSize, null);
					}else if (cellType == "SEA") {
						g.drawImage(sea, i * hexagonSize + offsetX, j * hexagonSize
								- offsetY, hexagonSize, hexagonSize, null);
					} else if (cellType == "LAND") {
						g.drawImage(land, i * hexagonSize + offsetX, j
								* hexagonSize - offsetY, hexagonSize, hexagonSize,
								null);
					} else if (cellType == "MOUNTAIN") {
						g.drawImage(mountain, i * hexagonSize + offsetX, j
								* hexagonSize - offsetY, hexagonSize, hexagonSize,
								null);
					} else if (cellType == "LAKE") {
						g.drawImage(lake, i * hexagonSize + offsetX, j
								* hexagonSize - offsetY, hexagonSize, hexagonSize,
								null);
					}
				} catch (InvalidPointException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		//Add Soldiers
		for(Player p : game.getAllPlayers()) {
			for(Unit u : game.getPlayerUnits(p)) {
				int offsetX = (u.getPosition().y % 2) * hexagonSize / 2;
				int offsetY = u.getPosition().y * hexagonSize / 4;// j*4;//
				g.drawImage(soldier, u.getPosition().x * hexagonSize + offsetX, u.getPosition().y
						* hexagonSize - offsetY, hexagonSize, hexagonSize,
						null);
			}
			for(Building build : game.getPlayerBuildings(p)) {
				int offsetX = (build.getPosition().x % 2) * hexagonSize / 2;
				int offsetY = build.getPosition().x * hexagonSize / 4;
				if(build.getClass().getName().contains("HQ")) {
					building = HQ;
				} else {
					building = mine;
				}
				g.drawImage(building, build.getPosition().x * hexagonSize + offsetX, build.getPosition().y
						* hexagonSize - offsetY, hexagonSize, hexagonSize,
						null);
			}
			
		}
		return output;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.internalPaint(), 0, 0, 500, 500, null);
	}
}