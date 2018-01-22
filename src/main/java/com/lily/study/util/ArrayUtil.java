package com.lily.study.util;

public class ArrayUtil {

	public static double[] toDouble(String[] params) {

		double[] d = new double[params.length];

		for (int i = 0; i < params.length; i++) {
			d[i] = Double.valueOf(params[i]);
		}

		return d;
	}
}
