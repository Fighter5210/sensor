package com.dy.sensor.foundation.util;

public class StringToHex {
	public static String printHexString(byte[] b) {
		String hex = "";
		for (int i = 0; i < b.length; i++) {
			String hexTemp = Integer.toHexString(b[i] & 0xFF) + " ";
			if (hexTemp.length() == 2) {
				hexTemp = '0' + hexTemp;
			}
			hex = hex + hexTemp.toUpperCase();
		}
		return hex;
	}
}