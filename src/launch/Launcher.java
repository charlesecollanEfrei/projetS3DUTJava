package launch;

import frame.MainFrame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class launch the agenda, by creating the MainFrame object
 * @author Charles Ecollan, Quentin Bresson, Hubert Mouginot
 */
public final class Launcher {

	
	public static void main(String[] args) {

		//Permet d'eviter diverses erreurs à la compilation, notamment sur le constructeur de Month
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame.instance();
			}
		});
	}

}
