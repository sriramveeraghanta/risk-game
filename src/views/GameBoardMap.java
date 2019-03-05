package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.GameConstant;

class GameBoardMap extends JPanel {

	public GameBoardMap() {
		this.buildPanel();
	}

	private void buildPanel(){

		BufferedImage panelMapImage = null;
		try {
			panelMapImage = ImageIO.read(new File(GameConstant.MAP_IMAGE_FILE_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel mapImageLabel = new JLabel(new ImageIcon(panelMapImage));
		this.add(mapImageLabel);

	}

	public JPanel getPanel() {
		return this;
	}
}