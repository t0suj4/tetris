package tw.ntustgrp12.tetris;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import utils.Utils;

public class ParticleColliderTest {
	
	private Grid bigGrid;
	private Grid grid1;
	private Grid grid2;
	private Grid grid3;
	private Grid grid4;

	@Before
	public void setUp() throws Exception {
		this.grid1 = new Grid(Utils.stringToArrays(
				   "X "+
				   "XX"));
		this.grid2 = new Grid(Utils.stringToArrays(
				   "XX"+
				   " X"));
		this.grid3 = new Grid(Utils.stringToArrays(
				   "XX"+
				   "XX"));
		this.grid4 = new Grid(Utils.stringToArrays(
				   "  "+
				   "  "));
		this.bigGrid = new Grid(Utils.stringToArrays(
				 "XX XX"+
				 "X   X"+
                 "     "+
				 "X   X"+
                 "XX XX"));
	}
	
	@Test
	public void testMergeBig(){
		Grid mergedGrid = new Grid(Utils.stringToArrays(
				"XXXXX"+
				"X XXX"+
				"     "+
				"X   X"+
				"XX XX"));
		ParticleCollider.mergeGrids(bigGrid, grid1, 2, 0);
		ParticleCollider.mergeGrids(bigGrid, grid4, 0, 0);
		assertTrue(Arrays.deepEquals(bigGrid.getData(), mergedGrid.getData()));
	}

	@Test
	public void testCollideBig() {
		assertFalse(ParticleCollider.collide(bigGrid, grid1, 2, 0));
		assertTrue(ParticleCollider.collide(bigGrid, grid1, 1, 0));
		assertFalse(ParticleCollider.collide(bigGrid, grid2, 1, 3));
		assertTrue(ParticleCollider.collide(bigGrid, grid2, 2, 3));
	}
	
	@Test
	public void testCollideSameOrigin() {
		assertTrue(ParticleCollider.collide(grid1, grid2, 0, 0));
		assertTrue(ParticleCollider.collide(grid1, grid3, 0, 0));
		assertTrue(ParticleCollider.collide(grid2, grid3, 0, 0));
		assertTrue(ParticleCollider.collide(grid2, grid1, 0, 0));
		assertTrue(ParticleCollider.collide(grid3, grid1, 0, 0));
		assertTrue(ParticleCollider.collide(grid3, grid2, 0, 0));
		assertTrue(ParticleCollider.collide(grid1, grid1, 0, 0));
		assertTrue(ParticleCollider.collide(grid2, grid2, 0, 0));
		assertTrue(ParticleCollider.collide(grid3, grid3, 0, 0));
		
	}
	
	@Test
	public void testCollideEmptyGrid() {
		assertFalse(ParticleCollider.collide(grid1, grid4, 0, 0));
		assertFalse(ParticleCollider.collide(grid2, grid4, 0, 0));
		assertFalse(ParticleCollider.collide(grid3, grid4, 0, 0));
		assertFalse(ParticleCollider.collide(grid4, grid1, 0, 0));
		assertFalse(ParticleCollider.collide(grid4, grid2, 0, 0));
		assertFalse(ParticleCollider.collide(grid4, grid3, 0, 0));
		assertFalse(ParticleCollider.collide(grid4, grid4, 0, 0));
	}
	
	@Test
	public void testCollideOutOfBounds() {
		assertTrue(ParticleCollider.collide(grid1, grid2, 1, 1));
		assertTrue(ParticleCollider.collide(grid4, grid1, 1, 1));
		assertFalse(ParticleCollider.collide(grid1, grid4, 1, 1));
		assertFalse(ParticleCollider.collide(grid4, grid4, 1, 1));
	}
	
