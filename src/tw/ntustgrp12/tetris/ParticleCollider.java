package tw.ntustgrp12.tetris;

import com.rits.cloning.Cloner;

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
	
	private static int[][] shiftGrid(int[][] data)
	{
		int w = data.length;
		int fullRows = w;
		for(int i = w-1; i >= 0; --i) {
			boolean isEmpty = true;
			for (int j = 0; j < w; j++) {
				if (data[i][j] != 0) {
					isEmpty = false;
					break;
				}
			}
			if (isEmpty)
				fullRows -= 1;
			else
				break;
		}
		int fullCols = w;
		for(int i = 0; i < w; i++) {
			boolean isEmpty = true;
			for (int j = 0; j < w; j++) {
				if (data[j][i] != 0) {
					isEmpty = false;
					break;
				}
			}
			/* Already where we need it */
			if (isEmpty)
				fullCols -= 1;
			else
				break;
		}
		
		if (fullRows != w) {
			/* Shift rows down */
			for (int i = 0; i < fullRows; i++) {
				data[w - 1 - i] = data[fullRows - 1 - i];
			}
			
			/* Clear free space */
			for (int i = 0; i < w - fullRows; i++) {
				data[i] = new int [w];
			}
		}
		
		if (fullCols != w) {
			/* Shift cols left */
			for (int i = w - fullRows; i < w; i++) {
				for (int j = 0; j < fullCols; j++) {
					data[i][j] = data[i][w - fullCols + j];
				}
			}
			
			/* Clear free space */
			for (int i = w - fullRows; i < w; i++) {
				for (int j = 0; j < w - fullCols; j++) {
					data[i][j + fullCols] = 0;
				}
			}
		}
		
		return data;
	}
}
