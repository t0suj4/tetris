package tw.ntustgrp12.tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import tw.ntustgrp12.tetris.block.RandomBlockGenerator;
import tw.ntustgrp12.tetris.board.*;

public class Tetris {
	final static int BOARD_WIDTH = 10;
	final static int BOARD_HEIGHT = 22;

	public static void main(String[] args) {
		JFrame frame;

		frame = new JFrame("Tetris Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GameBoard gameBoard = makeBoard();
		GameLogic gameLogic = new GameLogic(new RandomBlockGenerator(), gameBoard);
		KeyAdapter controller = new GameController(gameLogic);
		GameView gameView = new GameView();
		gameBoard.addObserver(gameView);
		// Refresh gameView, it now has data!
		gameBoard.notifyObservers();

		// gameView needs to stay focused!
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {
				gameView.requestFocus();
			}
		});

		gameView.addKeyListener(controller);
		frame.add(gameView);
		frame.pack();

		// Run the game!
		frame.setVisible(true);
	}

	private static GameBoard makeBoard()
	{
		PosGrid block = new PosGrid(new Grid(4, 4));
		Grid gameGrid = new Grid(BOARD_WIDTH, BOARD_HEIGHT);
		return new GameBoard(gameGrid, block);
	}
}
