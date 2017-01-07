package tw.ntustgrp12.tetris;

import java.awt.Canvas;
import java.util.Observable;
import java.util.Observer;

public class GameBoardObserver implements Observer {
	
	private final Canvas canvas;
	private GameBoard board;
	
	public GameBoardObserver(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public GameBoard getBoard()
	{
		return board;
	}
	
	@Override
	public void update(Observable board, Object arg1) {
		this.board = (GameBoard) board;
		canvas.repaint();
	}

}
