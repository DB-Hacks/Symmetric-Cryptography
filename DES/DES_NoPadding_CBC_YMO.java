package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_NoPadding_CBC_YMO {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.hexToBytes("437DBAB5607137A5"); //cut cipher text every 8 bytes 16 hex
		byte[] ct2 = CryptoTools.hexToBytes("CFC1031114634087");
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		byte[] neg_iv = new byte[iv.length];//convert to bytes than negate each byte
		for(int i = 0; i < iv.length; i++) {
			neg_iv[i] = (byte) ~(iv[i]);
		}
		byte[] neg_ct = new byte[ct.length];
		for(int i = 0; i < ct.length; i++) { //negate the first half
			neg_ct[i] = (byte) ~(ct[i]);
		}
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		AlgorithmParameterSpec aps = new IvParameterSpec(neg_iv);
		AlgorithmParameterSpec aps2 = new IvParameterSpec(neg_ct);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] pt = cipher.doFinal(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps2);
		byte[] pt2 = cipher.doFinal(ct2);

		System.out.println("PT = " + new String(pt) + new String(pt2) + "<");
	}
}
