package DES;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import Utility.CryptoTools;

public class DES_PKCS5Padding_CBC
{
	public static void main(String[] args) throws Exception
	{
		byte[] pt = "Transfer $1,000 to my savings account.".getBytes();
		byte[] ky = "PROTOCOL".getBytes();
		byte[] iv = CryptoTools.hexToBytes("F8A4537D08123490");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: F3FBC68E39A52E687A8332556A1327E06C7246DD4BD1633CADB10682E2C11824689AD58F34E6C6D7
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println("BK = " + new String(bk) + "<");		//Test Case: Transfer $1,000 to my savings account.<
	}

}