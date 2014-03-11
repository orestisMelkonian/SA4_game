package ptoma.hexoral.map;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Visualize extends Canvas {

	protected Map map;

	public Visualize(Map map) {
		setSize(200, 200);
		this.map = map;
	}

	public Map getMap() {
		return this.map;
	}

	public void show() {
		Visualize cnv = new Visualize(this.map);
		// this.map = map;
		Frame aFrame = new Frame();
		aFrame.setTitle("Map visual");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		System.out.println(this.map.sizeX + " : " + this.map.sizeY);
		aFrame.setSize(this.map.sizeX, this.map.sizeY);
		aFrame.add(cnv);

		aFrame.setVisible(true);
	}

	private void internalPaint(Graphics g) {
		Map map = this.getMap();
		for (int i = 0; i < map.sizeX; i++) {
			for (int j = 0; j < map.sizeY; j++) {
				if (map.matrix[i][j].getType() == "SEA") {
					g.setColor(Color.blue);
				} else {
					g.setColor(Color.green);
				}
				g.drawRect(i, j, 0, 0);
			}
		}
	}

	public void paint(Graphics g) {
		this.internalPaint(g);
	}
}