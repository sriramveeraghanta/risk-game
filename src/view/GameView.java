package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.GameModel;

@SuppressWarnings("deprecation")
public class GameView implements Observer {

	private JFrame frame;

	public GameView(GameModel model) {
		this._initialize(model);
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
		this.frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof GameModel) {
			GameModel model = (GameModel) o;
			this.frame.setVisible(model.isVisible());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param model
	 */
	private void _initialize(GameModel model) {
		// Creating instance for JFrame
		setFrame(new JFrame(model.getTitle()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}
