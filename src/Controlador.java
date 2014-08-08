import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Controlador extends JPanel implements Runnable, KeyListener  {
	private double xbol;
	private double ybol;
	private double xp1;
	private double yp1;
	private double xp2;
	private double yp2;
	private double speed = 6.0;
	private double vel = 5.0;
	private int player1 = 1;
	private int player2 = 1;
	private int bolitaX = 1;
	private int bolitaY = 1;
	private int countP1 = 0;
	private int countP2 = 0;
	Tenis tenis;

	protected Controlador(String nombreA, String nombreB) {
		super();
                tenis = new Tenis(nombreA,nombreB);
		this.xbol = 30;
		this.ybol = 0;
		this.xp1 = 565;
		this.yp1 = 180;
		this.xp2 = 10;
		this.yp2 = 180;
	}

	public void paint(Graphics gr) {
		super.paint(gr);
		Graphics2D g = (Graphics2D)gr;
		g.setColor(Color.WHITE);
		g.fill(new Ellipse2D.Double(this.xbol, this.ybol, 20, 20));
		g.setColor(Color.BLUE);
		g.fill(new Rectangle2D.Double(this.xp1, this.yp1, 15, 60));
		g.setColor(Color.ORANGE);
		g.fill(new Rectangle2D.Double(this.xp2, this.yp2, 15, 60));
                g.setColor(Color.RED);
		g.fill(new Rectangle2D.Double(295, 0, 5, 380));
               
		return;
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		switch (c) {
			case KeyEvent.VK_DOWN:
				this.player1 = 0;
				break;
			case KeyEvent.VK_UP:
				this.player1 = 1;
				break;
			case KeyEvent.VK_Z:
				this.player2 = 0;
				break;
			case KeyEvent.VK_A:
				this.player2 = 1;
				break;
		}

		super.repaint();
		return;
	}

	public void keyReleased(KeyEvent e) {
         
		return;
	}

	public void keyTyped(KeyEvent e) {
		return;
	}

	public void run() {
		while (!tenis.juegoTeminado()) {
			if (player1 == 0) {
				this.yp1 += this.speed;
				if (this.yp1 > 315) {
					this.yp1 = 315;
				}
			}
			if (player1 == 1) {
				this.yp1 -= this.speed;
				if (this.yp1 < 0) {
					this.yp1 = 0;
				}
			}

			if (player2 == 0) {
				this.yp2 += this.speed;
				if (this.yp2 > 315) {
					this.yp2 = 315;
				}
			}
			if (player2 == 1) {
				this.yp2 -= this.speed;
				if (this.yp2 < 0) {
					this.yp2 = 0;
				}
			}

			if (bolitaX == 0) {
				this.xbol -= this.vel;
				if (this.xbol < 0) {
					this.bolitaX = 1;
					this.xbol = 100; //
                                        tenis.darPunto(2);
				}
			}
			if (bolitaX == 1) {
				this.xbol += this.vel;
				if (this.xbol > 570) {
					this.bolitaX = 0;
					this.xbol = 360; //
                                        tenis.darPunto(1);
				}
			}
			if (bolitaY == 0) {
				this.ybol -= this.vel;
				if (this.ybol < 0) {
					this.bolitaY = 1;
				}
			}
			if (bolitaY == 1) {
				this.ybol += this.vel;
				if (this.ybol > 350) {
					this.bolitaY = 0;
				}
			}

			

			try {
				Thread.sleep(30);
			} catch (Exception e) {}

			double check1 = this.yp1-15;
			while (check1 <= this.yp1+60) {
				if (this.xp1 == this.xbol+20 && check1 == this.ybol) {
					this.countP1 += 1;
					if (player1 == 0) {
						this.bolitaX = 0;
						this.bolitaY = 1;
					}
					if (player1 == 1) {
						this.bolitaX = 0;
						this.bolitaY = 0;
					}
				}
				check1 += 1.0;
			}

			double check2 = this.yp2-15;
			while (check2 <= this.yp2+60) {
				if (this.xp2+15 == this.xbol && check2 == this.ybol) {
					this.countP2 += 1;
					if (player2 == 0) {
						this.bolitaX = 1;
						this.bolitaY = 1;
					}
					if (player2 == 1) {
						this.bolitaX = 1;
						this.bolitaY = 0;
					}
				}
				check2 += 1.0;
			}

			super.repaint();
		}
	}
  
}
