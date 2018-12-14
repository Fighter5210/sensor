package com.dy.sensor.common.support;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.dy.sensor.product.model.vo.ProductDataVo;
import com.dy.sensor.sys.model.vo.SysParamVo;

/**
 * 
 * @author Administrator
 */
public class ComRead implements Runnable {
	static CommPortIdentifier portId;
	@SuppressWarnings("unchecked")
	static Enumeration portList;//
	InputStream inputStream;
	OutputStream outputStream;
	SerialPort serialPort;
	Thread readThread;
	ComUtil comUtil;
	@SuppressWarnings("unchecked")
	List<ProductDataVo> sourceList = new ArrayList<ProductDataVo>();
	List<ProductDataVo> returnList = new ArrayList<ProductDataVo>();
	SysParamVo sysParamVo = new SysParamVo();
	int i = 0;
	int repeatSendCount = 0;

	public void start() {
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM1")) {
					try {
						serialPort = (SerialPort) portId.open("Main", 2000);
					} catch (PortInUseException e) {
						System.out.println("端口打开失败！");
					}
				}
			}
		}

		try {
			try {
				outputStream = serialPort.getOutputStream();
				inputStream = serialPort.getInputStream();
			} catch (IOException e) {
				serialPort.close();
				e.printStackTrace();
			}
			readThread = new Thread(this);
			readThread.start();
			readThread.join();
		} catch (Exception e) {
			System.out.println("portId 为空");
		}

	}

	@SuppressWarnings("static-access")
	public void run() {
		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			ProductDataVo productDataVo = sourceList.get(0);
			byte[] read = new byte[9];
			byte addressCode = (byte) Integer.parseInt(productDataVo
					.getAddressCode(), 16);
			// byte[] write = new byte[] { addressCode, 0x03, (byte) 0x9c, 0x40,
			// 0x00,
			// 0x02,(byte) 0xeb,(byte) 0xbc};
			byte[] write = new byte[] { addressCode, 0x03, (byte) 0x9c, 0x40,
					0x00, 0x02 };

			int intCrc = CRC16.calcCrc16(write);
			byte[] crc = DataTypeConvert.int2byte(intCrc);
			byte[] newWrite = new byte[write.length + 2];
			for (int i = 0; i < write.length; i++) {
				newWrite[i] = write[i];
			}
			newWrite[write.length] = crc[0];
			newWrite[write.length + 1] = crc[1];

			ProductDataVo vo = new ProductDataVo();

			outputStream.write(newWrite);

			int tempAvailable = inputStream.available();

			if (tempAvailable > 0) {
				inputStream.read(read);
			}
			while (tempAvailable == 0 && repeatSendCount <= 3) {
				// 延时读取
				long t1 = System.currentTimeMillis();
				while (tempAvailable == 0) {
					tempAvailable = inputStream.available();
					long t2 = System.currentTimeMillis();
					if (t2 - t1 > sysParamVo.getCommIntervalTime()) {
						break;
					}
				}
				// 读取不到，重发两次
				if (tempAvailable > 0) {
					inputStream.read(read);
					repeatSendCount = 0;
				} else {
					repeatSendCount++;
					outputStream.write(newWrite);
				}
			}
			if (tempAvailable == 0) {
				vo.setUnReturnData("1");
			}
			repeatSendCount = 0;

			byte[] newRead = new byte[7];
			for (int i = 0; i < newRead.length; i++) {
				newRead[i] = read[i];
			}
			//
			int readIntCrc = CRC16.calcCrc16(newRead);
			byte[] readCrc = DataTypeConvert.int2byte(readIntCrc);

			String hexReadCrc = comUtil.printHexString(readCrc);
			String hex = comUtil.printHexString(read);

			vo.setId(i++);
			vo.setProductId(productDataVo.getProductId());
			vo.setAddressCode(productDataVo.getAddressCode());
			vo.setBarCode(productDataVo.getBarCode());
			vo.setProductDate(productDataVo.getProductDate());
			vo.setProductBatch(productDataVo.getProductBatch());
			vo.setTemperatureStd(productDataVo.getTemperatureStd());
			vo.setHumidityStd(productDataVo.getHumidityStd());

			// 返回数据处理
			String temperatureData = hex.substring(6, 10);
			String humidityData = hex.substring(10, 14);
			String readCrcStr = hex.substring(14, 18);

			if (!readCrcStr.equals(hexReadCrc)) {
				vo.setIsCheckNoPass("1");
			} else {
				vo.setIsCheckNoPass("0");
			}
			// 计算温度偏差
			String temperatureDataStr = comUtil.hexToDecimal(temperatureData);
			if (temperatureDataStr.length() == 4) {
				vo.setTemperatureData(Double
						.parseDouble(formateStr(temperatureDataStr)));
				Double temperatureOffset = vo.getTemperatureData()
						- vo.getTemperatureStd();
				vo.setTemperatureOffset(temperatureOffset);
				if (Math.abs(vo.getTemperatureOffset()) > sysParamVo
						.getTemAllowOffset()) {
					vo.setIsOverTemAllowOffset("1");
				}
			}
			// 计算湿度偏差
			String humidityDataStr = comUtil.hexToDecimal(humidityData);
			if (humidityDataStr.length() == 4) {
				vo.setHumidityData(Double
						.parseDouble(formateStr(humidityDataStr)));
				Double humidityOffset = vo.getHumidityData()
						- vo.getHumidityStd();
				vo.setHumidityOffset(humidityOffset);

				if (Math.abs(vo.getHumidityOffset()) > sysParamVo.getHumAllowOffset()
						) {
					vo.setIsOverHumAllowOffset("1");
				}
			}
			returnList.add(vo);
			// try {
			// Thread.sleep(10);
			sourceList.remove(0);
			// } catch (Exception e) {
			// serialPort.close();
			// }

			if (sourceList != null && !sourceList.isEmpty()) {
				readThread.run();
			} else {
				serialPort.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			serialPort.close();
		}
	}

	public String formateStr(String str) {
		String s1 = str.substring(0, 2);
		String s2 = str.substring(2, 4);
		String s = s1 + "." + s2;
		return s;
	}

	public List<ProductDataVo> execute(List<ProductDataVo> list,
			SysParamVo sysParamVo) {
		if (list == null || list.size() <= 0) {
			return new ArrayList<ProductDataVo>();
		}
		ComRead comread = new ComRead();
		comread.sourceList = list;
		comread.sysParamVo = sysParamVo;
		comread.start();
		List<ProductDataVo> returnList = comread.returnList;
		return returnList;
	}
}