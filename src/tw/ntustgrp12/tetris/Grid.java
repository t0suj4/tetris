package tw.ntustgrp12.tetris;

import java.lang.reflect.Array;

@SuppressWarnings("serial")
public class Grid implements java.io.Serializable {
	private final int width;
	private final int height;
	private int[][] data;
	
	public Grid(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.data = new int[width][height];
	}
	
	public Grid(int[][] data)
	{
		this.height = Array.getLength(data);
		this.width = Array.getLength(data[0]);
		this.data = data;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int[][] getData()
	{
		return data;
	}
	
	public void setData(int[][] data)
	{
		this.data = data;
	}
	
	public void clear()
	{
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				data[i][j] = 0;
	}
}
