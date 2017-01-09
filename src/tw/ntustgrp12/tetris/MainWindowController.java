package tw.ntustgrp12.tetris;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;


public class MainWindowController {

	private final JFrame frame;
	
	public MainWindowController()
	{
		frame = new JFrame("Tetris Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameBoard gameBoard = GameFactory.newBoard();
		GameLogic gameLogic = GameFactory.newLogic(gameBoard);
		KeyAdapter controller = new GameController(gameLogic);
		GameView gameView = new GameView();
		gameBoard.addObserver(gameView);
		gameBoard.notifyObservers();
		frame.addKeyListener(controller);
		frame.add(gameView);
		frame.pack();
	}
	
	public void run() {
		frame.setVisible(true);
	}
}
