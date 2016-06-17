import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Lobster {
	private Image cursor;
	int sizeX = 160;
	int sizeY = 130;
	boolean alive = true;
	double health = 150;

	public Lobster(String fileName) {
		cursor = new ImageIcon(fileName + ".png").getImage();
	}

	public void drawLobster(Graphics g, int x, int y) {
		if(alive == true){
			g.drawImage(cursor, x - (sizeX / 2), y - (sizeY / 2), sizeX, sizeY,
				null);
		}
	}

	public double getHealth() {
		return health;
	}

	public void grow() {
		sizeX += 16 / 10;
		sizeY += 13 / 10;
		if (health < 150) {
			health += 1;
		}
	}

	public void shrink() {
		if (health < 0) {
			alive = false;
		}
		sizeX -= 16 / 10;
		sizeY -= 13 / 10;
		health -= 2;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;	
	}

}
