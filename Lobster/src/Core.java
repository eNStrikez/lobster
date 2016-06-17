import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	private Timer timer;
	private Timer breathTimer;
	private Timer prawnTimer;
	private Image sea;
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
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		initTimer();
	}

	private void initTimer() {
		timer = new Timer(1, this);
		timer.start();
		breathTimer = new Timer(500, this);
		breathTimer.start();
		prawnTimer = new Timer(250, this);
		prawnTimer.start();
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		sea = new ImageIcon("Sea.jpg").getImage();
		g.drawImage(sea, 0, 0, sX, sY, null);
		lobster.drawLobster(g, x1, y1);
		lobster2.drawLobster(g, x2, y2);
		eat();
		for (int x = 0; x < bubbles.size(); x++) {
			bubbles.get(x).draw(g);
		}
		for (int x = 0; x < prawns.size(); x++) {
			prawns.get(x).drawPrawn(g);
		}
		g.setFont(new Font("Century Gothic", Font.PLAIN, 150));

		g.setColor(Color.DARK_GRAY);
		if (lobster.alive == false) {
			breathTimer.stop();
			prawnTimer.stop();
			g.drawString("LOBSTER 2 WINS", sX / 2 - 550, sY / 2);
		} else if (lobster2.alive == false) {
			breathTimer.stop();
			prawnTimer.stop();
			g.drawString("LOBSTER 1 WINS", sX / 2 - 550, sY / 2);
		}

		g.setColor(Color.red);
		g.fillRect(sX / 2 - 655, 10, 600, 10);
		g.fillRect(sX / 2 + 55, 10, 600, 10);
		g.setColor(Color.green);
		g.fillRect(sX / 2 - (int) (lobster2.getHealth() * 4) - 55, 10, (int) (lobster2.getHealth() * 4), 10);
		g.fillRect(sX / 2 + 55, 10, (int) (lobster2.getHealth() * 4), 10);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if (key == KeyEvent.VK_RIGHT) {
			right1 = true;
		} else if (key == KeyEvent.VK_LEFT) {
			left1 = true;
		} else if (key == KeyEvent.VK_UP) {
			up1 = true;
		} else if (key == KeyEvent.VK_DOWN) {
			down1 = true;
		}

		if (key == KeyEvent.VK_D) {
			right2 = true;
		} else if (key == KeyEvent.VK_A) {
			left2 = true;
		} else if (key == KeyEvent.VK_W) {
			up2 = true;
		} else if (key == KeyEvent.VK_S) {
			down2 = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			right1 = false;
		} else if (key == KeyEvent.VK_LEFT) {
			left1 = false;
		} else if (key == KeyEvent.VK_UP) {
			up1 = false;
		} else if (key == KeyEvent.VK_DOWN) {
			down1 = false;
		}

		if (key == KeyEvent.VK_D) {
			right2 = false;
		} else if (key == KeyEvent.VK_A) {
			left2 = false;
		} else if (key == KeyEvent.VK_W) {
			up2 = false;
		} else if (key == KeyEvent.VK_S) {
			down2 = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		repaint();
		if (e.getSource() == breathTimer) {
			bubbles.add(new Bubble(x1, y1));
			bubbles.add(new Bubble(x2, y2));
			lobster.shrink();
			lobster2.shrink();
		}
		if (e.getSource() == prawnTimer) {
			prawns.add(new Prawn((int) (Math.random() * sX), 0));
		}

		if (up1 == true && y1 - lobster.getSizeY() / 2 > 0) {
			y1 -= 5;
		}
		if (down1 == true && y1 + lobster.getSizeY() / 2 < sY) {
			y1 += 5;
		}
		if (right1 == true && x1 + lobster.getSizeX() / 2 < sX) {
			x1 += 5;
		}
		if (left1 == true && x1 - lobster.getSizeX() / 2 > 0) {
			x1 -= 5;
		}

		if (up2 == true && y2 - lobster.getSizeY() / 2 > 0) {
			y2 -= 5;
		}
		if (down2 == true && y2 + lobster.getSizeY() / 2 < sY) {
			y2 += 5;
		}
		if (right2 == true && x2 + lobster.getSizeX() / 2 < sX) {
			x2 += 5;
		}
		if (left2 == true && x2 - lobster.getSizeX() / 2 > 0) {
			x2 -= 5;
		}

	}

}

@SuppressWarnings("serial")
public class Core extends JFrame {
	public Core() {
		int sY = Toolkit.getDefaultToolkit().getScreenSize().height;
		int sX = Toolkit.getDefaultToolkit().getScreenSize().width;
		setCursor(this.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), ""));
		Panel panel = new Panel(sX, sY);
		setTitle("LOBSTER");
		add(panel);
		setSize(sX, sY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
	}

	public static void main(String[] args) {
		Core c = new Core();
		c.setVisible(true);
	}
}
