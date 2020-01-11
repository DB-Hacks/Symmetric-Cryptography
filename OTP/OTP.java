package symmetric;

import util.CryptoTools;

public class OTP {
	public static void main(String[] args) throws Exception
	{
		byte [] pt = ("SENDMOREMONEY").getBytes();
		byte [] ky = ("JABHXPVOLLCIJ").getBytes();
		byte [] cash = ("CASHNOTNEEDED").getBytes();
		byte [] ct = new byte[pt.length];
		for (int i = 0; i < pt.length; i++) {
			ct[i] = (byte) (ky[i]^pt[i]);
		}
		
		System.out.println("PT = " + new String(pt));
		System.out.println("KY = " + new String(ky));
		//System.out.println("CT = " + new String(ct));
		
		//decrypt
		byte [] bk = new byte[ct.length];
		for(int i = 0;i < pt.length; i++) {
			bk[i] = (byte) (ky[i]^ct[i]);
		}
		System.out.println("BK = " + new String(bk));
		
		//random key
		byte [] test = CryptoTools.getXor(cash, ct);
		System.out.println("Random ky = " + new String(test));
		byte [] bk2 = CryptoTools.getXor(test, ct);
		System.out.println("Random bk = " + new String(bk2));
		
		// to get from single-core time to cluster convert it to one hour than multiple 2.15 to get the ms in cluster
		// flip a random bit in pt and if half or more of the ct changes its a block, stream uses xor
		// mono: x = y, poly: x1, x2, x3, ..., xn = y
	}
}
