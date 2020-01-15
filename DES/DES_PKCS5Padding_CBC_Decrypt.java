package DES;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import Utility.CryptoTools;

public class DES_PKCS5Padding_CBC_Decrypt {
	public static void main(String[] args) throws Exception
	{
		//The first 8-bytes/16-hex of the cipher is lost
		//AD516846444C2B4DDEDC4ACD6D9B60556395AC8E9C68F92543E5112D21C458BCBFC8139FC52A18CB78895302EEB6D8A5C818DE76472FC65D823470B18B3F52B4FA562BC1CB7D5C90
		byte[] ct = CryptoTools.hexToBytes("6395AC8E9C68F925");
		byte[] ct2 = CryptoTools.hexToBytes("43E5112D21C458BCBFC8139FC52A18CB78895302EEB6D8A5C818DE76472FC65D823470B18B3F52B4FA562BC1CB7D5C90");
		byte[] ky = ("COMPUTER").getBytes();
		//byte[] iv = CryptoTools.hexToBytes("0123456701234567"); 	not used because the first 8-bytes are lost to us
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] pt = cipher.doFinal(ct2);
		
		System.out.println("PT = >" + new String(pt) + "<");		//Test Case: > is lost to us the other parts can be recovered<
	}
}
