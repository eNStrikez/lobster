import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Lobster {
	private Image cursor;
	int sizeX = 160;
	int sizeY = 130;
	boolean alive = true;
	double health = 150;

	public Lobster(String fileName) {
		cursor = new Image(getClass().getResourceAsStream(fileName + ".png"));
	}

	public void drawLobster(GraphicsContext g, int x, int y) {
		if(alive == true){
			g.drawImage(cursor, x - (sizeX / 2), y - (sizeY / 2), sizeX, sizeY);
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
