package tw.ntustgrp12.tetris;

import tw.ntustgrp12.tetris.block.BlockGenerator;

public class GameLogic {
	private final BlockGenerator generator;
	private GameBoard board;
	
	private final int START_X = 4;
	private final int START_Y = 0;
	
	public GameLogic(BlockGenerator generator, GameBoard board)
	{
		this.board = board;
		this.generator = generator;
		regenBlock();
	}
	
	public void moveLeft()
	{
		if(move(-1, 0))
			update();
	}
	
	public void moveRight()
	{
		if (move(1, 0))
			update();
	}
	
	public void moveDown()
	{
		if (!move(0, 1))
			fixBlock();
		update();
	}
	
	public void rotate()
	{
		PosGrid block = board.getBlock();
		Grid rot = ParticleCollider.getRotatedGrid(block.getGrid());
		if (ParticleCollider.collide(board.getGrid(), rot,
									 block.x, block.y))
			return;
		block.setGrid(rot);
		update();
	}
	
	public void land()
	{
		
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
		System.out.println("Moving to "+block.x+" "+block.y);
		return true;
	}
	
	private void fixBlock()
	{
		PosGrid block = board.getBlock();
		
		ParticleCollider.mergeGrids(
				board.getGrid(),
				block.getGrid(),
				block.x, block.y);
		
		regenBlock();
	}
	
	private void regenBlock()
	{
		Grid freshGrid = generator.generate();
		PosGrid block = board.getBlock();
		
		if(ParticleCollider.collide(
				board.getGrid(),
				freshGrid,
				START_X, START_Y)) {
			gameOver();
			block.getGrid().clear();
		} else {
			block.setGrid(freshGrid);
			block.x = START_X;
			block.y = START_Y;
		}
		
	}
	
	private void gameOver()
	{
		throw new RuntimeException("Not implemented!");
	}
	
	private void update()
	{
		board.notifyObservers();
	}
}
