package tw.ntustgrp12.tetris.block;

import java.util.Random;

import tw.ntustgrp12.tetris.board.Grid;
import utils.Utils;

public class RandomBlockGenerator implements BlockGenerator {

	Random random;
	
	public RandomBlockGenerator()
	{
		random = new Random();
	}
	
	@Override
	public Grid generate() {
		return Utils.randomEnum(BlockShape.class, random).getShape();
	}

}
