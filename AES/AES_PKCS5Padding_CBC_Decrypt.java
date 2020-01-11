package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_PKCS5Padding_CBC_Decrypt {
	public static void main(String[] args) throws Exception
	{
		//activity B
//		byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
//		byte[] ky = ("DO NOT TELL EVE!").getBytes();
//		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		
		//Rehearsal question
		byte[] ct = CryptoTools.hexToBytes("FB0692B011F74F8BF77EDE2630852C1700C204407EDF2222D965F74A8BCEB236");
		byte[] ky = CryptoTools.hexToBytes("444F204E4F542054454C4C2045564521");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println("BK = " + new String(bk) + "<");
		//First Word "DO"
		//Rehearsal pt DO NOT DO THIS NO MORE

	}
}
