package com.dy.sensor.common.support;

public class CRC16_bak {
	    private short[] crcTable = new short[256];
	    private int gPloy = 0x8005; // ��ɶ���ʽ
	  
	    public CRC16_bak() {
	        computeCrcTable();
	    }
	  
	    private short getCrcOfByte(int aByte) {
	        int value = aByte << 8;
	  
	        for (int count = 7; count >= 0; count--) {
	            if ((value & 0x8000) != 0) { // �ߵ�16λΪ1�����԰�λ���
	                value = (value << 1) ^ gPloy;
	            } else {
	                value = value << 1; // ��λΪ0������
	            }
	  
	        }
	        value = value & 0xFFFF; // ȡ��16λ��ֵ
	        return (short)value;
	    }
	  
	    /*
	     * ���0 - 255��Ӧ��CRC16У����
	     */
	    private void computeCrcTable() {
	        for (int i = 0; i < 256; i++) {
	            crcTable[i] = getCrcOfByte(i);
	        }
	    }
	  
	    public short getCrc(byte[] data) {
	        int crc = 0;
	        int length = data.length;
	        for (int i = 0; i < length; i++) {
	            crc = ((crc & 0xFF) << 8) ^ crcTable[(((crc & 0xFF00) >> 8) ^ data[i]) & 0xFF];
	        }
	        crc = crc & 0xFFFF;
	        return (short)crc;
	    }
	}
