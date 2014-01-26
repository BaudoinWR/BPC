package com.ribdum.battlepets;

import java.util.Random;

public class RandomGenerator {
	private static Random random = new Random(System.currentTimeMillis());

	public static boolean isSuccess(int percent) {
		int i = random.nextInt(100) + 1;
		return i < percent;
	}
}
