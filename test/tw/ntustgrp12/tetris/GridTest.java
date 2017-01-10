package tw.ntustgrp12.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tw.ntustgrp12.tetris.board.Grid;
import utils.Utils;

public class GridTest {
	private Grid grid;

	@Before
	public void setUp() throws Exception {
		grid = new Grid(20, 20);
	}
	
	@Test
	public void testGetWidthHeight() {
		assertTrue(grid.getWidth() == 20);
		assertTrue(grid.getHeight() == 20);
	}

	@Test
	public void testGetData() {
		int[][] data = grid.getData();
		
		assertFalse(data == null);
		
		assertTrue(data[2][2] == 0);
		assertTrue(data[19][19] == 0);
		Grid grid1 = new Grid(20,40);
		data = grid1.getData();
		for (int i = 0; i < grid1.getHeight(); i++)
			for (int j = 0; j < grid1.getWidth(); j++)
				assertTrue(data[i][j] == 0);
		Grid grid2 = new Grid(Utils.stringToArrays("   XXX   "));
		data = grid2.getData();
		assertTrue(data[1][1] == 1);
		assertTrue(data[2][2] == 0);
	}

}
