import java.awt.EventQueue;

import controllers.GameController;

public class Game {
	/*
	 * This is the main method.
	 * */
	public static void main(String args[]) {
		// Logs
		System.out.println("Start Game");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController controller = new GameController();
					controller.showHomeView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("end");
	}
}

