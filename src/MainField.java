import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainField extends JFrame {
	// Константа для соотношения размера
	public static final int c = 10;

	// Прорисовка поля
	public static class Painter extends JLabel {
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			// super.paintComponents(g);
			// Graphics2D graph = (Graphics2D) g;

			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					// Проверка нажатия
					boolean bx = (i * c <= control.click.x - 8) && (i * c + c >= control.click.x - 8);
					boolean by = (j * c <= control.click.y - 30) && (j * c + 1 * c >= control.click.y - 30);
					boolean b = control.click.needed && bx && by;
					if (b) {
						System.out.println("Click accepted");
						control.click.needed = false;
						fieldCopy[i][j] = true;
					}
					// Прорисовка поля
					if (fieldCopy[i][j]) {
						g.setColor(Color.RED);
						g.fillRect(i * c, j * c, c, c);
					}
				}
			}
			this.repaint();
		}
	}
	//Описание переменных
	public static boolean[][] field;
	public static boolean[][] fieldCopy;
	public static boolean pause;
	private static final long serialVersionUID = 1L;
	public static Painter painter = new Painter();
	public static ClickingClass control = new ClickingClass();
	public static KeyManager controlK = new KeyManager();

	public MainField() {
		super("Conway's Game of Life");
		this.setBounds(200, 200, 1000, 1000);
		this.getScreenSize();
		// MainField.setDefaultLookAndFeelDecorated(true);
		MainField.pause = true;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(control);
		this.addKeyListener(controlK);
	}
	
	public static void setPause(boolean b) {
		MainField.pause = b;
	}

	public void GameStart() {

		this.add(painter);
		this.repaint();
		int count;
		
		fieldCopy = new boolean[field[0].length][field.length];
		for (int i = 0; i < field.length; i++)
			fieldCopy[i] = field[i].clone();

		do {
			//System.out.print("");//Заменить более адекватной функцией
			if (pause) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// System.out.println("Next generation:");

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
			}else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (true);

	}

	public int CheckNeighs(int x, int y) {
		int count = 0;
		if (x == 0 || x == field[0].length - 1 || y == 0 || y == field.length - 1) {
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
		field = new boolean[y][x];
	}

	public static void main(String[] args) {
		MainField mainWindow = new MainField();
		mainWindow.GameStart();
	}
}
