package com.mh.ormlitedemo.test.utils;
/**
 * 2016年1月26日
 * 			By---->JackHuang
 *
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatavalidationUtils {

	/**
	 * 判断是否存在特殊字符
	 * 用户名 判断
	 * @param str
	 * @return
	 */
	public static boolean isSpecialCharacter(String str){
		String regEx="[a-zA-Z][a-zA-Z0-9_]{0,15}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * 性别判断
	 * @param str
	 * @return
	 */
	public static boolean isSpecialCharacterSex(String str){
		String regEx="[\u4E00-\u9FA5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	/**
	 * 就诊人名判断
	 * @param str
	 * @return
	 */
	public static boolean isSpecialCharacter1(String str){
		String regEx="[\u4E00-\u9FA5]{2,5}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 判断是否是邮箱格式
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
//		String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){
		String strPattern = "1[34578]{1}\\d{9}$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static final String REGEX_CHINESE = "[\u4e00-\u9fa5]";

	public static boolean isRegexChinese(String str) {
		Pattern p = Pattern.compile(REGEX_CHINESE);
		Matcher m = p.matcher(str);
		int length = str.length();
		int count = 0;

		while (m.find()) {
			count++;
		}

		if (count < length) {
			return false;
		}

		return true;
	}

}
