package tw.ntustgrp12.tetris;

import tw.ntustgrp12.tetris.block.BlockGenerator;
import tw.ntustgrp12.tetris.block.RandomBlockGenerator;

public class GameFactory {
	
	public static GameBoard newBoard()
	{
		final int BOARD_WIDTH = 10;
	    final int BOARD_HEIGHT = 22;
	    
		PosGrid block = new PosGrid(new Grid(4, 4));
		Grid gameGrid = new Grid(BOARD_WIDTH, BOARD_HEIGHT);
		return new GameBoard(gameGrid, block);
	}
	
	public static GameLogic newLogic(GameBoard board)
	{
		BlockGenerator generator = new RandomBlockGenerator();
		return new GameLogic(generator, board);
	}
}
