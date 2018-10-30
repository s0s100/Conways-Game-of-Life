import javax.swing.JFrame;

public class MainField extends JFrame{
	private static final long serialVersionUID = 1L;
	public boolean [][] field = new boolean[172][100];
	ClickingClass control = new ClickingClass();
	
	public MainField() {
		super("Conway's Game of Life");
		this.setBounds(100,100,1820,1100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(control);
	}	
	
	public void GameStart() {
		do {
			
		}while(true);
	}

	public static void main(String[] args) {
		 MainField MainWindow = new MainField();
		 MainWindow.GameStart();
	}
}
