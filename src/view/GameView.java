package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.GameModel;

@SuppressWarnings("deprecation")
public class GameView implements Observer {

	private JFrame mainFrame;
	private JPanel topPanel;
	private JPanel leftPanel;

	public void setToptPanel(JPanel topPanel, GridBagConstraints gbc) {
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBorder(this.blackLine);
		topPanel.setBackground(Color.RED);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx = 0.2;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(1, 1, 1, 1);
		this.mainFrame.add(topPanel, gbc);
	}

	public void setLeftPanel(JPanel leftPanel, GridBagConstraints gbc) {
		leftPanel.setSize(100, 600);
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftPanel.setBorder(this.blackLine);
		leftPanel.setBackground(Color.GREEN);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(1, 1, 1, 1);
		this.mainFrame.add(leftPanel, gbc);
	}

	/**
	 * set the blackLine
	 */
	public void setBlackLine() {
		this.blackLine = BorderFactory.createLineBorder(Color.black);
	}

	private Border blackLine;

	public GameView(GameModel model) {
		this._initialize(model);
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return mainFrame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.mainFrame = frame;
		this.mainFrame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof GameModel) {
			GameModel model = (GameModel) o;
			this.mainFrame.setVisible(model.isVisible());
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
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		this.setBlackLine();
		topPanel = new JPanel();
		leftPanel = new JPanel();
		this.setToptPanel(topPanel, gbc);
		this.setLeftPanel(leftPanel, gbc);

//		JLabel label = new JLabel("RISK");
//
//		topPanel.add(label);

		mainFrame.pack();
		System.out.println("JPanel Size - " + topPanel.getSize());

		String current;
		try {
			current = new java.io.File(".").getCanonicalPath();

			System.out.println("Current dir:" + current);
			String currentDir = System.getProperty("user.dir");
			System.out.println("Current dir using System:" + currentDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
