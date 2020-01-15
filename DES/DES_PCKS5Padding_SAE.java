package DES;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Utility.CryptoTools;

public class DES_PCKS5Padding_SAE {
	public static void main(String[] args) throws Exception
	{
		byte[] ct1 = CryptoTools.hexToBytes("7AA38A029E773CBB");
		byte[] ct2 = CryptoTools.hexToBytes("C188A9FCEADAE99D");
		byte[] ct3 = CryptoTools.hexToBytes("A560B784C99AFEF2");
		byte[] ky = CryptoTools.hexToBytes("4F75725269676874");
		byte[] iv = CryptoTools.hexToBytes("496E566563746F72");
		byte[]ctxor1 = CryptoTools.getXor(ct1, iv);
		byte[]ctxor2 = CryptoTools.getXor(ct2, ct1);
		byte[]ctxor3 = CryptoTools.getXor(ct3, ct2);
		byte[] ct = CryptoTools.hexToBytes(CryptoTools.bytesToHex(ctxor1) + CryptoTools.bytesToHex(ctxor2)+ CryptoTools.bytesToHex(ctxor3));
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] pt = cipher.doFinal(ct);
		
		System.out.println("PT = " + new String(pt) +"<");
	}
}
