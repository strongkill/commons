package cn.qtone.util;

/**
 * <p>Title: SimpleTeam</p>
 * <p>Description: Bit7 Coder</p>
 * <p>Copyright: All rights reserved by www.simpleteam.com. Copyright (c) 2003 ~ 2006</p>
 * <p>Company: www.simpleteam.com</p>
 * @author forest_luo@simpleteam.com
 * @version 1.1.2
 */

public class Bit7Coder
{
	//Mask
	private final static int[] mask = {0,1,3,7,15,31,63,127,255};

	/**
	 * Make 7bit encode.
	 *
	 * @param source Source for encoding.
	 * @return
	 *     <p>Encoded bytes.</p>
	 */
	public static byte[] encode(String source)
	{
		//Get size of source.
		int size = source.length();
		//Calculate needed bytes for destination.
		int bytesNeeded = ((size * 7) >> 3) + (((size & 0x07) != 0) ? 1 : 0);

		//Create a new buffer.
		byte[] encoded = new byte[bytesNeeded];
		//Convert source into destination buffer.
		for(int i = 0;i < bytesNeeded;i ++)
		{
			//Calculate byte offset.
			int byteOffset = (i << 3) / 7;

			//Get lower byte.
			byte lowByte = (byte)source.charAt(byteOffset);
			//Get higher byte.
			byte highByte = 0;
			//Check offset.
			if(byteOffset + 1 < size)
			{
				//Get high byte.
				highByte = (byte)source.charAt(byteOffset + 1);
			}

			//Calculate needed bits.
			int highBitNeeded = i % 7 + 1;
			int lowBitNeeded = 8 - highBitNeeded;

			//Convert value.
			byte value = (byte)((lowByte >> (7 - lowBitNeeded)) & mask[lowBitNeeded]);
			//Add low byte.
			value |= (highByte & mask[highBitNeeded]) << lowBitNeeded;

			//Set value of buffer.
			encoded[i] = value;
		}
		//Return encoded bytes.
		return encoded;
	}

	/**
	 * Make 7bit decode.
	 *
	 * @param source Source for decoding.
	 * @return
	 *     <p>Decoded bytes.</p>
	 */
	public static String decode(byte[] source)
	{
		//Get size of source.
		int size = source.length;
		//Calculate needed bytes for destination.
		int bytesNeeded = size * 8 / 7;

		//Create a new string.
		String decoded = "";
		//Convert source into destination.
		for(int i = 0;i < bytesNeeded;i ++)
		{
			//Calculate offset.
			int byteOffset = (i >> 3) * 7 + (i & 0x07);

			//Get lower byte.
			byte lowByte = 0;
			//Check result.
			if((i & 0x07) != 0)
			{
				lowByte = (byte)source[byteOffset - 1];
			}
			//Get higher byte.
			byte highByte = 0;
			//Check offset.
			if(byteOffset < size)
			{
				//Get high byte.
				highByte = (byte)source[byteOffset];
			}

			//Calculate needed bits.
			int highBitNeeded = 7 - (i & 0x07);
			int lowBitNeeded = 7 - highBitNeeded;

			//Convert value.
			byte value = (byte)((lowByte >> (8 - lowBitNeeded)) & mask[lowBitNeeded]);
			//Add low value.
			value |= (highByte & mask[highBitNeeded]) << lowBitNeeded;

			//Set value.
			decoded += (char)value;
		}
		//Return decoded string.
		return decoded;
	}
}
