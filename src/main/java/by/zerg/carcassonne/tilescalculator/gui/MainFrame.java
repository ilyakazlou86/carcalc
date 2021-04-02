package by.zerg.carcassonne.tilescalculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import by.zerg.carcassonne.tilescalculator.helpers.Constants;

import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame implements ActionListener, MouseListener {
	JButton[] tileButtons;
	JLabel[] tileLables;
	JButton resetButton;
	JLabel totalLable;
	int tileCount;

	public MainFrame() {
		tileButtons = new JButton[Constants.TOTAL_TILES_COUNT];
		tileLables = new JLabel[Constants.TOTAL_TILES_COUNT];
		for (int i = 0; i < Constants.TOTAL_TILES_COUNT; i++) {
			tileButtons[i] = new JButton();
			tileButtons[i].setIcon(new ImageIcon(Constants.IMAGES_PATH
					+ (i + 1) + Constants.IMAGES_EXTENSION));
			tileButtons[i].setActionCommand(String.valueOf(i));
			// tileButtons[i].addActionListener(this);
			tileButtons[i].addMouseListener(this);
			tileLables[i] = new JLabel(String
					.valueOf(Constants.TILES_COUNTS[i]));
		}
		resetButton = new JButton("Reset");
		resetButton.setActionCommand("reset");
		// resetButton.addActionListener(this);
		resetButton.addMouseListener(this);
		totalLable = new JLabel("71");
	}

	public void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Carcassonne Calculator");
		setLayout(new GridLayout(4, 5));

		for (int i = 0; i < Constants.TOTAL_TILES_COUNT; i++) {
			JPanel newPanel = new JPanel(new BorderLayout());
			// newPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			newPanel.add(tileButtons[i]);
			JPanel labelPanel = new JPanel();
			labelPanel.add(tileLables[i]);
			newPanel.add(labelPanel, BorderLayout.SOUTH);
			add(newPanel);
		}

		JPanel newPanel = new JPanel(new BorderLayout());
		// newPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		newPanel.add(resetButton);
		JPanel labelPanel = new JPanel();
		labelPanel.add(totalLable);
		newPanel.add(labelPanel, BorderLayout.SOUTH);
		add(newPanel);

		AWTUtilities.setWindowOpacity(this, 1f);
		// pack();
		setSize(400, 440);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) throws Throwable {
		new MainFrame().init();
	}

	private void reset() {
		tileCount = 71;
		for (int i = 0; i < Constants.TOTAL_TILES_COUNT; i++) {
			tileButtons[i].setBackground(null);
			tileLables[i].setText(String.valueOf(Constants.TILES_COUNTS[i]));
		}
		totalLable.setText("71");
		// System.out.println(getWidth() + " " + getHeight());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("reset")) {
			reset();
		} else {
			int tileIndex = Integer.parseInt(actionCommand);
			if (!tileLables[tileIndex].getText().equals("0")) {
				tileLables[tileIndex].setText(String.valueOf(Integer
						.parseInt(tileLables[tileIndex].getText()) - 1));
				if (tileLables[tileIndex].getText().equals("0")) {
					// tileButtons[tileIndex].removeActionListener(this);
					tileButtons[tileIndex].setBackground(Color.red);
				}
				totalLable.setText(String.valueOf(Integer.parseInt(totalLable
						.getText()) - 1));
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		String actionCommand = button.getActionCommand();
		int inc = e.getButton() - 2;
		if (actionCommand.equals("reset")) {
			if (inc < 0) {
				reset();
			}
		} else {
			int tileIndex = Integer.parseInt(actionCommand);
			if (tileLables[tileIndex].getText().equals("0")) {
				if (inc > 0) {
					tileButtons[tileIndex].setBackground(null);
				} else {
					return;
				}
			} else if (tileLables[tileIndex].getText().equals(
					String.valueOf(Constants.TILES_COUNTS[tileIndex]))
					&& inc > 0) {
				return;
			}
			tileLables[tileIndex].setText(String.valueOf(Integer
					.parseInt(tileLables[tileIndex].getText())
					+ inc));
			if (tileLables[tileIndex].getText().equals("0") && inc < 0) {
				tileButtons[tileIndex].setBackground(Color.red);
			}
			totalLable.setText(String.valueOf(Integer.parseInt(totalLable
					.getText())
					+ inc));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
