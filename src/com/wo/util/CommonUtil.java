package com.wo.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtil {
	public static double fromB2G(BigInteger bInt) {
		Long base = (long) (1024*1024*1024);
		
		BigDecimal bd = new BigDecimal(bInt);
		
		bd = bd.divide(new BigDecimal(base.toString()),2,RoundingMode.HALF_EVEN);

		return bd.doubleValue();
	}
	
	public static byte[] fromHexString(String hexStr, boolean hasColon)
			throws NumberFormatException {
		hexStr = hexStr.toLowerCase();
		int len = hexStr.length();
		byte bytes[] = new byte[hasColon ? ((len / 3) + 1) : (len / 2)];
		int sPos = 0; // position in hexStr
		int bPos = 0; // position in bytes
		try {
			while (sPos < len) {
				char a = hexStr.charAt(sPos);
				char b = hexStr.charAt(sPos + 1);
				if (hasColon && (a == ':' || b == ':'))
					throw new NumberFormatException("bad Hex format");
				int v1 = Character.digit(a, 16);
				int v2 = Character.digit(b, 16);
				if (v1 < 0 || v2 < 0)
					throw new NumberFormatException("bad Hex format");
				int v3 = (v1 * 16 + v2);
				bytes[bPos] = (byte) v3;
				sPos += hasColon ? 3 : 2;
				bPos++;
			}
		} catch (Exception ex) {
			throw new NumberFormatException("bad Hex format");
		}
		if (bPos < bytes.length)
			throw new NumberFormatException("bad Hex format");
		return bytes;
	}

	public static String toHexString(byte[] inputByte) {
		char[] HEX_DIGIT = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		if (inputByte == null || inputByte.length == 0)
			return null;
		StringBuffer outputstr = new StringBuffer(inputByte.length * 3);
		for (int i = 0; i < inputByte.length; i++) {
			int n = (inputByte[i] & 0xFF);
			outputstr.append(HEX_DIGIT[(n >> 4) & 0x0F]);
			outputstr.append(HEX_DIGIT[n & 0x0F]);

			if (i + 1 < inputByte.length)
				outputstr.append(':');
		}
		return outputstr.toString();
	}
	
	public static List<String> toColonString(List<String> pwwnList) {
		String pwwn = null;
		List<String> list_ColonString = new ArrayList<String>();
		
		Iterator<String> iter_PwwnList = pwwnList.iterator();
		while(iter_PwwnList.hasNext()) {
			String newPwwn = "";
			
			pwwn = iter_PwwnList.next();

			newPwwn = toColonString(pwwn);
			
			list_ColonString.add(newPwwn);
		}
		
		return list_ColonString;
	}
	
	public static Set<String> toColonString(Set<String> pwwnSet) {
		String pwwn = null;
		
		HashSet<String> set_ColonString = new HashSet<String>();
		Iterator<String> iter_PwwnSet = pwwnSet.iterator();
		while(iter_PwwnSet.hasNext()) {
			String newPwwn = "";
			
			pwwn = iter_PwwnSet.next();

			newPwwn = toColonString(pwwn);
			
			set_ColonString.add(newPwwn);
		}
		
		return set_ColonString;
	}
	
	public static String toColonString(String pwwn) {
		String newPwwn = "";
		
		pwwn = pwwn.toLowerCase().replaceAll(":", "");
		
		newPwwn += pwwn.charAt(0);
		for(int i = 1; i < pwwn.length(); i++) {
			char c = pwwn.charAt(i);
			
			if(0 == i % 2 && ':' != c) {
				newPwwn += ":";
			}
			
			newPwwn += pwwn.charAt(i);
		}
			
		return newPwwn;
	}
	
	public static Set<String> toUpperString(Set<String> strSet) {
		String newStr = "";
		HashSet<String> set_UpperString = new HashSet<String>();
		
		Iterator<String> iter_strSet = strSet.iterator();
		while(iter_strSet.hasNext()) {
			newStr = iter_strSet.next().toUpperCase();
			
			set_UpperString.add(newStr);
		}
		
		return set_UpperString;
	}
	
	public static Set<String> toNoColonString(Set<String> pwwnSet) {
		String pwwn = null;
		String newPwwn = "";
		HashSet<String> set_NoColonString = new HashSet<String>();
		
		Iterator<String> iter_PwwnSet = pwwnSet.iterator();
		while(iter_PwwnSet.hasNext()) {
			pwwn = iter_PwwnSet.next().toLowerCase();
			if(!pwwn.contains(":")) {
				set_NoColonString.add(pwwn);
				continue;
			}
			
			for(int i = 0; i < pwwn.length(); i++) {
				if(':' == pwwn.charAt(i)) {
					continue;
				}
				newPwwn += pwwn.charAt(i);
			}
			
			set_NoColonString.add(newPwwn);
			newPwwn = "";
		}
		
		return set_NoColonString;
	}
	
	public static String toNoColonString(String pwwn) {
		String newPwwn = "";
		
		pwwn = pwwn.toLowerCase();
		if(!pwwn.contains(":")) {
			newPwwn = pwwn;
			
			return newPwwn;
		}
		
		for(int i = 0; i < pwwn.length(); i++) {
			if(':' == pwwn.charAt(i)) {
				continue;
			}
			newPwwn += pwwn.charAt(i);
		}
			
		
		return newPwwn;
	}
	
	public static Set<String> toEMCFCPortName(Set<String> fcPortNameSet) {
		String fcName = null;
		String newFCName = null;
		String prefix = "FA-";
		Set<String> newFCPortName = new HashSet<String>();
		
		Iterator<String> iter_fcPortNameSet = fcPortNameSet.iterator();
		while(iter_fcPortNameSet.hasNext()) {
			fcName = iter_fcPortNameSet.next();
			if(fcName.contains(":")) {
				newFCPortName.add(fcName);
				continue;
			}
			
			newFCName = fcName.substring(0, fcName.length()-1);
			newFCName += ":" + fcName.charAt(fcName.length()-1);
			newFCName = prefix + newFCName;
			
			newFCPortName.add(newFCName.toUpperCase());
		}
		
		return newFCPortName;
	}
	
	public static HashMap<String, String> mapPortName2EMCFCPortName(Set<String> fcPortNameSet) {
		String fcName = null;
		String newFCName = null;
		String prefix = "FA-";
		
		HashMap<String, String> map_portName_emcPortName = new HashMap<String, String>();
		Iterator<String> iter_fcPortNameSet = fcPortNameSet.iterator();
		while(iter_fcPortNameSet.hasNext()) {
			fcName = iter_fcPortNameSet.next();
			if(fcName.contains(":")) {
				map_portName_emcPortName.put(fcName, fcName);
				continue;
			}
			
			newFCName = fcName.substring(0, fcName.length()-1);
			newFCName += ":" + fcName.charAt(fcName.length()-1);
			newFCName = prefix + newFCName;
			newFCName = newFCName.toUpperCase();
			
			map_portName_emcPortName.put(fcName, newFCName);
		}
		
		return map_portName_emcPortName;
	}
	
	public static String toFCPortName(String emcFCPortName) {
		String newFCName = null;
		String prefix = "FA-";
		
		newFCName = emcFCPortName.substring(prefix.length());
		newFCName = newFCName.replace(":", "");
		newFCName = newFCName.toLowerCase();
		
		return newFCName;
	}
	
	public static double formatDecimal(double decimal, int significantFigure) {
		String style = "0.";
		
		for(int i = 0; i < significantFigure; i++) {
			style += "0";
		}
		
		 DecimalFormat df = new DecimalFormat();
		 df.applyPattern(style);
		 
		 return Double.parseDouble(df.format(decimal));
	}
	
	public static int getRandomInt(int scope) {
		Random rdm = new Random(System.currentTimeMillis());
		int intRd = 0;
		
		/*
		 * when rdmInt is the minimum int value, Math.abs(rdmInt) == rdmInt
		 * so for fix this bug, when rdmInt is the minimum int value, then do rdmInt += 1;
		 */
		int rdmInt = rdm.nextInt();
		if(Integer.MIN_VALUE == rdmInt) {
			rdmInt += 1;
		}
		
		intRd = Math.abs(rdmInt)%scope + 1;
		
		return intRd;
	}
	
	public static boolean isInt(String str) {
		String regex = "[0-9]+";
		
		return Pattern.matches(regex, str);
	}
	
	public static String compareStr(String str1,String str2){

//		byte[] str1_byte = StringUtils.getBytesUsAscii(str1);
//		byte[] str2_byte = StringUtils.getBytesUsAscii(str2);
//		
//		for(int i=0; i<str1_byte.length;i++){
//			if(str1_byte[i]==str2_byte[i]){
//				continue;
//			}else if(str1_byte[i]<str2_byte[i]){
//				return str1;
//			}else{
//				return str2;
//			}
//		}
		return null;
	}
	
	public static boolean isNumber(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		} else {
			boolean isNum = str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
			return isNum;
		}
	}
	/**
	 * 判断字符串是否为ip地址的字符串
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIP(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
}
