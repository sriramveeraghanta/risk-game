package view;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameView {
	JFrame frame;
	public GameView() {
		// Creating instance for JFrame
		frame = new JFrame("Initial Screen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Creating a button
		JButton newGameButton = new JButton("New Game");
		JButton quitGameButton = new JButton("Quit"); 
		newGameButton.setBounds(200, 150, 90, 50);
		
        frame.add(newGameButton);
        frame.setSize(500, 600); 
        frame.setLayout(null); 
         
	}
}
