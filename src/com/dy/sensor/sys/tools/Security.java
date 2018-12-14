// Source File Name:   Security.java

package com.dy.sensor.sys.tools;

import java.io.*;
import java.security.*;
import javax.crypto.*;


public class Security
{

    public static final String MD5_ALGORITHM = "MD5";
    public static final String SHA1_ALGORITHM = "SHA-1";


    public Security()
    {
    }

    public void init()
    {
    }

    public byte[] digest(byte rawInfo[])
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
            md.update(rawInfo);
            byte cipher[] = md.digest();
            System.out.println("MD5加密···");
            return cipher;
        }
        catch(NoSuchAlgorithmException nsae)
        {
            return null;
        }
    }

    public String digestToHex(byte rawInfo[])
    {
        return Util.encodeBase16(digest(rawInfo)).toUpperCase();
    }

    public String digestToBase64(byte rawInfo[])
    {
        return Util.encodeBase64(digest(rawInfo));
    }

    public char[] encryptography(char rawInfo[])
    {
        try
        {
            Cipher cipher = Cipher.getInstance(SHA1_ALGORITHM);
            KeyGenerator kg = KeyGenerator.getInstance(SHA1_ALGORITHM);
            Key encryptoKey = kg.generateKey();
            cipher.init(1, encryptoKey);
            return null;
        }
        catch(NoSuchPaddingException npe)
        {
            npe.printStackTrace();
            return null;
        }
        catch(NoSuchAlgorithmException nsae)
        {
            nsae.printStackTrace();
            return null;
        }
        catch(InvalidKeyException ivke)
        {
            ivke.printStackTrace();
        }
        return null;
    }

    public char[] decryptography(char cryptographInfo[], Key deCryptoKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(SHA1_ALGORITHM);
            KeyGenerator kg = KeyGenerator.getInstance(SHA1_ALGORITHM);
            Key decryptoKey = kg.generateKey();
            cipher.init(1, decryptoKey);
            return null;
        }
        catch(NoSuchPaddingException npe)
        {
            return null;
        }
        catch(NoSuchAlgorithmException nsae)
        {
            return null;
        }
        catch(InvalidKeyException ivke0)
        {
            return null;
        }
    }

    public OutputStream encryptography(InputStream rawStream)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(SHA1_ALGORITHM);
            return null;
        }
        catch(NoSuchPaddingException npe)
        {
            return null;
        }
        catch(NoSuchAlgorithmException nsae)
        {
            return null;
        }
    }

    public OutputStream decryptography(InputStream cryptographStream)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(SHA1_ALGORITHM);
            return null;
        }
        catch(NoSuchPaddingException npe)
        {
            return null;
        }
        catch(NoSuchAlgorithmException nsae)
        {
            return null;
        }
    }


    public static void main(String args[])
    {
        Security security = new Security();
        String password = "shoms2013";
        String encyrptoString = security.digestToHex(password.getBytes());
        System.out.println("password=" + encyrptoString+"\t size="+encyrptoString.length() );
        encyrptoString = security.digestToBase64(password.getBytes());
        System.out.println("password=" + encyrptoString+"\t size="+encyrptoString.length());
        
    }

}
