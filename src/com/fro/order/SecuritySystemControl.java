package com.fro.order;
/**
Copyright (C), 2013, FRO Tech., Ltd.
File name: ByteStringConvert 
Author: Chan Version: V1.0 Date: 2013-03-13 // ���ߡ��汾���������
Description: ��������ָ�������

Others: ��// �������ݵ�˵��
Function List: // ��Ҫ�����б�ÿ����¼Ӧ���������������ܼ�Ҫ˵��
1. onOrder
����ָ��
2��offOrder
����ָ��
3��checkOrder
��ѯ״ָ̬��
4��getStatus
������������
5��getStr
byteתString

History: // �޸���ʷ��¼�б�ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*/
public class SecuritySystemControl {

	/*************************************************
	Function: onOrder 
	Description: ����
	Return: ����ָ��
	*************************************************/
	public byte[] onOrder(){
		byte[] order = {0x58, 0x44, 0x02, 0x41};
		return order;
	}
	
	/*************************************************
	Function: offOrder 
	Description: ����
	Return: ����ָ��
	*************************************************/
	public byte[] offOrder(){
		byte[] order = {0x58, 0x44, 0x02, 0x44};
		return order;
	}
	
	/*************************************************
	Function: checkOrder 
	Description: ��ѯ״̬
	Return: ��ѯ״ָ̬��
	*************************************************/
	public byte[] checkOrder(){
		byte[] order = {0x58, 0x44, 0x02, 0x49};
		return order;
	}
	
	/*************************************************
	Function: getStatus 
	Description: ����״̬
	Return: ����״̬
	*************************************************/
	public String getStatus(byte[] b){

		int length_ = b.length;
		System.out.println(length_);
		String result = "";
		String str = "";
		String temp = "";
		for (int i = 0; i < length_ - 3; i++) {
			temp = getStr(b[i]) + getStr(b[i + 1]);
			System.out.println(temp);
			if (temp.equalsIgnoreCase("5844")) {//�жϰ�������״̬
				if (length_ - i > 3) {
					str = getStr(b[i+3]);
					if(str.equals("41")){//���������Ѳ���
						result += "securitySystem|on,";
					}
					else if(str.equals("44")){//���������ѳ���
						result += "securitySystem|off,";
					}
					i = i + 4;
				}
			} else if (temp.equalsIgnoreCase("5a4a")) {//�жϴ�����״̬
				if (length_ - i > 5) {
					result += "sensor|" + Integer.toString(b[i + 3],10)+",";
					i = i + 6;
				}else if (length_==4 ) {
					str = getStr(b[i+3]);
					if(str.equals("41")){//���������Ѳ���
						result += "securitySystem|on,";
					}
					else if(str.equals("44")){//���������ѳ���
						result += "securitySystem|off,";
					}
					i = i + 4;
				}
			}
		}
		return result.substring(0, result.length()-1);
	}
	
	/*************************************************
	Function: getStr 
	Description: byteתstring
	Return: string
	*************************************************/
	private String getStr(byte retval1) {
		String str = null;
		str = (Integer.toHexString(((retval1) & 0x000000FF) | 0xFFFFFF00)
				.substring(6));
		return str;
	}
	
	/**************test***********/
	public static void main(String[] args) {
		SecuritySystemControl ssc = new SecuritySystemControl();
		byte[] b = {0x58, 0x44, 0x02, 0x41, 0x00, 0x5a, 0x4a, 0x04, 0x1e, 0x01, 0x09, 0x00, 0x5a, 0x4a, 0x04, 0x07, 0x01, 0x09};
		System.out.println(ssc.getStatus(b));
	}
}
