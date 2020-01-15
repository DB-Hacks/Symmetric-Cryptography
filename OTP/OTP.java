package OTP;

import Utility.CryptoTools;

public class OTP {
	public static void main(String[] args) throws Exception
	{
		byte [] pt = ("Meet me at Lane on Sunday").getBytes();
		byte [] ky = ("JABHXPVOLLCIJSDFLJKSADFDF").getBytes();
		byte [] rt = ("Meet me at Berg on Friday").getBytes();
		
		byte [] ct = CryptoTools.getXor(ky, pt);
		System.out.println("CT = " + CryptoTools.bytesToHex(ct));	//Test Case: 0724273C783D336F2D3863052B3D216623246B00342A22253F
		
		
		byte [] bk = CryptoTools.getXor(ky, ct);
		System.out.println("BK = " + new String(bk));	//Test Case: Meet me at Lane on Sunday
		
		//A wrong key that results in another proper English sentence
		byte [] Wrong_ky = CryptoTools.getXor(rt, ct);
		System.out.println("Wrong ky = " + new String(Wrong_ky));	//Test Case: JABHXPVOLLCGNOFFLJKFFCFDF
		
		byte [] bk2 = CryptoTools.getXor(Wrong_ky, ct);
		System.out.println("Wrong bk = " + new String(bk2));	//Test Case: Meet me at Berg on Friday
	}
}
