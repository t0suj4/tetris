package tw.ntustgrp12.tetris;

import java.util.Timer;
import java.util.TimerTask;

import tw.ntustgrp12.tetris.block.BlockGenerator;
import tw.ntustgrp12.tetris.board.*;
import utils.ParticleCollider;

public class GameLogic {
	private final BlockGenerator generator;
	private Timer timer;
	private GameBoard board;
	private boolean gameOver;
	
	private final int START_Y = 0;
	
	public GameLogic(BlockGenerator generator, GameBoard board)
	{
		this.board = board;
		this.generator = generator;
		reset();
	}
	
	public synchronized void moveLeft()
	{
		if (gameOver)
			return;

		if(move(-1, 0))
			update();
	}
	
	public synchronized void moveRight()
	{
		if (gameOver)
			return;

		if (move(1, 0))
			update();
	}
	
	public synchronized void moveDown()
	{
		if (gameOver)
			return;

		// Hit something, attach the block
		if (!move(0, 1))
			fixBlock();
		update();
	}
	
	public synchronized void rotate()
	{
		if (gameOver)
			return;

		PosGrid block = board.getBlock();
		Grid rot = ParticleCollider.getRotatedGrid(block.getGrid());
		// Cannot rotate into objects
		if (ParticleCollider.collide(board.getGrid(), rot,
									 block.x, block.y))
			return;
		block.setGrid(rot);
		update();
	}
	
	public synchronized void land()
	{
		if (gameOver)
			return;
		// Move block down by up to one board height
		for (int i = 0; i < board.getGrid().getHeight() &&
				 move(0, 1); i++);
		fixBlock();
		update();
	}

	private boolean move(int x, int y)
	{
		PosGrid block = board.getBlock();

		if (ParticleCollider.collide(
				board.getGrid(),
				block.getGrid(),
				block.x+x, block.y + y))
			return false;

		block.x += x;
		block.y += y;
		return true;
	}
	
	private void fixBlock()
	{
		PosGrid block = board.getBlock();

		ParticleCollider.mergeGrids(
				board.getGrid(),
				block.getGrid(),
				block.x, block.y);

		int addScore =
		ParticleCollider.clearFullRows(
				board.getGrid(),
				block.y,
				block.y + block.getGrid().getHeight() -1);

		board.setScore(addScore + board.getScore());

		regenBlock();
	}

	private void regenBlock()
	{
		Grid next = board.getNextBlock();
		PosGrid block = board.getBlock();
		
		if (next == null)
			next = generator.generate();

		// Is the spawn location occupied?
		if(ParticleCollider.collide(
				board.getGrid(),
				next,
				getCenter(next), START_Y)) {
			gameOver();
		} else {
			block.setGrid(next);
			block.x = getCenter(next);
			block.y = START_Y;
			board.setNextBlock(generator.generate());
		}
	}
	
	// Get center of the grid placement
	private int getCenter(Grid grid)
	{
		return board.getGrid().getWidth()/2 - grid.getWidth()/2;
	}

	public synchronized void reset()
	{
		if (timer != null)
			timer.cancel();
		timer = new Timer(true);
		timer.schedule(new Dropper(this), 1000, 600);
		resetBoard();
		regenBlock();
		gameOver = false;
		update();
	}

	private void gameOver()
	{
		gameOver = true;
		timer.cancel();
		board.setNextBlock(null);
		board.setGameOver(true);
	}

	private void resetBoard()
	{
		board.setNextBlock(null);
		board.setGameOver(false);
		board.getGrid().clear();
		board.getBlock().getGrid().clear();
		board.setScore(0);
	}

	//	Called only at end of public methods, only if board got modified
	private void update()
	{
		board.notifyObservers();
	}
	
	private class Dropper extends TimerTask {
		private final GameLogic gameLogic;
		
		public Dropper(GameLogic gameLogic)
		{
			this.gameLogic = gameLogic;
		}
		
		@Override
		public void run()
		{
			gameLogic.moveDown();
		}
	}
}
