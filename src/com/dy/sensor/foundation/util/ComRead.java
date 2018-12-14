package com.dy.sensor.foundation.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;


/** 
 * 
 * @author Administrator 
 */
public class ComRead implements Runnable {
	static CommPortIdentifier portId;
	static Enumeration portList;//ö����  
	InputStream inputStream;
	OutputStream outputStream;
	SerialPort serialPort;
	Thread readThread;
	StringToHex sHex;
	List list = new ArrayList();
	List list1 = new ArrayList();
	public void start() {
		portList = CommPortIdentifier.getPortIdentifiers();//����ö�����͵õ�����ͨ�Ŷ˿�  
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {//���Ϊ���ڵĻ�  
				if (portId.getName().equals("COM2")) {//�����COM3��������Ҫ���Լ��趨ָ���Ĵ���  
					try {
						//�򿪴���  
						serialPort = (SerialPort) portId.open("Main", 2000);
					} catch (PortInUseException e) {
						System.out.println("************************");
					}
					try {
						outputStream = serialPort.getOutputStream();
						inputStream = serialPort.getInputStream();/*��ȡ�˿ڵ����������*/
					} catch (IOException e) {
					}
				}// end if  
			}// end if  
		}// end while  
		try {
			readThread = new Thread(this);
			//�̸߳���ÿ����һ����ݣ�����1����  
			readThread.start();
			//�ȴ��߳�ȫ��ִ�н�����ִ�����߳�
		    readThread.join();
		} catch (Exception e) {
		}
	}

	public void run() {

		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		byte[] read = new byte[8];
		byte[] write = new byte[]{0x01,0x03,0x0C,0x40,0x00,0x01,(byte)0xAB,0x0E};
		int nBytes = 0;
		try {
			outputStream.write(write);
			long t1 = System.currentTimeMillis();
			while (inputStream.available() == 0) {
				long t2 = System.currentTimeMillis();
	            if(t2-t1 > 2000){
	                break;
	            }
			}
			nBytes = inputStream.read(read);
			String hex = sHex.printHexString(read);//���s���ַ�������ݣ�ֱ��ת����ʮ����ơ�
			list1.add(hex);
			System.out.println(hex+"\t\n");
		} catch (IOException e) {
			System.err.println(e.toString());
		}
		try {
			Thread.sleep(1000); //��ȡ��ݳɹ�������start  
			list.remove("1");
		} catch (InterruptedException e) {
		}
		if(list!=null && !list.isEmpty()){
		   readThread.run();
		}
	}
	
	 public static void main(String[] args) {  
	        // TODO code application logic here  
	        ComRead comread=new ComRead();  
	        List li = new ArrayList();
	        li.add("1");
	        li.add("1");
	        comread.list = li;
	        comread.start();  
	        
	        
	        List l = comread.list1;
	        System.out.println(comread.list.size());
	    }  
}