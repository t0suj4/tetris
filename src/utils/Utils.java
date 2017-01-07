package utils;

import java.util.Random;

public class Utils {
	public static <T extends Enum<?>> T randomEnum(Class<T> enumeration, Random random)
	{
		int selection = random.nextInt(enumeration.getEnumConstants().length);
		return enumeration.getEnumConstants()[selection];
	}
	
	public static int[][] stringToArrays(String str) {
		double rt = Math.sqrt((double)str.length());
		if (rt % 1 != 0)
			throw new RuntimeException("String len is not perfect square");
		int len = (int) rt;
		int[][] data = new int[len][len];
		char chars[] = str.toCharArray();
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				char aChar = chars[j+i*len];
				data[i][j] = (aChar == ' ') ? 0 : 1;
			}
		}
		return data;
	}
}
