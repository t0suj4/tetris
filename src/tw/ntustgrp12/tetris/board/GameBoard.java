package tw.ntustgrp12.tetris.board;

import java.util.Observable;

@SuppressWarnings("serial")
public class GameBoard extends Observable implements java.io.Serializable {
	private final Grid grid;
	private final PosGrid block;
	
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
	
	@Override
	public void notifyObservers()
	{
		setChanged();
		super.notifyObservers();
	}
}
