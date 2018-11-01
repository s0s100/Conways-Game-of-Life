import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		int num = arg0.getKeyCode();
		if (num == 32) {
			if (MainField.pause) {
				MainField.setPause(false);
				System.out.println("Game continued");
			} else {
				MainField.setPause(true);
				System.out.println("Game paused");

			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
