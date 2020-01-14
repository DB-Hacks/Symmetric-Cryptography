package DES;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import Utility.CryptoTools;

public class DES_NoPadding_ECB
{

	public static void main(String[] args) throws Exception
	{
		byte[] pt = "ComputerSecurity".getBytes();
		byte[] ky = CryptoTools.hexToBytes("50927abcdef14092");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: 1AE55F40610292E7A83EBA321B213894
		
		cipher.init(Cipher.DECRYPT_MODE, secret);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println("BK = " + new String(bk) + "<");		//Test Case: ComputerSecurity<

	}

}