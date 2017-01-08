package tw.ntustgrp12.tetris.block;

import tw.ntustgrp12.tetris.Grid;
import utils.Utils;

public enum BlockShape {
	RIGHT_L(" X "+
			" X "+
			" XX"),
	
	LEFT_L(	" X "+
			" X "+
			"XX "),
	
	BIG_I(	" X  "+
			" X  "+
			" X  "+
			" X  "),
	
	A_SQUARE("XX"+
			 "XX"),
	
	THE_T(   "   "+
			 "XXX"+
			 " X "),
	
	RIGHT_Z( "   "+
			 " XX"+
			 "XX "),
	
	LEFT_Z(  "   "+
			 "XX "+
			 " XX");
	
	private final Grid shape;
	
	private BlockShape(String dataString)
	{
		this.shape = new Grid(Utils.stringToArrays(dataString));
	}
	
	public Grid getShape()
	{
		return shape;
	}
}
