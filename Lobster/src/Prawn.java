import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Prawn {
	private Image cursor;
	public int posX;
	public int posY;
	int size;

	public Prawn(int x, int y) {
		cursor = new Image(getClass().getResourceAsStream("prawn.png"));
		posX = x;
		posY = y;
		size = (int)(Math.random()*20 + 15);
	}

	public void drawPrawn(GraphicsContext g) {
		g.drawImage(cursor, posX - 10, posY - 10, size, size);
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
