package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.GameConstant;

/**
 * 
 * Adding the map image on the panel
 *
 */
class GameBoardMap extends JPanel {

	public GameBoardMap() {
		this.buildPanel();
	}

	private void buildPanel(){
		
		//Loading the map World.bmp file and adding it
		BufferedImage panelMapImage = null;
		try {
			panelMapImage = ImageIO.read(new File(GameConstant.MAP_IMAGE_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel mapImageLabel = new JLabel(new ImageIcon(panelMapImage));
		this.add(mapImageLabel);
	}

	public JPanel getPanel() {
		return this;
	}
}