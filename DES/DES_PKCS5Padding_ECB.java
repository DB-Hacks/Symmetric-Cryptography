package DES;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Utility.CryptoTools;

public class DES_PKCS5Padding_ECB
{

	public static void main(String[] args) throws Exception
	{
		byte[] pt = "Data Encryption Standard".getBytes();
		byte[] ky = CryptoTools.hexToBytes("88421abcdef17904");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: 81AE0BF23E56E988612D592BB00057B942CBBEC307BF23C5722BDDDF32A168B0
		
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println("BK = " + new String(bk) + "<");		//Test Case: Data Encryption Standard<
	}

}