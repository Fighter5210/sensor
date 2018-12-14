package com.dy.sensor.common.support;
public final class CodecUtil {
	    static CRC16_bak crc16 = new CRC16_bak();
	  
	    private CodecUtil() {
	    }
	  
	    public static byte[] short2bytes(short s) {
	        byte[] bytes = new byte[2];
	        for (int i = 1; i >= 0; i--) {
	            bytes[i] = (byte)(s % 256);
	            s >>= 8;
	        }
	        return bytes;
	    }
	  
	    public static short bytes2short(byte[] bytes) {
	        short s = (short)(bytes[1] & 0xFF);
	        s |= (bytes[0] << 8) & 0xFF00;
	        return s;
	    }
	  
	    /*
	     * ��ȡcrcУ���byte��ʽ
	     */
	    public static byte[] crc16Bytes(byte[] data) {
	        return short2bytes(crc16Short(data));
	    }
	  
	    /*
	     * ��ȡcrcУ���short��ʽ
	     */
	    public static short crc16Short(byte[] data) {
	        return crc16.getCrc(data);
	    }
	    
	  
	    public static void main(String[] args) {
	        byte[] test = new byte[] {0, 1, 2, 3, 4};
	        byte[] crc = crc16Bytes(test);
	  
	        byte[] testc = new byte[test.length + 2];
	        for (int i = 0; i < test.length; i++) {
	            testc[i] = test[i];
	        }
	        testc[test.length] = crc[0];
	        testc[test.length + 1] = crc[1];
	  
	        System.out.println(crc16Short(testc));
	    }

}
