package com.dy.sensor.foundation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * �����ļ�������
 * @author Spring.Cao
 * @version v1.0 2013-03-22
 */
public class ConfigUtil {
	/**
	 * ��ȡproperties�ļ�
	 * 
	 * @return
	 */
	private static Properties getProperty() {
		// ��ȡproperties�ļ�
		InputStream in = ConfigUtil.class
				.getResourceAsStream("/com/ccb/montior/cfg/properties/service.properties");
		Properties var = new Properties();
		try {
			var.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return var;
	}
}
