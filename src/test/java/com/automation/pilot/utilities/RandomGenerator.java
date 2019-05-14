package com.automation.pilot.utilities;

import java.util.Random;

public class RandomGenerator {

	static Random rand = new Random();

	public static int randaomNumGen(int range) {

		int rand_int = rand.nextInt(range);
		return rand_int;
	}

	public static float randaomFloatGen() {

		float rand_int = rand.nextFloat();
		return rand_int;
	}

	public static String randomStrGen() {
		String rand_Str = rand.toString();
		return rand_Str;
	}
}
