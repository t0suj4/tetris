package utils;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class UtilsTest {
	private String RightL =
		(	"    "+
			" X  "+
			" X  "+
			" XX ");
	
	private String LeftL =
		(	"    "+
			"  X "+
			"  X "+
			" XX ");
	
	private String BigI =
		(	"X   "+
			"X   "+
			"X   "+
			"X   ");

	@Test
	public void testStringToArrays() {
		int[][] bigI = {{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0}};
		assertTrue(Arrays.deepEquals(bigI,Utils.stringToArrays(BigI)));
		int[][] leftL = {{0,0,0,0},
				 {0,0,1,0},
				 {0,0,1,0},
				 {0,1,1,0}};
		assertTrue(Arrays.deepEquals(leftL,Utils.stringToArrays(LeftL)));
		int[][] rightL = {{0,0,0,0},
		 {0,1,0,0},
		 {0,1,0,0},
		 {0,1,1,0}};
		assertTrue(Arrays.deepEquals(rightL,Utils.stringToArrays(RightL)));
	}

}
