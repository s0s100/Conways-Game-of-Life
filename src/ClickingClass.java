import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickingClass implements MouseListener {

	static class Click {
		public int x;
		public int y;
		public boolean needed;

		public Click() {
			this.x = 0;
			this.y = 0;
			this.needed = false;
		}
	}

	Click click = new Click();

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("+");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("+");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("+");

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		this.click.needed = true;
		this.click.x = x;
		this.click.y = y;
		System.out.println("Click coordinates is : x =" + x + " y = " + y);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("+");

	}

	@Override
	public String toString() {
		String s = " x = " + this.click.x + " y = " + this.click.y + " and check = " + this.click.needed;
		return s;
	}

}
