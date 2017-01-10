package tw.ntustgrp12.tetris.board;

import java.util.Observable;

@SuppressWarnings("serial")
public class GameBoard extends Observable implements java.io.Serializable {
	private final Grid grid;
	private final PosGrid block;
	private Grid nextBlock;
	private int score;
	
	public GameBoard(Grid grid, PosGrid block)
	{
		this.grid = grid;
		this.block = block;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	public PosGrid getBlock()
	{
		return block;
	}
	
	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public Grid getNextBlock()
	{
		return nextBlock;
	}

	public void setNextBlock(Grid nextBlock)
	{
		this.nextBlock = nextBlock;
	}

	@Override
	public void notifyObservers()
	{
		setChanged();
		super.notifyObservers();
	}
}
