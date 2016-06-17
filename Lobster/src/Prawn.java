import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Prawn {
	private Image cursor;
	public int posX;
	public int posY;
	int size;

	public Prawn(int x, int y) {
		cursor = new ImageIcon("prawn.png").getImage();
		posX = x;
		posY = y;
		size = (int)(Math.random()*20 + 15);
	}

	public void drawPrawn(Graphics g) {
		g.drawImage(cursor, posX - 10, posY - 10, size, size, null);
	}

	public void move() {
		posY += (int) (Math.random() * 5);
		int m = (int) (Math.random() * 2);
		switch (m) {
		case 1:
			posX -= 2;
			break;
		case 0:
			posX += 2;
			break;
		}
	}
}
