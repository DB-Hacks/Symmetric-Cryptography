package AES;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Utility.CryptoTools;

public class AES_PKCS5Padding_CBC {
	public static void main(String[] args) throws Exception
	{
		byte[] pt = "Computer Security program at York University".getBytes();
		byte[] ky = "CONFIDENTIALITY!".getBytes();
		byte[] iv = CryptoTools.hexToBytes("13AD19123087BF6CAC8D0F1254128772");
		
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, secret, aps);
		byte[] ct = cipher.doFinal(pt);
		
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: 7A937C4387F5C719811C219FE1AB1E327A241CAE934C43EDE359A9AC9A1A3DA538AFFD28CB4F398B0EBB8428BA730EC6
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct);
		
		System.out.println("BK = " + new String(bk) + "<");		//Test Case: Computer Security program at York University<
	}
}
