package com.westear.ssm.common.util;

import java.security.MessageDigest;

/**
 * ������ܹ�����
 * @author westear
 *
 */
public class EncryptPsw {

	public EncryptPsw(){}
	
	private static final String KEY_MD = "MD5";
	
	private static final String KEY_SHA = "SHA-256";
	
	/**
	 * ����MD5���õ�32λ�����ַ���
	 * @param psw	����
	 * @return	32λ�����ַ���
	 */
	public static String encryptByMD(String psw){
		StringBuffer sb = new StringBuffer();
		try{
			MessageDigest md = MessageDigest.getInstance(KEY_MD);
			byte[] inputData = psw.getBytes("utf-8");
			md.update(inputData);
			byte byteData[] = md.digest();
			// ������ת��Ϊʮ������
			for(int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff)+0x100, 16).substring(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * ����SHA-256���õ�64λ�����ַ���
	 * @param psw	����
	 * @return	64λ�����ַ���
	 */
	public static String encryptBySHA(String psw){
		StringBuffer sb = new StringBuffer();
		try {
			byte[] inputData = psw.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
			messageDigest.update(inputData);
			byte byteData[] = messageDigest.digest();
			// ������ת��Ϊʮ������
			for(int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff)+0x100, 16).substring(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println("MD5���ܺ���ַ���:"+EncryptPsw.encryptByMD("test123")+"==="+EncryptPsw.encryptByMD("westear514*").length());
		System.out.println("SHA-256���ܺ���ַ���:"+EncryptPsw.encryptBySHA("test123*")+"==="+EncryptPsw.encryptBySHA("westear514*").length());
	}
}
