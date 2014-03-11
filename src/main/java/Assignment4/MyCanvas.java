
package Assignment4;

import java.awt.*;
import java.lang.Object;
import java.awt.Color;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class MyCanvas extends Canvas {
	
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    //private static final Random random = new Random();
    //public Noise noise = new Noise(null, 1.0f, WIDTH, HEIGHT);
    //public PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator();
    public PerlinNoise n = new PerlinNoise((long) (Math.random() * 1000), 80);
    
    public MyCanvas(){
    	
    	//noise.initialise();
    	
    }
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
            	//float noise = perlinNoise.noise2((float) x,(float) y); 
            	double noise = n.getNoiseLevelAt(x, y);
            	//System.out.println(noise);
                g.setColor(randomColor(noise));
                g.drawLine(x, y, x, y);
            }
        }
    }

    private Color randomColor(double noise) {
    	Color colors[] = {Color.BLUE, Color.YELLOW, Color.GREEN}; 
        if(noise <= 0.30){
        	return colors[0];
        } else if(noise > 0.30 && noise < 0.6666666666){
        	return colors[1];
        } else{
        	return colors[2];
        }
    	
    }

    public static void main(String[] args) {
    	MyCanvas can = new MyCanvas();
        JFrame frame = new JFrame();

        frame.setSize(WIDTH, HEIGHT);
        frame.add(can);

        frame.setVisible(true);
    }
}