	@Test
	public void testRotate() {
		Grid BigL1 = new Grid(Utils.stringToArrays(
				"  X"+
				"  X"+
				" XX"));
		Grid BigL2 = new Grid(Utils.stringToArrays(
				"   "+
				"X  "+
				"XXX"));
		Grid BigL3 = new Grid(Utils.stringToArrays(
				"XX "+
				"X  "+
				"X  "));
		Grid BigL4 = new Grid(Utils.stringToArrays(
				"XXX"+
				"  X"+
				"   "));
		Grid BigI1 = new Grid(Utils.stringToArrays(
				" X  "+
				" X  "+
				" X  "+
				" X  "));
		Grid BigI2 = new Grid(Utils.stringToArrays(
				"    "+
				"XXXX"+
				"    "+
				"    "));
		Grid BigI3 = new Grid(Utils.stringToArrays(
				"  X "+
				"  X "+
				"  X "+
				"  X "));
		Grid BigI4 = new Grid(Utils.stringToArrays(
				"    "+
				"    "+
				"XXXX"+
				"    "));
		Grid BigT1 = new Grid(Utils.stringToArrays(
				" X "+
				" XX"+
				" X "));
		Grid BigT2 = new Grid(Utils.stringToArrays(
				"   "+
				"XXX"+
				" X "));
		Grid BigT3 = new Grid(Utils.stringToArrays(
				" X "+
				"XX "+
				" X "));
		Grid BigT4 = new Grid(Utils.stringToArrays(
				" X "+
				"XXX"+
				"   "));
		
		Grid BigL = new Grid(BigL1.getData());
		assertTrue(Arrays.deepEquals(BigL.getData(), BigL1.getData()));
		BigL = ParticleCollider.getRotatedGrid(BigL);
		assertTrue(Arrays.deepEquals(BigL.getData(), BigL2.getData()));
		BigL = ParticleCollider.getRotatedGrid(BigL);
		assertTrue(Arrays.deepEquals(BigL.getData(), BigL3.getData()));
		BigL = ParticleCollider.getRotatedGrid(BigL);
		assertTrue(Arrays.deepEquals(BigL.getData(), BigL4.getData()));
		BigL = ParticleCollider.getRotatedGrid(BigL);
		assertTrue(Arrays.deepEquals(BigL.getData(), BigL1.getData()));
		BigL = ParticleCollider.getRotatedGrid(BigL);
		
		Grid BigT = new Grid(BigT1.getData());
		assertTrue(Arrays.deepEquals(BigT.getData(), BigT1.getData()));
		BigT = ParticleCollider.getRotatedGrid(BigT);
		assertTrue(Arrays.deepEquals(BigT.getData(), BigT2.getData()));
		BigT = ParticleCollider.getRotatedGrid(BigT);
		assertTrue(Arrays.deepEquals(BigT.getData(), BigT3.getData()));
		BigT = ParticleCollider.getRotatedGrid(BigT);
		assertTrue(Arrays.deepEquals(BigT.getData(), BigT4.getData()));
		BigT = ParticleCollider.getRotatedGrid(BigT);
		assertTrue(Arrays.deepEquals(BigT.getData(), BigT1.getData()));
		
		Grid BigI = new Grid(BigI1.getData());
		assertTrue(Arrays.deepEquals(BigI.getData(), BigI1.getData()));
		BigI = ParticleCollider.getRotatedGrid(BigI);
		assertTrue(Arrays.deepEquals(BigI.getData(), BigI2.getData()));
		BigI = ParticleCollider.getRotatedGrid(BigI);
		assertTrue(Arrays.deepEquals(BigI.getData(), BigI3.getData()));
		BigI = ParticleCollider.getRotatedGrid(BigI);
		assertTrue(Arrays.deepEquals(BigI.getData(), BigI4.getData()));
		BigI = ParticleCollider.getRotatedGrid(BigI);
		assertTrue(Arrays.deepEquals(BigI.getData(), BigI1.getData()));
		BigI = ParticleCollider.getRotatedGrid(BigI);
	}

	@Test
	public void testDeleteRows() {
		Grid undeleted1 = new Grid(Utils.stringToArrays(
				"      "+
		        " X X X"+
				"XXXXXX"+
		        "XXX XX"+
				"X XXXX"+
		        "XXXXXX"));

		Grid deleted1 = new Grid(Utils.stringToArrays(
				"      "+
				"      "+
				"      "+
				" X X X"+
				"XXX XX"+
				"X XXXX"));

		Grid undeleted2 = new Grid(Utils.stringToArrays(
				"XXXXXX"+
		        "XX X X"+
				"XXXXXX"+
		        "XXX XX"+
				"X XX X"+
		        "XXXXXX"));

		Grid deleted2 = new Grid(Utils.stringToArrays(
				"      "+
				"      "+
				"XXXXXX"+
				"XX X X"+
				"XXX XX"+
				"X XX X"));

		Grid undeleted3 = new Grid(Utils.stringToArrays(
				"XXXXXX"+
		        "XX X X"+
				"XXXXXX"+
		        "XXX XX"+
				"X XX X"+
		        "XXXXXX"));

		Grid deleted3 = new Grid(Utils.stringToArrays(
				"      "+
				"      "+
				"XX X X"+
				"XXX XX"+
				"X XX X"+
				"XXXXXX"));

		ParticleCollider.clearFullRows(undeleted1, 2, 5);
		ParticleCollider.clearFullRows(undeleted2, 2, 5);
		ParticleCollider.clearFullRows(undeleted3, 0, 4);

		assertTrue(Arrays.deepEquals(undeleted1.getData(), deleted1.getData()));
		assertTrue(Arrays.deepEquals(undeleted2.getData(), deleted2.getData()));
		assertTrue(Arrays.deepEquals(undeleted3.getData(), deleted3.getData()));
	}

	@Test
	public void testDeleteLongRows() {
		Grid undeleted1 = new Grid(Utils.stringToArrays(
				"      "+
		        " X X X"+
				"XXXXXX"+
		        "XXX XX"+
				"X XXXX"+
		        "XXXXXX"));

		Grid deleted1 = new Grid(Utils.stringToArrays(
				"      "+
				"      "+
				"      "+
				" X X X"+
				"XXX XX"+
				"X XXXX"));

		ParticleCollider.clearFullRows(undeleted1, 2, 6);

		assertTrue(Arrays.deepEquals(undeleted1.getData(), deleted1.getData()));
	}
}
