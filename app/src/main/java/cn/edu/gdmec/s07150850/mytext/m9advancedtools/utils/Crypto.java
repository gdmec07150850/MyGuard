package cn.edu.gdmec.s07150850.mytext.m9advancedtools.utils;



import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    //加密一个文本，饭弧base64编码后的内容
    public static String encrypt(String seed, String plain) throws Exception{
        byte[] rawKey=getRawKey(seed.getBytes());
        byte[] encryted=encrypt(rawKey,plain.getBytes());
        return Base64.encodeToString(encryted,Base64.DEFAULT);
    }
    //解密base64编码后的密文
public static String decrypt(String seed,String encryted) throws Exception{
    byte[] rawKey=getRawKey(seed.getBytes());
    byte[] enc=Base64.decode(encryted.getBytes(),Base64.DEFAULT);
    byte[] result=dectypt(rawKey,enc);
   return new String(result);

}



    private static byte[] getRawKey(byte[] seed) throws Exception{
    KeyGenerator keygen=KeyGenerator.getInstance("AES");
    SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
    random.setSeed(seed);
    keygen.init(128,random);
    SecretKey key=keygen.generateKey();
    byte[] raw=key.getEncoded();
    return raw;
}
private static byte[] encrypt(byte[] raw,byte[] plain) throws Exception{
    SecretKeySpec keySpec=new SecretKeySpec(raw,"AES");
    Cipher cipher=Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE,keySpec);
    byte[] encrypted=cipher.doFinal(plain);
    return encrypted;
}
    private static byte[] dectypt(byte[] raw,byte[] encrypted) throws Exception{
        SecretKeySpec keySpec=new SecretKeySpec(raw,"AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec);
        byte[] decrypted=cipher.doFinal(encrypted);
        return decrypted;
    }
}
