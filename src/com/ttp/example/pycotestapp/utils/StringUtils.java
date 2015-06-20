package com.ttp.example.pycotestapp.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class StringUtils {
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);
	}
	
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}
	
	/**
	 * Encode string to prevent causing errors in XML.
	 */
	public static String escapeXml(String s) {
		String result = "";
		
		try {
			result = URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Decode to get original string.
	 */
	public static String unescapeXml(String s) {
		String result = "";
		
		try {
			result = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean compare(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		} else {
			return s1.equals(s2);
		}
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;

	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }

	    is.close();

	    return sb.toString();
	}
	
	public static String listToString(List<String> listData, String separator) {
		StringBuilder result = new StringBuilder();
		
		int i = 0;
		for (String data : listData) {
			if (i > 0) {
				result.append(separator);
			}
			result.append(data);
			i++;
		}
		
		return result.toString();
	}
}
