package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_PKCS5Padding_CBC_Decrypt {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.hexToBytes("4E51297B424F90D8");
		byte[] ct2 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
		byte[] ky = ("CSE@YORK").getBytes();
		//byte[] iv = CryptoTools.hexToBytes("0123456701234567"); //not used because the first part is lost to us
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] pt = cipher.doFinal(ct2);
		
		System.out.println("PT = " + new String(pt) + "<");
		//SEL
	}
}
