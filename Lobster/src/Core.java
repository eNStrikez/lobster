
import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


class Panel extends Canvas {

	Timeline timer;
	Timeline breathTimer;
	Timeline prawnTimer;
	Image sea;
	int sX;
	int sY;
	int x1 = 3 * Toolkit.getDefaultToolkit().getScreenSize().width / 4;
	int y1 = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
	int x2 = Toolkit.getDefaultToolkit().getScreenSize().width / 4;
	int y2 = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
	Lobster lobster = new Lobster("lobster");
	Lobster lobster2 = new Lobster("lobster2");
	ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
	ArrayList<Prawn> prawns = new ArrayList<Prawn>();

	boolean up1 = false;
	boolean up2 = false;
	boolean down1 = false;
	boolean down2 = false;
	boolean right1 = false;
	boolean right2 = false;
	boolean left1 = false;
	boolean left2 = false;

	public Panel(int x, int y) {
		sX = x;
		sY = y;

		initTimer();
	}

	private void initTimer() {
		timer = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int x = 0; x < bubbles.size(); x++) {
					bubbles.get(x).moveX();
					bubbles.get(x).moveY();
					if (bubbles.get(x).posY < -25) {
						bubbles.remove(x);
					}

				}

				for (int x = 0; x < prawns.size(); x++) {
					prawns.get(x).move();
					if (prawns.get(x).posY > sY) {
						prawns.remove(x);
					}
				}

				if (up1 == true && y1 - lobster.getSizeY() / 2 > 0) {
					y1 -= 2;
				}
				if (down1 == true && y1 + lobster.getSizeY() / 2 < sY) {
					y1 += 2;
				}
				if (right1 == true && x1 + lobster.getSizeX() / 2 < sX) {
					x1 += 2;
				}
				if (left1 == true && x1 - lobster.getSizeX() / 2 > 0) {
					x1 -= 2;
				}

