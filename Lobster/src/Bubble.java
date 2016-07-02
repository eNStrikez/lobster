import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bubble {

	int posX;
	int posY;
	int rand = (int)(Math.random()*50);

	public Bubble(int x, int y) {
		posX = x;
		posY = y;
	}

	public void draw(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillOval(posX, posY, rand, rand);
		g.setFill(Color.web("0,0,0", 100));
		g.fillOval(posX, posY, rand, rand);
	}

	public void moveX() {
		int m = (int) (Math.random() * 5);
		switch (m) {
		case 1:
			posX -= 2;
			break;
		case 0:
			posX += 2;
			break;
		default:
			break;
		}
	}

	public void moveY(){
		posY -= 1;
	}

}
