package by.zerg.carccalc.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import by.zerg.carccalc.helpers.Constants;

import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame implements ActionListener, MouseListener {
    private JButton[] tileButtons;
    private JLabel[] tileLables;
    private JButton resetButton;
    private JLabel totalLable;

    public MainFrame() {
        tileButtons = new JButton[Constants.TOTAL_TILES_COUNT];
        tileLables = new JLabel[Constants.TOTAL_TILES_COUNT];
        for (int i = 0; i < Constants.TOTAL_TILES_COUNT; i++) {
            tileButtons[i] = new JButton();
            tileButtons[i].setIcon(new ImageIcon(Constants.IMAGES_PATH + (i + 1) + Constants.IMAGES_EXTENSION));
            tileButtons[i].setActionCommand(String.valueOf(i));
            tileButtons[i].addMouseListener(this);
            tileLables[i] = new JLabel(String.valueOf(Constants.TILES_COUNTS[i]));
            tileLables[i].setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        }
        resetButton = new JButton("Reset");
        resetButton.setActionCommand("reset");
        // resetButton.addActionListener(this);
        resetButton.addMouseListener(this);
        totalLable = new JLabel("71");
        totalLable.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
    }

    private void init() {
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
        newPanel.add(resetButton);
        JPanel labelPanel = new JPanel();
        labelPanel.add(totalLable);
        newPanel.add(labelPanel, BorderLayout.SOUTH);
        add(newPanel);

        setSize(400, 468);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame().init();
    }

    private void reset() {
        for (int i = 0; i < Constants.TOTAL_TILES_COUNT; i++) {
            tileButtons[i].setBackground(null);
            tileLables[i].setText(String.valueOf(Constants.TILES_COUNTS[i]));
        }
        totalLable.setText("71");
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
