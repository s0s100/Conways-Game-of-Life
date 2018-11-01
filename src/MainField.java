import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainField extends JFrame {
	// Константа для соотношения размера
	public static final int c = 5;

	// Прорисовка поля
	public static class Painter extends JLabel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					if (fieldCopy[i][j]) {
						g.setColor(Color.RED);
						g.fill3DRect(i * c, j * c, c, c, true);
					}
				}
			}
			this.repaint();
		}
	}

	// Поток для снятия данных о мыши
	public static class ClickingThread extends Thread {
		JFrame frame;

		public ClickingThread(JFrame f) {
			this.frame = f;
		}

		public void run() {
			System.out.println("ClickThread started");
			do {
				if (ClickingClass.pressCheck) {
					try {
						int x = (int) frame.getMousePosition().getX();
						int y = (int) frame.getMousePosition().getY();
						int xc = Math.round((x - 8) / c);
						int yc = Math.round((y - 30) / c);
						try {
							fieldCopy[xc][yc] = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (NullPointerException e) {
						control.setCheck(false);
					}

				} else {
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} while (true);
		}
	}

	// Описание переменных
	public static boolean[][] field;
	public static boolean[][] fieldCopy;
	public static boolean pause;
	public static Painter painter = new Painter();
	public static ClickingClass control = new ClickingClass();
	public static KeyManager controlK = new KeyManager();
	private static final long serialVersionUID = 1L;

	public MainField() {
		super("Conway's Game of Life");
		this.setBounds(200, 200, 1720, 1000);
		this.getScreenSize();
		// MainField.setDefaultLookAndFeelDecorated(true);
		MainField.pause = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(control);
		this.addKeyListener(controlK);
	}

	public void GameStart() {
		// Прорисовка и обработка запросов
		this.add(painter);
		new ClickingThread(this).start();
		this.repaint();

		int count;
		fieldCopy = new boolean[field.length][field[0].length];
		for (int i = 0; i < field.length; i++)
			fieldCopy[i] = field[i].clone();

		do {
			if (pause) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (int i = 0; i < field.length; i++) {
					for (int j = 0; j < field[i].length; j++) {
						count = CheckNeighs(i, j);
						if (field[i][j]) {
							if (!(count > 1 && count < 4)) {
								fieldCopy[i][j] = false;
							}
						} else {
							if (count == 3) {
								fieldCopy[i][j] = true;
							}
						}
					}

				}
				for (int i = 0; i < field.length; i++)
					field[i] = fieldCopy[i].clone();

			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (true);

	}

	public int CheckNeighs(int x, int y) {
		int count = 0;
		if (x == 0 || x == field.length - 1 || y == 0 || y == field[0].length - 1) {
			return 0;
		} else {
			for (int i = x - 1; i < x + 2; i++) {
				for (int j = y - 1; j < y + 2; j++) {
					boolean b = !(i == x && j == y);
					if (field[i][j] && b) {
						count++;
					}
				}

			}
		}
		return count;
	}

	public void outConsole(boolean[][] b) {
		System.out.println("This is " + b.toString());
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				if (b[i][j]) {
					System.out.print("0");
				} else {
					System.out.print(".");
				}

			}
			System.out.println();
		}
	}
	
	public void getScreenSize() {
		int x = this.getWidth();
		int y = this.getHeight();
		x = Math.round(x / c);
		y = Math.round(y / c);
		field = new boolean[x][y];
	}

	public static void setPause(boolean b) {
		MainField.pause = b;
	}

	public JFrame getJFrame() {
		return this;
	}

	public static void main(String[] args) {
		MainField mainWindow = new MainField();
		mainWindow.GameStart();
	}
}
