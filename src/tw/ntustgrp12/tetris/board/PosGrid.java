package tw.ntustgrp12.tetris.board;

@SuppressWarnings("serial")
public class PosGrid implements java.io.Serializable {
	private Grid grid;
	public int x;
	public int y;

	public PosGrid(Grid grid)
	{
		this.grid = grid;
	}
	
	public Grid getGrid()
	{
		return grid;
	}
	
	public void setGrid(Grid grid)
	{
		this.grid = grid;
	}
}
