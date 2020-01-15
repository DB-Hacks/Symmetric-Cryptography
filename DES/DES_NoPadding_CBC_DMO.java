package DES;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Utility.CryptoTools;

public class DES_NoPadding_CBC_DMO {
	public static void main(String[] args) throws Exception
	{
		byte[] pt = "Computer".getBytes();
		byte[] pt2 = "Security".getBytes();
		byte[] ky = CryptoTools.hexToBytes("8B9CFF3280D1E213");
		byte[] iv = CryptoTools.hexToBytes("82FFE38021A37348");
		byte[] neg_iv = CryptoTools.getNeg(iv);
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		AlgorithmParameterSpec aps = new IvParameterSpec(neg_iv);
		cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		byte[] ct = cipher.doFinal(pt);
		byte[] neg_ct = CryptoTools.getNeg(ct);
		AlgorithmParameterSpec aps2 = new IvParameterSpec(neg_ct);
		cipher.init(Cipher.ENCRYPT_MODE, secret, aps2);
		byte[] ct2 = cipher.doFinal(pt2);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct) + CryptoTools.bytesToHex(ct2));		//Test Case: D8B4F30A1683257FD122562418949BDA
		
		//Decrypt
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps2);
		byte[] bk2 = cipher.doFinal(ct2);

		System.out.println("BK = " + new String(bk) + new String(bk2) + "<");	//Test Case: ComputerSecurity<
	}
}