				if (up2 == true && y2 - lobster.getSizeY() / 2 > 0) {
					y2 -= 2;
				}
				if (down2 == true && y2 + lobster.getSizeY() / 2 < sY) {
					y2 += 2;
				}
				if (right2 == true && x2 + lobster.getSizeX() / 2 < sX) {
					x2 += 2;
				}
				if (left2 == true && x2 - lobster.getSizeX() / 2 > 0) {
					x2 -= 2;
				}
			}
		}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
		breathTimer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bubbles.add(new Bubble(x1, y1));
				bubbles.add(new Bubble(x2, y2));
				lobster.shrink();
				lobster2.shrink();
			}
		}));
		breathTimer.setCycleCount(Timeline.INDEFINITE);
		breathTimer.play();
		prawnTimer = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				prawns.add(new Prawn((int) (Math.random() * sX), 0));
			}
		}));
		prawnTimer.setCycleCount(Timeline.INDEFINITE);
		prawnTimer.play();
	}

	public void eat() {
		for (int x = 0; x < prawns.size(); x++) {
			if (lobster.alive == true) {
				if (x1 > prawns.get(x).posX - (lobster.sizeX / 2) && x1 < prawns.get(x).posX + (lobster.sizeX / 2)
						&& y1 > prawns.get(x).posY - (lobster.sizeY / 2)
						&& y1 < prawns.get(x).posY + (lobster.sizeY / 2)) {
					prawns.remove(x);
					lobster.grow();
				}
			}
		}
		for (int x = 0; x < prawns.size(); x++) {
			if (lobster2.alive == true) {
				if (x2 > prawns.get(x).posX - (lobster2.sizeX / 2) && x2 < prawns.get(x).posX + (lobster2.sizeX / 2)
						&& y2 > prawns.get(x).posY - (lobster2.sizeY / 2)
						&& y2 < prawns.get(x).posY + (lobster2.sizeY / 2)) {
					prawns.remove(x);
					lobster2.grow();
				}
			}
		}
	}

	public void paint(GraphicsContext g) {
		 sea = new Image(getClass().getResourceAsStream("sea.png"));
		 g.drawImage(sea, 0, 0, sX, sY);
		 lobster.drawLobster(g, x1, y1);
		 lobster2.drawLobster(g, x2, y2);
		 eat();
		 for (int x = 0; x < bubbles.size(); x++) {
		 bubbles.get(x).draw(g);
		 }
		 for (int x = 0; x < prawns.size(); x++) {
		 prawns.get(x).drawPrawn(g);
		 }
		 g.setFont(new Font("Century Gothic", 150));

		 g.setFill(Color.DARKGRAY);
		 if (lobster.alive == false) {
		 breathTimer.stop();
		 prawnTimer.stop();
		 g.strokeText("LOBSTER 2 WINS", sX / 2 - 550, sY / 2);
		 } else if (lobster2.alive == false) {
		 breathTimer.stop();
		 prawnTimer.stop();
		 g.strokeText("LOBSTER 1 WINS", sX / 2 - 550, sY / 2);
		 }

		 g.setFill(Color.RED);
		 g.fillRect(sX / 2 - 655, 10, 600, 10);
		 g.fillRect(sX / 2 + 55, 10, 600, 10);
		 g.setFill(Color.GREEN);
		 g.fillRect(sX / 2 - (int) (lobster2.getHealth() * 4) - 55, 10, (int)
		 (lobster2.getHealth() * 4), 10);
		 g.fillRect(sX / 2 + 55, 10, (int) (lobster2.getHealth() * 4), 10);

	}

	public void keyPressed(KeyEvent e) {

		KeyCode key = e.getCode();

		if (key == javafx.scene.input.KeyCode.ESCAPE) {
			System.exit(0);
		}

		if (key == javafx.scene.input.KeyCode.RIGHT) {
			right1 = true;
		} else if (key == javafx.scene.input.KeyCode.LEFT) {
			left1 = true;
		} else if (key == javafx.scene.input.KeyCode.UP) {
			up1 = true;
		} else if (key == javafx.scene.input.KeyCode.DOWN) {
			down1 = true;
		}

		if (key == javafx.scene.input.KeyCode.D) {
			right2 = true;
		} else if (key == javafx.scene.input.KeyCode.A) {
			left2 = true;
		} else if (key == javafx.scene.input.KeyCode.W) {
			up2 = true;
		} else if (key == javafx.scene.input.KeyCode.S) {
			down2 = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		KeyCode key = e.getCode();

		if (key == javafx.scene.input.KeyCode.ESCAPE) {
			System.exit(0);
		}

		if (key == javafx.scene.input.KeyCode.RIGHT) {
			right1 = false;
		} else if (key == javafx.scene.input.KeyCode.LEFT) {
			left1 = false;
		} else if (key == javafx.scene.input.KeyCode.UP) {
			up1 = false;
		} else if (key == javafx.scene.input.KeyCode.DOWN) {
			down1 = false;
		}

		if (key == javafx.scene.input.KeyCode.D) {
			right2 = false;
		} else if (key == javafx.scene.input.KeyCode.A) {
			left2 = false;
		} else if (key == javafx.scene.input.KeyCode.W) {
			up2 = false;
		} else if (key == javafx.scene.input.KeyCode.S) {
			down2 = false;
		}
	}

}

public class Core extends Application {
	Panel p;

	@Override
	public void start(Stage primaryStage) {
		try {
			int sX = Toolkit.getDefaultToolkit().getScreenSize().width;
			int sY = Toolkit.getDefaultToolkit().getScreenSize().height;

			p = new Panel(sX, sY);
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);

			GraphicsContext g = p.getGraphicsContext2D();
			drawShapes(g);

			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					p.keyPressed(event);
				}
			});

			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					p.keyReleased(event);
				}
			});

			root.getChildren().add(p);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawShapes(GraphicsContext g) {
		p.paint(g);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
