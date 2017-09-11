import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tic_tac_toe extends JFrame implements ActionListener {

	private int boardsize = 3;
	private boolean CrossTurn = true;

	private enum GameStatus {
		XWon, OWon, Tie, Incomplete
	}

	private JButton[][] button = new JButton[this.boardsize][this.boardsize];

	public tic_tac_toe() {
		super.setTitle("Tic Tac Toe");
		super.setSize(800, 800);
		GridLayout layout = new GridLayout(boardsize, boardsize);
		super.setLayout(layout);

		Font f = new Font("Comic Sans MS", 1, 150);

		for (int i = 0; i < boardsize; i++) {
			for (int j = 0; j < boardsize; j++) {
				JButton rt = new JButton();

				// most imp
				rt.addActionListener(this);
				rt.setFont(f);
				button[i][j] = rt;
				super.add(rt);
			}
		}

		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JButton jb = (JButton) e.getSource();
		makemove(jb);

		GameStatus gs = this.getGameStatus(button);

		if (gs == GameStatus.Incomplete) {
			return;
		}

		this.declarewinner(gs);

		// restarting?
		int choice = JOptionPane.showConfirmDialog(this, "Restart?");
		if (choice == JOptionPane.YES_OPTION) {
			for (int row = 0; row < button.length; row++) {
				for (int col = 0; col < button[row].length; col++) {
					button[row][col].setText("");
				}
			}
			this.CrossTurn = true;

		}

		else {
			super.dispose();
		}

	}

	private void makemove(JButton cb) {

		String text = cb.getText();

		if (text.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid");
			return;
		}

		if (this.CrossTurn) {
			cb.setText("X");
		}

		else {

			cb.setText("O");
		}

		this.CrossTurn = !this.CrossTurn;

	}

	private GameStatus getGameStatus(JButton[][] button) {
		String text1 = "", text2 = "", text3 = "";
		// for row
		for (int i = 0; i < button.length; i++) {
			text1 = button[i][0].getText();
			text2 = button[i][1].getText();
			text3 = button[i][2].getText();
		

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			}

			else {
				return GameStatus.OWon;
			}}
		}

		// for column
		for (int i = 0; i < button[0].length; i++) {
			text1 = button[0][i].getText();
			text2 = button[1][i].getText();
			text3 = button[2][i].getText();
		

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			}

			else {
				return GameStatus.OWon;
			}}
		}

		// for diag 1

		text1 = button[0][0].getText();
		text2 = button[1][1].getText();
		text3 = button[2][2].getText();

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			}

			else {
				return GameStatus.OWon;
			}
		}

		// for diag 2

		text1 = button[0][2].getText();
		text2 = button[1][1].getText();
		text3 = button[2][0].getText();

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			}

			else {
				return GameStatus.OWon;
			}
		}

		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[i].length; j++) {

				if (button[i][j].getText().length() == 0) {
					return GameStatus.Incomplete;
				}
			}
		}

		return GameStatus.Tie;

	}

	private void declarewinner(GameStatus gs) {

		if (gs == GameStatus.XWon) {
			JOptionPane.showMessageDialog(this, "X Won");
		}

		else if (gs == GameStatus.OWon) {
			JOptionPane.showMessageDialog(this, "O Won");
		}

		else if (gs == GameStatus.Tie) {
			JOptionPane.showMessageDialog(this, "Tied");

		}

	}

}
