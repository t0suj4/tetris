package utils;

import com.rits.cloning.Cloner;

import tw.ntustgrp12.tetris.board.Grid;

/* Used collide particles in CERN, now collides tetris bricks! */
public class ParticleCollider {
	
	public static boolean collide(Grid tarGrid, Grid grid, int x, int y)
	{
		int w = grid.getWidth();
		int h = grid.getHeight();
		
		int[][] tdata = tarGrid.getData();
		int[][] gdata = grid.getData();
		
		try {
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++){
					if(gdata[i][j] != 0 && tdata[y+i][x+j] != 0)
						return true;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		
		return false;
	}
	
	public static void mergeGrids(Grid tarGrid, Grid grid, int x, int y)
	{
		int w = grid.getWidth();
		int h = grid.getHeight();
		
		int[][] tdata = tarGrid.getData();
		int[][] gdata = grid.getData();
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if (gdata[i][j] != 0)
					tdata[y+i][x+j] = gdata[i][j];
			}
		}
	}
	
	public static Grid getRotatedGrid(Grid grid)
	{
		if (grid.getWidth() != grid.getHeight())
			throw new RuntimeException("Can't rotate rectangular grid!");
				
		int[][] data = Cloner.shared().deepClone(grid.getData());
		data = reverseGrid(data);
		data = transposeGrid(data);
		//data = shiftGrid(data);
		
		return new Grid(data);
	}
	
	public static void clearFullRows(Grid grid, int start, int end)
	{
		int[][] data = grid.getData();
		// Cannot shift past the grid
		end = end > grid.getHeight()-1 ? grid.getHeight()-1 : end;
		int w = grid.getWidth();
		int d = end - start;
		assert(d > 0);
		int fullRow = end;

		// Shift over full rows in range
		for(int i = end; i >= start; --i) {
			boolean isHoled = false;
			for (int j = 0; j < w; j++) {
				if (data[i][j] == 0) {
					isHoled = true;
					break;
				}
			}

			if (isHoled) {
				if (fullRow != i)
					data[fullRow] = data[i];
				fullRow -= 1;
			}
		}

		// Finish shift
		for (int i = start-1; i >= 0; --i) {
			data[fullRow] = data[i];
			fullRow -= 1;
		}

		// Clear free space
		for (; fullRow >= 0; --fullRow) {
			data[fullRow] = new int[w];
		}
	}

	private static int[][] transposeGrid(int[][] data)
	{
		int l = data.length;
		for (int i = 0; i < l; i++) {
			for (int j = i; j < l; j++) {
				int tmp = data[i][j];
				data[i][j] = data[j][i];
				data[j][i] = tmp;
			}
		}
		return data;
	}
	
	/* Reverse rows */
	private static int[][] reverseGrid(int[][] data)
	{
		int l = data.length/2;
		int h = data.length-1;
		for (int i = 0; i < l; i++) {
			int[] tmp = data[i];
			data[i] = data[h-i];
			data[h-i] = tmp;
		}
		return data;
	}
}
