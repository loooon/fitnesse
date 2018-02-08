package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

//import com.youku.uploader.FileUtil;

public class TypeChange {
	public TypeChange() {
	}

	// change the string type to the int type
	public static int stringToInt(String intstr) {
		Integer integer;
		integer = Integer.valueOf(intstr);
		return integer.intValue();
	}

	// change int type to the string type
	public static String intToString(int value) {
		Integer integer = new Integer(value);
		return integer.toString();
	}

	// change the string type to the number type
	public static Number stringToNumber(String str) {

		Double doub;
		doub = Double.valueOf(str).doubleValue();
		return (Number) doub;
	}

	// change number type to the string type
	public static String numberToString(Number num) {
		Double doub;
		doub = num.doubleValue();
		return doub.toString();
	}

	// change the string type to the float type
	public static float stringToFloat(String floatstr) {
		Float floatee;
		floatee = Float.valueOf(floatstr);
		return floatee.floatValue();
	}

	public static boolean stringToBoolean(String booleanStr) {
		boolean result = false;
		result = Boolean.valueOf(booleanStr);
		return result;
	}

	// change the float type to the string type
	public static String floatToString(float value) {
		Float floatee = new Float(value);
		return floatee.toString();
	}

	public static File stringToFile(String file) {
		String filePath = new String(file);
		File fileName = new File(filePath);
		return fileName;
	}

	// change the string type to the sqlDate type
	public static java.sql.Date stringToDate(String dateStr) {
		return java.sql.Date.valueOf(dateStr);
	}

	// change the sqlDate type to the string type
	public static String dateToString(java.sql.Date date) {
		return date.toString();
	}

	// change the string type to the BigDecimal type
	public static BigDecimal stringToBigDecimal(String bigDecimalStr) {
		BigDecimal bd = new BigDecimal(bigDecimalStr);
		return bd;
	}

	public static byte[] stringToByte64(String pathString) throws IOException {
		File file = new File(pathString);
		FileInputStream fis = new FileInputStream(file);
		byte[] rs = new byte[fis.available()];
		fis.read(rs);
		fis.close();
		return rs;
	}

	public static byte[] stringToByteArray(String pathString)
			throws IOException {
		File f = new File(pathString);
		FileInputStream fis = new FileInputStream(f);
		byte[] fileBytes = FileUtils.readBytes(fis);
		return fileBytes;
	}

	// public static byte[] stringToYouKuByteArray(String pathString) throws
	// IOException{
	// File f = new File(pathString);
	// byte[] data = FileUtil.getBytesFromFile(f);
	// return data;
	// }

	public static UploadBytesRequest stringToUploadBytesRequest(
			String pathString) throws IOException {
		File f = new File(pathString);
		System.out.println(pathString + "\n---------------------------");
		System.out.println(f + "\n***************************");
		FileInputStream fis = new FileInputStream(f);
		byte[] fileBytes = FileUtils.readBytes(fis);
		UploadBytesRequest asd = new UploadBytesRequest();
		asd.setFileBytes(fileBytes);
		asd.setSource(1);
		System.out.println("The file is now and " + asd.toString()
				+ "-------------------");
		return asd;
	}
}
