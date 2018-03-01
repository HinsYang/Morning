package org.pussinboots.morning.os.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Hins on 2017/12/6.
 */
public class SignUtil {
    private static final String token="weixinDemo";

    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] paramArray={token,timestamp,nonce};
        Arrays.sort(paramArray);
        String content=paramArray[0].concat(paramArray[1]).concat(paramArray[2]);
        String ciphertext=null;
        try {
            MessageDigest md=MessageDigest.getInstance("SHA-1");
            byte[] digest=md.digest(content.getBytes());
            ciphertext=byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (ciphertext!=null&&signature.toUpperCase().equals(ciphertext)){
            return true;
        }else {
            return false;
        }
    }

    private static String byteToString(byte[] digest) {
        String strDigest="";
        for (int i = 0; i < digest.length; i++) {
            strDigest += byteToHexStr(digest[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte b) {
        char[] digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr=new char[2];
        tempArr[0]=digit[(b>>>4) & 0X0F];
        tempArr[1]=digit[b & 0X0F];
        String s=new String(tempArr);
        return s;
    }
}
