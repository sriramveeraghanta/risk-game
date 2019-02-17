/**
 * 
 */
package model;

import java.util.Observable;

/**
 * @author edosh
 *
 */

@SuppressWarnings("deprecation")
public class GameModel extends Observable {

	private String title;
	private boolean visible;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 */
	public GameModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
		setChanged();
		notifyObservers("visible");
	}

}
