package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_NoPadding_ECB_Decrypt {
	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.hexToBytes("8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284");
		byte[] ky = ("FACEBOOK").getBytes();
		byte[] ky2 = new byte[ky.length];
		for(int i = 0; i < ky.length; i++) {
			ky2[i] = (byte) ~(ky[i]);
		}

		Key secret = new SecretKeySpec(ky, "DES");
		Key secret2 = new SecretKeySpec(ky2, "DES");
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, secret2);
		byte[] pt = cipher.doFinal(ct);
		cipher.init(Cipher.DECRYPT_MODE, secret);
		pt = cipher.doFinal(pt);

		System.out.println("PT = " + new String(pt) + "<");
		

	}
}
