import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JFrame;

public class MainField extends JFrame {
	// Константа для соотношения размера
	public static final int c = 250;
	// Игровое поле
	public static boolean[][] field;
	private static final long serialVersionUID = 1L;

	public ClickingClass control = new ClickingClass();
	public Graphics2D g;

	public MainField() {
		super("Conway's Game of Life");
		// Определяем размеры окна и массива
		this.setBounds(200, 200, 1700, 950);
		this.getScreenSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(control);
	}

	public void GameStart() {
		do {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					// Проверка нажатия
					boolean bx = (i * c <= control.click.x) && (i * c + c >= control.click.x);
					boolean by = (j * c <= control.click.y) && (j * c + c >= control.click.y);
					boolean b = control.click.needed && bx && by;
					if (b) {
						System.out.println("Check completed");
						control.click.needed = false;
						field[i][j] = true;
					}
					// g.fill(new Rectangle(i * c, j * c, i * c + c, j * c + c));
					// Прорисовка поля
					if (field[i][j]) {
						System.out.println("Drowing thing");
						g.setColor(Color.BLACK);
						g.fill(new Rectangle(i * c, j * c, i * c + c, j * c + c));
					}

				}
			}
			System.out.println("Main info: "+control.toString());
		} while (true);
	}

	public void getScreenSize() {
		int x = this.getWidth();
		int y = this.getHeight();
		x = Math.round(x / c);
		y = Math.round(y / c);
		field = new boolean[x][y];
	}

	public static void main(String[] args) {
		MainField mainWindow = new MainField();
		mainWindow.GameStart();
	}
}
