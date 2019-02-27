package launcher;

import java.awt.EventQueue;

import controller.GameController;

/**
 * This class creating the game objects and binds game models with game views by
 * interacting with the game GameController.
 * 
 * @author Sriram Veeraghanta
 *
 */
public class Launcher {
	public static void main(String args[]) {
		System.out.println("start");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController controller = new GameController();
					controller.showView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("end");
	}
}
