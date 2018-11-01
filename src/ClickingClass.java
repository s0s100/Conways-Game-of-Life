import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickingClass implements MouseListener {

	static boolean pressCheck = false;

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pressCheck = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		pressCheck = false;
	}

	public void setCheck(boolean b) {
		ClickingClass.pressCheck = b;
	}

}
