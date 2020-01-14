package DES;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import Utility.CryptoTools;

public class TwoDES_NoPadding_ECB 
{
	public static void main(String[] args) throws Exception
	{
		byte[] pt = ("The most advanced cybersecurity protocols can be broken by an employee that fails to uphold their duties").getBytes();
		byte[] ky = ("SECURITY").getBytes();
		byte[] ky2 = new byte[ky.length];
		for(int i = 0; i < ky.length; i++) {
			ky2[i] = (byte) ~(ky[i]);
		}

		Key secret = new SecretKeySpec(ky, "DES");
		Key secret2 = new SecretKeySpec(ky2, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(pt);
		cipher.init(Cipher.ENCRYPT_MODE, secret2);
		ct = cipher.doFinal(ct);

		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: D7D0F7FFAD8B052E3EB15E78AB32FFB96D65EEBDD75C089A80CF72C9B2DBF62AA83E7D0A6788F6D03739A7C960D61BE67032506E014DD09053F3E0A16187A733F2218FD07796F618E3204602A1E4D1D4D9D509B9AC72043937373FC6E6DF7807FBB48FE33D834A95
		
		cipher.init(Cipher.DECRYPT_MODE, secret2);
		byte[] bk = cipher.doFinal(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		bk = cipher.doFinal(bk);
		
		System.out.println("BK = " + new String(bk) + "<");		//Test Case: The most advanced cybersecurity protocols can be broken by an employee that fails to uphold their duties<
	}
}
