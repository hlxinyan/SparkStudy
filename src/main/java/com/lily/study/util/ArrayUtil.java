package com.lily.study.util;

import org.apache.commons.lang3.StringUtils;

public class ArrayUtil {

	public static double[] toDouble(String[] params) {

		double[] d = new double[params.length];

		for (int i = 0; i < params.length; i++) {
			if(StringUtils.isBlank(params[i])){
				continue;
			}
			d[i]=0;
			try{
				d[i]=Double.valueOf(params[i]);

			}catch(Exception e){

			}



		}

		return d;
	}


	public static int toInteger(String intStr){

		try{
			return Integer.valueOf(intStr);

		}catch (Exception e){
			return 0;
		}
	}

	public static double toSingleDouble(String intStr){

		try{
			return Double.valueOf(intStr);

		}catch (Exception e){
			return 0;
		}
	}
}
