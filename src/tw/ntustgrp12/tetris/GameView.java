package tw.ntustgrp12.tetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class GameView extends Canvas {
	
	private GameBoardObserver boardObserver;
	
	public GameView()
	{
		setSize(320,240);
	}
	
	public void setBoardObserver(GameBoardObserver boardObserver)
	{
		this.boardObserver = boardObserver;
	}
		
	public void paint(Graphics g)
	{
		super.paint(g);
		GameBoard board = boardObserver.getBoard();
		if (board != null) {
			paintGameGrid(g, board.getGrid(), 0, 0);
		
			PosGrid block = board.getBlock();
			paintBlockGrid(g, block.getGrid(), block.x, block.y);
		} else {
		}
	}
	
	private void paintGameGrid(Graphics g, Grid grid, int x, int y)
	{
		g.setColor(Color.BLACK);
		paintGrid(g, grid, x, y);
	}
	
	private void paintBlockGrid(Graphics g, Grid grid, int x, int y)
	{
		int[][] data = grid.getData();
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (data[i][j] != 0) {
					g.setColor(Color.GREEN);
					g.drawRect(x*10 + j*10, y*10 + i*10, 10, 10);
				} else {
					g.setColor(Color.YELLOW);
					g.drawRect(x*10 + j*10, y*10 + i*10, 10, 10);
				}
			}
		}
	}
	
	
	private void paintGrid(Graphics g, Grid grid, int x, int y)
	{
		int[][] data = grid.getData();
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (data[i][j] != 0) {
					g.setColor(Color.BLACK);
					g.drawRect(x*10 + j*10, y*10 + i*10, 10, 10);
				} else {
					g.setColor(Color.WHITE);
					g.drawRect(x*10 + j*10, y*10 + i*10, 10, 10);
				}
			}
		}
	}

}
